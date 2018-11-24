/**
 * 
 */
package com.jeecms.bbs.manager.member;

import java.util.List;

import com.jeecms.bbs.action.member.vo.CarFocusType;
import com.jeecms.bbs.entity.BbsMemberCarFocus;

/**
 * @author xuw
 *
 */
public interface BbsMemberCarFocusMng {
	/***
	 * 
	 * @param memberCarFocus
	 * @return  MemberCarFocus
	 */
	public BbsMemberCarFocus save(BbsMemberCarFocus  memberCarFocus);
	
	public List<BbsMemberCarFocus> queryById(Integer uuid);
	
	public List<CarFocusType> queryByUserId(Integer uuid);
	
	public void deleteById(Long id);
	
	public  List<CarFocusType> queryFocusFullCarType();
	
	public List<BbsMemberCarFocus> save(String[] carTypes,Integer uuid);
	
	
	public List<CarFocusType> queryFocusFullCarType(List<CarFocusType>  carFocusTypes);
	
 
}
