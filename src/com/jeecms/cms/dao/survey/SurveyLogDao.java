package com.jeecms.cms.dao.survey;

import com.jeecms.cms.entity.survey.SurveyLog;

public interface SurveyLogDao {
	public int  getTotalCountBySurveyIdAndUserId(long surveyId,Integer userId);
	public void add(SurveyLog t);
}
