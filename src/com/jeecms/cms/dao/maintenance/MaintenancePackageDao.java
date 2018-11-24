package com.jeecms.cms.dao.maintenance;

import java.util.List;

import com.jeecms.cms.entity.maintenance.MaintenancePackage;

public interface MaintenancePackageDao {
	
   public List<MaintenancePackage>  find(String model);
   public List<MaintenancePackage>  find(String model,String packageType);
   public List<String> findAllModel();
   public List<String> findAllModel(String packageType);

}
