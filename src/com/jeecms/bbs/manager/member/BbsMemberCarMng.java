/**
 * 
 */
package com.jeecms.bbs.manager.member;

import java.util.List;

import com.jeecms.bbs.action.member.vo.UserCarVo;
import com.jeecms.bbs.entity.BbsMemberCar;
import com.jeecms.common.page.Pagination;

/**
 * @author xuw
 *
 */
public interface BbsMemberCarMng {
	/***
	 * 
	 * @param memberCarFocus
	 * @return  MemberCarFocus
	 */
	public BbsMemberCar save(BbsMemberCar bbsMemberCar);
	
	public List<BbsMemberCar> queryById(Integer uuid);
	
	public void deleteById(Long id);
	
	/***
	 * 根据用户ID查询该用户所有车
	 * @param uuid 用户ID
	 * @return List<UserCarVo>  
	 */
	public List<UserCarVo>   queryUserCarBy(Integer  uuid);
	
	public String deleteImageById(Long id,Integer userId);
	
	/***
	 * 
	 * @param id ID
	 * @param uuid  用户ID
	 * @param imageUrl  图片路径
	 */
	public void  saveImage(Long imageId,Integer userId,String carId,String productCode,String uploadImgPath,String purchasedDate);
	/**
	 * 查询朋友的爱车
	 * @param userId 用户ID
	 * @param  pagination  分页对象
	 * @return   Pagination
	 */
	public Pagination queryFriendCarByUserId(Integer userId, int pageNo, int pageSize);
}
