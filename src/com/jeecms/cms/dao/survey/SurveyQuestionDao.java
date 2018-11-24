package com.jeecms.cms.dao.survey;

import java.util.List;

import com.jeecms.cms.entity.survey.SurveyQuestion;

public interface SurveyQuestionDao {
	
	public List<SurveyQuestion> findAllByGroupId(Long groupId);
	public List<SurveyQuestion> findAllBySurveyId(Long surveyId);

}
