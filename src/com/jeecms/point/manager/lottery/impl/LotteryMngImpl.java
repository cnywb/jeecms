package com.jeecms.point.manager.lottery.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.point.constant.Channel;
import com.jeecms.point.constant.PayoutType;
import com.jeecms.point.constant.RecordType;
import com.jeecms.point.constant.RuleConstant;
import com.jeecms.point.dao.activity.LotteryDao;
import com.jeecms.point.dao.product.ActivityProductDao;
import com.jeecms.point.dao.product.PointProductDao;
import com.jeecms.point.entity.GuaGuaLotteryResult;
import com.jeecms.point.entity.PointActivity;
import com.jeecms.point.entity.PointIncome;
import com.jeecms.point.entity.PointLotteryHistory;
import com.jeecms.point.entity.base.BasePointProduct;
import com.jeecms.point.manager.activity.ActivityMng;
import com.jeecms.point.manager.lottery.LotteryMng;
import com.jeecms.point.manager.point.PointIncomeMng;
import com.jeecms.point.manager.point.PointUserMng;
import com.jeecms.point.web.query.QueryVo;

/**
 * Created by kui.yang on 15/5/23.
 * 抽奖管理逻辑实现
 */
public class LotteryMngImpl implements LotteryMng {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ActivityMng activityMng;

    @Autowired
    private PointProductDao pointProductDao;
    @Autowired
    private LotteryDao lotteryDao;
    @Autowired
    private ActivityProductDao activityProductDao;
    @Autowired
    private PointUserMng pointUserMng;
    @Autowired
    private UnifiedUserMng unifiedUserMng;

    @Autowired
    private PointIncomeMng pointIncomeMng;
    
    
    
  //保存抽奖记录
    private PointLotteryHistory saveGuaGuaLotteryHistory(String vin,String openId,PointActivity activity,GuaGuaLotteryResult result,String resultDesc,BasePointProduct product) {
        
       
        //保存用户抽奖记录
        PointLotteryHistory history = new PointLotteryHistory();
        history.setVin(vin);
        history.setOpenId(openId);
        history.setAcitvityCode(activity.getCode());
        history.setLotteryDate(new Date());
        history.setUpdatedDate(new Date());
        history.setLotteryResult(resultDesc);
        if(result.isWon()){
            history.setIsLotteried(true);
        } else {
            history.setIsLotteried(false);
        }
        if(product!=null){
        	history.setProductId(product.getId());
        }
        lotteryDao.save(history);
        return history;
    }

    /* (non-Javadoc)
	 * @see com.jeecms.point.manager.lottery.LotteryMng#doGuaGuaLottery(com.jeecms.point.entity.PointActivity, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public  GuaGuaLotteryResult doGuaGuaLottery(PointActivity activity,String code, String vin, String openId) {
		List<BasePointProduct> productList = activityProductDao.getProducts(activity.getId()); 
		List<BasePointProduct> productList2 = new ArrayList<BasePointProduct>();
		GuaGuaLotteryResult  guaGuaLotteryResult=new GuaGuaLotteryResult(code,true);
		/*
         * 1.获取奖品项及数量，生成每种奖品中奖概率区间
         * 2.获取整体活动的中奖概率
         */
		for(BasePointProduct product:productList){
			  //只选择有库存的商品，根据每种商品的库存数量生成一个全集
            //获取每个月剩余的库存
            if (product.getMonthLeftLotteryStockNum() > 0) {
                for (int i = 0; i < product.getMonthLeftLotteryStockNum(); i++) {
                    productList2.add(product);//有多少剩余库存，就加多少个商品
                }
            }
		}
		//检查是否有产品数据
		if (productList2.isEmpty()) {
			guaGuaLotteryResult.addMessage("010","未中奖");
			guaGuaLotteryResult.setWon(false);//设置为未中奖
			//保存抽奖记录
			this.saveGuaGuaLotteryHistory(vin, openId, activity, guaGuaLotteryResult, "奖品列表为空", null);
			logger.info("当前活动对应的有库存的商品列表为空，无法进行抽奖，直接返回未中奖！");
			return guaGuaLotteryResult;
        }
	 	int totalProbability =activity.getProbability1();//总的中奖概率分母值
        int probability = activity.getProbability2();//中奖概率分子值
        //随机生成总数为totalProbability的数组，其中有奖的数组为中奖的概率值
        //如中奖概率为2%，则100个数组里面只有2个中奖
        int[] probabiArray = new int[totalProbability];
        for (int j = 0; j < probability; j++) {
            probabiArray[j] = 1;//有奖
        }
        for (int i = probability; i < totalProbability; i++) {
            probabiArray[i] = 0;//无奖
        }
        //随机生成一个值（范围在0-totalProbability之间），然后取probabiArray对应下标的值，判断是否中奖
        int index = RandomUtils.nextInt(totalProbability);
        int retval = probabiArray[index];
        if (retval == 1) { //中奖
            //随机获取奖品，从全集商品里面获取一个商品
            int productIndex = RandomUtils.nextInt(productList2.size());
            Collections.shuffle(productList2);//打乱顺序
            BasePointProduct product = productList2.get(productIndex);
            //减库存
            Long productId=product.getId();
            product = this.pointProductDao.lotteryProductsDecreaseByGuaguaCard(product.getId());
            if(product==null){
                logger.info("抽奖对应的商品库存不足，直接返回未中奖,id:{},vin:{},opendId:{}",new Object[]{productId,vin,openId});
                guaGuaLotteryResult.addMessage("011","未中奖");
    			guaGuaLotteryResult.setWon(false);//设置为未中奖
    			this.saveGuaGuaLotteryHistory(vin, openId, activity,guaGuaLotteryResult,"抽奖对应的商品库存不足,未中奖",product);
                return guaGuaLotteryResult;
            }
            logger.info("vin:{},openId:{},code:{},中了:{}", new Object[]{vin,openId,code,product.getTitle()});
            guaGuaLotteryResult.addMessage("000","恭喜您，中奖了");
			guaGuaLotteryResult.setWon(true);//设置为中奖
			guaGuaLotteryResult.setProductId(product.getId());//ID
			guaGuaLotteryResult.setProductNo(product.getProductNo());//代码
			guaGuaLotteryResult.setProductTitle(product.getTitle());
			guaGuaLotteryResult.setImageMiddleURL(product.getImageMiddleURL());
			guaGuaLotteryResult.setImageLargeURL(product.getImageLargeURL());
			//保存记录 
			this.saveGuaGuaLotteryHistory(vin, openId, activity, guaGuaLotteryResult, "抽中了", product);
            return guaGuaLotteryResult;
            
        } else {
        	  logger.info("vin:{},openId:{},code:{},未抽中奖", new Object[]{vin,openId,code});
              guaGuaLotteryResult.addMessage("012","未中奖");
  		      guaGuaLotteryResult.setWon(false);
  			  //保存记录  
  		      this.saveGuaGuaLotteryHistory(vin, openId, activity, guaGuaLotteryResult, "未抽中奖", null);
              return guaGuaLotteryResult;
        }
	}
	
	
	
	/**
     * 执行抽奖过程,默认已经在外层校验过用户是否有 资格参与抽奖
     * @return 抽奖结果
     */
    @Override
    @Transactional
    public String doLottery(String activityCode, Long userId) {
        PointActivity activity = this.activityMng.queryStatus(activityCode);
        int  limitForDay = activity.getLotteryLimitForDay();//每天限制次数
        UnifiedUser unifiedUser=unifiedUserMng.findById(userId.intValue());
        /*
         * 1.获取奖品项及数量，生成每种奖品中奖概率区间
         * 2.获取整体活动的中奖概率
        */
        List<BasePointProduct> productList = activityProductDao.getProducts(activity.getId());
        List<BasePointProduct> productList2 = new ArrayList<BasePointProduct>();
        for (BasePointProduct product : productList) {
            //只选择有库存的商品，根据每种商品的库存数量生成一个全集
            //获取每个月剩余的库存
            if (product.getMonthLeftLotteryStockNum() > 0) {
                for (int i = 0; i < product.getMonthLeftLotteryStockNum(); i++) {
                    productList2.add(product);//有多少剩余库存，就加多少个商品
                }
            }
        }

        if (productList2.isEmpty()) {
        	 int count = this.activityMng.queryLotteryCount(activityCode, unifiedUser.getId().longValue());
             this.saveLotteryHistory(activity, unifiedUser, "未中奖", null);
            logger.info("当前活动对应的有库存的商品列表为空，无法进行抽奖，直接返回未中奖！");
            return "true:-1:未中奖:"+(limitForDay-count-1);
        }
        int totalProbability =activity.getProbability1();//总的中奖概率分母值
        int probability = activity.getProbability2();//中奖概率分子值
        //随机生成总数为totalProbability的数组，其中有奖的数组为中奖的概率值
        //如中奖概率为2%，则100个数组里面只有2个中奖
        int[] probabiArray = new int[totalProbability];
        for (int j = 0; j < probability; j++) {
            probabiArray[j] = 1;//有奖
        }
        for (int i = probability; i < totalProbability; i++) {
            probabiArray[i] = 0;//无奖
        }

        //随机生成一个值（范围在0-totalProbability之间），然后取probabiArray对应下标的值，判断是否中奖
        int index = RandomUtils.nextInt(totalProbability);
        int retval = probabiArray[index];
        if (retval == 1) { //中奖
            //随机获取奖品，从全集商品里面获取一个商品
            int productIndex = RandomUtils.nextInt(productList2.size());
            Collections.shuffle(productList2);//打乱顺序
            BasePointProduct product = productList2.get(productIndex);
            Long productid = product.getId();
            //减库存
            product =  this.pointProductDao.lotteryProductsDecrease(product.getId());
            if(product==null){
                logger.info("抽奖对应的商品库存不足，直接返回未中奖,productId:{}",productid);
                int count = this.activityMng.queryLotteryCount(activityCode, unifiedUser.getId().longValue());
                this.saveLotteryHistory(activity, unifiedUser, "未中奖", null);
                return "true:-1:未中奖:"+(limitForDay-count-1);
            }
            String historyId = "";
            //判断是否为再抽一次，如果是再抽一次则减库存后直接返回结果，不减用户积分
            if (!"再抽一次".equals(product.getTitle())) {
                historyId =saveLotteryHistory(activity,unifiedUser,product.getTitle(),product).getId()+"";
            }

            logger.info("user:{},code:{},中了:{}", new Object[]{userId, activityCode, product.getTitle()});
            return "true:"+product.getProductNo()+":"+product.getTitle()+":"+product.getId()+":"+historyId;
        } else {
        	 int count = this.activityMng.queryLotteryCount(activityCode, unifiedUser.getId().longValue());
            this.saveLotteryHistory(activity,unifiedUser,"未中奖",null);
            return "true:-1:未中奖:"+(limitForDay-count-1);
        }
    }
    //保存抽奖记录
    private PointLotteryHistory saveLotteryHistory( PointActivity activity ,UnifiedUser unifiedUser, String lotteryResult,BasePointProduct product) {
        Integer userId = unifiedUser.getId();
        //减去用户的积分
        pointUserMng.updateUserPoint(unifiedUser.getId(), activity.getCostPoints().longValue());
        this.savePointCost(userId.longValue(), activity.getCostPoints().longValue());
        //保存用户抽奖记录
        PointLotteryHistory history = new PointLotteryHistory();
        history.setUserId(userId.longValue());
        history.setAcitvityCode(activity.getCode());
        history.setLotteryDate(new Date());
        history.setUserName(unifiedUser.getUsername());
        history.setLotteryResult(lotteryResult);
        if(product!=null){
            history.setIsLotteried(true);
            history.setProductId(product.getId());
        } else {
            history.setIsLotteried(false);
        }
        lotteryDao.save(history);
        return history;
    }


    //添加消费记录
    private void savePointCost(Long userId,Long pointNum){
        Date createdDate = new Date();
        PointIncome pointPayout=new PointIncome();
        pointPayout.setPointNum(-pointNum);
        pointPayout.setPointType(PayoutType.LOTTERY);
        pointPayout.setPointRuleName("抽奖");
        pointPayout.setChannel(Channel.PC);
        pointPayout.setIncomeDate(createdDate);
        pointPayout.setPointRuleNo(RuleConstant.LOTTERY_PAY_POINT);
        pointPayout.setCreatedId(userId);
        pointPayout.setCreatedDate(createdDate);
        pointPayout.setUpdatedId(userId);
        pointPayout.setUpdatedDate(createdDate);
        pointPayout.setUserId(userId);
        pointPayout.setRecordType(RecordType.PAYOUT);
        pointIncomeMng.save(pointPayout);
    }

    /**
     * 查询某个活动的中奖结果
     *
     * @param activityCode
     */
    @Override
    public List<String> queryLotteryResult(String activityCode) {
        List<String> names = new ArrayList<String>();
        List<PointLotteryHistory> retval= this.lotteryDao.queryLotteryResult(activityCode);
        for (PointLotteryHistory history : retval) {
            names.add(history.getUserName());
        }
        return names;
    }


    /**
     * 查询 某个活动下，用户是否真的中了对应的产品
     *
     * @param activityCode
     * @param userId
     * @param historyId
     * @return
     */
    @Override
    public boolean isLotteried(String activityCode, Long userId, Long historyId) {
        return this.lotteryDao.isLotterid(activityCode,userId,historyId);
    }
    @Override
    public Pagination getPage(QueryVo queryVo, String userName,Boolean isLotteried){
    	return this.lotteryDao.getPage(queryVo, userName,isLotteried);
    }

}
