package com.biz;

import java.util.List;

import com.po.Regions;

public interface IRegionBiz {
	/**
	 * 查找省份信息，用于初始化级联查询
	 * @return
	 * @throws Exception 
	 */
	public List<Regions> findProvince() throws Exception;
	
	/**
	 * 根据省份id查找城市信息
	 * @return
	 */
	public List<Regions> findCitys(Integer fcode) throws Exception ;
}
