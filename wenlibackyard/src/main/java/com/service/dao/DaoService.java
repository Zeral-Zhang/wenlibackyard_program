package com.service.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.BasicConfigDAO;
import com.dao.DatabaseInitDAO;
import com.dao.OrderdetailDAO;
import com.dao.OrdermainDAO;
import com.dao.ProductinfoDAO;
import com.dao.ProducttypeDAO;
import com.dao.RegionsDAO;
import com.dao.SchoolinfoDAO;
import com.dao.UserdetailinfoDAO;
import com.dao.UserinfoDAO;

@Service("DaoService")
public class DaoService {
	@Resource(name="UserinfoDAO")
	private UserinfoDAO userdao;
	@Resource(name="UserdetailinfoDAO")
	private UserdetailinfoDAO userdetailinfoDAO;
	@Resource(name="SchoolinfoDAO")
	private SchoolinfoDAO schoolinfoDAO;
	@Resource(name="RegionsDAO")
	private RegionsDAO regionDAO;
	@Resource(name="ProductinfoDAO")
	private ProductinfoDAO productinfoDAO;
	@Resource(name="ProducttypeDAO")
	private ProducttypeDAO producttypeDAO;
	@Resource(name="OrderdetailDAO")
	private OrderdetailDAO orderdetailDAO;
	@Resource(name="OrdermainDAO")
	private OrdermainDAO ordermainDAO;
	@Resource(name="BasicConfigDAO")
	private BasicConfigDAO basicConfigDAO;
	@Resource(name="DatabaseInitDAO")
	private DatabaseInitDAO dataBaseInitDAO;
	public UserinfoDAO getUserdao() {
		return userdao;
	}

	public void setUserdao(UserinfoDAO userdao) {
		this.userdao = userdao;
	}

	public UserdetailinfoDAO getUserdetailinfoDAO() {
		return userdetailinfoDAO;
	}

	public void setUserdetailinfoDAO(UserdetailinfoDAO userdetailinfoDAO) {
		this.userdetailinfoDAO = userdetailinfoDAO;
	}

	public SchoolinfoDAO getSchoolinfoDAO() {
		return schoolinfoDAO;
	}

	public void setSchoolinfoDAO(SchoolinfoDAO schoolinfoDAO) {
		this.schoolinfoDAO = schoolinfoDAO;
	}

	public RegionsDAO getRegionDAO() {
		return regionDAO;
	}

	public void setRegionDAO(RegionsDAO regionDAO) {
		this.regionDAO = regionDAO;
	}

	public ProductinfoDAO getProductinfoDAO() {
		return productinfoDAO;
	}

	public void setProductinfoDAO(ProductinfoDAO productinfoDAO) {
		this.productinfoDAO = productinfoDAO;
	}

	public ProducttypeDAO getProducttypeDAO() {
		return producttypeDAO;
	}

	public void setProducttypeDAO(ProducttypeDAO producttypeDAO) {
		this.producttypeDAO = producttypeDAO;
	}

	public OrderdetailDAO getOrderdetailDAO() {
		return orderdetailDAO;
	}

	public void setOrderdetailDAO(OrderdetailDAO orderdetailDAO) {
		this.orderdetailDAO = orderdetailDAO;
	}

	public OrdermainDAO getOrdermainDAO() {
		return ordermainDAO;
	}

	public void setOrdermainDAO(OrdermainDAO ordermainDAO) {
		this.ordermainDAO = ordermainDAO;
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
