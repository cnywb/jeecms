package com.jeecms.cms.entity.main;


import com.jeecms.cms.entity.main.base.BaseContentDoc;



public class ContentDoc extends BaseContentDoc {
	private static final long serialVersionUID = 1L;
	public void init(){
		if(getAvgScore()==null){
			setAvgScore(0f);
		}
		if(getDownNeed()==null){
			setDownNeed(0);
		}
		if(getGrain()==null){
			setGrain(0);
		}
		if(getIsOpen()==null){
			setIsOpen(true);
		}
	}

/*[CONSTRUCTOR MARKER BEGIN]*/
	public ContentDoc () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ContentDoc (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public ContentDoc (
		java.lang.Integer id,
		java.lang.String path,
		java.lang.Integer grain,
		java.lang.Integer downNeed,
		java.lang.Boolean isOpen,
		java.lang.String fileSuffix,
		java.lang.Float avgScore) {

		super (
			id,
			path,
			grain,
			downNeed,
			isOpen,
			fileSuffix,
			avgScore);
	}

/*[CONSTRUCTOR MARKER END]*/


}