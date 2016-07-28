package com.bean;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
/**
 * 更新数据库信息对象
 * @author 唐延波
 * 2013-5-29
 */
public class DatabaseUpdateInfo {

	/**更新脚本classpath*/
	@ElementList(inline=true,entry="sqlPath")
	private List<String> sqlPaths = new ArrayList<String>();
	
	/**存储过程脚本classpath*/
	@ElementList(inline=true,entry="procPath",required=false)
	private List<String> procedurePaths = new ArrayList<String>();
	
	/**更新版本*/
	@Attribute
	private String updateVersion;
	
	public static final DatabaseUpdateInfo getInstance(String sqlPath,String updateVersion){
		DatabaseUpdateInfo updateInfo = new DatabaseUpdateInfo();
		updateInfo.getSqlPaths().add(sqlPath);
		updateInfo.setUpdateVersion(updateVersion);
		return updateInfo;
	}
	
	public static final DatabaseUpdateInfo getInstance(List<String> sqlPaths,String updateVersion){
		DatabaseUpdateInfo updateInfo = new DatabaseUpdateInfo();
		updateInfo.setSqlPaths(sqlPaths);
		updateInfo.setUpdateVersion(updateVersion);
		return updateInfo;
	}

	public List<String> getSqlPaths() {
		return sqlPaths;
	}

	public void setSqlPaths(List<String> sqlPaths) {
		this.sqlPaths = sqlPaths;
	}
	
	public List<String> getProcedurePaths() {
		return procedurePaths;
	}

	public void setProcedurePaths(List<String> procedurePaths) {
		this.procedurePaths = procedurePaths;
	}

	public String getUpdateVersion() {
		return updateVersion;
	}

	public void setUpdateVersion(String updateVersion) {
		this.updateVersion = updateVersion;
	}
	
	
}
