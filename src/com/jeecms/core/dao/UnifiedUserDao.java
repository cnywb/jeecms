package com.jeecms.core.dao;

import java.util.List;

import org.hibernate.LockMode;

import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.UnifiedUser;

public interface UnifiedUserDao {
	public UnifiedUser getByUsername(String username);

	public UnifiedUser getByMobilePhone(String mobilePhone);
	
	public List<UnifiedUser> getByEmail(String email);

	public int countByEmail(String email);

	public Pagination getPage(int pageNo, int pageSize);

	public UnifiedUser findById(Integer id);

	public UnifiedUser save(UnifiedUser bean);

	public UnifiedUser updateByUpdater(Updater<UnifiedUser> updater);

	public UnifiedUser deleteById(Integer id);
	
	public UnifiedUser update(UnifiedUser bean);
	/**
	 * 根据用户ID查询一下
	 * @param openId
	 * @return
	 */
	public UnifiedUser getByOpenId(String openId);
	/***
	 * 锁定用户
	 * @param userId 用户ID
	 * @param lockMode 
	 * @return UnifiedUser
	 */
	public UnifiedUser lockUser(Integer userId, LockMode lockMode);
}