package com.bean;

import com.po.ProductInfo;

public class ShopCarItem {
	private ProductInfo product;
	
	private int num;
	
	private float price;		
	
	
	public ShopCarItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductInfo getProduct() {
		return product;
	}

	public void setProduct(ProductInfo product) {
		this.product = product;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
