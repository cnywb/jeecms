/**
 * 
 */
package com.jeecms.bbs.dao.member;

import java.util.List;

import com.jeecms.bbs.entity.BbsMemberFavorite;
import com.jeecms.common.page.Pagination;

/**
 * @author xuw
 *
 */
public interface MemberFavoriteDao {
	
	public  BbsMemberFavorite  save(BbsMemberFavorite bbsMemberFavorite);
	
	public List<BbsMemberFavorite> queryFavoriteByUserId(Integer userId,Integer type);
	
	public List<BbsMemberFavorite> queryFavoriteByUserId(Integer topicId,Integer userId,Integer type);
	
	public void  delete(BbsMemberFavorite  favorite);
	
	public Pagination queryFavoriteTopicsByUserId(Integer userId,int pageNo,int pageSize,int type);
}
