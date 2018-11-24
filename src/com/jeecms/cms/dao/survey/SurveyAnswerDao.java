package com.jeecms.cms.dao.survey;

import java.util.List;

import com.jeecms.cms.entity.survey.SurveyAnswer;

public interface SurveyAnswerDao {
	
	public List<SurveyAnswer> findAllByQuestionId(Long questionId);

}
