package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.po.Regions;
import com.service.biz.BizService;

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
	public void initProvince() {
		try {
			PrintWriter out = null;
			out = ServletActionContext.getResponse().getWriter();
			List<Regions> provincelst = biz.getRegionbiz().findProvince();
			Gson gson = new Gson();
			String provinceGson = gson.toJson(provincelst);
			out.write(provinceGson);
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

	@Action(value = "loadCitys_Region")
	public void loadCitys() {
		try {
			PrintWriter out = null;
				out = ServletActionContext.getResponse().getWriter();
			String fcode = ServletActionContext.getRequest().getParameter("code");
			if (!"".equals(fcode)) {
				Integer fcodeInt = 0;
				try {
					fcodeInt = Integer.parseInt(fcode);
				} catch (NumberFormatException e) {
					fcodeInt = 1;
				}
				List<Regions> citylst = biz.getRegionbiz().findCitys(fcodeInt);
				Gson gson = new Gson();
				String cityGson = gson.toJson(citylst);
				out.write(cityGson);
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

}
