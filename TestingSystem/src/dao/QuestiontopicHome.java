package dao;

// Generated Mar 14, 2014 12:37:44 AM by Hibernate Tools 3.6.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import model.Questiontopic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Questiontopic.
 * @see dao.Questiontopic
 * @author Hibernate Tools
 */
public class QuestiontopicHome {

	private static final Log log = LogFactory.getLog(QuestiontopicHome.class);

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

	public void persist(Questiontopic transientInstance) {
		log.debug("persisting Questiontopic instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Questiontopic instance) {
		log.debug("attaching dirty Questiontopic instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Questiontopic instance) {
		log.debug("attaching clean Questiontopic instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Questiontopic persistentInstance) {
		log.debug("deleting Questiontopic instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Questiontopic merge(Questiontopic detachedInstance) {
		log.debug("merging Questiontopic instance");
		try {
			Questiontopic result = (Questiontopic) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Questiontopic findById(java.lang.Integer id) {
		log.debug("getting Questiontopic instance with id: " + id);
		try {
			Questiontopic instance = (Questiontopic) sessionFactory
					.getCurrentSession().get("dao.Questiontopic", id);
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

	public List<Questiontopic> findByExample(Questiontopic instance) {
		log.debug("finding Questiontopic instance by example");
		try {
			List<Questiontopic> results = (List<Questiontopic>) sessionFactory
					.getCurrentSession().createCriteria("dao.Questiontopic")
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
