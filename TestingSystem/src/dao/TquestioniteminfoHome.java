package dao;

// Generated Mar 14, 2014 12:37:44 AM by Hibernate Tools 3.6.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import model.Tquestioniteminfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Tquestioniteminfo.
 * @see dao.Tquestioniteminfo
 * @author Hibernate Tools
 */
public class TquestioniteminfoHome {

	private static final Log log = LogFactory
			.getLog(TquestioniteminfoHome.class);

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

	public void persist(Tquestioniteminfo transientInstance) {
		log.debug("persisting Tquestioniteminfo instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tquestioniteminfo instance) {
		log.debug("attaching dirty Tquestioniteminfo instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tquestioniteminfo instance) {
		log.debug("attaching clean Tquestioniteminfo instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tquestioniteminfo persistentInstance) {
		log.debug("deleting Tquestioniteminfo instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tquestioniteminfo merge(Tquestioniteminfo detachedInstance) {
		log.debug("merging Tquestioniteminfo instance");
		try {
			Tquestioniteminfo result = (Tquestioniteminfo) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tquestioniteminfo findById(java.lang.Integer id) {
		log.debug("getting Tquestioniteminfo instance with id: " + id);
		try {
			Tquestioniteminfo instance = (Tquestioniteminfo) sessionFactory
					.getCurrentSession().get("dao.Tquestioniteminfo", id);
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

	public List<Tquestioniteminfo> findByExample(Tquestioniteminfo instance) {
		log.debug("finding Tquestioniteminfo instance by example");
		try {
			List<Tquestioniteminfo> results = (List<Tquestioniteminfo>) sessionFactory
					.getCurrentSession()
					.createCriteria("dao.Tquestioniteminfo")
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
