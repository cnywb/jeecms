package com.jeecms.cms.dao.main.impl.campaign.answercontest;

import java.util.List;

import com.jeecms.cms.dao.campaign.answercontest.ContestAnswerDao;
import com.jeecms.cms.entity.campaign.answercontest.ContestAnswer;
import com.jeecms.common.hibernate3.HibernateBaseDao;

public class ContestAnswerDaoImpl extends HibernateBaseDao<ContestAnswer,Long>implements ContestAnswerDao {

	@Override
	protected Class<ContestAnswer> getEntityClass() {
		// TODO Auto-generated method stub
		return ContestAnswer.class;
	}

	
	public List<ContestAnswer> findAllByAskId(long askId) {
		String hql = "select bean from ContestAnswer bean where bean.contestAsk.id=?";
		List<ContestAnswer> list=find(hql,askId);
		return list;
	}
}
