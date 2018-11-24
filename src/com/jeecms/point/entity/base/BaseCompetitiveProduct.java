package com.jeecms.point.entity.base;

/**
 * 精品附件
 * 
 * @author ziv.hung
 *
 */
public class BaseCompetitiveProduct extends BasePoint {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2197140432114722810L;

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
	/** 浏览次数 */
	private Integer browseTimes;

	/** 状态： 0：未上线，1：已上线，2：已下线，3：缺货 */
	private String status;

	/**
	 * 默认函数
	 */
	public BaseCompetitiveProduct() {
		super();
	}

	/**
	 * @param id
	 */
	public BaseCompetitiveProduct(Long id) {
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
	public BaseCompetitiveProduct(String vehicleModels, String classification, String productName, String productNo, String vehicleModelsLabel, String classificationLabel,
			String applicableModels, Double price, String briefIntroduction, String imgUrl, Integer browseTimes, String status) {
		super();
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
		this.browseTimes = browseTimes;
		this.status = status;
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

	public Integer getBrowseTimes() {
		return browseTimes;
	}

	public void setBrowseTimes(Integer browseTimes) {
		this.browseTimes = browseTimes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
