package com.biz.imp;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bean.PageBean;
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
	public boolean save(Productinfo productinfo) {
		log.info("save Productinfo "+ productinfo.toString() +"");
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
	public List<Productinfo> findAll(PageBean pageBean) throws Exception {
		try {
			return daos.getProductinfoDAO().findAll(pageBean);
		} catch (Exception e) {
			throw new RuntimeException("findAll Productinfo exception", e);
		}
	}

	public int findMaxPage(int rows) throws Exception  {
		if (rows < 1)
			rows = 5;
		try {
			return daos.getProductinfoDAO().findMaxPage(rows);
		} catch (Exception e) {
			throw new RuntimeException("findMaxPage Productinfo exception", e);
		}
	}

	@Override
	public Productinfo findDetail(Integer productId) throws Exception  {
		try {
			Productinfo productinfo = daos.getProductinfoDAO().findById(productId);
			if (productinfo != null) {
				return productinfo;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException("findDetail Productinfo exception", e);
		}
	}

	@Override
	public boolean update(Productinfo productinfo) {
		log.info("update Productinfo "+ productinfo.toString() +"");
		try {
			// 更新商品类别信息
			Producttype producttype = daos.getProducttypeDAO()
					.findById(productinfo.getProducttype().getProductTypeId());
			productinfo.setProducttype(producttype);
			daos.getProductinfoDAO().update(productinfo);
			log.info("update Productinfo "+ productinfo.toString() +" success");
			return true;
		} catch (Exception e) {
			log.error("update Productinfo "+ productinfo.toString() +" failed", e);
			return false;
		}
	}

}
