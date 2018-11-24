/**
 * 
 */
package com.jeecms.point.manager.point;

import com.jeecms.core.entity.UnifiedUser;

/**
 * 用户积分接口类
 * @author wanglijun
 *
 */
public interface PointUserMng {
	/***
	 *根据用户ID查询
	 * @param id
	 * @return
	 */
	public UnifiedUser getUser(Integer id);
	
	/**8
	 * 
	 * @param id
	 * @return
	 */
	public long  getUserPointById(Integer id);
	
	/***
	 * 更新用户积分
	 * @param userId 用户ID
	 * @param currentPointNum 用户当前积分
	 * @param productPointNum 产品积分
	 * @return UnifiedUser 用户
	 */
	public boolean updateUserPoint(Integer userId,Long productPointNum);
	/***
	 * 根据openId查询数据
	 * @param openId 微信openId
	 * @return  微信openId
	 */
	public UnifiedUser findByOpenId(String openId);
	
	
}
