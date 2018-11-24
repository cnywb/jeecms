package com.jeecms.cms.manager.main.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.FordMemberFormDao;
import com.jeecms.cms.entity.main.ClubUser;
import com.jeecms.cms.entity.main.CmsGroup;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.FordCar;
import com.jeecms.cms.entity.main.FordClubApply;
import com.jeecms.cms.entity.main.FordClubMember;
import com.jeecms.cms.entity.main.FordMemberForm;
import com.jeecms.cms.manager.main.ClubUserMng;
import com.jeecms.cms.manager.main.CmsGroupMng;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.cms.manager.main.FordCarMng;
import com.jeecms.cms.manager.main.FordClubApplyMng;
import com.jeecms.cms.manager.main.FordClubMemberMng;
import com.jeecms.cms.manager.main.FordMemberFormMng;
@Service
@Transactional
public class FordMemberFormMngImpl implements FordMemberFormMng {
	
	@Autowired
	private FordMemberFormDao dao;
	@Autowired
	private FordClubMemberMng clubMemberMng;
	@Autowired
	private FordClubApplyMng clubApplyMng;
	@Autowired
	private CmsUserMng userMng;
	@Autowired
	private CmsGroupMng groupMng;
	@Autowired
	private ClubUserMng clubUserMng;
	@Autowired
	private FordCarMng fordCarMng;

	@Override
	public FordMemberForm getFordMemberFormBy(String vname, String vmobile,
			String vvin) {
		return dao.getFordMemberFormBy(vname, vmobile, vvin);
	}

	@Override
	public int authenticationCarOwner(CmsUser user, String vname, String vmobile,
			String vvin) {
		int flag = 0;//0表示认证失败,1表示认证成功
		//判断是否改vin码已经被认证过
		FordClubMember clubMember = clubMemberMng.getFordClubMemberByVin(vvin);//todo
		if(clubMember == null){			
			//认证是否是车主
			FordMemberForm memberForm = dao.getFordMemberFormBy(vname, vmobile, vvin);
			//认证成功更新车主信息表以及用户状态为车主
			if(memberForm!=null){
				//更新车主FordClubMember表信息生成车主卡号
				clubMemberMng.save(setClubMember(user.getId(),vname, vmobile, vvin,memberForm));//todo
				//更新用户会员信息
				CmsGroup group = groupMng.findById(2);//2特指为车主会员编号todo
				user.setGroup(group);//todo
				userMng.updateUser(user);//todo
				//更新旧网站用户表车主状态，主要针对活动
				updateClubUser(user);
			    //设置标示符表示认证成功
				flag = 1;
			}else{
				List<FordMemberForm> list = getListByVin(vvin);
				if(null != list && list.size()>0)
				{
					boolean mobileflag = false;//判断是否手机号正确
					boolean nameflag = false;//判断是否姓名正确
					for(FordMemberForm m:list){
						String mobile = m.getVformTel();
						String name = m.getVformName();
						if(!StringUtils.isBlank(mobile) && mobile.equals(vmobile))
							mobileflag = true;
						if(!StringUtils.isBlank(name) && name.equals(vname))
							nameflag = true;
					}
					if(!mobileflag)
						flag=4;//手机号不正确
					if(!nameflag)
						flag=5;//姓名不正确
					if(!mobileflag && !nameflag)
						flag=6;//手机号和姓名都不正确
				}else{
					flag = 7;//表示vin未传送到dms系统或者是vin码不存在
				}
			}
			//判断该vin码是否已经存在认证资料,如果有则更新没有则新增
			FordClubApply clubApply = clubApplyMng.getFordClubApplyByVin(vvin);
			//更新FordClubApply信息
			if(clubApply==null){
				clubApplyMng.save(setClubApply(clubApply,vname, vmobile, vvin,flag,user.getId()));
			}else{
				clubApplyMng.update(setClubApply(clubApply,vname, vmobile, vvin,flag,user.getId()));			
			}	
		}else{
			flag=3;//表示该vin码已经被认证过
		}
		return flag;
	}
	
	private void updateClubUser(CmsUser user)
	{
		String uuid = "";
		if(user.getId().intValue()>=500000)
		{
			uuid = user.getId().toString();
		}
		else
		{
			int idLen = user.getId().toString().length();
			for(int i=0;i<(10-idLen);i++)
			{
				uuid += "0";
			}
			uuid += user.getId();
		}
		ClubUser u = clubUserMng.findById(uuid);
		if(u!=null)
		{
			u.setMid(1);
			u.setUpdatedate(new Date());
			clubUserMng.update(u);
		}
	}
	
	//设置FordClubMember属性值
	private FordClubMember setClubMember(int userId, String vname,
			String vmobile, String vvin,FordMemberForm form) {
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
	
	private FordClubMember setClubMember(int userId, String vname,
			String vmobile, String vvin,FordCar car) {
		FordClubMember mem = new FordClubMember();
		String vcardId = clubMemberMng.getSeq();
		mem.setVcardId(vcardId);
		if(car!=null){
			mem.setVcustomerId(car.getVcustomerId());
			mem.setVcarId(car.getVcarId());
		}
		mem.setVcardNo(vcardId.replaceFirst("0", "1"));
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

	@Override
	public List<FordMemberForm> getListByVin(String vvin) {
		// TODO Auto-generated method stub
		return dao.getListByVin(vvin);
	}
	
	
	/**
	 * 手动认证
	 * 
	 * @param vname
	 * @param vmobile
	 * @param vvin
	 * @return
	 */
	public int authenManualy(CmsUser user,String vname,String vmobile,String vvin){
		FordClubMember clubMember = clubMemberMng.getFordClubMemberByVin(vvin);
 		if(clubMember!=null){
 			return 2;//认证过
 		}
 		FordCar car=fordCarMng.findByVinOfOne(vvin);//因为是手动通过证件认证,所以以车辆信息即使为空,也可以认证通过
  		clubMemberMng.save(setClubMember(user.getId(),vname, vmobile, vvin,car));
		//更新用户会员信息
		CmsGroup group = groupMng.findById(2);//2特指为车主会员编号todo
		user.setGroup(group);//
		userMng.updateUser(user);//
		//更新旧网站用户表车主状态，主要针对活动
		updateClubUser(user);
	    //设置标示符表示认证成功
 		return 4;
	}
	

}
