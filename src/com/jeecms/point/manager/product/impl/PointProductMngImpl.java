/**
 * 
 */
package com.jeecms.point.manager.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.dao.product.PointProductDao;
import com.jeecms.point.entity.PointProduct;
import com.jeecms.point.manager.product.PointProductMng;
import com.jeecms.point.web.query.QueryVo;

/**
 * @author wanglijun
 *
 */
@Service
@Transactional
public class PointProductMngImpl implements PointProductMng{
	
	@Autowired
	private PointProductDao pointProductDao;
	
	/**
	 * @return the pointProductDao
	 */
	public PointProductDao getPointProductDao() {
		return pointProductDao;
	}

	/**
	 * @param pointProductDao the pointProductDao to set
	 */
	public void setPointProductDao(PointProductDao pointProductDao) {
		this.pointProductDao = pointProductDao;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.PointProductMng#save(com.jeecms.point.entity.PointProduct)
	 */
	@Override
	public PointProduct save(PointProduct pointProduct) {
		return pointProductDao.save(pointProduct); 
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.PointProductMng#getPage(com.jeecms.point.web.query.QueryVo, java.lang.String, com.jeecms.point.entity.base.BaseProductCategory)
	 */
	@Override
	@Transactional(readOnly=true)
	public Pagination getPage(QueryVo queryVo,String title,Long categoryId){
		return pointProductDao.getPage(queryVo, title, categoryId);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.PointProductMng#getPageOnline(com.jeecms.point.web.query.QueryVo, java.lang.String, java.lang.Long)
	 */
	@Override
	@Transactional(readOnly=true)
	public Pagination getPageOnline(QueryVo queryVo, String title,
			Long categoryId) {
		return pointProductDao.getPageOnline(queryVo, title, categoryId);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.PointProductMng#remove(java.lang.Long)
	 */
	@Override
	public PointProduct remove(Long id) {
		return pointProductDao.remove(id);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.PointProductMng#findById(java.lang.Long)
	 */
	@Override
	public PointProduct findById(Long id) {
		return pointProductDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.PointProductMng#checkPointProductNo(java.lang.String)
	 */
	@Override
	public boolean checkPointProductNo(String pointProductNo) {
		return pointProductDao.checkPointProductNo(pointProductNo);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.PointProductMng#update(com.jeecms.point.entity.PointProduct)
	 */
	@Override
	public PointProduct update(PointProduct pointProduct) {
		return pointProductDao.update(pointProduct);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.PointProductMng#getLotteryProducts()
	 */
	@Override
	public List<PointProduct> getLotteryProducts() {
		return pointProductDao.getLotteryProducts();
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.PointProductMng#getExchangeProducts()
	 */
	@Override
	public List<PointProduct> getExchangeProducts() {
		return pointProductDao.getExchangeProducts();
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.PointProductMng#getSeckillProducts()
	 */
	@Override
	public List<PointProduct> getSeckillProducts() {
		return pointProductDao.getSeckillProducts();
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.PointProductMng#lotteryProductsDecrease(java.lang.Long)
	 */
	@Override
	public PointProduct lotteryProductsDecrease(Long id) {
		return pointProductDao.lotteryProductsDecrease(id);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.PointProductMng#exchangeProductsDecrease(java.lang.Long)
	 */
	@Override
	public PointProduct exchangeProductsDecrease(Long id) {
		return pointProductDao.exchangeProductsDecrease(id);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.PointProductMng#seckillProductsDecrease(java.lang.Long)
	 */
	@Override
	public PointProduct seckillProductsDecrease(Long id) {
		return pointProductDao.seckillProductsDecrease(id);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.PointProductMng#getPageAndActivity(com.jeecms.point.web.query.QueryVo, java.lang.String, java.lang.Long)
	 */
	@Override
	public Pagination getPageAndActivity(QueryVo queryVo, String title,
			Long categoryId) {
		return pointProductDao.getPageAndActivity(queryVo, title, categoryId);
	}

	
	
}
