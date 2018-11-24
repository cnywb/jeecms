/**
 * 
 */
package com.jeecms.point.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jeecms.cms.web.WebErrors;
import com.jeecms.common.page.Pagination;
import com.jeecms.point.entity.CompetitiveProduct;
import com.jeecms.point.manager.competitiveproduct.CompetitiveProductMng;
import com.jeecms.point.vo.competitiveproduct.CompetitiveProductVo;
import com.jeecms.point.vo.competitiveproduct.ImCompetitiveVO;
import com.jeecms.point.web.json.JSONParser;
import com.jeecms.point.web.query.QueryVo;

/**
 * 精品附件管理
 * 
 * @author ziv.hung
 */
@Controller
@RequestMapping("/admin/point/competitive")
public class PointCompetitiveAdminAct {

	private static final Logger logger = LoggerFactory.getLogger(PointCompetitiveAdminAct.class);

	/** 首页 */
	private static final String INDEX_RETURN = "points/competitive/index";
	/** 添加页面 */
	private static final String ADD_RETURN = "points/competitive/add";

	@Autowired
	private CompetitiveProductMng competitiveProductMng;

	/** 列表页面 */
	@RequestMapping("/index.do")
	public String index(HttpServletRequest request, ModelMap model) {
		logger.info("精品附件页面...");
		List<ImCompetitiveVO> vehicleModelsList = competitiveProductMng.getImByCategory("vehicleModels");
		List<ImCompetitiveVO> classificationList = competitiveProductMng.getImByCategory("classification");
		List<ImCompetitiveVO> statusList = competitiveProductMng.getImByCategory("status");
		model.put("vehicleModelsList", vehicleModelsList);
		model.put("classificationList", classificationList);
		model.put("statusList", statusList);
		return "points/competitive/index";
	}

	/** 添加页面 */
	@RequestMapping("/add.do")
	public String add(ModelMap model) {
		CompetitiveProduct competitiveProduct = new CompetitiveProduct();
		model.put("competitiveProduct", competitiveProduct);
		return ADD_RETURN;
	}

	/** 保存页面 */
	@RequestMapping("/save.do")
	public String save(CompetitiveProductVo competitiveProductVo, HttpServletRequest request, ModelMap model) {
		WebErrors errors = WebErrors.create(request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		if (competitiveProductVo.getId() == null) {
			CompetitiveProduct competitiveProduct = new CompetitiveProduct();
			BeanUtils.copyProperties(competitiveProductVo, competitiveProduct);
			competitiveProduct.setClassificationLabel("classification");
			competitiveProduct.setVehicleModelsLabel("vehicleModels");
			Date createdDate = new Date();
			competitiveProduct.setCreatedId(1L);
			competitiveProduct.setCreatedDate(createdDate);
			competitiveProduct.setUpdatedId(1L);
			competitiveProduct.setUpdatedDate(createdDate);
			competitiveProduct.setBrowseTimes(0);
			competitiveProduct = this.competitiveProductMng.save(competitiveProduct);
		} else {
			CompetitiveProduct competitiveProduct = this.competitiveProductMng.findById(competitiveProductVo.getId());
			BeanUtils.copyProperties(competitiveProductVo, competitiveProduct);
			competitiveProduct.setClassificationLabel("classification");
			competitiveProduct.setVehicleModelsLabel("vehicleModels");
			Date updatedDate = new Date();
			competitiveProduct.setUpdatedDate(updatedDate);
			this.competitiveProductMng.update(competitiveProduct);
		}

		return INDEX_RETURN;
	}

	/**
	 * 删除数据
	 * 
	 * @param ruleVo对象
	 * @return
	 */
	@RequestMapping("/remove.do")
	@ResponseBody
	public String remove(@RequestParam(required = false) Long id) {
		this.competitiveProductMng.remove(id);
		return "删除成功!";
	}

	/*** 查询列表信息 */
	@RequestMapping("/list.do")
	@ResponseBody
	public String list(QueryVo queryVo, CompetitiveProductVo competitiveProductVo) {
		String productName = competitiveProductVo.getProductName();
		String vehicleModels = competitiveProductVo.getVehicleModels();
		String classification = competitiveProductVo.getClassification();
		String status = competitiveProductVo.getStatus();
		int pageNo = queryVo.getPage();
		int pageSize = queryVo.getRows();
		Pagination pagination = this.competitiveProductMng.getPage(productName, vehicleModels, classification, status, pageNo, pageSize);
		return JSONParser.toDataGridString(pagination);
	}

	/***
	 * 编辑
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit.do")
	public String edit(@RequestParam(required = false) Long id, ModelMap model) {
		CompetitiveProduct competitiveProduct = this.competitiveProductMng.findById(id);
		model.put("competitiveProduct", competitiveProduct);
		return ADD_RETURN;
	}

	@RequestMapping("/upload.do")
	public String upload(HttpServletRequest request,ModelMap model,@RequestParam MultipartFile filePath) throws IOException {
		String status = this.parseExcelDealData(filePath);
		if(status.equals("success")){
			return INDEX_RETURN;
		}else{
			return status;
		}
	}
	

	private String parseExcelDealData(MultipartFile filePath) {
		List<CompetitiveProduct> competitiveProducts = new ArrayList<CompetitiveProduct>();
		try {
			Workbook workbook = WorkbookFactory.create(filePath.getInputStream());  
			Sheet sheet = workbook.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			Date date = new Date();
			for (int i = 1; i <= rowNum; i++) {
				Row row = sheet.getRow(i);
				if(row != null){
                	Cell productNocell = row.getCell(0);// 精品代码
                	if(productNocell==null||productNocell.getStringCellValue()==null||productNocell.getStringCellValue().equals("")){
    					continue;
    				}
    				Cell productNamecell = row.getCell(1);// 精品名称
    				Cell classificationcell = row.getCell(2);// 附件分类代码
    				Cell classificationLabelcell = row.getCell(3);// 附件分类名称
    				Cell vehicleModelscell = row.getCell(4);// 车型种类代码
    				Cell vehicleModelsLabelcell = row.getCell(5);// 车型种类名称
    				Cell applicableModelscell = row.getCell(6);// 适用车型
    				Cell briefIntroductioncell = row.getCell(7);// 简介
    				Cell imgUrlcell = row.getCell(8);// 图片路径
    				Cell pricecell = row.getCell(9);// 价格
    				Cell statuscell = row.getCell(10);// 上线状态
    				CompetitiveProduct competitiveProduct = new CompetitiveProduct();
    				competitiveProduct.setProductNo(productNocell.getStringCellValue());
    				competitiveProduct.setProductName(productNamecell==null||productNamecell.getStringCellValue()==null||productNamecell.getStringCellValue().equals("")?"/":productNamecell.getStringCellValue());
    				competitiveProduct.setClassification(classificationcell==null||classificationcell.getStringCellValue()==null||classificationcell.getStringCellValue().equals("")?"/":classificationcell.getStringCellValue());
    				competitiveProduct.setClassificationLabel(classificationLabelcell==null||classificationLabelcell.getStringCellValue()!=null||classificationLabelcell.getStringCellValue().equals("")?"classificationLabel":classificationLabelcell.getStringCellValue());
    				competitiveProduct.setVehicleModels(vehicleModelscell==null||vehicleModelscell.getStringCellValue()==null||vehicleModelscell.getStringCellValue().equals("")?"CALL":vehicleModelscell.getStringCellValue());
    				competitiveProduct.setVehicleModelsLabel(vehicleModelsLabelcell==null||vehicleModelsLabelcell.getStringCellValue()!=null||vehicleModelsLabelcell.getStringCellValue().equals("")?"vehicleModelsLabel":vehicleModelsLabelcell.getStringCellValue());
    				competitiveProduct.setApplicableModels(applicableModelscell==null||applicableModelscell.getStringCellValue()!=null||applicableModelscell.getStringCellValue().equals("")?"适用车型":applicableModelscell.getStringCellValue());
    				//简介
    				String str = "";
    				if(briefIntroductioncell==null || briefIntroductioncell.getStringCellValue()==null||briefIntroductioncell.getStringCellValue().equals("")){
    					str = competitiveProduct.getProductName();
    				}else{
    					str = briefIntroductioncell.getStringCellValue();
    				}
    				competitiveProduct.setBriefIntroduction(str);
    				//价格
    				if(pricecell!=null){
    					pricecell.setCellType(Cell.CELL_TYPE_NUMERIC);
    					competitiveProduct.setPrice(pricecell.getNumericCellValue());
    				}else{
    					competitiveProduct.setPrice(0.0);
    				}
    				//图片
    				if(imgUrlcell!=null){
    					competitiveProduct.setImgUrl(imgUrlcell.getStringCellValue()==null||imgUrlcell.getStringCellValue().equals("")?"/":imgUrlcell.getStringCellValue());
    				}else{
    					competitiveProduct.setImgUrl("/");
    				}
    				//状态
    				if(statuscell!=null){
	    				statuscell.setCellType(Cell.CELL_TYPE_STRING);
	    				competitiveProduct.setStatus(statuscell.getStringCellValue()==null||statuscell.getStringCellValue().equals("")?"0":statuscell.getStringCellValue());
    				}else{
    					competitiveProduct.setStatus("0");
    				}
    				competitiveProduct.setBrowseTimes(0);
    				competitiveProduct.setCreatedId(1L);
    				competitiveProduct.setCreatedDate(date);
    				competitiveProduct.setUpdatedId(1L);
    				competitiveProduct.setUpdatedDate(date);
    				competitiveProducts.add(competitiveProduct);
				}
				
			}
		} catch (Exception e) {
			logger.error("文件解析失败！", e);
			return "文件解析失败！";
		}
		try {
			competitiveProductMng.saveAll(competitiveProducts);
			return "success";
		} catch (Exception e) {
			logger.error("数据持久化失败！", e);
			return "数据持久化失败！";
		}
	}
}
