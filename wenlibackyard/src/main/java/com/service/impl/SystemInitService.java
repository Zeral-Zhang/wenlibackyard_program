package com.service.impl;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.po.DatabaseInitInfo;
import com.service.IDatabaseInitService;
import com.util.XMLUtil;


/**
 * 系统初始化Service
 * @author Zeral 2016-7-20<br/>
 * 
 */
@Service("systemInitService")
public class SystemInitService {

	private static final Logger log = Logger.getLogger(SystemInitService.class);
	
/*	@Autowired
	protected IBasicConfigService basicConfigService;
	
	@Autowired
	protected ISystemConfigService systemConfigService;*/
	
	@Autowired
	protected IDatabaseInitService databaseInitService;

	/**
	 * 初始化方法
	 * 
	 * @param beanName
	 * @param context
	 * @author Zeral
	 */
	public static final void initialized(final String beanName, final ServletContext context) {
		try {
			final WebApplicationContext applicationContext = WebApplicationContextUtils
					.getWebApplicationContext(context);
			final SystemInitService initService = (SystemInitService) applicationContext
					.getBean(beanName, SystemInitService.class);
			initService.init(context);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 初始化数据库
	 * 
	 * @author Zeral
	 */
	private void initDatabase() {
		final DatabaseInitInfo databaseInitInfo = XMLUtil.getXMLRoot(
				DatabaseInitInfo.class, "sql/DatabaseInitInfo.xml");
		databaseInitService.initDatabase(databaseInitInfo);
	}

	

	/**
	 * 初始化
	 * 
	 * @author Zeral
	 */
	private void init(final ServletContext context) {
		initDatabase();
//		setSystemProperties(context);
	}
/*
	/**
	 * 设置系统属性
	 * 
	 * @author Zeral
	 *//*
	protected void setSystemProperties(final ServletContext context) {
		setTheme(context);
		setSyFileWebRoot(context);
		setCasServerUrlPrefix(context);
		setBaseUrl(context);		
	}

	/**
	 * 设置系统UI主题
	 * @author Zeral
	 *//*
	private void setTheme(final ServletContext context) {
		context.setAttribute(CommonConstants.THEME, "standard");
	}

	/**
	 * 设置附件服务器web root
	 * @param context
	 * @author Zeral
	 *//*
	private void setSyFileWebRoot(final ServletContext context) {
		final String syFileUrl = systemConfigService.getSyFileWebRoot();
		context.setAttribute(CommonConstants.FILE_URL, syFileUrl);
	}

	/**
	 * 设置CAS Server url
	 * @author Zeral
	 *//*
	private void setCasServerUrlPrefix(final ServletContext context) {
		final String casServerUrlPrefix = basicConfigService.findValueById(
				CommonConstants.CAS_SERVER_URL_PREFIX_KEY);
		context.setAttribute(CommonConstants.CAS_SERVER_URL_PREFIX_KEY,
				casServerUrlPrefix);
	}

	*//**
	 * 设置基础平台URL
	 * @author Zeral
	 *//*
	private void setBaseUrl(final ServletContext context) {
		context.setAttribute(CommonConstants.BASE_SERVER_NAME, systemConfigService
				.getBaseServerName());
		context.setAttribute(CommonConstants.BASE_URL, systemConfigService
				.getBaseWebRoot());
	}
	
*/

}
