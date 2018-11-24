/**
 * 
 */
package com.jeecms.point.manager.point.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.dao.point.PointRuleDao;
import com.jeecms.point.entity.PointRule;
import com.jeecms.point.manager.point.PointRuleMng;
import com.jeecms.point.web.query.QueryVo;

/**
 * 积分规则实现类
 * @author wanglijun
 *
 */
@Service
@Transactional
public class PointRuleMngImpl implements PointRuleMng {
	
	@Autowired
	private PointRuleDao pointRuleDao;
	
	@Transactional(readOnly=true)
	public List<PointRule> findAll(){
		return this.pointRuleDao.findAll();
	}

	@Override
	@Transactional
	public PointRule save(PointRule pointRule) {
		return pointRuleDao.save(pointRule);
	}
	
	

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointRuleMng#findByPointRuleNo(java.lang.String)
	 */
	@Override
	public PointRule findByPointRuleNo(String pointRuleNo) {
		return pointRuleDao.findByPointRuleNo(pointRuleNo);
	}



	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointRuleMng#getPage(com.jeecms.point.web.query.QueryVo, java.lang.String, java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly=true)
	public Pagination getPage(QueryVo queryVo, String pointRuleName,Integer pointType) {
		return pointRuleDao.queryPointRule(queryVo, pointRuleName, pointType);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointRuleMng#remove(java.lang.Long)
	 */
	@Override
	public PointRule remove(Long id) {
		return this.pointRuleDao.remove(id); 
	}
	
	@Override
	public PointRule update(PointRule pointRule) {
		return this.pointRuleDao.update(pointRule);
	}


	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointRuleMng#findById(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly=true)
	public PointRule findById(Long id) {		 
		return this.pointRuleDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointRuleMng#checkPointRuleNo(java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public boolean checkPointRuleNo(String pointRuleNo) {
		PointRule pointRule=this.pointRuleDao.findByPointRuleNo(pointRuleNo);
		if(pointRule==null){
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.point.PointRuleMng#checkPointRuleNo(java.lang.Long, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public boolean checkPointRuleNo(Long id, String pointRuleNo) {
		PointRule pointRule=this.pointRuleDao.findByPointRuleNo(id,pointRuleNo);
		if(pointRule==null){
			return true;
		}
		return false;
	}
}
