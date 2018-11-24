package com.jeecms.cms.manager.maintenance;

import java.util.List;

import com.jeecms.cms.entity.maintenance.MaintenancePackage;
import com.jeecms.cms.entity.maintenance.MaintenancePackageOrder;

public interface MaintenanceMng {

	
	
	public List<MaintenancePackageOrder> findAllMaintenancePackageOrder(String vin,String productType);
	
	/**
	 * 通过车型查询长安福特原厂保养套餐价格。
	 * @param model
	 * @return
	 */
	public List<MaintenancePackage> findMaintenancePackage(String model);
	
	
	
	
	/**
	 * 查找所有车型,用来为findMaintenancePackage传参
	 * @return
	 */
	public List<String> findAllCarModel();
	
	public List<MaintenancePackage> findMaintenancePackage(String model,String packageType);
	
	
	public List<String> findAllCarModel(String packageType);
}
