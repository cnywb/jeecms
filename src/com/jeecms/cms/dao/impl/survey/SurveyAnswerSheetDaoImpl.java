package com.jeecms.cms.dao.impl.survey;

import java.util.List;

import com.jeecms.cms.dao.survey.SurveyAnswerSheetDao;
import com.jeecms.cms.entity.survey.SurveyAnswerSheet;
import com.jeecms.common.hibernate3.HibernateBaseDao;

public class SurveyAnswerSheetDaoImpl extends
		HibernateBaseDao<SurveyAnswerSheet, Long> implements
		SurveyAnswerSheetDao {

	@Override
	protected Class<SurveyAnswerSheet> getEntityClass() {
		// TODO Auto-generated method stub
		return SurveyAnswerSheet.class;
	}
	
	
	public List<SurveyAnswerSheet> findByUserIdAndAnswerId(Long answerId,Integer userId){
		String hql = "select bean from SurveyAnswerSheet bean where bean.answer.id=? and bean.user.id=?";
		List<SurveyAnswerSheet> list=find(hql,answerId,userId);
		return list;
	}
	
	
	public void add(SurveyAnswerSheet t){
		List<SurveyAnswerSheet> list=findByUserIdAndAnswerId(t.getAnswer().getId(), t.getUser().getId());
		if(list.size()==0){
		getSession().save(t);
		}
	}
	public void addBatch(List<SurveyAnswerSheet> list){
		for(SurveyAnswerSheet t:list){
			if(t!=null){
				 add(t);
			}
		
		}
	}
	
	public void addBatchForAnonymous(List<SurveyAnswerSheet> list){
		for(SurveyAnswerSheet t:list){
			if(t!=null){
			getSession().save(t);
			}
		}
	}
	
	public List<SurveyAnswerSheet> findByUnitCondiction(String unitAnswerCode,String unitAnswerValue){
		String hql = "select bean from SurveyAnswerSheet bean where bean.answer.code=? and bean.sheetNo=?";
		List<SurveyAnswerSheet> list=find(hql,unitAnswerCode.trim(),unitAnswerValue.trim());
		return list;
	}

}
