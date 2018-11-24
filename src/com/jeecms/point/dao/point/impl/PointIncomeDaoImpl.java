/**
 * 
 */
package com.jeecms.point.dao.point.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;
import com.jeecms.point.constant.RecordType;
import com.jeecms.point.dao.point.PointIncomeDao;
import com.jeecms.point.entity.PointIncome;
import com.jeecms.point.entity.PointIncomeCount;
import com.jeecms.point.vo.point.PointIncomeQueryVo;
import com.jeecms.point.web.query.QueryVo;

/**
 * @author wanglijun
 *
 */
@Repository
public class PointIncomeDaoImpl extends HibernateBaseDao<PointIncome, Long> implements PointIncomeDao {

	@Override
	protected Class<PointIncome> getEntityClass() {
		return PointIncome.class;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.point.PointIncomeDao#save(com.jeecms.point.entity.PointIncome)
	 */
	@Override
	public PointIncome save(PointIncome pointIncome) {
		this.getSession().save(pointIncome);
		return pointIncome;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.point.PointIncomeDao#findByPointRuleNo(java.lang.String, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PointIncome findByPointRuleNo(String pointRuleNo, Long userId) {
		String hql="from PointIncome where recordType=:recordType and  pointRuleNo=:pointRuleNo and userId=:userId";
		Finder finder=Finder.create(hql);
		finder.setParam("recordType",RecordType.INCOME);
		finder.setParam("pointRuleNo",pointRuleNo);
		finder.setParam("userId", userId);
		List<PointIncome> list=this.find(finder);
		if(!CollectionUtils.isEmpty(list)){
			return list.get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.point.PointIncomeDao#findByPointRuleNo(java.lang.String, java.lang.Long, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PointIncome> findByPointRuleNo(String pointRuleNo, Long userId,Date incomeDate) {
		Calendar endCalendar=Calendar.getInstance();
		endCalendar.setTime(incomeDate);
		endCalendar.add(Calendar.DAY_OF_YEAR,1);
		Date endDate=endCalendar.getTime();
		String hql="from PointIncome where  recordType=:recordType and   pointRuleNo=:pointRuleNo and userId=:userId and incomeDate>=:incomeDate and incomeDate<:endDate";
		Finder finder=Finder.create(hql);
		finder.setParam("recordType",RecordType.INCOME);
		finder.setParam("pointRuleNo", pointRuleNo);
		finder.setParam("userId", userId);
		finder.setParam("incomeDate", incomeDate);
		finder.setParam("endDate", endDate);
		return this.find(finder);
	}
	
	

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.point.PointIncomeDao#findByPointRuleNo(java.lang.String, java.lang.Long, java.util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PointIncomeCount findByPointRuleNo(String pointRuleNo, Long userId,Date incomeStartDate, Date incomeEndDate) {
		String hql="select new com.jeecms.point.entity.PointIncomeCount(count(id) as totalCount,sum(pointNum)) from PointIncome where "
				+ "  recordType=:recordType and  pointRuleNo=:pointRuleNo and userId=:userId and incomeDate>=:incomeStartDate and incomeDate<:incomeEndDate";
		Finder finder=Finder.create(hql);
		finder.setParam("recordType",RecordType.INCOME);
		finder.setParam("pointRuleNo", pointRuleNo);
		finder.setParam("userId", userId);
		finder.setParam("incomeStartDate", incomeStartDate);
		finder.setParam("incomeEndDate", incomeEndDate);
		List<PointIncomeCount> list=this.find(finder);
		if(!CollectionUtils.isEmpty(list)){
			return list.get(0);
		}
		return  new PointIncomeCount();
	}
	
	

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.point.PointIncomeDao#findCountByPointRuleNo(java.lang.String, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PointIncomeCount findCountByPointRuleNo(String pointRuleNo,
			Long userId) {
		String hql="select new com.jeecms.point.entity.PointIncomeCount(count(id) as totalCount,sum(pointNum)as totalPointNum) from PointIncome  "
				+ " where  recordType=:recordType and   pointRuleNo=:pointRuleNo and userId=:userId ";
		Finder finder=Finder.create(hql);
		finder.setParam("recordType",RecordType.INCOME);
		finder.setParam("pointRuleNo", pointRuleNo);
		finder.setParam("userId", userId);	 
		List<PointIncomeCount> list=this.find(finder);
		if(!CollectionUtils.isEmpty(list)){
			return list.get(0);
		}
		return  new PointIncomeCount();
	}
	
	

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.point.PointIncomeDao#queryPaginationForUser(com.jeecms.point.web.query.QueryVo, java.lang.Integer)
	 */
	@Override
	public Pagination queryPaginationForUser(QueryVo queryVo, Integer userId) {
		String hql="SELECT  p from   PointIncome p ,UnifiedUser u   where recordType=:recordType and  u.id=p.userId  ";
		Finder finder = Finder.create(hql); 
		finder.append(" and u.id=:userId ");
		finder.setParam("recordType",RecordType.INCOME);
		finder.setParam("userId",userId);
		finder.append(" order by p.incomeDate  desc");
		Pagination pagination= this.find(finder, queryVo.getPage(), queryVo.getRows());
		return pagination;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.point.PointIncomeDao#queryPagination(com.jeecms.point.web.query.QueryVo, com.jeecms.point.action.point.PointIncomeQueryVo)
	 */
	@Override
	public Pagination queryPagination(QueryVo queryVo,PointIncomeQueryVo pointIncomeQueryVo) {
		String hql="SELECT  new com.jeecms.point.entity.PointIncomQuery(p.id, p.userId, p.pointNum,p.incomeDate,p.expiryDate, p.pointRuleNo, p.pointRuleName,p.pointRuleId,  p.memo, "
				+ "p.channel, u.username, p.orderId,  p.recordType, p.businessId,p.pointType) "
				+ " from   PointIncome p ,UnifiedUser u   "
				+ " where   u.id=p.userId ";
		Finder finder = Finder.create(hql);
		String  pointRuleNo=pointIncomeQueryVo.getPointRuleNo();
		String  pointRuleName=pointIncomeQueryVo.getPointRuleName();
		Integer pointType=pointIncomeQueryVo.getPointType();
		String  username=pointIncomeQueryVo.getUsername();
		String  startDate=pointIncomeQueryVo.getStartDate();
		String  endDate=pointIncomeQueryVo.getEndDate();
		Long    orderId=pointIncomeQueryVo.getOrderId();
		Integer recordType=pointIncomeQueryVo.getRecordType();
		//类型
		if(recordType!=null){
			finder.append(" and p.recordType=:recordType ");
			finder.setParam("recordType",recordType);
		}
		//订单号
		if(orderId!=null){
			finder.append(" and p.orderId=:orderId ");
			finder.setParam("orderId",orderId);
		}
		//添加规则代码
		if(StringUtils.isNotEmpty(pointRuleNo)){
			finder.append(" and p.pointRuleNo like :pointRuleNo ");
			finder.setParam("pointRuleNo","%"+pointRuleNo+"%");
		}
		//添加规则名称
		if(StringUtils.isNotEmpty(pointRuleName)){
			finder.append(" and p.pointRuleName like :pointRuleName ");
			finder.setParam("pointRuleName","%"+pointRuleName+"%");
		}
		//积分类型
		if(pointType!=null&&pointType>=0){
			finder.append(" and p.pointType=:pointType ");
			finder.setParam("pointType",pointType);
		}
		//用户名
		if(StringUtils.isNotEmpty(username)){
			finder.append(" and u.username=:username ");
			finder.setParam("username",username);
		}
		//添加查询日期起期
		if(StringUtils.isNotEmpty(startDate)){
			Date  incomeStartDate=DateUtils.getStartDate(DateUtils.getDateByFormat(startDate, DateUtils.FORMAT_DATE_DEFAULT));
			finder.append(" and p.incomeDate>=:incomeStartDate ");
			finder.setParam("incomeStartDate",incomeStartDate);
		}
		//止期
		if(StringUtils.isNotEmpty(startDate)&&StringUtils.isNotEmpty(endDate)){
			Date  endDateTemp=DateUtils.getDateByFormat(endDate, DateUtils.FORMAT_DATE_DEFAULT);
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(endDateTemp);
			calendar.add(Calendar.DAY_OF_YEAR,1);
			Date  incomeEndDate=DateUtils.getStartDate(calendar.getTime());
			finder.append(" and p.incomeDate<:incomeEndDate ");
			finder.setParam("incomeEndDate",incomeEndDate);
		}
		
		finder.append(" order by p.incomeDate  desc");
		Pagination pagination= this.find(finder, queryVo.getPage(), queryVo.getRows());
		return pagination;
	}
	
	

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.point.PointIncomeDao#paginationPayOut(com.jeecms.point.web.query.QueryVo, com.jeecms.point.vo.point.PointIncomeQueryVo)
	 */
	@Override
	public Pagination paginationPayOut(QueryVo queryVo,
			PointIncomeQueryVo pointIncomeQueryVo) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.point.PointIncomeDao#queryPaginationByUserId(com.jeecms.point.web.query.QueryVo, com.jeecms.point.vo.point.PointIncomeQueryVo)
	 */
	@Override
	public Pagination queryPaginationByUserId(QueryVo queryVo,PointIncomeQueryVo pointIncomeQueryVo) {
		String hql="SELECT  new com.jeecms.point.entity.PointIncomQuery(p.id, p.userId, p.pointNum,p.incomeDate,p.expiryDate, p.pointRuleNo, p.pointRuleName,p.pointRuleId,  p.memo, p.channel, p.recordType) "
				+ " from   PointIncome p where ";
		Finder finder = Finder.create(hql);
 
		Long   userId=pointIncomeQueryVo.getUserId();
		String  startDate=pointIncomeQueryVo.getStartDate();
		String  endDate=pointIncomeQueryVo.getEndDate();
		 
		//用户名
	 
		finder.append(" p.userId=:userId ");
		finder.setParam("userId", userId);
	 
		//添加查询日期起期
		if(StringUtils.isNotEmpty(startDate)){
			Date  incomeStartDate=DateUtils.getStartDate(DateUtils.getDateByFormat(startDate, DateUtils.FORMAT_DATE_DEFAULT));
			finder.append(" and p.incomeDate>=:incomeStartDate ");
			finder.setParam("incomeStartDate",incomeStartDate);
		}
		//止期
		if(StringUtils.isNotEmpty(startDate)&&StringUtils.isNotEmpty(endDate)){
			Date  endDateTemp=DateUtils.getDateByFormat(endDate, DateUtils.FORMAT_DATE_DEFAULT);
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(endDateTemp);
			calendar.add(Calendar.DAY_OF_YEAR,1);
			Date  incomeEndDate=DateUtils.getStartDate(calendar.getTime());
			finder.append(" and p.incomeDate<:incomeEndDate ");
			finder.setParam("incomeEndDate",incomeEndDate);
		}
		finder.append(" order by p.incomeDate  desc");
		Pagination pagination= this.find(finder, queryVo.getPage(), queryVo.getRows());
		return pagination;
	}
}
