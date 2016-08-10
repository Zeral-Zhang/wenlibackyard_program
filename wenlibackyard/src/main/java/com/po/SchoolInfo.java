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
import javax.persistence.Table;

/**
 * Schoolinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "schoolinfo", catalog = "wenlibackyard")
public class SchoolInfo implements java.io.Serializable {

	// Fields

	private Integer schoolInfoId;
	private String code;
	private String name;
	private String pCode;
	private Integer level;
	private Set<UserDetailInfo> userdetailinfos = new HashSet<UserDetailInfo>(0);

	// Constructors

	/** default constructor */
	public SchoolInfo() {
	}

	/** full constructor */
	public SchoolInfo(String code, String name, String pCode,
			Integer level, Set<UserDetailInfo> userdetailinfos) {
		this.code = code;
		this.name = name;
		this.pCode = pCode;
		this.level = level;
		this.userdetailinfos = userdetailinfos;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "schoolInfoId", unique = true, nullable = false)
	public Integer getSchoolInfoId() {
		return this.schoolInfoId;
	}

	public void setSchoolInfoId(Integer schoolInfoId) {
		this.schoolInfoId = schoolInfoId;
	}

	@Column(name = "code", length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "p_code", length = 20)
	public String getPCode() {
		return this.pCode;
	}

	public void setPCode(String pCode) {
		this.pCode = pCode;
	}

	@Column(name = "level", length = 10)
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "schoolinfo")
	public Set<UserDetailInfo> getUserdetailinfos() {
		return this.userdetailinfos;
	}

	public void setUserdetailinfos(Set<UserDetailInfo> userdetailinfos) {
		this.userdetailinfos = userdetailinfos;
	}

}