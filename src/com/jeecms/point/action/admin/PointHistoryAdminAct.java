/*
* Copyright (c)  2015, NewTouch
* All rights reserved. 
*
* $id: PointProductAdminAct.java 9552 May 22, 2015 10:43:02 AM MaoJiaWei$
*/
package com.jeecms.point.action.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.entity.PointLotteryHistory;
import com.jeecms.point.manager.lottery.LotteryMng;
import com.jeecms.point.vo.activity.HistoryVo;
import com.jeecms.point.web.json.JSONParser;
import com.jeecms.point.web.query.QueryVo;
/**
 * <p>
 * Title: 
 * </p>
 * <p>
 * Description: 
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * 
 * @author MaoJiaWei
 * @version 1.0
 */
@Controller
@RequestMapping("/admin/points/history")
public class PointHistoryAdminAct {
	private static final Logger logger = LoggerFactory
			.getLogger(PointLotteryHistory.class);
	/** 首页 */
	private static final String INDEX_RETURN = "points/history/index";
	@Autowired
	private LotteryMng lotteryMng;
	/** 列表页面 */
	@RequestMapping("/index.do")
	public String index() {
		logger.info("抽奖历史页面...");
		return INDEX_RETURN;
	}
	
	/*** 查询列表信息 */
	@RequestMapping("/list.do")
	@ResponseBody
	public String list(QueryVo queryVo, String userName,Boolean isLotteried) {
		Pagination pagination = this.lotteryMng.getPage(queryVo,
				userName,isLotteried);
		List<HistoryVo> list = new ArrayList<HistoryVo>();
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 ");
		for(Object p:pagination.getList()){
			if(p instanceof PointLotteryHistory){
				PointLotteryHistory plh = (PointLotteryHistory)p;
				HistoryVo h = new HistoryVo();
				h.setUserId(plh.getUserId());
				h.setUserName(plh.getUserName());
				h.setOpenId(plh.getOpenId());
				h.setVin(plh.getVin());
				h.setLotteryResult(plh.getLotteryResult());
				h.setLotteried(plh.getIsLotteried());
				h.setAcitvityCode(plh.getAcitvityCode());
				h.setLotteryDate(myFmt.format(plh.getLotteryDate()));
				list.add(h);
			}
		}
		pagination.setList(list);
		return JSONParser.toDataGridString2(pagination);
	}
}
