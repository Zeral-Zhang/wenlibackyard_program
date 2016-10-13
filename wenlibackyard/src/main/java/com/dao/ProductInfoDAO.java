package com.dao;

import org.springframework.stereotype.Service;

import com.po.ProductInfo;

@Service("ProductInfoDAO")
public class ProductInfoDAO extends BaseDAO<ProductInfo, Integer> {
	// property constants
	public static final String PRODUCT_NAME = "productName";
	public static final String BRAND = "brand";
	public static final String CONTEXT = "context";
	public static final String IMGS = "imgs";
	public static final String PRICE = "price";
	public static final String NUMBER = "number";
	public static final String STATE = "state";

	
	/**
	 * 获取最大页数
	 * */
	public int findMaxPage(int rows){
		int maxrow=0;
		int maxpage=0;
		//获取总记录数
		maxrow=countAll();
		if(maxrow==0){
			maxpage=1;
		}else{
			maxpage=maxrow%rows==0?maxrow/rows:maxrow/rows+1;
		}
		
		return maxpage;
		
	}
	
}