package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.po.Favorite;

@Service("FavoriteDAO")
public class FavoriteDAO extends BaseDAO<Favorite, Integer> {
	// property constants
	public static final String CONTEXT = "context";

	
	@SuppressWarnings("unchecked")
	public <T> List<T> findByProperty(String propertyName, Object value) {
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