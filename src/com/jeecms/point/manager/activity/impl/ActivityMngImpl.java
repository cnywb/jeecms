package com.jeecms.point.manager.activity.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.dao.activity.ActivityDao;
import com.jeecms.point.dao.activity.LotteryDao;
import com.jeecms.point.entity.GuaGuaLotteryResult;
import com.jeecms.point.entity.PointActivity;
import com.jeecms.point.manager.activity.ActivityMng;
import com.jeecms.point.manager.product.ActivityProductMng;
import com.jeecms.point.web.query.QueryVo;

/**
 * Created by kui.yang on 15/5/26.
 * 活动管理
 */
@Service
public class ActivityMngImpl implements ActivityMng {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private LotteryDao lotteryDao;
    @Autowired
    private ActivityProductMng activityProductMng;

    /**
     * 分页查询活动内容
     *
     * @param queryVo 查询条件
     * @return 活动记录
     */
    @Override
    public Pagination queryPagination(QueryVo queryVo) {
        return this.activityDao.queryPagination(queryVo);
    }

    @Override
    @Transactional
    public void save(PointActivity activity) {
        this.activityDao.save(activity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        this.activityDao.deleteById(id);
        this.activityProductMng.deleteProducts(id);
    }

    @Override
    public PointActivity getById(Long id) {
        return this.activityDao.getById(id);
    }

    /**
     * 查询指定的活动代码是否可以参与活动
     * 1.判断状态是否为启动状态
     * 2.判断当前时间是否在有效期内
     *
     * @param code 活动代码
     */
    @Override
    public PointActivity queryStatus(String code) {
        PointActivity activity = this.activityDao.getByCode(code);
        if(activity==null){
            logger.error("false:活动不存在,code:{}",code);
           throw new IllegalArgumentException("活动不存在");
        }

        if (!activity.getStatus()) {
            logger.error("false:活动未启用,code:{}",code);
            throw new IllegalArgumentException("活动未启用");
        }
        Date now = new Date();
        if(now.after(activity.getEndTime())||now.before(activity.getStartTime())){
            logger.error("false:活动尚未开始或已经结束,code:{}",code);
            throw new IllegalArgumentException("活动尚未开始或已经结束");
        }

        return activity;
    }
    
    
    
    
    

    /* (non-Javadoc)
	 * @see com.jeecms.point.manager.activity.ActivityMng#findByCode(java.lang.String)
	 */
	@Override
	public PointActivity findByCode(String code) {
		return this.activityDao.getByCode(code);	
	}
	
	
	

	

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.activity.ActivityMng#checkStatus(java.lang.String)
	 */
	@Override
	public GuaGuaLotteryResult checkStatus(PointActivity  activity,String code) {
		
	    boolean won=true;
	    Map<String,String> messages=new HashMap<String,String>();
	    //未创建活动
		if(activity==null){
			messages.put("001","此活动不存");
			won=false;
		}
		//活动未启用
	    if (!activity.getStatus()) {
	    	messages.put("002","活动未启用");
			won=false;
        }
	    
	    Date now = new Date();
	    //判断活动是否已结束
        if(now.after(activity.getEndTime())){
            messages.put("003","活动尚已经结束");
			won=false;
        }
        //判断活动是否已开始
        if(now.before(activity.getStartTime())){
            messages.put("004","活动尚未开始");
			won=false;
        }
		return new GuaGuaLotteryResult(code,won,messages);
	}
	
	
	

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.activity.ActivityMng#queryLotteryCountByOpenId(java.lang.String,java.lang.String,java.lang.String)
	 */
	@Override
	public int queryLotteryCountByOpenId(String code,String vin,String openId) {
		return this.lotteryDao.countUserLotteryByopenId(code, vin, openId);
	}

	/**
     * 查询用户本次活动可以抽奖的次数
     *
     * @param code   活动代码
     * @param userId 用户ID
     * @return
     */
    @Override
    public int queryLotteryCount(String code, Long userId) {
        return this.lotteryDao.countUserLottery(code,userId);
    }
    
    
    
}
