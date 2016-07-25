package com.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dao.BasicConfigDAO;
import com.exception.BaseException;
import com.po.BasicConfig;
import com.service.IBasicConfigService;


@Service
public class BasicConfigServiceImpl implements IBasicConfigService {

	private BasicConfigDAO basicConfigDAO;
	
	
	public void save(BasicConfig basicConfig) {
		basicConfigDAO.save(basicConfig);
	}

	public void delete(String basicConfigId) {
		basicConfigDAO.deleteObject(findByCode(basicConfigId));
	}

	public void update(BasicConfig basicConfig) {
		basicConfigDAO.update(basicConfig);
	}
	
	public void saveOrUpdate(BasicConfig basicConfig) {
		basicConfigDAO.saveOrUpdate(basicConfig);
	}

	public BasicConfig findById(String id) {
		return basicConfigDAO.findById(id);
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
		return basicConfigDAO.findUnique(bc);
	}

	public BasicConfig findByCode(String code) {
		List<BasicConfig> basicConfigs = findByCodeAll(code);
		if (!CollectionUtils.isEmpty(basicConfigs)) {
			return basicConfigs.get(0);
		}
		return null;
	}

	public void deleteIdAndUnitId(String basicConfigId, String unitId) {
		basicConfigDAO.deleteIdAndUnitId(basicConfigId, unitId);
	}
	
	public void savePlatformSet(List<BasicConfig> basicConfigs,String unitId) {
		for (BasicConfig basicConfig : basicConfigs) {
			saveOrUpdate(basicConfig);
		}
	}

	public List<BasicConfig> findPlatformSet() {
		return basicConfigDAO.findPlatformSet();
	}

	public List<BasicConfig> findByCodeAll(String code) {
		BasicConfig bc = new BasicConfig();
		bc.setBasicConfigId(code);
		return basicConfigDAO.findByExample(bc);
	}
}
