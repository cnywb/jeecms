package com.jeecms.cms.entity.maintenance;

public class MaintenanceCar {
	
	private long id;
	
	private String vehicleLine;
	
	private String engineType;
	
	private String model;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVehicleLine() {
		return vehicleLine;
	}

	public void setVehicleLine(String vehicleLine) {
		this.vehicleLine = vehicleLine;
	}

	public String getEngineType() {
		return engineType;
	}

	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	

}
