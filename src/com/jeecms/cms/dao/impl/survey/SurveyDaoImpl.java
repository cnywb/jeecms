package com.jeecms.cms.dao.impl.survey;

import java.math.BigDecimal;
import java.util.List;

import com.jeecms.cms.dao.survey.SurveyDao;
import com.jeecms.cms.entity.survey.Survey;
import com.jeecms.common.hibernate3.HibernateBaseDao;

public class SurveyDaoImpl extends HibernateBaseDao<Survey, Long> implements
		SurveyDao {

	@Override
	protected Class<Survey> getEntityClass() {
		// TODO Auto-generated method stub
		return Survey.class;
	}
	
	
	public Survey findByCode(String code){
		String hql = "select bean from Survey bean where bean.code=?";
		List<Survey> list=find(hql,code);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	
	public Integer getCarOwnerType(Integer userId){
		BigDecimal retVal=(BigDecimal)getSession().createSQLQuery("select GET_CAR_OWNER_TYPE(?) from dual").setInteger(0,userId).uniqueResult();
		return retVal==null?null:retVal.intValue();
	}
}
