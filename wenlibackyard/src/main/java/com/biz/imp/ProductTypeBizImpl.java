package com.biz.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.biz.IProductTypeBiz;
import com.po.Producttype;
import com.service.dao.DaoService;

@Service("ProductTypeBizImpl")
public class ProductTypeBizImpl implements IProductTypeBiz {
	@Resource(name="DaoService")
	private DaoService daos;
	
	public DaoService getDaos() {
		return daos;
	}

	public void setDaos(DaoService daos) {
		this.daos = daos;
	}

	@Override
	public List<Producttype> initProuctType() {
		List<Producttype> productTypelst = daos.getProducttypeDAO().findAll();
		if(productTypelst != null) {
			return productTypelst;
		}
		return null;
	}

}
