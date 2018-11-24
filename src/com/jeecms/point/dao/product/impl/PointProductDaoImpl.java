/**
 * 
 */
package com.jeecms.point.dao.product.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.point.dao.product.PointProductDao;
import com.jeecms.point.entity.PointProduct;
import com.jeecms.point.web.query.QueryVo;

/**
 * @author wanglijun
 *
 */
@Repository
public class PointProductDaoImpl extends HibernateBaseDao<PointProduct, Long> implements PointProductDao {

	@Override
	protected Class<PointProduct> getEntityClass() {
		return PointProduct.class;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.PointProductDao#save(com.jeecms.point.entity.PointProduct)
	 */
	@Override
	public PointProduct save(PointProduct pointProduct) {
		this.getSession().save(pointProduct);
		return pointProduct;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.PointProductDao#getPage(com.jeecms.point.web.query.QueryVo, java.lang.String, java.lang.Long)
	 */
	@Override
	public Pagination getPage(QueryVo queryVo, String title, Long categoryId) {
		String hql="select p   from   PointProduct p where 1=1 ";
		Finder finder = Finder.create(hql);
		//添加
		if(StringUtils.isNotEmpty(title)){
			finder.append(" and p.title like:title ");
			finder.setParam("title","%"+title+"%");
		}
		
		if(categoryId!=null){
			finder.append(" and p.productCategory.id=:id ");
			finder.setParam("id",categoryId);
		}
		finder.append(" order by showOrder "+queryVo.getOrder());
		Pagination pagination= this.find(finder, queryVo.getPage(), queryVo.getRows());
		return pagination;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.PointProductDao#getPageOnline(com.jeecms.point.web.query.QueryVo, java.lang.String, java.lang.Long)
	 */
	@Override
	public Pagination getPageOnline(QueryVo queryVo, String title,
			Long categoryId) {
		String hql="select p   from   PointProduct p where 1=1 ";
		Finder finder = Finder.create(hql);
		//添加
		if(StringUtils.isNotEmpty(title)){
			finder.append(" and p.title like:title ");
			finder.setParam("title","%"+title+"%");
		}
		
		if(categoryId!=null){
			finder.append(" and p.productCategory.id=:id ");
			finder.setParam("id",categoryId);
		}
		finder.append(" and p.status=1 ");
		finder.append(" order by showOrder "+queryVo.getOrder());
		Pagination pagination= this.find(finder, queryVo.getPage(), queryVo.getRows());
		return pagination;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.PointProductDao#getPageAndActivity(com.jeecms.point.web.query.QueryVo, java.lang.String, java.lang.Long)
	 */
	@Override
	public Pagination getPageAndActivity(QueryVo queryVo, String title,
			Long categoryId) {
		String hql="select p   from   PointProduct p where 1=1 ";
		Finder finder = Finder.create(hql);
		//添加
		if(StringUtils.isNotEmpty(title)){
			finder.append(" and p.title like:title ");
			finder.setParam("title","%"+title+"%");
		}
		
		if(categoryId!=null){
			finder.append(" and p.productCategory.id=:id ");
			finder.setParam("id",categoryId);
		}
		finder.append(" order by showOrder "+queryVo.getOrder());
		Pagination pagination= this.find(finder, queryVo.getPage(), queryVo.getRows());
		return pagination;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.PointProductDao#remove(java.lang.Long)
	 */
	@Override
	public PointProduct remove(Long id) {
		PointProduct pointProduct= super.get(id);
		if(pointProduct!=null){
			this.getSession().delete(pointProduct);
		}
		return pointProduct;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.PointProductDao#findById(java.lang.Long)
	 */
	@Override
	public PointProduct findById(Long id) {
		return super.get(id);
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.PointProductDao#checkPointRuleNo(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean checkPointProductNo(String pointProductNo) {
		String hql = "from PointProduct  where categoryId=:categoryId ";
		Finder finder = Finder.create(hql);
		finder.setParam("categoryId",pointProductNo);
		List<PointProduct> list = this.find(finder);
		if(list!=null&&list.size()>0){
			return true;
		}else{
			return false;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.PointProductDao#update(com.jeecms.point.entity.PointProduct)
	 */
	@Override
	public PointProduct update(PointProduct pointProduct) {
		this.getSession().update(pointProduct);
		return pointProduct;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.PointProductDao#getLotteryProducts()
	 */
	@Override
	public List<PointProduct> getLotteryProducts() {
		String hql = "from PointProduct where lotteryStockNum>0 and status=1";
		Query query = this.getSession().createQuery(hql);
		return query.list();
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.PointProductDao#getExchangeProducts()
	 */
	@Override
	public List<PointProduct> getExchangeProducts() {
		String hql = "from PointProduct where exchangeStockNum>0 and status=1";
		Query query = this.getSession().createQuery(hql);
		return query.list();
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.PointProductDao#getSeckillProducts()
	 */
	@Override
	public List<PointProduct> getSeckillProducts() {
		String hql = "from PointProduct where seckillStockNum>0 and status=1";
		Query query = this.getSession().createQuery(hql);
		return query.list();
	}

	public PointProduct lotteryProductsDecreaseByGuaguaCard(Long id){
		PointProduct pointProduct = this.get(id, true);
		if(pointProduct != null&&pointProduct.getStatus().equals(1)){
			if(pointProduct.getMonthLeftLotteryStockNum()>=1L&&pointProduct.getLotteryStockNum()>=1L){
				pointProduct.setLotteryStockNum(pointProduct.getLotteryStockNum()-1L);
				pointProduct.setMonthLotteryStockNum(pointProduct.getMonthLotteryStockNum()-1L);
				pointProduct.setMonthLeftLotteryStockNum(pointProduct.getMonthLeftLotteryStockNum()-1L);
				pointProduct.setUpdatedDate(new Date());
				this.update(pointProduct);
				this.getSession().flush();
				this.getSession().clear();
				return pointProduct;
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.PointProductDao#lotteryProductsDecrease(java.lang.Long)
	 */
	@Override
	public PointProduct lotteryProductsDecrease(Long id) {

		PointProduct pointProduct = this.get(id, true);
		if(pointProduct != null &&pointProduct.getStatus().equals(1)){
			//判断月份是否为同一个月
			if(pointProduct.getMonth()!=null && pointProduct.getMonth().equals(Calendar.getInstance().get(Calendar.MONTH))){
				if(pointProduct.getMonthLeftLotteryStockNum()>=1L){
					if(pointProduct.getLotteryStockNum()>=1L){
						pointProduct.setLotteryStockNum(pointProduct.getLotteryStockNum()-1L);
						pointProduct.setMonthLeftLotteryStockNum(pointProduct.getMonthLeftLotteryStockNum()-1L);
					}else{
						return null;
					}
				}else{
					return null;
				}
			}else{
				if(pointProduct.getLotteryStockNum()<=0){
					return null;
				}
				//如果库存量大于每月存量 
				if(pointProduct.getLotteryStockNum()>=pointProduct.getMonthLotteryStockNum()){
					pointProduct.setMonthLeftLotteryStockNum(pointProduct.getMonthLotteryStockNum());
				}else{
					pointProduct.setMonthLeftLotteryStockNum(pointProduct.getLotteryStockNum());
				}
				pointProduct.setMonth(Calendar.getInstance().get(Calendar.MONTH));
				if(pointProduct.getMonthLeftLotteryStockNum()>=1L){
					pointProduct.setLotteryStockNum(pointProduct.getLotteryStockNum()-1L);
					pointProduct.setMonthLeftLotteryStockNum(pointProduct.getMonthLeftLotteryStockNum()-1L);
				}else{
					return null;
				}
			}
			pointProduct.setUpdatedDate(new Date());
			this.update(pointProduct);
		}
		this.getSession().flush();
		this.getSession().clear();
		return pointProduct;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.PointProductDao#exchangeProductsDecrease(java.lang.Long)
	 */
	@Override
	public  PointProduct exchangeProductsDecrease(Long id) {
		PointProduct pointProduct = this.get(id, true);
		if(pointProduct != null){
			if(pointProduct.getExchangeStockNum()>=1L){
				pointProduct.setExchangeStockNum(pointProduct.getExchangeStockNum()-1L);
				pointProduct.setUpdatedDate(new Date());
				this.update(pointProduct);
			}else{
				return null;
			}
		}
		this.getSession().flush();
		this.getSession().clear();
		return pointProduct;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.product.PointProductDao#seckillProductsDecrease(java.lang.Long)
	 */
	@Override
	public PointProduct seckillProductsDecrease(Long id) {
		PointProduct pointProduct = this.get(id, true);
		if(pointProduct != null){
			if(pointProduct.getSeckillStockNum()>=1L){
				pointProduct.setSeckillStockNum(pointProduct.getSeckillStockNum()-1L);
				pointProduct.setUpdatedDate(new Date());
				this.update(pointProduct);
			}else{
				return null;
			}
		}
		this.getSession().flush();
		this.getSession().clear();
		return pointProduct;
	}
	
	
}
