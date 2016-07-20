package com.action;

public interface IOrderAction {
	/**
	 * 添加购买商品到订单，包括主订单和详细订单
	 */
	public String add();
	
	/**
	 * 查询所有订单主表信息
	 */
	public void findAllMain();
}
