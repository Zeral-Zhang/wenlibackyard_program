package com.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.po.BasicConfig;
import com.po.Productinfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * Productinfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.po.Productinfo
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Service("ProductinfoDAO")
public class ProductinfoDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ProductinfoDAO.class);
	// property constants
	public static final String PRODUCT_NAME = "productName";
	public static final String BRAND = "brand";
	public static final String CONTEXT = "context";
	public static final String IMGS = "imgs";
	public static final String PRICE = "price";
	public static final String NUMBER = "number";
	public static final String STATE = "state";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Productinfo transientInstance) {
		log.debug("saving Productinfo instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Productinfo persistentInstance) {
		log.debug("deleting Productinfo instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Productinfo findById(java.lang.Integer id) {
		log.debug("getting Productinfo instance with id: " + id);
		try {
			Productinfo instance = (Productinfo) getCurrentSession().get(
					"com.po.Productinfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Productinfo> findByExample(Productinfo instance) {
		log.debug("finding Productinfo instance by example");
		try {
			List<Productinfo> results = (List<Productinfo>) getCurrentSession()
					.createCriteria("com.po.Productinfo").add(create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Productinfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Productinfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Productinfo> findByProductName(Object productName) {
		return findByProperty(PRODUCT_NAME, productName);
	}

	public List<Productinfo> findByBrand(Object brand) {
		return findByProperty(BRAND, brand);
	}

	public List<Productinfo> findByContext(Object context) {
		return findByProperty(CONTEXT, context);
	}

	public List<Productinfo> findByImgs(Object imgs) {
		return findByProperty(IMGS, imgs);
	}

	public List<Productinfo> findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List<Productinfo> findByNumber(Object number) {
		return findByProperty(NUMBER, number);
	}

	public List<Productinfo> findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findAll() {
		log.debug("finding all Productinfo instances");
		try {
			String queryString = "from Productinfo";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * 根据发布日期逆序分页
	 * */
	public List<Productinfo> findAll(int page,int rows){
		String hql="from Productinfo where 1=1 order by pbdate desc";
		Query query=getCurrentSession().createQuery(hql);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		return query.list();
	}
	
	/**
	 * 获取最大页数
	 * */
	public int findMaxPage(int rows){
		String hql="select count(productId) from Productinfo";
		Query query=getCurrentSession().createQuery(hql);
		int maxrow=0;
		int maxpage=0;
		//获取总记录数
		maxrow=Integer.parseInt(query.list().get(0).toString());
		if(maxrow==0){
			maxpage=1;
		}else{
			maxpage=maxrow%rows==0?maxrow/rows:maxrow/rows+1;
		}
		
		return maxpage;
		
	}
	
	public Productinfo merge(Productinfo detachedInstance) {
		log.debug("merging Productinfo instance");
		try {
			Productinfo result = (Productinfo) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Productinfo instance) {
		log.debug("attaching dirty Productinfo instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Productinfo instance) {
		log.debug("attaching clean Productinfo instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProductinfoDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ProductinfoDAO) ctx.getBean("ProductinfoDAO");
	}
}