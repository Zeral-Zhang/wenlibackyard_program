package com.action;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.stereotype.Controller;

import com.service.biz.BizService;

@Controller
@Namespace("/")
public class CoreAction implements ICoreAction {
	@Resource(name = "BizService")
	private BizService bizs;

	public BizService getBizs() {
		return bizs;
	}

	public void setBizs(BizService bizs) {
		this.bizs = bizs;
	}

	@Action(value = "handleCore")
	public String handle() {
		String method = ServletActionContext.getRequest().getMethod();
		try {
			if ("POST".equalsIgnoreCase(method)) {
				bizs.getCorebiz().handleRequest();
			} else {
				bizs.getCorebiz().checkSignature();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
