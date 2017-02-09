package com.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 订单详情表
 * @author Zeral
 *
 */
@Entity
@Table(name = "order_detail")
public class OrderDetail implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private String orderDetailId;
	private ProductInfo productInfo;
	private OrderMain orderMain;
	private Integer num;
	private Float sumPrice;

	// Constructors

	/** default constructor */
	public OrderDetail() {
	}

	/** minimal constructor */
	public OrderDetail(ProductInfo productInfo, OrderMain orderMain, Integer num) {
		this.productInfo = productInfo;
		this.orderMain = orderMain;
		this.num = num;
	}

	/** full constructor */
	public OrderDetail(ProductInfo productInfo, OrderMain orderMain,
			Integer num, Float sumPrice) {
		this.productInfo = productInfo;
		this.orderMain = orderMain;
		this.num = num;
		this.sumPrice = sumPrice;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "order_detail_id", unique = true, nullable = false, length=32)
	public String getOrderDetailId() {
		return this.orderDetailId;
	}

	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id", nullable = false)
	public ProductInfo getProductInfo() {
		return this.productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_main_id", nullable = false)
	public OrderMain getOrderMain() {
		return this.orderMain;
	}

	public void setOrderMain(OrderMain orderMain) {
		this.orderMain = orderMain;
	}

	@Column(name = "num", nullable = false)
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name = "sum_price", precision = 7, scale = 2)
	public Float getSumPrice() {
		return this.sumPrice;
	}

	public void setSumPrice(Float sumPrice) {
		this.sumPrice = sumPrice;
	}

}