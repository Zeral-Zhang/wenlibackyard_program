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

import org.hibernate.annotations.GenericGenerator;


/**
 * 用户信息表
 * @author Zeral
 *
 */
@Entity
@Table(name = "user_info")
public class UserInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
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
			Set<OrderMain> orderMains, UserDetailInfo userDetailInfo,
			Set<Favorite> favorites, Set<ProductInfo> productInfos) {
		this.userNickName = userNickName;
		this.userHeadImgUrl = userHeadImgUrl;
		this.orderMains = orderMains;
		this.userDetailInfo = userDetailInfo;
		this.favorites = favorites;
		this.productInfos = productInfos;
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "paymengtableGenerator")
 	@GenericGenerator(name = "paymengtableGenerator", strategy = "uuid")
	@Column(name = "user_id", unique = true, nullable = false, length = 60)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "user_nick_name", nullable = false, length = 20)
	public String getUserNickName() {
		return this.userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	@Column(name = "user_head_img_url", nullable = false, length = 300)
	public String getUserHeadImgUrl() {
		return this.userHeadImgUrl;
	}

	public void setUserHeadImgUrl(String userHeadImgUrl) {
		this.userHeadImgUrl = userHeadImgUrl;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userInfo")
	public Set<OrderMain> getOrderMains() {
		return this.orderMains;
	}

	public void setOrderMains(Set<OrderMain> ordermains) {
		this.orderMains = ordermains;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userInfo")
	public UserDetailInfo getUserDetailInfo() {
		return this.userDetailInfo;
	}

	public void setUserDetailInfo(UserDetailInfo userdetailinfo) {
		this.userDetailInfo = userdetailinfo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userInfo")
	public Set<Favorite> getFavorites() {
		return this.favorites;
	}

	public void setFavorites(Set<Favorite> favorites) {
		this.favorites = favorites;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userInfo")
	public Set<ProductInfo> getProductInfos() {
		return this.productInfos;
	}
	
	public void setProductInfos(Set<ProductInfo> productInfos) {
		this.productInfos = productInfos;
	}

}