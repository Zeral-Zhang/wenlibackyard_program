package com.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.po.UserInfo;
/**
 * 基础通用Action 
 * 
 * @author Zeral
 * @date 2016-10-17
 *
 */
@Results({ @Result(name = com.opensymphony.xwork2.Action.ERROR, location = "/WEB-INF/error.jsp") })
public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	protected final Logger log = Logger.getLogger(this.getClass());
	protected UserInfo user;
	
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	protected ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	protected HttpSession getHttpSession() {
		return getRequest().getSession();
	}

	public UserInfo getLoginUser() {
		return (UserInfo) getRequest().getSession().getAttribute("userInfo");
	}

	public void setLoginUser(UserInfo user) {
		getRequest().getSession().setAttribute("userInfo", user);
	}
}
