package com.action;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.bean.WeixinOauth2Token;
import com.constant.WenlibackyardConstant;
import com.po.SchoolInfo;
import com.po.UserDetailInfo;
import com.po.UserInfo;
import com.service.biz.BizService;
import com.util.HttpsUtil;
import com.util.PropertiesConfigUtil;
import com.util.WebUtil;

import net.sf.json.JSONObject;

/**
 * @author Zeral_Zhang
 * 
 */
@Controller
@Namespace("/")
public class UserAction extends BaseAction implements IUserAction {

	private static final long serialVersionUID = 1L;

	private UserInfo userInfo;
	private UserDetailInfo userDetail;
	private String userid;
	private String path;
	/**
	 * 学院信息
	 */
	private List<SchoolInfo> schoolInfolst;
	/**
	 * 系信息
	 */
	private List<SchoolInfo> departmentlst;

	@Resource(name = "BizService")
	private BizService biz;

	@Action(value = "validateUser", results = {
			@Result(name = "success", location = "${path}", type = "redirectAction"),
			@Result(name = "error", location = "/WEB-INF/error.jsp") 
	})
	@Override
	public String validateUser() {
		Properties prop = PropertiesConfigUtil.getProperties("account.properties");
		HttpServletRequest request = ServletActionContext.getRequest();
		// 用户同意授权后，能获取到code
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		path = StringUtils.isEmpty(state) ? "toProductList" : state;
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
			userInfo = HttpsUtil.getSNSUserInfo(accessToken, openId);
			// 设置要传递的参数
			request.getSession().setAttribute("userInfo", userInfo);
			return "success";
		} else {
			return "error";
		}
	}

	@Override
	@Action(value = "userLogin", results = { @Result(name = "success", location = "/WEB-INF/userLogin.jsp") })
	public String initLogin() {
		return "success";
	}

	@Action(value = "toUserDetail", results = {
			@Result(name = "success", location = "/WEB-INF/new_front/userDetail.jsp"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp") })
	@Override
	public String toUserDetail() {
		try {
			schoolInfolst = biz.getSchoolInfoBiz().findColleges();
			if (null != getLoginUser().getUserDetailInfo() && null != getLoginUser().getUserDetailInfo().getSchoolInfo()) {
				departmentlst = biz.getSchoolInfoBiz().findByCollegeId(getLoginUser().getUserDetailInfo().getSchoolInfo().getPCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}

	@Action(value = "login_User")
	@Override
	public void login() {
		JSONObject object = null;
		try {
			if ("".equals(userid)) {
				object = new JSONObject();
				object.put("status", "0");
				WebUtil.sendJSONObjectResponse(object);
			} else {
				UserInfo userInfo = biz.getUserbiz().findUser(userid);
				if (userInfo == null) {
					object = new JSONObject();
					object.put("status", "-1");
					WebUtil.sendJSONObjectResponse(object);
				} else {
					super.setLoginUser(userInfo);
					object = new JSONObject();
					object.put("status", "1");
					WebUtil.sendJSONObjectResponse(object);
				}
			}
		} catch (Exception e) {
			object = new JSONObject();
			object.put("status", "-1");
			WebUtil.sendJSONObjectResponse(object);
		}

	}

	@Action(value = "toUserInfo", results = { @Result(name = "success", location = "/WEB-INF/new_front/userInfo.jsp"),
			@Result(name = "login", location = "/WEB-INF/userLogin.jsp") })
	@Override
	public String toUserInfo() {
		try {
			if (null == super.getLoginUser()) {
				getResponse().sendRedirect(HttpsUtil.AuthLogin(WenlibackyardConstant.VALIDATE_URL, "toUserInfo"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	@Action(value = "updateUser", results = { @Result(name="success", location="toUserDetail",type="redirectAction"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp") })
	@Override
	public String update() {
		try {
			UserInfo oldUser = getLoginUser();
			userDetail.setUserInfo(oldUser);
			userDetail.setUserGender(oldUser.getUserDetailInfo().getUserGender());
			userDetail.setUserLanguage(oldUser.getUserDetailInfo().getUserLanguage());
			// 将修改后的用户信息保存到session域中
			getLoginUser().setUserDetailInfo(biz.getUserbiz().update(userDetail));
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
	}


	public UserDetailInfo getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetailInfo userDetail) {
		this.userDetail = userDetail;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public List<SchoolInfo> getSchoolInfolst() {
		return schoolInfolst;
	}

	public void setSchoolInfolst(List<SchoolInfo> schoolInfolst) {
		this.schoolInfolst = schoolInfolst;
	}

	public List<SchoolInfo> getDepartmentlst() {
		return departmentlst;
	}

	public void setDepartmentlst(List<SchoolInfo> departmentlst) {
		this.departmentlst = departmentlst;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
