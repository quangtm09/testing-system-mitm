package dao;

// Generated Mar 14, 2014 12:37:44 AM by Hibernate Tools 3.6.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import model.Subjectassignment;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Subjectassignment.
 * @see dao.Subjectassignment
 * @author Hibernate Tools
 */
public class SubjectassignmentHome {

	private static final Log log = LogFactory
			.getLog(SubjectassignmentHome.class);

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

	public void persist(Subjectassignment transientInstance) {
		log.debug("persisting Subjectassignment instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Subjectassignment instance) {
		log.debug("attaching dirty Subjectassignment instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Subjectassignment instance) {
		log.debug("attaching clean Subjectassignment instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Subjectassignment persistentInstance) {
		log.debug("deleting Subjectassignment instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Subjectassignment merge(Subjectassignment detachedInstance) {
		log.debug("merging Subjectassignment instance");
		try {
			Subjectassignment result = (Subjectassignment) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Subjectassignment findById(java.lang.Integer id) {
		log.debug("getting Subjectassignment instance with id: " + id);
		try {
			Subjectassignment instance = (Subjectassignment) sessionFactory
					.getCurrentSession().get("dao.Subjectassignment", id);
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

	public List<Subjectassignment> findByExample(Subjectassignment instance) {
		log.debug("finding Subjectassignment instance by example");
		try {
			List<Subjectassignment> results = (List<Subjectassignment>) sessionFactory
					.getCurrentSession()
					.createCriteria("dao.Subjectassignment")
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
