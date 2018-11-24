package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.ClubDictionaryDao;
import com.jeecms.cms.entity.main.ClubDictionary;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
@Repository
public class ClubDictionaryDaoImpl extends HibernateBaseDao<ClubDictionary,Long> implements ClubDictionaryDao {

	@Override
	public ClubDictionary getClubDictionaryByCode(String cdcode) {
		Finder f = Finder.create("select d from ClubDictionary d where d.cdcode=:cdcode ");
		f.setParam("cdcode", cdcode);
		List<?> list = find(f);
		if(null != list && list.size() > 0)
			return (ClubDictionary)list.get(0);
		return null;
	}

	@Override
	protected Class<ClubDictionary> getEntityClass() {
		// TODO Auto-generated method stub
		return ClubDictionary.class;
	}

}
