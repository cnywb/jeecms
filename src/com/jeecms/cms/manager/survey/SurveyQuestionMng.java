package com.jeecms.cms.manager.survey;

import java.util.List;

import com.jeecms.cms.entity.survey.SurveyQuestion;

public interface SurveyQuestionMng {
	public List<SurveyQuestion> findAllByGroupId(Long groupId);
	public List<SurveyQuestion> findAllBySurveyId(Long surveyId);

}
