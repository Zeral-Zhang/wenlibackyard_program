package com.po;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Userinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "userinfo", catalog = "wenlibackyard")
public class UserInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -273826351985678325L;
	// Fields

	private String userId;
	private String userNickName;
	private String userHeadImgUrl;
	private Set<OrderMain> orderMains = new HashSet<OrderMain>(0);
	private UserDetailInfo userDetailInfo;
	private Set<Favorite> favorites = new HashSet<Favorite>(0);
	private Set<ProductInfo> productInfos = new HashSet<ProductInfo>(0);
	
	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	/** minimal constructor */
	public UserInfo(String userNickName, String userHeadImgUrl) {
		this.userNickName = userNickName;
		this.userHeadImgUrl = userHeadImgUrl;
	}

	/** full constructor */
	public UserInfo(String userNickName, String userHeadImgUrl,
			Set<OrderMain> ordermains, UserDetailInfo userdetailinfo,
			Set<Favorite> favorites, Set<ProductInfo> productInfos) {
		this.userNickName = userNickName;
		this.userHeadImgUrl = userHeadImgUrl;
		this.orderMains = ordermains;
		this.userDetailInfo = userdetailinfo;
		this.favorites = favorites;
		this.productInfos = productInfos;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "userId", unique = true, nullable = false, length = 60)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "userNickName", nullable = false, length = 20)
	public String getUserNickName() {
		return this.userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	@Column(name = "userHeadImgUrl", nullable = false, length = 300)
	public String getUserHeadImgUrl() {
		return this.userHeadImgUrl;
	}

	public void setUserHeadImgUrl(String userHeadImgUrl) {
		this.userHeadImgUrl = userHeadImgUrl;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userinfo")
	public Set<OrderMain> getOrderMains() {
		return this.orderMains;
	}

	public void setOrderMains(Set<OrderMain> ordermains) {
		this.orderMains = ordermains;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userinfo")
	public UserDetailInfo getUserDetailInfo() {
		return this.userDetailInfo;
	}

	public void setUserDetailInfo(UserDetailInfo userdetailinfo) {
		this.userDetailInfo = userdetailinfo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userinfo")
	public Set<Favorite> getFavorites() {
		return this.favorites;
	}

	public void setFavorites(Set<Favorite> favorites) {
		this.favorites = favorites;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userinfo")
	public Set<ProductInfo> getProductInfos() {
		return this.productInfos;
	}
	
	public void setProductInfos(Set<ProductInfo> productInfos) {
		this.productInfos = productInfos;
	}

}