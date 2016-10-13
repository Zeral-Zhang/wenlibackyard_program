package com.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.po.UserInfo;

@Service("UserInfoDAO")
public class UserInfoDAO extends BaseDAO<UserInfo, String> {
	// property constants
	public static final String USER_NICK_NAME = "userNickName";
	public static final String USER_HEAD_IMG_URL = "userHeadImgUrl";


	@SuppressWarnings("unchecked")
	public List<UserInfo> findByProperty(String propertyName, Object value) {
			String queryString = "from Userinfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
	}

	public List<UserInfo> findByUserNickName(Object userNickName) {
		return findByProperty(USER_NICK_NAME, userNickName);
	}

	public List<UserInfo> findByUserHeadImgUrl(Object userHeadImgUrl) {
		return findByProperty(USER_HEAD_IMG_URL, userHeadImgUrl);
	}

} 