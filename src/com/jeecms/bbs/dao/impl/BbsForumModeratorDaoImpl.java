package com.jeecms.bbs.dao.impl;

import java.util.Date;
import java.util.List;

import com.jeecms.bbs.dao.BbsForumModeratorDao;
import com.jeecms.bbs.entity.BbsForumModerator;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;
import com.jeecms.common.util.ValidateUtils;

public class BbsForumModeratorDaoImpl extends
		HibernateBaseDao<BbsForumModerator, Long> implements
		BbsForumModeratorDao {

	@Override
	protected Class<BbsForumModerator> getEntityClass() {
		return BbsForumModerator.class;
	}
	
	public long add(BbsForumModerator t){
		List<BbsForumModerator> list=findAllByForumIdAndUserId(t.getForum().getId(), t.getUser().getId());
		if(list.size()>0){//已经是该版版主则无法添加
			return 0L;
		}
		getSession().save(t);
		return 1L;
	}
	
	 public List<BbsForumModerator>  findAllByForumIdAndUserId(int forumId,int userId){
			String hql = "select bean from BbsForumModerator bean where bean.user.id=? and bean.forum.id=?";
			return find(hql,userId,forumId);
	 }
	 
	 public List<BbsForumModerator>  findAllByForumId(int forumId){
			String hql = "select bean from BbsForumModerator bean where bean.forum.id=?";
			return find(hql,forumId);
	 }
	 
	 public BbsForumModerator getById(Long id){
		 return get(id);
	 }
	 
	 public void delete(BbsForumModerator t){
		 getSession().delete(t);
	 }
	 
	 public Pagination getPage(String forumId,String createTimeMin,String createTimeMax,String updateTimeMin,String updateTimeMax, int pageNo,
				int pageSize){
			Finder f = Finder.create("from BbsForumModerator bean where 1=1");
			if(!ValidateUtils.isEmpty(forumId)){
				f.append(" and bean.forum.id=:forumId");
				f.setParam("forumId",Integer.parseInt(forumId));
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
