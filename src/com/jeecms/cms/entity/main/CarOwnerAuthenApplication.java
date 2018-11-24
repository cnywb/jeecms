package com.jeecms.cms.entity.main;

import com.jeecms.cms.entity.main.base.BaseCarOwnerAuthenApplication;

public class CarOwnerAuthenApplication extends BaseCarOwnerAuthenApplication {

	public CarOwnerAuthenApplication(String vvin, String vname, String vmobile,
			String certImageUrl) {
		super(vvin,vname,vmobile,certImageUrl);
	}
	public CarOwnerAuthenApplication(String vvin, String vname,
			String vmobile) {
		super(vvin,vname,vmobile);
	}
	public CarOwnerAuthenApplication() {
		super();
		
	}

}
