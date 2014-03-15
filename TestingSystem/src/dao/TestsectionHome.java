package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Testsection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Testsection.
 * @see .Testsection
 * @author Hibernate Tools
 */
@Stateless
public class TestsectionHome {

	private static final Log log = LogFactory.getLog(TestsectionHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Testsection transientInstance) {
		log.debug("persisting Testsection instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Testsection persistentInstance) {
		log.debug("removing Testsection instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Testsection merge(Testsection detachedInstance) {
		log.debug("merging Testsection instance");
		try {
			Testsection result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Testsection findById(Integer id) {
		log.debug("getting Testsection instance with id: " + id);
		try {
			Testsection instance = entityManager.find(Testsection.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
