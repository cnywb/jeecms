/**
 * 
 */
package com.jeecms.bbs.action.member.vo;

/**
 * @author xuw
 *
 */
public class CarFocusType {
	
	private Long id;
	
	private String key;
	
	private String name;
	
	private Boolean disabled=Boolean.FALSE;
	
	public CarFocusType() {
		super();
	}

	public CarFocusType(String key, String name) {
		super();
		this.key = key;
		this.name = name;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
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
	 * @return the disabled
	 */
	public Boolean getDisabled() {
		return disabled;
	}

	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
}
