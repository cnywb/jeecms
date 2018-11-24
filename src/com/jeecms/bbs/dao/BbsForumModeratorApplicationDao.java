package com.jeecms.bbs.dao;

import java.util.List;

import com.jeecms.bbs.entity.BbsForumModeratorApplication;
import com.jeecms.common.page.Pagination;

public interface BbsForumModeratorApplicationDao {
	public Long add(BbsForumModeratorApplication t);
    public List<BbsForumModeratorApplication>  findAllByForumIdAndUserId(int forumId,int userId);
	public BbsForumModeratorApplication getById(long id);
	public long update(BbsForumModeratorApplication t);
	public Pagination getPage(String status,String createTimeMin,String createTimeMax,String updateTimeMin,String updateTimeMax, int pageNo,
			int pageSize);
}
