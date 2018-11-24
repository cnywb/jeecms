package com.jeecms.cms.manager.main;


import com.jeecms.cms.entity.main.CarOwnerAuthenApplication;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.common.page.Pagination;
public interface CarOwnerAuthenApplicationMng {
	
	public int add(CmsUser user,CarOwnerAuthenApplication carOwnerAuthenApplication);
	public int add(CmsUser user,String vvin, String vname,String vmobile, String certImageUrl);
	public Pagination getPage(String status,String name,String mobile,String vin,String createTimeMin,String createTimeMax,String updateTimeMin,String updateTimeMax, int pageNo,
				int pageSize);
	 public CarOwnerAuthenApplication findById(long id);
	 public int authenPass(long id);
	 public int authenUnPass(long id,String memo);
	 public int saveTempInfo(CmsUser user, String vvin, String vname,String vmobile);
	 public CarOwnerAuthenApplication  findCarOwnerAuthenApplicationTempInfo(int createId);
	 public int updateCarOwnerAuthenApplicationTempInfo(int createId,long id);
}
