package com.biz.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.biz.IUserBiz;
import com.po.Schoolinfo;
import com.po.Userdetailinfo;
import com.po.Userinfo;
import com.service.dao.DaoService;

@Service("UserBiz")
public class UserBizImpl implements IUserBiz {
	@Resource(name = "DaoService")
	private DaoService daos;

	public DaoService getDaos() {
		return daos;
	}

	public void setDaos(DaoService daos) {
		this.daos = daos;
	}

	public Userinfo login(String userId) {
		Userinfo userInfo = daos.getUserdao().findById(userId);
		return userInfo;
	}

	public void update(Userinfo userInfo) {
		Userinfo oldUser = daos.getUserdao().findById(userInfo.getUserId());
		Userdetailinfo userdetailinfo = oldUser.getUserdetailinfo();
		if(userdetailinfo == null) {
			userdetailinfo = new Userdetailinfo();
			userdetailinfo.setUserTel(userInfo.getUserdetailinfo().getUserTel());
			userdetailinfo.setUserAge(userInfo.getUserdetailinfo().getUserAge());
			daos.getUserdetailinfoDAO().save(userdetailinfo);
			List<Object> objlst = daos.getUserdetailinfoDAO().findByOriginalSql("select LAST_INSERT_ID()");
			int id = -1;
			if(objlst != null) id = (Integer) objlst.get(0); 
			oldUser.setUserdetailinfo(daos.getUserdetailinfoDAO().findById(id));
		} else {
			userdetailinfo.setUserTel(userInfo.getUserdetailinfo().getUserTel());
			userdetailinfo.setUserAge(userInfo.getUserdetailinfo().getUserAge());
			oldUser.setUserdetailinfo(userdetailinfo);
		}
		
		Schoolinfo schoolinfo = oldUser.getUserdetailinfo().getSchoolinfo();
		if(schoolinfo == null) {
			schoolinfo = new Schoolinfo();
			schoolinfo.setCollege(userInfo.getUserdetailinfo().getSchoolinfo().getCollege());
			schoolinfo.setDepartment(userInfo.getUserdetailinfo().getSchoolinfo().getDepartment());
			schoolinfo.setGrade(userInfo.getUserdetailinfo().getSchoolinfo().getGrade());
			schoolinfo.setClasses(userInfo.getUserdetailinfo().getSchoolinfo().getClasses());
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
	}

}
