package dao;

// Generated Mar 14, 2014 12:37:44 AM by Hibernate Tools 3.6.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import model.Studenttestmap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Studenttestmap.
 * @see dao.Studenttestmap
 * @author Hibernate Tools
 */
public class StudenttestmapHome {

	private static final Log log = LogFactory.getLog(StudenttestmapHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Studenttestmap transientInstance) {
		log.debug("persisting Studenttestmap instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Studenttestmap instance) {
		log.debug("attaching dirty Studenttestmap instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Studenttestmap instance) {
		log.debug("attaching clean Studenttestmap instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Studenttestmap persistentInstance) {
		log.debug("deleting Studenttestmap instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Studenttestmap merge(Studenttestmap detachedInstance) {
		log.debug("merging Studenttestmap instance");
		try {
			Studenttestmap result = (Studenttestmap) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Studenttestmap findById(java.lang.Integer id) {
		log.debug("getting Studenttestmap instance with id: " + id);
		try {
			Studenttestmap instance = (Studenttestmap) sessionFactory
					.getCurrentSession().get("dao.Studenttestmap", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Studenttestmap> findByExample(Studenttestmap instance) {
		log.debug("finding Studenttestmap instance by example");
		try {
			List<Studenttestmap> results = (List<Studenttestmap>) sessionFactory
					.getCurrentSession().createCriteria("dao.Studenttestmap")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
