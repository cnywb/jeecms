/**
 * 
 */
package com.jeecms.point.dao.point.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.point.dao.point.PointRuleDao;
import com.jeecms.point.entity.PointRule;
import com.jeecms.point.web.query.QueryVo;

/**
 * @author wanglijun
 *
 */
@Repository
public class PointRuleDaoImpl extends HibernateBaseDao<PointRule,Long> implements PointRuleDao {

	@Override
	protected Class<PointRule> getEntityClass() {
		return PointRule.class;
	}

	
	
	
	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.point.PointRuleDao#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PointRule> findAll() {
		String hql="from PointRule  order by showOrder";
		Finder finder = Finder.create(hql);
		return this.find(finder);
	}




	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.point.PointRuleDao#save(com.jeecms.point.entity.PointRule)
	 */
	@Override
	public PointRule save(PointRule pointRule) {
		this.getSession().save(pointRule);
		return pointRule;
	}
	
	
	 

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.point.PointRuleDao#update(com.jeecms.point.entity.PointRule)
	 */
	@Override
	public PointRule update(PointRule pointRule) {
		this.getSession().update(pointRule);
		return pointRule;
	}
	
	

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.point.PointRuleDao#queryPointRule(com.jeecms.point.web.query.QueryVo, java.lang.String, java.lang.Integer)
	 */
	@Override
	public Pagination queryPointRule(QueryVo queryVo, String pointRuleName,
			Integer pointType) {
		String hql="select t   from   PointRule t where 1=1 ";
		Finder finder = Finder.create(hql);
		//添加
		if(StringUtils.isNotEmpty(pointRuleName)){
			finder.append(" and pointRuleName like :pointRuleName ");
			finder.setParam("pointRuleName","%"+pointRuleName+"%");
		}
		
		if(pointType!=null&&pointType>=0){
			finder.append(" and pointType=:pointType ");
			finder.setParam("pointType",pointType);
		}
		finder.append(" order by showOrder "+queryVo.getOrder());
		Pagination pagination= this.find(finder, queryVo.getPage(), queryVo.getRows());
		return pagination;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.point.PointRuleDao#remove(java.lang.Integer)
	 */
	@Override
	public PointRule remove(Long id) {
		PointRule pointRule= super.get(id);
		if(pointRule!=null){
			this.getSession().delete(pointRule);
		}
		return pointRule;
		
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.point.PointRuleDao#findById(java.lang.Long)
	 */
	@Override
	public PointRule findById(Long id) {		 
		return super.get(id);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.point.PointRuleDao#checkPointRuleNo(java.lang.String)
	 */
	@Override
	public PointRule findByPointRuleNo(String pointRuleNo) {
		String hql="from PointRule  where pointRuleNo=:pointRuleNo";
		Finder finder = Finder.create(hql);
		finder.setParam("pointRuleNo", pointRuleNo);
		@SuppressWarnings("unchecked")
		List<PointRule>  list=this.find(finder);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.point.PointRuleDao#findByPointRuleNo(java.lang.Long, java.lang.String)
	 */
	@Override
	public PointRule findByPointRuleNo(Long id, String pointRuleNo) {
		String hql="from PointRule  where  id!=:id  and pointRuleNo=:pointRuleNo";
		Finder finder = Finder.create(hql);
		finder.setParam("pointRuleNo", pointRuleNo);
		finder.setParam("id", id);
		@SuppressWarnings("unchecked")
		List<PointRule>  list=this.find(finder);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}
	
	
}
