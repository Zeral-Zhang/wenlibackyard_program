package com.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.PageBean;
import com.po.Ordermain;

@Service("OrdermainDAO")
public class OrdermainDAO extends BaseDAO<Ordermain, Integer> {
	// property constants
	public static final String STATE = "state";
	public static final String SUM_PRICE = "sumPrice";
	public static final String CONTEXT = "context";


	public List findByProperty(String propertyName, Object value) {
			String queryString = "from Ordermain as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
	}

	public List<Ordermain> findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List<Ordermain> findBySumPrice(Object sumPrice) {
		return findByProperty(SUM_PRICE, sumPrice);
	}

	public List<Ordermain> findByContext(Object context) {
		return findByProperty(CONTEXT, context);
	}
	
	public List<Ordermain> findAllByUser(String userId, PageBean pageBean) {
		String hql="from Ordermain o where o.userinfo.userId = ? order by o.confirmDate desc";
		Query query=getCurrentSession().createQuery(hql).setParameter(0, userId);
		query.setFirstResult(pageBean.getOffset());
		query.setMaxResults(pageBean.getRows());
		return query.list();
	}

}