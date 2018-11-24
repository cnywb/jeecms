package com.jeecms.cms.action.survey;

import java.util.Date;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.survey.Survey;
import com.jeecms.cms.entity.survey.SurveyAnswerSheetRequest;

import com.jeecms.cms.manager.survey.SurveyAnswerSheetMng;
import com.jeecms.cms.manager.survey.SurveyMng;
import com.jeecms.cms.manager.survey.SurveyQuestionGroupMng;
import com.jeecms.cms.manager.survey.SurveyQuestionMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.util.JSONUtil;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.point.manager.point.PointCalculateMng;


@Controller@Scope("prototype")
@RequestMapping("/survey/*")
public class SurveyAct {
	
	@Autowired
	private SurveyMng surveyMng;
	

	
	@Autowired
	private SurveyQuestionMng surveyQuestionMng;
	
	@Autowired
	private SurveyAnswerSheetMng  surveyAnswerSheetMng;
	
	@Autowired
	private PointCalculateMng pointCalculateMng;
	
	@RequestMapping(value="index.jspx")
	public String index(HttpServletRequest request,ModelMap model,String code) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		model.put("code",code);
		if (user == null) {
			return "/WEB-INF/t/cms/www/red/survey/index.html";
		}
		Date currentTime=new Date();
	
		Integer carOwnerType=surveyMng.getCarOwnerType(user.getId());
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
		if(t!=null&&t.getParticipantType()==1&&user.getGroup().getId()!=2L){
			model.addAttribute("enable",0);
			model.addAttribute("msg","您未完成车主认证，请先完成车主认证！" );
		}
		model.addAttribute("t", t);
		Boolean canAddPoint=pointCalculateMng.questionaryPointLimit(user.getId()).isResult();
		model.addAttribute("canAddPoint", canAddPoint);
		return "/WEB-INF/t/cms/www/red/survey/index.html";
	}

	
	
	@RequestMapping(value="findAllQuestionByGroupId.jspx")
	public void findAllQuestionByGroupId(HttpServletResponse response,HttpServletRequest request,ModelMap model,Long groupId) {
		String json=JSONUtil.objectToJson(surveyQuestionMng.findAllByGroupId(groupId));
		ResponseUtils.renderJson(response,json);
	}
	
	@RequestMapping(value="findAllQuestionBySurveyId.jspx")
	public void findAllQuestionBySurveyId(HttpServletResponse response,HttpServletRequest request,ModelMap model,Long surveyId) {
		String json=JSONUtil.objectToJson(surveyQuestionMng.findAllBySurveyId(surveyId));
		ResponseUtils.renderJson(response,json);
	}
	
	@RequestMapping(value="submitAnswers.jspx")
	public void submitAnswers(HttpServletResponse response,SurveyAnswerSheetRequest answerSheetRequest,HttpServletRequest request,ModelMap model){
		String json=JSONUtil.objectToJson(surveyAnswerSheetMng.submitAnswers(answerSheetRequest, request));
		ResponseUtils.renderJson(response,json);
	}
	
	@RequestMapping(value="addAnswerSheetForAnonymous.jspx")
	public void addAnswerSheetForAnonymous(HttpServletResponse response,SurveyAnswerSheetRequest answerSheetRequest,HttpServletRequest request,ModelMap model){
		String json=JSONUtil.objectToJson(surveyAnswerSheetMng.addAnswerSheetForAnonymous(answerSheetRequest, request));
		ResponseUtils.renderJson(response,json);
	}
	
}
