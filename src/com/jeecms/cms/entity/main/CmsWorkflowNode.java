package com.jeecms.cms.entity.main;

import com.jeecms.cms.entity.main.base.BaseCmsWorkflowNode;



public class CmsWorkflowNode extends BaseCmsWorkflowNode {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public CmsWorkflowNode () {
		super();
	}

	/**
	 * Constructor for required fields
	 */
	public CmsWorkflowNode (
		com.jeecms.cms.entity.main.CmsRole role,
		boolean countersign) {

		super (
			role,
			countersign);
	}

/*[CONSTRUCTOR MARKER END]*/


}