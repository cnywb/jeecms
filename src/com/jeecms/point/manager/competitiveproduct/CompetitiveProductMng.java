package com.jeecms.point.manager.competitiveproduct;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.entity.CompetitiveProduct;
import com.jeecms.point.vo.competitiveproduct.ImCompetitiveVO;

/**
 * 
 * @author ziv.hung
 *
 */
public interface CompetitiveProductMng {
	/***
	 * 保存精品附件
	 * 
	 * @param competitiveProduct
	 * @return competitiveProduct
	 */
	public CompetitiveProduct save(CompetitiveProduct competitiveProduct);

	/**
	 * 保存精品附件数据集
	 * 
	 * @param competitiveProducts
	 */
	public void saveAll(List<CompetitiveProduct> competitiveProducts);

	/***
	 * 更新精品附件
	 * 
	 * @param competitiveProduct
	 * @return competitiveProduct
	 */
	public CompetitiveProduct update(CompetitiveProduct competitiveProduct);

	/***
	 * 
	 * @param queryVo
	 *            查询参数
	 * @param productName
	 *            精品名称
	 * @param vehicleModels
	 *            车型种类名称
	 * @param classification
	 *            附件分类名称
	 * @return Pagination
	 */
	public Pagination getPage(String productName, String vehicleModels, String classification, String status, int pageNo, int pageSize);

	/** 删除 */
	public CompetitiveProduct remove(Long id);

	/***
	 * 根据id查询精品附件
	 * 
	 * @param id
	 *            ID
	 * @return 返回精品附件
	 */
	public CompetitiveProduct findById(Long id);

	/***
	 * 检查精品附件编号是否重新
	 * 
	 * @param competitiveProductNo
	 *            精品附件编号
	 * @return true 表示重复 ，false 表示不重复
	 */
	public boolean checkCompetitiveProductNo(String competitiveProductNo);

	/***
	 * 检查精品附件名称是否重新
	 * 
	 * @param competitiveProductName
	 *            精品附件名称
	 * @return true 表示重复 ，false 表示不重复
	 */
	public boolean checkcompetitiveProductName(String competitiveProductName);

	/**
	 * 根据类型获取字典数据
	 * 
	 * @param category
	 *            类型代码
	 * @return List<ImCompetitiveVO>
	 */
	public List<ImCompetitiveVO> getImByCategory(String category);

	/**
	 * 获取枚举中文
	 * 
	 * @param category
	 *            枚举CODE
	 * @param code
	 *            枚举项CODE
	 * @return String
	 */
	public String getImNameByCode(String category, String code);
	/***
	 *　更新浏览次数
	 * @param productId
	 */
	public int  updateBrowse(Long productId);
}
