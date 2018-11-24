package com.jeecms.cms.action.admin.member;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.jeecms.cms.manager.main.CarOwnerAuthenApplicationMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.common.web.session.SessionProvider;


@Controller@Scope("prototype")
@RequestMapping("/admin/member/carowner/*")
public class CarOwnerAuthenApplicationManageAct {
	
	@Autowired
	private CarOwnerAuthenApplicationMng carOwnerAuthenApplicationMng;
	
	
	@RequestMapping(value="authenApplicationlist.do")
	public String authenApplicationlist(String status,String name,String mobile,String vin,String createTimeMin,String createTimeMax,String updateTimeMin,String updateTimeMax, Integer pageNo,Integer pageSize, HttpServletRequest request,
			ModelMap model) {
	
		if(pageNo==null){
			pageNo=1;
		}
		if(pageSize==null){
			pageSize=10;
		}
		
		Pagination pagination = carOwnerAuthenApplicationMng.getPage(status,name, mobile, vin, createTimeMin, createTimeMax, updateTimeMin, updateTimeMax, pageNo, pageSize);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pagination.getPageNo());
		model.addAttribute("name", name);
		model.addAttribute("mobile", mobile);
		model.addAttribute("vin", vin);
		model.addAttribute("status", status);
		model.addAttribute("createTimeMin", createTimeMin);
		model.addAttribute("createTimeMax", createTimeMax);
		model.addAttribute("updateTimeMin", updateTimeMin);
		model.addAttribute("updateTimeMax", updateTimeMax);
		return "member/car_owner_authen_application_list";
	}
	
	
	@RequestMapping(value="authenApplicationDetail.do")
	public String authenApplicationDetail(long id, HttpServletRequest request,
			ModelMap model) {
	
		model.addAttribute("carOwnerAuthenApplication", carOwnerAuthenApplicationMng.findById(id));
		return "member/car_owner_authen_application_detail";
	}
	@RequestMapping(value="authenPass.do",method=RequestMethod.POST)
	public void authenPass(long id,HttpServletResponse response,HttpServletRequest request){
		JSONObject object = new JSONObject();
		try {
		  
			object.put("status",carOwnerAuthenApplicationMng.authenPass(id));
		   
			ResponseUtils.renderJson(response, object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="authenUnPass.do",method=RequestMethod.POST)
	public void authenUnPass(long id,String memo,HttpServletResponse response,HttpServletRequest request){
		try{
		   JSONObject object = new JSONObject();
	       object.put("status",carOwnerAuthenApplicationMng.authenUnPass(id,""));
		   ResponseUtils.renderJson(response, object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	

	
	
	
	
	@Autowired
	private SessionProvider session;


	public SessionProvider getSession() {
		return session;
	}


	public void setSession(SessionProvider session) {
		this.session = session;
	}

}
