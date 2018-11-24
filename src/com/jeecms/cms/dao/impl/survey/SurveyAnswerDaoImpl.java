package com.jeecms.cms.dao.impl.survey;

import java.util.List;

import com.jeecms.cms.dao.survey.SurveyAnswerDao;
import com.jeecms.cms.entity.survey.SurveyAnswer;
import com.jeecms.common.hibernate3.HibernateBaseDao;

public class SurveyAnswerDaoImpl extends HibernateBaseDao<SurveyAnswer, Long>
		implements SurveyAnswerDao {

	@Override
	protected Class<SurveyAnswer> getEntityClass() {
		// TODO Auto-generated method stub
		return SurveyAnswer.class;
	}
	
	
	public List<SurveyAnswer> findAllByQuestionId(Long questionId){
		String hql = "select bean from SurveyAnswer bean where bean.question.id=? order by bean.sequence asc";
		List<SurveyAnswer> list=find(hql,questionId);
		return list;
	}

}
