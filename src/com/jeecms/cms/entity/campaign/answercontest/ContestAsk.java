package com.jeecms.cms.entity.campaign.answercontest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonAutoDetect
@JsonIgnoreProperties(value={"answers"})
public class ContestAsk {

	
	  private long id;
	  
	  private String content;//问题内容
	  
	  private Integer sequence;//顺序
	  
	  private String code;//代码
	  
	  private String group;//组
	  
	  private Integer type;//0单选，1多选
	  
      private Date createTime=new Date();
	    
	  private Date updateTime=new Date();

	  
	  private Set<ContestAnswer> answers=new HashSet<ContestAnswer>();
	  
	  private  List<ContestAnswer> answersList=new ArrayList<ContestAnswer>();

	  
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public java.util.Set<ContestAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(java.util.Set<ContestAnswer> answers) {
		this.answers = answers;
	}

	public List<ContestAnswer> getAnswersList() {
		
		/*Iterator<ContestAnswer>it=answers.iterator();
		while(it.hasNext()){
			answersList.add(it.next());
		}*/
		System.out.print("答案个数----------"+answersList.size());
		return answersList;
	}

	public void setAnswersList(List<ContestAnswer> answersList) {
		this.answersList = answersList;
	}
	  
}
