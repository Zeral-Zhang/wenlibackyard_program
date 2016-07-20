package com.biz;

import java.util.List;

import com.po.Orderdetail;
import com.po.Ordermain;

public interface IOrderBiz {
	/**
	 * 保存订单详情信息
	 * @param orderdetail
	 * @return
	 */
	public boolean saveDetail(Orderdetail orderdetail);
	
	/**
	 * 保存订单主表信息
	 * @param ordermain
	 * @return
	 */
	public boolean saveMain(Ordermain ordermain);
	
	/**
	 * 查看订单详情信息
	 * @param orderDetailId
	 * @return
	 */
	public Orderdetail findDetail(Integer orderDetailId);
	
	/**
	 * 通过用户查找订单主表信息
	 * @param userId
	 * @return
	 */
	public Ordermain findNewMain();

	/**
	 * 分页查询用户的订单主表
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Ordermain> findAllMain(String userId, int page, int rows);
}
