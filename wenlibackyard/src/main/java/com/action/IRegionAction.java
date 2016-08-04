package com.action;

public interface IRegionAction {
	/**
	 * 初始化省份信息
	 * 
	 */
	public void initProvince();
	
	/**
	 * 根据省id查询城市信息
	 * 
	 */
	public void loadCitys();
}
