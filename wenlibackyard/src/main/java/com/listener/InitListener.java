package com.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.service.impl.SystemInitService;


public class InitListener implements ServletContextListener {
	private final Logger log = Logger.getLogger(this.getClass());
	
	public InitListener() {
	}

	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent event) {
		try {
			SystemInitService.initialized("baseInitService", event.getServletContext());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
}
