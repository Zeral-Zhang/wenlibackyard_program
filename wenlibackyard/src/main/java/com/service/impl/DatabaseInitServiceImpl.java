package com.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.DatabaseInitInfo;
import com.bean.DatabaseUpdateInfo;
import com.dao.DatabaseInitDAO;
import com.exception.BaseException;
import com.po.BasicConfig;
import com.service.IBasicConfigService;
import com.service.IDatabaseInitService;
import com.service.dao.DaoService;
import com.util.ResourceUtil;
import com.util.SQLUtil;


@Service("DatabaseInitService")
public class DatabaseInitServiceImpl implements IDatabaseInitService {
	
	
	static final String BASIC_TABLE_INIT_VERSIONS = "1.0";

	@Resource(name = "DaoService")
	private DaoService daos;

	public DaoService getDaos() {
		return daos;
	}

	public void setDaos(DaoService daos) {
		this.daos = daos;
	}
	

	@Resource(name="BasicConfigService")
	private IBasicConfigService basicConfigService;

	public void createTables(List<String> sqlPaths, BasicConfig initVersion) {
		try {
			if (isDataBaseInit(initVersion.getBasicConfigId())) {
				return;
			}
			executeSql(sqlPaths);
			basicConfigService.saveOrUpdate(initVersion);
		} catch (Exception e) {
			throw new BaseException(e.getMessage(), e);
		}
	}

	/**
	 * 判断数据库是否初始化
	 * 
	 * @param initCode
	 * @return
	 */
	private boolean isDataBaseInit(String initCode) {
		BasicConfig config = basicConfigService.findByCode(initCode);
		return config != null;
	}

	/**
	 * 判断数据库是否初始化
	 * 
	 * @param initInfo
	 * @return
	 */
	private boolean isDataBaseInit(DatabaseInitInfo initInfo) {
		if (initInfo.isBase()) {
			int count = daos.getDataBaseInitDAO().getTableCount();
			if (count <= 0) {
				return false;
			}
		}
		BasicConfig config = basicConfigService
				.findByCode(initInfo.getInitCode());
		return config != null;
	}

	public void initDatabase(DatabaseInitInfo initInfo) {
		createTables(initInfo);
		updateTables(initInfo.getUpdateInfoList(), initInfo.getInitCode());
	}

	private void updateTables(List<DatabaseUpdateInfo> updateInfoList,
			String initCode) {
		if (updateInfoList.isEmpty()) {
			return;
		}
		for (DatabaseUpdateInfo info : updateInfoList) {
			BasicConfig version = basicConfigService.findByCode(initCode);
			if(version != null){
				if (Float.parseFloat(version.getValue()) >= Float.parseFloat(info
						.getUpdateVersion())) {
					continue;
				}
			}else{
				version = new BasicConfig();
				version.setBasicConfigId(initCode);
			}			
			executeSql(info.getSqlPaths());
			createProcedure(info.getProcedurePaths());
			
			version.setValue(info.getUpdateVersion());
			basicConfigService.saveOrUpdate(version);
		}

	}

	/**
	 * 创建数据库脚本
	 * 
	 * @param createInfo
	 * @param initCode
	 */
	private void createTables(DatabaseInitInfo initInfo) {
		if (isDataBaseInit(initInfo)) {
			return;
		}
		if(initInfo.getCreateInfo() == null){
			return;
		}
		executeSql(initInfo.getCreateInfo().getSqlPaths());
		createProcedure(initInfo);
		BasicConfig initVersion = new BasicConfig();
		initVersion.setBasicConfigId(initInfo.getInitCode());
		initVersion.setName("数据库版本");
		initVersion.setValue(initInfo.getCreateInfo().getInitVersion());
		basicConfigService.saveOrUpdate(initVersion);
	}
	

	/**
	 * 批量执行sql脚本文件
	 * 
	 * @param sqlPaths
	 */
	private void executeSql(List<String> sqlPaths) {
		for (String sqlPath : sqlPaths) {
			List<String> sqls = SQLUtil.parseSQLFile(sqlPath);
			daos.getDataBaseInitDAO().excuteSql(sqls);
		}
	}
	/**
	 * 创建存储过程
	 * @author Zeral
	 * @param initInfo
	 */
	private void createProcedure(DatabaseInitInfo initInfo){
		List<String> procedurePaths = initInfo.getCreateInfo().getProcedurePaths();
		createProcedure(procedurePaths);
	}
	
	private void createProcedure(List<String> procedurePaths){
		for (String procPath : procedurePaths) {
			String text = getFileText(procPath);
			String[] sqls = text.split("#");
			List<String> procSqls = new ArrayList<String>(sqls.length);
			for (String sql : sqls) {
				procSqls.add(sql);
			}
			try {
				daos.getDataBaseInitDAO().excuteSql(procSqls);
			} catch (Exception e) {
				throw new BaseException("执行sql文件："+procPath+"时出错！",e);
			}
		}
	}
	
	String getFileText(String classPath){
		InputStream is = ResourceUtil.getResourceAsStream(classPath);
		String str = null;
		try {
			byte[] bs = new byte[is.available()];
			is.read(bs);
			is.close();
			str = new String(bs, "utf-8");
		} catch (UnsupportedEncodingException e) {
		} catch (IOException e) {
			throw new BaseException("文件读取失败："+classPath,e);
		}
		return str;
	}

}
