package com.jeecms.bbs.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.BbsForumModeratorDao;
import com.jeecms.bbs.entity.BbsForumModerator;
import com.jeecms.bbs.entity.BbsUserGroup;
import com.jeecms.bbs.manager.BbsForumModeratorMng;
import com.jeecms.bbs.manager.BbsUserGroupMng;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.common.page.Pagination;


@Service
@Transactional
public class BbsForumModeratorMngImpl implements BbsForumModeratorMng {
	@Autowired
	private BbsForumModeratorDao bbsForumModeratorDao;

	@Autowired
	private BbsUserGroupMng bbsUserGroupMng;
	@Autowired
	private BbsUserMng bbsUserMng;
	
	 public Pagination getPage(String forumId,String createTimeMin,String createTimeMax,String updateTimeMin,String updateTimeMax, int pageNo,
				int pageSize){
		return bbsForumModeratorDao.getPage(forumId, createTimeMin, createTimeMax, updateTimeMin, updateTimeMax, pageNo, pageSize);
	 }
	 
	 public long deleteById(long id){
		 BbsForumModerator  t=bbsForumModeratorDao.getById(id);
		 if(t==null){
			 return 0L;
		 }
		 
		 BbsUserGroup userGroup=new BbsUserGroup();
		 userGroup.setId(1);
		 t.getUser().setGroup(userGroup);//设为白丁
		 bbsUserMng.update(t.getUser());
		 bbsForumModeratorDao.delete(t);
		 return 1L;
	 }
	 
	 public List<BbsForumModerator>  findAllByForumId(int forumId){
			 return bbsForumModeratorDao.findAllByForumId(forumId);
	 }
	 
	 public String findAllNamesByByForumId(int forumId){
		 List<BbsForumModerator>  list=findAllByForumId(forumId);
		 StringBuffer sb=new StringBuffer();
		 int i=0;
		 for(BbsForumModerator bm:list){
             if(i!=0){
            	 sb.append(",");
			 }
			 sb.append(bm.getUser().getUsername());
			i=i+1;
		 }
		 return sb.toString();
	 }
	
}
