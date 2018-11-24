package com.jeecms.cms.dao.impl.survey;


import com.jeecms.cms.dao.survey.SurveyLogDao;
import com.jeecms.cms.entity.survey.SurveyLog;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;

public class SurveyLogDaoImpl extends HibernateBaseDao<SurveyLog, Long>
		implements SurveyLogDao {

	@Override
	protected Class<SurveyLog> getEntityClass() {
		// TODO Auto-generated method stub
		return SurveyLog.class;
	}
	
	public int  getTotalCountBySurveyIdAndUserId(long surveyId,Integer userId){
		Finder f = Finder.create("from SurveyLog bean where bean.survey.id=:surveyId and bean.user.id=:userId");
		f.setParam("surveyId",surveyId);
		f.setParam("userId",userId);
		return 	countQueryResult(f);
	}

	@Override
	public void add(SurveyLog t) {
		getSession().save(t);
	}
	

}
