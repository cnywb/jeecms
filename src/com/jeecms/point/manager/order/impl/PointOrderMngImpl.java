/**
 * 
 */
package com.jeecms.point.manager.order.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.jeecms.point.dao.product.PointProductDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.point.constant.OrderStatus;
import com.jeecms.point.constant.PayoutType;
import com.jeecms.point.constant.RecordType;
import com.jeecms.point.constant.RuleConstant;
import com.jeecms.point.dao.order.PointOrderDao;
import com.jeecms.point.entity.Express;
import com.jeecms.point.entity.PointIncome;
import com.jeecms.point.entity.PointOrder;
import com.jeecms.point.entity.PointOrderDetails;
import com.jeecms.point.entity.PointProduct;
import com.jeecms.point.manager.express.ExpressMng;
import com.jeecms.point.manager.order.PointOrderMng;
import com.jeecms.point.manager.point.PointIncomeMng;
import com.jeecms.point.manager.point.PointUserMng;
import com.jeecms.point.manager.product.PointProductMng;
import com.jeecms.point.vo.order.OrderExchengeVo;
import com.jeecms.point.vo.order.PointOrderVo;
import com.jeecms.point.web.query.QueryVo;

/**
 * @author wanglijun
 *
 */
@Service
@Transactional
public class PointOrderMngImpl implements PointOrderMng {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PointOrderDao pointOrderDao;
	@Autowired
	private ExpressMng expressMng;
	/**产品信息*/
	@Autowired
	private  PointProductMng pointProductMng;
	/**用户积分*/
	@Autowired
	private PointUserMng pointUserMng;
	/**用户积分消费*/
	@Autowired
	private PointIncomeMng pointIncomeMng;

	@Autowired
	private PointProductDao pointProductDao;

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.order.PointOrderMng#save(com.jeecms.point.entity.PointOrder)
	 */
	@Override
	@Transactional
	public PointOrder save(PointOrder pointOrder) {
	 
		return pointOrderDao.save(pointOrder);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.order.PointOrderMng#queryPaginationForUser(com.jeecms.point.web.query.QueryVo, java.lang.Integer)
	 */
	@Override
	public Pagination queryPaginationForUser(QueryVo queryVo, Integer userId) {		 
		return pointOrderDao.queryPaginationForUser(queryVo, userId);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.order.PointOrderMng#queryPagination(com.jeecms.point.web.query.QueryVo, com.jeecms.point.vo.order.PointOrderVo)
	 */
	@Override
	public Pagination queryPagination(QueryVo queryVo, PointOrderVo pointOrderVo) {
	 
		return pointOrderDao.queryPagination(queryVo, pointOrderVo);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.order.PointOrderMng#findById(java.lang.Long)
	 */
	@Override
	public PointOrder findById(Long id) {
		PointOrder pointOrder=pointOrderDao.findById(id);
		if(pointOrder==null){
			return null;
		}
		for(PointOrderDetails orderDetails:pointOrder.getPointOrderDetails()){			
			orderDetails.setPointProduct(pointProductMng.findById(orderDetails.getProductId()));
			pointOrder.addOrderDetails(orderDetails);
		}
		return pointOrder;
	}

	/**
	 * 根据订单唯一值查找
	 *
	 * @param orderKey
	 * @return
	 */
	@Override
	public PointOrder findByOrderKey(Long orderKey) {
		return this.pointOrderDao.findByOrderKey(orderKey);
	}

	/* (non-Javadoc)
         * @see com.jeecms.point.manager.order.PointOrderMng#updateSendInfo(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String)
         */
	@Override
	public PointOrder updateSendInfo(Long id, Long expressId, String expressNo,String expressCompany) {
		 //将订单状态修改发货状态
		PointOrder  pointOrder=this.findById(id);
		if(pointOrder==null){
			return null;
		}
		Date updateDate=new Date();
		pointOrder.setStatus(OrderStatus.SEND);
		pointOrder.setUpdatedDate(updateDate);
		pointOrder.setSendDate(updateDate);
		
		//更新快递信息
		Express express=expressMng.findById(expressId);
		if(express==null){
			return null;
		}
		express.setExpressCompany(expressCompany);
		express.setExpressNo(expressNo);
		express.setUpdatedDate(updateDate);
		expressMng.update(express);
		this.pointOrderDao.update(pointOrder);
		return pointOrder;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.order.PointOrderMng#updateStatus(java.lang.Long, java.lang.Integer)
	 */
	@Override
	public PointOrder updateStatus(Long id, Integer status) {
		PointOrder  pointOrder=this.findById(id);
		pointOrder.setStatus(status);
		pointOrder.setUpdatedDate(new Date());
		return this.pointOrderDao.update(pointOrder);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.order.PointOrderMng#saveOrderInfo(com.jeecms.point.vo.order.OrderExchengeVo, com.jeecms.core.entity.UnifiedUser, int)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public PointOrder saveOrderInfo(OrderExchengeVo exchengeVo,	UnifiedUser unifiedUser, int channel,int payoutType) {
		Long userId=new Long(unifiedUser.getId());
		PointProduct pointProduct=pointProductMng.findById(exchengeVo.getProductId());
		//判断兑换库存是否大于 0
		if(pointProduct.getExchangeStockNum()<=0){
			return null;
		}
		long pointNum=pointProduct.getPointNum();
		Date createdDate=new Date();
		PointOrder pointOrder=new PointOrder();
		pointOrder.setNum(1L);
		pointOrder.setTotalPointNum(pointNum);
		pointOrder.setMemo(exchengeVo.getRuleName()+pointProduct.getTitle());
		pointOrder.setPayoutDate(createdDate);
		pointOrder.setChannel(channel);
		pointOrder.setOrderDate(createdDate);
		pointOrder.setUserId(userId);		 
		pointOrder.setCreatedId(userId);
		pointOrder.setCreatedDate(createdDate);
		pointOrder.setUpdatedId(userId);
		pointOrder.setUpdatedDate(createdDate);
		pointOrder.setType(payoutType);
		pointOrder.setStatus(OrderStatus.CREATED);
		
		PointOrderDetails orderDetails=new PointOrderDetails();
		//保存订单的快照
		BeanUtils.copyProperties(pointProduct,orderDetails);
		orderDetails.setCreatedId(userId);
		orderDetails.setCreatedDate(createdDate);
		orderDetails.setUpdatedId(userId);
		orderDetails.setUpdatedDate(createdDate);
		orderDetails.setUserId(userId);
		orderDetails.setPayoutDate(createdDate);
		orderDetails.setPointOrder(pointOrder);
		orderDetails.setPointNum(pointNum);
		orderDetails.setProductId(pointProduct.getId());
		orderDetails.setId(null);
		
		Set<PointOrderDetails> details=new HashSet<PointOrderDetails>();
		//orderDetails.setPointOrder(pointOrder);
		details.add(orderDetails);
		pointOrder.setPointOrderDetails(details);		
		//保存数据
		pointOrder=this.save(pointOrder);
		//保存用户的快递信息
		Express express=new Express();
		BeanUtils.copyProperties(exchengeVo, express);
		express.setDisrict(exchengeVo.getCounty());
		express.setCreatedId(userId);
		express.setCreatedDate(createdDate);
		express.setUpdatedId(userId);
		express.setUpdatedDate(createdDate);
		express.setUserId(userId);		
		express.setOrderId(pointOrder.getId());
		//快递信息
		expressMng.save(express);
		//扣减积分
		pointUserMng.updateUserPoint(unifiedUser.getId(
				),pointNum);
		//减库存
		pointProductMng.exchangeProductsDecrease(exchengeVo.getProductId());
		//添加消费记录
		PointIncome pointPayout=new PointIncome();
		pointPayout.setPointNum(-pointNum);
		pointPayout.setPointType(payoutType);
		pointPayout.setPointRuleName(pointOrder.getMemo());
		pointPayout.setChannel(channel);
		pointPayout.setIncomeDate(createdDate);
		pointPayout.setPointRuleNo(RuleConstant.EXCHANGE_PAY_POINT);
		pointPayout.setCreatedId(userId);
		pointPayout.setCreatedDate(createdDate);
		pointPayout.setUpdatedId(userId);
		pointPayout.setUpdatedDate(createdDate);
		pointPayout.setUserId(userId);
		pointPayout.setOrderId(pointOrder.getId());
		pointPayout.setRecordType(RecordType.PAYOUT);
		pointIncomeMng.save(pointPayout);
		//更新订单记录
		pointOrder.setStatus(OrderStatus.PAYOUT);
		pointOrderDao.update(pointOrder);
		return pointOrder;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.order.PointOrderMng#saveOrderForLottery(com.jeecms.point.vo.order.OrderExchengeVo, com.jeecms.core.entity.UnifiedUser, int, int)
	 */
	@Override
	public PointOrder saveOrderForLottery(OrderExchengeVo exchengeVo,UnifiedUser unifiedUser, int channel) {
		Long userId=new Long(unifiedUser.getId());
		PointProduct pointProduct=pointProductMng.findById(exchengeVo.getProductId());
		//判断抽奖的库存是否大于 0
		if(pointProduct.getLotteryStockNum()<=0){
			return null;
		}
		long pointNum=pointProduct.getPointNum();
		Date createdDate=new Date();
		PointOrder pointOrder=new PointOrder();
		pointOrder.setNum(1L);
		pointOrder.setTotalPointNum(pointNum);
		pointOrder.setMemo(exchengeVo.getRuleName() + pointProduct.getTitle());
		pointOrder.setPayoutDate(createdDate);
		pointOrder.setChannel(channel);
		pointOrder.setOrderDate(createdDate);
		pointOrder.setUserId(userId);		 
		pointOrder.setCreatedId(userId);
		pointOrder.setCreatedDate(createdDate);
		pointOrder.setUpdatedId(userId);
		pointOrder.setUpdatedDate(createdDate);
		pointOrder.setType(PayoutType.LOTTERY);
		pointOrder.setStatus(OrderStatus.PAYOUT);
		pointOrder.setOrderKey(exchengeVo.getLotteryHistoryId());//使用抽奖记录ID来标示订单唯一性
		
		PointOrderDetails orderDetails=new PointOrderDetails();
		//保存订单的快照
		BeanUtils.copyProperties(pointProduct,orderDetails);
		orderDetails.setCreatedId(userId);
		orderDetails.setCreatedDate(createdDate);
		orderDetails.setUpdatedId(userId);
		orderDetails.setUpdatedDate(createdDate);
		orderDetails.setUserId(userId);
		orderDetails.setPayoutDate(createdDate);
		orderDetails.setPointOrder(pointOrder);
		orderDetails.setPointNum(pointNum);
		orderDetails.setProductId(pointProduct.getId());
		orderDetails.setId(null);
		
		Set<PointOrderDetails> details=new HashSet<PointOrderDetails>();
		//orderDetails.setPointOrder(pointOrder);
		details.add(orderDetails);
		pointOrder.setPointOrderDetails(details);		
		//保存数据
		pointOrder=this.save(pointOrder);
		//保存用户的快递信息
		Express express=new Express();
		BeanUtils.copyProperties(exchengeVo, express);
		express.setDisrict(exchengeVo.getCounty());
		express.setCreatedId(userId);
		express.setCreatedDate(createdDate);
		express.setUpdatedId(userId);
		express.setUpdatedDate(createdDate);
		express.setUserId(userId);		
		express.setOrderId(pointOrder.getId());
		//快递信息
		expressMng.save(express);
		//更新订单记录
		pointOrder.setStatus(OrderStatus.PAYOUT);
		pointOrderDao.update(pointOrder);
		return pointOrder;
	}

	@Override
	public PointOrder saveOrderForKill(OrderExchengeVo exchengeVo, Long  userId, int channel) {
		//秒杀库存减1
		PointProduct pointProduct = pointProductDao.seckillProductsDecrease(exchengeVo.getProductId());
		if(pointProduct==null){
			return null;
		}

		long pointNum=pointProduct.getPointNum();
		Date createdDate=new Date();

		//扣减积分
		boolean flag = pointUserMng.updateUserPoint(userId.intValue(),pointNum);
		if(!flag){
			logger.info("用户:{},扣减积分不成功!",userId);
			return null;
		}

		PointOrder pointOrder=new PointOrder();
		pointOrder.setNum(1L);
		pointOrder.setTotalPointNum(pointNum);
		pointOrder.setMemo(exchengeVo.getRuleName() + pointProduct.getTitle());
		pointOrder.setPayoutDate(createdDate);
		pointOrder.setChannel(channel);
		pointOrder.setOrderDate(createdDate);
		pointOrder.setUserId(userId);
		pointOrder.setCreatedId(userId);
		pointOrder.setCreatedDate(createdDate);
		pointOrder.setUpdatedId(userId);
		pointOrder.setUpdatedDate(createdDate);
		pointOrder.setType(PayoutType.SECKILL);
		pointOrder.setStatus(OrderStatus.PAYOUT);
		PointOrderDetails orderDetails=new PointOrderDetails();
		//保存订单的快照
		BeanUtils.copyProperties(pointProduct,orderDetails);
		orderDetails.setCreatedId(userId);
		orderDetails.setCreatedDate(createdDate);
		orderDetails.setUpdatedId(userId);
		orderDetails.setUpdatedDate(createdDate);
		orderDetails.setUserId(userId);
		orderDetails.setPayoutDate(createdDate);
		orderDetails.setPointOrder(pointOrder);
		orderDetails.setPointNum(pointNum);
		orderDetails.setProductId(pointProduct.getId());
		orderDetails.setId(null);

		Set<PointOrderDetails> details=new HashSet<PointOrderDetails>();
		//orderDetails.setPointOrder(pointOrder);
		details.add(orderDetails);
		pointOrder.setPointOrderDetails(details);
		//保存数据
		pointOrder=this.save(pointOrder);
		//保存用户的快递信息
		Express express=new Express();
		BeanUtils.copyProperties(exchengeVo, express);
		express.setDisrict(exchengeVo.getCounty());
		express.setCreatedId(userId);
		express.setCreatedDate(createdDate);
		express.setUpdatedId(userId);
		express.setUpdatedDate(createdDate);
		express.setUserId(userId);
		express.setOrderId(pointOrder.getId());
		//快递信息
		expressMng.save(express);
		//更新订单记录
		pointOrder.setStatus(OrderStatus.PAYOUT);
		pointOrderDao.update(pointOrder);

		//添加消费记录
		PointIncome pointPayout=new PointIncome();
		pointPayout.setPointNum(-pointNum);
		pointPayout.setPointType(PayoutType.SECKILL);
		pointPayout.setPointRuleName(exchengeVo.getRuleName()+ pointProduct.getTitle());
		pointPayout.setChannel(channel);
		pointPayout.setIncomeDate(createdDate);
		pointPayout.setPointRuleNo(RuleConstant.SECKILL_PAY_POINT);
		pointPayout.setCreatedId(userId);
		pointPayout.setCreatedDate(createdDate);
		pointPayout.setUpdatedId(userId);
		pointPayout.setUpdatedDate(createdDate);
		pointPayout.setUserId(userId);
		pointPayout.setOrderId(pointOrder.getId());
		pointPayout.setRecordType(RecordType.PAYOUT);
		pointIncomeMng.save(pointPayout);
		return pointOrder;
	}
}
