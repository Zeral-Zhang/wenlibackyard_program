package com.service.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.DatabaseInitDAO;
import com.dao.SchoolinfoDAO;
import com.dao.UserdetailinfoDAO;
import com.dao.UserinfoDAO;
import com.po.Userdetailinfo;

@Service("DaoService")
public class DaoService {
	@Resource(name="UserinfoDAO")
	private UserinfoDAO userdao;
	@Resource(name="UserdetailinfoDAO")
	private UserdetailinfoDAO userdetailinfoDAO;
	@Resource(name="SchoolinfoDAO")
	private SchoolinfoDAO schoolinfoDAO;
	@Resource(name="DatabaseInitDAO")
	private DatabaseInitDAO databaseInitDAO;
	
	public UserinfoDAO getUserdao() {
		return userdao;
	}

	public void setUserdao(UserinfoDAO userdao) {
		this.userdao = userdao;
	}

	public UserdetailinfoDAO getUserdetailinfoDAO() {
		return userdetailinfoDAO;
	}

	public void setUserdetailinfoDAO(UserdetailinfoDAO userdetailinfoDAO) {
		this.userdetailinfoDAO = userdetailinfoDAO;
	}

	public SchoolinfoDAO getSchoolinfoDAO() {
		return schoolinfoDAO;
	}

	public void setSchoolinfoDAO(SchoolinfoDAO schoolinfoDAO) {
		this.schoolinfoDAO = schoolinfoDAO;
	}

	public DatabaseInitDAO getDatabaseInitDAO() {
		return databaseInitDAO;
	}

	public void setDatabaseInitDAO(DatabaseInitDAO databaseInitDAO) {
		this.databaseInitDAO = databaseInitDAO;
	}
	

}
