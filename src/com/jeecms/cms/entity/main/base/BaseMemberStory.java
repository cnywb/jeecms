package com.jeecms.cms.entity.main.base;

import java.util.Date;

import com.jeecms.cms.entity.main.CmsUser;

public class BaseMemberStory implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4063170967489142469L;
	public static String REF = "MemberStory";
	public static String PROP_ID ="id";
	
	public static String PROP_CERT_IMAGE_URL="imageUrl";
	public static String PROP_CREATE_TIME ="createTime";
	public static String PROP_UPDATE_TIME ="updateTime";
	public static String PROP_CREATER="creater";
	public static String PROP_MEMO="content";
	public static String PROP_PRAISE_COUNT="praiseCount";
	
	private long id;
	
	private String imageUrl="";//代表图
	
	private Integer status=0;//0待审核,1审核通过,2审核不通过
	
	private String content="";//故事内容
	
	private CmsUser creater;//发表会员
	
    private Date createTime=new Date();
	
	private Date updateTime=new Date();
	
	private Date processTime;
	
	private Integer praiseCount=0;
	
	private Integer createrId;
	
	private String createrName;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CmsUser getCreater() {
		return creater;
	}

	public void setCreater(CmsUser creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public BaseMemberStory(String imageUrl, Integer status, String content,
			CmsUser creater) {
		super();
		this.imageUrl = imageUrl;
		this.status = status;
		this.content = content;
		this.creater = creater;
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}
	
	public BaseMemberStory() {
		initialize();
	}

	public Integer getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(Integer praiseCount) {
		this.praiseCount = praiseCount;
	}

	public Integer getCreaterId() {
		return this.creater!=null?this.creater.getId():0;
	}

	public void setCreaterId(Integer createrId) {
		this.createrId = createrId;
	}

	public String getCreaterName() {
		return this.creater!=null?this.creater.getUsername():"";
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	protected void initialize () {}

}
