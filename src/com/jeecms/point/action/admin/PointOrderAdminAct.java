/**
 * 
 */
package com.jeecms.point.action.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;
import com.jeecms.point.constant.OrderStatus;
import com.jeecms.point.entity.Express;
import com.jeecms.point.entity.PointOrder;
import com.jeecms.point.manager.express.ExpressMng;
import com.jeecms.point.manager.order.PointOrderMng;
import com.jeecms.point.vo.order.OrderExpressVo;
import com.jeecms.point.vo.order.PointOrderVo;
import com.jeecms.point.web.json.JSONParser;
import com.jeecms.point.web.query.QueryVo;

/**
 * 积分商品兑换查询
 * @author wanglijun
 *
 */
@Controller
@RequestMapping("/admin/points/order")
public class PointOrderAdminAct {
	/** 首页 */
	private static final String INDEX_RETURN = "points/order/index";
	/** 发货页面 */
	private static final String SEND_RETURN = "points/order/send";
	/** 详细页面*/
	private static final String INFO_RETURN = "points/order/info";
	/**订单管理*/
	@Autowired
	private PointOrderMng  pointOrderMng;
	/**快递信息*/
	@Autowired
	private ExpressMng expressMng;
	
	
	/***
	 * 首页显示数据
	 * @return
	 */
	@RequestMapping("/index.do")
	public String index(){
		return INDEX_RETURN;
	}
	/***
	 * 取消订单
	 * @param id 订单号
	 * @return
	 */
	@RequestMapping("/cannel.do")
	@ResponseBody
	public String cannel(Long id){
		this.pointOrderMng.updateStatus(id,OrderStatus.CANNEL);
		return "true";
	}
	/***
	 * 发货页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/send.do")
	public String send(Long id,ModelMap model){
		PointOrder pointOrder=pointOrderMng.findById(id);
		model.put("pointOrder", pointOrder);
		 		//收货人信息
		Express  express=expressMng.findByOrderId(id);
		model.put("express",express);
		return SEND_RETURN;
	}
	
	@RequestMapping("/info.do")
	public String info(Long id,ModelMap model){
		//订单信息
		PointOrder pointOrder=pointOrderMng.findById(id);
		//收货人信息
		Express  express=expressMng.findByOrderId(id);
		model.put("pointOrder", pointOrder);
		model.put("express",express);
		return INFO_RETURN;
	}
	/***
	 * 发货信息保存
	 * @param id
	 * @return
	 */
	@RequestMapping("/savesend.do")
	public String saveSend(OrderExpressVo orderExpressVo){
		pointOrderMng.updateSendInfo(orderExpressVo.getId(), orderExpressVo.getExpressId(), orderExpressVo.getExpressNo(), orderExpressVo.getExpressCompany());
		return INDEX_RETURN;
	}
	
	/***
	 * 订单完成
	 * @param id
	 * @return
	 */
	@RequestMapping("/finish.do")
	@ResponseBody
	public String finish(Long id){
		this.pointOrderMng.updateStatus(id,OrderStatus.FINISH);
		return "true";
	}
	
	/*** 查询列表信息 */
	@RequestMapping("/list.do")
	@ResponseBody
	public String list(QueryVo queryVo,PointOrderVo pointOrderVo) {		
		Pagination pagination =pointOrderMng.queryPagination(queryVo, pointOrderVo);
		return JSONParser.toDataGridString(pagination,DateUtils.FORMAT_DATE_TIME_DEFAULT);
	}
}
