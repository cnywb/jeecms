package com.jeecms.bbs.dao.impl;


import java.util.List;

import com.jeecms.bbs.dao.BbsTopicDraftDao;
import com.jeecms.bbs.entity.BbsTopicDraft;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

public class BbsTopicDraftDaoImpl extends HibernateBaseDao<BbsTopicDraft, Long>
		implements BbsTopicDraftDao {

	@Override
	protected Class<BbsTopicDraft> getEntityClass() {
		// TODO Auto-generated method stub
		return BbsTopicDraft.class;
	}
	
	//
	public BbsTopicDraft save(BbsTopicDraft t){
		//ajax提交html文本时自动加上＼ 注意它不是反斜杠，而是特殊字符所以要还原回去
		t.setContent(t.getContent().replaceAll("＼","").replaceAll("/＞","/>"));
		List<BbsTopicDraft> list=findAllByUserId(t.getUserId());
		if(list.size()>0){
			BbsTopicDraft bbsTopicDraft=list.get(0);
			if(bbsTopicDraft.getUserId().intValue()!=t.getUserId().intValue()){
				t.setId(0L);
				return t;//防止用户恶意修改别人的稿子
			}else{
				bbsTopicDraft.setContent(t.getContent());
				bbsTopicDraft.setForumId(t.getForumId());
				bbsTopicDraft.setPostTypeId(t.getPostTypeId());
				bbsTopicDraft.setTitle(t.getTitle());
				getSession().update(bbsTopicDraft);
			}
		}else{
			getSession().save(t);
   		}
		return t;
	}
	
	
    public List<BbsTopicDraft>  findAllByUserId(int userId){
			String hql = "select bean from BbsTopicDraft bean where bean.userId=?";
			return find(hql,userId);
	}
    
    public int deleteByIdAndUserId(long id,int userId){
		String sql="delete from BbsTopicDraft bean  where bean.id=:id and bean.userId=:userId";
		return getSession().createQuery(sql).setParameter("id", id).setParameter("userId",userId).executeUpdate();
	}
    
    public int deleteByUserId(int userId){
  		String sql="delete from BbsTopicDraft bean  where bean.userId=:userId";
  		return getSession().createQuery(sql).setParameter("userId",userId).executeUpdate();
  	}
	
    public int  getTotalCountByUserId(Integer userId){
		Finder f = Finder.create("from BbsTopicDraft bean where bean.userId=:userId");
		f.setParam("userId",userId);
		return 	countQueryResult(f);
	}
	
    
    
    public Pagination getPage( int pageNo,int pageSize) {
    	Finder f = Finder.create("from BbsTopicDraft bean where 1=1 order by id desc");
		return find(f, pageNo, pageSize);
	}
    
 

}
