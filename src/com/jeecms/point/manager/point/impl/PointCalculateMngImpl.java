/**
 * 
 */
package com.jeecms.point.manager.point.impl;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeecms.common.util.DateUtils;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.point.constant.Channel;
import com.jeecms.point.constant.ExpiryDateType;
import com.jeecms.point.constant.RecordType;
import com.jeecms.point.constant.RuleConstant;
import com.jeecms.point.constant.RuleCycle;
import com.jeecms.point.dao.point.PointIncomeDao;
import com.jeecms.point.entity.PointCalculateResult;
import com.jeecms.point.entity.PointIncome;
import com.jeecms.point.entity.PointIncomeCount;
import com.jeecms.point.entity.PointRule;
import com.jeecms.point.manager.point.PointCalculateMng;
import com.jeecms.point.manager.point.PointRuleMng;
/**
 * 服务
 * @author wanglijun
 *
 */
@Service
public class PointCalculateMngImpl  implements  PointCalculateMng{
	
	private static final Logger logger = LoggerFactory
			.getLogger(PointCalculateMngImpl.class);
	/**用户信息完善最大13项*/
	public static final int USER_INFO_MAX_NUM=13;
	
	@Autowired
	private PointIncomeDao pointIncomeDao;
	
	@Autowired
	private PointRuleMng pointRuleMng;
	
	@Autowired
	private UnifiedUserMng unifiedUserMng;
	
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
    
	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#checkUpperLimitPoint(com.jeecms.point.entity.PointRule, java.lang.Integer)
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

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#regUserPoint(java.lang.Integer)
	 */
	@Override
	public PointCalculateResult regUserPoint(Integer userId) {
		return this.regUserPoint(userId, Channel.PC);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#regUserPoint(java.lang.Integer, int)
	 */
	@Override
	public PointCalculateResult regUserPoint(Integer userId, int channel) {
		return this.calculate(userId, channel, RuleConstant.REG_USER);	
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#authUserPoint(java.lang.Integer)
	 */
	@Override
	public PointCalculateResult authUserPoint(Integer userId) {
		return this.regUserPoint(userId, Channel.PC);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#authUserPoint(java.lang.Integer, int)
	 */
	@Override
	public PointCalculateResult authUserPoint(Integer userId, int channel) {
		 return this.calculate(userId, channel,RuleConstant.AUTH_USER);
	}
	
	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#singinUserPoint(java.lang.Integer)
	 */
	@Override
	public PointCalculateResult singinUserPoint(Integer userId) {		 
		return this.singinUserPoint(userId,Channel.PC);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#singinUserPoint(java.lang.Integer, int)
	 */
	@Override
	public PointCalculateResult singinUserPoint(Integer userId, int channel) {
		return this.calculate(userId, channel,RuleConstant.SINGIN_USER);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#perfactUserInfo(java.lang.Integer)
	 */
	@Override
	public PointCalculateResult perfactUserInfo(Integer userId,int num) {	 
		return this.perfactUserInfo(userId, Channel.PC,num);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#perfactUserInfo(java.lang.Integer, int)
	 */
	@Override
	public PointCalculateResult perfactUserInfo(Integer userId, int channel,int num) {
		//最大小
	
		PointRule pointRule =pointRuleMng.findByPointRuleNo(RuleConstant.PERFACT_USER_INFO);
		PointCalculateResult  result=this.createPointCalculateResult(pointRule);
		// 检查积分是否达累计上限值
	  
		Long createUserId = new Long(userId);
		PointIncomeCount incomeCount = this.pointIncomeDao.findCountByPointRuleNo(RuleConstant.PERFACT_USER_INFO,createUserId);
		//判断积分是否大于最大积分
		if(incomeCount.getTotalPointNum()!=null&&incomeCount.getTotalPointNum()>=pointRule.getMaxPointNum()){
			result.setResult(false);
			return result;
		}
		//每项的积分
		long itemPoint=pointRule.getPointNum()/USER_INFO_MAX_NUM;
		//本次积分
		long tempPoint=itemPoint*num;
		if(incomeCount.getTotalPointNum()!=null&&tempPoint<=incomeCount.getTotalPointNum()){
			result.setResult(true);
			return result;
		}	
		//计算本次应该加的积分
		long currentPoint=incomeCount.getTotalPointNum()==null?tempPoint:tempPoint-incomeCount.getTotalPointNum();
		//将积分设置
		result.setLastNum(currentPoint);
		result.setResult(true);		
		PointIncome pointIncome = this.savePoint(pointRule, userId, channel,currentPoint);
		logger.info("pointIncome:" + pointIncome.getId());
		return result;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#issueTopicPoint(java.lang.Integer)
	 */
	@Override
	public PointCalculateResult issueTopicPoint(Integer userId) {
		return this.issueTopicPoint(userId, Channel.PC);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#issueTopicPoint(java.lang.Integer, int)
	 */
	@Override
	public PointCalculateResult issueTopicPoint(Integer userId, int channel) {
		return this.calculate(userId, channel,RuleConstant.ISSUE_TOPIC);
	}
	
	

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#issueTopicPointLimit(java.lang.Integer)
	 */
	@Override
	public PointCalculateResult issueTopicPointLimit(Integer userId) {
		PointRule pointRule =pointRuleMng.findByPointRuleNo(RuleConstant.ISSUE_TOPIC);
		PointCalculateResult  result=this.createPointCalculateResult(pointRule);
		// 检查积分是否达累计上限值
		boolean checkResult = this.checkUpperLimitPoint(pointRule, userId);
		// 检查通过则保存数据
		if (!checkResult) {
			return result;
		}
		result.setMaxNum(pointRule.getMaxPointNum());
		result.setNum(pointRule.getPointNum());
		result.setLastNum(pointRule.getPointNum());
		result.setResult(true);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#rpelyTopic(java.lang.Integer)
	 */
	@Override
	public PointCalculateResult replyTopic(Integer userId) {
		 
		return this.replyTopic(userId,Channel.PC);
	}
	
	

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#replyTopicLimit(java.lang.Integer)
	 */
	@Override
	public PointCalculateResult replyTopicLimit(Integer userId) {
		PointRule pointRule =pointRuleMng.findByPointRuleNo(RuleConstant.RPELY_TOPIC);
		PointCalculateResult  result=this.createPointCalculateResult(pointRule);
		// 检查积分是否达累计上限值
		boolean checkResult = this.checkUpperLimitPoint(pointRule, userId);
		// 检查通过则保存数据
		if (!checkResult) {
			result.setResult(false);
			return result;
		}
		result.setMaxNum(pointRule.getMaxPointNum());
		result.setNum(pointRule.getPointNum());
		result.setLastNum(pointRule.getPointNum());
		result.setResult(true);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#rpelyTopic(java.lang.Integer, int)
	 */
	@Override
	public PointCalculateResult replyTopic(Integer userId, int channel) {
		return this.calculate(userId, channel,RuleConstant.RPELY_TOPIC);
	}
	
	

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#auestionaryPoint(java.lang.Integer)
	 */
	@Override
	public PointCalculateResult questionaryPoint(Integer userId) {
		return this.questionaryPoint(userId, Channel.PC);				
	}
	
	
	

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#auestionaryPointLimit(java.lang.Integer)
	 */
	@Override
	public PointCalculateResult questionaryPointLimit(Integer userId) {
		PointRule pointRule =pointRuleMng.findByPointRuleNo(RuleConstant.QUESTIONARY);
		PointCalculateResult  result=this.createPointCalculateResult(pointRule);
		// 检查积分是否达累计上限值
		boolean checkResult = this.checkUpperLimitPoint(pointRule, userId);
		// 检查通过则保存数据
		if (!checkResult) {
			return result;
		}
		result.setMaxNum(pointRule.getMaxPointNum());
		result.setNum(pointRule.getPointNum());
		result.setLastNum(pointRule.getPointNum());
		result.setResult(true);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#auestionaryPoint(java.lang.Integer, int)
	 */
	@Override
	public PointCalculateResult questionaryPoint(Integer userId, int channel) {
		return  this.calculate(userId, channel,RuleConstant.QUESTIONARY);
	}
	
	

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#topicDisgest(java.lang.Integer)
	 */
	@Override
	public PointCalculateResult topicDisgest(Integer userId) {
		return this.topicDisgest(userId, Channel.PC);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#topicDisgest(java.lang.Integer, int)
	 */
	@Override
	public PointCalculateResult topicDisgest(Integer userId, int channel) {
		return  this.calculate(userId, channel,RuleConstant.TOPIC_DISGEST);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#pushTopic(java.lang.Integer)
	 */
	@Override
	public PointCalculateResult pushTopic(Integer userId) {
		return this.pushTopic(userId, Channel.PC);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#pushTopic(java.lang.Integer, int)
	 */
	@Override
	public PointCalculateResult pushTopic(Integer userId, int channel) {
		return  this.calculate(userId, channel,RuleConstant.PUSH_TOPIC);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#topTopic(java.lang.Integer)
	 */
	@Override
	public PointCalculateResult topTopic(Integer userId) {
		return this.pushTopic(userId, Channel.PC);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#topTopic(java.lang.Integer, int)
	 */
	@Override
	public PointCalculateResult topTopic(Integer userId, int channel) {
		return this.calculate(userId, channel,RuleConstant.TOP_TOPIC);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#gamePoint(java.lang.Integer)
	 */
	@Override
	public PointCalculateResult gamePoint(Integer userId) {
		return this.pushTopic(userId, Channel.PC);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#gamePoint(java.lang.Integer, int)
	 */
	@Override
	public PointCalculateResult gamePoint(Integer userId, int channel) {
		return this.calculate(userId, channel,RuleConstant.WEIXIN_GAME);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#sharePoint(java.lang.Integer)
	 */
	@Override
	public PointCalculateResult sharePoint(Integer userId) {
		return this.sharePoint(userId, Channel.WEIXIN);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#sharePoint(java.lang.Integer, int)
	 */
	@Override
	public PointCalculateResult sharePoint(Integer userId, int channel) {
		return this.calculate(userId, channel,RuleConstant.WEIXIN_SHARE);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#prasiePoint(java.lang.Integer)
	 */
	@Override
	public PointCalculateResult prasiePoint(Integer userId) {
		return this.prasiePoint(userId, Channel.WEIXIN);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointCalculateMng#prasiePoint(java.lang.Integer, int)
	 */
	@Override
	public PointCalculateResult prasiePoint(Integer userId, int channel) {
		 
		return this.calculate(userId, channel,RuleConstant.WEIXIN_PRASIE);
	}

	/***
	 * 
	 * @param userId 用户ID
	 * @param channel 渠道
	 * @param ruleNo  积分代码
	 */
	private PointCalculateResult calculate(Integer userId, int channel,String ruleNo){
		PointRule pointRule =pointRuleMng.findByPointRuleNo(ruleNo);
		PointCalculateResult  result=this.createPointCalculateResult(pointRule);
		// 检查积分是否达累计上限值
		boolean checkResult = this.checkUpperLimitPoint(pointRule, userId);
		// 检查通过则保存数据
		if (!checkResult) {
			return result;
		}
		result.setResult(true);	
		result.setLastNum(pointRule.getPointNum());
		result.setMaxNum(pointRule.getMaxPointNum());
		PointIncome pointIncome = this.savePoint(pointRule, userId, channel);
		logger.info("pointIncome:" + pointIncome.getId());
		return result;
	}
	
	
	
	
	/***
	 * 根据积分规则创建
	 * @param rule 积分规则
	 * @return   PointCalculateResult
	 */
	private PointCalculateResult createPointCalculateResult(PointRule rule){
		if(rule==null){
			return new PointCalculateResult();
		}
		return new PointCalculateResult(false,rule.getPointRuleNo(),rule.getPointNum());
	}
	
	/***
	 * 
	 * @param pointIncome
	 * @return
	 */
	public PointIncome save(PointIncome pointIncome) {
		return pointIncomeDao.save(pointIncome);
	}
	
	/***
	 * 只在积分数据
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
		pointIncome.setRecordType(RecordType.INCOME);
		pointIncome = this.save(pointIncome);
		// 更新User对象的总积分
		this.unifiedUserMng.updatePoint(userId, pointRule.getPointNum());
		return pointIncome;
	}
	
	/***
	 * 只在积分数据
	 * @param pointRule
	 * @param userId
	 * @return
	 */
	private PointIncome savePoint(PointRule pointRule, Integer userId,Integer channel,Long pointNum) {
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
		pointIncome.setPointNum(pointNum);
		pointIncome.setPointRuleId(pointRule.getId());
		pointIncome.setPointRuleName(pointRule.getPointRuleName());
		pointIncome.setChannel(channel);
		// 积分有效日期
		pointIncome.setExpiryDate(this.calculatorExpiryDate(pointRule.getExpiryDateType(), createdDate));
		pointIncome.setPointType(pointRule.getPointType());
		pointIncome.setRecordType(RecordType.INCOME);
		pointIncome = this.save(pointIncome);
		// 更新User对象的总积分
		this.unifiedUserMng.updatePoint(userId, pointRule.getPointNum());
		return pointIncome;
	}
}
