package com.jeecms.cms.entity.main.base;

import java.io.Serializable;
import java.util.Date;

public class BaseFordCar implements Serializable{

	public static String REF = "FordCar";
	public static String PROP_VCAR_ID ="vcarId";
	public static String PROP_VCUSTOMER_ID ="vcustomerId";
	public static String PROP_VBRAND ="vbrand";
	public static String PROP_VPRODUCT ="vproduct";
	public static String PROP_VMODEL ="vmodel";
	public static String PROP_VCOLOR ="vcolor";
	public static String PROP_VVIN ="vvin";
	public static String PROP_VENGINE_NMB ="vengineNmb";
	public static String PROP_VRADIO_NMB ="vradioNmb";
	public static String PROP_VCARKEY_NMB ="vcarKeyNmb";
	public static String PROP_VLICENSE_NMB ="vlicenseNmb";
	public static String PROP_VPURCHASED_DATE ="vpurchasedDate";
	public static String PROP_VDEALER_ID ="vdealerId";
	public static String PROP_VDEALER_NAME ="vdealerName";
	public static String PROP_VSALESMAN ="vsalesman";
	public static String PROP_VWARRANTY_BEGINDATE ="vwarrantyBegindate";
	public static String PROP_VWARRANTY_ENDDATE ="vwarrantyEnddate";
	public static String PROP_VIS_WARRANTY_EXTEND ="visWarrantyExtend";
	public static String PROP_VCAR_SOURCE ="vcarSource";
	public static String PROP_VCAR_DSID ="vcarDsid";
	public static String PROP_VBUY_TYPE ="vbuyType";
	public static String PROP_VCAR_USAGE ="vcarUsage";
	public static String PROP_VCAR_STATUS ="vcarStatus";
	public static String PROP_VPAY_TYPE ="vpayType";
	public static String PROP_VISCHECKED ="vischecked";
	public static String PROP_NMILEAGE ="nmileage";
	public static String PROP_VMILEAGE_DATE ="vmileageDate";
	public static String PROP_NUNIT_PRICE ="nunitPrice";
	public static String PROP_VINVOICE_NUMBER ="vinvoiceNumber";
	public static String PROP_VNOTES ="vnotes";
	public static String PROP_VDMS_OWNER_ID ="vdmsOwnerId";
	public static String PROP_VCREATED ="vcreated";
	public static String PROP_DATE ="dcrtDate";
	public static String PROP_VUPDATED ="vupdated";
	public static String PROP_DUP_DATE ="dupDate";
	
	
	public BaseFordCar() {
		 initialize() ;
	}


	public BaseFordCar(String vcarId, String vcustomerId, String vbrand,
			String vproduct, String vmodel, String vcolor, String vvin,
			String vengineNmb, String vradioNmb, String vcarKeyNmb,
			String vlicenseNmb, String vpurchasedDate, String vdealerId,
			String vdealerName, String vsalesman, String vwarrantyBegindate,
			String vwarrantyEnddate, String visWarrantyExtend,
			String vcarSource, String vcarDsid, String vbuyType,
			String vcarUsage, String vcarStatus, String vpayType,
			String vischecked, Long nmileage, String vmileageDate,
			Long nunitPrice, String vinvoiceNumber, String vnotes,
			String vdmsOwnerId, String vcreated, Date dcrtDate,
			String vupdated, Date dupDate) {
		this.vcarId = vcarId;
		this.vcustomerId = vcustomerId;
		this.vbrand = vbrand;
		this.vproduct = vproduct;
		this.vmodel = vmodel;
		this.vcolor = vcolor;
		this.vvin = vvin;
		this.vengineNmb = vengineNmb;
		this.vradioNmb = vradioNmb;
		this.vcarKeyNmb = vcarKeyNmb;
		this.vlicenseNmb = vlicenseNmb;
		this.vpurchasedDate = vpurchasedDate;
		this.vdealerId = vdealerId;
		this.vdealerName = vdealerName;
		this.vsalesman = vsalesman;
		this.vwarrantyBegindate = vwarrantyBegindate;
		this.vwarrantyEnddate = vwarrantyEnddate;
		this.visWarrantyExtend = visWarrantyExtend;
		this.vcarSource = vcarSource;
		this.vcarDsid = vcarDsid;
		this.vbuyType = vbuyType;
		this.vcarUsage = vcarUsage;
		this.vcarStatus = vcarStatus;
		this.vpayType = vpayType;
		this.vischecked = vischecked;
		this.nmileage = nmileage;
		this.vmileageDate = vmileageDate;
		this.nunitPrice = nunitPrice;
		this.vinvoiceNumber = vinvoiceNumber;
		this.vnotes = vnotes;
		this.vdmsOwnerId = vdmsOwnerId;
		this.vcreated = vcreated;
		this.dcrtDate = dcrtDate;
		this.vupdated = vupdated;
		this.dupDate = dupDate;
		 initialize() ;
	}


	protected void initialize() {
	}
	
	
	//车辆编号
		private String vcarId;
		//客户编号
		private String vcustomerId;
		//品牌
		private String vbrand;
		//车型
		private String vproduct;
		//车型统一代码(5位码/6位码)
		private String vmodel;
		//车辆颜色
		private String vcolor;
		//Vin码
		private String vvin;
		//发动机编号
		private String vengineNmb;
		//收音机编号
		private String vradioNmb;
		//车钥匙编号
		private String vcarKeyNmb;
		//牌照号
		private String vlicenseNmb;
		//购车日期(20090101)
		private String vpurchasedDate;
		//经销商编号
		private String vdealerId;
		//经销商名称
		private String vdealerName;
		//销售员
		private String vsalesman;
		//保修开始日期(20090101)
		private String vwarrantyBegindate;
		//保修结束日期(20090101)
		private String vwarrantyEnddate;
		//是否延长保修
		private String visWarrantyExtend;
		//获得车辆来源（经销商出售/赠与/二手车市场）
		private String vcarSource;
		//车辆数据录入来源（DMS/呼叫中心/维修站）
		private String vcarDsid;
		//购车方式（正常途径/内买/团购）
		private String vbuyType;
		//车辆用途（个人/公司/出租车/军队）
		private String vcarUsage;
		//车辆状态（当前拥有/报废/转卖/被盗）
		private String vcarStatus;
		//付款方式（现金付清/贷款）
		private String vpayType;
		//是否验证过(可能是呼叫中心验证)
		private String vischecked;
		//当前里程数
		private Long nmileage;
		//当前里程数读取日期(20090101)
		private String vmileageDate;
		//最终车辆销售单价
		private Long nunitPrice;
		//最终车辆销售发票号码
		private String vinvoiceNumber;
		//备注
		private String vnotes;
		//DMS原始编号
		private String vdmsOwnerId;
		//数据创建人
		private String vcreated;
		//创建日期
		private Date dcrtDate;
		//数据更新人
		private String vupdated;
		//更新日期
		private Date dupDate;
		
		
	public String getVcarId() {
			return vcarId;
		}


		public void setVcarId(String vcarId) {
			this.vcarId = vcarId;
		}


		public String getVcustomerId() {
			return vcustomerId;
		}


		public void setVcustomerId(String vcustomerId) {
			this.vcustomerId = vcustomerId;
		}


		public String getVbrand() {
			return vbrand;
		}


		public void setVbrand(String vbrand) {
			this.vbrand = vbrand;
		}


		public String getVproduct() {
			return vproduct;
		}


		public void setVproduct(String vproduct) {
			this.vproduct = vproduct;
		}


		public String getVmodel() {
			return vmodel;
		}


		public void setVmodel(String vmodel) {
			this.vmodel = vmodel;
		}


		public String getVcolor() {
			return vcolor;
		}


		public void setVcolor(String vcolor) {
			this.vcolor = vcolor;
		}


		public String getVvin() {
			return vvin;
		}


		public void setVvin(String vvin) {
			this.vvin = vvin;
		}


		public String getVengineNmb() {
			return vengineNmb;
		}


		public void setVengineNmb(String vengineNmb) {
			this.vengineNmb = vengineNmb;
		}


		public String getVradioNmb() {
			return vradioNmb;
		}


		public void setVradioNmb(String vradioNmb) {
			this.vradioNmb = vradioNmb;
		}


		public String getVcarKeyNmb() {
			return vcarKeyNmb;
		}


		public void setVcarKeyNmb(String vcarKeyNmb) {
			this.vcarKeyNmb = vcarKeyNmb;
		}


		public String getVlicenseNmb() {
			return vlicenseNmb;
		}


		public void setVlicenseNmb(String vlicenseNmb) {
			this.vlicenseNmb = vlicenseNmb;
		}


		public String getVpurchasedDate() {
			return vpurchasedDate;
		}


		public void setVpurchasedDate(String vpurchasedDate) {
			this.vpurchasedDate = vpurchasedDate;
		}


		public String getVdealerId() {
			return vdealerId;
		}


		public void setVdealerId(String vdealerId) {
			this.vdealerId = vdealerId;
		}


		public String getVdealerName() {
			return vdealerName;
		}


		public void setVdealerName(String vdealerName) {
			this.vdealerName = vdealerName;
		}


		public String getVsalesman() {
			return vsalesman;
		}


		public void setVsalesman(String vsalesman) {
			this.vsalesman = vsalesman;
		}


		public String getVwarrantyBegindate() {
			return vwarrantyBegindate;
		}


		public void setVwarrantyBegindate(String vwarrantyBegindate) {
			this.vwarrantyBegindate = vwarrantyBegindate;
		}


		public String getVwarrantyEnddate() {
			return vwarrantyEnddate;
		}


		public void setVwarrantyEnddate(String vwarrantyEnddate) {
			this.vwarrantyEnddate = vwarrantyEnddate;
		}


		public String getVisWarrantyExtend() {
			return visWarrantyExtend;
		}


		public void setVisWarrantyExtend(String visWarrantyExtend) {
			this.visWarrantyExtend = visWarrantyExtend;
		}


		public String getVcarSource() {
			return vcarSource;
		}


		public void setVcarSource(String vcarSource) {
			this.vcarSource = vcarSource;
		}


		public String getVcarDsid() {
			return vcarDsid;
		}


		public void setVcarDsid(String vcarDsid) {
			this.vcarDsid = vcarDsid;
		}


		public String getVbuyType() {
			return vbuyType;
		}


		public void setVbuyType(String vbuyType) {
			this.vbuyType = vbuyType;
		}


		public String getVcarUsage() {
			return vcarUsage;
		}


		public void setVcarUsage(String vcarUsage) {
			this.vcarUsage = vcarUsage;
		}


		public String getVcarStatus() {
			return vcarStatus;
		}


		public void setVcarStatus(String vcarStatus) {
			this.vcarStatus = vcarStatus;
		}


		public String getVpayType() {
			return vpayType;
		}


		public void setVpayType(String vpayType) {
			this.vpayType = vpayType;
		}


		public String getVischecked() {
			return vischecked;
		}


		public void setVischecked(String vischecked) {
			this.vischecked = vischecked;
		}


		public Long getNmileage() {
			return nmileage;
		}


		public void setNmileage(Long nmileage) {
			this.nmileage = nmileage;
		}


		public String getVmileageDate() {
			return vmileageDate;
		}


		public void setVmileageDate(String vmileageDate) {
			this.vmileageDate = vmileageDate;
		}


		public Long getNunitPrice() {
			return nunitPrice;
		}


		public void setNunitPrice(Long nunitPrice) {
			this.nunitPrice = nunitPrice;
		}


		public String getVinvoiceNumber() {
			return vinvoiceNumber;
		}


		public void setVinvoiceNumber(String vinvoiceNumber) {
			this.vinvoiceNumber = vinvoiceNumber;
		}


		public String getVnotes() {
			return vnotes;
		}


		public void setVnotes(String vnotes) {
			this.vnotes = vnotes;
		}


		public String getVdmsOwnerId() {
			return vdmsOwnerId;
		}


		public void setVdmsOwnerId(String vdmsOwnerId) {
			this.vdmsOwnerId = vdmsOwnerId;
		}


		public String getVcreated() {
			return vcreated;
		}


		public void setVcreated(String vcreated) {
			this.vcreated = vcreated;
		}


		public Date getDcrtDate() {
			return dcrtDate;
		}


		public void setDcrtDate(Date dcrtDate) {
			this.dcrtDate = dcrtDate;
		}


		public String getVupdated() {
			return vupdated;
		}


		public void setVupdated(String vupdated) {
			this.vupdated = vupdated;
		}


		public Date getDupDate() {
			return dupDate;
		}


		public void setDupDate(Date dupDate) {
			this.dupDate = dupDate;
		}


	@Override
	public String toString() {
		return super.toString();
	}
}
