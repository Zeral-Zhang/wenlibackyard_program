package com.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bean.AccessToken;
import com.constant.WenlibackyardConstant;
import com.po.BasicConfig;


/**
 * BasicConfigDAO类
 * 
 * @author Say
 * @date 2015年7月7日
 */
@Service("BasicConfigDAO")
public class BasicConfigDAO extends BaseDAO<BasicConfig, String> {
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

	public AccessToken getToken() {
		AccessToken accessToken = null;
		BasicConfig token = findByBasicConfigId(WenlibackyardConstant.ACCESS_TOKEN);
		if(null != token) {
			accessToken = new AccessToken();
			accessToken.setToken(token.getValue());
			accessToken.setExpiresIn(Long.parseLong(token.getName()));
		}
		return accessToken;
	}
	
	public void setToken(AccessToken token) throws Exception {
		if(null != token) {
			BasicConfig config = findByBasicConfigId(WenlibackyardConstant.ACCESS_TOKEN);
			if(null == config) {
				config = new BasicConfig(WenlibackyardConstant.ACCESS_TOKEN, String.valueOf(System.currentTimeMillis()/1000+token.getExpiresIn()), token.getToken());
			} else {
				config.setName(String.valueOf(System.currentTimeMillis()/1000+token.getExpiresIn()));
				config.setValue(token.getToken());
			}
			saveOrUpdate(config);
		}
	}

}
