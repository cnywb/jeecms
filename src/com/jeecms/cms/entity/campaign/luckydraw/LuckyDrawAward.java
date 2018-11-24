package com.jeecms.cms.entity.campaign.luckydraw;

import java.io.Serializable;
import java.util.Date;


/**
 * 抽奖奖品
 * @author xuwen
 *
 */
public class LuckyDrawAward implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5069347220996727192L;
	public static String PROP_ID="id";
	public static String PROP_NAME="name";
	public static String PROP_MEMO="memo";
	public static String PROP_CREATE_TIME ="createTime";
	public static String PROP_UPDATE_TIME ="updateTime";
	
	
    private long id;
	
	private Integer quantity=0;//总数量
	
	private Integer remainingQty=0;//剩余数量
	
	private String name;//奖品名称
	
	private String grade;//奖品等级
	
	private String memo;//备注
	
	private String imageUrl="";//奖品图片
	
	private LuckyDraw luckyDraw;//所属活动
	
    private Date createTime=new Date();
    
    private Date updateTime=new Date();

    private String code;//代码
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LuckyDraw getLuckyDraw() {
		return luckyDraw;
	}

	public void setLuckyDraw(LuckyDraw luckyDraw) {
		this.luckyDraw = luckyDraw;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Integer getRemainingQty() {
		return remainingQty;
	}

	public void setRemainingQty(Integer remainingQty) {
		this.remainingQty = remainingQty;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
