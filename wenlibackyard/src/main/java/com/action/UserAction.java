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

import com.bean.PageBean;
import com.bean.WeixinOauth2Token;
import com.constant.WenlibackyardConstant;
import com.po.OrderMain;
import com.po.ProductInfo;
import com.po.SchoolInfo;
import com.po.UserDetailInfo;
import com.po.UserInfo;
import com.service.biz.BizService;
import com.util.HttpsUtil;
import com.util.PropertiesConfigUtil;

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
	private PageBean pageBean;
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
		if (StringUtils.isNotBlank(code)) {
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
			UserInfo user = getLoginUser();
			user.setUserDetailInfo(biz.getUserbiz().findUserDetail(getLoginUser().getUserId()));
			schoolInfolst = biz.getSchoolInfoBiz().findColleges();
			if (null != user.getUserDetailInfo() && null != user.getUserDetailInfo().getSchoolInfo()) {
				departmentlst = biz.getSchoolInfoBiz().findByCollegeId(user.getUserDetailInfo().getSchoolInfo().getPCode());
			}
			setLoginUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}

	@Action(value = "toUserInfo", results = { 
			@Result(name = "success", location = "/WEB-INF/new_front/userInfo.jsp") 
			})
	@Override
	public String toUserInfo() {
		try {
			if (null == super.getLoginUser()) {
				getResponse().sendRedirect(HttpsUtil.AuthLogin(WenlibackyardConstant.VALIDATE_URL, "toUserInfo"));
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}

	@Action(value = "toUserSaling", results = { 
			@Result(name = "success", location = "/WEB-INF/new_front/userSaling.jsp") 
			})
	@Override
	public String toUserSaling() {
		try {
			if (null == super.getLoginUser()) {
				getResponse().sendRedirect(HttpsUtil.AuthLogin(WenlibackyardConstant.VALIDATE_URL, "toUserSaling"));
				return null;
			}
			pageBean = pageBean == null ? new PageBean() : pageBean;
			List<ProductInfo> lsemp = biz.getProductInfobiz().findByUserId(pageBean, getLoginUser().getUserId());
			pageBean.setPagelist(lsemp);
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}
	
	@Action(value = "toUserPayed", results = { 
			@Result(name = "success", location = "/WEB-INF/new_front/userPayed.jsp") 
			})
	@Override
	public String toUserPayed() {
		try {
			if (null == super.getLoginUser()) {
				getResponse().sendRedirect(HttpsUtil.AuthLogin(WenlibackyardConstant.VALIDATE_URL, "toUserSaling"));
				return null;
			}
			pageBean = pageBean == null ? new PageBean() : pageBean;
			List<OrderMain> lsemp = biz.getProductInfobiz().findOrderMain(pageBean, getLoginUser().getUserId());
			pageBean.setPagelist(lsemp);
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}
	
	@Action(value = "updateUser", results = { @Result(name="success", location="toUserDetail",type="redirectAction"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp") })
	@Override
	public String update() {
		try {
			userDetail.setUserInfo(getLoginUser().getUserId());
			// 将修改后的用户信息保存到session域中
			getLoginUser().setUserDetailInfo(biz.getUserbiz().update(userDetail));
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
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

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
}
