package com.po;

import java.io.File;
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
import javax.persistence.Transient;

/**
 * Productinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "productinfo", catalog = "wenlibackyard")
public class Productinfo implements java.io.Serializable {

	// Fields

	private Integer productId;
	private Producttype producttype;
	private Userinfo userinfo;
	private String productName;
	private String brand;
	private String context;
	private String imgs;
	private Float price;
	private Integer number;
	private Date buydate;
	private Date pbdate;
	private Integer state;
	private Set<Orderdetail> orderdetails = new HashSet<Orderdetail>(0);
	private Set<Favorite> favorites = new HashSet<Favorite>(0);
	/*********************与界面关联的属性***************************/
	private File pic;
	private String picFileName;
	private String picContentType;
	
	
	// Constructors

	/** default constructor */
	public Productinfo() {
	}

	/** minimal constructor */
	public Productinfo(Producttype producttype, Userinfo userinfo,
			String productName, String context, String imgs, Float price,
			Integer number, Date pbdate, Integer state) {
		this.producttype = producttype;
		this.userinfo = userinfo;
		this.productName = productName;
		this.context = context;
		this.imgs = imgs;
		this.price = price;
		this.number = number;
		this.pbdate = pbdate;
		this.state = state;
	}

	/** full constructor */
	public Productinfo(Producttype producttype, Userinfo userinfo,
			String productName, String brand, String context, String imgs,
			Float price, Integer number, Date buydate, Date pbdate,
			Integer state, Set<Orderdetail> orderdetails,
			Set<Favorite> favorites) {
		this.producttype = producttype;
		this.userinfo = userinfo;
		this.productName = productName;
		this.brand = brand;
		this.context = context;
		this.imgs = imgs;
		this.price = price;
		this.number = number;
		this.buydate = buydate;
		this.pbdate = pbdate;
		this.state = state;
		this.orderdetails = orderdetails;
		this.favorites = favorites;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "productId", unique = true, nullable = false)
	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productTypeId", nullable = false)
	public Producttype getProducttype() {
		return this.producttype;
	}

	public void setProducttype(Producttype producttype) {
		this.producttype = producttype;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", nullable = false)
	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	@Column(name = "productName", nullable = false, length = 20)
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "brand", length = 20)
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "context", nullable = false, length = 65535)
	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Column(name = "imgs", nullable = false, length = 65535)
	public String getImgs() {
		return this.imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	@Column(name = "price", nullable = false, precision = 12, scale = 0)
	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Column(name = "number", nullable = false)
	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "buydate", length = 10)
	public Date getBuydate() {
		return this.buydate;
	}

	public void setBuydate(Date buydate) {
		this.buydate = buydate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "pbdate", nullable = false, length = 10)
	public Date getPbdate() {
		return this.pbdate;
	}

	public void setPbdate(Date pbdate) {
		this.pbdate = pbdate;
	}

	@Column(name = "state", nullable = false)
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "productinfo")
	public Set<Orderdetail> getOrderdetails() {
		return this.orderdetails;
	}

	public void setOrderdetails(Set<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "productinfo")
	public Set<Favorite> getFavorites() {
		return this.favorites;
	}

	public void setFavorites(Set<Favorite> favorites) {
		this.favorites = favorites;
	}

	/**********************于界面关联的属性***************************/
	@Transient
	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	@Transient
	public String getPicContentType() {
		return picContentType;
	}

	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}

	@Transient
	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

}