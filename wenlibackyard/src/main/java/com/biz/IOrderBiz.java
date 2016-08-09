package com.biz;

import java.util.List;

import com.bean.MyCar;
import com.bean.PageBean;
import com.po.Orderdetail;
import com.po.Ordermain;
import com.po.Userinfo;

public interface IOrderBiz {
	/**
	 * 查看订单详情信息
	 * @param orderDetailId
	 * @return
	 */
	public Orderdetail findDetail(Integer orderDetailId);
	
	/**
	 * 分页查询用户的订单主表
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Ordermain> findAllMain(String userId, PageBean pageBean);

	/**
	 * 保存用户订单信息
	 * @param myCar
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public boolean saveOrder(MyCar myCar, Userinfo user) throws Exception;
}
