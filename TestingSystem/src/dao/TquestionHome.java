package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Tquestion;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Tquestion.
 * @see .Tquestion
 * @author Hibernate Tools
 */
@Stateless
public class TquestionHome {

	private static final Log log = LogFactory.getLog(TquestionHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Tquestion transientInstance) {
		log.debug("persisting Tquestion instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Tquestion persistentInstance) {
		log.debug("removing Tquestion instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Tquestion merge(Tquestion detachedInstance) {
		log.debug("merging Tquestion instance");
		try {
			Tquestion result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tquestion findById(Integer id) {
		log.debug("getting Tquestion instance with id: " + id);
		try {
			Tquestion instance = entityManager.find(Tquestion.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
