package com.biz.imp;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.biz.IFileInfoBiz;
import com.dao.FileInfoDao;
import com.po.FileInfo;
import com.util.FileUtil;

@Service("OrderBizImpl")
public class FileInfoBizImpl implements IFileInfoBiz {
	private static final Logger log = Logger.getLogger(OrderBizImpl.class);
	@Resource(name = "FileInfoDao")
	private FileInfoDao fileInfoDao;

	public void save(FileInfo fileInfo) {
		try {
			fileInfo.setPublishTime(new Date(System.currentTimeMillis()));
			this.fileInfoDao.save(fileInfo);
		} catch (Exception e) {
			log.error("保存文件失败", e);
			e.printStackTrace();
		}
	}

	public File uploadFile(File source, String fileInfoName) {
		try {
			File destFile = FileUtil.getDestFile(fileInfoName);
			FileUtils.copyFile(source, destFile);
			return destFile;
		} catch (IOException e) {
			log.error("上传文件失败", e);
			return null;
		}
	}

	public File uploadAndSaveFile(String uploadFileName, File source, FileInfo fileInfo, boolean isVideo) {
		String id = UUID.randomUUID().toString();
		fileInfo.setId(id);
		String suffix = FileUtil.getFileSuffix(uploadFileName);
		File destFile = uploadFile(source, id + suffix);
		if (isVideo) {
			suffix = ".flv";
		}
		String relativeFilePath = FileUtil.getRelativeFilePath(id + suffix);
		fileInfo.setName(uploadFileName);
		fileInfo.setPath("/" + relativeFilePath);
		fileInfo.setStatus(Integer.valueOf(0));
		fileInfo.setFileSize(Double.valueOf(source.length() / 1024L));
		fileInfo.setOriginalFormat(FileUtil.rtnFileType(uploadFileName));
		save(fileInfo);
		return destFile;
	}

	public File uploadAndSaveFile(String uploadFileName, File source, FileInfo fileInfo) {
		return uploadAndSaveFile(uploadFileName, source, fileInfo, false);
	}

}
