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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Ordermain entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ordermain", catalog = "wenlibackyard", uniqueConstraints = @UniqueConstraint(columnNames = "userId"))
public class Ordermain implements java.io.Serializable {

	// Fields

	private Integer orderMainId;
	private Userinfo userinfo;
	private Integer state;
	private Date buyDate;
	private Date payDate;
	private Date confirmDate;
	private Float sumPrice;
	private String context;
	private Set<Orderdetail> orderdetails = new HashSet<Orderdetail>(0);

	// Constructors

	/** default constructor */
	public Ordermain() {
	}

	/** minimal constructor */
	public Ordermain(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	/** full constructor */
	public Ordermain(Userinfo userinfo, Integer state, Date buyDate,
			Date payDate, Date confirmDate, Float sumPrice, String context,
			Set<Orderdetail> orderdetails) {
		this.userinfo = userinfo;
		this.state = state;
		this.buyDate = buyDate;
		this.payDate = payDate;
		this.confirmDate = confirmDate;
		this.sumPrice = sumPrice;
		this.context = context;
		this.orderdetails = orderdetails;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "orderMainId", unique = true, nullable = false)
	public Integer getOrderMainId() {
		return this.orderMainId;
	}

	public void setOrderMainId(Integer orderMainId) {
		this.orderMainId = orderMainId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", unique = true, nullable = false)
	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "buyDate", length = 10)
	public Date getBuyDate() {
		return this.buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "payDate", length = 10)
	public Date getPayDate() {
		return this.payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "confirmDate", length = 10)
	public Date getConfirmDate() {
		return this.confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	@Column(name = "sumPrice", precision = 12, scale = 0)
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ordermain")
	public Set<Orderdetail> getOrderdetails() {
		return this.orderdetails;
	}

	public void setOrderdetails(Set<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}

}