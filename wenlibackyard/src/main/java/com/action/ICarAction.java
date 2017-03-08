package com.action;

public interface ICarAction {
	/**
	 * 加入购物车
	 * @return
	 */
	public String add();
	
	/**
	 * 初始化购物车
	 * @return
	 */
	public String initShopCar();
	
	/**
	 * 从购物车中清除商品
	 */
	public void removeFromCar();
	
	/**
	 * 增加或减少购物车商品数量
	 */
	public String changeQuantity();
}
