package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.po.SchoolInfo;

@Service("SchoolInfoDAO")
public class SchoolInfoDAO extends BaseDAO<SchoolInfo, Integer> {

	// property constants
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String PCODE = "pCode";
	public static final String LEVEL = "level";


	@SuppressWarnings("unchecked")
	public List<SchoolInfo> findByProperty(String propertyName, Object value) {
			String queryString = "from SchoolInfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
	}

	public List<SchoolInfo> findByCode(Object college) {
		return findByProperty(CODE, college);
	}

	public List<SchoolInfo> findByNAME(Object department) {
		return findByProperty(NAME, department);
	}

	public List<SchoolInfo> findByPCode(Object classes) {
		return findByProperty(PCODE, classes);
	}

	public List<SchoolInfo> findByLevel(Object grade) {
		return findByProperty(LEVEL, grade);
	}
}