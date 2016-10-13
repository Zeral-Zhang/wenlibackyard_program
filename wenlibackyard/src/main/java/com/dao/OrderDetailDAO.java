package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.po.OrderDetail;

@Service("OrderDetailDAO")
public class OrderDetailDAO extends BaseDAO<OrderDetail, Integer> {
	
	// property constants
	public static final String NUM = "num";
	public static final String SUM_PRICE = "sumPrice";


	@SuppressWarnings("unchecked")
	public <T> List<T> findByProperty(String propertyName, Object value) {
			String queryString = "from Orderdetail as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
	}
	
	public List<OrderDetail> findByNum(Object num) {
		return findByProperty(NUM, num);
	}

	public List<OrderDetail> findBySumPrice(Object sumPrice) {
		return findByProperty(SUM_PRICE, sumPrice);
	}

}