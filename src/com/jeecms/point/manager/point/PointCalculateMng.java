/**
 * 
 */
package com.jeecms.point.manager.point;

import com.jeecms.point.entity.PointCalculateResult;
import com.jeecms.point.entity.PointRule;

/**
 * 
 * 积分服务管理
 * @author wanglijun
 *
 */
public interface PointCalculateMng {
	/***
	 * 用户绑定
	 * @param userId 用户ID
	 * @return PointCalculateResult 积分计算结果
	 */
	public PointCalculateResult regUserPoint(Integer userId);
	
	/***
	 * 用户绑定
	 * @param userId 用户ID
	 * @param channel 渠道
	 * @return PointCalculateResult 积分计算结果
	 */
	public PointCalculateResult regUserPoint(Integer userId,int channel);
	/**
	 * 用户认证添加积分
	 * @param userId 用户ID
	 * @return PointCalculateResult 积分计算结果
	 */
	public PointCalculateResult authUserPoint(Integer userId);
	/**
	 * 用户认证添加积分
	 * @param userId 用户ID
	 * @param 渠道
	 * @return PointCalculateResult 积分计算结果
	 */
	public PointCalculateResult authUserPoint(Integer userId,int channel);
	
	 
	/**
	 * 用户签到添加积分
	 * @param userId 用户ID
	 * @return PointCalculateResult 积分计算结果
	 */
	public PointCalculateResult singinUserPoint(Integer userId);
	/**
	 * 用户签到添加积分
	 * @param userId 用户ID
	 * @param 渠道
	 * @return PointCalculateResult 积分计算结果
	 */
	public PointCalculateResult singinUserPoint(Integer userId,int channel);
	
	/**
	 *完善个人信息添加积分
	 * @param userId 用户ID
	 * @param  num 更新数量 
	 * @return  PointCalculateResult
	 */
	public PointCalculateResult perfactUserInfo(Integer userId,int countNum);
	
	/**
	 * 完善个人信息添加积分
	 * @param userId 用户ID
	 * @param channel 渠道
	 * @param  num 更新数量 
	 * @return PointCalculateResult 积分计算结果
	 */
	public PointCalculateResult perfactUserInfo(Integer userId,int channel,int num);
	
	/**
	 * 发贴添加积分
	 * @param userId 用户ID
	 * @return  PointCalculateResult
	 */
	public PointCalculateResult issueTopicPoint(Integer userId);
	
	
	/**
	 * 检查发贴是否积分上限
	 * @param userId 用户ID
	 * @return  PointCalculateResult
	 */
	public PointCalculateResult issueTopicPointLimit(Integer userId);
	/**
	 * 发贴添加积分
	 * @param userId 用户ID
	 * @param channel 渠道
	 * @return PointCalculateResult 积分计算结果
	 */
	public PointCalculateResult issueTopicPoint(Integer userId,int channel);
	
	/**
	 * 回复添加积分
	 * @param userId 用户ID
	 * @return  PointCalculateResult
	 */
	public PointCalculateResult replyTopic(Integer userId);
	
	/**
	 * 回复积分上限
	 * @param userId 用户ID
	 * @return  PointCalculateResult
	 */
	public PointCalculateResult replyTopicLimit(Integer userId);
	
	/**
	 * 回复添加积分
	 * @param userId 用户ID
	 * @param channel 渠道
	 * @return PointCalculateResult 积分计算结果
	 */
	public PointCalculateResult replyTopic(Integer userId,int channel);
	
	/**
	 * 精华贴添加积分
	 * @param userId 用户ID
	 * @return PointCalculateResult 积分计算结果
	 */
	public PointCalculateResult  topicDisgest(Integer userId);
	
	/**
	 * 精华贴添加积分
	 * @param userId 用户ID
	 * @param channel 渠道
	 * @return PointCalculateResult 积分计算结果
	 */
	public PointCalculateResult  topicDisgest(Integer userId,int channel);
	
	/**
	 * 被顶贴添加积分
	 * @param userId 用户ID
	 * @return  PointCalculateResult
	 */
	public PointCalculateResult pushTopic(Integer userId);
	
	/**
	 * 被顶贴添加积分
	 * @param userId 用户ID
	 * @param channel 渠道
	 * @return PointCalculateResult 积分计算结果
	 */
	public PointCalculateResult pushTopic(Integer userId,int channel);
	
	/**
	 *置顶贴添加积分
	 * @param userId 用户ID
	 * @return  PointCalculateResult
	 */
	public PointCalculateResult topTopic(Integer userId);
	
	/**
	 * 置顶贴添加积分
	 * @param userId 用户ID
	 * @param channel 渠道
	 * @return PointCalculateResult 积分计算结果
	 */
	public PointCalculateResult topTopic(Integer userId,int channel);
	/**
	 * 问卷调查添加积分
	 * @param userId 用户ID
	 * @return  PointCalculateResult
	 */
	public PointCalculateResult questionaryPoint(Integer userId);
	
	/**
	 * 问卷调查积分上限
	 * @param userId 用户ID
	 * @return  PointCalculateResult
	 */
	public PointCalculateResult questionaryPointLimit(Integer userId);
	
	/**
	 * 问卷调查添加积分
	 * @param userId 用户ID
	 * @param channel 渠道
	 * @return PointCalculateResult 积分计算结果
	 */
	public PointCalculateResult questionaryPoint(Integer userId,int channel);
	
	
	/**
	 *游戏添加积分 默认为微信端
	 * @param userId 用户ID
	 * @return  PointCalculateResult
	 */
	public PointCalculateResult gamePoint(Integer userId);
	
	/**
	 * 游戏添加积分 默认为微信端
	 * @param userId 用户ID
	 * @param channel 渠道
	 * @return PointCalculateResult 积分计算结果
	 */
	public PointCalculateResult gamePoint(Integer userId,int channel);
	
	/**
	 *转发添加积分 默认为微信端
	 * @param userId 用户ID
	 * @return  PointCalculateResult
	 */
	public PointCalculateResult sharePoint(Integer userId);
	
	/**
	 * 转发添加积分 默认为微信端
	 * @param userId 用户ID
	 * @param channel 渠道
	 * @return PointCalculateResult 积分计算结果
	 */
	public PointCalculateResult sharePoint(Integer userId,int channel);
	
	/**
	 *转发添加积分 默认为微信端
	 * @param userId 用户ID
	 * @return  PointCalculateResult
	 */
	public PointCalculateResult prasiePoint(Integer userId);
	
	/**
	 * 转发添加积分 默认为微信端
	 * @param userId 用户ID
	 * @param channel 渠道
	 * @return PointCalculateResult 积分计算结果
	 */
	public PointCalculateResult prasiePoint(Integer userId,int channel);
	
	/***
	 * 检查用户是否达到上限
	 * @param pointRule 积分规则
	 * @param userId 用户ID 
	 * @return  true 是，false 否
	 */
	public boolean checkUpperLimitPoint(PointRule pointRule, Integer userId);
	
	
	
	
}
