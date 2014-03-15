package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Questionowner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Questionowner.
 * @see .Questionowner
 * @author Hibernate Tools
 */
@Stateless
public class QuestionownerHome {

	private static final Log log = LogFactory.getLog(QuestionownerHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Questionowner transientInstance) {
		log.debug("persisting Questionowner instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Questionowner persistentInstance) {
		log.debug("removing Questionowner instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Questionowner merge(Questionowner detachedInstance) {
		log.debug("merging Questionowner instance");
		try {
			Questionowner result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Questionowner findById(Integer id) {
		log.debug("getting Questionowner instance with id: " + id);
		try {
			Questionowner instance = entityManager
					.find(Questionowner.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
