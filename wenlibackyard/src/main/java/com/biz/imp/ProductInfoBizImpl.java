package com.biz.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.biz.IProductInfobBiz;
import com.googlecode.ehcache.annotations.Cacheable;
import com.po.Productinfo;
import com.po.Producttype;
import com.service.dao.DaoService;

@Service("ProductInfoBizImpl")
public class ProductInfoBizImpl implements IProductInfobBiz {
	public static final String CACHE_NAME = "wenlibackyard.cache";
	
	@Resource(name="DaoService")
	private DaoService daos;

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

	@Cacheable(cacheName = CACHE_NAME)
	@Override
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
