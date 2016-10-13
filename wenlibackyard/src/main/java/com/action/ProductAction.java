package com.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.bean.PageBean;
import com.po.ProductInfo;
import com.po.ProductType;
import com.po.UserInfo;
import com.service.biz.BizService;
import com.util.WebUtil;

@Controller
@Namespace("/")
public class ProductAction implements IProductAction {
	@Resource(name = "BizService")
	private BizService bizs;
	private ProductInfo productInfo;
	private Integer productId;

	private PageBean pageBean;
	
	@Action(value = "productAdd", results = {
			@Result(name = "success", location = "/WEB-INF/productAdd.jsp"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp") })
	public String productAdd() {
		return "success";
	}
	
	@Action(value = "productCategory", results = {
			@Result(name = "success", location = "/WEB-INF/productCategory.jsp"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp") })
	public String initProductCategory() {
		return "success";
	}
	
	@Action(value = "add_Product", results = {
			@Result(name = "success", location = "productList", type="redirect"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp") })
	@Override
	public String addProduct() {
		// 获取上传的服务器路径
		String rpath = ServletActionContext.getServletContext().getRealPath("/");

		// 判断是否存在上传的文件
		if (productInfo.getPic() != null && productInfo.getPicFileName() != null) {
			// 获取文件名称
			String fname = productInfo.getPicFileName();

			// 获取文件的后缀
			if (fname.lastIndexOf(".") != -1) {
				String ext = fname.substring(fname.lastIndexOf("."));

				// 改名
				String newfname = new Date().getTime() + ext;

				// 创建文件对象，制定上传文件的位置
				File destFile = new File(rpath + "/uppic/" + newfname);

				try {
					// 上传
					FileUtils.copyFile(productInfo.getPic(), destFile);
					productInfo.setImgs(newfname);// 设置文件名称到数据表
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
			}
		}
		// 添加商品发布日期
		productInfo.setPbdate(new Date());
		// 给商品添加用户信息
		UserInfo user = (UserInfo) ServletActionContext.getRequest().getSession().getAttribute("userInfo");
		productInfo.setUserInfo(user);

		boolean flag = bizs.getProductInfobiz().save(productInfo);
		if (flag) {
			productInfo = null;
			return "success";
		}

		return "failed";
	}

	@Action(value = "init_ProductType")
	@Override
	public void initProductType() {
		try {
			List<ProductType> productTypelst = bizs.getProductTypebiz().findProuctType();
			WebUtil.sendJSONArrayResponse(productTypelst, new String[] { "productInfos" });
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

	@Action(value = "productList", results = {
			@Result(name = "success", location = "/WEB-INF/productList.jsp"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp"),
			@Result(name = "login", location = "/WEB-INF/userLogin.jsp") })
	@Override
	public String initProduct() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		if (null == session.getAttribute("userInfo")) {
			request.setAttribute("originURL", "productList");
			return "login";
		}
		try {
			pageBean = pageBean == null ? new PageBean() : pageBean;

			pageBean.setMaxpage(bizs.getProductInfobiz().findMaxPage(pageBean.getRows()));
			if (pageBean.getPage() > pageBean.getMaxpage()) {
				pageBean.setPage(PageBean.DEFAULT_PAGE);
			}

			// 获取当前页的记录集合
			List<ProductInfo> lsemp = bizs.getProductInfobiz().findAll(pageBean);
			// 封装数据到PageBean
			pageBean.setPagelist(lsemp);

			return "success";
		} catch (Exception e) {
			return "failed";
		}
	}

	@Action(value = "find_ProductDetail", results = {
			@Result(name = "success", location = "/WEB-INF/productDetail.jsp"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp") })
	@Override
	public String findDetail() {

		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			ProductInfo productInfo = bizs.getProductInfobiz().findDetail(productId);
			if (productInfo != null) {
				request.setAttribute("product", productInfo);
				return "success";
			} else {
				return "failed";
			}
		} catch (Exception e) {
			return "failed";
		}
	}

	public ProductInfo getProductinfo() {
		return productInfo;
	}

	public void setProductinfo(ProductInfo productinfo) {
		this.productInfo = productinfo;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

}
