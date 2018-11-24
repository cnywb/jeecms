package com.jeecms.cms.dao.impl.infocollection;

import java.util.List;

import com.jeecms.cms.dao.infocollection.PotentialCustomerInfoDao;
import com.jeecms.cms.entity.infocollection.PotentialCustomerInfo;
import com.jeecms.cms.entity.survey.SurveyAnswer;
import com.jeecms.common.hibernate3.HibernateBaseDao;

public class PotentialCustomerInfoDaoImpl extends
		HibernateBaseDao<PotentialCustomerInfo, Long> implements PotentialCustomerInfoDao{

	@Override
	protected Class<PotentialCustomerInfo> getEntityClass() {
		// TODO Auto-generated method stub
		return PotentialCustomerInfo.class;
	}
	
    public long add(PotentialCustomerInfo t){
    	if(checkBeforeAdd(t)==false){
    	return 0L;	
    	}
        getSession().save(t);
    	return t.getId()==null?0L:t.getId();
	}
    
    public boolean checkBeforeAdd(PotentialCustomerInfo t){
    	String hql = "select bean from PotentialCustomerInfo bean where bean.customerMobilePhoneNo=? and bean.activityName=? ";
		List<PotentialCustomerInfo> list=find(hql,t.getCustomerMobilePhoneNo(),t.getActivityName());
		return list.size()==0;
    }
    

}
