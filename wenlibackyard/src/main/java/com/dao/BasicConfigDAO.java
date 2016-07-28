package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.po.BasicConfig;


/**
 * BasicConfigDAO类
 * 
 * @author Say
 * @date 2015年7月7日
 */
@Transactional
@Service("BasicConfigDAO")
public class BasicConfigDAO extends BaseDAO<BasicConfig, String> {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}
	/**
	 * 根据basicConfigId查询一条记录出来
	 * 
	 * @author Say
	 * @date 2015年7月7日
	 * @param basicConfigId
	 * @return
	 */
	public BasicConfig findByBasicConfigId(String basicConfigId) {
		String hql = "from BasicConfig a where a.basicConfigId = ?";
		return (BasicConfig) getCurrentSession().createQuery(hql).setParameter(0, basicConfigId).uniqueResult();
	}

	/**
	 * 根据basicConfigId和unitId删除数据
	 * @author Say
	 * @date 2015年7月9日
	 * @param basicConfigId
	 * @param unitId
	 */
	public void deleteIdAndUnitId(String basicConfigId, String unitId) {
		String hql = "delete from BasicConfig where basicConfigId = ? and unitId = ?";
		executeBulk(hql, basicConfigId, unitId);
	}
	
	/**
	 * 查询平台参数
	 * @author Melody
	 * @date 2015-10-15
	 * @return
	 *
	 */
	public List<BasicConfig> findPlatformSet() {
		String hql = "from BasicConfig bc where bc.basicConfigId like ?";
		return findByHQL(hql, "max%Size");
	}

}
