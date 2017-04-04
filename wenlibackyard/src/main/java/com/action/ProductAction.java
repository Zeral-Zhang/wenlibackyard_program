package com.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Controller;

import com.bean.PageBean;
import com.constant.WenlibackyardConstant;
import com.po.ProductInfo;
import com.po.ProductType;
import com.service.biz.BizService;
import com.util.HttpsUtil;
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
	private String search;
	private String productTypeId;
	private ProductType productType;
	private String schoolInfoId;

	private PageBean pageBean;

	@Action(value = "toProductAdd", results = {
			@Result(name = "success", location = "/WEB-INF/new_front/productAdd.jsp"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp") })
	public String toProductAdd() {
		try {
			if (null == super.getLoginUser()) {
				getResponse().sendRedirect(HttpsUtil.AuthLogin(WenlibackyardConstant.VALIDATE_URL, "toUserInfo"));
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "failed";
		}
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
			@Result(name = "failed", location = "/WEB-INF/error.jsp")
			})
	@Override
	public String toProductList() {
		/*if (null == getLoginUser()) {
			getRequest().setAttribute("originURL", "toProductList");
			return "login";
		}*/
		try {
			pageBean = pageBean == null ? new PageBean() : pageBean;

			List<ProductInfo> lsemp = null;
			if(StringUtils.isNotBlank(search)) {
				lsemp = bizs.getProductInfobiz().findByNameLike(pageBean, search);
			} else {
				// 获取当前页的记录集合
				lsemp = bizs.getProductInfobiz().findAll(pageBean, Order.desc("pbDate"));
			}
			// 封装数据到PageBean
			pageBean.setPagelist(lsemp);
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
	}
	
	@Action(value = "toProductCateList", results = {
			@Result(name = "success", location = "/WEB-INF/new_front/productCateList.jsp"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp")
			})
	@Override
	public String toProductCateList() {
		try {
			pageBean = pageBean == null ? new PageBean() : pageBean;
			
			List<ProductInfo> lsemp = null;
			if(StringUtils.isNotBlank(search)) {
				lsemp = bizs.getProductInfobiz().findByTypeAndNameLike(pageBean, productTypeId, search);
			} else {
				// 获取当前页的记录集合
				lsemp = bizs.getProductInfobiz().findByType(pageBean, productTypeId);
			}
			// 封装数据到PageBean
			pageBean.setPagelist(lsemp);
			productType = bizs.getProductTypebiz().findById(productTypeId);
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return "success";
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
	
	@Action(value = "toSchoolInfoProduct", results = {
			@Result(name = "success", location = "/WEB-INF/new_front/collegeProductList.jsp"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp") })
	@Override
	public String toSchoolInfoProduct() {
		try {
			pageBean = pageBean == null ? new PageBean() : pageBean;

			List<ProductInfo> lsemp = null;
			if(null != search) {
				/*lsemp = bizs.getProductInfobiz().findByUserSchoolInfoIdAndNameLike(pageBean, search);*/
			} else {
				// 获取当前页的记录集合
				lsemp = bizs.getProductInfobiz().findByUserSchoolInfoId(pageBean, schoolInfoId);
			}
			// 封装数据到PageBean
			pageBean.setPagelist(lsemp);
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

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public String getSchoolInfoId() {
		return schoolInfoId;
	}

	public void setSchoolInfoId(String schoolInfoId) {
		this.schoolInfoId = schoolInfoId;
	}
	
}
