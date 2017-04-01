package com.action;

import java.io.File;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.stereotype.Controller;

import com.po.FileInfo;
import com.service.biz.BizService;
import com.util.WebUtil;

/**
 * 处理文件action
 * 
 * @author Zeral
 *
 */
@Controller
@Namespace("/")
public class FileAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "BizService")
	private BizService bizs;

	private File upload;
	private String uploadFileName;
	private FileInfo fileInfo;

	@Action("uploadFile")
	public void uploadFile() {
		try {
			log.info("=========开始上传文件======================================");
			this.bizs.getFileInfoBiz().uploadAndSaveFile(this.uploadFileName, this.upload, this.fileInfo);
			
			String result = this.fileInfo.getId() + ":" + this.fileInfo.getName() + ":" + this.fileInfo.getPath();
			  
			WebUtil.sendResponse(result);
			log.info("==========文件上传完毕======================================");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
	}
	
	@Action("delFile")
	public void delFile() {
		try {
			this.bizs.getFileInfoBiz().delFile(fileInfo.getId());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

}
