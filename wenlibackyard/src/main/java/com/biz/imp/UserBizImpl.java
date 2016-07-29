package com.biz.imp;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.biz.IUserBiz;
import com.po.Regions;
import com.po.Schoolinfo;
import com.po.Userdetailinfo;
import com.po.Userinfo;
import com.service.dao.DaoService;
@Service("UserBizImpl")
public class UserBizImpl implements IUserBiz {
	private static final Logger log = Logger.getLogger(OrderBizImpl.class);
	@Resource(name = "DaoService")
	private DaoService daos;

	public Userinfo login(String userId) {
		try {
			Userinfo userInfo = daos.getUserdao().findById(userId);
			return userInfo;
		} catch (Exception e) {
			log.error("login exception", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Userinfo userInfo) {
		log.info("upate userinfo "+ userInfo.toString() +"");
		try {
			Userinfo oldUser = daos.getUserdao().findById(userInfo.getUserId());
			Userdetailinfo userdetailinfo = oldUser.getUserdetailinfo();
			if(userdetailinfo == null) {
				userdetailinfo = new Userdetailinfo();
				userdetailinfo.setUserTel(userInfo.getUserdetailinfo().getUserTel());
				userdetailinfo.setUserAge(userInfo.getUserdetailinfo().getUserAge());
				userdetailinfo.setUserLanguage(userInfo.getUserdetailinfo().getUserLanguage());
				userdetailinfo.setUserProvince(getNameFromCode(userInfo.getUserdetailinfo().getUserProvince()));
				userdetailinfo.setUserCity(getNameFromCode(userInfo.getUserdetailinfo().getUserCity()));
				// 保存用户id到用户详细信息表中
				userdetailinfo.setUserinfo(oldUser);
				daos.getUserdetailinfoDAO().save(userdetailinfo);
				List<Object> objlst = daos.getUserdetailinfoDAO().findByOriginalSql("SELECT MAX(userDetailId) FROM userdetailinfo");
				int id = -1;
				if(objlst != null) id = (Integer) objlst.get(0); 
				oldUser.setUserdetailinfo(daos.getUserdetailinfoDAO().findById(id));
			} else {
				userdetailinfo.setUserTel(userInfo.getUserdetailinfo().getUserTel());
				userdetailinfo.setUserAge(userInfo.getUserdetailinfo().getUserAge());
				userdetailinfo.setUserLanguage(userInfo.getUserdetailinfo().getUserLanguage());
				userdetailinfo.setUserProvince(getNameFromCode(userInfo.getUserdetailinfo().getUserProvince()));
				userdetailinfo.setUserCity(getNameFromCode(userInfo.getUserdetailinfo().getUserCity()));
				oldUser.setUserdetailinfo(userdetailinfo);
			}
			
			Schoolinfo schoolinfo = oldUser.getUserdetailinfo().getSchoolinfo();
			if(schoolinfo == null) {
				schoolinfo = new Schoolinfo();
				schoolinfo.setCollege(userInfo.getUserdetailinfo().getSchoolinfo().getCollege());
				schoolinfo.setDepartment(userInfo.getUserdetailinfo().getSchoolinfo().getDepartment());
				schoolinfo.setGrade(userInfo.getUserdetailinfo().getSchoolinfo().getGrade());
				schoolinfo.setClasses(userInfo.getUserdetailinfo().getSchoolinfo().getClasses());
				// 保存院校id到用户详细信息中
				daos.getSchoolinfoDAO().save(schoolinfo);
				List<Object> objlst = daos.getSchoolinfoDAO().findByOriginalSql("SELECT MAX(schoolInfoId) FROM schoolinfo");
				int id = -1;
				if(objlst != null) id = (Integer) objlst.get(0); 
				oldUser.getUserdetailinfo().setSchoolinfo(daos.getSchoolinfoDAO().findById(id));
			} else {
				schoolinfo.setCollege(userInfo.getUserdetailinfo().getSchoolinfo().getCollege());
				schoolinfo.setDepartment(userInfo.getUserdetailinfo().getSchoolinfo().getDepartment());
				schoolinfo.setGrade(userInfo.getUserdetailinfo().getSchoolinfo().getGrade());
				schoolinfo.setClasses(userInfo.getUserdetailinfo().getSchoolinfo().getClasses());
				oldUser.getUserdetailinfo().setSchoolinfo(schoolinfo);
			}
			daos.getUserdao().attachDirty(oldUser);
			log.info("upate userinfo "+ userInfo.toString() +" success");
		} catch (Exception e) {
			log.error("upate userinfo "+ userInfo.toString() +" failed", e);
			throw new RuntimeException(e);
		}
	}

	public String getNameFromCode(String code) {
		try {
			Integer codeInt = Integer.parseInt(code);
			List<Regions> provincelst = daos.getRegionDAO().findByCode(codeInt);
			String value = null;
			if(provincelst.size() > 0) {
				value = provincelst.get(0).getName();
			}
			return value;
		} catch (NumberFormatException e) {
			log.error("getNameFromCodee userinfo", e);
			throw new RuntimeException(e);
		}
	}
}
