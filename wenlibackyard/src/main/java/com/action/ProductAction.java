package com.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;

import com.bean.PageBean;
import com.google.gson.Gson;
import com.po.Productinfo;
import com.po.Producttype;
import com.po.Userinfo;
import com.service.biz.BizService;

@Controller
@Namespace("/")
public class ProductAction implements IProductAction {
	@Resource(name = "BizService")
	private BizService bizs;
	private Productinfo productinfo;
	private Integer productId;

	private PageBean pageBean;

	public BizService getBizs() {
		return bizs;
	}

	public void setBizs(BizService bizs) {
		this.bizs = bizs;
	}

	public Productinfo getProductinfo() {
		return productinfo;
	}

	public void setProductinfo(Productinfo productinfo) {
		this.productinfo = productinfo;
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

	@Action(value = "add_Product", results = {
			@Result(name = "success", location = "/WEB-INF/productList.jsp", type = "redirect"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp", type = "redirect") })
	public String add() {
		// 获取上传的服务器路径
		String rpath = ServletActionContext.getServletContext()
				.getRealPath("/");

		// 判断是否存在上传的文件
		if (productinfo.getPic() != null
				&& productinfo.getPicFileName() != null) {
			// 获取文件名称
			String fname = productinfo.getPicFileName();

			// 获取文件的后缀
			if (fname.lastIndexOf(".") != -1) {
				String ext = fname.substring(fname.lastIndexOf("."));

				// 改名
				String newfname = new Date().getTime() + ext;

				// 创建文件对象，制定上传文件的位置
				File destFile = new File(rpath + "/uppic/" + newfname);

				try {
					// 上传
					FileUtils.copyFile(productinfo.getPic(), destFile);
					productinfo.setImgs(newfname);// 设置文件名称到数据表
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
			}
		}
		// 添加商品发布日期
		productinfo.setPbdate(new Date());
		// 给商品添加用户信息
		Userinfo user = (Userinfo) ServletActionContext.getRequest()
				.getSession().getAttribute("userInfo");
		productinfo.setUserinfo(user);

		boolean flag = bizs.getProductInfobiz().save(productinfo);
		if (flag) {
			productinfo = null;
			return "success";
		}

		return "failed";
	}

	@Action(value = "init_ProductType")
	public void initProductType() {
		try {
			PrintWriter out = null;
			try {
				out = ServletActionContext.getResponse().getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
			List<Producttype> productTypelst = bizs.getProductTypebiz()
					.findProuctType();
			Gson gson = new Gson();
			// 防止陷入死循环，请求productType时懒加载productInfo，但是productInfo又请求productType
			for (Producttype producttype : productTypelst) {
				producttype.setProductinfos(null);
			}
			String productGson = gson.toJson(productTypelst);
			out.write(productGson);
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

	@Action(value = "productList", results = {
			@Result(name = "success", location = "/WEB-INF/productList.jsp", type = "dispatcher"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp"),
			@Result(name = "login", location = "/WEB-INF/userLogin.jsp")
	})
	public String initProduct() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		if(null == session.getAttribute("userInfo")) {
			request.setAttribute("originURL", "productList");
			return "login";
		}
		try {
			pageBean = pageBean == null ? new PageBean() : pageBean;

			pageBean.setMaxpage(bizs.getProductInfobiz().findMaxPage(
					pageBean.getRows()));
			if (pageBean.getPage() > pageBean.getMaxpage()) {
				pageBean.setPage(PageBean.DEFAULT_PAGE);
			}

			// 获取当前页的记录集合
			List<Productinfo> lsemp = bizs.getProductInfobiz()
					.findAll(pageBean);
			// 封装数据到PageBean
			pageBean.setPagelist(lsemp);

			return "success";
		} catch (Exception e) {
			return "failed";
		}
	}

	@Action(value = "find_ProductDetail", results = {
			@Result(name = "success", location = "productDetail.jsp", type = "dispatcher"),
			@Result(name = "failed", location = "/WEB-INF/error.jsp", type = "redirect") })
	public String findDetail() {

		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			Productinfo productinfo = bizs.getProductInfobiz().findDetail(
					productId);
			if (productinfo != null) {
				request.setAttribute("product", productinfo);
				return "success";
			} else {
				return "failed";
			}
		} catch (Exception e) {
			return "failed";
		}
	}

}
