package com.jeecms.bbs.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.BbsTopicDraftDao;
import com.jeecms.bbs.entity.BbsTopicDraft;
import com.jeecms.bbs.manager.BbsTopicDraftMng;
import com.jeecms.common.page.Pagination;



@Service
@Transactional
public class BbsTopicDraftMngImpl implements BbsTopicDraftMng {
	
	@Autowired
	private BbsTopicDraftDao bbsTopicDraftDao;
	
	public BbsTopicDraft save(BbsTopicDraft t){
		return bbsTopicDraftDao.save(t);
	}
    public List<BbsTopicDraft>  findAllByUserId(int userId){
    	return bbsTopicDraftDao.findAllByUserId(userId);
    }
    public int deleteByIdAndUserId(long id,int userId){
    	return bbsTopicDraftDao.deleteByIdAndUserId(id, userId);
    }
    
    public int deleteByUserId(int userId){
    	return bbsTopicDraftDao.deleteByUserId(userId);
    }
    public int  getTotalCountByUserId(Integer userId){
    	return bbsTopicDraftDao.getTotalCountByUserId(userId);
    }
    public Pagination getPage( int pageNo,int pageSize){
    	return bbsTopicDraftDao.getPage(pageNo, pageSize);
    }

}
