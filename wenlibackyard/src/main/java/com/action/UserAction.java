package com.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.annotation.Resource;
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
	@Resource(name="BizService")
	private BizService biz;

	public Userinfo getUser() {
		return user;
	}

	public void setUser(Userinfo user) {
		this.user = user;
	}

	public BizService getBiz() {
		return biz;
	}

	public void setBiz(BizService biz) {
		this.biz = biz;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Action(value = "login_User")
	public void login() {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = ServletActionContext.getRequest().getSession();
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if("".equals(userid)) {
			out.write("{\"status\":\"0\"}");
		} else {
			Userinfo userInfo = biz.getUserbiz().login(userid);
			if(userInfo == null) {
				out.write("{\"status\":\"-1\"}");
			} else {
				session.setAttribute("userInfo", userInfo);
				out.write("{\"status\":\"1\"}");
			}
		}
		
	}


	@Action(value="update_User", results = {
			@Result(name="success", location="/userDetail.jsp", type="redirect"),
			@Result(name="failed", location="/error.jsp", type="redirect")
		})
	public String update() {
		try {
			// 设置用户语言信息
			user.getUserdetailinfo().setUserLanguage(ServletActionContext.getRequest().getLocale().toString());
			// 修改用户信息，重新放置用户到session中
			biz.getUserbiz().update(user);
			// 通过id找到修改后的用户信息保存到session域中
			Userinfo newUser = biz.getUserbiz().login(user.getUserId());
			ServletActionContext.getRequest().getSession().setAttribute("userInfo", newUser);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
	}

}
