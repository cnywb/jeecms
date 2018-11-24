package com.jeecms.cms.dao.survey;

import java.util.List;

import com.jeecms.cms.entity.survey.SurveyAnswerSheet;

public interface SurveyAnswerSheetDao {
	public List<SurveyAnswerSheet> findByUserIdAndAnswerId(Long answerId,Integer userId);
	public void add(SurveyAnswerSheet t);
	public void addBatch(List<SurveyAnswerSheet> list);
	public List<SurveyAnswerSheet> findByUnitCondiction(String unitAnswerCode,String unitAnswerValue);
	public void addBatchForAnonymous(List<SurveyAnswerSheet> list);

}
