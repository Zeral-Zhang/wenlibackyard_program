package com.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 系统配置实体类
 * 
 * @author Zeral
 * 
 */
@Entity
@Table(name = "basic_config")
public class BasicConfig implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String basicConfigId;
	private String name;
	private String value;

	public BasicConfig() {
	}

	public BasicConfig(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public BasicConfig(String basicConfigId, String name, String value) {
		super();
		this.basicConfigId = basicConfigId;
		this.name = name;
		this.value = value;
	}
	
	public BasicConfig(String id, String basicConfigId, String name, String value) {
		super();
		this.id = id;
		this.basicConfigId = basicConfigId;
		this.name = name;
		this.value = value;
	}

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "BASIC_CONFIG_ID", nullable = false)
	public String getBasicConfigId() {
		return this.basicConfigId;
	}

	public void setBasicConfigId(String basicConfigId) {
		this.basicConfigId = basicConfigId;
	}

	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "VALUE", length = 400)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
