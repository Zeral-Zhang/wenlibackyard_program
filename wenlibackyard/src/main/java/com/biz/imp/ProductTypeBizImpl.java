package com.biz.imp;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.biz.IProductTypeBiz;
import com.po.ProductType;
import com.service.dao.DaoService;

@Service("ProductTypeBizImpl")
public class ProductTypeBizImpl implements IProductTypeBiz {
	private static final Logger log = Logger.getLogger(OrderBizImpl.class);
	@Resource(name="DaoService")
	private DaoService daos;
	
	@Override
	public List<ProductType> findProuctType() throws Exception  {
		try {
			List<ProductType> productTypelst = daos.getProducttypeDAO().findAll();
			if(productTypelst != null) {
				return productTypelst;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException("initProuctType exception", e);
		}
	}

}
