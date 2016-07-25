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
public class Orderdetail implements java.io.Serializable {

	// Fields

	private Integer orderDetailId;
	private Productinfo productinfo;
	private Ordermain ordermain;
	private Integer num;
	private Float sumPrice;

	// Constructors

	/** default constructor */
	public Orderdetail() {
	}

	/** minimal constructor */
	public Orderdetail(Productinfo productinfo, Ordermain ordermain, Integer num) {
		this.productinfo = productinfo;
		this.ordermain = ordermain;
		this.num = num;
	}

	/** full constructor */
	public Orderdetail(Productinfo productinfo, Ordermain ordermain,
			Integer num, Float sumPrice) {
		this.productinfo = productinfo;
		this.ordermain = ordermain;
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
	public Productinfo getProductinfo() {
		return this.productinfo;
	}

	public void setProductinfo(Productinfo productinfo) {
		this.productinfo = productinfo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orderMainId", nullable = false)
	public Ordermain getOrdermain() {
		return this.ordermain;
	}

	public void setOrdermain(Ordermain ordermain) {
		this.ordermain = ordermain;
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