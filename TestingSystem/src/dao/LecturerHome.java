package dao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Lecturer;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.ejb.*;
/**
 * Home object for domain model class Lecturer.
 * @see .Lecturer
 * @author Hibernate Tools
 */
@Stateless
public class LecturerHome {

	private static final Log log = LogFactory.getLog(LecturerHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Lecturer transientInstance) {
		log.debug("persisting Lecturer instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Lecturer persistentInstance) {
		log.debug("removing Lecturer instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Lecturer merge(Lecturer detachedInstance) {
		log.debug("merging Lecturer instance");
		try {
			Lecturer result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Lecturer findById(String id) {
		log.debug("getting Lecturer instance with id: " + id);
		try {
			Lecturer instance = entityManager.find(Lecturer.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
