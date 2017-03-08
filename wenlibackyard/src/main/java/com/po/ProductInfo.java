package com.po;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.GenericGenerator;

/**
 * 商品信息表
 * @author Zeral
 *
 */
@Entity
@Table(name = "product_info")
public class ProductInfo implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;	
	// Fields
	
	private String productId;
	/**
	 * 商品类型
	 */
	private ProductType productType;
	/**
	 * 用户id
	 */
	private String userInfoId;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 商标
	 */
	private String brand;
	/**
	 * 描述
	 */
	private String context;
	/**
	 * 图片
	 */
	private String imgs;
	/**
	 * 价格
	 */
	private Float price;
	/**
	 * 数量
	 */
	private Integer number;
	/**
	 * 购买日期
	 */
	private Date buyDate;
	/**
	 * 发布日期
	 */
	private Date pbDate;
	/**
	 * 状态
	 */
	private Integer state;
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>(0);
	private Set<Favorite> favorites = new HashSet<Favorite>(0);
	
	private UserInfo userInfo;
	/**
	 * 临时对象，转换图片地址
	 */
	private List<String> fileSrcs;
	
	// Constructors

	/** default constructor */
	public ProductInfo() {
	}

	// Property accessors
	@Id
	@GeneratedValue(generator = "paymengtableGenerator")
 	@GenericGenerator(name = "paymengtableGenerator", strategy = "uuid")
	@Column(name = "product_id", unique = true, nullable = false, length = 32)
	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_type_id", nullable = false)
	public ProductType getProductType() {
		return this.productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	@Column(name = "user_id", nullable = false)
	public String getUserInfoId() {
		return this.userInfoId;
	}

	public void setUserInfoId(String userInfoId) {
		this.userInfoId = userInfoId;
	}

	@Column(name = "product_name", nullable = false, length = 20)
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "brand", length = 20)
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "context", length = 65535)
	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Column(name = "imgs", nullable = false, length = 65535)
	public String getImgs() {
		return this.imgs;
	}

	public void setImgs(String imgs) {
		this.fileSrcs = Arrays.asList(imgs.substring(0, imgs.length()-1).split(":"));
		this.imgs = imgs;
	}

	@Column(name = "price", nullable = false, precision = 7, scale = 2)
	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Column(name = "number", nullable = false)
	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "buy_date", length = 10)
	public Date getBuyDate() {
		return this.buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "pb_date", nullable = false, length = 10)
	public Date getPbDate() {
		return this.pbDate;
	}

	public void setPbDate(Date pbDate) {
		this.pbDate = pbDate;
	}

	@Column(name = "state", nullable = false)
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "productInfo")
	public Set<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "productInfo")
	public Set<Favorite> getFavorites() {
		return this.favorites;
	}

	public void setFavorites(Set<Favorite> favorites) {
		this.favorites = favorites;
	}

	@Transient
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Transient
	public List<String> getFileSrcs() {
		return fileSrcs;
	}

	public void setFileSrcs(List<String> fileSrcs) {
		StringBuffer stringBuffer = new StringBuffer();
		if (CollectionUtils.isNotEmpty(fileSrcs)) {
			for (String src : fileSrcs) {
				stringBuffer.append(src).append(":");
			}
			this.setImgs(stringBuffer.toString());
		}
	}
	
	
}