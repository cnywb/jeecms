/**
 * 
 */
package com.jeecms.wxpoint.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.survey.Survey;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.cms.manager.survey.SurveyMng;
import com.jeecms.cms.manager.survey.SurveyQuestionMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.common.util.JSONUtil;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.point.manager.point.PointCalculateMng;
import com.jeecms.point.manager.point.PointUserMng;
import com.jeecms.wxpoint.manager.WeiXinPointMng;
import com.jeecms.wxpoint.vo.SurveyAnswerVo;

/**
 * 
 * 问卷调查的信息
 * @author wanglijun
 *
 */
@Controller
@RequestMapping("/wxpoints/survery")
public class WeiXinSurveyAction {
	
	/**问卷调查的首页*/
	private static final  String  INDEX_RETURN="/WEB-INF/t/cms/www/red/wxpoints/questionnaire.html";
	/**用户类*/
	@Autowired
	private PointUserMng pointUserMng;	
	/**用户类*/
	@Autowired
	private CmsUserMng cmsUserMng;
	/**问卷调查类*/
	@Autowired
	private SurveyMng surveyMng;
	/**积分计算*/
	@Autowired
	private PointCalculateMng pointCalculateMng;
	/**问卷项*/
	@Autowired
	private SurveyQuestionMng surveyQuestionMng;
	
	@Autowired
	private WeiXinPointMng weiXinPointMng;
	
    /**首页显示*/
	@RequestMapping(value = "/index.jhtml")
	public String index(String openId,String code,HttpServletRequest request, Model model){
		model.addAttribute("openId",openId);
		CmsSite site = CmsUtils.getSite(request);
		UnifiedUser unifiedUser=pointUserMng.findByOpenId(openId);
		if(unifiedUser==null){
			model.addAttribute("enable",0);
			model.addAttribute("msg","未关注微信");
			model.addAttribute("canAddPoint",'1');
			return INDEX_RETURN;
		}
		CmsUser user=cmsUserMng.findById(unifiedUser.getId());	 
		Integer carOwnerType=surveyMng.getCarOwnerType(unifiedUser.getId());
		model.addAttribute("code", code);
		if("CZZM".equals(code)){
            if(carOwnerType!=null&&carOwnerType.intValue()==1){
            	code="CZZM_001";
			}else if(carOwnerType!=null&&carOwnerType.intValue()==2){
				code="CZZM_002";
			}else if(carOwnerType!=null&&carOwnerType.intValue()==3){
				code="CZZM_003";
			}else if(carOwnerType!=null&&carOwnerType.intValue()==4){
				code="CZZM_004";
			}else{
				code="CZZM_004";
			}
		}
		
		Date currentTime=new Date();
		
		Survey t=surveyMng.findByCode(code);
		
		model.addAttribute("enable",1);
		if(t==null){
			model.addAttribute("enable",0);
			model.addAttribute("msg","该调研不存在！" );
		}
		if(t!=null&&(currentTime.getTime()<t.getStartTime().getTime())){
			model.addAttribute("enable",0);
			model.addAttribute("msg","该调研还未开始！" );
		}
		if(t!=null&&(currentTime.getTime()>t.getEndTime().getTime())){
			model.addAttribute("enable",0);
			model.addAttribute("msg","该调研已经结束！" );
		}
		if(t!=null&&t.getStatus()==0){
			model.addAttribute("enable",0);
			model.addAttribute("msg","该调研已经停用！" );
		}
		if(unifiedUser!=null){
			model.addAttribute("user", user);
			if(t!=null&&t.getParticipantType()==1&&user.getGroup().getId()!=2L){
				model.addAttribute("enable",0);
				model.addAttribute("msg","您未完成车主认证，请先完成车主认证！" );
			}
			Boolean canAddPoint=pointCalculateMng.questionaryPointLimit(user.getId()).isResult();
			model.addAttribute("canAddPoint", canAddPoint?1:0);
		}
		model.addAttribute("t", t);
		return INDEX_RETURN;
	}
	
	/**问卷选项*/
	@RequestMapping(value="question.jhtml")
	public void findAllQuestionBySurveyId(HttpServletResponse response,HttpServletRequest request,ModelMap model,Long surveyId) {
		String json=JSONUtil.objectToJson(surveyQuestionMng.findAllBySurveyId(surveyId));
		ResponseUtils.renderJson(response,json);
	}
	
	/***
	 * 
	 * @param response
	 * @param surveyAnswer
	 * @param request
	 * @param model
	 */
	@RequestMapping(value="answers.jhtml")
	public void submitAnswers(HttpServletResponse response,SurveyAnswerVo surveyAnswer,HttpServletRequest request,ModelMap model){
		String json=JSONUtil.objectToJson(weiXinPointMng.submitAnswers(surveyAnswer, request));
		ResponseUtils.renderJson(response,json);
	}
}
