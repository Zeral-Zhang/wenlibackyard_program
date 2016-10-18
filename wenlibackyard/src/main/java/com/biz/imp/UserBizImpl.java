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
	public void update(UserInfo userInfo) {
		log.info("upate userinfo "+ userInfo.toString() +"");
		try {
			UserInfo oldUser = daos.getUserdao().findById(userInfo.getUserId());
			UserDetailInfo userDetailInfo = oldUser.getUserDetailInfo();
			if(null == userDetailInfo) {
				userDetailInfo = new UserDetailInfo();
				userDetailInfo.setUserTel(userInfo.getUserDetailInfo().getUserTel());
				userDetailInfo.setUserAge(userInfo.getUserDetailInfo().getUserAge());
				userDetailInfo.setUserLanguage(userInfo.getUserDetailInfo().getUserLanguage());
				userDetailInfo.setUserProvince(findNameFromCode(userInfo.getUserDetailInfo().getUserProvince()));
				userDetailInfo.setUserCity(findNameFromCode(userInfo.getUserDetailInfo().getUserCity()));
				userDetailInfo.setUserClass(userInfo.getUserDetailInfo().getUserClass());
				userDetailInfo.setUserGrade(userInfo.getUserDetailInfo().getUserGrade());
				// 保存用户id到用户详细信息表中
				userDetailInfo.setUserInfo(oldUser);
				oldUser.setUserDetailInfo(userDetailInfo);
			} else {
				userDetailInfo.setUserTel(userInfo.getUserDetailInfo().getUserTel());
				userDetailInfo.setUserAge(userInfo.getUserDetailInfo().getUserAge());
				userDetailInfo.setUserLanguage(userInfo.getUserDetailInfo().getUserLanguage());
				userDetailInfo.setUserProvince(findNameFromCode(userInfo.getUserDetailInfo().getUserProvince()));
				userDetailInfo.setUserCity(findNameFromCode(userInfo.getUserDetailInfo().getUserCity()));
				userDetailInfo.setUserClass(userInfo.getUserDetailInfo().getUserClass());
				userDetailInfo.setUserGrade(userInfo.getUserDetailInfo().getUserGrade());
				oldUser.setUserDetailInfo(userDetailInfo);
			}
			oldUser.getUserDetailInfo().setSchoolInfo(daos.getSchoolInfoDAO().findByCode(userInfo.getUserDetailInfo().getSchoolInfo().getCode()));
			daos.getUserdao().saveOrUpdate(oldUser);
			log.info("upate userinfo "+ userInfo.toString() +" success");
		} catch (Exception e) {
			log.error("upate userinfo "+ userInfo.toString() +" failed", e);
		}
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
