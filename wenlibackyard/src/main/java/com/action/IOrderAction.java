package com.action;

public interface IOrderAction {
	/**
	 * 添加购买商品到订单，包括主订单和详细订单
	 */
	public String addOrder();
	
	/**
	 * 查询所有订单主表信息,并跳转到购物清单页面
	 */
	public String findAllMain();
}
