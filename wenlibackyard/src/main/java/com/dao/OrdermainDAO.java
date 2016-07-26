package com.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.po.Ordermain;

/**
 * A data access object (DAO) providing persistence and search support for
 * Ordermain entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.po.Ordermain
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Service("OrdermainDAO")
public class OrdermainDAO {
	private static final Logger log = LoggerFactory
			.getLogger(OrdermainDAO.class);
	// property constants
	public static final String STATE = "state";
	public static final String SUM_PRICE = "sumPrice";
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

	public void save(Ordermain transientInstance) {
		log.debug("saving Ordermain instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Ordermain persistentInstance) {
		log.debug("deleting Ordermain instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Ordermain findById(java.lang.Integer id) {
		log.debug("getting Ordermain instance with id: " + id);
		try {
			Ordermain instance = (Ordermain) getCurrentSession().get(
					"com.po.Ordermain", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Ordermain> findByExample(Ordermain instance) {
		log.debug("finding Ordermain instance by example");
		try {
			List<Ordermain> results = (List<Ordermain>) getCurrentSession()
					.createCriteria("com.po.Ordermain").add(create(instance))
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
		log.debug("finding Ordermain instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Ordermain as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Ordermain> findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List<Ordermain> findBySumPrice(Object sumPrice) {
		return findByProperty(SUM_PRICE, sumPrice);
	}

	public List<Ordermain> findByContext(Object context) {
		return findByProperty(CONTEXT, context);
	}

	public List findAll() {
		log.debug("finding all Ordermain instances");
		try {
			String queryString = "from Ordermain";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Ordermain merge(Ordermain detachedInstance) {
		log.debug("merging Ordermain instance");
		try {
			Ordermain result = (Ordermain) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Ordermain instance) {
		log.debug("attaching dirty Ordermain instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Ordermain instance) {
		log.debug("attaching clean Ordermain instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OrdermainDAO getFromApplicationContext(ApplicationContext ctx) {
		return (OrdermainDAO) ctx.getBean("OrdermainDAO");
	}
	
	public List<Object> findByOriginalSql(String originalSql) {
		log.debug("findByOriginal orderMain return List<Object>");
		try {
			SQLQuery query = getCurrentSession().createSQLQuery(originalSql);
			@SuppressWarnings("unchecked")
			List<Object> objlst = query.list();
			log.debug("findOriginalSql successful");
			return objlst;
		} catch (HibernateException re) {
			log.error("findOriginalSql failed", re);
			throw re;
		}
	}

	public List<Ordermain> findAllByUser(String userId, int page, int rows) {
		String hql="from Ordermain o where o.userinfo.userId = ? order by o.confirmDate desc";
		
		Query query=getCurrentSession().createQuery(hql).setParameter(0, userId);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		return query.list();
	}
	
}