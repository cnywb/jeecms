package com.jeecms.cms.dao.main.impl.campaign.luckydraw;



import com.jeecms.cms.dao.campaign.lucydraw.LuckyDrawLogDao;
import com.jeecms.cms.entity.campaign.luckydraw.LuckyDrawLog;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.util.ValidateUtils;


public class LuckyDrawLogDaoImpl extends HibernateBaseDao<LuckyDrawLog, Long>
		implements LuckyDrawLogDao {

	@Override
	protected Class<LuckyDrawLog> getEntityClass() {
		// TODO Auto-generated method stub
		return LuckyDrawLog.class;
	}

	
	public int  getTotalCountByLuckyDrawIdAndCreaterId(long luckyDrawIdId,Integer createrId){
		Finder f = Finder.create("from LuckyDrawLog bean where bean.luckyDraw.id=:luckyDrawIdId and bean.creater.id=:createrId");
		f.setParam("luckyDrawIdId",luckyDrawIdId);
		f.setParam("createrId",createrId);
		return 	countQueryResult(f);
	}
	
	
	public long add(LuckyDrawLog t){
		getSession().save(t);
		return t.getId();
	}
}
