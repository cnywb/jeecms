package com.jeecms.cms.entity.survey;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonAutoDetect
@JsonIgnoreProperties(value={"question"})
public class SurveyAnswer  implements Serializable{
	
	    /**
	 * 
	 */
	private static final long serialVersionUID = 6309675197737675070L;

		private long id;
		
	    private SurveyQuestion question;
		
		private String name;//名称
		
		private String code;//代码
		
		private Integer sequence;//顺序
		
		private Boolean needMemo;//是否需要说明
		 
		private Date createTime=new Date();
	    
	    private Date updateTime=new Date();

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public SurveyQuestion getQuestion() {
			return question;
		}

		public void setQuestion(SurveyQuestion question) {
			this.question = question;
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

		public Integer getSequence() {
			return sequence;
		}

		public void setSequence(Integer sequence) {
			this.sequence = sequence;
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

		public Boolean getNeedMemo() {
			return needMemo;
		}

		public void setNeedMemo(Boolean needMemo) {
			this.needMemo = needMemo;
		}
	    
	    

}
