package com.biz.imp;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bean.PageBean;
import com.biz.IProductInfobBiz;
import com.googlecode.ehcache.annotations.Cacheable;
import com.po.ProductInfo;
import com.po.ProductType;
import com.service.dao.DaoService;

@Service("ProductInfoBizImpl")
public class ProductInfoBizImpl implements IProductInfobBiz {
	private static final Logger log = Logger.getLogger(ProductInfoBizImpl.class);
	public static final String CACHE_NAME = "wenlibackyard.cache";

	@Resource(name = "DaoService")
	private DaoService daos;

	@Override
	public boolean save(ProductInfo productInfo) {
		log.info("save Productinfo "+ productInfo.toString() +"");
		try {
			// 给商品添加商品类别信息
			ProductType productType = daos.getProducttypeDAO()
					.findById(productInfo.getProducttype().getProductTypeId());
			productInfo.setProducttype(productType);
			// 用户信息在action层添加
			daos.getProductinfoDAO().save(productInfo);
			log.info("add Productinfo "+ productInfo.toString() +" success");
			return true;
		} catch (Exception e) {
			log.error("add Productinfo "+ productInfo.toString() +" failed", e);
			return false;
		}
	}

	@Cacheable(cacheName = CACHE_NAME)
	@Override
	public List<ProductInfo> findAll(PageBean pageBean) throws Exception {
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
	public ProductInfo findDetail(Integer productId) throws Exception  {
		try {
			ProductInfo productInfo = daos.getProductinfoDAO().findById(productId);
			if (productInfo != null) {
				return productInfo;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException("findDetail Productinfo exception", e);
		}
	}

	@Override
	public boolean update(ProductInfo productInfo) {
		log.info("update Productinfo "+ productInfo.toString() +"");
		try {
			// 更新商品类别信息
			ProductType productType = daos.getProducttypeDAO()
					.findById(productInfo.getProducttype().getProductTypeId());
			productInfo.setProducttype(productType);
			daos.getProductinfoDAO().update(productInfo);
			log.info("update Productinfo "+ productInfo.toString() +" success");
			return true;
		} catch (Exception e) {
			log.error("update Productinfo "+ productInfo.toString() +" failed", e);
			return false;
		}
	}

}
