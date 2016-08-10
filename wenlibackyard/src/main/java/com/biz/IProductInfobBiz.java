package com.biz;

import java.util.List;

import com.bean.PageBean;
import com.po.ProductInfo;

public interface IProductInfobBiz {
	/**
	 * 保存商品信息到数据库
	 * @param productInfo
	 * @return
	 */
	public boolean save(ProductInfo productInfo);
	
	public List<ProductInfo> findAll(PageBean pageBean) throws Exception;
	
	public int findMaxPage(int rows) throws Exception;

	public ProductInfo findDetail(Integer productId) throws Exception;
	
	public boolean update(ProductInfo productInfo);
}
