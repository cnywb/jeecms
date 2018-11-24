package com.jeecms.point.dao.competitiveproduct.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.point.dao.competitiveproduct.CompetitiveProductDao;
import com.jeecms.point.entity.CompetitiveProduct;

/**
 * 
 * @author ziv.hung
 *
 */
@Repository
@SuppressWarnings("unchecked")
public class CompetitiveProductDaoImpl extends HibernateBaseDao<CompetitiveProduct, Long> implements CompetitiveProductDao {

	@Override
	protected Class<CompetitiveProduct> getEntityClass() {
		return CompetitiveProduct.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.point.dao.competitiveproduct.CompetitiveProductDao#save(com
	 * .jeecms.point.entity.CompetitiveProduct)
	 */
	@Override
	public CompetitiveProduct save(CompetitiveProduct competitiveProduct) {
		this.getSession().save(competitiveProduct);
		return competitiveProduct;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.point.dao.competitiveproduct.CompetitiveProductDao#update(
	 * com.jeecms.point.entity.CompetitiveProduct)
	 */
	@Override
	public CompetitiveProduct update(CompetitiveProduct competitiveProduct) {
		this.getSession().update(competitiveProduct);
		super.getSession().flush();
		return competitiveProduct;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.point.dao.competitiveproduct.CompetitiveProductDao#getPage
	 * (com.jeecms.point.web.query.QueryVo, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Pagination getPage(String productName, String vehicleModels, String classification, String status, int pageNo, int pageSize) {
		String hql = "select p from   CompetitiveProduct p where 1=1 ";
		Finder finder = Finder.create(hql);
		// 添加
		if (StringUtils.isNotEmpty(productName)) {
			finder.append(" and p.productName like :productName ");
			finder.setParam("productName", "%" + productName + "%");
		}

		if (StringUtils.isNotEmpty(vehicleModels)) {
			finder.append(" and (p.vehicleModels=:vehicleModels ");
			finder.append(" or p.vehicleModels='CALL') ");
			finder.setParam("vehicleModels", vehicleModels);
		}

		if (StringUtils.isNotEmpty(classification)) {
			finder.append(" and p.classification=:classification ");
			finder.setParam("classification", classification);
		}
		if (StringUtils.isNotEmpty(status)) {
			finder.append(" and p.status=:status ");
			finder.setParam("status", status);
		}
		finder.append(" order by id desc");
		Pagination pagination = this.find(finder, pageNo, pageSize);
		return pagination;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.point.dao.competitiveproduct.CompetitiveProductDao#remove(
	 * java.lang.Long)
	 */
	@Override
	public CompetitiveProduct remove(Long id) {
		CompetitiveProduct competitiveProduct = super.get(id);
		if (competitiveProduct != null) {
			this.getSession().delete(competitiveProduct);
		}
		return competitiveProduct;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.point.dao.competitiveproduct.CompetitiveProductDao#findById
	 * (java.lang.Long)
	 */
	@Override
	public CompetitiveProduct findById(Long id) {
		return super.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jeecms.point.dao.competitiveproduct.CompetitiveProductDao#
	 * checkCompetitiveProductNo(java.lang.String)
	 */
	@Override
	public boolean checkCompetitiveProductNo(String competitiveProductNo) {
		String hql = "from CompetitiveProduct  where productNo=:competitiveProductNo ";
		Finder finder = Finder.create(hql);
		finder.setParam("competitiveProductNo", competitiveProductNo);
		List<CompetitiveProduct> list = this.find(finder);
		if (list != null && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jeecms.point.dao.competitiveproduct.CompetitiveProductDao#
	 * checkcompetitiveProductName(java.lang.String)
	 */
	@Override
	public boolean checkcompetitiveProductName(String competitiveProductName) {
		String hql = "from CompetitiveProduct  where productName=:competitiveProductName ";
		Finder finder = Finder.create(hql);
		finder.setParam("competitiveProductName", competitiveProductName);
		List<CompetitiveProduct> list = this.find(finder);
		if (list != null && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.dao.competitiveproduct.CompetitiveProductDao#updateBrowse(java.lang.Long)
	 */
	@Override
	public int updateBrowse(Long productId) {
		String hql="update CompetitiveProduct set browseTimes=browseTimes+1 where id=:productId";
		Finder finder = Finder.create(hql);
		finder.setParam("productId", productId);
		Query query=this.getSession().createQuery(hql);
		query.setParameter("productId", productId);
		return query.executeUpdate();
	}
	
	
}
