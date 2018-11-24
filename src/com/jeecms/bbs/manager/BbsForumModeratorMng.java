package com.jeecms.bbs.manager;


import java.util.List;

import com.jeecms.bbs.entity.BbsForumModerator;
import com.jeecms.common.page.Pagination;

public interface BbsForumModeratorMng {
	
	 public Pagination getPage(String forumId,String createTimeMin,String createTimeMax,String updateTimeMin,String updateTimeMax, int pageNo,
				int pageSize);
	 
	 public long deleteById(long id);
	 public List<BbsForumModerator>  findAllByForumId(int forumId);
	 public String findAllNamesByByForumId(int forumId);

}
