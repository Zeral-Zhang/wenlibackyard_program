package com.dao;

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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.po.Producttype;

/**
 * A data access object (DAO) providing persistence and search support for
 * Producttype entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.po.Producttype
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Service("ProducttypeDAO")
public class ProducttypeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ProducttypeDAO.class);
	// property constants
	public static final String PARENT_ID = "parentId";
	public static final String PRODUCT_TYPE_NAME = "productTypeName";
	public static final String IS_DELETE = "isDelete";
	public static final String CONTEXT = "context";

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

	public void save(Producttype transientInstance) {
		log.debug("saving Producttype instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Producttype persistentInstance) {
		log.debug("deleting Producttype instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Producttype findById(java.lang.Integer id) {
		log.debug("getting Producttype instance with id: " + id);
		try {
			Producttype instance = (Producttype) getCurrentSession().get(
					"com.po.Producttype", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Producttype> findByExample(Producttype instance) {
		log.debug("finding Producttype instance by example");
		try {
			List<Producttype> results = (List<Producttype>) getCurrentSession()
					.createCriteria("com.po.Producttype").add(create(instance))
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
		log.debug("finding Producttype instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Producttype as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Producttype> findByParentId(Object parentId) {
		return findByProperty(PARENT_ID, parentId);
	}

	public List<Producttype> findByProductTypeName(Object productTypeName) {
		return findByProperty(PRODUCT_TYPE_NAME, productTypeName);
	}

	public List<Producttype> findByIsDelete(Object isDelete) {
		return findByProperty(IS_DELETE, isDelete);
	}

	public List<Producttype> findByContext(Object context) {
		return findByProperty(CONTEXT, context);
	}

	public List findAll() {
		log.debug("finding all Producttype instances");
		try {
			String queryString = "from Producttype";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Producttype merge(Producttype detachedInstance) {
		log.debug("merging Producttype instance");
		try {
			Producttype result = (Producttype) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	public void attachDirty(Producttype instance) {
		log.debug("attaching dirty Producttype instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Producttype instance) {
		log.debug("attaching clean Producttype instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProducttypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ProducttypeDAO) ctx.getBean("ProducttypeDAO");
	}
}