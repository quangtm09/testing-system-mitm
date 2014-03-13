package dao;

// Generated Mar 14, 2014 12:37:44 AM by Hibernate Tools 3.6.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import model.Tquestionfigure;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Tquestionfigure.
 * @see dao.Tquestionfigure
 * @author Hibernate Tools
 */
public class TquestionfigureHome {

	private static final Log log = LogFactory.getLog(TquestionfigureHome.class);

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

	public void persist(Tquestionfigure transientInstance) {
		log.debug("persisting Tquestionfigure instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tquestionfigure instance) {
		log.debug("attaching dirty Tquestionfigure instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tquestionfigure instance) {
		log.debug("attaching clean Tquestionfigure instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tquestionfigure persistentInstance) {
		log.debug("deleting Tquestionfigure instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tquestionfigure merge(Tquestionfigure detachedInstance) {
		log.debug("merging Tquestionfigure instance");
		try {
			Tquestionfigure result = (Tquestionfigure) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tquestionfigure findById(java.lang.Integer id) {
		log.debug("getting Tquestionfigure instance with id: " + id);
		try {
			Tquestionfigure instance = (Tquestionfigure) sessionFactory
					.getCurrentSession().get("dao.Tquestionfigure", id);
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

	public List<Tquestionfigure> findByExample(Tquestionfigure instance) {
		log.debug("finding Tquestionfigure instance by example");
		try {
			List<Tquestionfigure> results = (List<Tquestionfigure>) sessionFactory
					.getCurrentSession().createCriteria("dao.Tquestionfigure")
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
