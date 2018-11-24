package com.jeecms.cms.manager.main.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.bbs.entity.BbsUserExt;
import com.jeecms.bbs.entity.BbsUserGroup;
import com.jeecms.bbs.manager.BbsConfigMng;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.cms.dao.main.FordMemberFormDao;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.CmsUserExt;
import com.jeecms.cms.entity.main.CommonResponse;
import com.jeecms.cms.entity.main.FordClubApply;
import com.jeecms.cms.entity.main.FordClubMember;
import com.jeecms.cms.entity.main.FordMemberForm;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.cms.manager.main.FordClubApplyMng;
import com.jeecms.cms.manager.main.FordClubMemberMng;
import com.jeecms.cms.manager.main.RegisterMng;
import com.jeecms.common.util.CallbackJS;
import com.jeecms.common.util.Encrypt;
import com.jeecms.common.util.StrUtils;




@Service
@Transactional(rollbackFor=Exception.class) 
public class RegisterMngImpl implements RegisterMng {
	
	@Autowired
	private FordClubMemberMng clubMemberMng;

	@Autowired
	private CmsUserMng cmsUserMng;
	
	@Autowired
	private BbsUserMng bbsUserMng;
	
	@Autowired
	private FordMemberFormDao dao;
	
	@Autowired
	private BbsConfigMng bbsConfigMng;
	
	@Autowired
	private FordClubApplyMng clubApplyMng;
	
	/**
	 * 车主认证并自动注册
	 * @param vname
	 * @param vmobile
	 * @param vvin
	 * @return
	 */
	public CommonResponse carOwnerAuthenAndRegister(String vname,String vmobile,String vvin,String ip,String registType){
		CommonResponse retVal=new CommonResponse();
		FordClubMember clubMember=clubMemberMng.getFordClubMemberByVin(vvin);//todo
		if(clubMember!=null){
			retVal.setMessage("认证失败，该VIN码已经被认证过,请误重新认证!");
			return retVal;
		}
		FordMemberForm memberForm = dao.getFordMemberFormBy(vname,vmobile,vvin);
		if(memberForm==null){
			retVal.setMessage("认证失败，车主信息不匹配!");
			return retVal;
		}
		String username = StrUtils.getRandomString(8);
		String password = StrUtils.getRandomString(5);// 随机生成一个五位长的字符
		CmsUser user=registerUser(username, password, vmobile,ip,registType);
		clubMemberMng.save(setClubMember(user.getId(),vname, vmobile, vvin,memberForm));
		//判断该vin码是否已经存在认证资料,如果有则更新没有则新增
		FordClubApply clubApply = clubApplyMng.getFordClubApplyByVin(vvin);
		//更新FordClubApply信息
		if(clubApply==null){
			clubApplyMng.save(setClubApply(clubApply,vname, vmobile, vvin,1,user.getId()));
		}else{
			clubApplyMng.update(setClubApply(clubApply,vname, vmobile, vvin,1,user.getId()));			
		}	
		retVal.setMessage("认证成功，您的用户名是:"+username+"，密码是:"+password);
		retVal.setStatus(1);
		return retVal;
	}

	
	private CmsUser registerUser(String username,String password,String mobilePhoneNo,String ip,String registType){
		CmsUserExt userExt=new CmsUserExt();
		BbsUserExt buserExt=new BbsUserExt();
		userExt.setMobile(mobilePhoneNo);
		CmsUser cmsuser=null;
		String encryptByJsPw = CallbackJS.getJSMD5(password);// 之前恶心的设计,密码在前端用js加密过,所以现在也要加密
		try {
			cmsuser=cmsUserMng.registerMember(username,"未填写", encryptByJsPw,ip, 2,null,userExt,4L);
			BbsUserGroup bbsgroup = bbsConfigMng.findById(2).getRegisterGroup();
			Integer groupId = null;
			if (bbsgroup != null) {
					groupId = bbsgroup.getId();
			}
			bbsUserMng.registerMember(username,"", encryptByJsPw, "", groupId,buserExt, cmsuser.getId());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cmsuser;
	}
	
	private FordClubMember setClubMember(int userId, String vname,String vmobile, String vvin,FordMemberForm form) {
		FordClubMember mem = new FordClubMember();
		String vcardId = clubMemberMng.getSeq();
		mem.setVcardId(vcardId);
		mem.setVcustomerId(form.getVcustomerid());
		mem.setVcardNo(vcardId.replaceFirst("0", "1"));
		mem.setVcarId(form.getVcarid());
		mem.setVvin(vvin);
		mem.setVname(vname);
		mem.setDcrtDate(new Date());
		mem.setVmobile(vmobile);
		mem.setVcardStatus("01");
		mem.setUserId(userId);
		return mem;
	}
	
	//设置FordClubApply属性值 dfsf
		private FordClubApply setClubApply(FordClubApply clubApply,String vname, String vmobile,String vvin,int flag,int userId){
			if(clubApply == null){
				clubApply = new FordClubApply();
				clubApply.setVapplyId(clubApplyMng.getSeq());
				clubApply.setDcrtDate(new Date());
			}else{
				clubApply.setDupDate(new Date());
			}
			clubApply.setVname(vname);
			clubApply.setVmobile(vmobile);
			clubApply.setVvin(vvin);
			clubApply.setUserId(userId);
			if(flag == 1){
				clubApply.setVapplyStatus("01");
				clubApply.setVconfirmResult("06");
			}else{
				clubApply.setVapplyStatus("00");
			}
			
			return clubApply;
		}
}
