package com.jeecms.cms.dao.impl.recall;

import java.util.List;



import com.jeecms.cms.dao.recall.CarRecallDao;
import com.jeecms.cms.entity.recall.CarRecall;
import com.jeecms.common.hibernate3.HibernateBaseDao;



public class CarRecallDaoImpl  extends HibernateBaseDao<CarRecall, Long> implements CarRecallDao {

	@Override
	protected Class<CarRecall> getEntityClass() {
		// TODO Auto-generated method stub
		return CarRecall.class;
	}
	
	public List<CarRecall>findByVin(String vin){
		String hql = "select bean from CarRecall bean where bean.vin=? order by bean.id asc";
		List<CarRecall> list=find(hql,vin);
		return list;
	}

}
