package com.jeecms.cms.entity.survey;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.jeecms.cms.entity.main.CmsUser;


@JsonAutoDetect
@JsonIgnoreProperties(value={"answer"})
public class SurveyAnswerSheet  implements Serializable{
	
	    /**
	 * 
	 */
	private static final long serialVersionUID = -5720581711141600270L;

		private long id;
		
	    private SurveyAnswer answer;
	    
	    private CmsUser user;
	    
	    private String answerMemo;
		
		private Date createTime=new Date();
	    
	    private Date updateTime=new Date();
	    
	    private String sheetNo;
	    
	   

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public SurveyAnswer getAnswer() {
			return answer;
		}

		public void setAnswer(SurveyAnswer answer) {
			this.answer = answer;
		}

		public CmsUser getUser() {
			return user;
		}

		public void setUser(CmsUser user) {
			this.user = user;
		}

		public String getAnswerMemo() {
			return answerMemo;
		}

		public void setAnswerMemo(String answerMemo) {
			this.answerMemo = answerMemo;
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

		public String getSheetNo() {
			return sheetNo;
		}

		public void setSheetNo(String sheetNo) {
			this.sheetNo = sheetNo;
		}

		
	    
	    

}
