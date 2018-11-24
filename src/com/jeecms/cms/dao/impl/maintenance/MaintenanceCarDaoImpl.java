package com.jeecms.cms.dao.impl.maintenance;

import java.util.List;

import com.jeecms.cms.dao.maintenance.MaintenanceCarDao;
import com.jeecms.cms.entity.maintenance.MaintenanceCar;
import com.jeecms.common.hibernate3.HibernateBaseDao;

public class MaintenanceCarDaoImpl extends
		HibernateBaseDao<MaintenanceCar, Long> implements MaintenanceCarDao {

	@Override
	protected Class<MaintenanceCar> getEntityClass() {
		// TODO Auto-generated method stub
		return MaintenanceCar.class;
	}
	
	public MaintenanceCar find(String vehicleLine){
		String hql = "select bean from MaintenanceCar bean where bean.vehicleLine=?";
		List<MaintenanceCar> list=find(hql,vehicleLine);
		return list.size()==0?null:list.get(0);
	}

}
