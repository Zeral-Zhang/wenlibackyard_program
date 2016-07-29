package com.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.po.Userdetailinfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * Userdetailinfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.po.Userdetailinfo
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Service("UserdetailinfoDAO")
public class UserdetailinfoDAO {
	private static final Logger log = Logger
			.getLogger(UserdetailinfoDAO.class);
	// property constants
	public static final String USER_TEL = "userTel";
	public static final String USER_CITY = "userCity";
	public static final String USER_PROVINCE = "userProvince";
	public static final String USER_LANGUAGE = "userLanguage";
	public static final String USER_GENDER = "userGender";
	public static final String USER_AGE = "userAge";

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

	public void save(Userdetailinfo transientInstance) {
		log.debug("saving Userdetailinfo instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Userdetailinfo persistentInstance) {
		log.debug("deleting Userdetailinfo instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Userdetailinfo findById(java.lang.Integer id) {
		log.debug("getting Userdetailinfo instance with id: " + id);
		try {
			Userdetailinfo instance = (Userdetailinfo) getCurrentSession().get(
					"com.po.Userdetailinfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Userdetailinfo> findByExample(Userdetailinfo instance) {
		log.debug("finding Userdetailinfo instance by example");
		try {
			List<Userdetailinfo> results = (List<Userdetailinfo>) getCurrentSession()
					.createCriteria("com.po.Userdetailinfo")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Userdetailinfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Userdetailinfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Userdetailinfo> findByUserTel(Object userTel) {
		return findByProperty(USER_TEL, userTel);
	}

	public List<Userdetailinfo> findByUserCity(Object userCity) {
		return findByProperty(USER_CITY, userCity);
	}

	public List<Userdetailinfo> findByUserProvince(Object userProvince) {
		return findByProperty(USER_PROVINCE, userProvince);
	}

	public List<Userdetailinfo> findByUserLanguage(Object userLanguage) {
		return findByProperty(USER_LANGUAGE, userLanguage);
	}

	public List<Userdetailinfo> findByUserGender(Object userGender) {
		return findByProperty(USER_GENDER, userGender);
	}

	public List<Userdetailinfo> findByUserAge(Object userAge) {
		return findByProperty(USER_AGE, userAge);
	}

	public List findAll() {
		log.debug("finding all Userdetailinfo instances");
		try {
			String queryString = "from Userdetailinfo";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Userdetailinfo merge(Userdetailinfo detachedInstance) {
		log.debug("merging Userdetailinfo instance");
		try {
			Userdetailinfo result = (Userdetailinfo) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Userdetailinfo instance) {
		log.debug("attaching dirty Userdetailinfo instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Userdetailinfo instance) {
		log.debug("attaching clean Userdetailinfo instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserdetailinfoDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (UserdetailinfoDAO) ctx.getBean("UserdetailinfoDAO");
	}
	
	public List<Object> findByOriginalSql(String originalSql) {
		log.debug("findByOriginal userdetailinfo return List<Object>");
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
}