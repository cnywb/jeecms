/**
 * 
 */
package com.jeecms.point.dao.product;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.entity.PointProduct;
import com.jeecms.point.web.query.QueryVo;

/**
 * @author wanglijun
 *
 */
public interface PointProductDao {
	/***
	 * 保存积分商品
	 * @param pointProduct
	 * @return pointProduct
	 */
	public PointProduct save(PointProduct pointProduct);
	
	/***
	 * 
	 * @param queryVo 查询参数
	 * @param productName 商品名称
	 * @param productCategory 商品类型
	 * @return Pagination
	 */
	public Pagination getPage(QueryVo queryVo,String title,Long categoryId);
	
	/***
	 * 获取已上线的商品
	 * @param queryVo 查询参数
	 * @param productName 商品名称
	 * @param productCategory 商品类型
	 * @return Pagination
	 */
	public Pagination getPageOnline(QueryVo queryVo,String title,Long categoryId);
	
	/***
	 * 
	 * @param queryVo 查询参数
	 * @param productName 商品名称
	 * @param productCategory 商品类型
	 * @return Pagination
	 */
	public Pagination getPageAndActivity(QueryVo queryVo,String title,Long categoryId);
	
	/**删除*/
	public PointProduct remove(Long id);
	
	/***
	 * 根据id查询商品
	 * @param id ID
	 * @return 返回商品
	 */
	public PointProduct findById(Long id);
	
	/***
	 * 检查规则代码是否重新
	 * @param pointProductNo  商品编号
	 * @return TRUE,表示重复 ，false表示不重复
	 */
	public boolean  checkPointProductNo(String pointProductNo);
	/**更新*/
	public PointProduct update(PointProduct pointProduct);
	/**获取抽奖的商品*/
	public List<PointProduct> getLotteryProducts();
	/**获取兑换的商品*/
	public List<PointProduct> getExchangeProducts();
	/**获取秒杀的商品*/
	public List<PointProduct> getSeckillProducts();
	/**减抽奖商品库存*/
	public PointProduct lotteryProductsDecrease(Long id);
	/***
	 * 减抽奖商品库存
	 * @param id
	 * @return
	 */
	public PointProduct lotteryProductsDecreaseByGuaguaCard(Long id);
	/**减兑换商品库存*/
	public PointProduct exchangeProductsDecrease(Long id);
	/**减秒杀商品库存*/
	public PointProduct seckillProductsDecrease(Long id);
}
