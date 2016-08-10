package com.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Orderdetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "orderdetail", catalog = "wenlibackyard")
public class OrderDetail implements java.io.Serializable {

	// Fields

	private Integer orderDetailId;
	private ProductInfo productinfo;
	private OrderMain ordermain;
	private Integer num;
	private Float sumPrice;

	// Constructors

	/** default constructor */
	public OrderDetail() {
	}

	/** minimal constructor */
	public OrderDetail(ProductInfo productInfo, OrderMain orderMain, Integer num) {
		this.productinfo = productInfo;
		this.ordermain = orderMain;
		this.num = num;
	}

	/** full constructor */
	public OrderDetail(ProductInfo productInfo, OrderMain orderMain,
			Integer num, Float sumPrice) {
		this.productinfo = productInfo;
		this.ordermain = orderMain;
		this.num = num;
		this.sumPrice = sumPrice;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "orderDetailId", unique = true, nullable = false)
	public Integer getOrderDetailId() {
		return this.orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productId", nullable = false)
	public ProductInfo getProductinfo() {
		return this.productinfo;
	}

	public void setProductinfo(ProductInfo productInfo) {
		this.productinfo = productInfo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orderMainId", nullable = false)
	public OrderMain getOrdermain() {
		return this.ordermain;
	}

	public void setOrdermain(OrderMain orderMain) {
		this.ordermain = orderMain;
	}

	@Column(name = "num", nullable = false)
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name = "sumPrice", precision = 12, scale = 0)
	public Float getSumPrice() {
		return this.sumPrice;
	}

	public void setSumPrice(Float sumPrice) {
		this.sumPrice = sumPrice;
	}

}