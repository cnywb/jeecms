package com.jeecms.point.dao.product;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.entity.ActivityProduct;
import com.jeecms.point.entity.base.BasePointProduct;
import com.jeecms.point.web.query.QueryVo;

public interface ActivityProductDao {
	/***
	 * 保存积分商品
	 * @param activityproduct
	 * @return activityProduct
	 */
	public ActivityProduct save(ActivityProduct activityProduct);
	
	
	/**删除*/
	public ActivityProduct remove(Long id);
	
	/***
	 * 根据id查询商品
	 * @param id ID
	 * @return 返回商品
	 */
	public ActivityProduct findById(Long id);
	
	/***
	 * 根据id查询商品
	 * @param id ID
	 * @return 返回商品
	 */
	public List<BasePointProduct> getProducts(Long activityId);
	/***
	 * 根据活动和商品查询活动商品
	 * @param id activityID
	 * @param id productID
	 * @return 返回活动商品
	 */
	public ActivityProduct getActivityProduct(Long productId,Long activityId);
	
	/***
	 * 根据id查询活动商品
	 * @param id ID
	 * @return 返回商品
	 */
	public List<ActivityProduct> getActivityProducts(Long activityId);
	/***
	 * 根据id查询活动商品
	 * @param id ID
	 * @return 返回商品
	 */
	public Pagination getPageOnline(QueryVo queryVo);
	/***
	 * 查询活动商品
	 * @return 返回商品
	 */
	public List<ActivityProduct> getActivityProducts();
	/***
	 * 根据id查询活动商品
	 * @param id ID
	 * @return 返回商品
	 */
	public List<ActivityProduct> getActivityProductsByproductId(Long productId);
}
