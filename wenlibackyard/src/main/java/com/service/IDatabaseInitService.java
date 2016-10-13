package com.service;



import com.bean.DatabaseInitInfo;


/**
 * 数据库的初始化
 * 
 * @author Zeral
 * @date 2013-3-22
 */
public interface IDatabaseInitService {
	
	
	/**
	 * 初始化数据库，如建表和视图，更新数据库表和视图等
	 * @param initInfo
	 * @throws Exception 
	 */
	void initDatabase(DatabaseInitInfo initInfo) throws Exception;
	
}
