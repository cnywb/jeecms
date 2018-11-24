package com.jeecms.point.manager.lottery;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.entity.GuaGuaLotteryResult;
import com.jeecms.point.entity.PointActivity;
import com.jeecms.point.web.query.QueryVo;

/**
 * Created by kui.yang on 15/5/23.
 * 抽奖逻辑接口
 */
public interface LotteryMng {

    /**
     * 执行抽奖过程,默认已经在外层校验过用户shi'是否有 资格参与抽奖
     *
     * * @return 抽奖结果
     */
    public String doLottery(String activityCode,Long userId);
    
    /***
     * 执行刮刮奖信息
     * @param activity 活动信息
     * @param code 活动Code
     * @param vin VIN码
     * @param openId 微信openId
     * @return GuaGuaLotteryResult
     */
    public GuaGuaLotteryResult doGuaGuaLottery(PointActivity activity,String code,String vin,String openId);

    /**
     * 查询某个活动的中奖结果
     * @param activityCode
     */
    public List<String> queryLotteryResult(String activityCode);

    /**
     * 查询 某个活动下，用户是否真的中了对应的产品
     * @param activityCode
     * @param userId
     * @param historyId
     * @return
     */
    public boolean isLotteried(String activityCode,Long userId,Long historyId);

    public Pagination getPage(QueryVo queryVo, String userName,Boolean isLotteried);
}
