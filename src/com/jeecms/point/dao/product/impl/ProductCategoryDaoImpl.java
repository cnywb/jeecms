/**
 * 
 */
package com.jeecms.point.dao.product.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.point.dao.product.ProductCategoryDao;
import com.jeecms.point.entity.ProductCategory;
import com.jeecms.point.web.query.QueryVo;

/**
 * @author wanglijun
 *
 */
@Repository
public class ProductCategoryDaoImpl extends HibernateBaseDao<ProductCategory, Long> implements ProductCategoryDao {

	@Override
	protected Class<ProductCategory> getEntityClass() {
		return ProductCategory.class;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.ProductCategoryDao#save(com.jeecms.point.entity.ProductCategory)
	 */
	@Override
	public ProductCategory save(ProductCategory productCategory) {	 
	    super.getSession().save(productCategory);
	    super.getSession().flush();
	    return productCategory;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.ProductCategoryDao#queryPagination(com.jeecms.point.web.query.QueryVo, java.lang.String)
	 */
	@Override
	public Pagination queryPagination(QueryVo queryVo, String categoryName) {
		String hql="select t   from   ProductCategory t where 1=1 ";
		Finder finder = Finder.create(hql);
		//添加
		if(StringUtils.isNotEmpty(categoryName)){
			finder.append(" and categoryName like :categoryName ");
			finder.setParam("categoryName","%"+categoryName+"%");
		}
		
		 
		finder.append(" order by showOrder "+queryVo.getOrder());
		Pagination pagination= this.find(finder, queryVo.getPage(), queryVo.getRows());
		return pagination;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.ProductCategoryDao#remove(java.lang.Long)
	 */
	@Override
	public ProductCategory remove(Long id) {
		ProductCategory  productCategory= this.findById(id);
		if(productCategory!=null){
				this.getSession().delete(productCategory);
		}
		return productCategory;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.ProductCategoryDao#findById(java.lang.Long)
	 */
	@Override
	public ProductCategory findById(Long id) {
		 
		return super.get(id);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.ProductCategoryDao#update(com.jeecms.point.entity.ProductCategory)
	 */
	@Override
	public ProductCategory update(ProductCategory productCategory) {
		this.getSession().update(productCategory);
		super.getSession().flush();
		return productCategory;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.ProductCategoryDao#getAllProduct()
	 */
	@Override
	public List<ProductCategory> getAllProductCategory() {
		String hql = "from ProductCategory";
		Query query = this.getSession().createQuery(hql);
		List<ProductCategory> list = query.list();
		return list;
	}
	
	

}
