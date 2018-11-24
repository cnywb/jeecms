package com.jeecms.point.vo.competitiveproduct;

/**
 * 精品附件VO
 * 
 * @author ziv.hung
 *
 */
public class CompetitiveProductVo {
	/** ID */
	private Long id;
	/** 车型种类代码 */
	private String vehicleModels;
	/** 附件分类代码 */
	private String classification;
	/** 精品名称 */
	private String productName;

	/** 精品代码 */
	private String productNo;
	/** 车型种类名称 */
	private String vehicleModelsLabel;
	/** 附件分类名称 */
	private String classificationLabel;
	/** 适用车型 */
	private String applicableModels;
	/** 价格 */
	private Double price;
	/** 简介 */
	private String briefIntroduction;
	/** 图片路径 */
	private String imgUrl;
	/** 状态 */
	private String status;

	public CompetitiveProductVo() {
		super();
	}

	public CompetitiveProductVo(Long id, String vehicleModels, String classification, String productName, String productNo, String vehicleModelsLabel,
			String classificationLabel, String applicableModels, Double price, String briefIntroduction, String imgUrl, String status) {
		super();
		this.id = id;
		this.vehicleModels = vehicleModels;
		this.classification = classification;
		this.productName = productName;
		this.productNo = productNo;
		this.vehicleModelsLabel = vehicleModelsLabel;
		this.classificationLabel = classificationLabel;
		this.applicableModels = applicableModels;
		this.price = price;
		this.briefIntroduction = briefIntroduction;
		this.imgUrl = imgUrl;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVehicleModels() {
		return vehicleModels;
	}

	public void setVehicleModels(String vehicleModels) {
		this.vehicleModels = vehicleModels;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getVehicleModelsLabel() {
		return vehicleModelsLabel;
	}

	public void setVehicleModelsLabel(String vehicleModelsLabel) {
		this.vehicleModelsLabel = vehicleModelsLabel;
	}

	public String getClassificationLabel() {
		return classificationLabel;
	}

	public void setClassificationLabel(String classificationLabel) {
		this.classificationLabel = classificationLabel;
	}

	public String getApplicableModels() {
		return applicableModels;
	}

	public void setApplicableModels(String applicableModels) {
		this.applicableModels = applicableModels;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getBriefIntroduction() {
		return briefIntroduction;
	}

	public void setBriefIntroduction(String briefIntroduction) {
		this.briefIntroduction = briefIntroduction;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
