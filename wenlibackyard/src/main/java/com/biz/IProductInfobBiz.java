package com.biz;

import java.util.List;

import com.bean.PageBean;
import com.po.Productinfo;

public interface IProductInfobBiz {
	/**
	 * 保存商品信息到数据库
	 * @param productinfo
	 * @return
	 */
	public boolean save(Productinfo productinfo);
	
	public List<Productinfo> findAll(PageBean pageBean) throws Exception;
	
	public int findMaxPage(int rows) throws Exception;

	public Productinfo findDetail(Integer productId) throws Exception;
	
	public boolean update(Productinfo productinfo);
}
