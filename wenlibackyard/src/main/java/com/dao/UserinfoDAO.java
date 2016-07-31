package com.dao;

import java.util.List;
import java.util.Set;

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

import com.po.Userinfo;

@Service("UserinfoDAO")
public class UserinfoDAO extends BaseDAO<Userinfo, String> {
	// property constants
	public static final String USER_NICK_NAME = "userNickName";
	public static final String USER_HEAD_IMG_URL = "userHeadImgUrl";


	public List findByProperty(String propertyName, Object value) {
			String queryString = "from Userinfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
	}

	public List<Userinfo> findByUserNickName(Object userNickName) {
		return findByProperty(USER_NICK_NAME, userNickName);
	}

	public List<Userinfo> findByUserHeadImgUrl(Object userHeadImgUrl) {
		return findByProperty(USER_HEAD_IMG_URL, userHeadImgUrl);
	}

} 