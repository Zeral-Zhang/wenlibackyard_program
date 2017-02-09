package com.po;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * 用户信息实体Bean
 * 用户信息来自微信用户数据
 * @author Zeral
 *
 */
@Entity
public class UserInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	/**
	 * 用户openId
	 */
	private String userId;
	/**
	 * 用户昵称
	 */
	private String userNickName;
	/**
	 * 用户头像地址
	 */
	private String userHeadImgUrl;
	/**
	 * 用户性别（1是男性，2是女性，0是未知）
	 */
	private Integer userGender;
	/**
	 * 用户城市
	 */
	private String userCity;
	/**
	 * 用户省
	 */
	private String userProvince;
	/**
	 * 用户使用语言
	 */
	private String userLanguage;

	private UserDetailInfo userDetailInfo;
	
	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	@Id
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNickName() {
		return this.userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getUserHeadImgUrl() {
		return this.userHeadImgUrl;
	}
	
	public Integer getUserGender() {
		return userGender;
	}

	public void setUserGender(Integer userGender) {
		this.userGender = userGender;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserProvince() {
		return userProvince;
	}

	public void setUserProvince(String userProvince) {
		this.userProvince = userProvince;
	}

	public void setUserHeadImgUrl(String userHeadImgUrl) {
		this.userHeadImgUrl = userHeadImgUrl;
	}

	public String getUserLanguage() {
		return userLanguage;
	}

	public void setUserLanguage(String userLanguage) {
		this.userLanguage = userLanguage;
	}

	public UserDetailInfo getUserDetailInfo() {
		return userDetailInfo;
	}

	public void setUserDetailInfo(UserDetailInfo userDetailInfo) {
		this.userDetailInfo = userDetailInfo;
	}

}