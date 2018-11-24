package com.jeecms.cms.manager.impl.recall;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.recall.CarRecallDao;
import com.jeecms.cms.entity.recall.CarRecall;
import com.jeecms.cms.manager.recall.CarRecallMng;



@Service("carRecallMng")
@Transactional(rollbackFor=Exception.class) 
public class CarRecallMngImpl implements CarRecallMng {
	
	@Resource
	private CarRecallDao carRecallDao;
	
	
	public List<CarRecall>findByVin(String vin){
		return carRecallDao.findByVin(vin);
	}

}
