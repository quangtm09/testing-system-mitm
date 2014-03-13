package dao;

// Generated Mar 14, 2014 12:37:44 AM by Hibernate Tools 3.6.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import model.Squestionsolution;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Squestionsolution.
 * @see dao.Squestionsolution
 * @author Hibernate Tools
 */
public class SquestionsolutionHome {

	private static final Log log = LogFactory
			.getLog(SquestionsolutionHome.class);

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

	public void persist(Squestionsolution transientInstance) {
		log.debug("persisting Squestionsolution instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Squestionsolution instance) {
		log.debug("attaching dirty Squestionsolution instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Squestionsolution instance) {
		log.debug("attaching clean Squestionsolution instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Squestionsolution persistentInstance) {
		log.debug("deleting Squestionsolution instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Squestionsolution merge(Squestionsolution detachedInstance) {
		log.debug("merging Squestionsolution instance");
		try {
			Squestionsolution result = (Squestionsolution) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Squestionsolution findById(java.lang.Integer id) {
		log.debug("getting Squestionsolution instance with id: " + id);
		try {
			Squestionsolution instance = (Squestionsolution) sessionFactory
					.getCurrentSession().get("dao.Squestionsolution", id);
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

	public List<Squestionsolution> findByExample(Squestionsolution instance) {
		log.debug("finding Squestionsolution instance by example");
		try {
			List<Squestionsolution> results = (List<Squestionsolution>) sessionFactory
					.getCurrentSession()
					.createCriteria("dao.Squestionsolution")
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
