package com.jeecms.cms.manager.impl.survey;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.cms.dao.survey.SurveyAnswerDao;
import com.jeecms.cms.dao.survey.SurveyQuestionDao;
import com.jeecms.cms.entity.survey.SurveyQuestion;
import com.jeecms.cms.manager.survey.SurveyQuestionMng;

public class SurveyQuestionMngImpl implements SurveyQuestionMng {
	
	@Autowired
	private SurveyQuestionDao surveyQuestionDao;
	@Autowired
	private SurveyAnswerDao surveyAnswerDao;
	
	public List<SurveyQuestion> findAllByGroupId(Long groupId){
		List<SurveyQuestion> list=surveyQuestionDao.findAllByGroupId(groupId);
		for(SurveyQuestion q:list){
			q.setAnswerList(surveyAnswerDao.findAllByQuestionId(q.getId()));
		}
		return list;
	}
	
	public List<SurveyQuestion> findAllBySurveyId(Long surveyId){
		List<SurveyQuestion> list=surveyQuestionDao.findAllBySurveyId(surveyId);
		for(SurveyQuestion q:list){
			q.setAnswerList(surveyAnswerDao.findAllByQuestionId(q.getId()));
		}
		return list;
	}

}
