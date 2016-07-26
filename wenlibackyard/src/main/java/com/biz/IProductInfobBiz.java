package com.biz;

import java.util.List;

import com.po.Productinfo;

public interface IProductInfobBiz {
	/**
	 * 保存商品信息到数据库
	 * @param productinfo
	 * @return
	 */
	boolean add(Productinfo productinfo);
	
	public List<Productinfo> findAll(int page, int rows);
	
	public int findMaxPage(int rows);

	public Productinfo findDetail(Integer productId);
	
}
