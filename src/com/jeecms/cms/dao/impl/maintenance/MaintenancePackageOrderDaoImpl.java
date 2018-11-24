package com.jeecms.cms.dao.impl.maintenance;

import java.util.List;

import com.jeecms.cms.dao.maintenance.MaintenancePackageOrderDao;
import com.jeecms.cms.entity.maintenance.MaintenancePackageOrder;
import com.jeecms.common.hibernate3.HibernateBaseDao;

public class MaintenancePackageOrderDaoImpl extends
		HibernateBaseDao<MaintenancePackageOrder, Long> implements
		MaintenancePackageOrderDao {

	@Override
	protected Class<MaintenancePackageOrder> getEntityClass() {
		// TODO Auto-generated method stub
		return MaintenancePackageOrder.class;
	}
	
	
	
	
	

	public List<MaintenancePackageOrder> findAll(String vin,String productType){
		String hql = "select bean from MaintenancePackageOrder bean where bean.vin=? and bean.productType=?";
		return find(hql,vin,productType);
	}
	
}
