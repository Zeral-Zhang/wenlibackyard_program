package com.biz;

import java.util.List;

import com.po.ProductType;

public interface IProductTypeBiz {
	/**
	 * 初始化商品列表
	 * @return
	 * @throws Exception 
	 */
	public List<ProductType> findProuctType() throws Exception;
}
