package com.jeecms.wxpoint.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.point.constant.Channel;
import com.jeecms.point.constant.PayoutType;
import com.jeecms.point.entity.OrderResult;
import com.jeecms.point.entity.PointOrder;
import com.jeecms.point.entity.PointProduct;
import com.jeecms.point.entity.base.BasePointProduct;
import com.jeecms.point.manager.order.PointOrderMng;
import com.jeecms.point.manager.point.PointUserMng;
import com.jeecms.point.manager.product.ActivityProductMng;
import com.jeecms.point.manager.product.PointProductMng;
import com.jeecms.point.vo.order.OrderExchengeVo;

@Controller
@RequestMapping("/wxpoints/product/")
public class WeiXinPointProductAction {
	/**微信积分查询页面*/
	private static final  String INDEX_RETURN="/WEB-INF/t/cms/www/red/wxpoints/redeem.html";
	@Autowired
	private PointUserMng pointUserMng;
	@Autowired
	private ActivityProductMng activityProductMng; 
	@Autowired
	private PointProductMng pointProductMng;
	@Autowired
	private PointOrderMng pointOrderMng;
	@Autowired
	private CmsUserMng cmsUserMng;
	/**首页*/
	@RequestMapping("index.jhtml")
	public String index(HttpServletRequest request,ModelMap model,String openId){
		CmsSite site = CmsUtils.getSite(request);
        UnifiedUser user = pointUserMng.findByOpenId(openId);
		FrontUtils.frontData(request,model,site);
		FrontUtils.frontPageData(request, model);
        model.addAttribute("unifiedUser", user);
        
        //微信兑换商品的活动id
		Long weixinProduct = 101L;
		List<BasePointProduct> list =  activityProductMng.getProducts(weixinProduct);
		model.addAttribute("list", list);
		return INDEX_RETURN;
	}
	
	@RequestMapping("exchange.jhtml")
	@ResponseBody
	public String index(ModelMap model,String openId,Long productId,OrderExchengeVo orderExchengeVo){
        UnifiedUser user = pointUserMng.findByOpenId(openId);
        PointProduct pointProduct = pointProductMng.findById(productId);
        OrderResult  orderResult=null;
        //如果没有库存
        if(pointProduct.getExchangeStockNum()==null||pointProduct.getExchangeStockNum()<=0L){
        	orderResult=new  OrderResult(OrderResult.MESGCODE_NO_STOCK);
        	return "noproduct";
        }
        //用户积分不足
        if(user==null||user.getCurrentPoint()<pointProduct.getPointNum()){
        	orderResult=new  OrderResult(OrderResult.MEGCODE_NO_POINT);
        	return "nopoint";
        }
        orderExchengeVo.setRuleName("兑换 ");
        //更新状态订单状态
      	PointOrder pointOrder=pointOrderMng.saveOrderInfo(orderExchengeVo, user,Channel.WEIXIN,PayoutType.EXCHANGE);
      	//保存失败的情况
  		if(pointOrder==null||pointOrder.getId()==null){
  			orderResult=new OrderResult(OrderResult.MEGCODE_FAIL);
  		}else{
  			orderResult=new OrderResult(OrderResult.MEGCODE_SUCCESS);
  			orderResult.setOrderId(pointOrder.getId());
  			BeanUtils.copyProperties(orderExchengeVo,orderResult);
  		}
		return "success";
	}
	
	@RequestMapping("verify.jhtml")
	@ResponseBody
	public String verify(ModelMap model,String openId,Long productId,OrderExchengeVo orderExchengeVo){
        UnifiedUser user = pointUserMng.findByOpenId(openId);
        CmsUser cmsuser=cmsUserMng.findById(user.getId());
		if(cmsuser.getGroup().getId()!=2){
			return "nopoint";
		}
        PointProduct pointProduct = pointProductMng.findById(productId);
        //如果没有库存
        if(pointProduct.getExchangeStockNum()==null||pointProduct.getExchangeStockNum()<=0L){
        	return "noproduct";
        }
        //用户积分不足
        if(user==null||user.getCurrentPoint()<pointProduct.getPointNum()){
        	return "nopoint";
        }
		return "success";
	}
}
