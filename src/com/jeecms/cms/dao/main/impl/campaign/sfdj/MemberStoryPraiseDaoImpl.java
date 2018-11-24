package com.jeecms.cms.dao.main.impl.campaign.sfdj;


import java.util.List;

import com.jeecms.cms.dao.campaign.sfdj.MemberStoryPraiseDao;
import com.jeecms.cms.entity.campaign.sfdj.MemberStoryPraise;

import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;

public class MemberStoryPraiseDaoImpl extends
		HibernateBaseDao<MemberStoryPraise, Long> implements MemberStoryPraiseDao{

	@Override
	protected Class<MemberStoryPraise> getEntityClass() {
		return MemberStoryPraise.class;
	}
	
	public long save(MemberStoryPraise t){
		getSession().save(t);
		return t.getId();
	}
	public int add(MemberStoryPraise t){
		List<MemberStoryPraise> list=findAllByMemberStoryIdAndCreaterId(t.getMemberStory().getId(), t.getCreater().getId());
		if(list.size()>0){
			return 0;
		}
		save(t);
		return 1;
	}
	
	
	public int  delete(long memberStoryId,Integer createrId){
		String sql="delete from MemberStoryPraise  where memberStory.id=:memberStoryId and creater.id=:createrId";
		return getSession().createQuery(sql).setParameter("memberStoryId", memberStoryId).setParameter("createrId",createrId).executeUpdate();
	}
	public List<MemberStoryPraise>  findAllByMemberStoryId(long memberStoryId){
		String hql = "select bean from MemberStoryPraise bean where bean.memberStory.id=?";
		return find(hql,memberStoryId);
	}
	
	public List<MemberStoryPraise>  findAllByMemberStoryIdAndCreaterId(long memberStoryId,int createrId){
		String hql = "select bean from MemberStoryPraise bean where bean.memberStory.id=? and bean.creater.id=?";
		return find(hql,memberStoryId,createrId);
	}
	
	public List<Object[]> summary(){
		String hql = "select bean.creater.username,count(bean.id) from MemberStoryPraise bean where group by bean.creater.username order by count(bean.id) desc";
		return find(hql);
	}
	
	
	public int  getTotalCountByMemberStoryId(long memberStoryId){
		String hql = "select bean.id,bean.memberStory.id from MemberStoryPraise bean where bean.memberStory.id=?";
		return find(hql,memberStoryId).size();
	}
	
	public int getTotalCountByMemberStoryIdCreaterId(String memberStoryId,Integer createrId){
		String hql = "select bean.id from MemberStoryPraise bean where bean.creater.id=:createrId and bean.memberStory.id in("+memberStoryId+")";
		Finder f = Finder.create(hql);
		f.setParam("createrId",createrId);
		return 	countQueryResult(f);
	}
	
	public int getTotalCountByCreaterId(Integer createrId){
		Finder f = Finder.create("from MemberStoryPraise bean where bean.creater.id=:createrId");
		f.setParam("createrId",createrId);
		return 	countQueryResult(f);
	}
}
