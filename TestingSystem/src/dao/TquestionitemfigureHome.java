package dao;

// Generated Mar 14, 2014 12:37:44 AM by Hibernate Tools 3.6.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import model.Tquestionitemfigure;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Tquestionitemfigure.
 * @see dao.Tquestionitemfigure
 * @author Hibernate Tools
 */
public class TquestionitemfigureHome {

	private static final Log log = LogFactory
			.getLog(TquestionitemfigureHome.class);

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

	public void persist(Tquestionitemfigure transientInstance) {
		log.debug("persisting Tquestionitemfigure instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tquestionitemfigure instance) {
		log.debug("attaching dirty Tquestionitemfigure instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tquestionitemfigure instance) {
		log.debug("attaching clean Tquestionitemfigure instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tquestionitemfigure persistentInstance) {
		log.debug("deleting Tquestionitemfigure instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tquestionitemfigure merge(Tquestionitemfigure detachedInstance) {
		log.debug("merging Tquestionitemfigure instance");
		try {
			Tquestionitemfigure result = (Tquestionitemfigure) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tquestionitemfigure findById(java.lang.Integer id) {
		log.debug("getting Tquestionitemfigure instance with id: " + id);
		try {
			Tquestionitemfigure instance = (Tquestionitemfigure) sessionFactory
					.getCurrentSession().get("dao.Tquestionitemfigure", id);
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

	public List<Tquestionitemfigure> findByExample(Tquestionitemfigure instance) {
		log.debug("finding Tquestionitemfigure instance by example");
		try {
			List<Tquestionitemfigure> results = (List<Tquestionitemfigure>) sessionFactory
					.getCurrentSession()
					.createCriteria("dao.Tquestionitemfigure")
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
