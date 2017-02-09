package com.biz.imp;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.biz.IProductInfobBiz;
import com.dao.BaseDAO;
import com.po.ProductInfo;
import com.po.ProductType;
import com.service.dao.DaoService;

@Service("ProductInfoBizImpl")
public class ProductInfoBizImpl extends BaseBizImpl<ProductInfo> implements IProductInfobBiz {
	private static final Logger log = Logger.getLogger(ProductInfoBizImpl.class);

	@Resource(name = "DaoService")
	private DaoService daos;


	public int findMaxPage(int rows) {
		if (rows < 1)
			rows = 5;
		try {
			return daos.getProductInfoDAO().findMaxPage(rows);
		} catch (Exception e) {
			throw new RuntimeException("findMaxPage Productinfo exception", e);
		}
	}

	@Override
	public ProductInfo findDetail(String productId) {
		try {
			ProductInfo productInfo = daos.getProductInfoDAO().findById(productId);
			if (productInfo != null) {
				return productInfo;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException("findDetail Productinfo exception", e);
		}
	}

	@Override
	public void update(ProductInfo productInfo) {
		log.info("update Productinfo "+ productInfo.toString() +"");
		try {
			// 更新商品类别信息
			ProductType productType = daos.getProductTypeDAO()
					.findById(productInfo.getProductType().getProductTypeId());
			productInfo.setProductType(productType);
			daos.getProductInfoDAO().update(productInfo);
			log.info("update Productinfo "+ productInfo.toString() +" success");
		} catch (Exception e) {
			log.error("update Productinfo "+ productInfo.toString() +" failed", e);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BaseDAO getDao() {
		return daos.getProductInfoDAO();
	}

}
