/**
 * 
 */
package com.jeecms.point.action.admin;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecms.cms.web.WebErrors;
import com.jeecms.common.page.Pagination;
import com.jeecms.point.entity.PointRule;
import com.jeecms.point.manager.point.PointRuleMng;
import com.jeecms.point.vo.point.PointRuleVo;
import com.jeecms.point.web.json.JSONParser;
import com.jeecms.point.web.query.QueryVo;

/**
 * 
 * 积分规则管理
 * 
 * @author wanglijun
 */
@Controller
@RequestMapping("/admin/points/rule")
public class PointRuleAdminAct {

	private static final Logger logger = LoggerFactory
			.getLogger(PointRuleAdminAct.class);
	/** 首页 */
	private static final String INDEX_RETURN = "points/rule/index";
	/** 添加页面 */
	private static final String ADD_RETURN = "points/rule/add";
	/** 存储层 */
	@Autowired
	private PointRuleMng pointRuleMng;

	/** 列表页面 */
	@RequestMapping("/index.do")
	public String index() {
		logger.info("积分规则页面...");
		return INDEX_RETURN;
	}

	/** 添加页面 */
	@RequestMapping("/add.do")
	public String add(ModelMap model) {
		logger.info("加载首页...");
		PointRule pointRule = new PointRule();
		pointRule.setPointType(0);
		pointRule.setCycle(0);
		model.put("pointRule", pointRule);
		return ADD_RETURN;
	}

	/** 保存页面 */
	@RequestMapping("/save.do")
	public String save(PointRuleVo pointRuleVo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = WebErrors.create(request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		if (pointRuleVo.getId() == null) {
			PointRule pointRule = new PointRule();
			BeanUtils.copyProperties(pointRuleVo, pointRule);
			// 创建时间，更新时间
			Date createdDate = new Date();
			pointRule.setCreatedId(1L);
			pointRule.setCreatedDate(createdDate);
			pointRule.setUpdatedId(1L);
			pointRule.setUpdatedDate(createdDate);

			this.pointRuleMng.save(pointRule);
		}else{
			PointRule pointRule=this.pointRuleMng.findById(pointRuleVo.getId());
			BeanUtils.copyProperties(pointRuleVo, pointRule);
			Date  updatedDate = new Date();
			pointRule.setUpdatedDate(updatedDate);
			this.pointRuleMng.update(pointRule);
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
		this.pointRuleMng.remove(id);
		return "删除成功!";
	}

	/*** 查询列表信息 */
	@RequestMapping("/list.do")
	@ResponseBody
	public String list(QueryVo queryVo, PointRuleVo ruleVo) {
		Pagination pagination = this.pointRuleMng.getPage(queryVo,
				ruleVo.getPointRuleName(), ruleVo.getPointType());
		return JSONParser.toDataGridString(pagination);
	}
	/***
	 * 编辑
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit.do")
	public String edit(@RequestParam(required = false) Long id, ModelMap model) {
		PointRule pointRule = this.pointRuleMng.findById(id);
		model.put("pointRule", pointRule);
		return ADD_RETURN;
	}

	/***
	 * 检查积分规则代码是否重复
	 * @param pointRuleNo
	 * @param id
	 * @return
	 */
	@RequestMapping("/checkpointruleno.do")
	@ResponseBody
	public String checkPointRuleNo(
			@RequestParam(required = false) String pointRuleNo,
			@RequestParam(required = false) Long id) {
		if (StringUtils.isNotEmpty(pointRuleNo) && id != null) {
			return this.pointRuleMng.checkPointRuleNo(id, pointRuleNo) + "";
		} else if (StringUtils.isNotEmpty(pointRuleNo)) {
			return this.pointRuleMng.checkPointRuleNo(pointRuleNo) + "";
		}
		return "true";
	}

}
