package com.biz;

import com.po.ProductInfo;

public interface IProductInfobBiz extends IBaseBiz<ProductInfo> {
	
	public int findMaxPage(int rows);

	public ProductInfo findDetail(String productId);
	
	public void update(ProductInfo productInfo);
}
