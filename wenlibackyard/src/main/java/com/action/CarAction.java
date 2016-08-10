package com.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.bean.MyCar;
import com.bean.ShopCarItem;
import com.po.ProductInfo;
import com.service.biz.BizService;
import com.util.WebUtil;

@Controller
@Namespace("/")
public class CarAction implements ICarAction {
	@Resource(name = "BizService")
	private BizService bizs;
	private Integer productId;
	private Integer num;

	@Action(value = "add_Car", results = { @Result(name = "success", location = "shopCar", type = "redirect"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp") })
	public String add() {
		HttpSession session = WebUtil.getSession();
		try {
			MyCar car = (MyCar) session.getAttribute("mycar");
			if (car == null) {
				car = new MyCar();
				WebUtil.getSession().setAttribute("mycar", car);
			}
			if (productId != null) {
				ProductInfo productInfo = bizs.getProductInfobiz().findDetail(productId);
				Map<Integer, ShopCarItem> items = car.add(productInfo, num);
				car.setItems(items);
				car.sumPrice();
				session.setAttribute("mycar", car);
			}
			return "success";
		} catch (Exception e) {
			return "failed";
		}

	}

	@Action(value = "shopCar", results = { 
			@Result(name = "success", location = "/WEB-INF/shopcar.jsp"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp") 
		})
	public String initShopCar() {
		return "success";
	}

	@Action(value = "removeFromCar")
	@Override
	public void removeFromCar() {
		HttpSession session = WebUtil.getSession();
		try {
			MyCar car = (MyCar) session.getAttribute("mycar");
			if (car == null) {
				WebUtil.sendInfoMsg("购物车为空");
				return;
			}
			if (productId != null) {
				Map<Integer, ShopCarItem> items = car.remove(productId);
				car.setItems(items);
				car.sumPrice();
				session.setAttribute("mycar", car);
			}
		} catch (Exception e) {
			WebUtil.sendErrorMsg("删除商品出错");
		}
	}

	@Action(value = "changeQuantity")
	@Override
	public void changeQuantity() {
		HttpSession session = WebUtil.getSession();
		try {
			MyCar car = (MyCar) session.getAttribute("mycar");
			if (car == null) {
				WebUtil.sendInfoMsg("购物车为空");
				return;
			}
			if (productId != null) {
				ProductInfo product = bizs.getProductInfobiz().findDetail(productId);
				Map<Integer, ShopCarItem> items = car.add(product, num);
				car.setItems(items);
				car.sumPrice();
				session.setAttribute("mycar", car);
			}
		} catch (Exception e) {
			WebUtil.sendErrorMsg("修改商品数量出错");
		}
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
