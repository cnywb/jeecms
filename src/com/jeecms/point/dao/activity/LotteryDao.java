package com.jeecms.point.dao.activity;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.entity.PointLotteryHistory;
import com.jeecms.point.web.query.QueryVo;

import java.util.List;

/**
 * Created by kui.yang on 15/5/30.
 */
public interface LotteryDao {

    /**
     * 查询用户历史抽奖记录，返回当天已经抽奖的次数
     * @param code 活动代码
     * @param userId 用户ID
     * @return
     */
    int countUserLottery(String code,Long userId);
 
    
    /***
     * 刮刮奖查询查询用户历史抽奖记录，返回当天已经抽奖的次数
     * @param code 活动代码
     * @param vin 车辆VIN
     * @param openId 微信openId
     * @return int
     */
    public  int countUserLotteryByopenId(String code,String vin,String openId);

    void save(PointLotteryHistory history);

    List<PointLotteryHistory> queryLotteryResult(String activityCode);

    boolean isLotterid(String activityCode,Long userId,Long historyId);
    /***
	 * 
	 * @param queryVo 查询参数
	 * @param productName 商品名称
	 * @param productCategory 商品类型
	 * @return Pagination
	 */
	public Pagination getPage(QueryVo queryVo,String userName,Boolean isLotteried);
}
