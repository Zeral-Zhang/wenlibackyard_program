package com.biz.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.biz.IRegionBiz;
import com.po.Regions;
import com.service.dao.DaoService;

@Service("RegionBizImpl")
public class RegionBizImpl implements IRegionBiz {
	@Resource(name="DaoService")
	private DaoService daos;
	
	@Override
	public List<Regions> findProvince() {
		try {
			List<Regions> provinceslst = daos.getRegionDAO().findByLevel(0);
			if(provinceslst != null) {
				return provinceslst;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException("findProvince exception", e);
		}
	}
	
	@Override
	public List<Regions> findCitys(Integer fcode) {
		try {
			List<Regions> citylst = daos.getRegionDAO().findByPCode(fcode);
			if(citylst != null) {
				return citylst;
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException("findCitys exception", e);
		}
	}


}
