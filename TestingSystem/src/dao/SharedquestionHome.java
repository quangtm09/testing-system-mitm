package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Sharedquestion;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Sharedquestion.
 * @see .Sharedquestion
 * @author Hibernate Tools
 */
@Stateless
public class SharedquestionHome {

	private static final Log log = LogFactory.getLog(SharedquestionHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Sharedquestion transientInstance) {
		log.debug("persisting Sharedquestion instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Sharedquestion persistentInstance) {
		log.debug("removing Sharedquestion instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Sharedquestion merge(Sharedquestion detachedInstance) {
		log.debug("merging Sharedquestion instance");
		try {
			Sharedquestion result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Sharedquestion findById(Integer id) {
		log.debug("getting Sharedquestion instance with id: " + id);
		try {
			Sharedquestion instance = entityManager.find(Sharedquestion.class,
					id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
