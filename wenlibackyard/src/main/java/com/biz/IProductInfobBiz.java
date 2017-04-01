package com.biz;

import java.util.List;

import com.bean.PageBean;
import com.po.OrderMain;
import com.po.ProductInfo;

public interface IProductInfobBiz extends IBaseBiz<ProductInfo> {
	
	public int findMaxPage(int rows);

	public ProductInfo findDetail(String productId);
	
	public void update(ProductInfo productInfo);

	/**
	 * 通过商品发布用户查找商品分页信息
	 */
	public List<ProductInfo> findByUserId(PageBean pageBean, String userId);

	/**
	 * 通过商品名称模糊查询
	 * @param pageBean
	 * @param name
	 * @return
	 */
	List<ProductInfo> findByNameLike(PageBean pageBean, String name);
	
	/**
	 * 通过用户查找自己购买商品的订单信息
	 * @param pageBean
	 * @param userId
	 * @return
	 */
	public List<OrderMain> findOrderMain(PageBean pageBean, String userId);

	/**
	 * 根据商品类型和名称模糊查询
	 * @param pageBean
	 * @param name
	 * @return
	 */
	List<ProductInfo> findByTypeAndNameLike(PageBean pageBean, String productTypeId, String name);

	/**
	 * 根据商品类型查询商品列表信息
	 * @param pageBean
	 * @param productTypeId
	 * @return
	 */
	List<ProductInfo> findByType(PageBean pageBean, String productTypeId);
}
