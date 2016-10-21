package com.action;

public interface IProductAction {
	/**
	 * 添加商品，添加成功返回商品列表界面
	 * @return
	 */
	public String addProduct();
	
	/**
	 * 初始化商品类别信息
	 * 
	 */
	public void initProductType();
	
	/**
	 * 初始化商品信息
	 */
	public String toProductList();
	
	/**
	 * 查找商品详细信息
	 * @return
	 */
	public String toProductDetail();
}
