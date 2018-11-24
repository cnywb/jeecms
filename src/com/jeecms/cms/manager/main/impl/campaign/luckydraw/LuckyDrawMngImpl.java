package com.jeecms.cms.manager.main.impl.campaign.luckydraw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.campaign.lucydraw.LuckyDrawAwardDao;
import com.jeecms.cms.dao.campaign.lucydraw.LuckyDrawDao;
import com.jeecms.cms.dao.campaign.lucydraw.LuckyDrawLogDao;
import com.jeecms.cms.dao.campaign.lucydraw.LuckyDrawResultDao;
import com.jeecms.cms.dao.campaign.lucydraw.LuckyDrawRuleDao;
import com.jeecms.cms.entity.campaign.answercontest.ContestAskAndAnswer;
import com.jeecms.cms.entity.campaign.luckydraw.LuckyDraw;
import com.jeecms.cms.entity.campaign.luckydraw.LuckyDrawAward;
import com.jeecms.cms.entity.campaign.luckydraw.LuckyDrawLog;
import com.jeecms.cms.entity.campaign.luckydraw.LuckyDrawResponse;
import com.jeecms.cms.entity.campaign.luckydraw.LuckyDrawResult;
import com.jeecms.cms.entity.campaign.luckydraw.LuckyDrawRule;
import com.jeecms.cms.entity.campaign.sfdj.MemberStory;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.manager.campaign.answercontest.ContestAskMng;
import com.jeecms.cms.manager.campaign.luckydraw.LuckyDrawMng;
import com.jeecms.cms.manager.campaign.sfdj.MemberStoryMng;
import com.jeecms.cms.manager.campaign.sfdj.MemberStoryPraiseMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;


@Service
@Transactional(rollbackFor=Exception.class) 
public class LuckyDrawMngImpl implements LuckyDrawMng {
	
	
	@Autowired
	private LuckyDrawAwardDao luckyDrawAwardDao;
	@Autowired
	private LuckyDrawDao luckyDrawDao;
	@Autowired
	private LuckyDrawLogDao luckyDrawLogDao;
	@Autowired
	private LuckyDrawResultDao luckyDrawResultDao;
	@Autowired
	private LuckyDrawRuleDao luckyDrawRuleDao;
	
	@Autowired
	private MemberStoryPraiseMng memberStoryPraiseMng;
	@Autowired
	private MemberStoryMng memberStoryMng;
	@Autowired
	private ContestAskMng  contestAskMng;
	
	/**
	 * 抽奖实现方法
	 * @param request
	 * @param code
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public LuckyDrawResponse doDraw(HttpServletRequest request,String code){
		LuckyDrawResponse resp=new LuckyDrawResponse();
		CmsUser user = CmsUtils.getUser(request);
		
		if(user==null){
			resp.setStatus(0);
			resp.setMessage("用户未登录!");
			return resp;
		}
		LuckyDraw luckyDraw=luckyDrawDao.findByCode(code);
		if(luckyDraw==null){
			resp.setStatus(1);
			resp.setMessage("抽奖活动不存在!");
			return resp;
		}
		if(luckyDraw.getStatus()==0){
			resp.setStatus(2);
			resp.setMessage("抽奖活动已停用!");
			return resp;
		}

		if(luckyDraw.getStartTime().getTime()>(new Date()).getTime()){
			resp.setStatus(3);
			resp.setMessage("抽奖活动未开始!");
			return resp;
		}
		if(luckyDraw.getEndTime().getTime()<(new Date()).getTime()){
			
			resp.setStatus(4);
			resp.setMessage("抽奖活动已结束!");
			return resp;
		}
		if(luckyDraw.getParticipantType()==1&&user.getGroup().getId()!=2){
			resp.setStatus(5);
			resp.setMessage("只有认证用户才能参与抽奖!");
			return resp;
		}
		
		int qualificationFlag=0;
		if(luckyDraw.getParticipantType()==2&&code.equals("SFDJ")){
			 qualificationFlag=checkUserDrawQualificationForSFDJ(user.getId());
			if(qualificationFlag==0){
						resp.setStatus(6);
						resp.setMessage("只有上传全家福的车主及投票的会员才有机会参与抽奖!");
						return resp;
			}
		}
		
		if(luckyDraw.getChanceQty()!=0){//如果有设定最大参与抽奖次数
			int c=luckyDrawLogDao.getTotalCountByLuckyDrawIdAndCreaterId(luckyDraw.getId(), user.getId());
			if(c>=luckyDraw.getChanceQty()){
			 resp.setStatus(7);
			 resp.setMessage("抽奖次数超限!");
			 return resp;
			}
		}
		
		if(luckyDraw.getAwardQty()!=0){//如果有设定最大中奖次数
			int c=luckyDrawResultDao.getTotalCountByLuckyDrawIdAndCreaterId(luckyDraw.getId(), user.getId());
			if(c>=luckyDraw.getAwardQty()){
			 resp.setStatus(8);
			 resp.setMessage("您已经中过奖,不能继续抽奖!");
			 return resp;
			}
		}
		LuckyDrawLog drawLog=new LuckyDrawLog();
		drawLog.setCreater(user);
		drawLog.setLuckyDraw(luckyDraw);
		
		List<LuckyDrawRule> luckyDrawRuleList=luckyDrawRuleDao.queryAllByLuckyDrawId(luckyDraw.getId());
		String currentDateStr=DateUtils.format(new Date(), DateUtils.FORMAT_DATE_DEFAULT);
		Date currentDate=DateUtils.parseDate(currentDateStr, DateUtils.FORMAT_DATE_DEFAULT);
		List<LuckyDrawRule> needToDrawList=new ArrayList<LuckyDrawRule>();
		for(LuckyDrawRule t:luckyDrawRuleList){
			if(t.getAwardDate().getTime()<=currentDate.getTime()){//抽奖日期在当天或是当天之前
				if(t.getRemainingQty()>0){
					needToDrawList.add(t);
				}
			}
		}
		    int rate=luckyDraw.getAwardRate();
		    for(int i=0;i<rate;i++){//添加非奖品随机对象
		    	LuckyDrawRule randomLDR=new LuckyDrawRule();
		    	randomLDR.setId(0L);
		    	needToDrawList.add(randomLDR);
		    }
		    Collections.shuffle(needToDrawList); //随机打乱
			LuckyDrawRule needRule=needToDrawList.get(0);//抽取第一条
			
			/*if(needRule.getId()!=0L&&!isQualifiyAwardForSFDJ(needRule.getAward().getCode(), qualificationFlag)){
				drawLog.setStatus(0);
				resp.setStatus(10);
			    resp.setMessage("抱歉,未抽中!");
			    luckyDrawLogDao.add(drawLog);
				return resp;
			}*/
			
			
			 if(needRule.getId()!=0L){//抽到合法奖品
			    Long awardId=needRule.getAward().getId();
				luckyDrawAwardDao.evictEntity(needRule.getAward());//清除缓存中的对象
				LuckyDrawAward needAward=luckyDrawAwardDao.loadAndLockEntity(awardId);
				
				Long ruleId=needRule.getId();
				luckyDrawRuleDao.evictEntity(needRule);//清除缓存中的对象，否则无法锁定
				needRule=luckyDrawRuleDao.loadAndLockEntity(ruleId);//锁定每日抽奖规则
			
				if(needAward.getRemainingQty()>0&&needRule.getRemainingQty()>0){//抽中奖品奖池余数与当前抽奖规则中奖品的余数大于0

			    needAward.setRemainingQty(needAward.getRemainingQty()-1);//奖品总余数减一
				luckyDrawAwardDao.update(needAward);
				
				needRule.setRemainingQty(needRule.getRemainingQty()-1);//当前规则奖品余下奖品减一
				luckyDrawRuleDao.update(needRule);
				
				LuckyDrawResult result=new LuckyDrawResult();
				result.setAward(needAward);
				result.setCreater(user);
				luckyDrawResultDao.add(result);//保存中奖结果 
				
				drawLog.setStatus(1);//设置抽奖日志状态为抽中
				
		        resp.setStatus(9);
				resp.setMessage("恭喜您中奖！");
				resp.setCode(needRule.getAward().getCode());
				resp.setName(needRule.getAward().getName());
				}else{
					drawLog.setStatus(0);
					resp.setStatus(10);
				    resp.setMessage("抱歉,未抽中!");
				}
			
		}else{
			drawLog.setStatus(0);
			resp.setStatus(10);
		    resp.setMessage("抱歉,未抽中!");
		}
		luckyDrawLogDao.add(drawLog);
		return resp;
	}
	
	

	private int checkUserDrawQualificationForSFDJ(Integer createrId){
		int storyCount=memberStoryMng.getTotalCountByCreaterId(createrId);
		if(storyCount>0){
			return 1;//发表过全家福的车主
		}
		int storyPraiseCount=memberStoryPraiseMng.getTotalCountByCreaterId(createrId);
		if(storyPraiseCount>0){
			return 2;//参与过投票的用户
		}
		return 0;
	}
	
	public boolean isQualifiyAwardForSFDJ(String awardCode,int qualificationFlag){
		if(qualificationFlag==1&&("1".equals(awardCode)||"2".equals(awardCode)||"3".equals(awardCode)||"4".equals(awardCode)||"5".equals(awardCode))){
		    
			return true;//如果是发表全家福的用户抽到了代码为1到5的奖品说明合法
		}
		if(qualificationFlag==2&&("6".equals(awardCode)||"7".equals(awardCode)||"8".equals(awardCode))){
			return true;//如果是投票用户抽到代码为6到8的奖品说明合法
		}
		return false;//不合法
	}
	
	
	public List<LuckyDraw> findAllByCurrentDate(){
		return luckyDrawDao.findAllByCurrentDate();
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public LuckyDrawResponse doDrawForAnswerContest(HttpServletRequest request,String code,List<ContestAskAndAnswer> askAndAnswers){
		boolean areAllAnswersCorrect=contestAskMng.areAllAnswersCorrect(askAndAnswers);
		if(!areAllAnswersCorrect){
			LuckyDrawResponse resp=new LuckyDrawResponse();
			resp.setStatus(11);
			resp.setMessage("未全部回答正确!");
			return resp;
		}
		return doDraw(request, code);
	}
}
