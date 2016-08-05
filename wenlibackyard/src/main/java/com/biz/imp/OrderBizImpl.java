package com.biz.imp;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bean.PageBean;
import com.biz.IOrderBiz;
import com.po.Orderdetail;
import com.po.Ordermain;
import com.service.dao.DaoService;

@Service("OrderBizImpl")
public class OrderBizImpl implements IOrderBiz {
	private static final Logger log = Logger.getLogger(OrderBizImpl.class);
	@Resource(name="DaoService")
	private DaoService daos;

	@Override
	public boolean saveDetail(Orderdetail orderdetail) {
		log.info("save orderdetail"+ orderdetail.toString() +"");
		try {
			daos.getOrderdetailDAO().save(orderdetail);
			log.info("save orderdetail"+ orderdetail.toString() +" success");
			return true;
		} catch (Exception e) {
			log.error("save orderdetail"+ orderdetail.toString() +" failed", e);
			return false;
		}
	}

	@Override
	public boolean saveMain(Ordermain ordermain) {
		log.info("save ordermain"+ ordermain.toString() +"");
		try {
			daos.getOrdermainDAO().save(ordermain);
			log.info("save ordermain"+ ordermain.toString() +" success");
			return true;
		} catch (Exception e) {
			log.error("save ordermain"+ ordermain.toString() +" failed", e);
			return false;
		}
	}

	@Override
	public Orderdetail findDetail(Integer orderDetailId) throws Exception {
		Orderdetail orderdetail;
		try {
			orderdetail = daos.getOrderdetailDAO().findById(orderDetailId);
			if(orderdetail != null) {
				return orderdetail;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException("findDetail exception "+ orderDetailId.toString() +"", e);
		}
		
	}

	@Override
	public Ordermain findNewMain() throws Exception  {
		try {
			List<Object[]> objlst = daos.getOrdermainDAO().findBySQL("SELECT MAX(orderMainId) FROM orderMain;");
			int id = -1;
			if(objlst != null) id =  (int) objlst.get(0)[0]; 
			Ordermain orderMain = daos.getOrdermainDAO().findById(id);
			if(orderMain != null) {
				return orderMain;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException("findNewMain exception", e);
		}
	}

	@Override
	public List<Ordermain> findAllMain(String userId, PageBean pageBean) throws Exception  {
		try {
			List<Ordermain> mainlst = daos.getOrdermainDAO().findAllByUser(userId, pageBean);
			return mainlst;
		} catch (Exception e) {
			throw new RuntimeException("findAllMain exception", e);
		}
	}

}
