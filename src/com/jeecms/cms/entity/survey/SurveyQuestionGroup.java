package com.jeecms.cms.entity.survey;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonAutoDetect
@JsonIgnoreProperties(value={"survey"})
public class SurveyQuestionGroup  implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 7505489209342667950L;

	private long id;
	
    private Survey survey;
	
	private String name;//名称
	
	private String code;//代码

	private Date createTime=new Date();
    
    private Date updateTime=new Date();
    
    

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
    
    
    

}
