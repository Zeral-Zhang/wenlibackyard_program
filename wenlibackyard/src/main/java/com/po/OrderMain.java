package com.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 订单主表
 * @author Zeral
 *
 */
@Entity
@Table(name = "order_main")
public class OrderMain implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	public static final int UN_HANDLE = 0;
	public static final int HANDLED = 1;
	public static final int HAS_PRESS = 2;
	public static final int HAS_CHECKED = 3;

	// Fields

	private String orderMainId;
	private String userInfoId;
	/**
	 * 销售单状态（0-未处理，1-已处理，2-以发货，3-已收货）
	 */
	private Integer state;
	private Date buyDate;
	private Date payDate;
	private Date confirmDate;
	private Float sumPrice;
	private String context;
	private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>(0);

	// Constructors

	/** default constructor */
	public OrderMain() {
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "order_main_id", unique = true, nullable = false, length = 32)
	public String getOrderMainId() {
		return this.orderMainId;
	}

	public void setOrderMainId(String orderMainId) {
		this.orderMainId = orderMainId;
	}

	@Column(name = "user_id", length = 32)
	public String getUserInfoId() {
		return this.userInfoId;
	}

	public void setUserInfoId(String userInfoId) {
		this.userInfoId = userInfoId;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "buy_date", length = 10)
	public Date getBuyDate() {
		return this.buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "pay_date", length = 10)
	public Date getPayDate() {
		return this.payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "confirm_date", length = 10)
	public Date getConfirmDate() {
		return this.confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	@Column(name = "sum_price", precision = 7, scale = 2)
	public Float getSumPrice() {
		return this.sumPrice;
	}

	public void setSumPrice(Float sumPrice) {
		this.sumPrice = sumPrice;
	}

	@Column(name = "context", length = 50)
	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderMain")
	public List<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

}