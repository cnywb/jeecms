package com.jeecms.cms.manager.survey;

import com.jeecms.cms.entity.survey.Survey;

public interface SurveyMng {
	public Survey findByCode(String code);
	public Integer getCarOwnerType(Integer userId);
}
