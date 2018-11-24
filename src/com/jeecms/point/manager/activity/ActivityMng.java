package com.jeecms.point.manager.activity;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.entity.GuaGuaLotteryResult;
import com.jeecms.point.entity.PointActivity;
import com.jeecms.point.web.query.QueryVo;

/**
 * Created by kui.yang on 15/5/26.
 * 活动管理
 */
public interface ActivityMng {

    /**
     * 分页查询活动内容
     *
     * @param queryVo 查询条件
     * @return 活动记录
     */
    Pagination queryPagination(QueryVo queryVo);

    void save(PointActivity activity);

    void deleteById(Long id);

    PointActivity getById(Long id);
    /**
     * 查询指定的活动代码是否可以参与活动
     * 1.判断状态是否为启动状态
     * 2.判断当前时间是否在有效期内
    * */
    PointActivity queryStatus(String code);

    /**
     * 查询用户本次活动可以抽奖的次数
     * @param code 活动代码
     * @param userId 用户ID
     * @return
     */
    int queryLotteryCount(String code,Long userId);
    /***
     * 查询用户本次活动可以抽奖的次数
     * @param code  
     * @param openId
     * @return
     */
    public int queryLotteryCountByOpenId(String code,String vin,String openId);
    
    /***
     * @param code 活动代码
     * @return  PointActivity
     */
    public PointActivity findByCode(String code);
    
    /**
     * 检查活动的的状态
     * @param PointActivity
     * @return
     */
    public  GuaGuaLotteryResult checkStatus(PointActivity  pointActivity,String code); 
}
