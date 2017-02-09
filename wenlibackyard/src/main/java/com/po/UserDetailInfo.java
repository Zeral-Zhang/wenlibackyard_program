package com.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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

	private String userDetailId;
	/**
	 * 用户基本信息
	 */
	private String userInfo;
	/**
	 * 系
	 */
	private SchoolInfo schoolInfo;
	/**
	 * 用户电话
	 */
	private String userTel;
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


	// Property accessors
	@Id
	@GeneratedValue(generator = "paymengtableGenerator")
 	@GenericGenerator(name = "paymengtableGenerator", strategy = "uuid")
	@Column(name = "user_detail_id", unique = true, nullable = false, length = 32)
	public String getUserDetailId() {
		return this.userDetailId;
	}

	public void setUserDetailId(String userDetailId) {
		this.userDetailId = userDetailId;
	}

	@Column(name = "user_id", length = 32)
	public String getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(String userinfo) {
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
}