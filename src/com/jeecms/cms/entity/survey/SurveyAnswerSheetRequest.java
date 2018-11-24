package com.jeecms.cms.entity.survey;

import java.util.List;

public class SurveyAnswerSheetRequest {
	
	private String surveyCode;
	
	private String unitAnswerCode;//答案中作为维一属性的答案代码
	
	private String unitAnswerValue;//答案中作为维一属性的答案值(answerMemo)
	
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

	public String getUnitAnswerCode() {
		return unitAnswerCode;
	}

	public void setUnitAnswerCode(String unitAnswerCode) {
		this.unitAnswerCode = unitAnswerCode;
	}

	public String getUnitAnswerValue() {
		return unitAnswerValue;
	}

	public void setUnitAnswerValue(String unitAnswerValue) {
		this.unitAnswerValue = unitAnswerValue;
	}

	
	
	
	
	

}
