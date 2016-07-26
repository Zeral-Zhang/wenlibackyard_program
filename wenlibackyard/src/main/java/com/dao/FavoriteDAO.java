package com.dao;

import java.util.Date;
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

import com.po.Favorite;

/**
 * A data access object (DAO) providing persistence and search support for
 * Favorite entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.po.Favorite
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Service("FavoriteDAO")
public class FavoriteDAO {
	private static final Logger log = LoggerFactory
			.getLogger(FavoriteDAO.class);
	// property constants
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

	public void save(Favorite transientInstance) {
		log.debug("saving Favorite instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Favorite persistentInstance) {
		log.debug("deleting Favorite instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Favorite findById(java.lang.Integer id) {
		log.debug("getting Favorite instance with id: " + id);
		try {
			Favorite instance = (Favorite) getCurrentSession().get(
					"com.po.Favorite", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Favorite> findByExample(Favorite instance) {
		log.debug("finding Favorite instance by example");
		try {
			List<Favorite> results = (List<Favorite>) getCurrentSession()
					.createCriteria("com.po.Favorite").add(create(instance))
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
		log.debug("finding Favorite instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Favorite as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Favorite> findByContext(Object context) {
		return findByProperty(CONTEXT, context);
	}

	public List findAll() {
		log.debug("finding all Favorite instances");
		try {
			String queryString = "from Favorite";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Favorite merge(Favorite detachedInstance) {
		log.debug("merging Favorite instance");
		try {
			Favorite result = (Favorite) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Favorite instance) {
		log.debug("attaching dirty Favorite instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Favorite instance) {
		log.debug("attaching clean Favorite instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FavoriteDAO getFromApplicationContext(ApplicationContext ctx) {
		return (FavoriteDAO) ctx.getBean("FavoriteDAO");
	}
}