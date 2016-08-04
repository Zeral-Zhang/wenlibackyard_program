package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.po.Userinfo;
import com.service.biz.BizService;

/**
 * @author Zeral_Zhang
 * 
 */
@Controller
@Namespace("/")
public class UserAction implements IUserAction {
	private Userinfo user;
	private String userid;
	@Resource(name = "BizService")
	private BizService biz;

	@Action(value = "userLogin", results = { @Result(name = "success", location = "/WEB-INF/userLogin.jsp", type = "dispatcher"), })
	public String initLogin() {
		return "success";
	}

	@Action(value = "login_User")
	public void login() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = ServletActionContext.getRequest().getSession();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if ("".equals(userid)) {
				out.write("{\"status\":\"0\"}");
			} else {
				Userinfo userInfo = biz.getUserbiz().findUser(userid);
				if (userInfo == null) {
					out.write("{\"status\":\"-1\"}");
				} else {
					session.setAttribute("userInfo", userInfo);
					out.write("{\"status\":\"1\"}");
				}
			}
		} catch (Exception e) {
			out.write("{\"status\":\"-1\"}");
		}

	}

	@Action(value = "userInfo", results = {
			@Result(name = "success", location = "/WEB-INF/userInfo.jsp"),
			@Result(name = "login", location = "WEB-INF/userLogin.jsp")
	})
	@Override
	public String initUserInfo() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("userInfo")) {
			request.setAttribute("originURL", "userInfo");
			return "login";
		}
		return "success";
	}

	@Action(value = "update_User", results = {
			@Result(name = "success", location = "/WEB-INF/userDetail.jsp", type = "redirect"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp", type = "redirect") })
	public String update() {
		try {
			// 设置用户语言信息
			user.getUserdetailinfo().setUserLanguage(
					ServletActionContext.getRequest().getLocale().toString());
			// 修改用户信息，重新放置用户到session中
			biz.getUserbiz().update(user);
			// 通过id找到修改后的用户信息保存到session域中
			Userinfo newUser = biz.getUserbiz().findUser(user.getUserId());
			ServletActionContext.getRequest().getSession()
					.setAttribute("userInfo", newUser);
			return "success";
		} catch (Exception e) {
			return "failed";
		}
	}

	public Userinfo getUser() {
		return user;
	}

	public void setUser(Userinfo user) {
		this.user = user;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
