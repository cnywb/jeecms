package com.jeecms.bbs.dao.member.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.bbs.dao.member.MemberFavoriteDao;
import com.jeecms.bbs.entity.BbsMemberFavorite;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class MemberFavoriteDaoImpl extends HibernateBaseDao<BbsMemberFavorite,Long> implements 	MemberFavoriteDao {

	@Override
	public BbsMemberFavorite save(BbsMemberFavorite bbsMemberFavorite) {
		this.getSession().save(bbsMemberFavorite);
		return bbsMemberFavorite;
	}

	
	
	/* (non-Javadoc)
	 * @see com.jeecms.bbs.dao.member.MemberFavoriteDao#queryFavoriteByUserId(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<BbsMemberFavorite> queryFavoriteByUserId(Integer userId,Integer type) { 
		String hql = "from BbsMemberFavorite t where t.uuid=:uuid and type=:type";
		Finder f = Finder.create(hql);
		f.setParam("uuid",userId);
		f.setParam("type",type);
		return find(f);
	}
	
	@Override
	public List<BbsMemberFavorite> queryFavoriteByUserId(Integer topicId,Integer userId,Integer type) { 
		String hql = "from BbsMemberFavorite t where  t.topicId=:topicId and t.uuid=:uuid and type=:type";
		Finder f = Finder.create(hql);
		f.setParam("topicId",topicId);
		f.setParam("uuid",userId);
		f.setParam("type",type);
		return find(f);
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see com.jeecms.bbs.dao.member.MemberFavoriteDao#queryFavoriteTopicsByUserId(java.lang.Integer)
	 */
	@Override
	public Pagination queryFavoriteTopicsByUserId(Integer userId,int pageNo,int pageSize,int type) {
		String hql="select t   from   BbsMemberFavorite f,BbsTopic t where  t.id=f.topicId and f.uuid=:userId and f.type=:type  order by t.lastTime desc";
		Finder finder = Finder.create(hql);
		finder.setParam("userId",userId);
		finder.setParam("type",type);
		Pagination pagination=super.find(finder, pageNo, pageSize);
		return pagination;
	}



	public void  delete(BbsMemberFavorite  favorite){
		 
		if (favorite != null) {
			getSession().delete(favorite);
		}
	}

	@Override
	protected Class<BbsMemberFavorite> getEntityClass() {
		return this.getEntityClass();
	}
}
