package com.jeecms.cms.manager.dealer;

import java.util.List;

import com.jeecms.cms.entity.main.ClubCity;
import com.jeecms.cms.entity.main.ClubDealer;
import com.jeecms.cms.entity.main.ClubProvince;

public interface ClubDealerMng {
	
	public List<ClubProvince> findAllProvince();
	
	public List<ClubCity> findAllCityByProvinceId(Long provinceId);

	public List<ClubDealer> findAllDealerByCityId(Long cityId);

}
