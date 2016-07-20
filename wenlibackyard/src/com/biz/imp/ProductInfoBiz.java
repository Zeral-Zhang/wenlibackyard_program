package com.biz.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biz.IProductInfobBiz;
import com.po.Productinfo;
import com.po.Producttype;
import com.service.dao.DaoService;

@Service("ProductInfoBiz")
public class ProductInfoBiz implements IProductInfobBiz {
	@Resource(name="DaoService")
	private DaoService daos;
	
	public DaoService getDaos() {
		return daos;
	}


	public void setDaos(DaoService daos) {
		this.daos = daos;
	}


	@Override
	public boolean add(Productinfo productinfo) {
		// 给商品添加商品类别信息
		Producttype producttype = daos.getProducttypeDAO().findById(productinfo.getProducttype().getProductTypeId());
		productinfo.setProducttype(producttype);
		// 用户信息在action层添加
		try {
			daos.getProductinfoDAO().save(productinfo);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<Productinfo> findAll(int page, int rows) {
		if(page<1)page=1;
		if(rows<1)rows=5;
		
		return daos.getProductinfoDAO().findAll(page, rows);
	}
	
	public int findMaxPage(int rows) {
		if(rows<1)rows=5;
		return daos.getProductinfoDAO().findMaxPage(rows);
	}


	@Override
	public Productinfo findDetail(Integer productId) {
		Productinfo productinfo = daos.getProductinfoDAO().findById(productId);
		if(productinfo != null) {
			return productinfo;
		}
		return null;
	}

}
