package com.jeecms.cms.dao.maintenance;

import java.util.List;

import com.jeecms.cms.entity.maintenance.MaintenancePackageOrder;

public interface MaintenancePackageOrderDao {
	
	
	
	public List<MaintenancePackageOrder> findAll(String vin,String productType);

}
