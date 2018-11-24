/**
 * 
 */
package com.jeecms.wxpoint.vo;

import java.util.List;

import com.jeecms.cms.entity.survey.SurveyAnswerSheet;

/**
 * 
 * 问卷提交VO
 * @author wanglijun
 *
 */
public class SurveyAnswerVo{
	
	/**微信的ID*/
	private String openId;

	/**
	 * @return the openId
	 */
	public String getOpenId() {
		return openId;
	}

	/**
	 * @param openId the openId to set
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	
private String surveyCode;
	
	private List<SurveyAnswerSheet> answerSheetlist;

	public String getSurveyCode() {
		return surveyCode;
	}

	public void setSurveyCode(String surveyCode) {
		this.surveyCode = surveyCode;
	}

	public List<SurveyAnswerSheet> getAnswerSheetlist() {
		return answerSheetlist;
	}

	public void setAnswerSheetlist(List<SurveyAnswerSheet> answerSheetlist) {
		this.answerSheetlist = answerSheetlist;
	}
}
