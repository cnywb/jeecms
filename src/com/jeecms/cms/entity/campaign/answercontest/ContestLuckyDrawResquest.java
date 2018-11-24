package com.jeecms.cms.entity.campaign.answercontest;

import java.util.ArrayList;
import java.util.List;



public class ContestLuckyDrawResquest {

	private String code;
	
	private List<ContestAskAndAnswer> askAndAnswers=new ArrayList<ContestAskAndAnswer>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<ContestAskAndAnswer> getAskAndAnswers() {
		return askAndAnswers;
	}

	public void setAskAndAnswers(List<ContestAskAndAnswer> askAndAnswers) {
		this.askAndAnswers = askAndAnswers;
	}
	
	
	
}
