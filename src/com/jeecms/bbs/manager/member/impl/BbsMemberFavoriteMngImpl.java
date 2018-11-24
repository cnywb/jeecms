package com.jeecms.bbs.manager.member.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.jeecms.bbs.action.member.vo.ForumVo;
import com.jeecms.bbs.dao.member.MemberFavoriteDao;
import com.jeecms.bbs.entity.BbsForum;
import com.jeecms.bbs.entity.BbsMemberFavorite;
import com.jeecms.bbs.manager.BbsForumMng;
import com.jeecms.bbs.manager.member.BbsMemberFavoriteMng;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class BbsMemberFavoriteMngImpl  implements  BbsMemberFavoriteMng{
	
	
	
	
	@Autowired
	private MemberFavoriteDao  memberFavoriteDao;
	
	
	@Autowired
	private BbsForumMng  bbsForumMng;

	@Override
	public BbsMemberFavorite save(BbsMemberFavorite bbsMemberFavorite) {
		return memberFavoriteDao.save(bbsMemberFavorite);
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.jeecms.bbs.manager.member.BbsMemberFavoriteMng#deleteById(java.lang.Long)
	 */
	@Override
	public void deleteById(Long id) {
		BbsMemberFavorite bbsMemberFavorite=new BbsMemberFavorite();
		bbsMemberFavorite.setId(id);
		this.memberFavoriteDao.delete(bbsMemberFavorite);
	}
	
	
	
	
	
	

	/* (non-Javadoc)
	 * @see com.jeecms.bbs.manager.member.BbsMemberFavoriteMng#saveForums(java.lang.String[], java.lang.Integer)
	 */
	@Override
	public List<BbsMemberFavorite> saveForums(String[] forumsId, Integer uuid) {
		if (forumsId == null) {
			return null;
		}
		List<BbsMemberFavorite> list = new ArrayList<BbsMemberFavorite>();
		for (String str : forumsId) {
			BbsMemberFavorite favorite = new BbsMemberFavorite();
			favorite.setType(FORUM_TYPE);
			favorite.setUuid(uuid);
			favorite.setTopicId(Integer.parseInt(str));
			favorite.setUpdateUser(uuid);
			favorite.setUpdateDate(new Date());
			favorite.setCreateDate(new Date());
			favorite.setCreateUser(uuid);
			list.add(favorite);

		}
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		list = this.saveObjects(list);
		return list;
	}
	
	public BbsMemberFavorite  saveTopic(Integer topicId,Integer userId,Integer type){
		BbsMemberFavorite favorite =null;
		if(this.checkFavoriteTopicNoExist(topicId, userId)){
			favorite =new BbsMemberFavorite();
			if(type==0){
				favorite.setType(TOPIC_TYPE);
			}else{
				favorite.setType(type);
			}
			favorite.setUuid(userId);
			favorite.setTopicId(topicId);
			favorite.setUpdateUser(userId);
			favorite.setUpdateDate(new Date());
			favorite.setCreateDate(new Date());
			favorite.setCreateUser(userId);
			favorite=this.save(favorite);
		}
		return favorite;
	}

	public List<BbsMemberFavorite> saveObjects(List<BbsMemberFavorite> list) {
		List<BbsMemberFavorite> favorites = new ArrayList<BbsMemberFavorite>();
		for (BbsMemberFavorite favorite : list) {
			favorite = this.save(favorite);
			favorites.add(favorite);
		}
		return favorites;
	}



	/* (non-Javadoc)
	 * @see com.jeecms.bbs.manager.member.BbsMemberFavoriteMng#queryForumBysiteId(java.lang.Integer)
	 */
	@Override
	public List<BbsForum> queryForumBysiteId(Integer siteId) {
		return bbsForumMng.getList(siteId);
	}

	
	
	
 



	/* (non-Javadoc)
	 * @see com.jeecms.bbs.manager.member.BbsMemberFavoriteMng#queryFavoriteByUserId(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<BbsMemberFavorite> queryFavoriteByUserId(Integer userId,
			Integer type) {
		return  this.memberFavoriteDao.queryFavoriteByUserId(userId, type);
	}

	
	


	/* (non-Javadoc)
	 * @see com.jeecms.bbs.manager.member.BbsMemberFavoriteMng#checkFavoriteTopicExist(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly=true)
	public boolean checkFavoriteTopicNoExist(Integer topicId, Integer userId) {
	    List<BbsMemberFavorite> bbsMemberFavorites=this.memberFavoriteDao.queryFavoriteByUserId(topicId, userId, BbsMemberFavoriteMng.TOPIC_TYPE);
	    if(CollectionUtils.isEmpty(bbsMemberFavorites)||bbsMemberFavorites.size()==0){
	    	 return true;
	    }
		return false;
	}



	/* (non-Javadoc)
	 * @see com.jeecms.bbs.manager.member.BbsMemberFavoriteMng#queryAllForumByUserId(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<ForumVo> queryAllForumByUserId(Integer userId, Integer siteId) {
	 
		List<ForumVo>  forumVos=this.queryForumByUserId(userId, siteId);
		Map<Integer,ForumVo> forumVoMap=new HashMap<Integer, ForumVo>();
		
		 if(!CollectionUtils.isEmpty(forumVos)){
		     for(ForumVo favorite:forumVos){
		    	 ForumVo  forumVo=new ForumVo();
		    	 forumVo.setId(favorite.getId());
		    	 forumVo.setForumId(favorite.getForumId());
		    	 forumVo.setUserId(userId);
		    	 forumVo.setSiteId(siteId);
		    	// forumVo.setTitle(forumVo.getTitle());
		    	 forumVo.setDisabled(Boolean.TRUE);
		    	 forumVoMap.put(favorite.getForumId(),forumVo);
		     }
		 }
	     
	     List<BbsForum>  forumsSite=bbsForumMng.getList(siteId);
	     if(!CollectionUtils.isEmpty(forumsSite)){
		     for(BbsForum bbsForum:forumsSite){
		    	 ForumVo  forumVo=null;
		    	 forumVo=forumVoMap.get(bbsForum.getId());
		    	 if(forumVo!=null){
		    		 forumVo.setDisabled(Boolean.TRUE);
		    	 }else{
		    		 forumVo=new ForumVo();
		    	 }
		    	 forumVo.setForumId(bbsForum.getId());
		    	 forumVo.setUserId(userId);
		    	 forumVo.setSiteId(siteId);
		    	 forumVo.setTitle(bbsForum.getTitle());
		    	 forumVo.setUrl("/"+bbsForum.getPath()+"/index.jhtml");
		    	 forumVoMap.put(bbsForum.getId(),forumVo);
		     }
	     }
	     
	     List<ForumVo>  list=new ArrayList<ForumVo>();
	     for(Integer key:forumVoMap.keySet()){
	    	 list.add(forumVoMap.get(key));
	     }
		 return list;
	}



	/* (non-Javadoc)
	 * @see com.jeecms.bbs.manager.member.BbsMemberFavoriteMng#queryForumByUserId(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<ForumVo> queryForumByUserId(Integer userId, Integer siteId) {
		 List<BbsMemberFavorite>  favorites=this.queryFavoriteByUserId(userId,BbsMemberFavoriteMng.FORUM_TYPE);
		 
		 Map<Integer,ForumVo> forumVoMap=new HashMap<Integer, ForumVo>();
		 if(!CollectionUtils.isEmpty(favorites)){
		     for(BbsMemberFavorite favorite:favorites){
		    	 ForumVo  forumVo=new ForumVo();
		    	 forumVo.setId(favorite.getId());
		    	 forumVo.setForumId(favorite.getTopicId());
		    	 forumVo.setUserId(userId);
		    	 forumVo.setSiteId(siteId);
		    	 forumVo.setTitle(forumVo.getTitle());
		    	 forumVo.setDisabled(Boolean.TRUE);
		    	 forumVoMap.put(favorite.getTopicId(),forumVo);
		     } 
		 }
	     
	     List<ForumVo>  list=new ArrayList<ForumVo>();
	     for(Integer key:forumVoMap.keySet()){
	    	 list.add(forumVoMap.get(key));
	     }
		 return list;
	}



	/* (non-Javadoc)
	 * @see com.jeecms.bbs.manager.member.BbsMemberFavoriteMng#queryFavoriteTopicByUserId(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly=true)
	public Pagination queryFavoriteTopicsByUserId(Integer userId,int pageNo,int pageSize) {
		return this.memberFavoriteDao.queryFavoriteTopicsByUserId(userId, pageNo, pageSize, BbsMemberFavoriteMng.TOPIC_TYPE);
	}
	
}
