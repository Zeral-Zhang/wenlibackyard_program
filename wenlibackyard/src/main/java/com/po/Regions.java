package com.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Regions entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "regions", catalog = "wenlibackyard")
public class Regions implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer code;
	private String name;
	private Integer PCode;
	private Integer level;

	// Constructors

	/** default constructor */
	public Regions() {
	}

	/** full constructor */
	public Regions(Integer code, String name, Integer PCode, Integer level) {
		this.code = code;
		this.name = name;
		this.PCode = PCode;
		this.level = level;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "code")
	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "p_code")
	public Integer getPCode() {
		return this.PCode;
	}

	public void setPCode(Integer PCode) {
		this.PCode = PCode;
	}

	@Column(name = "level")
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}