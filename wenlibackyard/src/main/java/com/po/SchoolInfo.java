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
 * 院系信息表
 * @author Zeral
 *
 */
@Entity
@Table(name = "school_info")
public class SchoolInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private Integer schoolInfoId;
	private String code;
	private String name;
	private String PCode;
	private Integer level;
	private Set<UserDetailInfo> userDetailInfos = new HashSet<UserDetailInfo>(0);

	// Constructors

	/** default constructor */
	public SchoolInfo() {
	}

	/** full constructor */
	public SchoolInfo(String code, String name, String pCode,
			Integer level, Set<UserDetailInfo> userDetailInfos) {
		this.code = code;
		this.name = name;
		this.PCode = pCode;
		this.level = level;
		this.userDetailInfos = userDetailInfos;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "school_info_id", unique = true, nullable = false)
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
		return this.PCode;
	}

	public void setPCode(String pCode) {
		this.PCode = pCode;
	}

	@Column(name = "level", length = 10)
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "schoolInfo")
	public Set<UserDetailInfo> getUserDetailInfos() {
		return this.userDetailInfos;
	}

	public void setUserDetailInfos(Set<UserDetailInfo> userDetailInfos) {
		this.userDetailInfos = userDetailInfos;
	}

}