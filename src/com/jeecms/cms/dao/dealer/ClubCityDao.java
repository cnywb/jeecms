package com.jeecms.cms.dao.dealer;

import java.util.List;

import com.jeecms.cms.entity.main.ClubCity;

public interface ClubCityDao {
	public List<ClubCity> findAllByProvinceId(long provinceId) ;
}
