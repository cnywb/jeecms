/**
 * 
 */
package com.jeecms.point.dao.product;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.entity.ProductCategory;
import com.jeecms.point.web.query.QueryVo;

/**
 * @author wanglijun
 *
 */
public interface ProductCategoryDao {
	/***
	 * 保存积分规则
	 * @param pointRule 规则
	 * @return  PointRule
	 */
	public ProductCategory save(ProductCategory productCategory);
	
	
	/***
	 * 分页查询参数
	 * @param queryVo 分页参数
	 * @param categoryName 
	 * @return Pagination
	 */
	public Pagination queryPagination(QueryVo queryVo,String categoryName);
	
	/**
	 * 删除对象
	 * @param id 对象ID
	 * @return true 删除
	 */
	public  ProductCategory remove(Long id);
	
	/***
	 * 根据ID查询积分规则
	 * @param id
	 * @return PointRule
	 */
	public ProductCategory findById(Long id);
	
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
