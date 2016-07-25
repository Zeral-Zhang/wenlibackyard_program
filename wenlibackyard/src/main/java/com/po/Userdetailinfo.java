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
import javax.persistence.UniqueConstraint;

/**
 * Userdetailinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "userdetailinfo", catalog = "wenlibackyard", uniqueConstraints = { @UniqueConstraint(columnNames = "schoolInfoId") })
public class Userdetailinfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4147990827448918428L;
	// Fields

	private Integer userDetailId;
	private Userinfo userinfo;
	private Schoolinfo schoolinfo;
	private String userTel;
	private String userCity;
	private String userProvince;
	private String userLanguage;
	private String userGender;
	private Integer userAge;

	// Constructors

	/** default constructor */
	public Userdetailinfo() {
	}

	/** minimal constructor */
	public Userdetailinfo(Userinfo userinfo, Schoolinfo schoolinfo,
			String userGender, Integer userAge) {
		this.userinfo = userinfo;
		this.schoolinfo = schoolinfo;
		this.userGender = userGender;
		this.userAge = userAge;
	}

	/** full constructor */
	public Userdetailinfo(Userinfo userinfo, Schoolinfo schoolinfo,
			String userTel, String userCity, String userProvince,
			String userLanguage, String userGender, Integer userAge) {
		this.userinfo = userinfo;
		this.schoolinfo = schoolinfo;
		this.userTel = userTel;
		this.userCity = userCity;
		this.userProvince = userProvince;
		this.userLanguage = userLanguage;
		this.userGender = userGender;
		this.userAge = userAge;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "userDetailId", unique = true, nullable = false)
	public Integer getUserDetailId() {
		return this.userDetailId;
	}

	public void setUserDetailId(Integer userDetailId) {
		this.userDetailId = userDetailId;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "schoolInfoId", unique = true, nullable = true)
	public Schoolinfo getSchoolinfo() {
		return this.schoolinfo;
	}

	public void setSchoolinfo(Schoolinfo schoolinfo) {
		this.schoolinfo = schoolinfo;
	}

	@Column(name = "userTel", length = 13)
	public String getUserTel() {
		return this.userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	@Column(name = "userCity", length = 20)
	public String getUserCity() {
		return this.userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	@Column(name = "userProvince", length = 20)
	public String getUserProvince() {
		return this.userProvince;
	}

	public void setUserProvince(String userProvince) {
		this.userProvince = userProvince;
	}

	@Column(name = "userLanguage", length = 20)
	public String getUserLanguage() {
		return this.userLanguage;
	}

	public void setUserLanguage(String userLanguage) {
		this.userLanguage = userLanguage;
	}

	@Column(name = "userGender", nullable = true, length = 1)
	public String getUserGender() {
		return this.userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	@Column(name = "userAge", nullable = false)
	public Integer getUserAge() {
		return this.userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	@Override
	public String toString() {
		return "Userdetailinfo [userDetailId=" + userDetailId + ", userinfo="
				+ userinfo + ", schoolinfo=" + schoolinfo + ", userTel="
				+ userTel + ", userCity=" + userCity + ", userProvince="
				+ userProvince + ", userLanguage=" + userLanguage
				+ ", userGender=" + userGender + ", userAge=" + userAge + "]";
	}

}