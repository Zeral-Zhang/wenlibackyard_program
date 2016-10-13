package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.po.SchoolInfo;

@Service("SchoolInfoDAO")
public class SchoolInfoDAO extends BaseDAO<SchoolInfo, Integer> {

	// property constants
	public static final String COLLEGE = "college";
	public static final String DEPARTMENT = "department";
	public static final String CLASSES = "classes";
	public static final String GRADE = "grade";


	@SuppressWarnings("unchecked")
	public List<SchoolInfo> findByProperty(String propertyName, Object value) {
			String queryString = "from Schoolinfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
	}

	public List<SchoolInfo> findByCollege(Object college) {
		return findByProperty(COLLEGE, college);
	}

	public List<SchoolInfo> findByDepartment(Object department) {
		return findByProperty(DEPARTMENT, department);
	}

	public List<SchoolInfo> findByClasses(Object classes) {
		return findByProperty(CLASSES, classes);
	}

	public List<SchoolInfo> findByGrade(Object grade) {
		return findByProperty(GRADE, grade);
	}
}