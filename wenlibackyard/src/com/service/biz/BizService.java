package com.service.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.biz.IOrderBiz;
import com.biz.IProductInfobBiz;
import com.biz.IProductTypeBiz;
import com.biz.IRegionBiz;
import com.biz.IUserBiz;

@Service("BizService")
public class BizService {
	@Resource(name="UserBiz")
	private IUserBiz userbiz;
	@Resource(name="RegionBiz")
	private IRegionBiz regionbiz;
	@Resource(name="ProductTypeBiz")
	private IProductTypeBiz productTypebiz;
	@Resource(name="ProductInfoBiz")
	private IProductInfobBiz productInfobiz;
	@Resource(name="OrderBiz")
	private IOrderBiz orderBiz;

	public IUserBiz getUserbiz() {
		return userbiz;
	}

	public void setUserbiz(IUserBiz userbiz) {
		this.userbiz = userbiz;
	}

	public IRegionBiz getRegionbiz() {
		return regionbiz;
	}

	public void setRegionbiz(IRegionBiz regionbiz) {
		this.regionbiz = regionbiz;
	}

	public IProductTypeBiz getProductTypebiz() {
		return productTypebiz;
	}

	public void setProductTypebiz(IProductTypeBiz productTypebiz) {
		this.productTypebiz = productTypebiz;
	}

	public IProductInfobBiz getProductInfobiz() {
		return productInfobiz;
	}

	public void setProductInfobiz(IProductInfobBiz productInfobiz) {
		this.productInfobiz = productInfobiz;
	}

	public IOrderBiz getOrderBiz() {
		return orderBiz;
	}

	public void setOrderBiz(IOrderBiz orderBiz) {
		this.orderBiz = orderBiz;
	}
	
}
