package com.bean;

import com.po.Productinfo;

public class ShopCarItem {
	private Productinfo product;
	
	private int num;
	
	private float price;		
	
	
	public ShopCarItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Productinfo getProduct() {
		return product;
	}

	public void setProduct(Productinfo product) {
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
