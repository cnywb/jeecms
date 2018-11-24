/**
 * 
 */
package com.jeecms.point.action.admin;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.bbs.entity.BbsTopic;
import com.jeecms.bbs.manager.BbsTopicMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.point.constant.Channel;
import com.jeecms.point.constant.RecordType;
import com.jeecms.point.constant.RuleConstant;
import com.jeecms.point.manager.point.PointIncomeMng;
import com.jeecms.point.vo.point.AddPointNumVo;
import com.jeecms.point.vo.point.PointIncomeQueryVo;
import com.jeecms.point.web.json.JSONParser;
import com.jeecms.point.web.query.QueryVo;

/**
 * 积分收益管理
 * @author wanglijun
 *
 */
@Controller
@RequestMapping("/admin/points/income")
public class PointIncomeAdminAct {
	/**日志*/
	private static final Logger logger=LoggerFactory.getLogger(PointIncomeAdminAct.class);
	/***
	 * 积分收入
	 */
	@Autowired
	private PointIncomeMng pointIncomeMng;
	@Autowired
	private UnifiedUserMng unifiedUserMng;
	@Autowired
	private  BbsTopicMng bbsTopicMng;
	
	/** 首页 */
	private static final String INDEX_RETURN = "points/income/index";
	/** 添加页面 */
	private static final String ADD_USER_POINTNUM = "points/income/userpointnum";
	/** 添加页面 */
	private static final String ADD_TOPIC_POINTNUM = "points/income/topicpointnum";
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
	public String list(QueryVo queryVo,PointIncomeQueryVo pointIncomeQueryVo) {		
		pointIncomeQueryVo.setRecordType(RecordType.INCOME);
		Pagination pagination =pointIncomeMng.queryPagination(queryVo, pointIncomeQueryVo);
		return JSONParser.toDataGridString(pagination,DateUtils.FORMAT_DATE_TIME_DEFAULT);
	}
	
	/*** 用户加积分页面 */
	@RequestMapping("/adduserpointnum.do")
	public String addUserPointNum() {
		return ADD_USER_POINTNUM;
	}
	
	/***贴子加积分页面 */
	@RequestMapping("/addtopicpointnum.do")
	public String addTopicPointNum() {
		return ADD_TOPIC_POINTNUM;
	}
	
	/*** 保存用户加积分页面 */
	@RequestMapping("/saveuserpointnum.do")
	public String saveUserPointNum(AddPointNumVo addPointNumVo){
		UnifiedUser unifiedUser=unifiedUserMng.getByUsername(addPointNumVo.getUsername());
		pointIncomeMng.pointManual(RuleConstant.POINT_MANUAL, unifiedUser.getId(), Channel.PC,addPointNumVo);
		logger.info(addPointNumVo.toString());
		return INDEX_RETURN;
	}
	
	/***检查用户是否存在*/
	@RequestMapping("checkusername.do")
	@ResponseBody
	public String checkUsername(@RequestParam(required = false)String username){
		if(StringUtils.isNotEmpty(username)){
			UnifiedUser unifiedUser=unifiedUserMng.getByUsername(username);
			if(unifiedUser!=null){
				return "true";
			}
		}
		return "false";
	}
	
	@RequestMapping("checktopicid.do")
	@ResponseBody
	public String checkTopicId(
			@RequestParam(required = false)Integer topicId){
		if( topicId!=null){
			BbsTopic bbsTopic=this.bbsTopicMng.findById(topicId);
			if(bbsTopic!=null){
				return "true";
			}
		}
		return "false";
	}
	
	
	/***保存贴子加积分页面 */
	@RequestMapping("/savetopicpointnum.do")
	public String saveTopicPointNum(AddPointNumVo addPointNumVo) {	
		logger.info(addPointNumVo.toString());
		String ruleNo=addPointNumVo.getAddType()==0?RuleConstant.TOPIC_DISGEST:RuleConstant.TOP_TOPIC;
		UnifiedUser unifiedUser=unifiedUserMng.getByUsername(addPointNumVo.getUsername());
		pointIncomeMng.pointManual(ruleNo,unifiedUser.getId(), Channel.PC,addPointNumVo);
		return INDEX_RETURN;
	}
}
