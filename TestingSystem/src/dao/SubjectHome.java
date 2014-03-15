package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Subject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Subject.
 * @see .Subject
 * @author Hibernate Tools
 */
@Stateless
public class SubjectHome {

	private static final Log log = LogFactory.getLog(SubjectHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Subject transientInstance) {
		log.debug("persisting Subject instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Subject persistentInstance) {
		log.debug("removing Subject instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Subject merge(Subject detachedInstance) {
		log.debug("merging Subject instance");
		try {
			Subject result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Subject findById(String id) {
		log.debug("getting Subject instance with id: " + id);
		try {
			Subject instance = entityManager.find(Subject.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
