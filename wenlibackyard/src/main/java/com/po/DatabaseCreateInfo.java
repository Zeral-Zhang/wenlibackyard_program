package com.po;

import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.ElementList;

/**
 * 数据库建表信息对象
 * @author Zeral
 * 2013-5-29
 */
public class DatabaseCreateInfo {
	
	private static final String DEFAULT_INIT_VERSION = "1.0";

	/**建表脚本classpath*/
	@ElementList(inline=true,entry="sqlPath")
	private List<String> sqlPaths = new ArrayList<String>(){
		public boolean add(String e) {
			e = e.trim();
			return super.add(e);
		}
	};
	
	/**存储过程脚本classpath*/
	@ElementList(inline=true,entry="procPath",required=false)
	private List<String> procedurePaths = new ArrayList<String>();
	
	/**建表初始化版本*/
	private String initVersion = DEFAULT_INIT_VERSION;

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

	public String getInitVersion() {
		return initVersion;
	}

	public void setInitVersion(String initVersion) {
		this.initVersion = initVersion;
	}

	
}
