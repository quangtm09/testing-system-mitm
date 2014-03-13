package dao;

// Generated Mar 14, 2014 12:37:44 AM by Hibernate Tools 3.6.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import model.Sharedquestion;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Sharedquestion.
 * @see dao.Sharedquestion
 * @author Hibernate Tools
 */
public class SharedquestionHome {

	private static final Log log = LogFactory.getLog(SharedquestionHome.class);

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

	public void persist(Sharedquestion transientInstance) {
		log.debug("persisting Sharedquestion instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Sharedquestion instance) {
		log.debug("attaching dirty Sharedquestion instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Sharedquestion instance) {
		log.debug("attaching clean Sharedquestion instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Sharedquestion persistentInstance) {
		log.debug("deleting Sharedquestion instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Sharedquestion merge(Sharedquestion detachedInstance) {
		log.debug("merging Sharedquestion instance");
		try {
			Sharedquestion result = (Sharedquestion) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Sharedquestion findById(java.lang.Integer id) {
		log.debug("getting Sharedquestion instance with id: " + id);
		try {
			Sharedquestion instance = (Sharedquestion) sessionFactory
					.getCurrentSession().get("dao.Sharedquestion", id);
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

	public List<Sharedquestion> findByExample(Sharedquestion instance) {
		log.debug("finding Sharedquestion instance by example");
		try {
			List<Sharedquestion> results = (List<Sharedquestion>) sessionFactory
					.getCurrentSession().createCriteria("dao.Sharedquestion")
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
