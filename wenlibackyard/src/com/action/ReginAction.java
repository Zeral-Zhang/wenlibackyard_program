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
	@Resource(name="BizService")
	private BizService biz;

	public BizService getBiz() {
		return biz;
	}

	public void setBiz(BizService biz) {
		this.biz = biz;
	}

	@Action(value="initProvince_Region")
	@Override
	public void initProvince() {
		PrintWriter out = null;
		try {
			out = ServletActionContext.getResponse().getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Regions> provincelst = biz.getRegionbiz().findProvince();
		Gson gson = new Gson();
		String provinceGson = gson.toJson(provincelst);
		out.write(provinceGson);
		out.flush();
		out.close();
	}

	@Action(value="loadCitys_Region")
	@Override
	public void loadCitys() {
		PrintWriter out = null;
		try {
			out = ServletActionContext.getResponse().getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fcode = ServletActionContext.getRequest().getParameter("code");
		if(!"".equals(fcode)) {
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
	}

}
