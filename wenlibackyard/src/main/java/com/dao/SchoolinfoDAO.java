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

import com.po.Schoolinfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * Schoolinfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.po.Schoolinfo
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Service("SchoolinfoDAO")
public class SchoolinfoDAO {
	private static final Logger log = Logger
			.getLogger(SchoolinfoDAO.class);
	// property constants
	public static final String COLLEGE = "college";
	public static final String DEPARTMENT = "department";
	public static final String CLASSES = "classes";
	public static final String GRADE = "grade";

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

	public void save(Schoolinfo transientInstance) {
		log.debug("saving Schoolinfo instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Schoolinfo persistentInstance) {
		log.debug("deleting Schoolinfo instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Schoolinfo findById(java.lang.Integer id) {
		log.debug("getting Schoolinfo instance with id: " + id);
		try {
			Schoolinfo instance = (Schoolinfo) getCurrentSession().get(
					"com.po.Schoolinfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Schoolinfo> findByExample(Schoolinfo instance) {
		log.debug("finding Schoolinfo instance by example");
		try {
			List<Schoolinfo> results = (List<Schoolinfo>) getCurrentSession()
					.createCriteria("com.po.Schoolinfo").add(create(instance))
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
		log.debug("finding Schoolinfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Schoolinfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Schoolinfo> findByCollege(Object college) {
		return findByProperty(COLLEGE, college);
	}

	public List<Schoolinfo> findByDepartment(Object department) {
		return findByProperty(DEPARTMENT, department);
	}

	public List<Schoolinfo> findByClasses(Object classes) {
		return findByProperty(CLASSES, classes);
	}

	public List<Schoolinfo> findByGrade(Object grade) {
		return findByProperty(GRADE, grade);
	}

	public List findAll() {
		log.debug("finding all Schoolinfo instances");
		try {
			String queryString = "from Schoolinfo";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Schoolinfo merge(Schoolinfo detachedInstance) {
		log.debug("merging Schoolinfo instance");
		try {
			Schoolinfo result = (Schoolinfo) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Schoolinfo instance) {
		log.debug("attaching dirty Schoolinfo instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Schoolinfo instance) {
		log.debug("attaching clean Schoolinfo instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SchoolinfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SchoolinfoDAO) ctx.getBean("SchoolinfoDAO");
	}
	
	public List<Object> findByOriginalSql(String originalSql) {
		log.debug("findByOriginal schoolinfo return List<Object>");
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