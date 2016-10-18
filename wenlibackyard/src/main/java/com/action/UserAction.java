package com.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.po.SchoolInfo;
import com.po.UserInfo;
import com.service.biz.BizService;
import com.util.WebUtil;

import net.sf.json.JSONObject;

/**
 * @author Zeral_Zhang
 * 
 */
@Controller
@Namespace("/")
public class UserAction extends BaseAction implements IUserAction  {
	
	private static final long serialVersionUID = 1L;
	
	private UserInfo user;
	private String userid;
	/**
	 *  学院信息
	 */
	private List<SchoolInfo> schoolInfolst;
	/**
	 * 系信息
	 */
	private List<SchoolInfo> departmentlst;
	
	@Resource(name = "BizService")
	private BizService biz;

	@Action(value = "userLogin", results = { @Result(name = "success", location = "/WEB-INF/userLogin.jsp") })
	public String initLogin() {
		return "success";
	}
	
	@Action(value = "userDetail", results = { @Result(name = "success", location = "/WEB-INF/userDetail.jsp") })
	public String initUserDetail() {
		try {
			schoolInfolst = biz.getSchoolInfoBiz().findColleges();
			departmentlst = biz.getSchoolInfoBiz().findByCollegeId(getLoginUser().getUserDetailInfo().getSchoolInfo().getPCode());
		} catch (Exception e) {
			e.printStackTrace();
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

	@Action(value = "userInfo", results = {
			@Result(name = "success", location = "/WEB-INF/userInfo.jsp"),
			@Result(name = "login", location = "/WEB-INF/userLogin.jsp")
	})
	@Override
	public String initUserInfo() {
		if (null == super.getLoginUser()) {
			getRequest().setAttribute("originURL", "userInfo");
			return "login";
		}
		return "success";
	}

	@Action(value = "updateUser", results = {
			@Result(name = "success", location = "/WEB-INF/userDetail.jsp"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp") })
	@Override
	public String update() {
		try {
			// 设置用户语言信息
			user.getUserDetailInfo().setUserLanguage(getRequest().getLocale().toString());
			// 修改用户信息，重新放置用户到session中
			biz.getUserbiz().update(user);
			// 通过id找到修改后的用户信息保存到session域中
			UserInfo newUser = biz.getUserbiz().findUser(user.getUserId());
			super.setLoginUser(newUser);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
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
	
}
