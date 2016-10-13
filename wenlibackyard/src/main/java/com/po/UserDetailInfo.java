package com.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 用户信息详情表
 * @author Zeral
 *
 */
@Entity
@Table(name = "user_detail_info")
public class UserDetailInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private Integer userDetailId;
	/**
	 * 用户基本信息
	 */
	private UserInfo userInfo;
	/**
	 * 系
	 */
	private SchoolInfo schoolInfo;
	/**
	 * 用户电话
	 */
	private String userTel;
	/**
	 * 用户城市
	 */
	private String userCity;
	/**
	 * 用户省
	 */
	private String userProvince;
	/**
	 * 微信使用语言
	 */
	private String userLanguage;
	/**
	 * 用户性别
	 */
	private String userGender;
	/**
	 * 用户班
	 */
	private String userClass;
	/**
	 * 用户级
	 */
	private String userGrade;
	/**
	 * 用户年龄
	 */
	private Integer userAge;

	// Constructors

	/** default constructor */
	public UserDetailInfo() {
	}


	public UserDetailInfo(UserInfo userInfo, SchoolInfo schoolInfo, String userTel,
			String userCity, String userProvince, String userLanguage, String userGender, String userClass,
			String userGrade, Integer userAge) {
		this.userInfo = userInfo;
		this.schoolInfo = schoolInfo;
		this.userTel = userTel;
		this.userCity = userCity;
		this.userProvince = userProvince;
		this.userLanguage = userLanguage;
		this.userGender = userGender;
		this.userClass = userClass;
		this.userGrade = userGrade;
		this.userAge = userAge;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_detail_id", unique = true, nullable = false)
	public Integer getUserDetailId() {
		return this.userDetailId;
	}

	public void setUserDetailId(Integer userDetailId) {
		this.userDetailId = userDetailId;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public UserInfo getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(UserInfo userinfo) {
		this.userInfo = userinfo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "school_info_id", unique = true, nullable = true)
	public SchoolInfo getSchoolInfo() {
		return this.schoolInfo;
	}

	public void setSchoolInfo(SchoolInfo schoolinfo) {
		this.schoolInfo = schoolinfo;
	}

	@Column(name = "user_tel", length = 13)
	public String getUserTel() {
		return this.userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	@Column(name = "user_city", length = 20)
	public String getUserCity() {
		return this.userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	@Column(name = "user_province", length = 20)
	public String getUserProvince() {
		return this.userProvince;
	}

	public void setUserProvince(String userProvince) {
		this.userProvince = userProvince;
	}

	@Column(name = "user_language", length = 20)
	public String getUserLanguage() {
		return this.userLanguage;
	}

	public void setUserLanguage(String userLanguage) {
		this.userLanguage = userLanguage;
	}

	@Column(name = "user_gender", nullable = true, length = 1)
	public String getUserGender() {
		return this.userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	
	@Column(name = "user_class", length = 10)
	public String getUserClass() {
		return userClass;
	}

	public void setUserClass(String userClass) {
		this.userClass = userClass;
	}

	@Column(name = "user_grade", length = 4)
	public String getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}

	@Column(name = "user_age", nullable = true)
	public Integer getUserAge() {
		return this.userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	@Override
	public String toString() {
		return "UserDetailInfo [userDetailId=" + userDetailId + ", userInfo=" + userInfo + ", schoolInfo=" + schoolInfo
				+ ", userTel=" + userTel + ", userCity=" + userCity + ", userProvince=" + userProvince
				+ ", userLanguage=" + userLanguage + ", userGender=" + userGender + ", userClass=" + userClass
				+ ", userGrade=" + userGrade + ", userAge=" + userAge + "]";
	}
}