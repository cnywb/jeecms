package com.jeecms.cms.entity.survey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonAutoDetect
@JsonIgnoreProperties(value={"group"})//因为group没有配及时加载所以不作解析 
public class SurveyQuestion  implements Serializable{
	
	    /**
	 * 
	 */
	private static final long serialVersionUID = -242184207196083540L;

		private long id;
		
	    private SurveyQuestionGroup group;
		
		private String name;//名称
		
		private String code;//代码
		
		private Integer sequence;//顺序
		 
	    private Integer type;//0单选，1多选,2文本域,3下拉菜单,
	 
		private Date createTime=new Date();
	    
	    private Date updateTime=new Date();
	    
	    private List<SurveyAnswer> answerList=new ArrayList<SurveyAnswer>();
	    

		public List<SurveyAnswer> getAnswerList() {
			return answerList;
		}

		public void setAnswerList(List<SurveyAnswer> answerList) {
			this.answerList = answerList;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public SurveyQuestionGroup getGroup() {
			return group;
		}

		public void setGroup(SurveyQuestionGroup group) {
			this.group = group;
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
	    
	    

}
