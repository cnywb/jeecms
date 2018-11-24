package com.jeecms.bbs.manager.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.dao.BbsUserOnlineDao;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.entity.BbsUserOnline;
import com.jeecms.bbs.manager.BbsUserOnlineMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class BbsUserOnlineMngImpl implements BbsUserOnlineMng {

	@Transactional(readOnly = true)
	public List<BbsUserOnline> getList() {
		return dao.getList();
	}
	
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public BbsUserOnline findById(Integer id) {
		BbsUserOnline entity = dao.findById(id);
		return entity;
	}

	public BbsUserOnline save(BbsUserOnline bean) {
		dao.save(bean);
		return bean;
	}

	public BbsUserOnline update(BbsUserOnline bean) {
		Updater<BbsUserOnline> updater = new Updater<BbsUserOnline>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public BbsUserOnline deleteById(Integer id) {
		BbsUserOnline bean = dao.deleteById(id);
		return bean;
	}

	public BbsUserOnline[] deleteByIds(Integer[] ids) {
		BbsUserOnline[] beans = new BbsUserOnline[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	
	/**
	 * 会话消除时更新用户在线时长信息
	 */
	public void updateUserOnlineInfoForSessionUnBind(BbsUser bbsUser){
		BbsUserOnline online = bbsUser.getUserOnline();
		Date lastLoginTime=bbsUser.getLastLoginTime();
		Date now=new Date();
		long onlineLatest=now.getTime()-lastLoginTime.getTime();
		online.setOnlineLatest(Double.valueOf(String.valueOf(onlineLatest/(1000*60))));//最近在线时长,分钟
		online.setOnlineTotal(online.getOnlineTotal()+(onlineLatest/(1000*60)));
		update(online);
	}
	
	
	private BbsUserOnlineDao dao;

	@Autowired
	public void setDao(BbsUserOnlineDao dao) {
		this.dao = dao;
	}

}
