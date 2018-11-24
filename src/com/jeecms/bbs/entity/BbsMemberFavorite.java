/**
 * 
 */
package com.jeecms.bbs.entity;

import java.util.Date;

import com.jeecms.bbs.entity.base.BaseBbsMemberFavorite;

/**
 * @author xuw
 *
 */
public class BbsMemberFavorite extends BaseBbsMemberFavorite {

	public BbsMemberFavorite() {
		super();
	}

	public BbsMemberFavorite(Long id, Integer uuid, Integer type,
			Integer topicId, Integer boardId, String url, Date createDate,
			Integer createUser, Date updateDate, Integer updateUser) {
		super(id, uuid, type, topicId, boardId, url, createDate, createUser,
				updateDate, updateUser);
	}
	
}
