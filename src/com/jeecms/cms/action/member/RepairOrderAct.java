package com.jeecms.cms.action.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.jeecms.cms.entity.main.ClubDictionary;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.FordCar;
import com.jeecms.cms.entity.main.FordRepair;
import com.jeecms.cms.entity.main.FordRepairAddition;
import com.jeecms.cms.entity.main.FordRepairLabor;
import com.jeecms.cms.entity.main.FordRepairRepairpart;
import com.jeecms.cms.entity.main.FordRepairSalepart;
import com.jeecms.cms.entity.main.TempDeal;
import com.jeecms.cms.entity.main.TempElec;
import com.jeecms.cms.manager.main.ClubDictionaryMng;
import com.jeecms.cms.manager.main.FordCarMng;
import com.jeecms.cms.manager.main.FordRepairAdditionMng;
import com.jeecms.cms.manager.main.FordRepairLaborMng;
import com.jeecms.cms.manager.main.FordRepairMng;
import com.jeecms.cms.manager.main.FordRepairRepairpartMng;
import com.jeecms.cms.manager.main.FordRepairSalepartMng;
import com.jeecms.cms.manager.main.FordRepairWebMng;
import com.jeecms.cms.manager.main.TempDealMng;
import com.jeecms.cms.manager.main.TempElecMng;
import com.jeecms.cms.vo.CardScoreInfoVo;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;

@Controller
public class RepairOrderAct {
	private static final Logger log = LoggerFactory
			.getLogger(RepairOrderAct.class);
	
	/**
	 * 电子账单列表查询
	 */
	@RequestMapping(value = "/member/allDealElec.jspx", method = RequestMethod.GET)
	public String allDealElec(HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		FrontUtils.frontData(request, model, site);
			List<TempDeal> list1 = tempDealMng.getUsableTempDeal(user.getId());
			model.put("data1", list1);
			List<TempElec> list2 = tempElecMng.getAllTempElecByUserId(user.getId());
			model.put("data2", list2);
			List<TempDeal> list3 = tempDealMng.getFailureTempDeal(user.getId());
			model.put("data3", list3);
			model.put("pageNo", 1);//我的朋友标签必须
		return "/WEB-INF/t/cms/www/red/member/self02.html";
	}
	
	/**
	 * 电子账单详情查询
	 */
	@RequestMapping(value = "/member/tempElecDetails.jspx", method = RequestMethod.GET)
	public String tempElecDetails(String accountid,HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		Long id = 0L;
		if(accountid != null && !accountid.equals(""))
			id = Long.parseLong(accountid);
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		FrontUtils.frontData(request, model, site);
		TempDeal tempDeal = tempDealMng.findById(id);
		model.put("tempdeal", tempDeal);
		List<TempElec> list = tempElecMng.getTempElecByTempDealId(id);
		model.put("tempelecs", list);
		model.put("pageNo", 1);//我的朋友标签必须
		return "/WEB-INF/t/cms/www/red/member/self05.html";
	}
	
	/**
	 * 维修工单列表查询
	 */
	@RequestMapping(value = "/member/service_history.jspx", method = RequestMethod.GET)
	public String service_history(HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
//		int pageNo = (Integer)model.get("pageNo");
//		List<TempDeal> list = tempDealMng.getTop3TempDealByUserId(user.getId(),3);		
//		model.put("tempdeals", list);
//		Pagination pagination = fordRepairWebMng.getFordRepairWebByUserId(user.getId(), pageNo, 10);
		List<CardScoreInfoVo>  list = fordRepairWebMng.getCardScoreInfoVoBy(user.getId(),0,0);
		model.put("carInfo", list);//利用tag/forumtopic.html分页
		if(list!=null)
			model.put("size", list.size());
		model.put("pageNo", 1);//我的朋友标签必须
		return "/WEB-INF/t/cms/www/red/member/self03.html";
	}
	
	/**
	 * 维修工单详情查询
	 */
	@RequestMapping(value = "/member/serviceDetails.jspx", method = RequestMethod.GET)
	public String serviceDetails(String vbillId,HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		CmsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		//获得工单维修总信息
		FordRepair fr = fordRepairMng.getFordRepairByBillId(vbillId);
		model.put("fr", fr);
		FordCar fc = null;
		ClubDictionary dic = null;
		if(fr!=null){
			//车辆信息
			fc = fordCarMng.findById(fr.getVcarId());
			
			//品牌信息
			dic = clubDictionaryMng.getClubDictionaryByCode(fr.getVmodel());
			
		}
		model.put("fc", fc);
		model.put("dic", dic);
		//获得工单维修附加信息
		List<FordRepairAddition> fras = fordRepairAdditionMng.getFordRepairAdditionByBill(vbillId);
		model.put("fras", fras);
		//获得工单维修工时信息
		List<FordRepairLabor> frls = fordRepairLaborMng.getFordRepairLaborByBill(vbillId);
		model.put("frls", frls);
		//获得工单维修配件信息
		List<FordRepairRepairpart> frrs = fordRepairRepairpartMng.getFordRepairRepairpartByBill(vbillId);
		model.put("frrs", frrs);
		//获得工单维修销售配件信息
		List<FordRepairSalepart> frss = fordRepairSalepartMng.getFordRepairSalepartByBill(vbillId);
		model.put("frss", frss);
		model.put("pageNo", 1);//我的朋友标签必须
		return "/WEB-INF/t/cms/www/red/member/self04.html";
	}
	
	@Autowired
	private ClubDictionaryMng clubDictionaryMng;
	@Autowired
	private FordCarMng fordCarMng;
	@Autowired
	private FordRepairAdditionMng fordRepairAdditionMng;
	@Autowired
	private FordRepairLaborMng fordRepairLaborMng;
	@Autowired
	private FordRepairMng fordRepairMng;
	@Autowired
	private FordRepairRepairpartMng fordRepairRepairpartMng;
	@Autowired
	private FordRepairSalepartMng fordRepairSalepartMng;
	@Autowired
	private FordRepairWebMng fordRepairWebMng;
	@Autowired
	private TempDealMng tempDealMng;
	@Autowired
	private TempElecMng tempElecMng;
}
