package com.jeecms.bbs.dao.impl;

import java.util.List;





import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.jeecms.bbs.dao.BbsPostDao;
import com.jeecms.bbs.entity.BbsPost;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.web.HtmlParserUtil;

@Repository
public class BbsPostDaoImpl extends HibernateBaseDao<BbsPost, Integer>
		implements BbsPostDao {
	public Pagination getForTag(Integer siteId, Integer topicId,
			Integer userId, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from BbsPost bean where 1=1");
		f.append(" and bean.site.id=:siteId").setParam("siteId", siteId);
		if (topicId != null) {
			f.append(" and bean.topic.id=:topicId")
					.setParam("topicId", topicId);
		}
		if (userId != null) {
			f.append(" and bean.creater.id=:userId");
			f.setParam("userId", userId);
		}
		f.append(" order by bean.indexCount");
		return find(f, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	public List<Number> getMemberReply(Integer webId, Integer memberId,
			int pageNo, int pageSize) {
		Finder f = Finder
				.create("select max(bean.id) from BbsPost bean where bean.indexCount>1");
		f.append(" and bean.site.id=:webId").setParam("webId", webId);
		f.append(" and bean.creater.id=:memberId").setParam("memberId",
				memberId);
		f.append(" group by bean.topic.id order by max(bean.id) desc");
		f.setFirstResult(pageNo);
		f.setMaxResults(pageSize);
		return find(f);
	}

	@SuppressWarnings("unchecked")
	public List<BbsPost> getPostByTopic(Integer topicId) {
		String hql = "select bean from BbsPost bean where bean.topic.id=?";
		return find(hql, topicId);
	}

	/**
	 * 找出不属于当前主题但属于当前版块的最新回贴
	 */
	public BbsPost getLastPost(Integer forumId, Integer topicId) {
		String hql = "select bean from BbsPost bean where bean.topic.id!=? and bean.topic.forum.id=? order by bean.createTime desc";
		List<BbsPost> lastPostNotThisTopic=find(hql,topicId,forumId);
		return lastPostNotThisTopic.size()>0?lastPostNotThisTopic.get(0):null;
	}
	
	public int getMemberReplyCount(Integer webId, Integer memberId) {
		StringBuilder f = new StringBuilder(
				"select count(DISTINCT bean.topic.id) from BbsPost bean where bean.indexCount>1");
		f.append(" and bean.site.id=:webId");
		f.append(" and bean.creater.id=:memberId");
		return ((Number) getSession().createQuery(f.toString()).setParameter(
				"webId", webId).setParameter("memberId", memberId).iterate()
				.next()).intValue();
	}

	public int getIndexCount(Integer topicId) {
		String hql = "select max(bean.indexCount) from BbsPost bean where bean.topic.id=:topicId";
		return ((Number) getSession().createQuery(hql).setParameter("topicId",
				topicId).iterate().next()).intValue() + 1;
	}

	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}
	public List<Integer> getList(int count, int orderby) {
		String hql = "select  max(bean.id) from BbsPost bean  group by bean.topic.id  ";
		if(orderby==1){
			hql+="order by max(post_id) desc";
		}else if(orderby==2){
			hql+="order by max(post_id) asc";
		}
		return getSession().createQuery(hql).setFirstResult(0).setMaxResults(count).setCacheable(false).list();
	}

	public BbsPost findById(Integer id) {
		BbsPost entity = get(id);
		return entity;
	}

	public BbsPost save(BbsPost bean) {
		getSession().save(bean);
		return bean;
	}

	public BbsPost deleteById(Integer id) {
		BbsPost entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<BbsPost> getEntityClass() {
		return BbsPost.class;
	}

	public void deleleByForumId(Integer forumId) {
		String hql = "delete BbsPost bean where bean.topic.forum.id=:forumId ";
		getSession().createQuery(hql).setInteger("forumId", forumId);
	}
	/**
	 * 统计论坛下贴子数
	 * @param forumId
	 * @return
	 */
	public int getCountByForumId(Integer forumId) {
		String hql = "select count(bean.id) from BbsPost bean where bean.topic.forum.id=:forumId";
		return ((Number) getSession().createQuery(hql).setParameter("forumId", forumId).iterate().next()).intValue();
	}

	@Override
	public Pagination getReplyToMe(Integer siteId, Integer userId, int pageNo,
			int pageSize) {
		Finder f = Finder.create("select bean from BbsPost bean where 1=1");
		f.append(" and bean.site.id=:siteId and bean.isRead=:isRead").setParam("siteId", siteId);
		if (userId != null) {
			f.append(" and (bean.topic.creater.id=:userId or bean.replyToBbsPost.creater.id=:replyToUser) and indexCount!=:indexCount");
			f.setParam("userId", userId);
			f.setParam("replyToUser", userId);
			f.setParam("isRead", false);
			f.setParam("indexCount", 1);
		}
		f.append(" order by bean.createTime desc");
		return find(f, pageNo, pageSize);
	}
	
	public int  getReplyToMeTotalCountByUserId(Integer userId){
		Finder f = Finder.create("from BbsPost bean where 1=1");
		f.append(" and bean.isRead=:isRead");
		if (userId != null) {
			f.append(" and (bean.topic.creater.id=:userId or bean.replyToBbsPost.creater.id=:replyToUser) and indexCount!=:indexCount");
			f.setParam("userId", userId);
			f.setParam("replyToUser", userId);
			f.setParam("isRead", false);
			f.setParam("indexCount", 1);
		}
		return 	countQueryResult(f);
	}
	
	/**
	 * 读取回贴并标记为已读
	 * @param id
	 * @return
	 */
	public BbsPost readById(Integer id) {
		BbsPost entity = get(id);
		entity.setIsRead(true);
		getSession().update(entity);
		return entity;
	}
	
	/**
	 * 统计指定用户对指定主题的发贴量
	 * @param topicId
	 * @param createrId
	 * @return
	 */
	public int  getTotalCountByTopicIdAndCreaterId(Integer topicId,Integer createrId){
		Finder f = Finder.create("from BbsPost bean where bean.topic.id=:topicId and bean.creater.id=:createrId");
		f.setParam("topicId",topicId);
		f.setParam("createrId",createrId);
		return 	countQueryResult(f);
	}
	
	@SuppressWarnings("unchecked")
	public List<BbsPost> getPostByTopic(Integer topicId,Integer indexCount) {
		String hql = "select bean from BbsPost bean where bean.topic.id=? and bean.indexCount=?";
		return find(hql, topicId,indexCount);
	}
	
	public BbsPost getFirstPostByTopicId(Integer topicId){
		return getPostByTopic(topicId, 1).get(0);
	}
	//得到首贴所有图片路径
	public List<String>getFirstPostImageSrcList(Integer topicId){
		BbsPost post=getFirstPostByTopicId(topicId);
		return HtmlParserUtil.getImgSrcFromString(post.getPostText().getContent());
	}

}