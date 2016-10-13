package com.biz.imp;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.biz.IUserBiz;
import com.po.Regions;
import com.po.SchoolInfo;
import com.po.UserDetailInfo;
import com.po.UserInfo;
import com.service.dao.DaoService;
@Service("UserBizImpl")
public class UserBizImpl implements IUserBiz {
	private static final Logger log = Logger.getLogger(OrderBizImpl.class);
	@Resource(name = "DaoService")
	private DaoService daos;

	public UserInfo findUser(String userId) throws Exception {
		try {
			UserInfo userInfo = daos.getUserdao().findById(userId);
			return userInfo;
		} catch (Exception e) {
			log.error("find userinfo failed", e);
			throw new RuntimeException("login exception");
		}
	}

	@Override
	public void update(UserInfo userInfo) throws Exception  {
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
				// 保存用户id到用户详细信息表中
				userDetailInfo.setUserInfo(oldUser);
				daos.getUserdetailinfoDAO().save(userDetailInfo);
				List<Integer> objlst = daos.getUserdetailinfoDAO().findBySQL("SELECT MAX(userDetailId) FROM userdetailinfo WHERE userId = '" + userInfo.getUserId() + "'");
				int id = -1;
				if(objlst != null) id = objlst.get(0); 
				oldUser.setUserDetailInfo(daos.getUserdetailinfoDAO().findById(id));
			} else {
				userDetailInfo.setUserTel(userInfo.getUserDetailInfo().getUserTel());
				userDetailInfo.setUserAge(userInfo.getUserDetailInfo().getUserAge());
				userDetailInfo.setUserLanguage(userInfo.getUserDetailInfo().getUserLanguage());
				userDetailInfo.setUserProvince(findNameFromCode(userInfo.getUserDetailInfo().getUserProvince()));
				userDetailInfo.setUserCity(findNameFromCode(userInfo.getUserDetailInfo().getUserCity()));
				oldUser.setUserDetailInfo(userDetailInfo);
			}
			
			SchoolInfo schoolInfo = oldUser.getUserDetailInfo().getSchoolInfo();
			if(null == schoolInfo) {
				schoolInfo = new SchoolInfo();
				schoolInfo.setCode(userInfo.getUserDetailInfo().getSchoolInfo().getCode());
				schoolInfo.setName(userInfo.getUserDetailInfo().getSchoolInfo().getName());
				schoolInfo.setPCode(userInfo.getUserDetailInfo().getSchoolInfo().getPCode());
				schoolInfo.setLevel(userInfo.getUserDetailInfo().getSchoolInfo().getLevel());
				// 保存院校id到用户详细信息中
				daos.getSchoolinfoDAO().save(schoolInfo);
				List<Integer> objlst = daos.getSchoolinfoDAO().findBySQL("SELECT MAX(schoolInfoId) FROM schoolinfo");
				int id = -1;
				if(objlst != null) id = objlst.get(0); 
				oldUser.getUserDetailInfo().setSchoolInfo(daos.getSchoolinfoDAO().findById(id));
			} else {
				schoolInfo.setCode(userInfo.getUserDetailInfo().getSchoolInfo().getCode());
				schoolInfo.setName(userInfo.getUserDetailInfo().getSchoolInfo().getName());
				schoolInfo.setPCode(userInfo.getUserDetailInfo().getSchoolInfo().getPCode());
				schoolInfo.setLevel(userInfo.getUserDetailInfo().getSchoolInfo().getLevel());
				oldUser.getUserDetailInfo().setSchoolInfo(schoolInfo);
			}
			daos.getUserdao().saveOrUpdate(oldUser);
			log.info("upate userinfo "+ userInfo.toString() +" success");
		} catch (Exception e) {
			log.error("upate userinfo "+ userInfo.toString() +" failed", e);
			throw new RuntimeException("upate userinfo "+ userInfo.toString() +" failed");
		}
	}

	public String findNameFromCode(String code) throws Exception  {
		try {
			Integer codeInt = Integer.parseInt(code);
			List<Regions> provincelst = daos.getRegionDAO().findByCode(codeInt);
			String value = null;
			if(provincelst.size() > 0) {
				value = provincelst.get(0).getName();
			}
			return value;
		} catch (NumberFormatException e) {
			log.error("findNameFromCode failed", e);
			throw new RuntimeException("getNameFromCodee userinfo", e);
		}
	}
}
