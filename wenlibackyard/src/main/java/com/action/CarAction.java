package com.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.bean.MyCar;
import com.bean.ShopCarItem;
import com.po.Productinfo;
import com.service.biz.BizService;

@Controller
@Namespace("/")
public class CarAction implements ICarAction {
	@Resource(name="BizService")
	private BizService bizs;
	private Integer productId;
	private Integer num;
	
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

	public BizService getBizs() {
		return bizs;
	}

	public void setBizs(BizService bizs) {
		this.bizs = bizs;
	}

	@Action(value="add_Car", results={
			@Result(name="success", location="/WEB-INF/shopcar.jsp", type="redirect"),
			@Result(name="failed", location="/WEB-INF/error.jsp", type="redirect")
	})
	public String add() {
		HttpServletRequest req = ServletActionContext.getRequest();
		
		try {
			MyCar car = (MyCar)req.getSession().getAttribute("mycar");
			if(car == null) {
				car = new MyCar();
				req.getSession().setAttribute("mycar", car);
			}
			if(productId != null) {
				Productinfo productinfo = bizs.getProductInfobiz().findDetail(productId);
				Map<Integer,ShopCarItem> items = car.add(productinfo, num);
				car.setItems(items);
				car.sumPrice();
				req.getSession().setAttribute("mycar", car);
			}
			return "success";
		} catch (Exception e) {
			return "failed";
		}
		
	}
}
