package com.jeecms.cms.manager.main.impl.campaign.sfdj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.campaign.sfdj.MemberStoryDao;
import com.jeecms.cms.entity.campaign.sfdj.MemberStory;

import com.jeecms.cms.manager.campaign.sfdj.MemberStoryMng;
import com.jeecms.common.page.Pagination;


@Service
@Transactional(rollbackFor=Exception.class) 
public class MemberStoryMngImpl implements MemberStoryMng {
	@Autowired
	private MemberStoryDao memberStoryDao;
	

	@Override
	public int add(MemberStory t) {
		return memberStoryDao.add(t);
	}

	@Override
	public int update(MemberStory t) {
		
		return memberStoryDao.update(t);
	}

	@Override
	public int updateStatus(MemberStory t) {
		
		return memberStoryDao.updateStatus(t);
	}
	

	public int  updatePraiseCount(Integer praiseCount,Long memberStoryId){
		return memberStoryDao.updatePraiseCount(praiseCount,memberStoryId);
	}

	@Override
	public List<MemberStory> findAllByCreaterId(int createrId) {
	
		return memberStoryDao.findAllByCreaterId(createrId);
	}

	@Override
	public Pagination getPage(String status, String userName,String createTimeMin, String createTimeMax, int pageNo, int pageSize) {
		return memberStoryDao.getPage(status, userName, createTimeMin, createTimeMax, pageNo, pageSize);
	}

	@Override
	public MemberStory findById(long id) {
		return memberStoryDao.findById(id);
	}

	@Override
	public Pagination getPageForPass(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return memberStoryDao.getPageForPass(pageNo, pageSize);
	}

	public int getTotalCountByCreaterId(Integer createrId){
		return memberStoryDao.getTotalCountByCreaterId(createrId);
	}
}
