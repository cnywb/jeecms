package com.jeecms.cms.entity.main.base;

import java.io.Serializable;
import java.util.Date;

import com.jeecms.cms.entity.main.CmsUser;

public abstract class BaseCarOwnerAuthenApplication implements Serializable {

	public static String REF = "CarOwnerAuthenApplication";
	public static String PROP_ID ="id";
	public static String PROP_VVIN ="vvin";
	public static String PROP_VNAME ="vname";
	public static String PROP_VMOBILE ="vmobile";
	public static String PROP_DEALER_NAME ="dealerName";
	public static String PROP_DEALER_SERVICE_CODE ="dealerServiceCode";
	public static String PROP_SALES_CODE ="salesCode";
	public static String PROP_CERT_IMAGE_URL="certImageUrl";
	public static String PROP_CREATE_TIME ="createTime";
	public static String PROP_UPDATE_TIME ="updateTime";
	public static String PROP_CREATER="creater";
	public static String PROP_MEMO="memo";
	
	public BaseCarOwnerAuthenApplication() {
		initialize () ;
	}

	public BaseCarOwnerAuthenApplication(long id, String vvin, String vname,
			String vmobile, String dealerName, String dealerServiceCode,
			String salesCode,String certImageUrl, Date createTime, Date updateTime, Integer status,
			CmsUser creater) {
		super();
		this.id = id;
		this.vvin = vvin;
		this.vname = vname;
		this.vmobile = vmobile;
		this.dealerName = dealerName;
		this.dealerServiceCode = dealerServiceCode;
		this.salesCode = salesCode;
		this.certImageUrl=certImageUrl;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.status = status;
		this.creater = creater;
	}

	public BaseCarOwnerAuthenApplication(String vvin, String vname,
			String vmobile, String certImageUrl) {
		super();
		this.vvin = vvin;
		this.vname = vname;
		this.vmobile = vmobile;
		this.certImageUrl = certImageUrl;
	}

	protected void initialize () {}
	/**
	 * 
	 */
	private static final long serialVersionUID = -2474879649309925543L;
	
	
	private long id;
	
	private String vvin;
	
	private String vname;
	
	private String vmobile;
	
	private String dealerName;
	
	private String dealerServiceCode;
	
	private String salesCode;
	
	private String certImageUrl;
	
	public String getCertImageUrl() {
		return certImageUrl;
	}

	public void setCertImageUrl(String certImageUrl) {
		this.certImageUrl = certImageUrl;
	}
	private Date createTime=new Date();
	
	private Date updateTime=new Date();
	
	private Integer status=0;
	
	private CmsUser creater;
	
	private String memo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVvin() {
		return vvin;
	}

	public void setVvin(String vvin) {
		this.vvin = vvin;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getVmobile() {
		return vmobile;
	}

	public void setVmobile(String vmobile) {
		this.vmobile = vmobile;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getDealerServiceCode() {
		return dealerServiceCode;
	}

	public void setDealerServiceCode(String dealerServiceCode) {
		this.dealerServiceCode = dealerServiceCode;
	}

	public String getSalesCode() {
		return salesCode;
	}

	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public CmsUser getCreater() {
		return creater;
	}

	public void setCreater(CmsUser creater) {
		this.creater = creater;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public BaseCarOwnerAuthenApplication(String vvin, String vname,
			String vmobile) {
		super();
		this.vvin = vvin;
		this.vname = vname;
		this.vmobile = vmobile;
	}

	
	
	
	
	
	

}
