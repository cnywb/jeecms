/**
 * 
 */
package com.jeecms.point.action.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;
import com.jeecms.point.constant.RecordType;
import com.jeecms.point.manager.point.PointIncomeMng;
import com.jeecms.point.vo.point.PointIncomeQueryVo;
import com.jeecms.point.web.json.JSONParser;
import com.jeecms.point.web.query.QueryVo;

/**
 * @author wanglijun
 *
 */
@Controller
@RequestMapping("/admin/points/payout")
public class PointPayoutAdminAct {
	/** 首页 */
	private static final String INDEX_RETURN = "points/payout/index";
 
	
	@Autowired
	private PointIncomeMng pointIncomeMng;
	
	/***
	 * 首页显示数据
	 * @return
	 */
	@RequestMapping("/index.do")
	public String index(){
		return INDEX_RETURN;
	}
	
	/*** 查询列表信息 */
	@RequestMapping("/list.do")
	@ResponseBody
	public String list(QueryVo queryVo,PointIncomeQueryVo incomeQueryVo) {
		incomeQueryVo.setRecordType(RecordType.PAYOUT);
		Pagination pagination =pointIncomeMng.queryPagination(queryVo, incomeQueryVo);
		return JSONParser.toDataGridString(pagination,DateUtils.FORMAT_DATE_TIME_DEFAULT);
	}
}
