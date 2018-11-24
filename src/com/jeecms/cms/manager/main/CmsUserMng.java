package com.jeecms.cms.manager.main;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.CmsUserExt;
import com.jeecms.common.email.EmailSender;
import com.jeecms.common.email.MessageTemplate;
import com.jeecms.common.page.Pagination;

public interface CmsUserMng {
	public Pagination getPage(String username, String email, Integer siteId,
			Integer groupId, Boolean disabled, Boolean admin, Integer rank,
			int pageNo, int pageSize);
	
	public List<CmsUser> getList(String username, String email, Integer siteId,
			Integer groupId, Boolean disabled, Boolean admin, Integer rank);

	public List<CmsUser> getAdminList(Integer siteId, Boolean allChannel,
			Boolean disabled, Integer rank);
	
	public Pagination getAdminsByDepartId(Integer id, int pageNo,int pageSize);
	
	public Pagination getAdminsByRoleId(Integer roleId, int pageNo, int pageSize);

	public CmsUser findById(Integer id);

	public CmsUser findByUsername(String username);

	public CmsUser registerMember(String username, String email,
			String password, String ip, Integer groupId,Integer grain,CmsUserExt userExt);
	
	public CmsUser registerMember(String username, String email,
			String password, String ip, Integer groupId, CmsUserExt userExt,
			Integer unifUserId);
	
	public CmsUser registerMember(String username, String email,
			String password, String ip, Integer groupId, CmsUserExt userExt, Boolean activation , EmailSender sender, MessageTemplate msgTpl)throws UnsupportedEncodingException, MessagingException ;

	public void updateLoginInfo(Integer userId, String ip);

	public void updateUploadSize(Integer userId, Integer size);
	
	public void updateUser(CmsUser user);

	public void updatePwdEmail(Integer id, String password, String email);

	public boolean isPasswordValid(Integer id, String password);

	public CmsUser saveAdmin(String username, String email, String password,
			String ip, boolean viewOnly, boolean selfAdmin, int rank,
			Integer groupId, Integer departmentId,Integer[] roleIds, Integer[] channelIds,
			Integer[] siteIds, Byte[] steps, Boolean[] allChannels,
			CmsUserExt userExt);

	public CmsUser updateAdmin(CmsUser bean, CmsUserExt ext, String password,
			Integer groupId,Integer departmentId,Integer[] roleIds, Integer[] channelIds,
			Integer[] siteIds, Byte[] steps, Boolean[] allChannels);

	public CmsUser updateAdmin(CmsUser bean, CmsUserExt ext, String password,
			Integer groupId,Integer departmentId, Integer[] roleIds, Integer[] channelIds,
			Integer siteId, Byte step, Boolean allChannel);

	public CmsUser updateMember(Integer id, String email, String password,
			Boolean isDisabled, CmsUserExt ext, Integer groupId,Integer grain);
	
	public CmsUser updateUserConllection(CmsUser user,Integer cid,Integer operate);

	public void addSiteToUser(CmsUser user, CmsSite site, Byte checkStep);

	public CmsUser deleteById(Integer id);

	public CmsUser[] deleteByIds(Integer[] ids);
	
	public CmsUser deleteCmsUserById(Integer id);

	public CmsUser[] deleteCmsUserByIds(Integer[] ids);

	public boolean usernameNotExist(String username);
	
	public boolean usernameNotExistInMember(String username);

	public boolean emailNotExist(String email);
	/**ping.qi add 查询认证车主信息*/
	public Pagination getPage(String username, String vin, String createTimeMax,String createTimeMin,
		int pageNo, int pageSize);
	
	public CmsUser registerMember(String username, String email,
			String password, String ip, Integer groupId,CmsUserExt userExt,
			Boolean activation, EmailSender sender, MessageTemplate msgTpl,Long registType)throws UnsupportedEncodingException, MessagingException;

	public CmsUser registerMember(String username, String email,
			String password, String ip, Integer groupId,Integer grain, CmsUserExt userExt,Long registType);
}