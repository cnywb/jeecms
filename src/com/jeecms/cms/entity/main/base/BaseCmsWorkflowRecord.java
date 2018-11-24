package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_workflow_record table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_workflow_record"
 */

public abstract class BaseCmsWorkflowRecord  implements Serializable {

	public static String REF = "CmsWorkflowRecord";
	public static String PROP_USER = "user";
	public static String PROP_SITE = "site";
	public static String PROP_EVENT = "event";
	public static String PROP_TYPE = "type";
	public static String PROP_ID = "id";
	public static String PROP_RECORD_TIME = "recordTime";
	public static String PROP_OPINION = "opinion";


	// constructors
	public BaseCmsWorkflowRecord () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsWorkflowRecord (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCmsWorkflowRecord (
		java.lang.Integer id,
		com.jeecms.cms.entity.main.CmsSite site,
		com.jeecms.cms.entity.main.CmsWorkflowEvent event,
		com.jeecms.cms.entity.main.CmsUser user,
		java.util.Date recordTime,
		java.lang.Integer type) {

		this.setId(id);
		this.setSite(site);
		this.setEvent(event);
		this.setUser(user);
		this.setRecordTime(recordTime);
		this.setType(type);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.util.Date recordTime;
	private java.lang.String opinion;
	private java.lang.Integer type;

	// many to one
	private com.jeecms.cms.entity.main.CmsSite site;
	private com.jeecms.cms.entity.main.CmsWorkflowEvent event;
	private com.jeecms.cms.entity.main.CmsUser user;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="record_id"
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
	 * Return the value associated with the column: record_time
	 */
	public java.util.Date getRecordTime () {
		return recordTime;
	}

	/**
	 * Set the value related to the column: record_time
	 * @param recordTime the record_time value
	 */
	public void setRecordTime (java.util.Date recordTime) {
		this.recordTime = recordTime;
	}


	/**
	 * Return the value associated with the column: opinion
	 */
	public java.lang.String getOpinion () {
		return opinion;
	}

	/**
	 * Set the value related to the column: opinion
	 * @param opinion the opinion value
	 */
	public void setOpinion (java.lang.String opinion) {
		this.opinion = opinion;
	}


	/**
	 * Return the value associated with the column: type
	 */
	public java.lang.Integer getType () {
		return type;
	}

	/**
	 * Set the value related to the column: type
	 * @param type the type value
	 */
	public void setType (java.lang.Integer type) {
		this.type = type;
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


	/**
	 * Return the value associated with the column: event_id
	 */
	public com.jeecms.cms.entity.main.CmsWorkflowEvent getEvent () {
		return event;
	}

	/**
	 * Set the value related to the column: event_id
	 * @param event the event_id value
	 */
	public void setEvent (com.jeecms.cms.entity.main.CmsWorkflowEvent event) {
		this.event = event;
	}


	/**
	 * Return the value associated with the column: user_id
	 */
	public com.jeecms.cms.entity.main.CmsUser getUser () {
		return user;
	}

	/**
	 * Set the value related to the column: user_id
	 * @param user the user_id value
	 */
	public void setUser (com.jeecms.cms.entity.main.CmsUser user) {
		this.user = user;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.CmsWorkflowRecord)) return false;
		else {
			com.jeecms.cms.entity.main.CmsWorkflowRecord cmsWorkflowRecord = (com.jeecms.cms.entity.main.CmsWorkflowRecord) obj;
			if (null == this.getId() || null == cmsWorkflowRecord.getId()) return false;
			else return (this.getId().equals(cmsWorkflowRecord.getId()));
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