package com.service.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.BasicConfigDAO;
import com.dao.DatabaseInitDAO;
import com.dao.OrderDetailDAO;
import com.dao.OrderMainDAO;
import com.dao.ProductInfoDAO;
import com.dao.ProductTypeDAO;
import com.dao.RegionsDAO;
import com.dao.SchoolInfoDAO;
import com.dao.UserDetailInfoDAO;
import com.dao.UserInfoDAO;

@Service("DaoService")
public class DaoService {
	@Resource(name="UserInfoDAO")
	private UserInfoDAO userdao;
	@Resource(name="UserDetailInfoDAO")
	private UserDetailInfoDAO userDetailInfoDAO;
	@Resource(name="SchoolInfoDAO")
	private SchoolInfoDAO schoolInfoDAO;
	@Resource(name="RegionsDAO")
	private RegionsDAO regionDAO;
	@Resource(name="ProductInfoDAO")
	private ProductInfoDAO productInfoDAO;
	@Resource(name="ProductTypeDAO")
	private ProductTypeDAO productTypeDAO;
	@Resource(name="OrderDetailDAO")
	private OrderDetailDAO orderDetailDAO;
	@Resource(name="OrderMainDAO")
	private OrderMainDAO orderMainDAO;
	@Resource(name="BasicConfigDAO")
	private BasicConfigDAO basicConfigDAO;
	@Resource(name="DatabaseInitDAO")
	private DatabaseInitDAO dataBaseInitDAO;
	public UserInfoDAO getUserdao() {
		return userdao;
	}

	public void setUserdao(UserInfoDAO userdao) {
		this.userdao = userdao;
	}

	public UserDetailInfoDAO getUserdetailinfoDAO() {
		return userDetailInfoDAO;
	}

	public void setUserdetailinfoDAO(UserDetailInfoDAO userDetailInfoDAO) {
		this.userDetailInfoDAO = userDetailInfoDAO;
	}

	public SchoolInfoDAO getSchoolinfoDAO() {
		return schoolInfoDAO;
	}

	public void setSchoolinfoDAO(SchoolInfoDAO schoolInfoDAO) {
		this.schoolInfoDAO = schoolInfoDAO;
	}

	public RegionsDAO getRegionDAO() {
		return regionDAO;
	}

	public void setRegionDAO(RegionsDAO regionDAO) {
		this.regionDAO = regionDAO;
	}

	public ProductInfoDAO getProductinfoDAO() {
		return productInfoDAO;
	}

	public void setProductinfoDAO(ProductInfoDAO productInfoDAO) {
		this.productInfoDAO = productInfoDAO;
	}

	public ProductTypeDAO getProducttypeDAO() {
		return productTypeDAO;
	}

	public void setProducttypeDAO(ProductTypeDAO productTypeDAO) {
		this.productTypeDAO = productTypeDAO;
	}

	public OrderDetailDAO getOrderdetailDAO() {
		return orderDetailDAO;
	}

	public void setOrderdetailDAO(OrderDetailDAO orderDetailDAO) {
		this.orderDetailDAO = orderDetailDAO;
	}

	public OrderMainDAO getOrdermainDAO() {
		return orderMainDAO;
	}

	public void setOrdermainDAO(OrderMainDAO orderMainDAO) {
		this.orderMainDAO = orderMainDAO;
	}

	public BasicConfigDAO getBasicConfigDAO() {
		return basicConfigDAO;
	}

	public void setBasicConfigDAO(BasicConfigDAO basicConfigDAO) {
		this.basicConfigDAO = basicConfigDAO;
	}

	public DatabaseInitDAO getDataBaseInitDAO() {
		return dataBaseInitDAO;
	}

	public void setDataBaseInitDAO(DatabaseInitDAO dataBaseInitDAO) {
		this.dataBaseInitDAO = dataBaseInitDAO;
	}
}
