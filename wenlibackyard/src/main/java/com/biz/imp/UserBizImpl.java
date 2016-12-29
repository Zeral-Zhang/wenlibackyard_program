package com.biz.imp;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.biz.IUserBiz;
import com.po.Regions;
import com.po.UserDetailInfo;
import com.po.UserInfo;
import com.service.dao.DaoService;
@Service("UserBizImpl")
public class UserBizImpl implements IUserBiz {
	private static final Logger log = Logger.getLogger(OrderBizImpl.class);
	@Resource(name = "DaoService")
	private DaoService daos;

	public UserInfo findUser(String userId) {
		try {
			UserInfo userInfo = daos.getUserdao().findById(userId);
			return userInfo;
		} catch (Exception e) {
			log.error("find userinfo failed", e);
			throw new RuntimeException("login exception");
		}
	}

	@Override
	public UserDetailInfo update(UserDetailInfo userInfo) {
		log.info("upate userinfo "+ userInfo.toString() +"");
		UserDetailInfo userDetailInfo = daos.getUserDetailInfoDAO().findByUserId(userInfo.getUserInfo().getUserId());
		if(null == userDetailInfo) {
			userDetailInfo = new UserDetailInfo();
			userDetailInfo.setUserTel(userInfo.getUserTel());
			userDetailInfo.setUserAge(userInfo.getUserAge());
			userDetailInfo.setUserLanguage(userInfo.getUserLanguage());
			userDetailInfo.setUserProvince(findNameFromCode(userInfo.getUserProvince()));
			userDetailInfo.setUserCity(findNameFromCode(userInfo.getUserCity()));
			userDetailInfo.setUserClass(userInfo.getUserClass());
			userDetailInfo.setUserGrade(userInfo.getUserGrade());
			userDetailInfo.setUserInfo(userInfo.getUserInfo());
			userDetailInfo.setSchoolInfo(daos.getSchoolInfoDAO().findByCode(userInfo.getSchoolInfo().getCode()));
			daos.getUserDetailInfoDAO().save(userDetailInfo);
		} else {
			userDetailInfo.setUserTel(userInfo.getUserTel());
			userDetailInfo.setUserAge(userInfo.getUserAge());
			userDetailInfo.setUserLanguage(userInfo.getUserLanguage());
			userDetailInfo.setUserProvince(findNameFromCode(userInfo.getUserProvince()));
			userDetailInfo.setUserCity(findNameFromCode(userInfo.getUserCity()));
			userDetailInfo.setUserClass(userInfo.getUserClass());
			userDetailInfo.setUserGrade(userInfo.getUserGrade());
			userDetailInfo.setUserInfo(userInfo.getUserInfo());
			userDetailInfo.setSchoolInfo(daos.getSchoolInfoDAO().findByCode(userInfo.getSchoolInfo().getCode()));
			daos.getUserDetailInfoDAO().update(userDetailInfo);
		}
		return userDetailInfo;
	}

	public String findNameFromCode(String code) {
		Integer codeInt = Integer.parseInt(code);
		List<Regions> provincelst = daos.getRegionDAO().findByCode(codeInt);
		String value = null;
		if(provincelst.size() > 0) {
			value = provincelst.get(0).getName();
		}
		return value;
	}
}
