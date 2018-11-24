package com.jeecms.bbs.dao.impl;

import java.util.Date;
import java.util.List;

import com.jeecms.bbs.dao.BbsForumModeratorApplicationDao;
import com.jeecms.bbs.entity.BbsForumModeratorApplication;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;
import com.jeecms.common.util.ValidateUtils;

public class BbsForumModeratorApplicationDaoImpl extends
		HibernateBaseDao<BbsForumModeratorApplication, Long> implements
		BbsForumModeratorApplicationDao {

	@Override
	protected Class<BbsForumModeratorApplication> getEntityClass() {
		return BbsForumModeratorApplication.class;
	}
	
	public Long add(BbsForumModeratorApplication t){
		getSession().save(t);
		return t.getId();
	}
	
	
	 public List<BbsForumModeratorApplication>  findAllByForumIdAndUserId(int forumId,int userId){
			String hql = "select bean from BbsForumModeratorApplication bean where bean.user.id=? and bean.forum.id=? and bean.status=?";
			return find(hql,userId,forumId,0);
	 }
	 
	public BbsForumModeratorApplication getById(long id){
		return get(id);
	}
	
	public long update(BbsForumModeratorApplication t){
		getSession().update(t);
		return 1L;
	}
	
	public Pagination getPage(String status,String createTimeMin,String createTimeMax,String updateTimeMin,String updateTimeMax, int pageNo,
			int pageSize){
		Finder f = Finder.create("from BbsForumModeratorApplication bean where 1=1");
		if(!ValidateUtils.isEmpty(status)){
			f.append(" and bean.status=:status");
			f.setParam("status",Integer.parseInt(status));
		}
		if(!ValidateUtils.isEmpty(createTimeMin)){
			createTimeMin=createTimeMin+" 00:00:00";
			Date minTime=DateUtils.parseDate(createTimeMin, DateUtils.FORMAT_DATE_TIME_DEFAULT);
			f.append(" and bean.createTime>=:minTime");
			f.setParam("minTime",minTime);
		}
		if(!ValidateUtils.isEmpty(createTimeMax)){
			createTimeMax=createTimeMax+" 23:59:59";
			Date maxTime=DateUtils.parseDate(createTimeMax, DateUtils.FORMAT_DATE_TIME_DEFAULT);
			f.append(" and bean.createTime<=:maxTime");
			f.setParam("maxTime",maxTime);
		}
		if(!ValidateUtils.isEmpty(updateTimeMin)){
			updateTimeMin=updateTimeMin+" 00:00:00";
			Date minTime=DateUtils.parseDate(updateTimeMin, DateUtils.FORMAT_DATE_TIME_DEFAULT);
			f.append(" and bean.updateTime>=:minTime");
			f.setParam("minTime",minTime);
		}
		if(!ValidateUtils.isEmpty(updateTimeMax)){
			updateTimeMax=updateTimeMax+" 23:59:59";
			Date maxTime=DateUtils.parseDate(updateTimeMax, DateUtils.FORMAT_DATE_TIME_DEFAULT);
			f.append(" and bean.updateTime<=:maxTime");
			f.setParam("maxTime",maxTime);
		}
		f.append(" order by bean.createTime desc");
		return find(f, pageNo, pageSize);
	}

}
