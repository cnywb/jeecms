package com.jeecms.bbs.action.member;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jeecms.bbs.entity.BbsUser;
import com.jeecms.bbs.web.CmsUtils;
import com.jeecms.bbs.web.FrontUtils;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.common.image.ImageUtils;
import com.jeecms.common.upload.FileRepository;
import com.jeecms.core.web.WebErrors;

@Controller
public class CarImageUploadAct {
	private static final Logger log = LoggerFactory
			.getLogger(CarImageUploadAct.class);
	/**
	 * 用户头像路径
	 */
	private static final String USER_IMG_PATH = "/user/car";
	/**
	 * 结果页
	 */
	private static final String RESULT_PAGE = "/WEB-INF/t/cms/www/blue/member/car_iframe_upload.html";
	/**
	 * 错误信息参数
	 */
	public static final String ERROR = "error";
	
	private static final Integer  IMAGE_WIDTH=385;
	private static final Integer  IMAGE_HEIGHT=264;

	@RequestMapping("/member/car/o_upload_image.jhtml")
	public String execute(
			String filename,
			Integer uploadNum,
			Boolean mark,
			@RequestParam(value = "uploadFile", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validate(filename, file, request);
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		if (errors.hasErrors()) {
			model.addAttribute(ERROR, errors.getErrors().get(0));
			return RESULT_PAGE;
		}
		String origName = file.getOriginalFilename();
		String ext = FilenameUtils.getExtension(origName).toLowerCase(
				Locale.ENGLISH);
		try {
			String fileUrl;
				String ctx = request.getContextPath();
				if (!StringUtils.isBlank(filename)) {
					filename = filename.substring(ctx.length());
						fileUrl = fileRepository
								.storeByFilename(filename, file);
				} else {
						fileUrl = fileRepository.storeByExt(USER_IMG_PATH, ext, file);
					// 加上部署路径
					fileUrl = ctx + fileUrl;
				}
			model.addAttribute("uploadPath", fileUrl);
			model.addAttribute("uploadNum", uploadNum);
			model.addAttribute("image_width",IMAGE_WIDTH);
			model.addAttribute("image_heigth",IMAGE_HEIGHT);
		} catch (IllegalStateException e) {
			model.addAttribute(ERROR, e.getMessage());
			log.error("upload file error!", e);
		} catch (IOException e) {
			model.addAttribute(ERROR, e.getMessage());
			log.error("upload file error!", e);
		} catch (Exception e) {
			model.addAttribute(ERROR, e.getMessage());
			log.error("upload file error!", e);
		}
		return RESULT_PAGE;
	}

	private WebErrors validate(String filename, MultipartFile file,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (file == null) {
			errors.addErrorCode("imageupload.error.noFileToUpload");
			return errors;
		}
		if (StringUtils.isBlank(filename)) {
			filename = file.getOriginalFilename();
		}
		String ext = FilenameUtils.getExtension(filename);
		if (!ImageUtils.isValidImageExt(ext)) {
			errors.addErrorCode("imageupload.error.notSupportExt", ext);
			return errors;
		}
		try {
			if (!ImageUtils.isImage(file.getInputStream())) {
				errors.addErrorCode("imageupload.error.notImage", ext);
				return errors;
			}
		} catch (IOException e) {
			log.error("image upload error", e);
			errors.addErrorCode("imageupload.error.ioError", ext);
			return errors;
		}
		return errors;
	}

	
	private FileRepository fileRepository;

	@Autowired
	public void setFileRepository(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}


}