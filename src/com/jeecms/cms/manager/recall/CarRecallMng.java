package com.jeecms.cms.manager.recall;

import java.util.List;

import com.jeecms.cms.entity.recall.CarRecall;

public interface CarRecallMng {
	public List<CarRecall>findByVin(String vin);

}
