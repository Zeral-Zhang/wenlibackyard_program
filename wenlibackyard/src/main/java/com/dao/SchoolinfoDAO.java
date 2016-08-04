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

import com.po.Schoolinfo;

@Service("SchoolinfoDAO")
public class SchoolinfoDAO extends BaseDAO<Schoolinfo, Integer> {

	// property constants
	public static final String COLLEGE = "college";
	public static final String DEPARTMENT = "department";
	public static final String CLASSES = "classes";
	public static final String GRADE = "grade";


	public List findByProperty(String propertyName, Object value) {
			String queryString = "from Schoolinfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
	}

	public List<Schoolinfo> findByCollege(Object college) {
		return findByProperty(COLLEGE, college);
	}

	public List<Schoolinfo> findByDepartment(Object department) {
		return findByProperty(DEPARTMENT, department);
	}

	public List<Schoolinfo> findByClasses(Object classes) {
		return findByProperty(CLASSES, classes);
	}

	public List<Schoolinfo> findByGrade(Object grade) {
		return findByProperty(GRADE, grade);
	}
}