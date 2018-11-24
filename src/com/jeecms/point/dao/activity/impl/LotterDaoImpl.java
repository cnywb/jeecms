package com.jeecms.point.dao.activity.impl;

import java.util.Date;
import java.util.List;

import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;
import com.jeecms.point.dao.activity.LotteryDao;
import com.jeecms.point.entity.PointLotteryHistory;
import com.jeecms.point.web.query.QueryVo;

/**
 * Created by kui.yang on 15/5/30.
 *
 */
public class LotterDaoImpl  extends HibernateBaseDao<PointLotteryHistory,Long> implements LotteryDao {

    /**
     * 查询用户历史抽奖记录，返回当天已经抽奖的次数
     *
     * @param code   活动代码
     * @param userId 用户ID
     * @return
     */
    @Override
    public int countUserLottery(String code, Long userId) {
        String hql = "FROM PointLotteryHistory p where (1=1) ";
        Finder finder = Finder.create(hql);
        finder.append("and p.acitvityCode=:acitvityCode ");
        finder.setParam("acitvityCode", code);
        finder.append("and p.userId=:userId ");
        finder.setParam("userId", userId);
        finder.append("and p.lotteryDate between :startDate and :endDate");

        String now = DateUtils.format(new Date(),"yyyy-MM-dd");
        String startTime = now +" 00:00:00";
        String endTime = now+" 23:59:59";

        finder.setParam("startDate", DateUtils.getDateByFormat(startTime,"yyyy-MM-dd HH:ss:ss"));
        finder.setParam("endDate",DateUtils.getDateByFormat(endTime,"yyyy-MM-dd HH:ss:ss"));
        return this.countQueryResult(finder);
    }
    
    
    
    
 




	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.activity.LotteryDao#countUserLotteryByopenId(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public int countUserLotteryByopenId(String code, String vin, String openId) {
		 	String hql = "FROM PointLotteryHistory p where (1=1) ";
	        Finder finder = Finder.create(hql);
	        finder.append("and p.acitvityCode=:acitvityCode ");
	        finder.setParam("acitvityCode", code);
	        finder.append("and p.vin=:vin ");
	        finder.setParam("vin",vin);
	        finder.append("and p.openId=:openId ");
	        finder.setParam("openId", openId);
	        return this.countQueryResult(finder); 	
	}





	@Override
    public void save(PointLotteryHistory history) {
        history.setCreatedDate(new Date());
        this.getSession().save(history);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<PointLotteryHistory> queryLotteryResult(String activityCode) {
        String hql = "FROM PointLotteryHistory p where (1=1) ";
        Finder finder = Finder.create(hql);
        finder.append("and p.acitvityCode=:acitvityCode ");
        finder.setParam("acitvityCode", activityCode);
        finder.append("and p.isLotteried=:isLotteried ");
        finder.setParam("isLotteried", true);
        return this.find(finder);
    }

    @Override
    public boolean isLotterid(String activityCode, Long userId, Long historyId) {
        String hql = "FROM PointLotteryHistory p where (1=1) ";
        Finder finder = Finder.create(hql);
        finder.append("and p.acitvityCode=:acitvityCode ");
        finder.setParam("acitvityCode", activityCode);
        finder.append("and p.isLotteried=:isLotteried ");
        finder.setParam("isLotteried", true);
        finder.append("and p.userId=:userId ");
        finder.setParam("userId", userId);
        finder.append("and p.id=:id ");
        finder.setParam("id", historyId);
        int retval  = this.countQueryResult(finder);
        return retval>0;
    }

    /**
     * 获得Dao对于的实体类
     *
     * @return
     */
    @Override
    protected Class<PointLotteryHistory> getEntityClass() {
        return PointLotteryHistory.class;
    }

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.activity.LotteryDao#getPage(com.jeecms.point.web.query.QueryVo, java.lang.Long)
	 */
	@Override
	public Pagination getPage(QueryVo queryVo, String userName,Boolean isLotteried) {
		String hql="select p   from   PointLotteryHistory p where 1=1 ";
		Finder finder = Finder.create(hql);
		//添加
		if(userName!=null){
			finder.append(" and p.userName like:userName ");
			finder.setParam("userName","%"+userName+"%");
		}
		if(isLotteried!=null){
			finder.append(" and p.isLotteried=:isLotteried ");
			finder.setParam("isLotteried",isLotteried);
		}
		finder.append(" order by lotteryDate "+queryVo.getOrder());
		Pagination pagination= this.find(finder, queryVo.getPage(), queryVo.getRows());
		return pagination;
	}

}
