package com.action;

import java.util.List;
import java.util.Map;

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
import com.po.OrderMain;
import com.po.ProductInfo;
import com.po.UserInfo;
import com.service.biz.BizService;
import com.util.WebUtil;

@Controller
@Namespace("/")
public class OrderAction implements IOrderAction {
	@Resource(name = "BizService")
	private BizService bizs;

	private PageBean pageBean;
	private List<OrderMain> mainlst;

	@Action(value = "add_Order", results = { 
			@Result(name = "success", location = "shopCar", type = "redirect"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp") 
		})
	@Override
	public String addOrder() {
		HttpSession session = WebUtil.getSession();
		try {
			MyCar myCar = (MyCar) session.getAttribute("mycar");
			UserInfo user = (UserInfo) session.getAttribute("userInfo");
			// 购买成功，商品数量减少
			if (bizs.getOrderBiz().saveOrder(myCar, user)) {
				session.removeAttribute("mycar");
				Map<Integer, ShopCarItem> items = myCar.getItems();
				for (Integer productInfoId : items.keySet()) {
					ProductInfo product = bizs.getProductInfobiz().findDetail(productInfoId);
					product.setNumber(product.getNumber() - items.get(productInfoId).getNum());
					bizs.getProductInfobiz().update(product);
				}
				return "success";
			} else {
				return "failed";
			}
		} catch (Exception e) {
			return "failed";
		}
	}

	@Action(value = "order_list", results = { 
			@Result(name = "success", location = "/WEB-INF/orderList.jsp"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp") 
		})
	@Override
	public String findAllMain() {
		HttpSession session = WebUtil.getSession();
		try {
			UserInfo user = (UserInfo) session.getAttribute("userInfo");
			String userId = user.getUserId();
			pageBean = new PageBean();
			pageBean.setPage(1);
			pageBean.setRows(8);
			mainlst = bizs.getOrderBiz().findAllMain(userId, pageBean);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public List<OrderMain> getMainlst() {
		return mainlst;
	}

	public void setMainlst(List<OrderMain> mainlst) {
		this.mainlst = mainlst;
	}
	
}
