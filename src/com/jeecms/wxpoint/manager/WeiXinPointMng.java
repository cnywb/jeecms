/**
 * 
 */
package com.jeecms.wxpoint.manager;

import javax.servlet.http.HttpServletRequest;

import com.jeecms.cms.entity.survey.SurveyResponse;
import com.jeecms.wxpoint.vo.SurveyAnswerVo;

/**
 *
 * 微信用户信息获取
 * @author wanglijun
 *
 */
public interface WeiXinPointMng {
	/***
	 * 问卷调查提交答案信息
	 * @param answerSheetRequest
	 * @param request
	 * @return
	 */
	public SurveyResponse submitAnswers(SurveyAnswerVo surveyAnswerVo,HttpServletRequest request);
}
