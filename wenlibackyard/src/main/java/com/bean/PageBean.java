package com.bean;

import java.io.Serializable;
import java.util.List;

import com.util.IPageInfo;

/**
 * 
 * @author: ZeralZhang
 * @date: 2017年1月8日
 */
public class PageBean implements IPageInfo,  Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final Integer DEFAULT_PAGE = 0;
	public static final Integer DEFAULT_SIZE = 8;

	/**
	 * 当前页
	 */
	private int page = DEFAULT_PAGE; 
	/**
	 * 每页记录数
	 */
	private int pageSize = DEFAULT_SIZE; 
	/**
	 * 记录总条数
	 */
	private long totalCount; 
	/**
	 * 偏移量，从第几条记录开始(nowPage-1)*pageSize
	 */
	private int offset; 
	/**
	 * 数据
	 */
	private List<?> pagelist;

	public PageBean() {
	}

	public PageBean(int pageNo, int pageSize) {
		super();
		this.offset = pageSize * pageNo;
		this.pageSize = pageSize;
	}

	/**
	 * 获取总页数
	 * @return
	 */
	public int getPageCount() {
		return (int) Math.ceil((0.0 + this.totalCount) / this.pageSize);
	}

	/**
	 * 获取当前页
	 * @return
	 */
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page < 1) {
			this.page = DEFAULT_PAGE;
		} else {
			this.page = page;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if(pageSize < 1) {
			this.pageSize = DEFAULT_SIZE;
		} else {
			this.pageSize = pageSize;
		}
	}

	public List<?> getPagelist() {
		return pagelist;
	}

	public void setPagelist(List<?> pagelist) {
		this.pagelist = pagelist;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getOffset() {
		if(offset != 0){
			return offset;
		}
		return this.page * this.pageSize;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

}
