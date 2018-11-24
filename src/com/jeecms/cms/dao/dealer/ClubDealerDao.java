package com.jeecms.cms.dao.dealer;

import java.util.List;

import com.jeecms.cms.entity.main.ClubDealer;

public interface ClubDealerDao {
	public List<ClubDealer> findAllByCityId(long cityId);

}
