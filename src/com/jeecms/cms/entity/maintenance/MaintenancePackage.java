package com.jeecms.cms.entity.maintenance;

public class MaintenancePackage {
	
	private long id;
	
	private String name;
	
	private String type;
	
	private String model;
	
	private String price;
	
	private String packageType;
	
	private String buyChance;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getBuyChance() {
		return buyChance;
	}

	public void setBuyChance(String buyChance) {
		this.buyChance = buyChance;
	}
	
	

}
