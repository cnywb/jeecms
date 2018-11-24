package com.jeecms.point.entity;

import com.jeecms.point.entity.base.BaseCompetitiveProduct;

/**
 * 精品附件
 * 
 * @author ziv.hung
 *
 */
public class CompetitiveProduct extends BaseCompetitiveProduct {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -1702566858854167998L;

	/**
	 * 默认函数
	 */
	public CompetitiveProduct() {
		super();
	}

	/**
	 * @param id
	 */
	public CompetitiveProduct(Long id) {
		super(id);
	}

	/**
	 * 带参构造函数
	 * 
	 * @param vehicleModels
	 * @param classification
	 * @param productName
	 * @param productNo
	 * @param vehicleModelsLabel
	 * @param classificationLabel
	 * @param applicableModels
	 * @param price
	 * @param briefIntroduction
	 * @param imgUrl
	 * @param browseTimes
	 * @param status
	 */
	public CompetitiveProduct(String vehicleModels, String classification, String productName, String productNo, String vehicleModelsLabel, String classificationLabel,
			String applicableModels, Double price, String briefIntroduction, String imgUrl, Integer browseTimes, String status) {
		super(vehicleModels, classification, productName, productNo, vehicleModelsLabel, classificationLabel, applicableModels, price, briefIntroduction, imgUrl, browseTimes,
				status);
	}

	@Override
	public String toString() {
		return "CompetitiveProduct [getVehicleModels()=" + getVehicleModels()
				+ ", getClassification()=" + getClassification()
				+ ", getProductName()=" + getProductName()
				+ ", getProductNo()=" + getProductNo()
				+ ", getVehicleModelsLabel()=" + getVehicleModelsLabel()
				+ ", getClassificationLabel()=" + getClassificationLabel()
				+ ", getApplicableModels()=" + getApplicableModels()
				+ ", getPrice()=" + getPrice() + ", getBriefIntroduction()="
				+ getBriefIntroduction() + ", getImgUrl()=" + getImgUrl()
				+ ", getBrowseTimes()=" + getBrowseTimes() + ", getStatus()="
				+ getStatus() + ", getId()=" + getId() + ", getCreatedId()="
				+ getCreatedId() + ", getCreatedDate()=" + getCreatedDate()
				+ ", getUpdatedId()=" + getUpdatedId() + ", getUpdatedDate()="
				+ getUpdatedDate() + ", hashCode()=" + hashCode()
				+ ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}

}
