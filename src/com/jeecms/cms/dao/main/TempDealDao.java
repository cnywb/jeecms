package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.TempDeal;


public interface TempDealDao {
		//查看最新的三条优惠券数据
		public List<TempDeal> getTop3TempDealByUserId (Integer uuid,int count);
		
		public List<TempDeal> getFailureTempDeal(Integer uuid);
		
		public List<TempDeal> getUsableTempDeal(Integer uuid);
		
		public TempDeal findById(Long id);
		
		public List<TempDeal> getTempDealsByVin(String vin,int count);
}
