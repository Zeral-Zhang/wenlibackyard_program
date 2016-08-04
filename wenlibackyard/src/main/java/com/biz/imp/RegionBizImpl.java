package com.biz.imp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.action.IRegionAction;
import com.biz.IRegionBiz;
import com.po.Regions;
import com.service.dao.DaoService;

@Service("RegionBizImpl")
public class RegionBizImpl implements IRegionBiz {
	private static final Logger log = Logger.getLogger(OrderBizImpl.class);
	@Resource(name="DaoService")
	private DaoService daos;
	
	@Override
	public List<Regions> findProvince() throws Exception  {
		try {
			List<Regions> provinceslst = daos.getRegionDAO().findByLevel(0);
			if(provinceslst != null) {
				return provinceslst;
			}
			return null;
		} catch (Exception e) {
			log.error("findProvince exception", e);
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Regions> findCitys(Integer fcode) throws Exception  {
		try {
			List<Regions> citylst = daos.getRegionDAO().findByPCode(fcode);
			if(citylst != null) {
				return citylst;
			}
			return null;
		} catch (Exception e) {
			log.error("findCitys exception", e);
			throw new RuntimeException(e);
		}
	}


}
