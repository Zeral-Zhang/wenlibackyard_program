package com.biz;

import java.util.List;

import com.bean.MyCar;
import com.bean.PageBean;
import com.po.OrderDetail;
import com.po.OrderMain;
import com.po.UserInfo;

public interface IOrderBiz {
	/**
	 * 查看订单详情信息
	 * @param orderDetailId
	 * @return
	 */
	public OrderDetail findDetail(Integer orderDetailId);
	
	/**
	 * 分页查询用户的订单主表
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<OrderMain> findAllMain(String userId, PageBean pageBean);

	/**
	 * 保存用户订单信息
	 * @param myCar
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public boolean saveOrder(MyCar myCar, UserInfo user) throws Exception;
}
