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

import com.po.Userdetailinfo;

@Service("UserdetailinfoDAO")
public class UserdetailinfoDAO extends BaseDAO<Userdetailinfo, Integer> {

	// property constants
	public static final String USER_TEL = "userTel";
	public static final String USER_CITY = "userCity";
	public static final String USER_PROVINCE = "userProvince";
	public static final String USER_LANGUAGE = "userLanguage";
	public static final String USER_GENDER = "userGender";
	public static final String USER_AGE = "userAge";

	public List findByProperty(String propertyName, Object value) {
			String queryString = "from Userdetailinfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
	}

	public List<Userdetailinfo> findByUserTel(Object userTel) {
		return findByProperty(USER_TEL, userTel);
	}

	public List<Userdetailinfo> findByUserCity(Object userCity) {
		return findByProperty(USER_CITY, userCity);
	}

	public List<Userdetailinfo> findByUserProvince(Object userProvince) {
		return findByProperty(USER_PROVINCE, userProvince);
	}

	public List<Userdetailinfo> findByUserLanguage(Object userLanguage) {
		return findByProperty(USER_LANGUAGE, userLanguage);
	}

	public List<Userdetailinfo> findByUserGender(Object userGender) {
		return findByProperty(USER_GENDER, userGender);
	}

	public List<Userdetailinfo> findByUserAge(Object userAge) {
		return findByProperty(USER_AGE, userAge);
	}

}