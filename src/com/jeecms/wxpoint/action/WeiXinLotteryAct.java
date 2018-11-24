package com.jeecms.wxpoint.action;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.point.constant.Channel;
import com.jeecms.point.entity.ActivityProduct;
import com.jeecms.point.entity.Express;
import com.jeecms.point.entity.PointActivity;
import com.jeecms.point.entity.PointOrder;
import com.jeecms.point.entity.PointProduct;
import com.jeecms.point.entity.base.BasePointProduct;
import com.jeecms.point.manager.activity.ActivityMng;
import com.jeecms.point.manager.express.ExpressMng;
import com.jeecms.point.manager.lottery.LotteryMng;
import com.jeecms.point.manager.order.PointOrderMng;
import com.jeecms.point.manager.point.PointUserMng;
import com.jeecms.point.manager.product.ActivityProductMng;
import com.jeecms.point.manager.product.PointProductMng;
import com.jeecms.point.vo.order.OrderExchengeVo;
import com.jeecms.point.web.json.JSONParser;

/**
 * Created by kui.yang on 15/9/4.
 * 微信抽奖活动控制器
 * 只提供ajax访问请求，页面通过CMS模板来控制
 */
@Controller
@RequestMapping("/wxpoints/lottery")
public class WeiXinLotteryAct {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UnifiedUserMng unifiedUserMng;

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

    @Autowired
    private ExpressMng expressMng;

    @RequestMapping(value = "/index.jhtml")
    public String index(HttpServletRequest request, ModelMap model, String openId) {

        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        FrontUtils.frontPageData(request, model);
        return "/WEB-INF/t/cms/www/red/wxpoints/lottery.html";
    }

    @RequestMapping(value = "/kill.jhtml")
    public String kill(HttpServletRequest request, ModelMap model, String openId) {

        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
      FrontUtils.frontPageData(request, model);
        return "/WEB-INF/t/cms/www/red/wxpoints/kill.html";
    }

    /**
     * 查询当前登录用户可以抽奖的次数
     *
     * @return
     */
    @RequestMapping(value = "/queryCount.jhtml", method = RequestMethod.POST)
    @ResponseBody
    public String queryCount(String activityCode, String openId, HttpServletRequest request, ModelMap model) {
        if (StringUtils.isEmpty(openId)) {
            logger.info("查询用户抽奖次数时：false:未获取到openId");
            return "false:请在微信客户端当中打开";
        }
        UnifiedUser user = pointUserMng.findByOpenId(openId);
        if (user == null) {
            //通过获取openId来获得用户是否登录，未登录则提示必须在微信登录下方可以访问
            logger.info("查询用户抽奖次数时：false:用户未登录");
            return "false:用户未登录";
        }
        String retval = "";
        //获取用户积分
        Long userPoints = user.getCurrentPoint();
        try {
            PointActivity activity = this.activityMng.queryStatus(activityCode);
            int count = this.activityMng.queryLotteryCount(activityCode, user.getId().longValue());
            if (count >= activity.getLotteryLimitForDay()) {
                logger.info("查询用户抽奖次数时：false:{}您今天抽奖次数用完了:{}，请明天再来!", user.getUsername(), count);
                return "false:提示：机会已用完,养精蓄锐,明天再继续吧!";
            } else {
                retval = "true:" + (activity.getLotteryLimitForDay() - count);
            }
            Integer costPoints = activity.getCostPoints();
            if (userPoints == null || userPoints < costPoints) {
                logger.info("查询用户抽奖次数时：false:{}您的积分已经不足:{},需要:{}，无法参加本次抽奖活动!", new Object[]{user.getUsername(), userPoints, costPoints});
                return "false:提示：积分不足，无法参与抽奖。请继续努力~";
            }

        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            logger.error("用户抽奖出现异常:{},userId:{},activityCode:{}", new Object[]{e.getMessage(), user.getId(), activityCode});
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
    public String lottery(String activityCode, String openId, HttpServletRequest request, ModelMap model) {
        String retval = this.queryCount(activityCode, openId, request, model);
        if (!retval.equals("") && !retval.startsWith("true")) {
            logger.info("用户抽奖前校验结果：{}", retval);
            return retval;
        }
        UnifiedUser user = pointUserMng.findByOpenId(openId);
        try {
            retval = this.lotteryMng.doLottery(activityCode, user.getId().longValue());
        } catch (IllegalArgumentException e) {
            logger.error("用户抽奖出现异常:{},userId:{},activityCode:{}", new Object[]{e.getMessage(), user.getId(), activityCode}, e);
            retval = e.getMessage();
        }
        logger.error("用户抽奖结果:{},userId:{},activityCode:{}", new Object[]{retval, user.getId(), activityCode});

        return retval;
    }

    /*当前商品ID与正抢购的用户数缓存数据*/
    private static Map<Long, Long> productKillCache = new ConcurrentHashMap<Long, Long>();
    /*当前商品ID与对应的库存缓存数据*/
    private static Map<Long, Long> productStockCache = new ConcurrentHashMap<Long, Long>();
    private static Map<Long, BasePointProduct> productCache = new ConcurrentHashMap<Long, BasePointProduct>();
    /*当前活动号对应的活动信息缓存数据*/
    private static Map<String, PointActivity> activityCache = new ConcurrentHashMap<String, PointActivity>();
    private static Date cacheTime = new Date();

    @RequestMapping(value = "/queryActivityProduct.jhtml", method = RequestMethod.GET)
    public void queryActivityProduct(String activityCode, HttpServletRequest request, ModelMap model, HttpServletResponse response) {

        try {
            Calendar now = Calendar.getInstance();
            //每5分钟清理一次缓存
            if (now.getTime().after(cacheTime)) {
                productKillCache.clear();
                productStockCache.clear();
                activityCache.clear();
                productCache.clear();
                now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + 5);
                cacheTime = now.getTime();
            }
            PointActivity activity = this.activityMng.queryStatus(activityCode);

            List<ActivityProduct> retval = this.activityProductMng.getActivityProducts(activity.getId());
            for (ActivityProduct p : retval) {
                Long stock = productStockCache.get(p.getProduct().getId());
                Long productStock = p.getProduct().getSeckillStockNum();
                if (stock == null || stock.longValue() != productStock.longValue()) {
                    productStockCache.put(p.getProduct().getId(), productStock);
                }
                p.setActivity(null);
                p.setCreatedId(null);
                p.setUpdatedDate(null);
                p.setUpdatedId(null);
                p.getProduct().setUpdatedId(null);
                p.getProduct().setUpdatedDate(null);
                p.getProduct().setCreatedId(null);
                p.getProduct().setAccumulatedStockNum(null);
                p.getProduct().setChannel(null);
                p.getProduct().setCreatedDate(null);
                p.getProduct().setSubTitle(null);
                p.getProduct().setStatus(null);
                p.getProduct().setShowOrder(null);
                p.getProduct().setProductCategory(null);
                p.getProduct().setMonthLeftLotteryStockNum(null);
                p.getProduct().setLotteryStockNum(null);
                p.getProduct().setMemo(null);
                p.getProduct().setExchangeStockNum(null);
                productCache.put(p.getProduct().getId(),p.getProduct());
            }
            String result = JSONParser.toJSONString(retval);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result);
            response.getWriter().flush();
            response.getWriter().close();
            response.flushBuffer();
        } catch (IllegalArgumentException e) {
            logger.error("查询奖品列表出现异常:{},activityCode:{}", new Object[]{e.getMessage(), activityCode});
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error("查询奖品列表出现异常:{},activityCode:{}", new Object[]{e.getMessage(), activityCode});
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 秒杀活动
     *
     * @param openId    微信OPENID
     * @param productId 秒杀的商品
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/doKill.jhtml", method = RequestMethod.POST)
    public void kill(String openId, Long productId, String activityCode, HttpServletRequest request,
                     ModelMap model, HttpServletResponse response) {
        if(StringUtils.isEmpty(openId)){
            logger.info("秒杀活动：false:未获取到openId");
            writeJson("false:非法请求!", response);
            return;
        }
        UnifiedUser user = pointUserMng.findByOpenId(openId);
        /*
            1.校验openId是否为空，
            记录当前请求数量，判断请求数量与商品缓存数量，
            根据商品ID查询缓存数量，
            判断活动时间是否已经开始
         */
        PointActivity activity = this.getPointActivity(activityCode);
        if (!activity.isStatus()) {
            writeJson("false:" + activity.getName(), response);
            return;
        }
        //判断当前活动是否在有效时间内，周几、时间点
        String weeks = activity.getKillOfWeek();
        Integer killStartTime = activity.getKillStartTime();
        Calendar now = Calendar.getInstance();
        if (!StringUtils.isEmpty(weeks)) {
            boolean flag = true;

            int dayOfWeek = now.get(Calendar.DAY_OF_WEEK) - 1;
            if (dayOfWeek == 0) {
                dayOfWeek = 7;
            }
            String[] ws = weeks.split(",");
            for (String w : ws) {
                if (w.equals(dayOfWeek + "")) {
                    flag = false;
                }
            }
            if (flag) {
                logger.info("activityCode:{}不在指定周几之内:{}", activityCode, weeks);
                writeJson("false:秒杀还未开始,请注意秒杀时间!", response);
                return;
            }
        }
        Calendar end = Calendar.getInstance();
        end.set(Calendar.HOUR_OF_DAY, killStartTime);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);
        if (now.before(end)) {
            logger.info("activityCode:{}尚未开始，开始时间:{}", activityCode, killStartTime);
            writeJson("false:秒杀还未开始,请注意秒杀时间!", response);
            return;
        }
        //当前请求用户总数与库存判断
        Long stock = productStockCache.get(productId);
        if (stock == null) {
            BasePointProduct product =getProduct(productId);
            if (product == null) {
                logger.info("activityCode:{},productId:{}对应的商品不存在", activityCode, productId);
                writeJson("false:数据异常，非法请求!", response);
                return;
            }
            stock = product.getSeckillStockNum();
            productStockCache.put(productId, stock);
        }
        Long count = productKillCache.get(productId);
        if (count == null) {
            count = 0L;
        }

        if (count > stock) {
            logger.info("activityCode:{},productId:{},count:{},stock:{}对应的商品库存小于当前实际请求的用户数",
                    new Object[]{activityCode, productId, count, stock});
            writeJson("false:哎呀,手慢啦!<br/>礼品已被抢光!下期再来吧!", response);
            return;
        }
        //积分判断
        BasePointProduct product =getProduct(productId);
        Long pointNum = product.getPointNum();
        //获取用户积分
        Long userPoints = user.getCurrentPoint();
        if (userPoints == null || userPoints < pointNum) {
            logger.info("用户秒杀时：false:{}您的积分已经不足:{},需要:{}", new Object[]{user.getUsername(), userPoints, pointNum});
            writeJson("false:抱歉,积分不足<br/>请继续努力~", response);
            return;
        }

        OrderExchengeVo order = new OrderExchengeVo();
        order.setProductId(productId);
        order.setActivityCode(activityCode);
        order.setRuleName("秒杀 ");
        PointOrder pointOrder = pointOrderMng.saveOrderForKill(order, user.getId().longValue(), Channel.WEIXIN);
        if (pointOrder != null) {
            count++;
            productKillCache.put(productId, count);
            writeJson("true:秒杀成功！:"+pointOrder.getId(), response);
        }else{
            writeJson("false:哎呀,手慢啦!<br/>礼品已被抢光!下期再来吧!", response);
        }

    }

    private  BasePointProduct getProduct(Long productId){
        BasePointProduct product = productCache.get(productId);
        if(product!=null){
            return product;
        }
        product = pointProductMng.findById(productId);
        return product;
    }
    private void writeJson(String result, HttpServletResponse response) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result);
            response.getWriter().flush();
            response.getWriter().close();
            response.flushBuffer();
        } catch (IOException e) {
            logger.error("异常:{}", new Object[]{e.getMessage()});
            logger.error(e.getMessage(), e);
        }
    }

    private PointActivity getPointActivity(String activityCode) {
        PointActivity activity = activityCache.get(activityCode);
        if (activity == null) {

            try {
                activity = this.activityMng.queryStatus(activityCode);
            } catch (IllegalArgumentException e) {
                logger.error("查询活动出现异常:{},activityCode:{}", new Object[]{e.getMessage(), activityCode});
                activity = new PointActivity();
                activity.setName(e.getMessage());
                activity.setStatus(false);
            }
            activityCache.put(activityCode, activity);
        }
        activity = activityCache.get(activityCode);
        return activity;
    }

    @RequestMapping(value = "/queryActivity.jhtml", method = RequestMethod.POST)
    public void queryActivity(String openId, String activityCode, HttpServletRequest request, ModelMap model, HttpServletResponse response) {

        PointActivity activity = this.getPointActivity(activityCode);

        try {
            String result = JSONParser.toJSONString(activity);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(result);
            response.getWriter().flush();
            response.getWriter().close();
            response.flushBuffer();
        } catch (IOException e) {
            logger.error("查询活动出现异常:{},activityCode:{}", new Object[]{e.getMessage(), activityCode});
            logger.error(e.getMessage(), e);
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
        FrontUtils.frontData(request, model, site);
        UnifiedUser unifiedUser=null;
        if(orderExchengeVo.getOpenId()!=null){
            unifiedUser = pointUserMng.findByOpenId(orderExchengeVo.getOpenId());
        }
        //用户没有登录
        if(unifiedUser==null){
            retval.put("status","false");
            retval.put("message","用户没有登录");
            writeSaveResult(response, retval);
            return ;
        }

        // 查询当前用户当前订单否真的秒杀成功
        PointOrder order = pointOrderMng.findById(orderExchengeVo.getOrderId());
        if(order==null||order.getUserId()!=unifiedUser.getId().longValue()){
            retval.put("status","false");
            retval.put("message","非法请求");
            writeSaveResult(response, retval);
            return ;
        }

        //将错误信息返回
        if(retval.get("status").equals("false")){
            writeSaveResult(response, retval);
            return ;
        }
        //保存用户的快递信息
        Express express=expressMng.findByOrderId(order.getId());
        BeanUtils.copyProperties(orderExchengeVo, express);
        expressMng.update(express);
        logger.info("orderExchengeVo:" + orderExchengeVo.toString());
        retval.put("message", "保存成功");
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
