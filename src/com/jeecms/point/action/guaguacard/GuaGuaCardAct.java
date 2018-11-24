/**
 * 
 */
package com.jeecms.point.action.guaguacard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.common.util.DateUtils;
import com.jeecms.point.entity.ActivityProduct;
import com.jeecms.point.entity.GuaGuaLotteryResult;
import com.jeecms.point.entity.PointActivity;
import com.jeecms.point.entity.base.BasePointProduct;
import com.jeecms.point.manager.activity.ActivityMng;
import com.jeecms.point.manager.lottery.LotteryMng;
import com.jeecms.point.manager.product.ActivityProductMng;
import com.jeecms.point.web.json.JSONParser;

/**
 * @author wanglijun
 *
 */
@Controller
@RequestMapping("/points/guaguacard")
public class GuaGuaCardAct {
	/**日志*/
	private static final Logger logger=LoggerFactory.getLogger(GuaGuaCardAct.class);
	/**刮刮的产品代码*/
	public static final String ACTIVITY_CODE="8200";
	/**HTTP*/
	private static final String HTTP="http://";
	/**LIMIT_IP*/
	private static final String  LIMIT_IP="127.0.0.1";
	
	private static final List<String> LIMIT_IPS=new ArrayList<String>();
	
	static{
		LIMIT_IPS.add("127.0.0.1");
		LIMIT_IPS.add("46.51.221.44");
		LIMIT_IPS.add("122.144.137.144");
	}
 
    @Autowired
    private ActivityMng activityMng;
    
    @Autowired
    private ActivityProductMng activityProductMng;
    
    @Autowired
    private LotteryMng lotteryMng;
    
    
    
	@RequestMapping(value = "/activity.jhtml", method = RequestMethod.GET)
    @ResponseBody
	public void awardList(String key,HttpServletRequest request,HttpServletResponse response,ModelMap model){
		 CmsSite site = CmsUtils.getSite(request);
		
		 logger.info("site:{}",site.getBaseDomain());
		 PointActivity activity = this.activityMng.queryStatus(ACTIVITY_CODE);
         List<ActivityProduct> activityProducts=this.activityProductMng.getActivityProducts(activity.getId());
         List<BasePointProduct>  products=new ArrayList<BasePointProduct>();
         for(ActivityProduct  activityProduct:activityProducts){
        	 BasePointProduct  pointProduct=activityProduct.getProduct();
        	 pointProduct.setImageLargeURL(HTTP+site.getDomain()+pointProduct.getImageLargeURL());
        	 pointProduct.setImageMiddleURL(HTTP+site.getDomain()+pointProduct.getImageMiddleURL());
        	 products.add(activityProduct.getProduct());
         }
         
         Set<String> activityProperties=new HashSet<String>();
         activityProperties.add("startTime");
         activityProperties.add("endTime");
         activityProperties.add("type");
         activityProperties.add("name");
         activityProperties.add("code");
         activityProperties.add("status");
         
         String  activityJsonStr=JSONParser.toJSONString(activity,DateUtils.FORMAT_DATE_TIME_DEFAULT,activityProperties,true);
         
         Set<String> properties=new HashSet<String>();
         properties.add("productNo");
         properties.add("lotteryStockNum");
         properties.add("title");
         properties.add("subTitle");
         properties.add("imageLargeURL");
         properties.add("imageMiddleURL");
         properties.add("monthLotteryStockNum");
         properties.add("monthLeftLotteryStockNum");
         properties.add("memo");
         String productStr=JSONParser.toJSONString(products,properties);
         StringBuilder builder=new StringBuilder();
         builder.append(activityJsonStr);
         builder.insert(activityJsonStr.length()-1,",\"products\":"+productStr);
         this.printJson(builder.toString(),response);  
	}
	/***
	 * 
	 * @param request
	 * @return
	 */
	private String getRemortIP(HttpServletRequest request) {
	   if (request.getHeader("x-forwarded-for") == null) {
		   return request.getRemoteAddr();
	   }
	   return request.getHeader("x-forwarded-for");
	}
		
	
	@RequestMapping(value = "/lottery.jhtml", method = RequestMethod.GET)
    @ResponseBody
    public void lottery(String key,String vin,String openId,HttpServletRequest request, ModelMap model,HttpServletResponse response) {
		//限制IP
		String requestip=this.getRemortIP(request);
		
		CmsSite site = CmsUtils.getSite(request);
		GuaGuaLotteryResult lotteryResult=new GuaGuaLotteryResult(ACTIVITY_CODE,true);

		if(!LIMIT_IPS.contains(requestip)){
			lotteryResult.addMessage("040","请求IP限制",false);
			this.printJson(JSONParser.toJSONString(lotteryResult),response);
			return;
		}
		//判断VIN码不能为空
		if(StringUtils.isEmpty(vin)){		
			lotteryResult.addMessage("031","VIN码不能为空！",false);
			this.printJson(JSONParser.toJSONString(lotteryResult),response);
			return;
		}
		//判断微信的opendId不能为空
		if(StringUtils.isEmpty(openId)){
			lotteryResult.addMessage("032","微用户的opendId不以为空",false);
			this.printJson(JSONParser.toJSONString(lotteryResult),response);
			return;
		}
		PointActivity  activity=this.activityMng.findByCode(ACTIVITY_CODE);
		lotteryResult=this.activityMng.checkStatus(activity,ACTIVITY_CODE);
		
		
		//判断活动状态
		if(!lotteryResult.isWon()){
			this.printJson(JSONParser.toJSONString(lotteryResult),response);
			return;
		}
		//判断是否已抽奖
		if(this.activityMng.queryLotteryCountByOpenId(ACTIVITY_CODE,vin,openId)>0){
			lotteryResult.addMessage("033","VIN和openId已参与抽奖",false);
			
			this.printJson(JSONParser.toJSONString(lotteryResult),response);
			return;
		}
		//执行抽奖逻辑
		lotteryResult=this.lotteryMng.doGuaGuaLottery(activity,ACTIVITY_CODE,vin, openId);
		//设置图片的路径
		if(StringUtils.isNotEmpty(lotteryResult.getImageLargeURL())){
			lotteryResult.setImageLargeURL(HTTP+site.getDomain()+lotteryResult.getImageLargeURL());
		}
		if(StringUtils.isNotEmpty(lotteryResult.getImageMiddleURL())){
			lotteryResult.setImageMiddleURL(HTTP+site.getDomain()+lotteryResult.getImageMiddleURL());
		}
		this.printJson(JSONParser.toJSONString(lotteryResult),response);
	}
	
	
	public void printJson(String jsonStr,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		try {
			response.getWriter().write(jsonStr);
			response.getWriter().flush();
			response.getWriter().close();
			response.flushBuffer();
		} catch (IOException e) {
			logger.error(StringUtils.EMPTY,e);
		}
	}
}
