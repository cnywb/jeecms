package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.CarOwnerAuthenApplication;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.common.page.Pagination;

public interface CarOwnerAuthenApplicationDao {
	public void save(CarOwnerAuthenApplication t);
	public void update(CarOwnerAuthenApplication t);
	public List<CarOwnerAuthenApplication>  findCarOwnerAuthenApplicationInProcess(String vin);
	public Pagination getPage(String status,String name,String mobile,String vin,String createTimeMin,String createTimeMax,String updateTimeMin,String updateTimeMax, int pageNo,
			int pageSize);
	public CarOwnerAuthenApplication findByEntityId(long id);
	public CarOwnerAuthenApplication  findCarOwnerAuthenApplicationTempInfo(int createId);
	public int updateCarOwnerAuthenApplicationTempInfo(int createId,long id);
	public List<CarOwnerAuthenApplication>  findCarOwnerAuthenApplicationInSave(int createrId);
}
