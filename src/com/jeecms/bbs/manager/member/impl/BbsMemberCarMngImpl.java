package com.jeecms.bbs.manager.member.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.jeecms.bbs.action.member.vo.UserCarVo;
import com.jeecms.bbs.dao.member.MemberCarDao;
import com.jeecms.bbs.entity.BbsMemberCar;
import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.manager.BbsUserMng;
import com.jeecms.bbs.manager.member.BbsMemberCarMng;
import com.jeecms.cms.entity.main.ClubDictionary;
import com.jeecms.cms.entity.main.FordCar;
import com.jeecms.cms.entity.main.FordClubMember;
import com.jeecms.cms.manager.main.ClubDictionaryMng;
import com.jeecms.cms.manager.main.FordCarMng;
import com.jeecms.cms.manager.main.FordClubMemberMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;

@Service
@Transactional
public class BbsMemberCarMngImpl implements BbsMemberCarMng {

	private final Logger logger = LoggerFactory.getLogger(super.getClass());

	@Autowired
	private MemberCarDao memberCarDao;

	@Autowired
	private FordClubMemberMng memberMng;

	@Autowired
	private FordCarMng carMng;

	@Autowired
	private ClubDictionaryMng dictionaryMng;
	@Autowired
	private BbsUserMng bbsUserMng;

	@Override
	public BbsMemberCar save(BbsMemberCar memberCar) {
		return this.memberCarDao.save(memberCar);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BbsMemberCar> queryById(Integer uuid) {
		return this.memberCarDao.queryById(uuid);
	}

	@Override
	public void deleteById(Long id) {
		this.memberCarDao.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.bbs.manager.member.BbsMemberCarMng#queryFriendCarByUserId(
	 * java.lang.Integer, com.jeecms.common.page.Pagination)
	 */
	@Override
	public Pagination queryFriendCarByUserId(Integer userId, int pageNo,
			int pageSize) {
		Pagination pagination = memberCarDao.queryFriendCarByUserId(userId,
				pageNo, pageSize);

		List<BbsMemberCar> memberCars = (List<BbsMemberCar>) pagination
				.getList();

		Map<String, UserCarVo> userCarVoMap = new HashMap<String, UserCarVo>();

		if (!CollectionUtils.isEmpty(memberCars)) {
			for (BbsMemberCar memberCar : memberCars) {
				UserCarVo userCarVo = new UserCarVo();
				userCarVo.setId(memberCar.getId());
				if (StringUtils.isNotEmpty(memberCar.getOcarImg())
						&& memberCar.getOcarImg().length() > 1) {
					userCarVo.setOcarImg(memberCar.getOcarImg().substring(0,
							memberCar.getOcarImg().length() - 1));
				}
				userCarVo.setUuid(memberCar.getUuid());
				ClubDictionary dic = dictionaryMng
						.getClubDictionaryByCode(memberCar.getVproduct());
				if (dic != null)
					userCarVo.setVproduct(dic.getCdname());
				if (memberCar.getPurchasedDate() != null) {
					userCarVo.setPurchasedDate(DateUtils.parseDate(
							memberCar.getPurchasedDate(), "yyyy-MM"));
					userCarVo.setPurchasedDateZh(DateUtils.parseDate(
							memberCar.getPurchasedDate(), "yyyy年MM月"));
					userCarVo.setPurchasedFullDate(DateUtils.parseDate(
							memberCar.getPurchasedDate(), "yyyy-MM-dd"));
				}
				userCarVo.setVproductCode(memberCar.getVproduct());
				userCarVo.setCarId(memberCar.getCarId());
				userCarVo.setUsername(this.queryUserNameByUserId(memberCar
						.getUuid()));
				userCarVoMap.put(memberCar.getCarId(), userCarVo);
			}
		}

		List<UserCarVo> list = new ArrayList<UserCarVo>();
		for (String carId : userCarVoMap.keySet()) {
			list.add(userCarVoMap.get(carId));
		}
		pagination.setList(list);
		return pagination;
	}

	private String queryUserNameByUserId(Integer userId) {
		BbsUser user = this.bbsUserMng.findById(userId);
		if (user != null) {
			return user.getUsername();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.bbs.manager.member.BbsMemberCarMng#queryUserCarBy(java.lang
	 * .Integer)
	 */
	@Override
	public List<UserCarVo> queryUserCarBy(Integer userId) {

		List<BbsMemberCar> memberCars = this.queryById(userId);

		Map<String, UserCarVo> userCarVoMap = new HashMap<String, UserCarVo>();

		if (!CollectionUtils.isEmpty(memberCars)) {
			for (BbsMemberCar memberCar : memberCars) {
				UserCarVo userCarVo = new UserCarVo();
				userCarVo.setId(memberCar.getId());
				userCarVo.setOcarImg(memberCar.getOcarImg());
				userCarVo.setUuid(memberCar.getUuid());
				userCarVo.setVproduct(memberCar.getVproduct());
				if (memberCar.getPurchasedDate() != null) {
					userCarVo.setPurchasedDate(DateUtils.parseDate(
							memberCar.getPurchasedDate(), "yyyy-MM"));
					userCarVo.setPurchasedDateZh(DateUtils.parseDate(
							memberCar.getPurchasedDate(), "yyyy年MM月"));
					userCarVo.setPurchasedFullDate(DateUtils.parseDate(
							memberCar.getPurchasedDate(), "yyyy-MM-dd"));
				}
				userCarVo.setVproductCode(memberCar.getVproduct());
				userCarVo.setCarId(memberCar.getCarId());
				userCarVoMap.put(memberCar.getCarId(), userCarVo);
			}
		}

		List<FordClubMember> members = memberMng.getFordClubMemberByUid(userId);

		if (!CollectionUtils.isEmpty(members)) {
			for (FordClubMember member : members) {
				// 如果车已经添加图片，就结束本次查询UserCar的信息；
				if (userCarVoMap.containsKey(member.getVcarId())) {
					continue;
				}
				// FordCar car = carMng.findById(member.getVcarId());
				FordCar car = carMng.findByVinOfOne(member.getVvin());
				UserCarVo userCarVo = new UserCarVo();
				userCarVo.setUuid(userId);
				if (car != null) {
					userCarVo.setVproduct("");
					if(!StringUtils.isBlank(car.getVmodel()))
					{
					ClubDictionary dicc=	dictionaryMng.getClubDictionaryByCode(car.getVmodel());
					if(dicc!=null)
						userCarVo.setVproduct(dicc.getCdname());
					}
					userCarVo.setPurchasedDate(this.parseDate(
							car.getVpurchasedDate(), "yyyyMMdd", "yyyy-MM"));
					userCarVo.setPurchasedFullDate(this.parseDate(
							car.getVpurchasedDate(), "yyyyMMdd", "yyyy-MM-dd"));
					userCarVo.setPurchasedDateZh(this.parseDate(
							car.getVpurchasedDate(), "yyyyMMdd", "yyyy年MM月"));
					userCarVo.setCarId(car.getVcarId());
				}
				userCarVo.setVproductCode(userCarVo.getVproduct());				
				userCarVoMap.put(member.getVcarId(), userCarVo);
			}

		}

		List<UserCarVo> list = new ArrayList<UserCarVo>();
		for (String carId : userCarVoMap.keySet()) {
			list.add(userCarVoMap.get(carId));
		}

		return list;
	}

	private String parseDate(String purchasedDate, String pattern,
			String newPattern) {
		String purchasedDateTarget = null;
		if (StringUtils.isEmpty(purchasedDate)) {
			return purchasedDateTarget;
		}

		if (StringUtils.isEmpty(pattern)) {
			pattern = "yyyyMMdd";
			newPattern = "yyyy-MM";
		}

		DateUtils.format.applyPattern(pattern);

		try {
			Date date = DateUtils.format.parse(purchasedDate);
			purchasedDateTarget = DateUtils.parseDate(date, newPattern);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return purchasedDateTarget;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.bbs.manager.member.BbsMemberCarMng#deleteImageById(java.lang
	 * .Long)
	 */
	@Override
	public String deleteImageById(Long id, Integer userId) {
		BbsMemberCar bbsMemberCar = memberCarDao.findById(id);
		String imagePath = bbsMemberCar.getOcarImg();
		if (bbsMemberCar != null) {
			bbsMemberCar.setUpdateDate(new Date());
			bbsMemberCar.setUpdateUser(userId);
			bbsMemberCar.setOcarImg("");
			this.memberCarDao.update(bbsMemberCar);
		}
		return imagePath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jeecms.bbs.manager.member.BbsMemberCarMng#saveImage(java.lang.Long,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void saveImage(Long id, Integer userId, String carId,
			String productCode, String uploadImgPath, String purchasedDate) {
		if (StringUtils.isNotEmpty(uploadImgPath) && uploadImgPath.length() > 1) {
			uploadImgPath = uploadImgPath.substring(0,
					uploadImgPath.length() - 1);
		}
		BbsMemberCar bbsMemberCar = null;
		if (id == null) {
			bbsMemberCar = new BbsMemberCar();
			bbsMemberCar.setCreateDate(new Date());
			bbsMemberCar.setUuid(userId);
			bbsMemberCar.setCreateUser(userId);
			bbsMemberCar.setUpdateDate(new Date());
			bbsMemberCar.setUpdateUser(userId);
			bbsMemberCar.setVproduct(productCode);
			bbsMemberCar.setOcarImg(uploadImgPath);
			bbsMemberCar.setCarId(carId);
			bbsMemberCar.setPurchasedDate(this.purchaseDate(purchasedDate));
			this.save(bbsMemberCar);
		} else {
			bbsMemberCar = memberCarDao.findById(id);
			if (bbsMemberCar != null) {
				bbsMemberCar.setUuid(userId);
				bbsMemberCar.setUpdateDate(new Date());
				bbsMemberCar.setUpdateUser(userId);
				bbsMemberCar.setVproduct(productCode);
				bbsMemberCar.setOcarImg(uploadImgPath);
				bbsMemberCar.setCarId(carId);
				bbsMemberCar.setPurchasedDate(this.purchaseDate(purchasedDate));
				this.memberCarDao.update(bbsMemberCar);
			}
		}
	}

	private Date purchaseDate(String purchasedDate) {
		Date date = null;
		if (StringUtils.isEmpty(purchasedDate)) {
			return null;
		}
		try {

			DateUtils.format.applyPattern("yyyy-MM-dd");
			date = DateUtils.format.parse(purchasedDate);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return date;
	}
}
