package com.jeecms.cms.entity.campaign.answercontest;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonAutoDetect
@JsonIgnoreProperties(value={"contestAsk"})
public class ContestAnswer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -43110321527997085L;
	
	
	private long id;
	
	private ContestAsk contestAsk;//所属问题
	
	private String content;//问题内容
	
	private Boolean isCorrectAnswer;//是否正确答案
	
	private Integer sequence;//顺序
	  
    private String code;//代码
	
    private Date createTime=new Date();
    
	private Date updateTime=new Date();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ContestAsk getContestAsk() {
		return contestAsk;
	}

	public void setContestAsk(ContestAsk contestAsk) {
		this.contestAsk = contestAsk;
	}

	public Boolean getIsCorrectAnswer() {
		return isCorrectAnswer;
	}

	public void setIsCorrectAnswer(Boolean isCorrectAnswer) {
		this.isCorrectAnswer = isCorrectAnswer;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContestAnswer other = (ContestAnswer) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
