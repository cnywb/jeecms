/**
 * 
 */
package com.jeecms.bbs.entity;

import java.util.Date;

import com.jeecms.bbs.entity.base.BaseBbsMemberCarFocus;

/**
 * @author xuw
 *
 */
public class BbsMemberCarFocus extends BaseBbsMemberCarFocus{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3092649912779191710L;

	public BbsMemberCarFocus() {
		super();
	}
	
	public BbsMemberCarFocus(Long id, Integer uuid, String carType,
			Date createDate, Integer createUser, Date updateDate,
			Integer updateUser) {
		super(id, uuid, carType, createDate, createUser, updateDate, updateUser);
	}
	
	public BbsMemberCarFocus(Long id) {
		super(id);
	}	
}
