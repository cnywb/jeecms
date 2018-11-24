package com.jeecms.cms.dao.survey;

import java.util.List;

import com.jeecms.cms.entity.survey.SurveyQuestionGroup;

public interface SurveyQuestionGroupDao {
	public List<SurveyQuestionGroup> findAllBySurveyId(Long surveyId);

}
