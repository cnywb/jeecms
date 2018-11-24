package com.jeecms.cms.manager.survey;

import javax.servlet.http.HttpServletRequest;

import com.jeecms.cms.entity.survey.SurveyAnswerSheetRequest;
import com.jeecms.cms.entity.survey.SurveyResponse;

public interface SurveyAnswerSheetMng {
	
	public SurveyResponse submitAnswers(SurveyAnswerSheetRequest answerSheetRequest,HttpServletRequest request);
	public SurveyResponse addAnswerSheetForAnonymous(SurveyAnswerSheetRequest answerSheetRequest,HttpServletRequest request);

}
