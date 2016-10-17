package com.dao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.po.SchoolInfo;

@Service("SchoolInfoDAO")
public class SchoolInfoDAO extends BaseDAO<SchoolInfo, Integer> {

	// property constants
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String _PCODE = "PCode";
	public static final String LEVEL = "level";


	@SuppressWarnings("unchecked")
	public List<SchoolInfo> findByProperty(String propertyName, Object value) {
			String queryString = "from SchoolInfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
	}

	public SchoolInfo findByCode(Object code) {
		List<SchoolInfo> schoolInfolst = findByProperty(CODE, code);
		if(CollectionUtils.isNotEmpty(schoolInfolst)) {
			return schoolInfolst.get(0);
		}
		return null;
	}

	public List<SchoolInfo> findByNAME(Object name) {
		return findByProperty(NAME, name);
	}

	public List<SchoolInfo> findByPCode(Object pCode) {
		return findByProperty(_PCODE, pCode);
	}

	public List<SchoolInfo> findByLevel(Object level) {
		return findByProperty(LEVEL, level);
	}
}