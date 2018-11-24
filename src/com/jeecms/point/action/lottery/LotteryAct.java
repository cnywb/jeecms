package com.jeecms.point.action.lottery;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.MemberConfig;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.point.constant.Channel;
import com.jeecms.point.entity.ActivityProduct;
import com.jeecms.point.entity.PointActivity;
import com.jeecms.point.entity.PointOrder;
import com.jeecms.point.entity.PointProduct;
import com.jeecms.point.manager.activity.ActivityMng;
import com.jeecms.point.manager.lottery.LotteryMng;
import com.jeecms.point.manager.order.PointOrderMng;
import com.jeecms.point.manager.point.PointUserMng;
import com.jeecms.point.manager.product.ActivityProductMng;
import com.jeecms.point.manager.product.PointProductMng;
import com.jeecms.point.vo.order.OrderExchengeVo;
import com.jeecms.point.web.json.JSONParser;

/**
 * Created by kui.yang on 15/5/30.
 * 抽奖活动控制器
 * 只提供ajax访问请求，页面通过CMS模板来控制
 */
@Controller
@RequestMapping("/points/lottery")
public class LotteryAct {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
	private UnifiedUserMng  unifiedUserMng;

    @Autowired
    private ActivityMng activityMng;

    @Autowired
    private LotteryMng lotteryMng;

    @Autowired
    private PointUserMng pointUserMng;
    @Autowired
    private ActivityProductMng activityProductMng;
    @Autowired
    private PointProductMng pointProductMng;
    @Autowired
    private PointOrderMng pointOrderMng;

    @RequestMapping(value = "/index.jhtml")
    public String index(HttpServletRequest request, ModelMap model) {
    	CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request,model,site);
		FrontUtils.frontPageData(request, model);
		
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		//判断是否登录
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		UnifiedUser  unifiedUser=unifiedUserMng.findById(user.getId());
		model.addAttribute("unifiedUser", unifiedUser);

        return "/WEB-INF/t/cms/www/red/points/lottery/index.html";
    }

        /**
         * 查询当前登录用户可以抽奖的次数
         *
         * @return
         */
    @RequestMapping(value = "/queryCount.jhtml", method = RequestMethod.POST)
    @ResponseBody
    public String queryCount(String activityCode, HttpServletRequest request, ModelMap model) {
        CmsUser user = CmsUtils.getUser(request);
        if (user == null) {
            logger.info("查询用户抽奖次数时：false:用户未登录");
            return "false:用户未登录";
        }
        UnifiedUser unifiedUser = pointUserMng.getUser(user.getId());
        String retval = "";
        //获取用户积分
        Long userPoints = unifiedUser.getCurrentPoint();
        try {
            PointActivity activity = this.activityMng.queryStatus(activityCode);
            int count = this.activityMng.queryLotteryCount(activityCode, user.getId().longValue());
            if (count >= activity.getLotteryLimitForDay()) {
                logger.info("查询用户抽奖次数时：false:{}您今天抽奖次数用完了:{}，请明天再来!",user.getUsername(),count);
                return "false:提示：您已达到今天抽奖机会上限T^T明天再来吧!";
            }else{
                retval = "true:"+(activity.getLotteryLimitForDay()-count);
            }
            Integer costPoints = activity.getCostPoints();
            if(userPoints==null||userPoints<costPoints){
                logger.info("查询用户抽奖次数时：false:{}您的积分已经不足:{},需要:{}，无法参加本次抽奖活动!",new Object[]{unifiedUser.getUsername(),userPoints,costPoints});
                return "false:提示：您的积分已经不足!";
            }

        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(),e);
            logger.error("用户抽奖出现异常:{},userId:{},activityCode:{}",new Object[]{e.getMessage(),user.getId(),activityCode});
            retval = "false:" + e.getMessage();
        }

        return retval;
    }


    /**
     * 用户抽奖
     *
     * @param activityCode 活动代码
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/lottery.jhtml", method = RequestMethod.POST)
    @ResponseBody
    public String lottery(String activityCode, HttpServletRequest request, ModelMap model) {
        String   retval = this.queryCount(activityCode, request, model);
        if(!retval.equals("")&&!retval.startsWith("true")){
            logger.info("用户抽奖前校验结果：{}",retval);
            return retval;
        }
        CmsUser user = CmsUtils.getUser(request);
        try{
            retval = this.lotteryMng.doLottery(activityCode,user.getId().longValue());
        }catch (IllegalArgumentException e){
            logger.error("用户抽奖出现异常:{},userId:{},activityCode:{}",new Object[]{e.getMessage(),user.getId(),activityCode},e);
            retval = e.getMessage();
        }
        logger.error("用户抽奖结果:{},userId:{},activityCode:{}",new Object[]{retval,user.getId(),activityCode});

        return retval;
    }


    @RequestMapping(value = "/querylotteryResult.jhtml", method = RequestMethod.GET)
    public void querylotteryResult(String activityCode, HttpServletRequest request, ModelMap model,HttpServletResponse response) {
        try{
            List<String>  retval =  this.lotteryMng.queryLotteryResult(activityCode);

            String result= JSONParser.toJSONString(retval);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result);
            response.getWriter().flush();
            response.getWriter().close();
            response.flushBuffer();
        }catch (IllegalArgumentException e){
            logger.error("查询抽奖结果出现异常:{},activityCode:{}",new Object[]{e.getMessage(),activityCode});
            logger.error(e.getMessage(),e);
        } catch (IOException e) {
            logger.error("查询抽奖结果出现异常:{},activityCode:{}",new Object[]{e.getMessage(),activityCode});
            logger.error(e.getMessage(),e);
        }

    }

    @RequestMapping(value = "/queryActivityProduct.jhtml", method = RequestMethod.GET)
    public void queryActivityProduct(String activityCode, HttpServletRequest request, ModelMap model,HttpServletResponse response) {

        try{
            PointActivity activity = this.activityMng.queryStatus(activityCode);
            List<ActivityProduct> retval = this.activityProductMng.getActivityProducts(activity.getId());
            String result= JSONParser.toJSONString(retval);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result);
            response.getWriter().flush();
            response.getWriter().close();
            response.flushBuffer();
        }catch (IllegalArgumentException e){
            logger.error("查询奖品列表出现异常:{},activityCode:{}",new Object[]{e.getMessage(),activityCode});
            logger.error(e.getMessage(),e);
        }catch (IOException e) {
            logger.error("查询奖品列表出现异常:{},activityCode:{}",new Object[]{e.getMessage(),activityCode});
            logger.error(e.getMessage(),e);
        }
    }

    /**
     * 保存收货人信息
     * @param orderExchengeVo 订单交易
     * @param model 订单交易
     * @return
     */
    @RequestMapping("/exchangesave.jhtml")
    public void exchangeSave(OrderExchengeVo orderExchengeVo,ModelMap model,HttpServletRequest request,HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        Map<String,String> retval = new HashMap<String,String>();
        retval.put("status","true");
        PointProduct product=pointProductMng.findById(orderExchengeVo.getProductId());
        if(product==null){
            retval.put("status","false");
            retval.put("message","产品不存在!");
            writeSaveResult(response, retval);
            return ;
        }

        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);

        UnifiedUser unifiedUser=null;
        //用户没有登录
        if (user == null) {
            if(orderExchengeVo.getOpenId()!=null){
                unifiedUser = pointUserMng.findByOpenId(orderExchengeVo.getOpenId());
            }
            if(unifiedUser==null){
                retval.put("status","false");
                retval.put("message","用户没有登录");
                writeSaveResult(response, retval);
                return ;
            }
        }else{
            unifiedUser = pointUserMng.getUser(user.getId());
        }
        // 查询当前用户当前活动及商品是否真的中奖
        boolean flag = lotteryMng.isLotteried(orderExchengeVo.getActivityCode(),unifiedUser.getId().longValue(),orderExchengeVo.getLotteryHistoryId());
        if(!flag){
            retval.put("status","false");
            retval.put("message","非法请求");
            writeSaveResult(response, retval);
            return ;
        }
        //查询订单记录是否已经存在
        PointOrder order = pointOrderMng.findByOrderKey(orderExchengeVo.getLotteryHistoryId());
        if(order!=null){
            retval.put("status","false");
            retval.put("message","订单已经存在，请勿重复提交");
            writeSaveResult(response, retval);
            return ;
        }
        //将错误信息返回
        if(retval.get("status").equals("false")){
            writeSaveResult(response, retval);
            return ;
        }

        orderExchengeVo.setRuleName("抽奖 ");
        logger.info("orderExchengeVo:"+orderExchengeVo.toString());
        //保存订单及订单明细和、快递信息商品快照
        //更新状态订单状态

        PointOrder pointOrder=pointOrderMng.saveOrderForLottery(orderExchengeVo, unifiedUser, Channel.PC);

        //保存失败的情况
        if(pointOrder==null||pointOrder.getId()==null){
            retval.put("status","false");
            retval.put("message","保存失败");
        }else{
            retval.put("message", "保存成功");
        }
        writeSaveResult(response, retval);
    }

    private void writeSaveResult(HttpServletResponse response, Map<String, String> retval) {
        try {
            String result= JSONParser.toJSONString(retval);
            logger.info("订单提交结果：{}",result);
            response.getWriter().write(result);
            response.getWriter().flush();
            response.getWriter().close();
            response.flushBuffer();
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }
    }


}
