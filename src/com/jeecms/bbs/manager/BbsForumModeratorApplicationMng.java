package com.jeecms.bbs.manager;


import com.jeecms.bbs.entity.BbsForumModeratorApplication;
import com.jeecms.common.page.Pagination;

public interface BbsForumModeratorApplicationMng {
	    public long add(BbsForumModeratorApplication t);
		
		public long passApplication(long id);
		
		public long unPassApplication(long id);
		
		public Pagination getPage(String status,String createTimeMin,String createTimeMax,String updateTimeMin,String updateTimeMax, int pageNo,
				int pageSize);
}
