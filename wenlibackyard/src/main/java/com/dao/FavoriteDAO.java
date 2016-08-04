package com.dao;

import java.util.Date;
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

import com.po.Favorite;

@Service("FavoriteDAO")
public class FavoriteDAO extends BaseDAO<Favorite, Integer> {
	// property constants
	public static final String CONTEXT = "context";

	
	public List findByProperty(String propertyName, Object value) {
			String queryString = "from Favorite as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
	}

	public List<Favorite> findByContext(Object context) {
		return findByProperty(CONTEXT, context);
	}
}