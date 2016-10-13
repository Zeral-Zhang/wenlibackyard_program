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

/**
 * 收藏夹
 * @author Zeral
 *
 */
@Entity
@Table(name = "favorite")
public class Favorite implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
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
	@Column(name = "favorite_id", unique = true, nullable = false)
	public Integer getFavoriteId() {
		return this.favoriteId;
	}

	public void setFavoriteId(Integer favoriteId) {
		this.favoriteId = favoriteId;
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
	@JoinColumn(name = "user_id",  nullable = false)
	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "create_date", nullable = false, length = 10)
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