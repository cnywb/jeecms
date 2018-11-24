package com.jeecms.point.manager.competitiveproduct.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.jeecms.point.dao.competitiveproduct.CompetitiveProductDao;
import com.jeecms.point.entity.CompetitiveProduct;
import com.jeecms.point.manager.competitiveproduct.CompetitiveProductMng;
import com.jeecms.point.vo.competitiveproduct.ImCompetitiveVO;

/**
 * 
 * @author ziv.hung
 */
@Service
@Transactional
public class CompetitiveProductMngImpl implements CompetitiveProductMng {

	@Autowired
	private CompetitiveProductDao competitiveProductDao;

	@Override
	public CompetitiveProduct save(CompetitiveProduct competitiveProduct) {
		return competitiveProductDao.save(competitiveProduct);
	}
	
	@Override
	public void saveAll(List<CompetitiveProduct> competitiveProducts) {
		for (CompetitiveProduct competitiveProduct : competitiveProducts) {
			competitiveProductDao.save(competitiveProduct);
		}
	}

	@Override
	public CompetitiveProduct update(CompetitiveProduct competitiveProduct) {
		return competitiveProductDao.update(competitiveProduct);
	}

	@Override
	public Pagination getPage(String productName, String vehicleModels, String classification, String status, int pageNo, int pageSize) {
		return competitiveProductDao.getPage(productName, vehicleModels, classification, status, pageNo, pageSize);
	}

	@Override
	public CompetitiveProduct remove(Long id) {
		return competitiveProductDao.remove(id);
	}

	@Override
	@Transactional(readOnly=true)
	public CompetitiveProduct findById(Long id) {
		return competitiveProductDao.findById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public boolean checkCompetitiveProductNo(String competitiveProductNo) {
		return competitiveProductDao.checkCompetitiveProductNo(competitiveProductNo);
	}

	@Override
	public boolean checkcompetitiveProductName(String competitiveProductName) {
		return competitiveProductDao.checkcompetitiveProductName(competitiveProductName);
	}

	@Override
	public List<ImCompetitiveVO> getImByCategory(String category) {
		List<ImCompetitiveVO> list = new ArrayList<ImCompetitiveVO>();
		if ("vehicleModels".equalsIgnoreCase(category)) {
			ImCompetitiveVO imCompetitiveVO1 = new ImCompetitiveVO();
			imCompetitiveVO1.setCode("NFKS");
			imCompetitiveVO1.setName("新福克斯");
			list.add(imCompetitiveVO1);
			ImCompetitiveVO imCompetitiveVO2 = new ImCompetitiveVO();
			imCompetitiveVO2.setCode("JDFKS");
			imCompetitiveVO2.setName("经典福克斯");
			list.add(imCompetitiveVO2);
			ImCompetitiveVO imCompetitiveVO3 = new ImCompetitiveVO();
			imCompetitiveVO3.setCode("FRS");
			imCompetitiveVO3.setName("福睿斯");
			list.add(imCompetitiveVO3);
			ImCompetitiveVO imCompetitiveVO4 = new ImCompetitiveVO();
			imCompetitiveVO4.setCode("NMDO");
			imCompetitiveVO4.setName("新蒙迪欧");
			list.add(imCompetitiveVO4);
			ImCompetitiveVO imCompetitiveVO5 = new ImCompetitiveVO();
			imCompetitiveVO5.setCode("MDO_ZS");
			imCompetitiveVO5.setName("蒙迪欧致胜");
			list.add(imCompetitiveVO5);
			ImCompetitiveVO imCompetitiveVO6 = new ImCompetitiveVO();
			imCompetitiveVO6.setCode("NJNH");
			imCompetitiveVO6.setName("新嘉年华");
			list.add(imCompetitiveVO6);
			ImCompetitiveVO imCompetitiveVO7 = new ImCompetitiveVO();
			imCompetitiveVO7.setCode("YH");
			imCompetitiveVO7.setName("翼虎");
			list.add(imCompetitiveVO7);
			ImCompetitiveVO imCompetitiveVO9 = new ImCompetitiveVO();
			imCompetitiveVO9.setCode("YB");
			imCompetitiveVO9.setName("翼搏");
			list.add(imCompetitiveVO9);
			ImCompetitiveVO imCompetitiveVO10 = new ImCompetitiveVO();
			imCompetitiveVO10.setCode("RJ");
			imCompetitiveVO10.setName("锐界");
			list.add(imCompetitiveVO10);
			ImCompetitiveVO imCompetitiveVO11 = new ImCompetitiveVO("S-MAX","S-MAX");
			list.add(imCompetitiveVO11);
			ImCompetitiveVO imCompetitiveVO12 = new ImCompetitiveVO("JNZ","金牛座");
			list.add(imCompetitiveVO12);
		} else if ("classification".equalsIgnoreCase(category)) {
			ImCompetitiveVO imCompetitiveVO1 = new ImCompetitiveVO();
			imCompetitiveVO1.setCode("ALL");
			imCompetitiveVO1.setName("通用附件");
			list.add(imCompetitiveVO1);
			ImCompetitiveVO imCompetitiveVO2 = new ImCompetitiveVO();
			imCompetitiveVO2.setCode("NADD");
			imCompetitiveVO2.setName("内饰附件");
			list.add(imCompetitiveVO2);
			ImCompetitiveVO imCompetitiveVO3 = new ImCompetitiveVO();
			imCompetitiveVO3.setCode("WADD");
			imCompetitiveVO3.setName("外饰附件");
			list.add(imCompetitiveVO3);
		} else if ("status".equalsIgnoreCase(category)) {
			ImCompetitiveVO imCompetitiveVO = new ImCompetitiveVO();
			imCompetitiveVO.setCode("0");
			imCompetitiveVO.setName("未上线");
			list.add(imCompetitiveVO);
			ImCompetitiveVO imCompetitiveVO1 = new ImCompetitiveVO();
			imCompetitiveVO1.setCode("1");
			imCompetitiveVO1.setName("已上线");
			list.add(imCompetitiveVO1);
			ImCompetitiveVO imCompetitiveVO2 = new ImCompetitiveVO();
			imCompetitiveVO2.setCode("2");
			imCompetitiveVO2.setName("已下线");
			list.add(imCompetitiveVO2);
			ImCompetitiveVO imCompetitiveVO3 = new ImCompetitiveVO();
			imCompetitiveVO3.setCode("3");
			imCompetitiveVO3.setName("缺货");
			list.add(imCompetitiveVO3);
		}
		return list;
	}
	
	@Override
	public String getImNameByCode(String category, String code){
			List<ImCompetitiveVO> list = getImByCategory(category);
			for (ImCompetitiveVO imCompetitiveVO : list) {
				if(code!=null&&code.equalsIgnoreCase(imCompetitiveVO.getCode())){
					return imCompetitiveVO.getName();
				}
			}
			return null;
	}

	/* (non-Javadoc)
	 * @see com.jeecms.point.manager.competitiveproduct.CompetitiveProductMng#updateBrowse(java.lang.Long)
	 */
	@Override
	@Transactional
	public int updateBrowse(Long productId) {
		return this.competitiveProductDao.updateBrowse(productId);
	}
	
	
}
