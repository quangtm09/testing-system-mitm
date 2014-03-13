package dao;

// Generated Mar 14, 2014 12:37:44 AM by Hibernate Tools 3.6.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import model.Tquestiontopic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Tquestiontopic.
 * @see dao.Tquestiontopic
 * @author Hibernate Tools
 */
public class TquestiontopicHome {

	private static final Log log = LogFactory.getLog(TquestiontopicHome.class);

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

	public void persist(Tquestiontopic transientInstance) {
		log.debug("persisting Tquestiontopic instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tquestiontopic instance) {
		log.debug("attaching dirty Tquestiontopic instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tquestiontopic instance) {
		log.debug("attaching clean Tquestiontopic instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tquestiontopic persistentInstance) {
		log.debug("deleting Tquestiontopic instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tquestiontopic merge(Tquestiontopic detachedInstance) {
		log.debug("merging Tquestiontopic instance");
		try {
			Tquestiontopic result = (Tquestiontopic) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tquestiontopic findById(java.lang.Integer id) {
		log.debug("getting Tquestiontopic instance with id: " + id);
		try {
			Tquestiontopic instance = (Tquestiontopic) sessionFactory
					.getCurrentSession().get("dao.Tquestiontopic", id);
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

	public List<Tquestiontopic> findByExample(Tquestiontopic instance) {
		log.debug("finding Tquestiontopic instance by example");
		try {
			List<Tquestiontopic> results = (List<Tquestiontopic>) sessionFactory
					.getCurrentSession().createCriteria("dao.Tquestiontopic")
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
