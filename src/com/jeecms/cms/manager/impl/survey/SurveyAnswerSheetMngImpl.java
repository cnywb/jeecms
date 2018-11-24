package com.jeecms.cms.manager.impl.survey;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.survey.SurveyAnswerSheetDao;
import com.jeecms.cms.dao.survey.SurveyDao;
import com.jeecms.cms.dao.survey.SurveyLogDao;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.survey.Survey;
import com.jeecms.cms.entity.survey.SurveyAnswerSheet;
import com.jeecms.cms.entity.survey.SurveyAnswerSheetRequest;
import com.jeecms.cms.entity.survey.SurveyLog;
import com.jeecms.cms.entity.survey.SurveyResponse;
import com.jeecms.cms.manager.survey.SurveyAnswerSheetMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.point.manager.point.PointCalculateMng;



@Service
@Transactional(rollbackFor=Exception.class) 
public class SurveyAnswerSheetMngImpl implements SurveyAnswerSheetMng {
	
	@Autowired
	private SurveyAnswerSheetDao  surveyAnswerSheetDao;
	@Autowired
	private SurveyLogDao surveyLogDao;
	
	@Autowired
	private SurveyDao surveyDao;
	
	@Autowired
	private PointCalculateMng pointCalculateMng;
	
	
	
	/**
	 * 提交调研答案
	 * @param answerSheetRequest
	 * @param request
	 * @return
	 */
	public SurveyResponse submitAnswers(SurveyAnswerSheetRequest answerSheetRequest,HttpServletRequest request){
		SurveyResponse res=new SurveyResponse();
        CmsUser user = CmsUtils.getUser(request);
		if(user==null){
			res.setStatus(0);
			res.setMessage("操作失败,用户未登录!");
			return res;
		}
		Survey t=surveyDao.findByCode(answerSheetRequest.getSurveyCode());
		Date currentTime=new Date();
		if(t==null){
			res.setStatus(0);
			res.setMessage("操作失败,该调研不存在！");
			return res;
    	}
		if(t!=null&&(currentTime.getTime()<t.getStartTime().getTime())){
			res.setStatus(0);
			res.setMessage("操作失败,该调研还未开始！");
			return res;
		}
		if(t!=null&&(currentTime.getTime()>t.getEndTime().getTime())){
			res.setStatus(0);
			res.setMessage("操作失败,该调研已经结束！");
			return res;
		}
		if(t!=null&&t.getStatus()==0){
			res.setStatus(0);
			res.setMessage("操作失败,该调研已经停用！");
			return res;
		}
		if(t!=null&&t.getParticipantType()==1&&user.getGroup().getId()!=2L){
			res.setStatus(0);
			res.setMessage("操作失败,该调研仅限认证车主参与！");
			return res;
		}
		int totalCount=surveyLogDao.getTotalCountBySurveyIdAndUserId(t.getId(), user.getId());
		if(totalCount>0){
			res.setStatus(0);
			res.setMessage("操作失败,当前用户已经参与过本调研,请勿重复参与！");
			return res;
		}
		List<SurveyAnswerSheet> list=answerSheetRequest.getAnswerSheetlist();
		for(SurveyAnswerSheet s:list){
			s.setUser(user);
		}
		surveyAnswerSheetDao.addBatch(list);
		SurveyLog log=new SurveyLog();
		log.setSurvey(t);
		log.setUser(user);
		surveyLogDao.add(log);
		res.setMessage("操作成功!");
		res.setStatus(1);
		Long point=pointCalculateMng.questionaryPoint(user.getId()).getNum();//添加积分
		res.setPoint(point);
		return res;
		
	}
	
	/**
	 * 匿名提交
	 * @param answerSheetRequest
	 * @param request
	 * @return
	 */
	public SurveyResponse addAnswerSheetForAnonymous(SurveyAnswerSheetRequest answerSheetRequest,HttpServletRequest request){
		SurveyResponse res=new SurveyResponse();
        CmsUser user =new  CmsUser();
        user.setId(1);
	    Survey t=surveyDao.findByCode(answerSheetRequest.getSurveyCode());
		Date currentTime=new Date();
		if(t==null){
			res.setStatus(0);
			res.setMessage("操作失败,该调研不存在！");
			return res;
    	}
		if(t!=null&&(currentTime.getTime()<t.getStartTime().getTime())){
			res.setStatus(1);
			res.setMessage("操作失败,该调研还未开始！");
			return res;
		}
		if(t!=null&&(currentTime.getTime()>t.getEndTime().getTime())){
			res.setStatus(2);
			res.setMessage("操作失败,该调研已经结束！");
			return res;
		}
		if(t!=null&&t.getStatus()==0){
			res.setStatus(3);
			res.setMessage("操作失败,该调研已经停用！");
			return res;
		}
		if(!StringUtils.isEmpty(answerSheetRequest.getUnitAnswerCode())&&!StringUtils.isEmpty(answerSheetRequest.getUnitAnswerValue())){
			Integer size=surveyAnswerSheetDao.findByUnitCondiction(answerSheetRequest.getUnitAnswerCode(), answerSheetRequest.getUnitAnswerValue()).size();
			if(size.intValue()>0){
				res.setStatus(4);
				res.setMessage("操作失败,重复的提交,"+answerSheetRequest.getUnitAnswerCode()+"的值不能重复!");
				return res;
			}
		}
		
		List<SurveyAnswerSheet> list=answerSheetRequest.getAnswerSheetlist();
		for(SurveyAnswerSheet s:list){
			s.setUser(user);
			if(!StringUtils.isEmpty(answerSheetRequest.getUnitAnswerCode())&&!StringUtils.isEmpty(answerSheetRequest.getUnitAnswerValue())){
				s.setSheetNo(answerSheetRequest.getUnitAnswerValue());
			}
		}
		surveyAnswerSheetDao.addBatchForAnonymous(list);
		res.setMessage("操作成功!");
		res.setStatus(5);
		return res;
		
	}

}
