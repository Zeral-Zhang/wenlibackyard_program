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

import org.hibernate.annotations.GenericGenerator;


/**
 * 商品类别表
 * @author Zeral
 *
 */
@Entity
@Table(name = "product_type")
public class ProductType implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private String productTypeId;
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
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "product_type_id", unique = true, nullable = false, length = 32)
	public String getProductTypeId() {
		return this.productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	@Column(name = "parent_id", nullable = false)
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "product_type_name", nullable = false, length = 20)
	public String getProductTypeName() {
		return this.productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	@Column(name = "is_Delete", nullable = false)
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "productType")
	public Set<ProductInfo> getProductInfos() {
		return this.productInfos;
	}

	public void setProductInfos(Set<ProductInfo> productInfos) {
		this.productInfos = productInfos;
	}

}