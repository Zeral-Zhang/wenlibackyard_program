package com.biz.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.biz.IOrderBiz;
import com.po.Orderdetail;
import com.po.Ordermain;
import com.service.dao.DaoService;

@Service("OrderBiz")
public class OrderBiz implements IOrderBiz {
	@Resource(name="DaoService")
	private DaoService daos;
	
	public DaoService getDaos() {
		return daos;
	}

	public void setDaos(DaoService daos) {
		this.daos = daos;
	}

	@Override
	public boolean saveDetail(Orderdetail orderdetail) {
		try {
			daos.getOrderdetailDAO().save(orderdetail);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean saveMain(Ordermain ordermain) {
		try {
			daos.getOrdermainDAO().save(ordermain);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Orderdetail findDetail(Integer orderDetailId) {
		Orderdetail orderdetail = daos.getOrderdetailDAO().findById(orderDetailId);
		if(orderdetail != null) {
			return orderdetail;
		}
		return null;
	}

	@Override
	public Ordermain findNewMain() {
		List<Object> objlst = daos.getOrdermainDAO().findByOriginalSql("SELECT MAX(orderMainId) FROM orderMain;");
		int id = -1;
		if(objlst != null) id = (Integer) objlst.get(0); 
		Ordermain orderMain = daos.getOrdermainDAO().findById(id);
		if(orderMain != null) {
			return orderMain;
		}
		return null;
	}

	@Override
	public List<Ordermain> findAllMain(String userId, int page, int rows) {
		if(page < 1) page = 1;
		if(rows < 1) rows = 5;
		List<Ordermain> mainlst = daos.getOrdermainDAO().findAllByUser(userId, page, rows);
		return mainlst;
	}

}
