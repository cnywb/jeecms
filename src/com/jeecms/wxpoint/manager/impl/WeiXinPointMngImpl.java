/**
 * 
 */
package com.jeecms.wxpoint.manager.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeecms.cms.dao.survey.SurveyAnswerSheetDao;
import com.jeecms.cms.dao.survey.SurveyDao;
import com.jeecms.cms.dao.survey.SurveyLogDao;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.survey.Survey;
import com.jeecms.cms.entity.survey.SurveyAnswerSheet;
import com.jeecms.cms.entity.survey.SurveyLog;
import com.jeecms.cms.entity.survey.SurveyResponse;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.point.manager.point.PointCalculateMng;
import com.jeecms.point.manager.point.PointUserMng;
import com.jeecms.wxpoint.manager.WeiXinPointMng;
import com.jeecms.wxpoint.vo.SurveyAnswerVo;

/**
 * @author wanglijun
 *
 */
@Service
public class WeiXinPointMngImpl implements WeiXinPointMng {
	/**日志*/
	private static final Logger logger=LoggerFactory.getLogger(WeiXinPointMngImpl.class);
	
	@Autowired
	private SurveyAnswerSheetDao  surveyAnswerSheetDao;
	@Autowired
	private SurveyLogDao surveyLogDao;
	
	@Autowired
	private SurveyDao surveyDao;
	
	@Autowired
	private PointCalculateMng pointCalculateMng;
	/**积分用户*/
	@Autowired
	private PointUserMng pointUserMng;
	/**用户类*/
	@Autowired
	private CmsUserMng cmsUserMng;
	/* (non-Javadoc)
	 * @see com.jeecms.wxpoint.manager.WeiXinPointMng#submitAnswers(com.jeecms.wxpoint.vo.SurveyAnswerVo, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public SurveyResponse submitAnswers(SurveyAnswerVo surveyAnswerVo,HttpServletRequest request) {
		SurveyResponse res=new SurveyResponse();
        
		logger.info("openId:"+surveyAnswerVo.getOpenId());
		UnifiedUser unifiedUser=pointUserMng.findByOpenId(surveyAnswerVo.getOpenId());
		CmsUser user=null;
		
		//没有
		if(unifiedUser==null){
			logger.info("----------------");
			res.setStatus(0);
			res.setMessage("用户未绑定!");
			return res;
		}
		logger.info("unifiedUser:"+unifiedUser.getId());
		user=cmsUserMng.findById(unifiedUser.getId());
		
		if(user==null){
			res.setStatus(0);
			res.setMessage("用户未绑定!");
			return res;
		}
		Survey t=surveyDao.findByCode(surveyAnswerVo.getSurveyCode());
		Date currentTime=new Date();
		if(t==null){
			res.setStatus(0);
			res.setMessage("该调研不存在！");
			return res;
    	}
		if(t!=null&&(currentTime.getTime()<t.getStartTime().getTime())){
			res.setStatus(0);
			res.setMessage("该调研还未开始！");
			return res;
		}
		if(t!=null&&(currentTime.getTime()>t.getEndTime().getTime())){
			res.setStatus(0);
			res.setMessage("该调研已经结束！");
			return res;
		}
		if(t!=null&&t.getStatus()==0){
			res.setStatus(0);
			res.setMessage("该调研已经停用！");
			return res;
		}
		if(t!=null&&t.getParticipantType()==1&&user.getGroup().getId()!=2L){
			res.setStatus(0);
			res.setMessage("该调研仅限认证车主参与！");
			return res;
		}
		int totalCount=surveyLogDao.getTotalCountBySurveyIdAndUserId(t.getId(), user.getId());
		if(totalCount>0){
			res.setStatus(0);
			res.setMessage("已经参与过本调研,请勿重复参与！");
			return res;
		}
		List<SurveyAnswerSheet> list=surveyAnswerVo.getAnswerSheetlist();
		for(SurveyAnswerSheet s:list){
			s.setUser(user);
		}
		surveyAnswerSheetDao.addBatch(list);
		SurveyLog log=new SurveyLog();
		log.setSurvey(t);
		log.setUser(user);
		surveyLogDao.add(log);		
		res.setStatus(1);
		Long point=pointCalculateMng.questionaryPoint(user.getId()).getNum();//添加积分
		res.setPoint(point);
		res.setMessage("提交成功!获取"+point+"积分!");
		return res;
	}
	
	
}
