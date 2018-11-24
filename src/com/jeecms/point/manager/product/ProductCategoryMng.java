/**
 * 
 */
package com.jeecms.point.manager.product;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.entity.ProductCategory;
import com.jeecms.point.web.query.QueryVo;

/**
 * @author wanglijun
 *
 */
public interface ProductCategoryMng {
	/***
	 * 根据ID查询 ProductCategory
	 * @param id ID
	 * @return ProductCategory
	 */
	public ProductCategory findById(Long id);
	
	/***
	 * 保存产品分类
	 * @param pointRule
	 * @return PointRule
	 */
	public ProductCategory save(ProductCategory productCategory);
	
	
	/***
	 * 
	 * @param queryVo 查询参数
	 * @param categoryName 规则名称
	 * @return Pagination
	 */
	public Pagination queryPagination(QueryVo queryVo,String categoryName);
	/**删除*/
	public ProductCategory remove(Long id);
	
	/***
	 * 更新数据
	 * @param pointRule 更新数据
	 * @return 更新数据
	 */
	public ProductCategory update(ProductCategory productCategory);
	
	/***
	 * 获取所有的商品种类
	 * @return 商品种类
	 */
	public List<ProductCategory> getAllProductCategory();
}
