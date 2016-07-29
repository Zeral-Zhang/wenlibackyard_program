package com.biz.imp;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.biz.IProductInfobBiz;
import com.googlecode.ehcache.annotations.Cacheable;
import com.po.Productinfo;
import com.po.Producttype;
import com.service.dao.DaoService;

@Service("ProductInfoBizImpl")
public class ProductInfoBizImpl implements IProductInfobBiz {
	private static final Logger log = Logger.getLogger(ProductInfoBizImpl.class);
	public static final String CACHE_NAME = "wenlibackyard.cache";

	@Resource(name = "DaoService")
	private DaoService daos;

	@Override
	public boolean add(Productinfo productinfo) {
		log.info("add Productinfo "+ productinfo.toString() +"");
		try {
			// 给商品添加商品类别信息
			Producttype producttype = daos.getProducttypeDAO()
					.findById(productinfo.getProducttype().getProductTypeId());
			productinfo.setProducttype(producttype);
			// 用户信息在action层添加
			daos.getProductinfoDAO().save(productinfo);
			log.info("add Productinfo "+ productinfo.toString() +" success");
			return true;
		} catch (Exception e) {
			log.error("add Productinfo "+ productinfo.toString() +" failed", e);
			return false;
		}
	}

	@Cacheable(cacheName = CACHE_NAME)
	@Override
	public List<Productinfo> findAll(int page, int rows) {
		if (page < 1)
			page = 1;
		if (rows < 1)
			rows = 5;
		try {
			return daos.getProductinfoDAO().findAll(page, rows);
		} catch (Exception e) {
			log.error("findAll Productinfo exception", e);
			throw new RuntimeException(e);
		}
	}

	public int findMaxPage(int rows) {
		if (rows < 1)
			rows = 5;
		try {
			return daos.getProductinfoDAO().findMaxPage(rows);
		} catch (Exception e) {
			log.error("findMaxPage Productinfo exception", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public Productinfo findDetail(Integer productId) {
		try {
			Productinfo productinfo = daos.getProductinfoDAO().findById(productId);
			if (productinfo != null) {
				return productinfo;
			}
			return null;
		} catch (Exception e) {
			log.error("findDetail Productinfo exception", e);
			throw new RuntimeException(e);
		}
	}

}
