/**
 * 
 */
package com.jeecms.point.dao.order.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;
import com.jeecms.point.dao.order.PointOrderDao;
import com.jeecms.point.entity.PointOrder;
import com.jeecms.point.vo.order.PointOrderVo;
import com.jeecms.point.web.query.QueryVo;

/**
 * @author wanglijun
 *
 */
public class PointOrderDaoImpl extends HibernateBaseDao<PointOrder,Long> implements PointOrderDao{

	@Override
	protected Class<PointOrder> getEntityClass() {
		return PointOrder.class;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.order.PointOrderDao#save(com.jeecms.point.entity.PointOrder)
	 */
	@Override
	public PointOrder save(PointOrder pointOrder) {
		this.getSession().save(pointOrder);
		//this.getSession().flush();
		return pointOrder;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.order.PointOrderDao#queryPaginationForUser(com.jeecms.point.web.query.QueryVo, java.lang.Integer)
	 */
	@Override
	public Pagination queryPaginationForUser(QueryVo queryVo, Integer userId) {
		String hql="SELECT  p from   PointOrder p    where ";
		Finder finder = Finder.create(hql); 
		finder.append(" and p.userId=:userId ");
		finder.setParam("userId",userId);
		finder.append(" order by p.payoutDate  desc");
		Pagination pagination= this.find(finder, queryVo.getPage(), queryVo.getRows());
		return pagination;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.order.PointOrderDao#queryPagination(com.jeecms.point.web.query.QueryVo, com.jeecms.point.vo.order.PointOrderVo)
	 */
	@Override
	public Pagination queryPagination(QueryVo queryVo, PointOrderVo pointOrderVo) {
		String hql="SELECT  new com.jeecms.point.entity.PointOrderQuery(p.id, p.userId, p.totalPointNum,"
				+ " p.orderDate, p.status, p.type, p.memo,p.num, p.payoutDate, ex.phoneNo, ex.mobilePhoneNo, "
				+ " ex.sendee, u.username,p.sendDate,p.channel)  "
				+ " from   PointOrder p ,UnifiedUser u ,Express ex  where  u.id=p.userId  ";
		Finder finder = Finder.create(hql);
		finder.append(" and ex.orderId=p.id  ");
		Long    orderId=pointOrderVo.getOrderId();
		Integer status=pointOrderVo.getStatus();
		Integer channel=pointOrderVo.getChannel();
		Integer type=pointOrderVo.getType();
		String  username=pointOrderVo.getUsername();
		String  startDate=pointOrderVo.getStartDate();
		String  endDate=pointOrderVo.getEndDate();
		//添加规则代码
		if(orderId!=null){
			finder.append(" and p.id =:orderId");
			finder.setParam("orderId",orderId);
		}
		//状态
		if(status!=null){
			finder.append(" and p.status =:status");
			finder.setParam("status",status);
		}
		//渠道
		if(channel!=null){
			finder.append(" and p.channel =:channel");
			finder.setParam("channel",channel);
		}
		//类型
		if(type!=null){
			finder.append(" and p.type =:type");
			finder.setParam("type",type);
		}
		//用户名
		if(StringUtils.isNotEmpty(username)){
			finder.append(" and u.username=:username ");
			finder.setParam("username",username);
		}
		//添加查询日期起期
		if(StringUtils.isNotEmpty(startDate)){
			Date  orderStartDate=DateUtils.getStartDate(DateUtils.getDateByFormat(startDate, DateUtils.FORMAT_DATE_DEFAULT));
			finder.append(" and p.orderDate>=:orderStartDate ");
			finder.setParam("orderStartDate",orderStartDate);
		}
		//止期
		if(StringUtils.isNotEmpty(startDate)&&StringUtils.isNotEmpty(endDate)){
			Date  endDateTemp=DateUtils.getDateByFormat(endDate, DateUtils.FORMAT_DATE_DEFAULT);
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(endDateTemp);
			calendar.add(Calendar.DAY_OF_YEAR,1);
			Date  orderEndDate=DateUtils.getStartDate(calendar.getTime());
			finder.append(" and p.orderDate<:orderEndDate ");
			finder.setParam("orderEndDate",orderEndDate);
		}
		finder.append(" order by p.createdDate desc");
		Pagination pagination= this.find(finder, queryVo.getPage(), queryVo.getRows());
		return pagination;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.order.PointOrderDao#findById(java.lang.Long)
	 */
	@Override
	public PointOrder findById(Long id) {
		return super.get(id);
	}

	@Override
	public PointOrder findByOrderKey(Long orderKey) {
		String hql = "FROM PointOrder p where (1=1) ";
		Finder finder = Finder.create(hql);
		finder.append("and p.orderKey=:orderKey ");
		finder.setParam("orderKey", orderKey);
		@SuppressWarnings("unchecked")
		List<PointOrder> retval = this.find(finder);
		if(retval!=null&&!retval.isEmpty()){
			return retval.get(0);
		}
		return null;
	}

	/* (non-Javadoc)
         * @see com.jeecms.point.dao.order.PointOrderDao#update(com.jeecms.point.entity.PointOrder)
         */
	@Override
	public PointOrder update(PointOrder pointOrder) {
		this.getSession().update(pointOrder);
		this.getSession().flush();
		return pointOrder;
	}
}
