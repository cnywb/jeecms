package com.jeecms.cms.entity.main;

import com.jeecms.cms.entity.main.base.BaseCmsWorkflowRecord;



public class CmsWorkflowRecord extends BaseCmsWorkflowRecord {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public CmsWorkflowRecord () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsWorkflowRecord (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CmsWorkflowRecord (
		java.lang.Integer id,
		com.jeecms.cms.entity.main.CmsSite site,
		com.jeecms.cms.entity.main.CmsWorkflowEvent event,
		com.jeecms.cms.entity.main.CmsUser user,
		java.util.Date recordTime,
		java.lang.Integer type) {

		super (
			id,
			site,
			event,
			user,
			recordTime,
			type);
	}

/*[CONSTRUCTOR MARKER END]*/


}