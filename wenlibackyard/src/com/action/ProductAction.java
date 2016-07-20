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
	@Resource(name="BizService")
	private BizService bizs;
	private Productinfo productinfo;
	private Integer productId;
	
	private int page;
	private int rows;
	
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

	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public int getRows() {
		return rows;
	}


	public void setRows(int rows) {
		this.rows = rows;
	}


	public Integer getProductId() {
		return productId;
	}


	public void setProductId(Integer productId) {
		this.productId = productId;
	}


	@Action(value="add_Product", results = {
			@Result(name="success", location="/productList.jsp", type="redirect"),
			@Result(name="failed", location="/error.jsp", type="redirect")
		})
	@Override
	public String add() {
		// 获取上传的服务器路径
				String rpath = ServletActionContext.getServletContext().getRealPath("/");

				// 判断是否存在上传的文件
				if (productinfo.getPic() != null && productinfo.getPicFileName() != null) {
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
							e.printStackTrace();
						}
					}
				}
				// 添加商品发布日期
				productinfo.setPbdate(new Date());
				// 给商品添加用户信息
				Userinfo user = (Userinfo) ServletActionContext.getRequest().getSession().getAttribute("userInfo");
				productinfo.setUserinfo(user);
				
				boolean flag = bizs.getProductInfobiz().add(productinfo);
				if (flag) {
					productinfo = null;
					return "success";
				}

		return "failed";
	}


	@Action(value="init_ProductType")
	@Override
	public void initProductType() {
		PrintWriter out = null;
		try {
			out = ServletActionContext.getResponse().getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Producttype> productTypelst = bizs.getProductTypebiz().initProuctType();
		Gson gson = new Gson();
		// 防止陷入死循环，请求productType时懒加载productInfo，但是productInfo又请求productType
		for (Producttype producttype : productTypelst) {
			producttype.setProductinfos(null);
		}
		String productGson = gson.toJson(productTypelst);
		out.write(productGson);
		out.flush();
		out.close();
	}
	
	@Action(value="init_Product",results={
			@Result(name="success",location="productList.jsp",type="redirect")
	})
	@Override
	public String initProduct() {
		HttpSession session=ServletActionContext.getRequest().getSession();

		PageBean pb=(PageBean) session.getAttribute("pb");
		pb=pb==null?new PageBean():pb;
		System.out.println(pb.toString());
		page=page<1?pb.getPage():page;
		rows=rows<1?pb.getRows():rows;
		System.out.println(page);
		//获取最大页数
		int maxpage=bizs.getProductInfobiz().findMaxPage(rows);
		
		if(page>maxpage)page=0;
		
		//获取当前页的记录集合
		List<Productinfo> lsemp=bizs.getProductInfobiz().findAll(page, rows);
		//封装数据到PageBean
		pb.setMaxpage(maxpage);
		pb.setPage(page);
		pb.setPagelist(lsemp);
		pb.setRows(rows);
		
		session.setAttribute("pb", pb);
		return "success";
	}

	@Action(value="find_ProductDetail",results={
			@Result(name="success",location="/productDetail.jsp",type="dispatcher"),
			@Result(name="failed", location="error.jsp", type="redirect")
	})
	@Override
	public String findDetail() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Productinfo productinfo = bizs.getProductInfobiz().findDetail(productId);
		if(productinfo != null) {
			request.setAttribute("product",productinfo);
			return "success";
		} else {
			return "failed";
		}
	}

}
	