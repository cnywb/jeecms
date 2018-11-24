package com.jeecms.cms.dao.recall;

import java.util.List;

import com.jeecms.cms.entity.recall.CarRecall;

public interface CarRecallDao {
	public List<CarRecall>findByVin(String vin);

}
