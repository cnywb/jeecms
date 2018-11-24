/**
 * 
 */
package com.jeecms.wxpoint.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.FordClubMember;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.cms.manager.main.FordClubMemberMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.point.entity.PointCalculateResult;
import com.jeecms.point.manager.point.PointCalculateMng;
import com.jeecms.point.manager.point.PointIncomeMng;
import com.jeecms.point.manager.point.PointUserMng;
import com.jeecms.point.vo.point.PointIncomeQueryVo;
import com.jeecms.point.web.query.QueryVo;
import com.jeecms.wxpoint.vo.PointResultVo;

/**
 * @author wanglijun
 *
 */
@Controller
@RequestMapping("/wxpoints/")
public class WeiXinPointAction {
	
	private static final Logger logger=LoggerFactory.getLogger(WeiXinPointAction.class);
	/**微信积分首页*/
	private static final  String  INDEX_RETURN="/WEB-INF/t/cms/www/red/wxpoints/index.html";
	/**微信积分规则页面*/
	private static final  String RULE_RETURN="/WEB-INF/t/cms/www/red/wxpoints/rule.html";
	/**微信积分查询页面*/
	private static final  String QUERY_RETURN="/WEB-INF/t/cms/www/red/wxpoints/query.html";
	/**微信积分明细*/
	private static final  String QUEYR_VIEW_RETURN="/WEB-INF/t/cms/www/red/wxpoints/query-view.html";
	/**微信积分获取页面*/
	private static final  String ACCUMULATE_RETURN="/WEB-INF/t/cms/www/red/wxpoints/accumulate.html";
	/**微信积分消费页面*/
	private static final String  CONSUME_RETURN="/WEB-INF/t/cms/www/red/wxpoints/consume.html";
	/**积分用户*/
	@Autowired
	private PointUserMng pointUserMng;
	/**用户类*/
	@Autowired
	private CmsUserMng cmsUserMng;
	/**用户车辆信息*/
	@Autowired
	private FordClubMemberMng  fordClubMemberMng;
	/**积分收入*/
	@Autowired
	private PointIncomeMng pointIncomeMng;
	/***/
	@Autowired
	private PointCalculateMng pointCalculateMng;
	
	/**首页*/
	@RequestMapping("index.jhtml")
	public String index(String openId,Model model){
		model.addAttribute("openId", openId);
		return INDEX_RETURN;
	}
	
	/**规则页面*/
	@RequestMapping("rule.jhtml")
	public String  rule(String openId,Model model){
		model.addAttribute("openId", openId);
		return RULE_RETURN;
	}
	
	/**积分查询*/
	@RequestMapping("query.jhtml")
	public String  query(String openId,Model model){
		model.addAttribute("openId", openId);
		UnifiedUser unifiedUser=pointUserMng.findByOpenId(openId);
		model.addAttribute("unifiedUser", unifiedUser);
		List<String> vins= new ArrayList<String>(5);
		String  vname=StringUtils.EMPTY;
		String  vmobile=StringUtils.EMPTY;
		if(unifiedUser!=null){
			CmsUser user=cmsUserMng.findById(unifiedUser.getId());
			model.addAttribute("user", user);
			List<FordClubMember> clubMembers=fordClubMemberMng.getFordClubMemberByUid(unifiedUser.getId());
			//读取vin码
			if(!CollectionUtils.isEmpty(clubMembers)){
				for(FordClubMember member:clubMembers){
					vins.add(member.getVvin());
					if(StringUtils.isEmpty(vname)){
						vname=member.getVname();
					}
					if(StringUtils.isEmpty(vmobile)){
						vmobile=member.getVmobile();
					}
					
				}
			}
			logger.info("vmobile:"+vmobile+" vname:"+vname);
			model.addAttribute("vmobile",vmobile);
			model.addAttribute("vname",vname);
			model.addAttribute("vins", vins);
		}
	
		return QUERY_RETURN;
	}
	
	/**积分明细查询*/
	@RequestMapping("queryview.jhtml")
	public String  queryView(String openId,QueryVo queryVo,PointIncomeQueryVo pointIncomeQueryVo,HttpServletRequest request, HttpServletResponse response,ModelMap model){
		model.addAttribute("openId", openId);
		model.addAttribute("recordType",pointIncomeQueryVo.getRecordType());
		queryVo.setRows(10);
		UnifiedUser unifiedUser=pointUserMng.findByOpenId(openId);
		if(unifiedUser!=null){
			Long userId=Long.valueOf(unifiedUser.getId());
			pointIncomeQueryVo.setUserId(userId);
			Pagination pagination=pointIncomeMng.queryPagination(queryVo,pointIncomeQueryVo);
			model.addAttribute("points", pagination.getList());
			model.put("pagination", pagination);
		}
		return QUEYR_VIEW_RETURN;
	}
	
	/**积分获取页面*/
	@RequestMapping("accumulate.jhtml")
	public String accumulate(String openId,HttpServletResponse response,ModelMap model){
		model.addAttribute("openId", openId);
		return ACCUMULATE_RETURN;
	}
	
	/**积分消费页面*/
	@RequestMapping("consume.jhtml")
	public String consume(String openId,HttpServletResponse response,ModelMap model){
		model.addAttribute("openId", openId);
		return CONSUME_RETURN;
	}
	
	/**微信分享积分*/
	@RequestMapping(value="share.jhtml", method = RequestMethod.GET)
    @ResponseBody
	public void share(String openId,String ruleno,HttpServletResponse response,ModelMap model){
		UnifiedUser unifiedUser=pointUserMng.findByOpenId(openId);
		PointResultVo resultVo=null;
		//判断用户是否存在
		if(unifiedUser==null){
			resultVo=new PointResultVo("01", "未关注微信",ruleno);
			ResponseUtils.renderJson(response,JSON.toJSONString(resultVo));
			return;
		}
		//判断是否车主
		CmsUser user=cmsUserMng.findById(unifiedUser.getId());
		if(user.getGroup().getId()!=2){
			resultVo=new PointResultVo("02", "用户未绑定车主",ruleno);
			ResponseUtils.renderJson(response,JSON.toJSONString(resultVo));
			return;
		}
		//分享规则
		if("00".equals(ruleno)){
			PointCalculateResult calculateResult=pointCalculateMng.sharePoint(user.getId());
			if(calculateResult.isResult()){
				resultVo=new PointResultVo("00", "恭喜您获取"+calculateResult.getNum()+"积分！",calculateResult.getNum(),ruleno);
			}else{
				if(calculateResult.getNum()>=calculateResult.getMaxNum()){
					resultVo=new PointResultVo("04", "今天已累计达上限:"+calculateResult.getNum()+"积分！",ruleno);
				}else{
					resultVo=new PointResultVo("05", "积分未记录成功",ruleno);
				}
			}
			ResponseUtils.renderJson(response,JSON.toJSONString(resultVo));
			return;
		}else if("01".equals(ruleno)){
			//点赞规则
			PointCalculateResult calculateResult=pointCalculateMng.prasiePoint(user.getId());
			if(calculateResult.isResult()){
				resultVo=new PointResultVo("00", "恭喜您获取"+calculateResult.getNum()+"积分！",calculateResult.getNum(),ruleno);
			}else{
				if(calculateResult.getNum()>=calculateResult.getMaxNum()){
					resultVo=new PointResultVo("04", "今天已累计达上限:"+calculateResult.getNum()+"积分！",ruleno);
				}else{
					resultVo=new PointResultVo("05", "积分未记录成功",ruleno);
				}
			}
			ResponseUtils.renderJson(response,JSON.toJSONString(resultVo));
			return;
		}else{
			resultVo=new PointResultVo("03", "积分规则代码不正确",ruleno);
		}
		ResponseUtils.renderJson(response,JSON.toJSONString(resultVo));
	}
}
