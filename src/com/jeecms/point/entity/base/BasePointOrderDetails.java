/**
 * 
 */
package com.jeecms.point.entity.base;

import java.util.Date;

import com.jeecms.point.entity.PointOrder;
import com.jeecms.point.entity.PointProduct;

/**
 * @author wanglijun
 *
 */
public class BasePointOrderDetails extends BasePoint{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 978178036267321620L;
	
	private Long  orderId;
	
	private Long userId;
	
	private Long productId;
	
	private Long productSnapshotId;
	
	private Long pointNum;
	
	private Long payoutPointNum;
	
	private Date payoutDate;
	
	private PointOrder  pointOrder;	
	/**产品信息*/
	private PointProduct pointProduct;
	/**商品编号*/
	private String productNo;
	/**商品分类*/
	private Long categoryId;
	/**商品名称*/
	private String info;
	/**显示标题*/
	private String title;
	/**显示副标题*/
	private String subTitle;
	/**市场价格*/
	private Double price;
	/**抽奖库存量*/
	private Long lotteryStockNum;
	/**秒杀库存量*/
	private Long seckillStockNum;
	/**兑换库存量*/
	private Long exchangeStockNum;
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
	
	/**
	 * 
	 */
	public BasePointOrderDetails() {
		 super();
	}

	/**
	 * @param id
	 */
	public BasePointOrderDetails(Long id) {
		super(id);
	}
	
	

	/**
	 * @param id
	 * @param orderId
	 * @param userId
	 * @param productId
	 * @param productSnapshotId
	 * @param pointNum
	 * @param payoutPointNum
	 * @param payoutDate
	 */
	public BasePointOrderDetails(Long id, Long orderId, Long userId,
			Long productId, Long productSnapshotId, Long pointNum,
			Long payoutPointNum, Date payoutDate) {
		super(id);
		this.orderId = orderId;
		this.userId = userId;
		this.productId = productId;
		this.productSnapshotId = productSnapshotId;
		this.pointNum = pointNum;
		this.payoutPointNum = payoutPointNum;
		this.payoutDate = payoutDate;
	}

	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
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

	/**
	 * @return the productSnapshotId
	 */
	public Long getProductSnapshotId() {
		return productSnapshotId;
	}

	/**
	 * @param productSnapshotId the productSnapshotId to set
	 */
	public void setProductSnapshotId(Long productSnapshotId) {
		this.productSnapshotId = productSnapshotId;
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
	 * @return the payoutPointNum
	 */
	public Long getPayoutPointNum() {
		return payoutPointNum;
	}

	/**
	 * @param payoutPointNum the payoutPointNum to set
	 */
	public void setPayoutPointNum(Long payoutPointNum) {
		this.payoutPointNum = payoutPointNum;
	}

	/**
	 * @return the payoutDate
	 */
	public Date getPayoutDate() {
		return payoutDate;
	}

	/**
	 * @param payoutDate the payoutDate to set
	 */
	public void setPayoutDate(Date payoutDate) {
		this.payoutDate = payoutDate;
	}

	/**
	 * @return the pointOrder
	 */
	public PointOrder getPointOrder() {
		return pointOrder;
	}

	/**
	 * @param pointOrder the pointOrder to set
	 */
	public void setPointOrder(PointOrder pointOrder) {
		this.pointOrder = pointOrder;
	}

	/**
	 * @return the pointProduct
	 */
	public PointProduct getPointProduct() {
		return pointProduct;
	}

	/**
	 * @param pointProduct the pointProduct to set
	 */
	public void setPointProduct(PointProduct pointProduct) {
		this.pointProduct = pointProduct;
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

	
}
