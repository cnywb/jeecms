/**
 * 
 */
package com.jeecms.point.manager.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.dao.product.ProductCategoryDao;
import com.jeecms.point.entity.ProductCategory;
import com.jeecms.point.manager.product.ProductCategoryMng;
import com.jeecms.point.web.query.QueryVo;

/**
 * @author wanglijun
 *
 */
@Service
public class ProductCategoryMngImpl implements ProductCategoryMng {
	
	
	@Autowired 
	private ProductCategoryDao productCategoryDao;

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.ProductCategoryMng#findById(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly=true)
	public ProductCategory findById(Long id) {
		return productCategoryDao.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.ProductCategoryMng#save(com.jeecms.point.entity.ProductCategory)
	 */
	@Override
	public ProductCategory save(ProductCategory productCategory) {
		 
		return this.productCategoryDao.save(productCategory);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.ProductCategoryMng#getPage(com.jeecms.point.web.query.QueryVo, java.lang.String)
	 */
	@Override
	@Transactional(readOnly=true)
	public Pagination queryPagination(QueryVo queryVo, String categoryName) {
		 
		return this.productCategoryDao.queryPagination(queryVo, categoryName);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.ProductCategoryMng#remove(java.lang.Long)
	 */
	@Override
	public ProductCategory remove(Long id) {
		 
		return this.productCategoryDao.remove(id);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.ProductCategoryMng#update(com.jeecms.point.entity.ProductCategory)
	 */
	@Override
	public ProductCategory update(ProductCategory productCategory) {
	 
		return this.productCategoryDao.update(productCategory);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.product.ProductCategoryMng#getAllProduct()
	 */
	@Override
	public List<ProductCategory> getAllProductCategory() {
		return this.productCategoryDao.getAllProductCategory();
	}
	
	
	
}
