package com.jeecms.cms.manager.main.impl.campaign.sfdj;

import java.util.Date;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.jeecms.cms.dao.campaign.sfdj.MemberStoryPraiseDao;
import com.jeecms.cms.entity.campaign.sfdj.MemberStoryPraise;
import com.jeecms.cms.manager.campaign.sfdj.MemberStoryMng;
import com.jeecms.cms.manager.campaign.sfdj.MemberStoryPraiseMng;
import com.jeecms.common.util.DateUtils;



@Service
@Transactional(rollbackFor=Exception.class) 
public class MemberStoryPraiseMngImpl implements MemberStoryPraiseMng {
	
	@Autowired
	private MemberStoryPraiseDao memberStoryPraiseDao;
	
	
	
	@Autowired
	private MemberStoryMng memberStoryMng;
	

	public int doAdd(MemberStoryPraise t){
		return memberStoryPraiseDao.add(t);
	}
	
	@Override
	public int add(MemberStoryPraise t) {
		Date limitDate=DateUtils.parseDate("2015-01-26 22:00:00", DateUtils.FORMAT_DATE_TIME_DEFAULT);
		Date currentDate=new Date();
		if(currentDate.getTime()>=limitDate.getTime()){
			return 777;
		}
		if(t.getCreater()==null){
			return 999;
		}
		/*if(t.getCreater().getGroup().getId()!=2){
			return 888;
		}*/
		
		
		int addResult=doAdd(t);
		if(addResult==1){//如果添加成功则更新投票统计
		  int praiseCount=memberStoryPraiseDao.getTotalCountByMemberStoryId(t.getMemberStory().getId());
		  memberStoryMng.updatePraiseCount(praiseCount, t.getMemberStory().getId());
		}
		return addResult;
	}

	public int doDelete(long memberStoryId, Integer createrId) {
		return memberStoryPraiseDao.delete(memberStoryId, createrId);
	}
	
	@Override
	public int delete(long memberStoryId, Integer createrId) {
		int delResult=doDelete(memberStoryId,createrId) ;
		if(delResult==1){
			 int praiseCount=memberStoryPraiseDao.getTotalCountByMemberStoryId(memberStoryId);
			 memberStoryMng.updatePraiseCount(praiseCount, memberStoryId);
		}
		return delResult;
	}

	@Override
	public List<MemberStoryPraise> findAllByMemberStoryId(long memberStoryId) {
		return memberStoryPraiseDao.findAllByMemberStoryId(memberStoryId);
	}

	@Override
	public List<MemberStoryPraise> findAllByMemberStoryIdAndCreaterId(
			long memberStoryId, int createrId) {
		return memberStoryPraiseDao.findAllByMemberStoryIdAndCreaterId(memberStoryId, createrId);
	}

	public int getTotalCountByMemberStoryId(long memberStoryId) {
		return memberStoryPraiseDao.getTotalCountByMemberStoryId(memberStoryId);
	}
	
	public int getTotalCountByMemberStoryIdCreaterId(String memberStoryId,Integer createrId){
		return memberStoryPraiseDao.getTotalCountByMemberStoryIdCreaterId(memberStoryId, createrId);
	}
	public int getTotalCountByCreaterId(Integer createrId){
		return memberStoryPraiseDao.getTotalCountByCreaterId(createrId);
	}
}
