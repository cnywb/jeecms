package com.jeecms.cms.dao.impl.maintenance;

import java.util.List;

import com.jeecms.cms.dao.maintenance.MaintenancePackageDao;
import com.jeecms.cms.entity.maintenance.MaintenancePackage;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;

public class MaintenancePackageDaoImpl extends
		HibernateBaseDao<MaintenancePackage, Long> implements
		MaintenancePackageDao {

	@Override
	protected Class<MaintenancePackage> getEntityClass() {
		// TODO Auto-generated method stub
		return MaintenancePackage.class;
	}

	
	public List<MaintenancePackage>  find(String model){
		String hql = "select bean from MaintenancePackage bean where bean.model=?";
		return find(hql,model);
	}
	
	public List<MaintenancePackage>  find(String model,String packageType){
		String hql = "select bean from MaintenancePackage bean where bean.model=? and bean.packageType=?";
		return find(hql,model,packageType);
	}
	
	public List<String> findAllModel(){
		Finder f = Finder.create("select distinct model from MaintenancePackage");
		return find(f);
	}
	
	public List<String> findAllModel(String packageType){
		Finder f = Finder.create("select distinct model from MaintenancePackage where packageType=:packageType");
		f.setParam("packageType", packageType);
		return find(f);
	}

}
