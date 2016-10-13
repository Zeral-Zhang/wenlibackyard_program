package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.po.UserDetailInfo;

@Service("UserDetailInfoDAO")
public class UserDetailInfoDAO extends BaseDAO<UserDetailInfo, Integer> {

	// property constants
	public static final String USER_TEL = "userTel";
	public static final String USER_CITY = "userCity";
	public static final String USER_PROVINCE = "userProvince";
	public static final String USER_LANGUAGE = "userLanguage";
	public static final String USER_GENDER = "userGender";
	public static final String USER_AGE = "userAge";

	@SuppressWarnings("unchecked")
	public List<UserDetailInfo> findByProperty(String propertyName, Object value) {
			String queryString = "from Userdetailinfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
	}

	public List<UserDetailInfo> findByUserTel(Object userTel) {
		return findByProperty(USER_TEL, userTel);
	}

	public List<UserDetailInfo> findByUserCity(Object userCity) {
		return findByProperty(USER_CITY, userCity);
	}

	public List<UserDetailInfo> findByUserProvince(Object userProvince) {
		return findByProperty(USER_PROVINCE, userProvince);
	}

	public List<UserDetailInfo> findByUserLanguage(Object userLanguage) {
		return findByProperty(USER_LANGUAGE, userLanguage);
	}

	public List<UserDetailInfo> findByUserGender(Object userGender) {
		return findByProperty(USER_GENDER, userGender);
	}

	public List<UserDetailInfo> findByUserAge(Object userAge) {
		return findByProperty(USER_AGE, userAge);
	}

}