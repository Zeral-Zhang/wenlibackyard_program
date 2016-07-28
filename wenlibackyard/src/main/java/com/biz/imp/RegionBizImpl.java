package com.biz.imp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.action.IRegionAction;
import com.biz.IRegionBiz;
import com.po.Regions;
import com.service.dao.DaoService;

@Service("RegionBizImpl")
public class RegionBizImpl implements IRegionBiz {
	@Resource(name="DaoService")
	private DaoService daos;
	
	public DaoService getDaos() {
		return daos;
	}

	public void setDaos(DaoService daos) {
		this.daos = daos;
	}

	@Override
	public List<Regions> findProvince() {
		List<Regions> provinceslst = daos.getRegionDAO().findByLevel(0);
		if(provinceslst != null) {
			return provinceslst;
		}
		return null;
	}
	
	@Override
	public List<Regions> findCitys(Integer fcode) {
		List<Regions> citylst = daos.getRegionDAO().findByPCode(fcode);
		if(citylst != null) {
			return citylst;
		}
		return null;
	}


}
