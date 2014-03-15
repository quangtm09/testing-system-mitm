package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Tquestionowner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Tquestionowner.
 * @see .Tquestionowner
 * @author Hibernate Tools
 */
@Stateless
public class TquestionownerHome {

	private static final Log log = LogFactory.getLog(TquestionownerHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Tquestionowner transientInstance) {
		log.debug("persisting Tquestionowner instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Tquestionowner persistentInstance) {
		log.debug("removing Tquestionowner instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Tquestionowner merge(Tquestionowner detachedInstance) {
		log.debug("merging Tquestionowner instance");
		try {
			Tquestionowner result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tquestionowner findById(Integer id) {
		log.debug("getting Tquestionowner instance with id: " + id);
		try {
			Tquestionowner instance = entityManager.find(Tquestionowner.class,
					id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
