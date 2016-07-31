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

import com.po.Regions;

@Service("RegionsDAO")
public class RegionsDAO extends BaseDAO<Regions, Integer> {
	// property constants
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String _PCODE = "PCode";
	public static final String LEVEL = "level";

	public List findByProperty(String propertyName, Object value) {
			String queryString = "from Regions as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
	}

	public List<Regions> findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List<Regions> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Regions> findByPCode(Object PCode) {
		return findByProperty(_PCODE, PCode);
	}

	public List<Regions> findByLevel(Object level) {
		return findByProperty(LEVEL, level);
	}
	
}