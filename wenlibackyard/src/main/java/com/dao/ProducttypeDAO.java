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

import com.po.Producttype;

@Service("ProducttypeDAO")
public class ProducttypeDAO extends BaseDAO<Producttype, Integer> {
	// property constants
	public static final String PARENT_ID = "parentId";
	public static final String PRODUCT_TYPE_NAME = "productTypeName";
	public static final String IS_DELETE = "isDelete";
	public static final String CONTEXT = "context";
	
	public List findByProperty(String propertyName, Object value) {
			String queryString = "from Producttype as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
	}

	public List<Producttype> findByParentId(Object parentId) {
		return findByProperty(PARENT_ID, parentId);
	}

	public List<Producttype> findByProductTypeName(Object productTypeName) {
		return findByProperty(PRODUCT_TYPE_NAME, productTypeName);
	}

	public List<Producttype> findByIsDelete(Object isDelete) {
		return findByProperty(IS_DELETE, isDelete);
	}

	public List<Producttype> findByContext(Object context) {
		return findByProperty(CONTEXT, context);
	}

}