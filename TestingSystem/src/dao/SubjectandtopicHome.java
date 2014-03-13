package dao;

// Generated Mar 14, 2014 12:37:44 AM by Hibernate Tools 3.6.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import model.Subjectandtopic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Subjectandtopic.
 * @see dao.Subjectandtopic
 * @author Hibernate Tools
 */
public class SubjectandtopicHome {

	private static final Log log = LogFactory.getLog(SubjectandtopicHome.class);

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

	public void persist(Subjectandtopic transientInstance) {
		log.debug("persisting Subjectandtopic instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Subjectandtopic instance) {
		log.debug("attaching dirty Subjectandtopic instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Subjectandtopic instance) {
		log.debug("attaching clean Subjectandtopic instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Subjectandtopic persistentInstance) {
		log.debug("deleting Subjectandtopic instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Subjectandtopic merge(Subjectandtopic detachedInstance) {
		log.debug("merging Subjectandtopic instance");
		try {
			Subjectandtopic result = (Subjectandtopic) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Subjectandtopic findById(java.lang.Integer id) {
		log.debug("getting Subjectandtopic instance with id: " + id);
		try {
			Subjectandtopic instance = (Subjectandtopic) sessionFactory
					.getCurrentSession().get("dao.Subjectandtopic", id);
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

	public List<Subjectandtopic> findByExample(Subjectandtopic instance) {
		log.debug("finding Subjectandtopic instance by example");
		try {
			List<Subjectandtopic> results = (List<Subjectandtopic>) sessionFactory
					.getCurrentSession().createCriteria("dao.Subjectandtopic")
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
