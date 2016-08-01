package com.service;

import java.util.List;

import com.po.BasicConfig;


/**
 * 系统配置信息接口类
 * 
 */
public interface IBasicConfigService {
	
	/**
	 * 保存一条系统配置信息
	 * @param basicConfig 待保存对象
	 * @throws Exception 
	 */
	void save(BasicConfig basicConfig) throws Exception;
	
	/**
	 * 根据code删除一条数据
	 * @param basicConfigId code值
	 * @throws Exception 
	 */
	void delete(String basicConfigId) throws Exception;

	/**
	 * 更新系统配置信息
	 * @param basicConfig 待更新对象
	 */
	void update(BasicConfig basicConfig) throws Exception;
	
	/**
	 * 保存或更新系统配置信息
	 * @param basicConfig 待操作对象
	 */
	void saveOrUpdate(BasicConfig basicConfig) throws Exception;
	
	/**
	 * 根据id查询数据
	 * @param id id值
	 * @return 查询出的对象
	 */
	BasicConfig findById(String id) throws Exception;
	
	
	/**
	 * 根据code查询一条数据的value值
	 * @param basicConfigId code值
	 * @return value值
	 */
	String findValueById(String basicConfigId);
	/**
	 * 是否存在
	 * @author Rambo
	 * @param basicConfigId code值
	 * @return 是否存在  true存在  false不存在
	 */
	boolean isExist(String basicConfigId);
	
	
	/**
	 * 根据code查询
	 * @param code code值
	 * @return 查询出的对象
	 */
	BasicConfig findByCode(String code);
	
	/**
	 * 根据code取得所有BasicConfig信息
	 * 注：适合一个code对应多个值的情况
	 * 
	 * @author Say
	 * @date 2015年7月17日
	 * @param code code值
	 * @return 配置信息数据集合
	 */
	List<BasicConfig> findByCodeAll(String code);
	
}
