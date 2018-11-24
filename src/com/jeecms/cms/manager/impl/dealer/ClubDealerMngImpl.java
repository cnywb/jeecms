package com.jeecms.cms.manager.impl.dealer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.dealer.ClubCityDao;
import com.jeecms.cms.dao.dealer.ClubDealerDao;
import com.jeecms.cms.dao.dealer.ClubProvinceDao;
import com.jeecms.cms.entity.main.ClubCity;
import com.jeecms.cms.entity.main.ClubDealer;
import com.jeecms.cms.entity.main.ClubProvince;
import com.jeecms.cms.manager.dealer.ClubDealerMng;



@Service
@Transactional(rollbackFor=Exception.class) 
public class ClubDealerMngImpl implements ClubDealerMng {
	@Autowired
	private ClubCityDao clubCityDao;
	@Autowired
	private ClubDealerDao clubDealerDao;
	@Autowired
	private ClubProvinceDao clubProvinceDao;
	
	
	public List<ClubProvince> findAllProvince(){
		return clubProvinceDao.findAll();
	}
	
	public List<ClubCity> findAllCityByProvinceId(Long provinceId){
		return clubCityDao.findAllByProvinceId(provinceId);
	}

	public List<ClubDealer> findAllDealerByCityId(Long cityId){
		return clubDealerDao.findAllByCityId(cityId);
	}
}
