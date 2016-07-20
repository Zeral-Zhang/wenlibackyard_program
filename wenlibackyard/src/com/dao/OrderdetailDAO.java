package com.dao;

import java.util.List;

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

import com.po.Orderdetail;

/**
 * A data access object (DAO) providing persistence and search support for
 * Orderdetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.po.Orderdetail
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Service("OrderdetailDAO")
public class OrderdetailDAO {
	private static final Logger log = LoggerFactory
			.getLogger(OrderdetailDAO.class);
	// property constants
	public static final String NUM = "num";
	public static final String SUM_PRICE = "sumPrice";

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

	public void save(Orderdetail transientInstance) {
		log.debug("saving Orderdetail instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Orderdetail persistentInstance) {
		log.debug("deleting Orderdetail instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Orderdetail findById(java.lang.Integer id) {
		log.debug("getting Orderdetail instance with id: " + id);
		try {
			Orderdetail instance = (Orderdetail) getCurrentSession().get(
					"com.po.Orderdetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Orderdetail> findByExample(Orderdetail instance) {
		log.debug("finding Orderdetail instance by example");
		try {
			List<Orderdetail> results = (List<Orderdetail>) getCurrentSession()
					.createCriteria("com.po.Orderdetail").add(create(instance))
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
		log.debug("finding Orderdetail instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Orderdetail as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Orderdetail> findByNum(Object num) {
		return findByProperty(NUM, num);
	}

	public List<Orderdetail> findBySumPrice(Object sumPrice) {
		return findByProperty(SUM_PRICE, sumPrice);
	}

	public List findAll() {
		log.debug("finding all Orderdetail instances");
		try {
			String queryString = "from Orderdetail";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Orderdetail merge(Orderdetail detachedInstance) {
		log.debug("merging Orderdetail instance");
		try {
			Orderdetail result = (Orderdetail) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Orderdetail instance) {
		log.debug("attaching dirty Orderdetail instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Orderdetail instance) {
		log.debug("attaching clean Orderdetail instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OrderdetailDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (OrderdetailDAO) ctx.getBean("OrderdetailDAO");
	}
}