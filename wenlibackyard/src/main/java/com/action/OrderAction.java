package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.bean.MyCar;
import com.bean.PageBean;
import com.bean.ShopCarItem;
import com.google.gson.Gson;
import com.po.Orderdetail;
import com.po.Ordermain;
import com.po.Userinfo;
import com.service.biz.BizService;

@Controller
@Namespace("/")
public class OrderAction implements IOrderAction {
	@Resource(name="BizService")
	private BizService bizs;
	
	private PageBean pageBean;
	
	@Action(value="add_Order", results={
			@Result(name="success", location="/WEB-INF/shopcar.jsp", type="redirect"),
			@Result(name="failed", location="/WEB-INF/error.jsp", type="redirect")}
	)
	public String add() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		try {
			MyCar myCar = (MyCar) session.getAttribute("mycar");
			Userinfo user = (Userinfo) session.getAttribute("userInfo");
			// 取出购物车信息存入订单表中，并清空购物车
			Map<Integer, ShopCarItem> items = myCar.getItems();
			Ordermain ordermain = new Ordermain();
			ordermain.setSumPrice(myCar.getSumPrice());
			ordermain.setUserinfo(user);
			ordermain.setState(1);
			// 存入订单主表信息
			bizs.getOrderBiz().saveMain(ordermain);
			
			Set<Orderdetail> orderDetailSet = new HashSet<Orderdetail>();
			for (Integer key : items.keySet()) {
				ShopCarItem item = items.get(key);
				Orderdetail orderdetail = new Orderdetail();
				orderdetail.setNum(item.getNum());
				orderdetail.setProductinfo(item.getProduct());
				orderdetail.setSumPrice(item.getPrice());
				orderdetail.setOrdermain(bizs.getOrderBiz().findNewMain());
				// 存入订单详情信息
				bizs.getOrderBiz().saveDetail(orderdetail);
				orderDetailSet.add(orderdetail);
			}
			session.removeAttribute("mycar");
			return "success";
		} catch (Exception e) {
			return "failed";
		}
	}

	public void findAllMain() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		try {
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			Userinfo user = (Userinfo) session.getAttribute("userInfo");
			String userId = user.getUserId();
			List<Ordermain> mainlst = bizs.getOrderBiz().findAllMain(userId, pageBean);
			Gson json = new Gson();
			String mainlstJson = json.toJson(mainlst);
			out.write(mainlstJson);
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
}
