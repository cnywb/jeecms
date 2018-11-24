/**
 * 
 */
package com.jeecms.point.dao.point.impl;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;
import com.jeecms.point.dao.point.PointPayoutDao;
import com.jeecms.point.entity.PointPayout;
import com.jeecms.point.vo.point.PointPayoutQueryVo;
import com.jeecms.point.web.query.QueryVo;

/**
 * @author wanglijun
 *
 */
@Repository
public class PointPayoutDaoImpl extends HibernateBaseDao<PointPayout,Long>  implements PointPayoutDao{

	@Override
	protected Class<PointPayout> getEntityClass() {
		return PointPayout.class;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.point.PointPayoutDao#save(com.jeecms.point.entity.PointPayout)
	 */
	@Override
	public PointPayout save(PointPayout pointPayout) {
		this.getSession().save(pointPayout);
		this.getSession().flush();
		return pointPayout;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.point.PointPayoutDao#queryPagination(com.jeecms.point.web.query.QueryVo, com.jeecms.point.vo.point.PointPayoutQueryVo)
	 */
	@Override
	public Pagination queryPagination(QueryVo queryVo,PointPayoutQueryVo pointPayoutQueryVo) {
		String hql="SELECT  new com.jeecms.point.entity.PointPayoutQuery(p.id, p.userId, p.pointNum, p.payoutDate, p.payoutType, p.pointRuleNo,p.pointRuleName,p.orderId, p.memo, p.channel, u.username)  from   PointPayout p ,UnifiedUser u   where  u.id=p.userId  ";
		Finder finder = Finder.create(hql);
		Long    orderId=pointPayoutQueryVo.getOrderId();
		String  username=pointPayoutQueryVo.getUsername();
		String  startDate=pointPayoutQueryVo.getStartDate();
		String  endDate=pointPayoutQueryVo.getEndDate();
		//添加规则代码
		if(orderId!=null){
			finder.append(" and p.pointRuleNo =:orderId");
			finder.setParam("orderId",orderId);
		}
		 
		//用户名
		if(StringUtils.isNotEmpty(username)){
			finder.append(" and u.username=:username ");
			finder.setParam("username",username);
		}
		//添加查询日期起期
		if(StringUtils.isNotEmpty(startDate)){
			Date  payoutStartDate=DateUtils.getStartDate(DateUtils.getDateByFormat(startDate, DateUtils.FORMAT_DATE_DEFAULT));
			finder.append(" and p.payoutDate>=:payoutStartDate ");
			finder.setParam("payoutStartDate",payoutStartDate);
		}
		//止期
		if(StringUtils.isNotEmpty(startDate)&&StringUtils.isNotEmpty(endDate)){
			Date  endDateTemp=DateUtils.getDateByFormat(endDate, DateUtils.FORMAT_DATE_DEFAULT);
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(endDateTemp);
			calendar.add(Calendar.DAY_OF_YEAR,1);
			Date  payoutEndDate=DateUtils.getStartDate(calendar.getTime());
			finder.append(" and p.payoutDate<:payoutEndDate ");
			finder.setParam("payoutEndDate",payoutEndDate);
		}
		finder.append(" order by p.payoutDate desc");
		Pagination pagination= this.find(finder, queryVo.getPage(), queryVo.getRows());
		return pagination;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.point.PointPayoutDao#queryPaginationForUser(com.jeecms.point.web.query.QueryVo, java.lang.Integer)
	 */
	@Override
	public Pagination queryPaginationForUser(QueryVo queryVo, Integer userId) {
		String hql="SELECT  p from   PointPayout p where ";
		Finder finder = Finder.create(hql); 
		finder.append(" and p.id=:userId ");
		finder.setParam("userId",userId);
		finder.append(" order by p.payoutDate  desc");
		Pagination pagination= this.find(finder, queryVo.getPage(), queryVo.getRows());
		return pagination;
	}

	
}
