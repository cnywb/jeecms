package com.jeecms.cms.dao.main.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.CmsUserDao;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.FordClubMember;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.FinderBase;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;
import com.jeecms.common.util.ValidateUtils;

@Repository
public class CmsUserDaoImpl extends HibernateBaseDao<CmsUser, Integer>
		implements CmsUserDao {
	public Pagination getPage(String username, String email, Integer siteId,
			Integer groupId, Boolean disabled, Boolean admin, Integer rank,
			int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from CmsUser bean");
		if (siteId != null) {
			f.append(" join bean.userSites userSite");
			f.append(" where userSite.site.id=:siteId");
			f.setParam("siteId", siteId);
		} else {
			f.append(" where 1=1");
		}
		if (!StringUtils.isBlank(username)) {
			f.append(" and bean.username like :username");
			f.setParam("username", "%" + username + "%");
		}
		if (!StringUtils.isBlank(email)) {
			//f.append(" and bean.email like :email");
			//f.setParam("email", "%" + email + "%");
			//ping.qi modify by 2014-07-10
		       f.append(" and bean.email = :email");
			f.setParam("email", email);
		}
		if (groupId != null) {
			f.append(" and bean.group.id=:groupId");
			f.setParam("groupId", groupId);
		}
		if (disabled != null) {
			f.append(" and bean.disabled=:disabled");
			f.setParam("disabled", disabled);
		}
		if (admin != null) {
			f.append(" and bean.admin=:admin");
			f.setParam("admin", admin);
		}
		if (rank != null) {
			f.append(" and bean.rank<=:rank");
			f.setParam("rank", rank);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	public List<CmsUser> getList(String username, String email, Integer siteId,
			Integer groupId, Boolean disabled, Boolean admin, Integer rank) {
		Finder f = Finder.create("select bean from CmsUser bean");
		if (siteId != null) {
			f.append(" join bean.userSites userSite");
			f.append(" where userSite.site.id=:siteId");
			f.setParam("siteId", siteId);
		} else {
			f.append(" where 1=1");
		}
		if (!StringUtils.isBlank(username)) {
			f.append(" and bean.username like :username");
			f.setParam("username", "%" + username + "%");
		}
		if (!StringUtils.isBlank(email)) {
			f.append(" and bean.email like :email");
			f.setParam("email", "%" + email + "%");
		}
		if (groupId != null) {
			f.append(" and bean.group.id=:groupId");
			f.setParam("groupId", groupId);
		}
		if (disabled != null) {
			f.append(" and bean.disabled=:disabled");
			f.setParam("disabled", disabled);
		}
		if (admin != null) {
			f.append(" and bean.admin=:admin");
			f.setParam("admin", admin);
		}
		if (rank != null) {
			f.append(" and bean.rank<=:rank");
			f.setParam("rank", rank);
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	@SuppressWarnings("unchecked")
	public List<CmsUser> getAdminList(Integer siteId, Boolean allChannel,
			Boolean disabled, Integer rank) {
		Finder f = Finder.create("select bean from CmsUser");
		f.append(" bean join bean.userSites us");
		f.append(" where us.site.id=:siteId");
		f.setParam("siteId", siteId);
		if (allChannel != null) {
			f.append(" and us.allChannel=:allChannel");
			f.setParam("allChannel", allChannel);
		}
		if (disabled != null) {
			f.append(" and bean.disabled=:disabled");
			f.setParam("disabled", disabled);
		}
		if (rank != null) {
			f.append(" and bean.rank<=:rank");
			f.setParam("rank", rank);
		}
		f.append(" and bean.admin=true");
		f.append(" order by bean.id asc");
		return find(f);
	}
	
	public Pagination getAdminsByDepartId(Integer id, int pageNo,int pageSize){
		Finder f = Finder.create("select bean from CmsUser bean ");
		f.append(" where bean.department.id=:departId");
		f.setParam("departId", id);
		f.append(" and bean.admin=true");
		f.append(" order by bean.id asc");
		return find(f,pageNo,pageSize);
	}
	
	public Pagination getAdminsByRoleId(Integer roleId, int pageNo, int pageSize){
		Finder f = Finder.create("select bean from CmsUser");
		f.append(" bean join bean.roles role");
		f.append(" where role.id=:roleId");
		f.setParam("roleId", roleId);
		f.append(" and bean.admin=true");
		f.append(" order by bean.id asc");
		return find(f,pageNo,pageSize);
	}

	public CmsUser findById(Integer id) {
		CmsUser entity = get(id);
		return entity;
	}

	public CmsUser findByUsername(String username) {
		return findUniqueByProperty("username", username);
	}

	public int countByUsername(String username) {
		String hql = "select count(*) from CmsUser bean where bean.username=:username";
		Query query = getSession().createQuery(hql);
		query.setParameter("username", username);
		return ((Number) query.iterate().next()).intValue();
	}
	public int countMemberByUsername(String username) {
		String hql = "select count(*) from CmsUser bean where bean.username=:username and bean.admin=false";
		Query query = getSession().createQuery(hql);
		query.setParameter("username", username);
		return ((Number) query.iterate().next()).intValue();
	}

	public int countByEmail(String email) {
		String hql = "select count(*) from CmsUser bean where bean.email=:email";
		Query query = getSession().createQuery(hql);
		query.setParameter("email", email);
		return ((Number) query.iterate().next()).intValue();
	}
	/**ping.qi add 查询认证车主信息*/
	public Pagination getPage(String username, String vvin,String createTimeMax,String createTimeMin,
		int pageNo, int pageSize){
	    StringBuffer buffer=new StringBuffer();
	    buffer.append(" select cmu.username ");//用户名
	    buffer.append(" ,bean.vmobile ");//手机
	    buffer.append(" ,bean.vname ");//客户名称
	    buffer.append(" ,bean.vcardNo ");//俱乐部卡号
	    buffer.append(" ,bean.vaddress ");//地址
	    buffer.append(" ,bean.vemail ");//邮箱地址
	    buffer.append(" ,bean.vvin ");//vin码
	    buffer.append(" ,bean.dcrtDate ");//vin码
	    buffer.append("from CmsUser cmu,FordClubMember bean ");
	    buffer.append(" where cmu.id = bean.userId ");
	    FinderBase f = FinderBase.create(buffer.toString());
		if (!StringUtils.isBlank(username)) {
			f.append(" and cmu.username like :username");
			f.setParam("username", "%" + username + "%");
		}
		if(!StringUtils.isBlank(vvin)){
		    f.append(" and bean.vvin = :vvin");
			f.setParam("vvin",vvin);
		}
		if(!ValidateUtils.isEmpty(createTimeMin)){
			createTimeMin=createTimeMin+" 00:00:00";
			Date minTime=DateUtils.parseDate(createTimeMin, DateUtils.FORMAT_DATE_TIME_DEFAULT);
			f.append(" and bean.dcrtDate>=:minTime");
			f.setParam("minTime",minTime);
		}
		if(!ValidateUtils.isEmpty(createTimeMax)){
			createTimeMax=createTimeMax+" 23:59:59";
			Date maxTime=DateUtils.parseDate(createTimeMax, DateUtils.FORMAT_DATE_TIME_DEFAULT);
			f.append(" and bean.dcrtDate<=:maxTime");
			f.setParam("maxTime",maxTime);
		}
	Pagination page=findBase(f,pageNo,pageSize);
	List<Object []> listo=(List<Object[]>) page.getList();
	List<FordClubMember> clubList=new ArrayList<FordClubMember>();
	for(Object[] obj:listo){
	    FordClubMember member=new FordClubMember();
	    if (obj != null && !"".equals(obj)) {
		Object[] objs = (Object[]) obj;
		if (objs[0] != null && !"".equals(objs[0])) {
		    member.setVnotes(objs[0].toString());//用户名
		}
		if (objs[1] != null && !"".equals(objs[1])) {
		    member.setVmobile(objs[1].toString());//手机
		}
		if (objs[2] != null && !"".equals(objs[2])) {
		    member.setVname(objs[2].toString());//客户名称
		}
		if (objs[3] != null && !"".equals(objs[3])) {
		    member.setVcardNo(objs[3].toString());//俱乐部卡号
		}
		if (objs[4] != null && !"".equals(objs[4])) {
		    member.setVaddress(objs[4].toString());//地址
		}
		if (objs[5] != null && !"".equals(objs[5])) {
		    member.setVemail(objs[5].toString());//邮箱地址
		}
		if (objs[6] != null && !"".equals(objs[6])) {
		    member.setVvin(objs[6].toString());//vin码
		}
		if (objs[7] != null && !"".equals(objs[7])) {
		    member.setDcrtDate((Date)objs[7]);//认证日期
		}
		clubList.add(member);
	   }
	}
	 page.setList(clubList);
	   return page;
	}
	public CmsUser save(CmsUser bean) {
		getSession().save(bean);
		return bean;
	}
	
	public void update(CmsUser bean) {
		getSession().update(bean);
		
	}

	public CmsUser deleteById(Integer id) {
		CmsUser entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<CmsUser> getEntityClass() {
		return CmsUser.class;
	}
}