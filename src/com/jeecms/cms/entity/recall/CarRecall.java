package com.jeecms.cms.entity.recall;

public class CarRecall {
	
	private long id;
	
	private String vin;
	
	private String salesCode;
	
	private String salesName;
	
	private String area;
	
	private String areaManager;
	
	private String reason;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getSalesCode() {
		return salesCode;
	}

	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
	}

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAreaManager() {
		return areaManager;
	}

	public void setAreaManager(String areaManager) {
		this.areaManager = areaManager;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	

}
