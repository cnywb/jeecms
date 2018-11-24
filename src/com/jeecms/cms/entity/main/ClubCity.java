package com.jeecms.cms.entity.main;

import java.io.Serializable;

public class ClubCity implements Serializable {
	
   /**
	 * 
	 */
	private static final long serialVersionUID = 4553611188748152354L;

    private long id;
	
    private long provinceId;
    
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id; 
	}

	public String getName() {
		return name;
	}

	public long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(long provinceId) {
		this.provinceId = provinceId;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
