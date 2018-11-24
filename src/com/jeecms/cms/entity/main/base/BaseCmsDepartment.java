package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_department table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_department"
 */

public abstract class BaseCmsDepartment  implements Serializable {

	public static String REF = "CmsDepartment";
	public static String PROP_NAME = "name";
	public static String PROP_SITE = "site";
	public static String PROP_ID = "id";
	public static String PROP_WEIGHTS = "weights";
	public static String PROP_PRIORITY = "priority";


	// constructors
	public BaseCmsDepartment () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsDepartment (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCmsDepartment (
		java.lang.Integer id,
		java.lang.String name,
		java.lang.Integer priority,
		java.lang.Integer weights) {

		this.setId(id);
		this.setName(name);
		this.setPriority(priority);
		this.setWeights(weights);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String name;
	private java.lang.Integer priority;
	private java.lang.Integer weights;

	// many to one
	private com.jeecms.cms.entity.main.CmsSite site;
	private com.jeecms.cms.entity.main.CmsDepartment parent;

	// collections
	private java.util.Set<com.jeecms.cms.entity.main.CmsDepartment> child;
	private java.util.Set<com.jeecms.cms.entity.main.Channel> channels;
	private java.util.Set<com.jeecms.cms.entity.assist.CmsGuestbookCtg> guestBookCtgs;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="depart_id"
     */
	public java.lang.Integer getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: depart_name
	 */
	public java.lang.String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: depart_name
	 * @param name the depart_name value
	 */
	public void setName (java.lang.String name) {
		this.name = name;
	}


	/**
	 * Return the value associated with the column: priority
	 */
	public java.lang.Integer getPriority () {
		return priority;
	}

	/**
	 * Set the value related to the column: priority
	 * @param priority the priority value
	 */
	public void setPriority (java.lang.Integer priority) {
		this.priority = priority;
	}


	/**
	 * Return the value associated with the column: weights
	 */
	public java.lang.Integer getWeights () {
		return weights;
	}

	/**
	 * Set the value related to the column: weights
	 * @param weights the weights value
	 */
	public void setWeights (java.lang.Integer weights) {
		this.weights = weights;
	}


	/**
	 * Return the value associated with the column: site_id
	 */
	public com.jeecms.cms.entity.main.CmsSite getSite () {
		return site;
	}

	/**
	 * Set the value related to the column: site_id
	 * @param site the site_id value
	 */
	public void setSite (com.jeecms.cms.entity.main.CmsSite site) {
		this.site = site;
	}

	public com.jeecms.cms.entity.main.CmsDepartment getParent() {
		return parent;
	}

	public void setParent(com.jeecms.cms.entity.main.CmsDepartment parent) {
		this.parent = parent;
	}

	public java.util.Set<com.jeecms.cms.entity.main.CmsDepartment> getChild() {
		return child;
	}

	public void setChild(
			java.util.Set<com.jeecms.cms.entity.main.CmsDepartment> child) {
		this.child = child;
	}
	/**
	 * Return the value associated with the column: channels
	 */
	public java.util.Set<com.jeecms.cms.entity.main.Channel> getChannels () {
		return channels;
	}

	/**
	 * Set the value related to the column: channels
	 * @param channels the channels value
	 */
	public void setChannels (java.util.Set<com.jeecms.cms.entity.main.Channel> channels) {
		this.channels = channels;
	}


	/**
	 * Return the value associated with the column: guestBookCtgs
	 */
	public java.util.Set<com.jeecms.cms.entity.assist.CmsGuestbookCtg> getGuestBookCtgs () {
		return guestBookCtgs;
	}

	/**
	 * Set the value related to the column: guestBookCtgs
	 * @param guestBookCtgs the guestBookCtgs value
	 */
	public void setGuestBookCtgs (java.util.Set<com.jeecms.cms.entity.assist.CmsGuestbookCtg> guestBookCtgs) {
		this.guestBookCtgs = guestBookCtgs;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.CmsDepartment)) return false;
		else {
			com.jeecms.cms.entity.main.CmsDepartment cmsDepartment = (com.jeecms.cms.entity.main.CmsDepartment) obj;
			if (null == this.getId() || null == cmsDepartment.getId()) return false;
			else return (this.getId().equals(cmsDepartment.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}