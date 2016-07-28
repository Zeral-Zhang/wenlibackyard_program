package com.bean;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.po.DatabaseCreateInfo;



/**
 * 数据库初始化信息对象
 * @author Zeral
 * 2016-7-20
 */
@Root
public class DatabaseInitInfo {
	
	/**
	 * 初始化标识，如oa-database-init
	 */
	@Attribute
	private String initCode;
	
	/**
	 * 初始化建表信息
	 */
	@Element
	private DatabaseCreateInfo createInfo;
	
	/**
	 * 是否基础模块，默认为false
	 */
	@Attribute(required=false)
	private boolean isBase = false;
	
	/**
	 * 数据库更新信息
	 */
	@ElementList(inline=true,entry="updateInfo",required=false)
	private List<DatabaseUpdateInfo> updateInfoList = new ArrayList<DatabaseUpdateInfo>();

	public String getInitCode() {
		return initCode;
	}

	public void setInitCode(String initCode) {
		this.initCode = initCode;
	}

	public DatabaseCreateInfo getCreateInfo() {
		return createInfo;
	}

	public void setCreateInfo(DatabaseCreateInfo createInfo) {
		this.createInfo = createInfo;
	}

	public List<DatabaseUpdateInfo> getUpdateInfoList() {
		return updateInfoList;
	}

	public void setUpdateInfoList(List<DatabaseUpdateInfo> updateInfoList) {
		this.updateInfoList = updateInfoList;
	}

	public boolean isBase() {
		return isBase;
	}

	public void setBase(boolean isBase) {
		this.isBase = isBase;
	}

	
	
}

