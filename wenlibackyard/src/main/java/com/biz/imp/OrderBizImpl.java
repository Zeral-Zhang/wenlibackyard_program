package com.biz.imp;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bean.MyCar;
import com.bean.PageBean;
import com.bean.ShopCarItem;
import com.biz.IOrderBiz;
import com.po.OrderDetail;
import com.po.OrderMain;
import com.po.UserInfo;
import com.service.dao.DaoService;

@Service("OrderBizImpl")
public class OrderBizImpl implements IOrderBiz {
	private static final Logger log = Logger.getLogger(OrderBizImpl.class);
	@Resource(name = "DaoService")
	private DaoService daos;

	@Override
	public OrderDetail findDetail(Integer orderDetailId) {
		OrderDetail orderDetail;
		try {
			orderDetail = daos.getOrderdetailDAO().findById(orderDetailId);
			if (orderDetail != null) {
				return orderDetail;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException("findDetail exception " + orderDetailId.toString() + "", e);
		}

	}

	public OrderMain findNewMain(String userId) {
		try {
			List<Integer> objlst = daos.getOrdermainDAO()
					.findBySQL("SELECT MAX(orderMainId) FROM orderMain WHERE userId = '" + userId + "' ;");
			int id = -1;
			if (objlst != null) id = objlst.get(0);
			OrderMain orderMain = daos.getOrdermainDAO().findById(id);
			return orderMain;
		} catch (Exception e) {
			log.error("findNewMain exception", e);
			throw new RuntimeException("findNewMain exception", e);
		}
	}

	@Override
	public List<OrderMain> findAllMain(String userId, PageBean pageBean) {
		try {
			List<OrderMain> mainlst = daos.getOrdermainDAO().findAllByUser(userId, pageBean);
			return mainlst;
		} catch (Exception e) {
			throw new RuntimeException("findAllMain exception", e);
		}
	}

	@Override
	public boolean saveOrder(MyCar myCar, UserInfo user) {
		log.info("保存" + user.toString() + "订单");
		try {
			// 取出购物车信息存入订单表中，并清空购物车, 同时减少商品数量
			Map<Integer, ShopCarItem> items = myCar.getItems();
			OrderMain orderMain = new OrderMain();
			orderMain.setSumPrice(myCar.getSumPrice());
			orderMain.setUserinfo(user);
			orderMain.setState(OrderMain.UN_HANDLE);
			orderMain.setBuyDate(new Date());
			// 存入订单主表信息
			daos.getOrdermainDAO().save(orderMain);

			Set<OrderDetail> orderDetailSet = new HashSet<OrderDetail>();
			for (Integer key : items.keySet()) {
				ShopCarItem item = items.get(key);
				OrderDetail orderdetail = new OrderDetail();
				orderdetail.setNum(item.getNum());
				orderdetail.setProductinfo(item.getProduct());
				orderdetail.setSumPrice(item.getPrice());
				orderdetail.setOrdermain(findNewMain(user.getUserId()));
				// 存入订单详情信息
				daos.getOrderdetailDAO().save(orderdetail);
				orderDetailSet.add(orderdetail);
			}
			log.info("保存" + user.toString() + "订单成功");
			return true;
		} catch (Exception e) {
			log.error("保存" + user.toString() + "订单失败", e);
			return false;
		}
	}

}
