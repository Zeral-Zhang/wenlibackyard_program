package com.biz;

import java.io.File;

import com.po.FileInfo;

public interface IFileInfoBiz {

	public abstract File uploadFile(File paramFile, String paramString2);

	public abstract File uploadAndSaveFile(String paramString, File paramFile, FileInfo paramFileInfo,
			boolean paramBoolean);

	public abstract File uploadAndSaveFile(String paramString, File paramFile, FileInfo paramFileInfo);

}
