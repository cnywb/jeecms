/**
 * 
 */
package com.jeecms.bbs.manager.member;

import java.util.List;

import com.jeecms.bbs.action.member.vo.ForumVo;
import com.jeecms.bbs.entity.BbsForum;
import com.jeecms.bbs.entity.BbsMemberFavorite;
import com.jeecms.common.page.Pagination;

/**
 * @author xuw
 *
 */
public interface BbsMemberFavoriteMng {
	
	
	public static final Integer TOPIC_TYPE=2;
	
	public static final Integer FORUM_TYPE=1;
	
	public  BbsMemberFavorite  save(BbsMemberFavorite bbsMemberFavorite);
	
	
	public List<BbsForum> queryForumBysiteId(Integer siteId);
	/***
	 * 
	 * @param userId  
	 * @return
	 */
	public List<ForumVo> queryForumByUserId(Integer userId,Integer siteId);
	
 
	public Pagination  queryFavoriteTopicsByUserId(Integer userId,int pageNo,int pageSize);
	
	
	public List<ForumVo> queryAllForumByUserId(Integer userId,Integer siteId);
	
	public List<BbsMemberFavorite> queryFavoriteByUserId(Integer userId,Integer type);
	
	public void deleteById(Long id);
	
	public boolean  checkFavoriteTopicNoExist(Integer topicId,Integer userId);
	
	public List<BbsMemberFavorite> saveForums(String[] forumsId, Integer uuid);
	
	public BbsMemberFavorite  saveTopic(Integer topicId,Integer  userId,Integer type);
	
	public List<BbsMemberFavorite> saveObjects(List<BbsMemberFavorite> list);

}
