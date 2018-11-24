package com.jeecms.point.manager.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.dao.product.ActivityProductDao;
import com.jeecms.point.entity.ActivityProduct;
import com.jeecms.point.entity.base.BasePointProduct;
import com.jeecms.point.manager.product.ActivityProductMng;
import com.jeecms.point.web.query.QueryVo;
@Service
@Transactional
public class ActivityProductMngImpl implements ActivityProductMng{
	@Autowired
	private ActivityProductDao activityProductDao;

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.ActivityProductMng#save(com.jeecms.point.entity.ActivityProduct)
	 */
	@Override
	public ActivityProduct save(ActivityProduct activityProduct) {
		return activityProductDao.save(activityProduct);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.ActivityProductMng#remove(java.lang.Long)
	 */
	@Override
	public ActivityProduct remove(Long id) {
		return activityProductDao.remove(id);
	}

	/**
	 * 删除活动对应的所有商品列表
	 *
	 * @param activityId
	 */
	@Override
	public void deleteProducts(Long activityId) {
		List<ActivityProduct> activityProducts = this.getActivityProducts(activityId);
		for (ActivityProduct activityProduct : activityProducts) {
			this.activityProductDao.remove(activityProduct.getId());
		}
	}

	/* (non-Javadoc)
         * @see com.jeecms.point.manager.product.ActivityProductMng#findById(java.lang.Long)
         */
	@Override
	public ActivityProduct findById(Long id) {
		return activityProductDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.ActivityProductMng#getProducts(java.lang.Long)
	 */
	@Override
	public List<BasePointProduct> getProducts(Long activityId) {
		return activityProductDao.getProducts(activityId);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.ActivityProductMng#getActivityProduct(java.lang.Long, java.lang.Long)
	 */
	@Override
	public ActivityProduct getActivityProduct(Long productId, Long activityId) {
		return activityProductDao.getActivityProduct(productId, activityId);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.ActivityProductMng#getActivityProducts(java.lang.Long)
	 */
	@Override
	public List<ActivityProduct> getActivityProducts(Long activityId) {
		return activityProductDao.getActivityProducts(activityId);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.ActivityProductMng#getPageOnline(java.lang.Long)
	 */
	@Override
	public Pagination getPageOnline(QueryVo queryVo) {
		return activityProductDao.getPageOnline(queryVo);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.ActivityProductMng#getActivityProducts()
	 */
	@Override
	public List<ActivityProduct> getActivityProducts() {
		return activityProductDao.getActivityProducts();
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.ActivityProductMng#getActivityProductsByproductId(java.lang.Long)
	 */
	@Override
	public List<ActivityProduct> getActivityProductsByproductId(Long productId) {
		return activityProductDao.getActivityProductsByproductId(productId);
	}
	
}
