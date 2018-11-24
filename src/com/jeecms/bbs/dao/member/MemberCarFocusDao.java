/**
 * 
 */
package com.jeecms.bbs.dao.member;

 

import java.util.List;

import com.jeecms.bbs.entity.BbsMemberCarFocus;

/**
 * @author xuw
 *
 */
public interface MemberCarFocusDao {
	/***
	 * 
	 * @param memberCarFocus
	 * @return  MemberCarFocus
	 */
	public BbsMemberCarFocus save(BbsMemberCarFocus  memberCarFocus);
	
	public List<BbsMemberCarFocus> queryById(Integer uuid);
	
	public void deleteById(Long id);
}
