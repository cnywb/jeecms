package com.jeecms.cms.entity.survey;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.jeecms.cms.entity.main.CmsUser;
@JsonAutoDetect
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class SurveyLog  implements Serializable{
	
	
	    /**
	 * 
	 */
	private static final long serialVersionUID = 7275044339089215006L;

		private long id;
	
	    private CmsUser user;
	    
	    private Survey survey;
	    
		private Date createTime=new Date();
	    
	    private Date updateTime=new Date();

		public Survey getSurvey() {
			return survey;
		}

		public void setSurvey(Survey survey) {
			this.survey = survey;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public CmsUser getUser() {
			return user;
		}

		public void setUser(CmsUser user) {
			this.user = user;
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
