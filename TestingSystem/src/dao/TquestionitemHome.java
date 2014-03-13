package dao;

// Generated Mar 14, 2014 12:37:44 AM by Hibernate Tools 3.6.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import model.Tquestionitem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Tquestionitem.
 * @see dao.Tquestionitem
 * @author Hibernate Tools
 */
public class TquestionitemHome {

	private static final Log log = LogFactory.getLog(TquestionitemHome.class);

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

	public void persist(Tquestionitem transientInstance) {
		log.debug("persisting Tquestionitem instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tquestionitem instance) {
		log.debug("attaching dirty Tquestionitem instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tquestionitem instance) {
		log.debug("attaching clean Tquestionitem instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tquestionitem persistentInstance) {
		log.debug("deleting Tquestionitem instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tquestionitem merge(Tquestionitem detachedInstance) {
		log.debug("merging Tquestionitem instance");
		try {
			Tquestionitem result = (Tquestionitem) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tquestionitem findById(java.lang.Integer id) {
		log.debug("getting Tquestionitem instance with id: " + id);
		try {
			Tquestionitem instance = (Tquestionitem) sessionFactory
					.getCurrentSession().get("dao.Tquestionitem", id);
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

	public List<Tquestionitem> findByExample(Tquestionitem instance) {
		log.debug("finding Tquestionitem instance by example");
		try {
			List<Tquestionitem> results = (List<Tquestionitem>) sessionFactory
					.getCurrentSession().createCriteria("dao.Tquestionitem")
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
