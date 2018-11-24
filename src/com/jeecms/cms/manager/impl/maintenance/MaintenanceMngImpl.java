package com.jeecms.cms.manager.impl.maintenance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.cms.dao.maintenance.MaintenanceCarDao;
import com.jeecms.cms.dao.maintenance.MaintenancePackageDao;
import com.jeecms.cms.dao.maintenance.MaintenancePackageOrderDao;
import com.jeecms.cms.entity.maintenance.MaintenanceCar;
import com.jeecms.cms.entity.maintenance.MaintenancePackage;
import com.jeecms.cms.entity.maintenance.MaintenancePackageOrder;
import com.jeecms.cms.manager.maintenance.MaintenanceMng;

public class MaintenanceMngImpl implements MaintenanceMng {

	
	@Autowired
	private MaintenanceCarDao maintenanceCarDao;
	@Autowired
	private MaintenancePackageDao maintenancePackageDao;
	@Autowired
	private MaintenancePackageOrderDao maintenancePackageOrderDao;
	
	
	
	
	public List<MaintenancePackageOrder> findAllMaintenancePackageOrder(String vin,String productType){
		List<MaintenancePackageOrder> list=maintenancePackageOrderDao.findAll(vin.toUpperCase(),productType);
		for(MaintenancePackageOrder mpo:list){
			MaintenanceCar mc=maintenanceCarDao.find(mpo.getVehicleLine());//查出车型
			if(mc!=null){
				mpo.setModel(mc.getModel());
			}
		}
		return list;
	}
	
	
	
	
	
	
	/**
	 * 通过车型查询长安福特原厂保养套餐价格。
	 * @param model
	 * @return
	 */
	public List<MaintenancePackage> findMaintenancePackage(String model){
		return maintenancePackageDao.find(model);
	}
	
	public List<MaintenancePackage> findMaintenancePackage(String model,String packageType){
		return maintenancePackageDao.find(model,packageType);
	}
	
	
	public List<String> findAllCarModel(String packageType){
		return maintenancePackageDao.findAllModel(packageType);
	}
	/**
	 * 查找所有车型,用来为findMaintenancePackage传参
	 * @return
	 */
	public List<String> findAllCarModel(){
		return maintenancePackageDao.findAllModel();
	}
}
