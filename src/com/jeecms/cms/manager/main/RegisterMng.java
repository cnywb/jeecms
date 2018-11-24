package com.jeecms.cms.manager.main;

import com.jeecms.cms.entity.main.CommonResponse;

public interface RegisterMng {
	public CommonResponse carOwnerAuthenAndRegister(String vname,String vmobile,String vvin,String ip,String registType);

}
