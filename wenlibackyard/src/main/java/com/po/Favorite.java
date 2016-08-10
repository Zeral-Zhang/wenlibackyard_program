package com.po;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Favorite entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "favorite", catalog = "wenlibackyard", uniqueConstraints = @UniqueConstraint(columnNames = "userId"))
public class Favorite implements java.io.Serializable {

	// Fields

	private Integer favoriteId;
	private ProductInfo productInfo;
	private UserInfo userInfo;
	private Date createDate;
	private String context;

	// Constructors

	/** default constructor */
	public Favorite() {
	}

	/** minimal constructor */
	public Favorite(ProductInfo productInfo, UserInfo userInfo, Date createDate) {
		this.productInfo = productInfo;
		this.userInfo = userInfo;
		this.createDate = createDate;
	}

	/** full constructor */
	public Favorite(ProductInfo productInfo, UserInfo userInfo,
			Date createDate, String context) {
		this.productInfo = productInfo;
		this.userInfo = userInfo;
		this.createDate = createDate;
		this.context = context;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "favoriteId", unique = true, nullable = false)
	public Integer getFavoriteId() {
		return this.favoriteId;
	}

	public void setFavoriteId(Integer favoriteId) {
		this.favoriteId = favoriteId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productId", nullable = false)
	public ProductInfo getProductinfo() {
		return this.productInfo;
	}

	public void setProductinfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", unique = true, nullable = false)
	public UserInfo getUserinfo() {
		return this.userInfo;
	}

	public void setUserinfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "createDate", nullable = false, length = 10)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "context", length = 50)
	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

}