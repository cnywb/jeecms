package com.jeecms.cms.manager.main.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.ContentDocDao;
import com.jeecms.cms.entity.main.CmsConfig;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.entity.main.ContentDoc;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.cms.manager.main.ContentCountMng;
import com.jeecms.cms.manager.main.ContentDocMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.office.FileUtils;
import com.jeecms.common.office.OpenOfficeConverter;
import com.jeecms.common.office.PdfToSwfConverter;
import com.jeecms.common.web.springmvc.RealPathResolver;

@Service
@Transactional
public class ContentDocMngImpl implements ContentDocMng {
	public ContentDoc save(ContentDoc doc, Content content) {
		if (StringUtils.isBlank(doc.getDocPath())) {
			return null;
		} else {
			doc.setContent(content);
			doc.init();
			dao.save(doc);
			content.setContentDoc(doc);
			return doc;
		}
	}

	public ContentDoc update(ContentDoc doc, Content content) {
		ContentDoc entity = dao.findById(content.getId());
		if (entity == null) {
			entity = save(doc, content);
			content.getContentDocSet().add(entity);
			return entity;
		} else {
			if (StringUtils.isBlank(doc.getDocPath())) {
				content.getContentDocSet().clear();
				return null;
			} else {
				Updater<ContentDoc> updater = new Updater<ContentDoc>(doc);
				entity = dao.updateByUpdater(updater);
				return entity;
			}
		}
	}
	
	public ContentDoc operateDocGrain(CmsUser downUser, ContentDoc doc) {
		downUser.setGrain(downUser.getGrain() - doc.getDownNeed());
		userMng.updateUser(downUser);
		CmsUser owner = doc.getContent().getUser();
		owner.setGrain(doc.getDownNeed() + owner.getGrain());
		userMng.updateUser(owner);
		doc.setGrain(doc.getGrain() + doc.getDownNeed());
		contentCountMng.downloadCount(doc.getId());
		update(doc,doc.getContent());
		return doc;
	}
	
	public ContentDoc createSwfFile(ContentDoc doc){
			CmsSite site=doc.getContent().getSite();
			CmsConfig config=site.getConfig();
			String swfhome=config.getSwftoolsHome();
			String ctx=config.getContextPath();
			String path = doc.getDocPath();
			//swf文件放在和文档目录同级目录下
			String swfPath=FileUtils.getFilePath(path);
			if(StringUtils.isNotBlank(ctx)&&path.indexOf(ctx)!=-1){
				path = path.split(ctx)[1];
			}
			//获取文件真实路径
			String fileRealPath = realPathResolver.get(path);
			String fileName=FileUtils.getFileName(path);
			String outPdfRealPath = realPathResolver.get(FileUtils.getFilePath(path));
			//转换文档成pdf
			File pdfFile = openOfficeConverter.convertToPdf(fileRealPath,outPdfRealPath + "/",fileName);
			//获取要转换的swf真实路径
			String outSwfRealPath = realPathResolver.get(FileUtils.getFilePath(path))+ "/";
			//swf文件名称和转换的pdf文件名相同
			String swfFileName=fileName+".swf";
			//转换pdf为swf
			int processStatus=1;
			try {
				processStatus = PdfToSwfConverter.convertPDF2SWF(swfhome, pdfFile.getAbsolutePath(), outSwfRealPath,swfFileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//更新文档对象swf路径为swf相对路径
			String swfFile=swfPath+swfFileName;
			doc.setSwfPath(swfFile);
			doc=update(doc,doc.getContent());
			//删除pdf文件
			if(processStatus==0){
				pdfFile.delete();
			}
			return doc;
	}

	private ContentDocDao dao;
	@Autowired
	private OpenOfficeConverter openOfficeConverter;
	@Autowired
	private RealPathResolver realPathResolver;
	@Autowired
	private CmsUserMng userMng;
	@Autowired
	private ContentCountMng contentCountMng;

	@Autowired
	public void setDao(ContentDocDao dao) {
		this.dao = dao;
	}
}