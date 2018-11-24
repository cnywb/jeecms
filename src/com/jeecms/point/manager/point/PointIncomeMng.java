/**
 * 
 */
package com.jeecms.point.manager.point;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.entity.PointIncome;
import com.jeecms.point.entity.PointRule;
import com.jeecms.point.vo.point.AddPointNumVo;
import com.jeecms.point.vo.point.PointIncomeQueryVo;
import com.jeecms.point.web.query.QueryVo;


/**
 * @author wanglijun
 *
 */
public interface PointIncomeMng {
	/****
	 * 保存积分收入
	 * @param pointIncome
	 * @return
	 */
	public PointIncome save(PointIncome pointIncome);
	/***
	 * 查询分页
	 * @param queryVo  分页信息
	 * @param pointIncomeQueryVo 查询条件
	 * @return Pagination
	 */
	public Pagination queryPagination(QueryVo queryVo,PointIncomeQueryVo pointIncomeQueryVo);
	
	/***
	 * 查询分页
	 * @param queryVo  分页信息
	 * @param userId   用户ID
	 * @return Pagination 分页数据对象
	 */
	public Pagination queryPaginationForUser(QueryVo queryVo,Integer userId);
	
	/***
	 * 根据积分规则和用户ID，查检该积分是否已达积分上限
	 * @param pointRule
	 * @param userId
	 * @return
	 */
	public boolean checkUpperLimitPoint(PointRule  pointRule, Integer userId);
	
	/**
	 * 用户认证添加积分
	 * @param pointRuleNo 积分规则代码
	 * @param userId 用户ID
	 * @return boolean true 成功 false 失败
	 */
	public boolean authUserPoint(String pointRuleNo,Integer userId,Integer channel);
	
	/**
	 * 用户认证添加积分
	 * @param pointRuleNo 积分规则代码
	 * @param userId 用户ID
	 * @return boolean true 成功 false 失败
	 */
	public boolean authUserPoint(Integer userId);
	
	/**
	 * 发贴添加积分
	 * @param pointRuleNo 积分规则代码
	 * @param userId 用户ID
	 * @return  boolean true 成功 false 失败
	 */
	public boolean issueTopicPoint(String pointRuleNo,Integer userId,Integer channel);
	
	
	/**
	 * 发贴添加积分
	 * @param pointRuleNo 积分规则代码
	 * @param userId 用户ID
	 * @return  boolean true 成功 false 失败
	 */
	public boolean issueTopicPoint(Integer userId);
	
	
	/**
	 * 回复添加积分
	 * @param pointRuleNo 积分规则代码
	 * @param userId 用户ID
	 * @return  boolean
	 */
	public boolean rpelyTopic(String pointRuleNo,Integer userId,Integer channel);
	
	
	/**
	 * 回复添加积分
	 * @param pointRuleNo 积分规则代码
	 * @param userId 用户ID
	 * @return  boolean
	 */
	public boolean rpelyTopic(Integer userId);
	
	/**问卷调查添加积分
	 * @param pointRuleNo 积分规则代码
	 * @param userId 用户ID
	 * @return  boolean true 成功 false 失败
	 */
	public boolean auestionaryPoint(String pointRuleNo,Integer userId,Integer channel);
	
	/**问卷调查添加积分
	 * @param pointRuleNo 积分规则代码
	 * @param userId 用户ID
	 * @return  boolean true 成功 false 失败
	 */
	public boolean auestionaryPoint(Integer userId);
	
	 /***
	  * 完善信息添加积分
	  * @param pointRuleNo  积分规则代码
	  * @param userId 用户ID
	  * @param channel 渠道(微信、PC)
	  * @return boolean true 成功 false 失败
	  */
	public boolean perfactUserInfo(String pointRuleNo,Integer userId,Integer channel);
	
	

	 /***
	  * 完善信息添加积分
	  * @param userId 用户ID
	  * @param num 完善的项目，共计13项;130分；
	  * @return boolean true 成功 false 失败
	  */
	public boolean perfactUserInfo(Integer userId,int num);
	
	/***
	 * 微信点赞添加积分
	 * @param pointRuleNo  积分规则代码
	  * @param userId 用户ID
	  * @param channel 渠道(微信、PC)
	  * @return boolean true 成功 false 失败
	 */
	public boolean weixinPrasie(String pointRuleNo,Integer userId,Integer channel);
	
	/***
	 * 微信点赞添加积分
	 * @param pointRuleNo  积分规则代码
	  * @param userId 用户ID
	  * @param channel 渠道(微信、PC)
	  * @return boolean true 成功 false 失败
	 */
	public boolean weixinPrasie(Integer userId);
	
	
	/***
	 * 精华贴子手动加精
	 * @param pointRuleNo  积分规则代码
	  * @param userId 用户ID
	  * @param channel 渠道(微信、PC)
	  * @return boolean true 成功 false 失败
	 */
	public boolean  topicDisgest(String pointRuleNo,Integer userId,Integer channel);
	

	/***
	 * 精华贴子手动加精
	 * @param pointRuleNo  积分规则代码
	  * @param userId 用户ID
	  * @param channel 渠道(微信、PC)
	  * @return boolean true 成功 false 失败
	 */
	public boolean  topicDisgest(Integer userId);
	/***
	 * 精华帖子手动加分
	 * @param pointRuleNo
	 * @param userId
	 * @param channel
	 * @param addPointNumVo
	 * @return
	 */
	public boolean  topicDisgest(String pointRuleNo,Integer userId,Integer channel,AddPointNumVo addPointNumVo);
	
	
	
	/***
	 * 微信分享
	 * @param pointRuleNo  积分规则代码
	  * @param userId 用户ID
	  * @param channel 渠道(微信、PC)
	  * @return boolean true 成功 false 失败
	 */
	public boolean weiXinShare(String pointRuleNo,Integer userId,Integer channel);
	
	/***
	 * 微信分享
	 * @param pointRuleNo  积分规则代码
	  * @param userId 用户ID
	  * @param channel 渠道(微信、PC)
	  * @return boolean true 成功 false 失败
	 */
	public boolean weiXinShare(Integer userId);
	
	/***
	 * 手动调整积分
	 * @param pointRuleNo  积分规则代码
	  * @param userId 用户ID
	  * @param channel 渠道(微信、PC)
	  * @return boolean true 成功 false 失败
	 */
	public boolean  pointManual(String pointRuleNo,Integer userId,Integer channel);
	
	/***
	 * 手动调整积分
	 * @param pointRuleNo  积分规则代码
	  * @param userId 用户ID
	  * @param channel 渠道(微信、PC)
	  * @return boolean true 成功 false 失败
	 */
	public boolean  pointManual(String pointRuleNo,Integer userId,Integer channel,AddPointNumVo addPointNumVo);
	
	/***
	 * 抽奖积分
	 * @param pointRuleNo  积分规则代码
	  * @param userId 用户ID
	  * @param channel 渠道(微信、PC)
	  * @return boolean true 成功 false 失败
	 */
	public boolean lotteryPoint(String pointRuleNo,Integer userId,Integer channel);
	/***
	 * 查询
	 * @param queryVo
	 * @param pointIncomeQueryVo
	 * @return
	 */
	public Pagination queryPaginationByUserId(QueryVo queryVo,PointIncomeQueryVo pointIncomeQueryVo);
}
