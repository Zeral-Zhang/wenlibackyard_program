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
public class Schoolinfo implements java.io.Serializable {

	// Fields

	private Integer schoolInfoId;
	private String college;
	private String department;
	private String classes;
	private String grade;
	private Set<Userdetailinfo> userdetailinfos = new HashSet<Userdetailinfo>(0);

	// Constructors

	/** default constructor */
	public Schoolinfo() {
	}

	/** full constructor */
	public Schoolinfo(String college, String department, String classes,
			String grade, Set<Userdetailinfo> userdetailinfos) {
		this.college = college;
		this.department = department;
		this.classes = classes;
		this.grade = grade;
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

	@Column(name = "college", length = 20)
	public String getCollege() {
		return this.college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	@Column(name = "department", length = 20)
	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "classes", length = 2)
	public String getClasses() {
		return this.classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	@Column(name = "grade", length = 10)
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "schoolinfo")
	public Set<Userdetailinfo> getUserdetailinfos() {
		return this.userdetailinfos;
	}

	public void setUserdetailinfos(Set<Userdetailinfo> userdetailinfos) {
		this.userdetailinfos = userdetailinfos;
	}

}