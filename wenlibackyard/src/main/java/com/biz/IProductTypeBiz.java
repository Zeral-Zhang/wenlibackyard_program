package com.biz;

import java.util.List;

import com.po.Producttype;

public interface IProductTypeBiz {
	/**
	 * 初始化商品列表
	 * @return
	 * @throws Exception 
	 */
	public List<Producttype> findProuctType() throws Exception;
}
