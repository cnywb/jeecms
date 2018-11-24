package com.jeecms.cms.manager.survey;

import java.util.List;

import com.jeecms.cms.entity.survey.SurveyQuestionGroup;

public interface SurveyQuestionGroupMng {
	public List<SurveyQuestionGroup> findAllBySurveyId(Long surveyId);

}
