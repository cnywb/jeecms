package com.jeecms.point.dao.product.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.point.dao.product.ActivityProductDao;
import com.jeecms.point.entity.ActivityProduct;
import com.jeecms.point.entity.base.BasePointProduct;
import com.jeecms.point.web.query.QueryVo;

@Repository
public class ActivityProductDaoImpl extends HibernateBaseDao<ActivityProduct, Long> implements ActivityProductDao{
	
	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.ActivityProductDao#save(com.jeecms.point.entity.ActivityProduct)
	 */
	@Override
	public ActivityProduct save(ActivityProduct activityProduct) {
		activityProduct.setCreatedId(1L);
		activityProduct.setCreatedDate(new Date());
		activityProduct.setUpdatedId(1L);
		activityProduct.setUpdatedDate(new Date());
		getSession().save(activityProduct);
		return activityProduct;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.ActivityProductDao#remove(java.lang.Long)
	 */
	@Override
	public ActivityProduct remove(Long id) {
		ActivityProduct activityProduct= super.get(id);
		if(activityProduct!=null){
			this.getSession().delete(activityProduct);
		}
		return activityProduct;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.ActivityProductDao#findById(java.lang.Long)
	 */
	@Override
	public ActivityProduct findById(Long id) {
		ActivityProduct activityProduct= super.get(id);
		return activityProduct;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.ActivityProductDao#getProducts(java.lang.Long)
	 */
	@Override
	public List<BasePointProduct> getProducts(Long activityId) {
		String hql = "from ActivityProduct where activityId = "+activityId;
		Query query = this.getSession().createQuery(hql);
		List<ActivityProduct> list = query.list();
		List<BasePointProduct> list2 = new ArrayList<BasePointProduct>();
		for(ActivityProduct a:list){
			list2.add(a.getProduct());
		}
		return list2;
	}

	@Override
	protected Class<ActivityProduct> getEntityClass() {
		return ActivityProduct.class;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.ActivityProductDao#getActivityProduct(java.lang.Long, java.lang.Long)
	 */
	@Override
	public ActivityProduct getActivityProduct(Long productId, Long activityId) {
		String hql = "from ActivityProduct where activityId = "+activityId+" and productId = "+productId;
		Query query = this.getSession().createQuery(hql);
		List<ActivityProduct> list = query.list();
		for(ActivityProduct activityProduct:list){
			return activityProduct;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.ActivityProductDao#getActivityProducts(java.lang.Long)
	 */
	@Override
	public List<ActivityProduct> getActivityProducts(Long activityId) {
		String hql = "from ActivityProduct a left join fetch a.product BasePointProduct left join fetch a.activity BasePointActivity where a.activityId = "+activityId+" and a.product.status = 1 order by a.product.showOrder asc";
		Query query = this.getSession().createQuery(hql);
		List<ActivityProduct> list = query.list();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.ActivityProductDao#getPageOnline(java.lang.Long)
	 */
	@Override
	public Pagination getPageOnline(QueryVo queryVo) {
		String hql="select product   from   ActivityProduct a  where a.activity.type = '3' and (a.product.channel=0 or a.product.channel=2) order by a.product.showOrder";
		Finder finder = Finder.create(hql);
		Pagination pagination= this.find(finder, queryVo.getPage(), queryVo.getRows());
		return pagination;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.ActivityProductDao#getActivityProducts()
	 */
	@Override
	public List<ActivityProduct> getActivityProducts() {
		String hql = "from ActivityProduct a left join fetch a.product BasePointProduct left join fetch a.activity BasePointActivity where a.product.status = 1 and a.activity.type != null order by a.activity.type";
		Query query = this.getSession().createQuery(hql);
		List<ActivityProduct> list = query.list();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.ActivityProductDao#getActivityProductsByproductId(java.lang.Long)
	 */
	@Override
	public List<ActivityProduct> getActivityProductsByproductId(Long productId) {
		String hql = "from ActivityProduct a left join fetch a.product BasePointProduct left join fetch a.activity BasePointActivity where a.productId = "+productId+" order by a.product.showOrder asc";
		Query query = this.getSession().createQuery(hql);
		List<ActivityProduct> list = query.list();
		return list;
	}
	
}
