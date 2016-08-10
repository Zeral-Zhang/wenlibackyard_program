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
 * Producttype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "producttype", catalog = "wenlibackyard")
public class ProductType implements java.io.Serializable {

	// Fields

	private Integer productTypeId;
	private Integer parentId;
	private String productTypeName;
	private Integer isDelete;
	private String context;
	private Set<ProductInfo> productInfos = new HashSet<ProductInfo>(0);

	// Constructors

	/** default constructor */
	public ProductType() {
	}

	/** minimal constructor */
	public ProductType(Integer parentId, String productTypeName,
			Integer isDelete) {
		this.parentId = parentId;
		this.productTypeName = productTypeName;
		this.isDelete = isDelete;
	}

	/** full constructor */
	public ProductType(Integer parentId, String productTypeName,
			Integer isDelete, String context, Set<ProductInfo> productInfos) {
		this.parentId = parentId;
		this.productTypeName = productTypeName;
		this.isDelete = isDelete;
		this.context = context;
		this.productInfos = productInfos;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "productTypeId", unique = true, nullable = false)
	public Integer getProductTypeId() {
		return this.productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	@Column(name = "parentId", nullable = false)
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "productTypeName", nullable = false, length = 20)
	public String getProductTypeName() {
		return this.productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	@Column(name = "isDelete", nullable = false)
	public Integer getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "context", length = 50)
	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "producttype")
	public Set<ProductInfo> getProductinfos() {
		return this.productInfos;
	}

	public void setProductinfos(Set<ProductInfo> productInfos) {
		this.productInfos = productInfos;
	}

}