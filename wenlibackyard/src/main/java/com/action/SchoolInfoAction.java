package com.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.po.SchoolInfo;
import com.service.biz.BizService;
import com.util.WebUtil;

/**
 * 院系信息action
 * 
 * @author ZeralZhang
 *
 */
@Controller
@Namespace("/")
public class SchoolInfoAction implements ISchoolInfoAction {
	@Resource(name = "BizService")
	private BizService biz;
	private String code;
	private List<SchoolInfo> schoolInfolst;

	
	@Override
	@Action(value = "loadDepartments")
	public void loadDepartments() {
		try {
			List<SchoolInfo> schoolInfolst = biz.getSchoolInfoBiz().findByCollegeId(code);
			WebUtil.sendJSONArrayResponse(schoolInfolst);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	@Action(value = "toDiscovery", results = { @Result(name = "success", location = "/WEB-INF/new_front/discovery.jsp"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp") })
	public String toDiscovery() {
		try {
			schoolInfolst = biz.getSchoolInfoBiz().findColleges();
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<SchoolInfo> getSchoolInfolst() {
		return schoolInfolst;
	}

	public void setSchoolInfolst(List<SchoolInfo> schoolInfolst) {
		this.schoolInfolst = schoolInfolst;
	}

}
