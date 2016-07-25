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

import com.bean.SNSUserInfo;
import com.bean.WeixinOauth2Token;
import com.po.Userinfo;
import com.service.biz.BizService;
import com.util.HttpsUtil;
import com.util.PropertiesConfigUtil;

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


	@Action(value = "validate_User", results = {
			@Result(name = "success", location = "/list.jsp", type = "dispatcher"),
			@Result(name = "error", location = "/error.jsp", type = "redirect") 
	})
	public String validate() {
		Properties prop = PropertiesConfigUtil
				.getProperties("account.properties");
		HttpServletRequest request = ServletActionContext.getRequest();
		// 用户同意授权后，能获取到code
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		// 用户同意授权
		if (!"authdeny".equals(code)) {
			// 获取网页授权access_token
			WeixinOauth2Token weixinOauth2Token = HttpsUtil
					.getOauth2AccessToken(prop.getProperty("appid"),
							prop.getProperty("appsecret"), code);
			// 网页授权接口访问凭证
			String accessToken = weixinOauth2Token.getToken();
			// 用户标识
			String openId = weixinOauth2Token.getOpenId();
			// 获取用户信息
			SNSUserInfo snsUserInfo = HttpsUtil.getSNSUserInfo(accessToken,
					openId);
			// 设置要传递的参数
			request.getSession().setAttribute("snsUserInfo", snsUserInfo);
			request.setAttribute("state", state);
			return "success";
		} else {
			return "error";
		}
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
			@Result(name="success", location="/userdetail.jsp", type="redirect"),
			@Result(name="failed", location="/error.jsp", type="redirect")
		})
	public String update() {
		try {
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
