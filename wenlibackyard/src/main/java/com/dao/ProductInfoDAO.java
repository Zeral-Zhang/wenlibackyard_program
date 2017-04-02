package com.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;

import com.bean.PageBean;
import com.po.ProductInfo;

@Service("ProductInfoDAO")
public class ProductInfoDAO extends BaseDAO<ProductInfo, String> {
	// property constants
	public static final String PRODUCT_NAME = "productName";
	public static final String BRAND = "brand";
	public static final String CONTEXT = "context";
	public static final String IMGS = "imgs";
	public static final String PRICE = "price";
	public static final String NUMBER = "number";
	public static final String STATE = "state";

	
	/**
	 * 获取最大页数
	 * */
	public int findMaxPage(int rows){
		int maxrow=0;
		int maxpage=0;
		//获取总记录数
		maxrow=countAll();
		if(maxrow==0){
			maxpage=1;
		}else{
			maxpage=maxrow%rows==0?maxrow/rows:maxrow/rows+1;
		}
		
		return maxpage;
		
	}


	/**
	 * 根据商品发布用户的学院查询商品列表信息
	 * @param pageBean
	 * @param schoolInfoId
	 * @return
	 */
	public List<ProductInfo> findByUserSchoolInfoId(PageBean pageBean, String schoolInfoId) {
		String sql = "select product.* from product_info product LEFT JOIN user_detail_info detail ON product.user_id = detail.user_id " +
				"LEFT JOIN school_info school ON school.school_info_id = detail.school_info_id " +
				"WHERE school.school_info_id IN (SELECT school_info_id FROM school_info WHERE school_info.p_code = (SELECT school_info.code from school_info WHERE school_info.school_info_id=?)) ORDER BY product.pb_date LIMIT ?,?";
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		query.setParameter(0, schoolInfoId)
		.setParameter(1, pageBean.getOffset())
		.setParameter(2, pageBean.getPageSize());
		query.addEntity(ProductInfo.class);
		@SuppressWarnings("unchecked")
		List<ProductInfo> results = query.list();
		return results;
	}
	
}