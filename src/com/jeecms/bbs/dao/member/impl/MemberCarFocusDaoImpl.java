package com.jeecms.bbs.dao.member.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.bbs.dao.member.MemberCarFocusDao;
import com.jeecms.bbs.entity.BbsMemberCarFocus;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
@Repository
public class MemberCarFocusDaoImpl  extends HibernateBaseDao<BbsMemberCarFocus, Long> implements
MemberCarFocusDao {

	@Override
	public BbsMemberCarFocus save(BbsMemberCarFocus memberCarFocus) {
		this.getSession().save(memberCarFocus);
		return memberCarFocus;
	}
	
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<BbsMemberCarFocus> queryById(Integer uuid) {
		String hql = "from BbsMemberCarFocus t where t.uuid=:uuid";
		Finder f = Finder.create(hql);
		f.setParam("uuid", uuid);
		return find(f);
	}
 
	
	@Override
	public void deleteById(Long id) {
		BbsMemberCarFocus entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
	} 
	
	
	@Override
	protected Class<BbsMemberCarFocus> getEntityClass() {
	 
		return BbsMemberCarFocus.class;
	}
		
}
