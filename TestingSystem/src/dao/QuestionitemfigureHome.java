package dao;

// Generated Mar 14, 2014 12:37:44 AM by Hibernate Tools 3.6.0

import static org.hibernate.criterion.Example.create;

import java.util.List;

import javax.naming.InitialContext;

import model.Questionitemfigure;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class Questionitemfigure.
 * @see dao.Questionitemfigure
 * @author Hibernate Tools
 */
public class QuestionitemfigureHome {

	private static final Log log = LogFactory
			.getLog(QuestionitemfigureHome.class);

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

	public void persist(Questionitemfigure transientInstance) {
		log.debug("persisting Questionitemfigure instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Questionitemfigure instance) {
		log.debug("attaching dirty Questionitemfigure instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Questionitemfigure instance) {
		log.debug("attaching clean Questionitemfigure instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Questionitemfigure persistentInstance) {
		log.debug("deleting Questionitemfigure instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Questionitemfigure merge(Questionitemfigure detachedInstance) {
		log.debug("merging Questionitemfigure instance");
		try {
			Questionitemfigure result = (Questionitemfigure) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Questionitemfigure findById(java.lang.Integer id) {
		log.debug("getting Questionitemfigure instance with id: " + id);
		try {
			Questionitemfigure instance = (Questionitemfigure) sessionFactory
					.getCurrentSession().get("dao.Questionitemfigure", id);
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

	public List<Questionitemfigure> findByExample(Questionitemfigure instance) {
		log.debug("finding Questionitemfigure instance by example");
		try {
			List<Questionitemfigure> results = (List<Questionitemfigure>) sessionFactory
					.getCurrentSession()
					.createCriteria("dao.Questionitemfigure")
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
