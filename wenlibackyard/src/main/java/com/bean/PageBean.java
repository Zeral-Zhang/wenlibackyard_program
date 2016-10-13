package com.bean;

import java.io.Serializable;
import java.util.List;

public class PageBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Integer DEFAULT_PAGE = 0;
	public static final Integer DEFAULT_ROWS = 8;

	private int page = DEFAULT_PAGE; // 当前页
	private int rows = DEFAULT_ROWS; // 每页记录数
	private int maxpage; // 最大页数
	private long totalCount; // 总记录数
	private int offset; // 偏移量，从第几条记录开始(nowPage-1)*pageSize
	private List<?> pagelist;

	public PageBean() {
		super();
	}

	public PageBean(int page, int rows, int maxpage, List<?> pagelist) {
		super();
		this.page = page;
		this.rows = rows;
		this.maxpage = maxpage;
		this.pagelist = pagelist;
		
	}

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

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		if(rows < 1) {
			this.rows = rows;
		} else {
			this.rows = rows;
		}
	}

	public int getMaxpage() {
		return maxpage;
	}

	public void setMaxpage(int maxpage) {
		this.maxpage = maxpage;
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
		return this.page * this.rows;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "PageBean [page=" + page + ", rows=" + rows + ", maxpage="
				+ maxpage + ", pagelist=" + pagelist + "]";
	}

}
