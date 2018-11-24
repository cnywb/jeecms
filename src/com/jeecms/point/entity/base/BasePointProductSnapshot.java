/**
 * 
 */
package com.jeecms.point.entity.base;

import java.io.Serializable;

/**
 * 
 * 积分商品快照信息
 * 
 * @author wanglijun
 * 
 */
public class BasePointProductSnapshot extends BasePoint implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -3175532112859244469L;
	
	/**商品ID*/
	private Long productId;
	/**商品编号*/
	private String productNo;
	/**商品分类*/
	private Long categoryId;
	/**商品名称*/
	private String name;
	/**显示标题*/
	private String title;
	/**显示副标题*/
	private String subTitle;
	/**市场价格*/
	private Double price;
	/**兑换积分*/ 
	private Long pointNum;
	/**库存量*/
	private Long stockNum;
	/**产品状态*/
	private Integer status;
	/**渠道*/
	private Integer channel;
	/**备注*/
	private String memo;
	/**小图片*/
	private String imageSmallURL;
	/**中图片*/
	private String imageMiddleURL;
	/**大小图*/
	private String imageLargeURL;
	/**
	 * 默认
	 */
	public BasePointProductSnapshot() {
		super();
	}
	
	public BasePointProductSnapshot(Long id) {
		super(id);
	}
	
	
	

	/**
	 * @param id
	 * @param productId
	 * @param productNo
	 * @param categoryId
	 * @param name
	 * @param title
	 * @param subTitle
	 * @param price
	 * @param pointNum
	 * @param stockNum
	 * @param status
	 * @param channel
	 * @param memo
	 * @param imageSmallURL
	 * @param imageMiddleURL
	 * @param imageLargeURL
	 */
	public BasePointProductSnapshot(Long id, Long productId, String productNo,
			Long categoryId, String name, String title, String subTitle,
			Double price, Long pointNum, Long stockNum, Integer status,
			Integer channel, String memo, String imageSmallURL,
			String imageMiddleURL, String imageLargeURL) {
		super(id);
		this.productId = productId;
		this.productNo = productNo;
		this.categoryId = categoryId;
		this.name = name;
		this.title = title;
		this.subTitle = subTitle;
		this.price = price;
		this.pointNum = pointNum;
		this.stockNum = stockNum;
		this.status = status;
		this.channel = channel;
		this.memo = memo;
		this.imageSmallURL = imageSmallURL;
		this.imageMiddleURL = imageMiddleURL;
		this.imageLargeURL = imageLargeURL;
	}

	/**
	 * @return the productNo
	 */
	public String getProductNo() {
		return productNo;
	}

	/**
	 * @param productNo the productNo to set
	 */
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	/**
	 * @return the categoryId
	 */
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the subTitle
	 */
	public String getSubTitle() {
		return subTitle;
	}

	/**
	 * @param subTitle the subTitle to set
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the pointNum
	 */
	public Long getPointNum() {
		return pointNum;
	}

	/**
	 * @param pointNum the pointNum to set
	 */
	public void setPointNum(Long pointNum) {
		this.pointNum = pointNum;
	}

	/**
	 * @return the stockNum
	 */
	public Long getStockNum() {
		return stockNum;
	}

	/**
	 * @param stockNum the stockNum to set
	 */
	public void setStockNum(Long stockNum) {
		this.stockNum = stockNum;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the channel
	 */
	public Integer getChannel() {
		return channel;
	}

	/**
	 * @param channel the channel to set
	 */
	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @return the imageSmallURL
	 */
	public String getImageSmallURL() {
		return imageSmallURL;
	}

	/**
	 * @param imageSmallURL the imageSmallURL to set
	 */
	public void setImageSmallURL(String imageSmallURL) {
		this.imageSmallURL = imageSmallURL;
	}

	/**
	 * @return the imageMiddleURL
	 */
	public String getImageMiddleURL() {
		return imageMiddleURL;
	}

	/**
	 * @param imageMiddleURL the imageMiddleURL to set
	 */
	public void setImageMiddleURL(String imageMiddleURL) {
		this.imageMiddleURL = imageMiddleURL;
	}

	/**
	 * @return the imageLargeURL
	 */
	public String getImageLargeURL() {
		return imageLargeURL;
	}

	/**
	 * @param imageLargeURL the imageLargeURL to set
	 */
	public void setImageLargeURL(String imageLargeURL) {
		this.imageLargeURL = imageLargeURL;
	}

	/**
	 * @return the productId
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	
}	
