/**
 * 
 */
package com.jeecms.point.action.order;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.MemberConfig;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.point.constant.Channel;
import com.jeecms.point.constant.OrderStatus;
import com.jeecms.point.constant.PayoutType;
import com.jeecms.point.constant.RuleConstant;
import com.jeecms.point.constant.UserType;
import com.jeecms.point.entity.Express;
import com.jeecms.point.entity.OrderResult;
import com.jeecms.point.entity.PointOrder;
import com.jeecms.point.entity.PointOrderDetails;
import com.jeecms.point.entity.PointPayout;
import com.jeecms.point.entity.PointProduct;
import com.jeecms.point.manager.express.ExpressMng;
import com.jeecms.point.manager.order.PointOrderMng;
import com.jeecms.point.manager.point.PointPayoutMng;
import com.jeecms.point.manager.point.PointUserMng;
import com.jeecms.point.manager.product.PointProductMng;
import com.jeecms.point.vo.order.OrderExchengeVo;
import com.jeecms.point.web.json.JSONParser;

/**
 * @author wanglijun
 *
 */

@RequestMapping("/points/order/")
public class PointOrderAct {
	/**日志*/
	private static final Logger logger=LoggerFactory.getLogger(PointOrderAct.class);
	/**订单确认页面*/
	private static final String EXCHANGE_INDEX="/WEB-INF/t/cms/www/red/points/order/index.html";
	/**订单提交成功页面*/
	private static final String EXCHANGE_SUCCESS="/WEB-INF/t/cms/www/red/points/order/index.html";
	/**订单管理*/
	@Autowired
	private PointOrderMng pointOrderMng; 
	/**快递信息*/
	@Autowired
	private ExpressMng expressMng;
	/**产品信息*/
	@Autowired
	private PointProductMng pointProductMng;
	/**用户积分信息*/
	@Autowired
	private PointUserMng pointUserMng; 
	/**用户积分消费*/
	@Autowired
	private PointPayoutMng pointPayoutMng;
	
	/**测试页面*/
	@RequestMapping("/index.jhtml")
	@ResponseBody
	public String index(){
		Long userId=new Long(1);
		PointProduct pointProduct=pointProductMng.findById(106L);
		
		long pointNum=pointProduct.getPointNum();
		Date createdDate=new Date();
		PointOrder pointOrder=new PointOrder();
		pointOrder.setNum(1L);
		pointOrder.setTotalPointNum(pointNum);
		pointOrder.setMemo("兑换"+pointProduct.getTitle());
		pointOrder.setPayoutDate(createdDate);
		pointOrder.setChannel(Channel.PC);
		pointOrder.setOrderDate(createdDate);
		pointOrder.setUserId(userId);		 
		pointOrder.setCreatedId(userId);
		pointOrder.setCreatedDate(createdDate);
		pointOrder.setUpdatedId(userId);
		pointOrder.setUpdatedDate(createdDate);
		pointOrder.setType(PayoutType.EXCHANGE);
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
		orderDetails.setPointNum(50L);
		orderDetails.setProductSnapshotId(2L);
		orderDetails.setProductId(pointProduct.getId());
		orderDetails.setId(null);
		Set<PointOrderDetails> details=new HashSet<PointOrderDetails>();
		orderDetails.setPointOrder(pointOrder);
		details.add(orderDetails);
		pointOrder.setPointOrderDetails(details);		
		//保存数据
		pointOrder=this.pointOrderMng.save(pointOrder);
		//保存积分消费数据
		Express express=new Express();
		express.setCreatedId(userId);
		express.setCreatedDate(createdDate);
		express.setUpdatedId(userId);
		express.setUpdatedDate(createdDate);
		express.setUserId(userId);
		express.setMemo("配置送信息");
		express.setProvince("上海市");
		express.setCity("上海市");
		express.setDisrict("浦东新区");
		express.setMobilePhoneNo("15502152926");
		express.setPhoneNo("15502152629");
		express.setSendee("Even");
		express.setOrderId(pointOrder.getId());
		expressMng.save(express);
		
		//扣减积分
		pointUserMng.updateUserPoint(1,pointNum);
		//添加消费记录
		PointPayout pointPayout=new PointPayout();
		pointPayout.setPointRuleName(pointOrder.getMemo());
		pointPayout.setChannel(Channel.PC);
		pointPayout.setPayoutDate(createdDate);
		pointPayout.setPointRuleNo(RuleConstant.EXCHANGE_PAY_POINT);
		pointPayout.setCreatedId(userId);
		pointPayout.setCreatedDate(createdDate);
		pointPayout.setUpdatedId(userId);
		pointPayout.setUpdatedDate(createdDate);
		pointPayout.setUserId(userId);
		pointPayout.setOrderId(pointOrder.getId());
		pointPayoutMng.save(pointPayout);
		//更新订单记录
		pointOrder.setStatus(OrderStatus.PAYOUT);
		
		return  pointOrder.getId()+"";
	}
	/***
	 * 查询收商品信息
	 * @param productId
	 * @param model
	 * @return 
	 */
	@RequestMapping("/exchange/{productId}.jspx")
	public String exchangeProudct(@PathVariable long productId,ModelMap model,HttpServletRequest request){
		PointProduct product=pointProductMng.findById(productId);
		model.addAttribute("product", product);
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		//用户没有登录
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		//判断用户是车主认证用户
		if(UserType.AUTH_USER_GROUP==user.getGroup().getId()){
			model.addAttribute("noauthuser","1");
		}else{
			model.addAttribute("noauthuser","y");
		}
		//查询用户信息
		UnifiedUser unifiedUser=pointUserMng.getUser(user.getId());
		model.addAttribute("userpoint", unifiedUser.getCurrentPoint());
		//判断用户积分是否够兑换商品
		if(product!=null&&unifiedUser!=null&&product.getPointNum()<=unifiedUser.getCurrentPoint()){
			model.addAttribute("enoughpoint","1");
		}else{
			model.addAttribute("enoughpoint","0");
		}
		return EXCHANGE_INDEX;
	}
	
	/**
	 * 保存收货人信息
	 * @param orderExchengeVo 订单交易 
	 * @param model 订单交易 
	 * @return 
	 */
	@RequestMapping("/exchangesave.jspx")
	public String exchangeSave(OrderExchengeVo orderExchengeVo,ModelMap model,HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		PointProduct product=pointProductMng.findById(orderExchengeVo.getProductId());
		model.addAttribute("product", product);
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		OrderResult  orderResult=null;
		//用户没有登录
		if (user == null) {
			orderResult=new OrderResult(OrderResult.MSGCODE_NO_LOGIN);
		}
		//判断用户是车主认证用户
		if(UserType.AUTH_USER_GROUP!=user.getGroup().getId()){
			orderResult=new  OrderResult(OrderResult.MEGCODE_NO_AUTH);
		}
		//查询用户信息
		UnifiedUser unifiedUser=pointUserMng.getUser(user.getId());
		model.addAttribute("userpoint", unifiedUser.getCurrentPoint());
		//判断用户积分是否够兑换商品
		if(product!=null&&unifiedUser!=null&&product.getPointNum()>unifiedUser.getCurrentPoint()){
			orderResult=new  OrderResult(OrderResult.MEGCODE_NO_POINT);
		}
		//判断库存是否大于0
		if(product!=null&&product.getExchangeStockNum()<=0){
			orderResult=new  OrderResult(OrderResult.MESGCODE_NO_STOCK);
		}
		//将错误信息返回
		if(orderResult!=null){
			BeanUtils.copyProperties(orderExchengeVo,orderResult);
			try {
				String result=JSONParser.toJSONString(orderResult);
				response.getWriter().write(result);
				response.getWriter().flush();
				response.getWriter().close();
				response.flushBuffer();
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
			return StringUtils.EMPTY;
		}
		orderExchengeVo.setRuleName("兑换 ");
		logger.info("orderExchengeVo:"+orderExchengeVo.toString());
		//保存订单及订单明细和、快递信息商品快照		
		//保存积分消费和扣减积分数据
		//更新状态订单状态
		PointOrder pointOrder=pointOrderMng.saveOrderInfo(orderExchengeVo, unifiedUser,Channel.PC,PayoutType.EXCHANGE);
		
		//保存失败的情况
		if(pointOrder==null||pointOrder.getId()==null){
			orderResult=new OrderResult(OrderResult.MEGCODE_FAIL);
		}else{
			orderResult=new OrderResult(OrderResult.MEGCODE_SUCCESS);
			orderResult.setOrderId(pointOrder.getId());
			BeanUtils.copyProperties(orderExchengeVo,orderResult);
		}
		try {
			response.getWriter().write(JSONParser.toJSONString(orderResult));
			response.getWriter().flush();
			response.getWriter().close();
			response.flushBuffer();
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
		return StringUtils.EMPTY;
	}
}
