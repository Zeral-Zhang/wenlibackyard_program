package com.service.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.biz.ICoreBiz;
import com.biz.IUserBiz;

@Service("BizService")
public class BizService {
	@Resource(name="CoreBiz")
	private ICoreBiz corebiz;
	@Resource(name="UserBiz")
	private IUserBiz userbiz;

	public ICoreBiz getCorebiz() {
		return corebiz;
	}

	public void setCorebiz(ICoreBiz corebiz) {
		this.corebiz = corebiz;
	}

	public IUserBiz getUserbiz() {
		return userbiz;
	}

	public void setUserbiz(IUserBiz userbiz) {
		this.userbiz = userbiz;
	}
	
	
}
