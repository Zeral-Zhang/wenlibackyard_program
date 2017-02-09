package com.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.bean.PageBean;
import com.po.ProductInfo;
import com.po.ProductType;
import com.service.biz.BizService;
import com.util.WebUtil;

@Controller
@Namespace("/")
public class ProductAction extends BaseAction implements IProductAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "BizService")
	private BizService bizs;
	private ProductInfo productInfo;
	private String productId;
	private List<String> fileSrcs;

	private PageBean pageBean;

	@Action(value = "toProductAdd", results = {
			@Result(name = "success", location = "/WEB-INF/new_front/productAdd.jsp"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp") })
	public String toProductAdd() {
		return "success";
	}

	@Action(value = "productCategory", results = { @Result(name = "success", location = "/WEB-INF/productCategory.jsp"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp") })
	public String initProductCategory() {
		return "success";
	}

	@Action(value = "addProduct", results = { @Result(name = "success", location = "toProductList", type = "redirectAction"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp") })
	@Override
	public String addProduct() {
		try {
			// 添加商品发布日期
			productInfo.setPbDate(new Date());
			// 给商品添加用户信息
			productInfo.setUserInfoId(getLoginUser().getUserId());
			productInfo.setFileSrcs(fileSrcs);
			bizs.getProductInfobiz().save(productInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}

	@Action(value = "init_ProductType")
	@Override
	public void initProductType() {
		try {
			List<ProductType> productTypelst = bizs.getProductTypebiz().findProuctType();
			WebUtil.sendJSONArrayResponse(productTypelst, new String[] { "productInfos" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Action(value = "toProductList", results = {
			@Result(name = "success", location = "/WEB-INF/new_front/productList.jsp"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp"),
			@Result(name = "login", location = "/WEB-INF/userLogin.jsp")})
	@Override
	public String toProductList() {
		/*if (null == getLoginUser()) {
			getRequest().setAttribute("originURL", "toProductList");
			return "login";
		}*/
		try {
			pageBean = pageBean == null ? new PageBean() : pageBean;

			// 获取当前页的记录集合
			List<ProductInfo> lsemp = bizs.getProductInfobiz().findAll(pageBean);
			// 封装数据到PageBean
			pageBean.setPagelist(lsemp);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
	}

	@Action(value = "toProductDetail", results = {
			@Result(name = "success", location = "/WEB-INF/new_front/productDetail.jsp"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp") })
	@Override
	public String toProductDetail() {
		try {
			if (null != productId) {
				productInfo = bizs.getProductInfobiz().findDetail(productId);
				productInfo.setUserInfo(bizs.getUserbiz().findByOpenId(productInfo.getUserInfoId()));
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
	}

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public List<String> getFileSrcs() {
		return fileSrcs;
	}

	public void setFileSrcs(List<String> fileSrcs) {
		this.fileSrcs = fileSrcs;
	}
	
}
