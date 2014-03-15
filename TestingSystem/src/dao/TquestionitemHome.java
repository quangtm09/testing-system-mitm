package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Tquestionitem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Tquestionitem.
 * @see .Tquestionitem
 * @author Hibernate Tools
 */
@Stateless
public class TquestionitemHome {

	private static final Log log = LogFactory.getLog(TquestionitemHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Tquestionitem transientInstance) {
		log.debug("persisting Tquestionitem instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Tquestionitem persistentInstance) {
		log.debug("removing Tquestionitem instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Tquestionitem merge(Tquestionitem detachedInstance) {
		log.debug("merging Tquestionitem instance");
		try {
			Tquestionitem result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tquestionitem findById(Integer id) {
		log.debug("getting Tquestionitem instance with id: " + id);
		try {
			Tquestionitem instance = entityManager
					.find(Tquestionitem.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
