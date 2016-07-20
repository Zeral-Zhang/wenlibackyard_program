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

import com.po.Regions;

/**
 * A data access object (DAO) providing persistence and search support for
 * Regions entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.po.Regions
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Service("RegionsDAO")
public class RegionsDAO {
	private static final Logger log = LoggerFactory.getLogger(RegionsDAO.class);
	// property constants
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String _PCODE = "PCode";
	public static final String LEVEL = "level";

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

	public void save(Regions transientInstance) {
		log.debug("saving Regions instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Regions persistentInstance) {
		log.debug("deleting Regions instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Regions findById(java.lang.Integer id) {
		log.debug("getting Regions instance with id: " + id);
		try {
			Regions instance = (Regions) getCurrentSession().get(
					"com.po.Regions", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Regions> findByExample(Regions instance) {
		log.debug("finding Regions instance by example");
		try {
			List<Regions> results = (List<Regions>) getCurrentSession()
					.createCriteria("com.po.Regions").add(create(instance))
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
		log.debug("finding Regions instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Regions as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Regions> findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List<Regions> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Regions> findByPCode(Object PCode) {
		return findByProperty(_PCODE, PCode);
	}

	public List<Regions> findByLevel(Object level) {
		return findByProperty(LEVEL, level);
	}

	public List findAll() {
		log.debug("finding all Regions instances");
		try {
			String queryString = "from Regions";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Regions merge(Regions detachedInstance) {
		log.debug("merging Regions instance");
		try {
			Regions result = (Regions) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Regions instance) {
		log.debug("attaching dirty Regions instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Regions instance) {
		log.debug("attaching clean Regions instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RegionsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RegionsDAO) ctx.getBean("RegionsDAO");
	}
	
}