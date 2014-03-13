package dao;

// Generated Mar 14, 2014 12:37:44 AM by Hibernate Tools 3.6.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import model.Tquestionowner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Tquestionowner.
 * @see dao.Tquestionowner
 * @author Hibernate Tools
 */
public class TquestionownerHome {

	private static final Log log = LogFactory.getLog(TquestionownerHome.class);

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

	public void persist(Tquestionowner transientInstance) {
		log.debug("persisting Tquestionowner instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tquestionowner instance) {
		log.debug("attaching dirty Tquestionowner instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tquestionowner instance) {
		log.debug("attaching clean Tquestionowner instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tquestionowner persistentInstance) {
		log.debug("deleting Tquestionowner instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tquestionowner merge(Tquestionowner detachedInstance) {
		log.debug("merging Tquestionowner instance");
		try {
			Tquestionowner result = (Tquestionowner) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tquestionowner findById(java.lang.Integer id) {
		log.debug("getting Tquestionowner instance with id: " + id);
		try {
			Tquestionowner instance = (Tquestionowner) sessionFactory
					.getCurrentSession().get("dao.Tquestionowner", id);
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

	public List<Tquestionowner> findByExample(Tquestionowner instance) {
		log.debug("finding Tquestionowner instance by example");
		try {
			List<Tquestionowner> results = (List<Tquestionowner>) sessionFactory
					.getCurrentSession().createCriteria("dao.Tquestionowner")
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
