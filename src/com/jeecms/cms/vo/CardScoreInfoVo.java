package com.jeecms.cms.vo;

import java.io.Serializable;
import java.util.List;

import com.jeecms.cms.entity.main.FordRepairWeb;
import com.jeecms.cms.entity.main.TempDeal;

public class CardScoreInfoVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cardId;//会员卡编号
	private String carType;//车辆型号
	private String vin;//vin码
	private String licenseNumber;//车牌号码
	private String dealerName;//经销商名称
	private String carColor;//车辆颜色
	private List<FordRepairWeb> repairs;//维修工单
	private List<TempDeal> tempDeals;//电子卷
	private int count=1;//计数器
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public List<FordRepairWeb> getRepairs() {
		return repairs;
	}
	public void setRepairs(List<FordRepairWeb> repairs) {
		this.repairs = repairs;
	}
	public List<TempDeal> getTempDeals() {
		return tempDeals;
	}
	public void setTempDeals(List<TempDeal> tempDeals) {
		this.tempDeals = tempDeals;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getCarColor() {
		return carColor;
	}
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
	
	

}
