package com.jeecms.cms.dao.campaign.sfdj;

import java.util.List;

import com.jeecms.cms.entity.campaign.sfdj.MemberStoryPraise;



public interface MemberStoryPraiseDao {
	public int  add(MemberStoryPraise t);
	public int  delete(long memberStoryId,Integer createrId);
	public List<MemberStoryPraise>  findAllByMemberStoryId(long memberStoryId);
	public List<MemberStoryPraise>  findAllByMemberStoryIdAndCreaterId(long memberStoryId,int createrId);
	public int  getTotalCountByMemberStoryId(long memberStoryId);
	public int getTotalCountByMemberStoryIdCreaterId(String memberStoryId,Integer createrId);
	public int getTotalCountByCreaterId(Integer createrId);
}
