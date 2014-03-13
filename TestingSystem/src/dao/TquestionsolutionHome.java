package dao;

// Generated Mar 14, 2014 12:37:44 AM by Hibernate Tools 3.6.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import model.Tquestionsolution;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Tquestionsolution.
 * @see dao.Tquestionsolution
 * @author Hibernate Tools
 */
public class TquestionsolutionHome {

	private static final Log log = LogFactory
			.getLog(TquestionsolutionHome.class);

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

	public void persist(Tquestionsolution transientInstance) {
		log.debug("persisting Tquestionsolution instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tquestionsolution instance) {
		log.debug("attaching dirty Tquestionsolution instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tquestionsolution instance) {
		log.debug("attaching clean Tquestionsolution instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tquestionsolution persistentInstance) {
		log.debug("deleting Tquestionsolution instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tquestionsolution merge(Tquestionsolution detachedInstance) {
		log.debug("merging Tquestionsolution instance");
		try {
			Tquestionsolution result = (Tquestionsolution) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tquestionsolution findById(java.lang.Integer id) {
		log.debug("getting Tquestionsolution instance with id: " + id);
		try {
			Tquestionsolution instance = (Tquestionsolution) sessionFactory
					.getCurrentSession().get("dao.Tquestionsolution", id);
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

	public List<Tquestionsolution> findByExample(Tquestionsolution instance) {
		log.debug("finding Tquestionsolution instance by example");
		try {
			List<Tquestionsolution> results = (List<Tquestionsolution>) sessionFactory
					.getCurrentSession()
					.createCriteria("dao.Tquestionsolution")
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
