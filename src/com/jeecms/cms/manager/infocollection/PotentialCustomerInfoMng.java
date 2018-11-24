package com.jeecms.cms.manager.infocollection;

import com.jeecms.cms.entity.infocollection.PotentialCustomerInfoRequest;
import com.jeecms.cms.entity.infocollection.PotentialCustomerInfoResponse;

public interface PotentialCustomerInfoMng {
	
	public PotentialCustomerInfoResponse add(PotentialCustomerInfoRequest request);
	public PotentialCustomerInfoResponse addForCheckCustomerInfo(PotentialCustomerInfoRequest request);

}
