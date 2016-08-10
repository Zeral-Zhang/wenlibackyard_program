package com.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.stereotype.Controller;

import com.po.Regions;
import com.service.biz.BizService;
import com.util.WebUtil;

@Controller
@Namespace("/")
public class ReginAction implements IRegionAction {
	@Resource(name = "BizService")
	private BizService biz;

	public BizService getBiz() {
		return biz;
	}

	public void setBiz(BizService biz) {
		this.biz = biz;
	}

	@Action(value = "initProvince_Region")
	@Override
	public void initProvince() {
		try {
			List<Regions> provincelst = biz.getRegionbiz().findProvince();
			WebUtil.sendJSONArrayResponse(provincelst);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

	@Action(value = "loadCitys_Region")
	@Override
	public void loadCitys() {
		try {
			String fcode = ServletActionContext.getRequest().getParameter("code");
			if (!"".equals(fcode)) {
				Integer fcodeInt = 0;
				try {
					fcodeInt = Integer.parseInt(fcode);
				} catch (NumberFormatException e) {
					fcodeInt = 1;
				}
				List<Regions> citylst = biz.getRegionbiz().findCitys(fcodeInt);
				WebUtil.sendJSONArrayResponse(citylst);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

}
