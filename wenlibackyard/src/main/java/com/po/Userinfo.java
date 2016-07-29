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
public class Userinfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -273826351985678325L;
	// Fields

	private String userId;
	private String userNickName;
	private String userHeadImgUrl;
	private Set<Ordermain> ordermains = new HashSet<Ordermain>(0);
	private Userdetailinfo userdetailinfo;
	private Set<Favorite> favorites = new HashSet<Favorite>(0);
	private Set<Productinfo> productinfos = new HashSet<Productinfo>(0);
	
	// Constructors

	/** default constructor */
	public Userinfo() {
	}

	/** minimal constructor */
	public Userinfo(String userNickName, String userHeadImgUrl) {
		this.userNickName = userNickName;
		this.userHeadImgUrl = userHeadImgUrl;
	}

	/** full constructor */
	public Userinfo(String userNickName, String userHeadImgUrl,
			Set<Ordermain> ordermains, Userdetailinfo userdetailinfo,
			Set<Favorite> favorites, Set<Productinfo> productinfos) {
		this.userNickName = userNickName;
		this.userHeadImgUrl = userHeadImgUrl;
		this.ordermains = ordermains;
		this.userdetailinfo = userdetailinfo;
		this.favorites = favorites;
		this.productinfos = productinfos;
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
	public Set<Ordermain> getOrdermains() {
		return this.ordermains;
	}

	public void setOrdermains(Set<Ordermain> ordermains) {
		this.ordermains = ordermains;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userinfo")
	public Userdetailinfo getUserdetailinfo() {
		return this.userdetailinfo;
	}

	public void setUserdetailinfo(Userdetailinfo userdetailinfo) {
		this.userdetailinfo = userdetailinfo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userinfo")
	public Set<Favorite> getFavorites() {
		return this.favorites;
	}

	public void setFavorites(Set<Favorite> favorites) {
		this.favorites = favorites;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userinfo")
	public Set<Productinfo> getProductinfos() {
		return this.productinfos;
	}
	
	public void setProductinfos(Set<Productinfo> productinfos) {
		this.productinfos = productinfos;
	}

}