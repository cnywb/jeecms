package com.jeecms.cms.manager.impl.survey;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.survey.SurveyQuestionGroupDao;
import com.jeecms.cms.entity.survey.SurveyQuestionGroup;
import com.jeecms.cms.manager.survey.SurveyQuestionGroupMng;


@Service
@Transactional(rollbackFor=Exception.class) 
public class SurveyQuestionGroupMngImpl implements SurveyQuestionGroupMng {

	@Autowired
	private SurveyQuestionGroupDao surveyQuestionGroupDao;
	
	public List<SurveyQuestionGroup> findAllBySurveyId(Long surveyId){
		return surveyQuestionGroupDao.findAllBySurveyId(surveyId);
	}
	
}
