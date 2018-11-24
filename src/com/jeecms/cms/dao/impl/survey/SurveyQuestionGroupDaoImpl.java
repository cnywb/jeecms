package com.jeecms.cms.dao.impl.survey;

import java.util.List;

import com.jeecms.cms.dao.survey.SurveyQuestionGroupDao;
import com.jeecms.cms.entity.survey.SurveyQuestionGroup;
import com.jeecms.common.hibernate3.HibernateBaseDao;

public class SurveyQuestionGroupDaoImpl extends
		HibernateBaseDao<SurveyQuestionGroup, Long> implements
		SurveyQuestionGroupDao {

	@Override
	protected Class<SurveyQuestionGroup> getEntityClass() {
		// TODO Auto-generated method stub
		return SurveyQuestionGroup.class;
	}
	
	public List<SurveyQuestionGroup> findAllBySurveyId(Long surveyId){
		String hql = "select bean from SurveyQuestionGroup bean where bean.survey.id=?";
		List<SurveyQuestionGroup> list=find(hql,surveyId);
		return list;
	}
	
	

}
