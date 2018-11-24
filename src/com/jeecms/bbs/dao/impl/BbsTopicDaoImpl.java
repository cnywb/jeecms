package com.jeecms.bbs.dao.impl;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.bbs.dao.BbsTopicDao;
import com.jeecms.bbs.entity.BbsTopic;
import com.jeecms.bbs.schedule.ClearUserOnlineJob.TopicCountEnum;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class BbsTopicDaoImpl extends HibernateBaseDao<BbsTopic, Integer>
		implements BbsTopicDao {
	@SuppressWarnings("unchecked")
	public List<BbsTopic> getTopTopic(Integer webId, Integer ctgId,
			Integer forumId) {
		Finder f = Finder.create("from BbsTopic bean where 1=1");
		f.append(" and bean.website.id=:webId").setParam("webId", webId);
		f.append(" and bean.status>=").append(String.valueOf(BbsTopic.NORMAL));
		f.append(" and (bean.topLevel=3 ");
		f.append(" or (bean.topLevel=2 and bean.category.id=:ctgId)");
		f.setParam("ctgId", ctgId);
		f.append(" or (bean.topLevel=1 and bean.forum.id=:forumId))");
		f.setParam("forumId", forumId);
		f.append(" order by bean.topLevel desc");
		return find(f);
	}
	
	public Pagination getForTag(Integer siteId, Integer forumId,Integer parentPostTypeId, Integer postTypeId, Short status,
			Short primeLevel, String keyWords, String creater,
			Integer createrId, Short topLevel, int pageNo, int pageSize,String jinghua) {
		Finder f = Finder.create("select bean from BbsTopic bean where 1=1");
		f.append(" and bean.site.id=:siteId").setParam("siteId", siteId);
		if (forumId != null) {
			f.append(" and bean.forum.id=:forumId")
					.setParam("forumId", forumId);
		}
		if(parentPostTypeId!=null){
			f.append(" and bean.postType.parent.id=:parentPostTypeId")
			.setParam("parentPostTypeId", parentPostTypeId);
		}
		if(postTypeId!=null && postTypeId.intValue() > 0){
			f.append(" and bean.postType.id=:postTypeId")
			.setParam("postTypeId", postTypeId);
		}
		if (status == null) {
			status = 0;
		}
		f.append(" and bean.status>=:status").setParam("status", status);
		if (topLevel != null) {
			if (topLevel != 0) {
				f.append(" and bean.topLevel>=:topLevel").setParam("topLevel",
						topLevel);
			} else {
				f.append(" and bean.topLevel=0");
			}
		}
		if (primeLevel != null) {
			f.append(" and bean.primeLevel >=:primeLevel").setParam(
					"primeLevel", primeLevel);
		}
		if (!StringUtils.isBlank(keyWords)) {
			f.append(" and bean.topicText.title like :keyWords").setParam("keyWords",
					"%" + keyWords + "%");
		}
		if (!StringUtils.isBlank(creater)) {
			f.append(" and bean.creater.username like :creater").setParam(
					"creater", "%" + creater + "%");
		}
		if (createrId != null) {
			f.append(" and bean.creater.id =:createrId").setParam("createrId",
					createrId);
		}
		if("index_jing".equals(jinghua)){
			f.append(" and bean.primeLevel != 0");
		}
		f.append(" order by bean.topLevel desc,bean.sortTime desc");
		return find(f, pageNo, pageSize);
	}

	public Pagination getForSearchDate(Integer siteId, Integer forumId,
			Short primeLevel, Integer day, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from BbsTopic bean where 1=1");
		f.append(" and bean.site.id=:siteId").setParam("siteId", siteId);
		if (forumId != null) {
			f.append(" and bean.forum.id=:forumId")
					.setParam("forumId", forumId);
		}
		f.append(" and bean.status>=0");
		if (day != null) {
			Calendar now = Calendar.getInstance();
			now.setTime(new Date(System.currentTimeMillis()));
			now.add(Calendar.DATE, -day);
			f.append(" and bean.createTime>:day")
					.setParam("day", now.getTime());
		}
		if (primeLevel != null) {
			f.append(" and bean.primeLevel >=:primeLevel").setParam(
					"primeLevel", primeLevel);
		}
		f.append(" order by bean.topLevel desc,bean.sortTime desc");
		return find(f, pageNo, pageSize);
	}

	public Pagination getTopicByTime(Integer siteId, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from BbsTopic bean where 1=1");
		f.append(" and bean.site.id=:siteId").setParam("siteId", siteId);
		f.append(" order by bean.lastTime desc");
		return find(f, pageNo, pageSize);
	}

	public Pagination getMemberTopic(Integer siteId, Integer userId,
			int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from BbsTopic bean where 1=1");
		//f.append(" and bean.site.id=:siteId").setParam("siteId", siteId);
		f.append(" and bean.creater.id=:userId");
		f.setParam("userId", userId);
		f.append(" order by bean.lastTime desc");
		return find(f, pageNo, pageSize);
	}

	public Pagination getMemberReply(Integer siteId, Integer userId,
			int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from BbsTopic bean where 1=1");
		//f.append(" and bean.site.id=:siteId").setParam("siteId", siteId);
		f.append(" and bean.haveReply like :userId").setParam("userId",
				"%," + userId + ",%");
		f.append(" order by bean.sortTime desc");
		return find(f, pageNo, pageSize);
	}

	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public BbsTopic findById(Integer id) {
		BbsTopic entity = get(id);
		return entity;
	}

	public BbsTopic save(BbsTopic bean) {
		getSession().save(bean);
		return bean;
	}

	public BbsTopic deleteById(Integer id) {
		BbsTopic entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<BbsTopic> getEntityClass() {
		return BbsTopic.class;
	}

	@SuppressWarnings("unchecked")
	public List<BbsTopic> getListByForumId(Integer forumId) {
		String hql = "from BbsTopic bean where bean.forum.id=:forumId order by bean.id desc";
		return getSession().createQuery(hql).setInteger("forumId", forumId).setCacheable(false).list();
	}

	@SuppressWarnings("unchecked")
	public List<BbsTopic> getNewList(Integer count,Integer orderby) {
		String hql = "from BbsTopic bean where bean.forum.title!='版务区' ";
		if(orderby==null){
			hql+="order by bean.createTime desc";
		}
		if(orderby.equals(1)){
			hql+="order by bean.createTime desc";
		}else if(orderby.equals(2)){
			hql+="order by bean.viewsDay desc,bean.createTime desc";
		}else if(orderby.equals(3)){
			hql+="order by bean.viewsWeek desc,bean.createTime desc";
		}else if(orderby.equals(4)){
			hql+="order by bean.viewsMonth desc,bean.createTime desc";
		}else if(orderby.equals(5)){
			hql+="order by bean.viewCount desc,bean.createTime desc";
		}else if(orderby.equals(6)){
			hql+="order by bean.replyCount desc,bean.createTime desc";
		}else if(orderby.equals(7)){
			hql+="order by bean.replyCountDay desc,bean.createTime desc";
		}
		return getSession().createQuery(hql).setFirstResult(0).setMaxResults(count).setCacheable(false).list();
	}
	
	public Pagination getNewList(Integer orderby,int pageNo,int pageSize) {
		String hql = "from BbsTopic bean ";
		if(orderby==null){
			hql+="order by bean.createTime desc";
		}
		if(orderby.equals(1)){
			hql+="order by bean.createTime desc";
		}else if(orderby.equals(2)){
			hql+="order by bean.viewsDay desc,bean.createTime desc";
		}else if(orderby.equals(3)){
			hql+="order by bean.viewsWeek desc,bean.createTime desc";
		}else if(orderby.equals(4)){
			hql+="order by bean.viewsMonth desc,bean.createTime desc";
		}else if(orderby.equals(5)){
			hql+="order by bean.viewCount desc,bean.createTime desc";
		}else if(orderby.equals(6)){
			hql+="order by bean.replyCount desc,bean.createTime desc";
		}else if(orderby.equals(7)){
			hql+="order by bean.replyCountDay desc,bean.createTime desc";
		}
		Finder f = Finder.create(hql);
		return find(f, pageNo, pageSize);
	}
	public List<BbsTopic> getTopicList(){
		String hql = "from BbsTopic bean order by bean.createTime desc";
		return getSession().createQuery(hql).setFirstResult(0).setCacheable(false).list();
	}
	public void updateAllTopicCount(TopicCountEnum e){
		String hql="";
		String updateReplyCountDaySql="";
		if(e==null){
			hql = "update BbsTopic bean set bean.viewsDay=0";
		}
		if(e.equals(TopicCountEnum.day)){
			hql = "update BbsTopic bean set bean.viewsDay=0";
			updateReplyCountDaySql="update BbsTopic bean set bean.replyCountDay=0";
		}else if(e.equals(TopicCountEnum.week)){
			hql = "update BbsTopic bean set bean.viewsWeek=0";
		}else if(e.equals(TopicCountEnum.month)){
			hql = "update BbsTopic bean set bean.month=0";
		}
		if(StringUtils.isNotBlank(hql)){
			getSession().createQuery(hql).executeUpdate();
		}
		if(StringUtils.isNotBlank(updateReplyCountDaySql)){
			getSession().createQuery(updateReplyCountDaySql).executeUpdate();
		}
	}

	@Override
	public List<BbsTopic> getBeanByForumId(Integer forumId, int count) {
		Finder f = Finder.create("from BbsTopic bean where bean.forum.id=:forumId order by bean.id desc");
		f.setParam("forumId", forumId);
		f.setFirstResult(0);
		f.setMaxResults(count);
		f.setCacheable(false);
		return find(f);
	}
	

	
	public int getCountByForumId(Integer forumId) {
		String hql = "select count(bean.id)from BbsTopic bean where bean.forum.id=:forumId";
		return ((Number) getSession().createQuery(hql).setParameter("forumId", forumId).iterate().next()).intValue();
	}
	
	/**
	 * 设置头条话题
	 * @param id
	 */
	public void setToHeadlineById(Integer id,Integer forumId){
		String hql = "update BbsTopic set isHeadline=:isHeadline where isHeadline=:oldIsHeadline and id in(select t.id from BbsTopic t where t.forum.id=:forumId)";
		getSession().createQuery(hql).setBoolean("isHeadline", false).setBoolean("oldIsHeadline", true).setInteger("forumId", forumId).executeUpdate();
		hql = "update BbsTopic set isHeadline=:isHeadline where id=:id";
		getSession().createQuery(hql).setBoolean("isHeadline",true).setInteger("id",id).executeUpdate();
	}

	
	/**
	 * 查找版块头条话题
	 * @param forumId
	 * @param count
	 * @return
	 */
	public List<BbsTopic> getHeadlineByForumId(Integer forumId, int count) {
		Finder f = Finder.create("from BbsTopic bean where bean.forum.id=:forumId and isHeadline=:isHeadline order by bean.id desc");
		f.setParam("forumId", forumId);
		f.setParam("isHeadline",true);
		f.setFirstResult(0);
		f.setMaxResults(count);
		f.setCacheable(false);
		return find(f);
	}
	
	
	/**
	 * 更新是否禁止回贴
	 * @param ids
	 */
	public int updateIsForbidReply(String ids,Boolean isForbidReply ){
		String hql = "update BbsTopic set isForbidReply=:isForbidReply where id in(:ids)";
		return getSession().createQuery(hql).setBoolean("isForbidReply",isForbidReply).setString("ids",ids).executeUpdate();
	}
	
	
}