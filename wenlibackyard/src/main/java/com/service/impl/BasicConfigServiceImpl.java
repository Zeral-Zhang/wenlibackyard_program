package com.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.biz.imp.ProductInfoBizImpl;
import com.dao.BasicConfigDAO;
import com.exception.BaseException;
import com.po.BasicConfig;
import com.service.IBasicConfigService;
import com.service.dao.DaoService;


@Service("BasicConfigService")
public class BasicConfigServiceImpl implements IBasicConfigService {
	private static final Logger log = Logger.getLogger(BasicConfigServiceImpl.class);

	@Resource(name = "DaoService")
	private DaoService daos;

	public DaoService getDaos() {
		return daos;
	}

	public void setDaos(DaoService daos) {
		this.daos = daos;
	}
	
	
	public void save(BasicConfig basicConfig) throws Exception {
		log.info("save BasicConfig "+ basicConfig.toString() +"");
		try {
			daos.getBasicConfigDAO().save(basicConfig);
			log.info("add BasicConfig "+ basicConfig.toString() +" success");
		} catch (Exception e) {
			log.error("add BasicConfig "+ basicConfig.toString() +" failed", e);
			throw new RuntimeException(e);
		}
	}

	public void delete(String basicConfigId) throws Exception {
		log.info("delete BasicConfig");
		try {
			daos.getBasicConfigDAO().deleteObject(findByCode(basicConfigId));
			log.info("delete BasicConfig success");
		} catch (Exception e) {
			log.error("delete BasicConfig failed", e);
			throw new RuntimeException(e);
		}
	}

	public void update(BasicConfig basicConfig) throws Exception {
		log.info("update BasicConfig "+ basicConfig.toString() +"");
		try {
			daos.getBasicConfigDAO().update(basicConfig);
			log.info("update BasicConfig "+ basicConfig.toString() +" success");
		} catch (Exception e) {
			log.error("update BasicConfig "+ basicConfig.toString() +" failed", e);
			throw new RuntimeException(e);
		}
	}
	
	public void saveOrUpdate(BasicConfig basicConfig) {
		log.info("saveOrUpdate BasicConfig "+ basicConfig.toString() +"");
		try {
			daos.getBasicConfigDAO().saveOrUpdate(basicConfig);
			log.info("saveOrUpdate BasicConfig "+ basicConfig.toString() +" success");
		} catch (Exception e) {
			log.error("saveOrUpdate BasicConfig "+ basicConfig.toString() +" failed", e);
			throw new RuntimeException(e);
		}
		
	}

	public BasicConfig findById(String id) throws Exception {
		return daos.getBasicConfigDAO().findById(id);
	}
	
	public String findValueById(String basicConfigId) {
		BasicConfig basicConfig = findByCode(basicConfigId);
		if(basicConfig == null){
			throw new BaseException("Not find BasicConfig by id:"+basicConfigId);
		}
		return basicConfig.getValue();
	}
	
	public boolean isExist(String basicConfigId) {
		return findByCode(basicConfigId) != null;
	}


	public BasicConfig findByCodeAndUnit(String code, String unitId) {
		BasicConfig bc = new BasicConfig();
		bc.setBasicConfigId(code);
		return daos.getBasicConfigDAO().findUnique(bc);
	}

	public BasicConfig findByCode(String code) {
		List<BasicConfig> basicConfigs = findByCodeAll(code);
		if (!CollectionUtils.isEmpty(basicConfigs)) {
			return basicConfigs.get(0);
		}
		return null;
	}

	public void deleteIdAndUnitId(String basicConfigId, String unitId) {
		daos.getBasicConfigDAO().deleteIdAndUnitId(basicConfigId, unitId);
	}
	
	public void savePlatformSet(List<BasicConfig> basicConfigs,String unitId) {
		for (BasicConfig basicConfig : basicConfigs) {
			saveOrUpdate(basicConfig);
		}
	}

	public List<BasicConfig> findPlatformSet() {
		return daos.getBasicConfigDAO().findPlatformSet();
	}

	public List<BasicConfig> findByCodeAll(String code) {
		BasicConfig bc = new BasicConfig();
		bc.setBasicConfigId(code);
		return daos.getBasicConfigDAO().findByExample(bc);
	}
}
