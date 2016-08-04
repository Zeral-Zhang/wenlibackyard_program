package com.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.po.Orderdetail;

@Service("OrderdetailDAO")
public class OrderdetailDAO extends BaseDAO<Orderdetail, Integer> {
	
	// property constants
	public static final String NUM = "num";
	public static final String SUM_PRICE = "sumPrice";


	public List findByProperty(String propertyName, Object value) {
			String queryString = "from Orderdetail as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
	}
	
	public List<Orderdetail> findByNum(Object num) {
		return findByProperty(NUM, num);
	}

	public List<Orderdetail> findBySumPrice(Object sumPrice) {
		return findByProperty(SUM_PRICE, sumPrice);
	}

}