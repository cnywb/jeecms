/**
 * 
 */
package com.jeecms.bbs.dao.member;

 

import java.util.List;

import com.jeecms.bbs.entity.BbsMemberCar;
import com.jeecms.common.page.Pagination;

/**
 * @author xuw
 *
 */
public interface MemberCarDao {
	/***
	 * 
	 * @param memberCarFocus
	 * @return  MemberCarFocus
	 */
	public BbsMemberCar save(BbsMemberCar  bbsMemberCar);
	
	public List<BbsMemberCar> queryById(Integer uuid);
	
	public void deleteById(Long id);
	
	public BbsMemberCar findById(Long id);
	
	
	public BbsMemberCar  update(BbsMemberCar   bbsMemberCar);
	/***
	 * 
	 * @param userId 用户ID
	 * @param finder 分页查询对象
	 * @param pageNo 
	 * @param pageSize
	 * @return
	 */
	public Pagination queryFriendCarByUserId(Integer userId, int pageNo, int pageSize);
}
