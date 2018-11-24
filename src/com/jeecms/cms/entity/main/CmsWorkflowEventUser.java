package com.jeecms.cms.entity.main;

import com.jeecms.cms.entity.main.base.BaseCmsWorkflowEventUser;



public class CmsWorkflowEventUser extends BaseCmsWorkflowEventUser {
	private static final long serialVersionUID = 1L;

	public CmsWorkflowEventUser(CmsWorkflowEvent event, CmsUser user) {
		super(event, user);
	}
	
	

/*[CONSTRUCTOR MARKER BEGIN]*/
	public CmsWorkflowEventUser () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsWorkflowEventUser (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CmsWorkflowEventUser (
		java.lang.Integer id,
		com.jeecms.cms.entity.main.CmsWorkflowEvent event,
		com.jeecms.cms.entity.main.CmsUser user) {

		super (
			id,
			event,
			user);
	}


/*[CONSTRUCTOR MARKER END]*/


}