package com.jeecms.cms.dao.impl.survey;

import java.util.List;

import com.jeecms.cms.dao.survey.SurveyQuestionDao;
import com.jeecms.cms.entity.survey.SurveyQuestion;
import com.jeecms.common.hibernate3.HibernateBaseDao;

public class SurveyQuestionDaoImpl extends
		HibernateBaseDao<SurveyQuestion, Long> implements SurveyQuestionDao {

	@Override
	protected Class<SurveyQuestion> getEntityClass() {
		// TODO Auto-generated method stub
		return SurveyQuestion.class;
	}
	
	
	public List<SurveyQuestion> findAllByGroupId(Long groupId){
		String hql = "select bean from SurveyQuestion bean where bean.group.id=?";
		List<SurveyQuestion> list=find(hql,groupId);
		return list;
	}

	
	public List<SurveyQuestion> findAllBySurveyId(Long surveyId){
		String hql = "select bean from SurveyQuestion bean where bean.group.survey.id=? order by bean.group.id asc,bean.sequence asc";
		List<SurveyQuestion> list=find(hql,surveyId);
		return list;
	}
}
