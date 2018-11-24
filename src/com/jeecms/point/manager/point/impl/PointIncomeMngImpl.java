/**
 * 
 */
package com.jeecms.point.manager.point.impl;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.point.constant.Channel;
import com.jeecms.point.constant.ExpiryDateType;
import com.jeecms.point.constant.RuleConstant;
import com.jeecms.point.constant.RuleCycle;
import com.jeecms.point.dao.point.PointIncomeDao;
import com.jeecms.point.entity.PointIncome;
import com.jeecms.point.entity.PointIncomeCount;
import com.jeecms.point.entity.PointRule;
import com.jeecms.point.manager.point.PointIncomeMng;
import com.jeecms.point.manager.point.PointRuleMng;
import com.jeecms.point.vo.point.AddPointNumVo;
import com.jeecms.point.vo.point.PointIncomeQueryVo;
import com.jeecms.point.web.query.QueryVo;

/**
 * @author wanglijun
 * 
 */
@Service
@Transactional
public class PointIncomeMngImpl implements PointIncomeMng {

	private static final Logger logger = LoggerFactory
			.getLogger(PointIncomeMngImpl.class);

	@Autowired
	private PointIncomeDao pointIncomeDao;

	@Autowired
	private PointRuleMng pointRuleMng;

	@Autowired
	private UnifiedUserMng unifiedUserMng;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.point.manager.point.PointIncomeMng#save(com.jeecms.point.entity
	 * .PointIncome)
	 */
	@Override
	public PointIncome save(PointIncome pointIncome) {
		return pointIncomeDao.save(pointIncome);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.point.manager.point.PointIncomeMng#checkUpperLimitPoint(java
	 * .lang.String, java.lang.Integer)
	 */
	@Override
	public boolean checkUpperLimitPoint(PointRule pointRule, Integer userId) {
		boolean checkResult = true;
		// 判断是否为空
		if (pointRule == null || userId == null) {
			checkResult = false;
			return checkResult;
		}
		Long createUserId = new Long(userId);
		String pointRuleNo=pointRule.getPointRuleNo();
		Date startDate = new Date();
		Date incomeEndDate=this.calculatorEndDate(startDate);
		// 检查是否一次性积分规则的上限
		switch (pointRule.getCycle()) {
		// 一次
		case RuleCycle.TIME_ONE:
			PointIncome pointIncome = this.pointIncomeDao.findByPointRuleNo(pointRuleNo, createUserId);
			if (pointIncome != null) {
				checkResult = false;
			}
			break;
		case RuleCycle.TIME_DAY:
			// 检查一天多次积分规则的上限
			Date incomeStartDate =DateUtils.getStartDate(startDate);			
			PointIncomeCount  incomeCount=this.pointIncomeDao.findByPointRuleNo(pointRuleNo,createUserId,incomeStartDate,incomeEndDate);
			//判断总记录是否大小
			checkResult=this.checkCycleRule(pointRule, incomeCount);
			break;
		// 一周
		case RuleCycle.TIME_WEEK:			
			incomeStartDate =this.calculatorWeekStartDate(startDate);			
			incomeCount=this.pointIncomeDao.findByPointRuleNo(pointRuleNo,createUserId,incomeStartDate,incomeEndDate);
			//判断总记录是否大小
			checkResult=this.checkCycleRule(pointRule, incomeCount);
			break;
		// 一月
		case RuleCycle.TIME_MONTH:
			incomeStartDate =this.calculatorMonthStartDate(startDate);			
			incomeCount=this.pointIncomeDao.findByPointRuleNo(pointRuleNo,createUserId,incomeStartDate,incomeEndDate);
			//判断总记录是否大小
			checkResult=this.checkCycleRule(pointRule, incomeCount);
			break;
		// 季度
		case RuleCycle.TIME_QUARTER:
			incomeStartDate =this.calculatorQuarterStartDate(startDate);			
			incomeCount=this.pointIncomeDao.findByPointRuleNo(pointRuleNo,createUserId,incomeStartDate,incomeEndDate);
			//判断总记录是否大小
			checkResult=this.checkCycleRule(pointRule, incomeCount);
			break;
		// 年
		case RuleCycle.TIME_YEAR:
			incomeStartDate =this.calculatorYearStartDate(startDate);			
			incomeCount=this.pointIncomeDao.findByPointRuleNo(pointRuleNo,createUserId,incomeStartDate,incomeEndDate);
			//判断总记录是否大小
			checkResult=this.checkCycleRule(pointRule, incomeCount);
			break;
		//无限次数
		case RuleCycle.TIME_NO_LIMIT:
			incomeCount=this.pointIncomeDao.findCountByPointRuleNo(pointRuleNo,createUserId);
			//判断总记录是否大小
			checkResult=this.checkCycleRule(pointRule, incomeCount);
			break;
		default:
			pointIncome = this.pointIncomeDao.findByPointRuleNo(pointRule.getPointRuleNo(), createUserId);
			if (pointIncome != null) {
				checkResult = false;
			}
			break;
		}

		if (pointRule.getCycle() == 0) {
			PointIncome pointIncome = this.pointIncomeDao.findByPointRuleNo(pointRule.getPointRuleNo(), createUserId);
			if (pointIncome != null) {
				return false;
			}
		} else {

		}
		return checkResult;
	}
	/***
	 * 检查总次及总积分是否超过规则记录
	 * @param pointRule
	 * @param pointIncomeCount
	 * @return 
	 */
    public boolean checkCycleRule(PointRule pointRule,PointIncomeCount incomeCount){
    	if(incomeCount.getTotalCount()>=pointRule.getCycleTime()||(incomeCount.getTotalPointNum()!=null&&incomeCount.getTotalPointNum()>=pointRule.getMaxPointNum())){
			 return false;
		}
    	return true;
    }

	public static void main(String[] args) {
		Date startDate = new Date();
		//System.out.println(DateUtils.parseDate(calculatorWeekStartDate(startDate),DateUtils.FORMAT_DATE_TIME_DEFAULT));
		PointIncomeMngImpl  incomeMngImpl=new PointIncomeMngImpl();
		Date monthStartDate=incomeMngImpl.calculatorMonthStartDate(startDate);
		Date  quarterStartDate=incomeMngImpl.calculatorQuarterStartDate(startDate);
		Date  yearStartDate=incomeMngImpl.calculatorYearStartDate(startDate);
		System.out.println(DateUtils.parseDate(monthStartDate,DateUtils.FORMAT_DATE_TIME_DEFAULT));
		System.out.println(DateUtils.parseDate(quarterStartDate,DateUtils.FORMAT_DATE_TIME_DEFAULT));
		System.out.println(DateUtils.parseDate(yearStartDate,DateUtils.FORMAT_DATE_TIME_DEFAULT));
	}
	
	/***
	 * 计算当前日期周的第一天（以周一为每周第一天）
	 * @param date
	 * @return
	 */
	private  Date calculatorWeekStartDate(Date date) {
		Date startDate = DateUtils.getStartDate(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);		
		calendar.setFirstDayOfWeek(Calendar.MONDAY); /* 设置一周的第一天为星期一 */
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		return calendar.getTime();
	}
	/***
	 * 计算当前日期月的第一天（以周一为每周第一天）
	 * @param date
	 * @return
	 */
	private  Date calculatorMonthStartDate(Date date) {
		Date startDate = DateUtils.getStartDate(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		return calendar.getTime();
	}
	
	/***
	 * 计算当前日期季度的第一天（以周一为每周第一天）
	 * @param date
	 * @return
	 */
	private  Date calculatorQuarterStartDate(Date date) {
		Date startDate = DateUtils.getStartDate(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
        int month = getQuarterInMonth(calendar.get(Calendar.MONTH), true);  
        calendar.set(Calendar.MONTH, month);  
        calendar.set(Calendar.DAY_OF_MONTH, 1);  
		return calendar.getTime();
	}
	
	/***
	 * 计算当前日期年的第一天（以周一为每周第一天）
	 * @param date
	 * @return
	 */
	private  Date calculatorYearStartDate(Date date) {
		Date startDate = DateUtils.getStartDate(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
        calendar.set(Calendar.DAY_OF_YEAR, 1);  
		return calendar.getTime();
	}

	/***
	 * 计算当前EndDate日期，当前日期加一天；
	 * @param date 日期
	 * @return 当前最后
	 */
	private Date calculatorEndDate(Date date) {
		Date endDate = DateUtils.getStartDate(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate);
		calendar.add(Calendar.DAY_OF_YEAR,1);
		return calendar.getTime();
	}
	/***
	 * 返回第几个月份，不是几月  
     * 季度一年四季， 第一季度：2月-4月， 第二季度：5月-7月， 第三季度：8月-10月， 第四季度：11月-1月  
	 * @param month
	 * @param isQuarterStart
	 * @return
	 */
	 private static int getQuarterInMonth(int month, boolean isQuarterStart) {  
	        int months[] = {0, 3, 6,9};  
	        if (!isQuarterStart) {  
	            months = new int[] { 3, 6, 9, 12 };  
	        }  
	        if (month >= 1 && month <= 3)  
	            return months[0];  
	        else if (month >= 4 && month <=6)  
	            return months[1];  
	        else if (month >= 7 && month <=9)  
	            return months[2];  
	        else  
	            return months[3];  
	}  

	/***
	 * 计算积分有效日期
	 * 
	 * @param pointRule
	 *            积分规则
	 * @param createdDate
	 *            创建日期
	 * @return Date 有效日期
	 */
	private Date calculatorExpiryDate(int expiryDateType, Date createdDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(createdDate);
		switch (expiryDateType) {
		// 一月
		case ExpiryDateType.MONTH_ONE:
			calendar.add(Calendar.MONTH, 1);
			break;
		// 季度
		case ExpiryDateType.QUARTER:
			calendar.add(Calendar.MONTH, 3);
			break;
		case ExpiryDateType.HALF_YEAR:
			calendar.add(Calendar.MONTH, 6);
			break;
		case ExpiryDateType.YEAR_ONE:
			calendar.add(Calendar.YEAR, 1);
			break;
		case ExpiryDateType.YEAR_TWO:
			calendar.add(Calendar.YEAR, 2);
			break;
		case ExpiryDateType.YEAR_THREE:
			calendar.add(Calendar.YEAR, 3);
			break;
		case ExpiryDateType.YEAR_FIVE:
			calendar.add(Calendar.YEAR, 5);
			break;
		case ExpiryDateType.YEAR_TEN:
			calendar.add(Calendar.YEAR, 10);
			break;
		default:
			calendar.add(Calendar.YEAR, 1);
			break;
		}
		calendar.add(Calendar.DAY_OF_YEAR,1);		
		return DateUtils.getStartDate(calendar.getTime());
	}

	/***
	 * 只在积分数据
	 * 
	 * @param pointRule
	 * @param userId
	 * @return
	 */
	private PointIncome savePoint(PointRule pointRule, Integer userId,Integer channel) {
		Long createdId = new Long(userId);
		Date createdDate = new Date();
		PointIncome pointIncome = new PointIncome();
		pointIncome.setCreatedId(createdId);
		pointIncome.setCreatedDate(createdDate);
		pointIncome.setUpdatedId(createdId);
		pointIncome.setUpdatedDate(createdDate);
		pointIncome.setIncomeDate(createdDate);
		pointIncome.setUserId(createdId);
		pointIncome.setPointRuleNo(pointRule.getPointRuleNo());
		pointIncome.setPointNum(pointRule.getPointNum());
		pointIncome.setPointRuleId(pointRule.getId());
		pointIncome.setPointNum(pointRule.getPointNum());
		pointIncome.setPointRuleName(pointRule.getPointRuleName());
		pointIncome.setChannel(channel);
		// 积分有效日期
		pointIncome.setExpiryDate(this.calculatorExpiryDate(pointRule.getExpiryDateType(), createdDate));
		pointIncome.setPointType(pointRule.getPointType());
		pointIncome = this.save(pointIncome);
		// 更新User对象的总积分
		this.unifiedUserMng.updatePoint(userId, pointRule.getPointNum());
		return pointIncome;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.point.manager.point.PointIncomeMng#authUserPoint(java.lang
	 * .String, java.lang.Integer)
	 */
	@Override
	public boolean authUserPoint(String pointRuleNo, Integer userId,
			Integer channel) {
		// 判断积分规则或用户ID是否为空
		if (StringUtils.isEmpty(pointRuleNo)) {
			return false;
		}
		PointRule pointRule = this.pointRuleMng.findByPointRuleNo(pointRuleNo);
		// 检查积分是否达累计上限值
		boolean checkResult = this.checkUpperLimitPoint(pointRule, userId);
		// 检查通过则保存数据
		if (!checkResult) {
			return false;
		}
		PointIncome pointIncome = this.savePoint(pointRule, userId, channel);
		logger.info("pointIncome:" + pointIncome.getId());
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.point.manager.point.PointIncomeMng#issueTopicPoint(java.lang
	 * .String, java.lang.Integer)
	 */
	@Override
	public boolean issueTopicPoint(String pointRuleNo, Integer userId,
			Integer channel) {
		// 判断积分规则或用户ID是否为空
		if (StringUtils.isEmpty(pointRuleNo)) {
			return false;
		}
		PointRule pointRule = this.pointRuleMng.findByPointRuleNo(pointRuleNo);
		// 检查积分是否达累计上限值
		boolean checkResult = this.checkUpperLimitPoint(pointRule, userId);
		// 检查通过则保存数据
		if (!checkResult) {
			return false;
		}
		PointIncome pointIncome = this.savePoint(pointRule, userId, channel);
		logger.info("pointIncome:" + pointIncome.getId());
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.point.manager.point.PointIncomeMng#rpelyTopic(java.lang.String
	 * , java.lang.Integer)
	 */
	@Override
	public boolean rpelyTopic(String pointRuleNo, Integer userId,
			Integer channel) {
		// 判断积分规则或用户ID是否为空
		if (StringUtils.isEmpty(pointRuleNo)) {
			return false;
		}
		PointRule pointRule = this.pointRuleMng.findByPointRuleNo(pointRuleNo);
		// 检查积分是否达累计上限值
		boolean checkResult = this.checkUpperLimitPoint(pointRule, userId);
		// 检查通过则保存数据
		if (!checkResult) {
			return false;
		}
		PointIncome pointIncome = this.savePoint(pointRule, userId, channel);
		logger.info("pointIncome:" + pointIncome.getId());
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.point.manager.point.PointIncomeMng#auestionaryPoint(java.lang
	 * .String, java.lang.Integer)
	 */
	@Override
	public boolean auestionaryPoint(String pointRuleNo, Integer userId,
			Integer channel) {
		// 判断积分规则或用户ID是否为空
		if (StringUtils.isEmpty(pointRuleNo)) {
			return false;
		}
		PointRule pointRule = this.pointRuleMng.findByPointRuleNo(pointRuleNo);
		// 检查积分是否达累计上限值
		boolean checkResult = this.checkUpperLimitPoint(pointRule, userId);
		// 检查通过则保存数据
		if (!checkResult) {
			return false;
		}
		PointIncome pointIncome = this.savePoint(pointRule, userId, channel);
		logger.info("pointIncome:" + pointIncome.getId());
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.point.manager.point.PointIncomeMng#perfactUserInfo(java.lang
	 * .String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public boolean perfactUserInfo(String pointRuleNo, Integer userId,
			Integer channel) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.point.manager.point.PointIncomeMng#weixinPrasie(java.lang.
	 * String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public boolean weixinPrasie(String pointRuleNo, Integer userId,
			Integer channel) {
		// 判断积分规则或用户ID是否为空
		if (StringUtils.isEmpty(pointRuleNo)) {
			return false;
		}
		PointRule pointRule = this.pointRuleMng.findByPointRuleNo(pointRuleNo);
		// 检查积分是否达累计上限值
		boolean checkResult = this.checkUpperLimitPoint(pointRule, userId);
		// 检查通过则保存数据
		if (!checkResult) {
			return false;
		}
		PointIncome pointIncome = this.savePoint(pointRule, userId, channel);
		logger.info("pointIncome:" + pointIncome.getId());
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.point.manager.point.PointIncomeMng#topicDisgest(java.lang.
	 * String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public boolean topicDisgest(String pointRuleNo, Integer userId,
			Integer channel) {
		// 判断积分规则或用户ID是否为空
		if (StringUtils.isEmpty(pointRuleNo)) {
			return false;
		}
		PointRule pointRule = this.pointRuleMng.findByPointRuleNo(pointRuleNo);
		// 检查积分是否达累计上限值
		boolean checkResult = this.checkUpperLimitPoint(pointRule, userId);
		// 检查通过则保存数据
		if (!checkResult) {
			return false;
		}
		PointIncome pointIncome = this.savePoint(pointRule, userId, channel);
		logger.info("pointIncome:" + pointIncome.getId());
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.point.manager.point.PointIncomeMng#weiXinShare(java.lang.String
	 * , java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public boolean weiXinShare(String pointRuleNo, Integer userId,
			Integer channel) {
		// 判断积分规则或用户ID是否为空
		if (StringUtils.isEmpty(pointRuleNo)) {
			return false;
		}
		PointRule pointRule = this.pointRuleMng.findByPointRuleNo(pointRuleNo);
		// 检查积分是否达累计上限值
		boolean checkResult = this.checkUpperLimitPoint(pointRule, userId);
		// 检查通过则保存数据
		if (!checkResult) {
			return false;
		}
		PointIncome pointIncome = this.savePoint(pointRule, userId, channel);
		logger.info("pointIncome:" + pointIncome.getId());
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.point.manager.point.PointIncomeMng#pointManual(java.lang.String
	 * , java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public boolean pointManual(String pointRuleNo, Integer userId,
			Integer channel) {
		// 判断积分规则或用户ID是否为空
		if (StringUtils.isEmpty(pointRuleNo)) {
			return false;
		}
		PointRule pointRule = this.pointRuleMng.findByPointRuleNo(pointRuleNo);
		// 检查积分是否达累计上限值
		boolean checkResult = this.checkUpperLimitPoint(pointRule, userId);
		// 检查通过则保存数据
		if (!checkResult) {
			return false;
		}
		PointIncome pointIncome = this.savePoint(pointRule, userId, channel);
		logger.info("pointIncome:" + pointIncome.getId());
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.point.manager.point.PointIncomeMng#queryPaginationForUser(
	 * com.jeecms.point.web.query.QueryVo, java.lang.Integer)
	 */
	@Override
	public Pagination queryPaginationForUser(QueryVo queryVo, Integer userId) {
		 
		return this.pointIncomeDao.queryPaginationForUser(queryVo,userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.point.manager.point.PointIncomeMng#queryPagination(com.jeecms
	 * .point.web.query.QueryVo,
	 * com.jeecms.point.action.point.PointIncomeQueryVo)
	 */
	@Override
	public Pagination queryPagination(QueryVo queryVo,PointIncomeQueryVo pointIncomeQueryVo) {
		return this.pointIncomeDao.queryPagination(queryVo, pointIncomeQueryVo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.point.manager.point.PointIncomeMng#lotteryPoint(java.lang.
	 * String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public boolean lotteryPoint(String pointRuleNo, Integer userId,Integer channel) {
		// 判断积分规则或用户ID是否为空
		if (StringUtils.isEmpty(pointRuleNo)) {
			return false;
		}
		PointRule pointRule = this.pointRuleMng.findByPointRuleNo(pointRuleNo);
		// 检查积分是否达累计上限值
		boolean checkResult = this.checkUpperLimitPoint(pointRule, userId);
		// 检查通过则保存数据
		if (!checkResult) {
			return false;
		}
		PointIncome pointIncome = this.savePoint(pointRule, userId, channel);
		logger.info("pointIncome:" + pointIncome.getId());
		return true;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointIncomeMng#topicDisgest(java.lang.String, java.lang.Integer, java.lang.Integer, com.jeecms.point.vo.point.AddPointNumVo)
	 */
	@Override
	public boolean topicDisgest(String pointRuleNo, Integer userId,
			Integer channel, AddPointNumVo addPointNumVo) {
		return  this.addManualPoint(pointRuleNo, userId, channel, addPointNumVo);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointIncomeMng#pointManual(java.lang.String, java.lang.Integer, java.lang.Integer, com.jeecms.point.vo.point.AddPointNumVo)
	 */
	@Override
	public boolean pointManual(String pointRuleNo, Integer userId,Integer channel, AddPointNumVo addPointNumVo) {
		return this.addManualPoint(pointRuleNo, userId, channel, addPointNumVo);
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointIncomeMng#authUserPoint(java.lang.Integer)
	 */
	@Override
	public boolean authUserPoint(Integer userId) {
		return this.authUserPoint(RuleConstant.AUTH_USER, userId, Channel.PC);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointIncomeMng#issueTopicPoint(java.lang.Integer)
	 */
	@Override
	public boolean issueTopicPoint(Integer userId) {
		return this.issueTopicPoint(RuleConstant.ISSUE_TOPIC, userId, Channel.PC);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointIncomeMng#rpelyTopic(java.lang.Integer)
	 */
	@Override
	public boolean rpelyTopic(Integer userId) {
		return this.rpelyTopic(RuleConstant.RPELY_TOPIC, userId, Channel.PC);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointIncomeMng#auestionaryPoint(java.lang.Integer)
	 */
	@Override
	public boolean auestionaryPoint(Integer userId) {
		 return this.auestionaryPoint(RuleConstant.QUESTIONARY, userId, Channel.PC);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointIncomeMng#perfactUserInfo(java.lang.Integer)
	 */
	@Override
	public boolean perfactUserInfo(Integer userId,int num) {
	 
		return this.perfactUserInfo(RuleConstant.PERFACT_USER_INFO, userId, Channel.PC);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointIncomeMng#weixinPrasie(java.lang.Integer)
	 */
	@Override
	public boolean weixinPrasie(Integer userId) {
		return this.weiXinShare(RuleConstant.WEIXIN_SHARE, userId,Channel.PC);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointIncomeMng#topicDisgest(java.lang.Integer)
	 */
	@Override
	public boolean topicDisgest(Integer userId) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointIncomeMng#weiXinShare(java.lang.Integer)
	 */
	@Override
	public boolean weiXinShare(Integer userId) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean addManualPoint(String pointRuleNo, Integer userId,Integer channel, AddPointNumVo addPointNumVo){
		PointRule pointRule=pointRuleMng.findByPointRuleNo(pointRuleNo);
		if(pointRule==null)
			return false;
		Long createdId = new Long(userId);
		Date createdDate = new Date();
		PointIncome pointIncome = new PointIncome();
		pointIncome.setCreatedId(createdId);
		pointIncome.setCreatedDate(createdDate);
		pointIncome.setUpdatedId(createdId);
		pointIncome.setUpdatedDate(createdDate);
		pointIncome.setIncomeDate(createdDate);
		pointIncome.setUserId(createdId);
		pointIncome.setPointRuleNo(pointRule.getPointRuleNo());
		pointIncome.setPointRuleId(pointRule.getId());
		pointIncome.setPointNum(addPointNumVo.getPointNum());
		pointIncome.setPointRuleName(pointRule.getPointRuleName());
		pointIncome.setChannel(channel);
		pointIncome.setMemo(addPointNumVo.getMemo());
		// 积分有效日期
		pointIncome.setExpiryDate(this.calculatorExpiryDate(pointRule.getExpiryDateType(), createdDate));
		pointIncome.setPointType(pointRule.getPointType());
		pointIncome = this.save(pointIncome);
		// 更新User对象的总积分
		this.unifiedUserMng.updatePoint(userId, addPointNumVo.getPointNum());
		return true;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointIncomeMng#queryPaginationByUserId(com.jeecms.point.web.query.QueryVo, com.jeecms.point.vo.point.PointIncomeQueryVo)
	 */
	@Override
	public Pagination queryPaginationByUserId(QueryVo queryVo,
			PointIncomeQueryVo pointIncomeQueryVo) {
		return  this.pointIncomeDao.queryPaginationByUserId(queryVo,pointIncomeQueryVo);
	}

	
}
