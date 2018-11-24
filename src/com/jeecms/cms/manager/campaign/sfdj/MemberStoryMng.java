package com.jeecms.cms.manager.campaign.sfdj;

import java.util.List;


import com.jeecms.cms.entity.campaign.sfdj.MemberStory;
import com.jeecms.common.page.Pagination;

public interface MemberStoryMng {
	public int add(MemberStory t);
	public int  update(MemberStory t);
	public int  updateStatus(MemberStory t);
	public int  updatePraiseCount(Integer praiseCount,Long memberStoryId);
	public MemberStory findById(long id);
	public List<MemberStory>  findAllByCreaterId(int createrId);
	public Pagination getPage(String status,String userName,String createTimeMin,String createTimeMax, int pageNo,int pageSize);
	public Pagination getPageForPass(int pageNo,int pageSize);
	public int getTotalCountByCreaterId(Integer createrId);
}
