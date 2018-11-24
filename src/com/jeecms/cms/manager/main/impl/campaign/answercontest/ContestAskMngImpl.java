package com.jeecms.cms.manager.main.impl.campaign.answercontest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.campaign.answercontest.ContestAnswerDao;
import com.jeecms.cms.dao.campaign.answercontest.ContestAskDao;
import com.jeecms.cms.entity.campaign.answercontest.ContestAnswer;
import com.jeecms.cms.entity.campaign.answercontest.ContestAsk;
import com.jeecms.cms.entity.campaign.answercontest.ContestAskAndAnswer;
import com.jeecms.cms.manager.campaign.answercontest.ContestAskMng;


@Service
@Transactional(rollbackFor=Exception.class) 
public class ContestAskMngImpl implements ContestAskMng {
	
	@Autowired
	private ContestAskDao contestAskDao;
	
	@Autowired
	private ContestAnswerDao contestAnswerDao;
	
	public List<ContestAsk> findAllByGroup(String group){
		List<ContestAsk> list=contestAskDao.findAllByGroup(group);
		for(ContestAsk ask:list){
			ask.setAnswersList(contestAnswerDao.findAllByAskId(ask.getId()));
		}
		return list;
	}
	
	/**
	 * 检查某个答题的答案是否正确
	 * @param askCode
	 * @param answerCodes
	 * @return
	 */
	public boolean isAnswersCorrect(String askCode,String answerCodes){
		ContestAsk ask=contestAskDao.findByCode(askCode);
		if(ask==null){
			return false;
		}
		ask.setAnswersList(contestAnswerDao.findAllByAskId(ask.getId()));
		String[] answerCodesArray=answerCodes.split(",");
		List<ContestAnswer> answers=ask.getAnswersList();
		int correctAnswerCount=0;
		for(ContestAnswer answer:answers){
			if(answer.getIsCorrectAnswer()==true){
				correctAnswerCount=correctAnswerCount+1;
			}
		}
		if(answerCodesArray.length!=correctAnswerCount){//如果用户传的答案数量与实际答案数量不同
			return false;
		}
		for(String answerCode:answerCodesArray){
			for(ContestAnswer answer:answers){
				if(answer.getCode().equals(answerCode)&&answer.getIsCorrectAnswer()==false){
					//如果有对不上的答案
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 检查所有答题是否正确
	 * @param askAndAnswers
	 * @return
	 */
	public boolean areAllAnswersCorrect(List<ContestAskAndAnswer> askAndAnswers){
		for(ContestAskAndAnswer aksAnswer:askAndAnswers){
			boolean isCorrect=isAnswersCorrect(aksAnswer.getAskCode(), aksAnswer.getAnswerCodes());
		    if(isCorrect==false){
		    	return false;
		    }
		}
		return true;
	}
	

}
