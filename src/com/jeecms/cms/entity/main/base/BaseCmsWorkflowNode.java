package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_workflow table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_workflow"
 */

public abstract class BaseCmsWorkflowNode  implements Serializable {

	public static String REF = "CmsWorkflowNode";
	public static String PROP_COUNTERSIGN = "countersign";
	public static String PROP_ROLE = "role";


	// constructors
	public BaseCmsWorkflowNode () {
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCmsWorkflowNode (
		com.jeecms.cms.entity.main.CmsRole role,
		boolean countersign) {

		this.setRole(role);
		this.setCountersign(countersign);
		initialize();
	}

	protected void initialize () {}



	// fields
	private boolean countersign;

	// many to one
	private com.jeecms.cms.entity.main.CmsRole role;






	/**
	 * Return the value associated with the column: is_countersign
	 */
	public boolean isCountersign () {
		return countersign;
	}

	/**
	 * Set the value related to the column: is_countersign
	 * @param countersign the is_countersign value
	 */
	public void setCountersign (boolean countersign) {
		this.countersign = countersign;
	}


	/**
	 * Return the value associated with the column: role_id
	 */
	public com.jeecms.cms.entity.main.CmsRole getRole () {
		return role;
	}

	/**
	 * Set the value related to the column: role_id
	 * @param role the role_id value
	 */
	public void setRole (com.jeecms.cms.entity.main.CmsRole role) {
		this.role = role;
	}






	public String toString () {
		return super.toString();
	}


}