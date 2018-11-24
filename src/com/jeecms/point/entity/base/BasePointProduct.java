/**
 * 
 */
package com.jeecms.point.entity.base;

import java.io.Serializable;

/**
 * 
 * 积分商品
 * 
 * @author wanglijun
 * 
 */
public class BasePointProduct extends BasePoint implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -3175532112859244469L;
	
	/**商品编号*/
	private String productNo;
	/**商品分类*/
	private Long categoryId;
	/**商品分类对象*/
	private BaseProductCategory productCategory;
	/**商品名称*/
	private String info;
	/**显示标题*/
	private String title;
	/**显示副标题*/
	private String subTitle;
	/**市场价格*/
	private Double price;
	/**兑换积分*/ 
	private Long pointNum;
	/**抽奖库存量*/
	private Long lotteryStockNum;
	/**秒杀库存量*/
	private Long seckillStockNum;
	/**兑换库存量*/
	private Long exchangeStockNum;
	/**累积库存量*/
	private Long accumulatedStockNum;
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
	/**显示顺序*/
	private Integer showOrder;
	/**每月可抽奖库存数据*/
	private Long monthLotteryStockNum;
	/**每月剩余抽奖库存数据*/
	private Long monthLeftLotteryStockNum;
	/**月份值*/
	private Integer month;
	/**是否需要运输*/
	private Integer isTransport;
	/**
	 * 默认
	 */
	public BasePointProduct() {
		super();
	}
	
	public BasePointProduct(Long id) {
		super(id);
	}

	/**
	 * @return the isTransport
	 */
	public Integer getIsTransport() {
		return isTransport;
	}

	/**
	 * @param isTransport the isTransport to set
	 */
	public void setIsTransport(Integer isTransport) {
		this.isTransport = isTransport;
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
	 * @return the productCategory
	 */
	public BaseProductCategory getProductCategory() {
		return productCategory;
	}

	/**
	 * @param productCategory the productCategory to set
	 */
	public void setProductCategory(BaseProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * @return the lotteryStockNum
	 */
	public Long getLotteryStockNum() {
		return lotteryStockNum;
	}

	/**
	 * @param lotteryStockNum the lotteryStockNum to set
	 */
	public void setLotteryStockNum(Long lotteryStockNum) {
		this.lotteryStockNum = lotteryStockNum;
	}

	/**
	 * @return the seckillStockNum
	 */
	public Long getSeckillStockNum() {
		return seckillStockNum;
	}

	/**
	 * @param seckillStockNum the seckillStockNum to set
	 */
	public void setSeckillStockNum(Long seckillStockNum) {
		this.seckillStockNum = seckillStockNum;
	}

	/**
	 * @return the exchangeStockNum
	 */
	public Long getExchangeStockNum() {
		return exchangeStockNum;
	}

	/**
	 * @param exchangeStockNum the exchangeStockNum to set
	 */
	public void setExchangeStockNum(Long exchangeStockNum) {
		this.exchangeStockNum = exchangeStockNum;
	}
	
	/**
	 * @return the showOrder
	 */
	public Integer getShowOrder() {
		return showOrder;
	}

	/**
	 * @param showOrder the showOrder to set
	 */
	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}
	
	public Long getMonthLotteryStockNum() {
		return monthLotteryStockNum;
	}

	public void setMonthLotteryStockNum(Long monthLotteryStockNum) {
		this.monthLotteryStockNum = monthLotteryStockNum;
	}

	public Long getMonthLeftLotteryStockNum() {
		return monthLeftLotteryStockNum;
	}

	public void setMonthLeftLotteryStockNum(Long monthLeftLotteryStockNum) {
		this.monthLeftLotteryStockNum = monthLeftLotteryStockNum;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	/**
	 * @return the accumulatedStockNum
	 */
	public Long getAccumulatedStockNum() {
		return accumulatedStockNum;
	}

	/**
	 * @param accumulatedStockNum the accumulatedStockNum to set
	 */
	public void setAccumulatedStockNum(Long accumulatedStockNum) {
		this.accumulatedStockNum = accumulatedStockNum;
	}

	/**
	 * @param productNo
	 * @param categoryId
	 * @param productCategory
	 * @param info
	 * @param title
	 * @param subTitle
	 * @param price
	 * @param pointNum
	 * @param lotteryStockNum
	 * @param seckillStockNum
	 * @param exchangeStockNum
	 * @param status
	 * @param channel
	 * @param memo
	 * @param imageSmallURL
	 * @param imageMiddleURL
	 * @param imageLargeURL
	 */
	public BasePointProduct(String productNo, Long categoryId,
			BaseProductCategory productCategory, String info, String title,
			String subTitle, Double price, Long pointNum, Long lotteryStockNum,
			Long seckillStockNum, Long exchangeStockNum, Integer status,
			Integer channel, String memo, String imageSmallURL,
			String imageMiddleURL, String imageLargeURL) {
		super();
		this.productNo = productNo;
		this.categoryId = categoryId;
		this.productCategory = productCategory;
		this.info = info;
		this.title = title;
		this.subTitle = subTitle;
		this.price = price;
		this.pointNum = pointNum;
		this.lotteryStockNum = lotteryStockNum;
		this.seckillStockNum = seckillStockNum;
		this.exchangeStockNum = exchangeStockNum;
		this.status = status;
		this.channel = channel;
		this.memo = memo;
		this.imageSmallURL = imageSmallURL;
		this.imageMiddleURL = imageMiddleURL;
		this.imageLargeURL = imageLargeURL;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BasePointProduct [productNo=" + productNo + ", categoryId="
				+ categoryId + ", productCategory=" + productCategory
				+ ", info=" + info + ", title=" + title + ", subTitle="
				+ subTitle + ", price=" + price + ", pointNum=" + pointNum
				+ ", lotteryStockNum=" + lotteryStockNum + ", seckillStockNum="
				+ seckillStockNum + ", exchangeStockNum=" + exchangeStockNum
				+ ", status=" + status + ", channel=" + channel + ", memo="
				+ memo + ", imageSmallURL=" + imageSmallURL
				+ ", imageMiddleURL=" + imageMiddleURL + ", imageLargeURL="
				+ imageLargeURL + "]";
	}
	
	
}	
