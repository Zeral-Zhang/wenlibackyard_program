package com.biz;

import java.util.List;

import com.po.ProductType;

public interface IProductTypeBiz {
	/**
	 * 初始化商品列表
	 * @return
	 */
	public List<ProductType> findProuctType();
	
	/**
	 * 根据商品类型Id查询
	 * @param productTypeId
	 * @return
	 */
	ProductType findById(String productTypeId);
}
