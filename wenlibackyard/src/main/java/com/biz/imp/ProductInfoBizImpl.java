package com.biz.imp;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bean.PageBean;
import com.biz.IProductInfobBiz;
import com.dao.BaseDAO;
import com.po.OrderMain;
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

	@Override
	public List<ProductInfo> findByUserId(PageBean pageBean, String userId) {
		String hql = "from ProductInfo info where info.userInfoId = ? order by info.pbDate desc";
		return daos.getProductInfoDAO().findByHQL(hql, pageBean, userId);
	}

	@Override
	public List<ProductInfo> findByNameLike(PageBean pageBean, String name) {
		String hql = "from ProductInfo info where info.productName like ?";
		return daos.getProductInfoDAO().findByHQL(hql, pageBean, "%" + name + "%");
	}
	
	@Override
	public List<ProductInfo> findByTypeAndNameLike(PageBean pageBean, String productTypeId, String name) {
		String hql = "from ProductInfo info where info.productType.productTypeId = ? and info.productName like ?";
		return daos.getProductInfoDAO().findByHQL(hql, pageBean, productTypeId, "%" + name + "%");
	}
	
	@Override
	public List<ProductInfo> findByType(PageBean pageBean, String productTypeId) {
		String hql = "from ProductInfo info where info.productType.productTypeId = ?";
		return daos.getProductInfoDAO().findByHQL(hql, pageBean, productTypeId);
	}

	@Override
	public List<OrderMain> findOrderMain(PageBean pageBean, String userId) {
		String hql = "from OrderMain main where main.userInfoId = ?";
		return daos.getOrderMainDAO().findByHQL(hql, pageBean, userId);
	}

	@Override
	public List<ProductInfo> findByUserSchoolInfoId(PageBean pageBean, String schoolInfoId) {
		return daos.getProductInfoDAO().findByUserSchoolInfoId(pageBean, schoolInfoId);
	}
}
