package com.biz.imp;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bean.AccessToken;
import com.biz.IUserBiz;
import com.po.Regions;
import com.po.UserDetailInfo;
import com.po.UserInfo;
import com.service.dao.DaoService;
import com.util.HttpsUtil;
import com.util.PropertiesConfigUtil;
@Service("UserBizImpl")
public class UserBizImpl implements IUserBiz {
	private static final Logger log = Logger.getLogger(OrderBizImpl.class);
	@Resource(name = "DaoService")
	private DaoService daos;

	public UserDetailInfo findUserDetail(String openId) {
		UserDetailInfo userInfo = daos.getUserDetailInfoDAO().findByUserId(openId);
		return userInfo;
	}

	@Override
	public UserDetailInfo update(UserDetailInfo userInfo) {
		log.info("upate userinfo "+ userInfo.toString() +"");
		UserDetailInfo userDetailInfo = daos.getUserDetailInfoDAO().findByUserId(userInfo.getUserInfo());
		if(null == userDetailInfo) {
			userDetailInfo = new UserDetailInfo();
			userDetailInfo.setUserTel(userInfo.getUserTel());
			userDetailInfo.setUserAge(userInfo.getUserAge());
			userDetailInfo.setUserClass(userInfo.getUserClass());
			userDetailInfo.setUserGrade(userInfo.getUserGrade());
			userDetailInfo.setUserInfo(userInfo.getUserInfo());
			userDetailInfo.setSchoolInfo(daos.getSchoolInfoDAO().findByCode(userInfo.getSchoolInfo().getCode()));
			daos.getUserDetailInfoDAO().save(userDetailInfo);
		} else {
			userDetailInfo.setUserTel(userInfo.getUserTel());
			userDetailInfo.setUserAge(userInfo.getUserAge());
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
	
	/**
	 * 通过微信openId查找用户数据
	 * @param openId
	 * @return
	 * @throws Exception 
	 */
	public UserInfo findByOpenId(String openId) {
		AccessToken accessToken = daos.getBasicConfigDAO().getToken();
		try {
			Properties prop = PropertiesConfigUtil.getProperties("account.properties");
			if(null == accessToken || System.currentTimeMillis()/1000 > accessToken.getExpiresIn()) {
				accessToken = HttpsUtil.getAccessToken(prop.getProperty("appid"), prop.getProperty("appsecret"));
				daos.getBasicConfigDAO().setToken(accessToken);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HttpsUtil.getUserInfo(accessToken.getToken(), openId);
	}
}
