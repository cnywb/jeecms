package com.jeecms.cms.dao.survey;

import com.jeecms.cms.entity.survey.Survey;

public interface SurveyDao {
	public Survey findByCode(String code);
	public Integer getCarOwnerType(Integer userId);

}
