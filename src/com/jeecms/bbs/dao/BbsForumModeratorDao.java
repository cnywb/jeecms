package com.jeecms.bbs.dao;


import java.util.List;
import com.jeecms.bbs.entity.BbsForumModerator;
import com.jeecms.common.page.Pagination;


public interface BbsForumModeratorDao {
	
	 public long add(BbsForumModerator t);
	
	 public List<BbsForumModerator>  findAllByForumIdAndUserId(int forumId,int userId);
	 
	 public List<BbsForumModerator>  findAllByForumId(int forumId);
	 
	 public BbsForumModerator getById(Long id);
	 
	 public Pagination getPage(String forumId,String createTimeMin,String createTimeMax,String updateTimeMin,String updateTimeMax, int pageNo,
				int pageSize);
	 public void delete(BbsForumModerator t);

}
