package com.jeecms.cms.manager.impl.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.survey.SurveyDao;
import com.jeecms.cms.entity.survey.Survey;
import com.jeecms.cms.manager.survey.SurveyMng;


@Service
@Transactional(rollbackFor=Exception.class) 
public class SurveyMngImpl implements SurveyMng {
	
	@Autowired
	private SurveyDao surveyDao;
	
	public Survey findByCode(String code){
		return surveyDao.findByCode(code);
	}

	/**
	 *得到车主类型
	 *1新车主
	 *2活跃车主
	 *3摇摆车主
	 *4其他车主
	 */
	public Integer getCarOwnerType(Integer userId){
		return surveyDao.getCarOwnerType(userId);
	}
}
