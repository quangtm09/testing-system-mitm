package dao;

// Generated Mar 14, 2014 12:37:44 AM by Hibernate Tools 3.6.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import model.Tfigurelink;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Tfigurelink.
 * @see dao.Tfigurelink
 * @author Hibernate Tools
 */
public class TfigurelinkHome {

	private static final Log log = LogFactory.getLog(TfigurelinkHome.class);

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

	public void persist(Tfigurelink transientInstance) {
		log.debug("persisting Tfigurelink instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tfigurelink instance) {
		log.debug("attaching dirty Tfigurelink instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tfigurelink instance) {
		log.debug("attaching clean Tfigurelink instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tfigurelink persistentInstance) {
		log.debug("deleting Tfigurelink instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tfigurelink merge(Tfigurelink detachedInstance) {
		log.debug("merging Tfigurelink instance");
		try {
			Tfigurelink result = (Tfigurelink) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tfigurelink findById(java.lang.Integer id) {
		log.debug("getting Tfigurelink instance with id: " + id);
		try {
			Tfigurelink instance = (Tfigurelink) sessionFactory
					.getCurrentSession().get("dao.Tfigurelink", id);
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

	public List<Tfigurelink> findByExample(Tfigurelink instance) {
		log.debug("finding Tfigurelink instance by example");
		try {
			List<Tfigurelink> results = (List<Tfigurelink>) sessionFactory
					.getCurrentSession().createCriteria("dao.Tfigurelink")
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
