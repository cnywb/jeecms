package com.jeecms.bbs.dao.member.impl;

import java.util.List;

import com.jeecms.bbs.dao.member.MemberCarDao;
import com.jeecms.bbs.entity.BbsMemberCar;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

public class MemberCarDaoImpl extends HibernateBaseDao<BbsMemberCar, Long>
		implements MemberCarDao {

	@Override
	public BbsMemberCar save(BbsMemberCar bbsMemberCar) {
		this.getSession().save(bbsMemberCar);
		return bbsMemberCar;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BbsMemberCar> queryById(Integer uuid) {
		String hql = "from BbsMemberCar t where t.uuid=:uuid";
		Finder f = Finder.create(hql);
		f.setParam("uuid", uuid);
		return find(f);
	}

	@Override
	public void deleteById(Long id) {
		BbsMemberCar entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
	}
	
	

	/* (non-Javadoc)
	 * @see com.jeecms.bbs.dao.member.MemberCarDao#queryFriendCarByUserId(java.lang.Integer, com.jeecms.common.hibernate3.Finder, int, int)
	 */
	@Override
	public Pagination queryFriendCarByUserId(Integer userId, int pageNo, int pageSize) {
		String hql="SELECT car FROM  BbsMemberCar car,BbsFriendShip s where car.uuid=s.friend.id and s.user.id=:userId";
		Finder finder = Finder.create(hql);
		finder.setParam("userId",userId);
		
		Pagination pagination=super.find(finder, pageNo, pageSize);
		return pagination;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jeecms.bbs.dao.member.MemberCarDao#update(com.jeecms.bbs.entity.
	 * BbsMemberCar)
	 */
	@Override
	public BbsMemberCar update(BbsMemberCar bbsMemberCar) {
		this.getSession().update(bbsMemberCar);
		return bbsMemberCar;
	}

	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jeecms.bbs.dao.member.MemberCarDao#findById(java.lang.Integer)
	 */
	@Override
	public BbsMemberCar findById(Long id) {
		return this.get(id);
	}

	@Override
	protected Class<BbsMemberCar> getEntityClass() {
		// TODO Auto-generated method stub
		return BbsMemberCar.class;
	}

}
