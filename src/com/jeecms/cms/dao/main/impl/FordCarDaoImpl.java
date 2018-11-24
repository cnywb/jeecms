package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.FordCarDao;
import com.jeecms.cms.entity.main.FordCar;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
@Repository
public class FordCarDaoImpl extends HibernateBaseDao<FordCar,String> implements FordCarDao {

	@Override
	public FordCar findById(String vcarId) {
		return get(vcarId);
	}

	@Override
	protected Class<FordCar> getEntityClass() {
		// TODO Auto-generated method stub
		return FordCar.class;
	}

	@Override
	public List<FordCar> findByVin(String vvin) {
		Finder finder = Finder.create("select f from FordCar f where upper(f.vvin)=:vvin");
		finder.setParam("vvin", vvin.trim().toUpperCase());
		return find(finder);
	}

}
