package com.action;

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
import com.po.Orderdetail;
import com.po.Ordermain;
import com.po.Productinfo;
import com.po.Userinfo;
import com.service.biz.BizService;
import com.util.WebUtil;

@Controller
@Namespace("/")
public class OrderAction implements IOrderAction {
	@Resource(name = "BizService")
	private BizService bizs;

	private PageBean pageBean;

	@Action(value = "add_Order", results = { 
			@Result(name = "success", location = "shopCar", type = "redirect"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp") 
		})
	public String add() {
		HttpSession session = WebUtil.getSession();
		try {
			MyCar myCar = (MyCar) session.getAttribute("mycar");
			Userinfo user = (Userinfo) session.getAttribute("userInfo");
			// 购买成功，商品数量减少
			if (bizs.getOrderBiz().saveOrder(myCar, user)) {
				session.removeAttribute("mycar");
				Map<Integer, ShopCarItem> items = myCar.getItems();
				for (Integer productInfoId : items.keySet()) {
					Productinfo product = bizs.getProductInfobiz().findDetail(productInfoId);
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

	public void findAllMain() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		try {
			Userinfo user = (Userinfo) session.getAttribute("userInfo");
			String userId = user.getUserId();
			List<Ordermain> mainlst = bizs.getOrderBiz().findAllMain(userId, pageBean);
			WebUtil.sendJSONArrayResponse(mainlst);
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
