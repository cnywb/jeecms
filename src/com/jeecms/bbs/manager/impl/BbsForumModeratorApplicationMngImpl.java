package com.jeecms.bbs.manager.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.BbsForumModeratorApplicationDao;
import com.jeecms.bbs.dao.BbsForumModeratorDao;
import com.jeecms.bbs.entity.BbsForumModerator;
import com.jeecms.bbs.entity.BbsForumModeratorApplication;
import com.jeecms.bbs.entity.BbsMessage;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.entity.BbsUserGroup;
import com.jeecms.bbs.manager.BbsForumModeratorApplicationMng;
import com.jeecms.bbs.manager.BbsMessageMng;
import com.jeecms.bbs.manager.BbsUserGroupMng;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.common.page.Pagination;


@Service
@Transactional
public class BbsForumModeratorApplicationMngImpl implements
		BbsForumModeratorApplicationMng {
	
	@Autowired
	private BbsForumModeratorApplicationDao bbsForumModeratorApplicationDao;
	@Autowired
	private BbsForumModeratorDao bbsForumModeratorDao;
	
	@Autowired
	private BbsUserGroupMng bbsUserGroupMng;
	@Autowired
	private BbsUserMng bbsUserMng;
	@Autowired
	private BbsMessageMng bbsMessageMng;
	
	
	public long add(BbsForumModeratorApplication t){
	   List<BbsForumModerator>	bmList=bbsForumModeratorDao.findAllByForumIdAndUserId(t.getForum().getId(), t.getUser().getId());
		if(bmList.size()>0){//已经是该版版主，则无法申请
		  return 0L;
	     }	
	   List<BbsForumModeratorApplication> list=bbsForumModeratorApplicationDao.findAllByForumIdAndUserId(t.getForum().getId(), t.getUser().getId());
	   if(list.size()>0){//如果有正在待审批的申请，则无法申请
		  return 1L;
	   }
	   bbsForumModeratorApplicationDao.add(t);
	   return 2L;
	}
	
	public long passApplication(long id){
		BbsForumModeratorApplication t=bbsForumModeratorApplicationDao.getById(id);
		if(t==null){
			return 0L;
		}
		if(t.getStatus()!=0){
			return 1L;
		}
		t.setStatus(1);
		short groupType=2;
		List<BbsUserGroup> groupList=bbsUserGroupMng.getList(2,groupType);
		for(BbsUserGroup g:groupList){
			if(g.getName().equals("版主")){
				BbsUser user=t.getUser();
				user.setGroup(g);
				bbsUserMng.update(user);//更新用户组
			}
		}
		
		BbsForumModerator  bbsForumModerator=new BbsForumModerator();
		bbsForumModerator.setForum(t.getForum());
		bbsForumModerator.setUser(t.getUser());
		bbsForumModeratorDao.add(bbsForumModerator);//添加版主记录
		bbsForumModeratorApplicationDao.update(t);//更新版主申请记录
		sendMessage("恭喜，您的版主申请已通过！您已成为"+t.getForum().getTitle()+"版块版主。",t.getUser());
		return 2L;
	}

	private void sendMessage(String content,BbsUser user) {
		BbsMessage bbsMessage=new BbsMessage();
		bbsMessage.setContent(content);
		bbsMessage.setCreateTime(new Date());
		bbsMessage.setMsgType(4);
		bbsMessage.setUser(user);
		bbsMessage.setReceiver(user);
		BbsUser sys=new BbsUser();
		sys.setId(1);
		bbsMessage.setSender(sys);
		bbsMessage.setStatus(false);
		bbsMessage.setSys(true);
		bbsMessageMng.save(bbsMessage);
	}
	
	public long unPassApplication(long id){
		BbsForumModeratorApplication t=bbsForumModeratorApplicationDao.getById(id);
		if(t==null){
			return 0L;
		}
		if(t.getStatus()!=0){
			return 1L;
		}
		t.setStatus(2);
		bbsForumModeratorApplicationDao.update(t);
		sendMessage("抱歉，您的版主申请未通过！",t.getUser());
		return 2L;
	}
	
	public Pagination getPage(String status,String createTimeMin,String createTimeMax,String updateTimeMin,String updateTimeMax, int pageNo,
			int pageSize){
		return bbsForumModeratorApplicationDao.getPage(status, createTimeMin, createTimeMax, updateTimeMin, updateTimeMax, pageNo, pageSize);
	}
	

}
