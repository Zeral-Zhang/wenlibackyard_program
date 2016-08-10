package com.po;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

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

	private Integer orderMainId;
	private UserInfo userInfo;
	/**
	 * 销售单状态（0-未处理，1-已处理，2-以发货，3-已收货）
	 */
	private Integer state;
	private Date buyDate;
	private Date payDate;
	private Date confirmDate;
	private Float sumPrice;
	private String context;
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>(0);

	// Constructors

	/** default constructor */
	public OrderMain() {
	}

	/** minimal constructor */
	public OrderMain(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/** full constructor */
	public OrderMain(UserInfo userInfo, Integer state, Date buyDate,
			Date payDate, Date confirmDate, Float sumPrice, String context,
			Set<OrderDetail> orderDetails) {
		this.userInfo = userInfo;
		this.state = state;
		this.buyDate = buyDate;
		this.payDate = payDate;
		this.confirmDate = confirmDate;
		this.sumPrice = sumPrice;
		this.context = context;
		this.orderDetails = orderDetails;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "order_main_id", unique = true, nullable = false)
	public Integer getOrderMainId() {
		return this.orderMainId;
	}

	public void setOrderMainId(Integer orderMainId) {
		this.orderMainId = orderMainId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
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
	public Set<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

}