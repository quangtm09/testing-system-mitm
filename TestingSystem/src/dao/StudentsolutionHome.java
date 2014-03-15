package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Studentsolution;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Studentsolution.
 * @see .Studentsolution
 * @author Hibernate Tools
 */
@Stateless
public class StudentsolutionHome {

	private static final Log log = LogFactory.getLog(StudentsolutionHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Studentsolution transientInstance) {
		log.debug("persisting Studentsolution instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Studentsolution persistentInstance) {
		log.debug("removing Studentsolution instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Studentsolution merge(Studentsolution detachedInstance) {
		log.debug("merging Studentsolution instance");
		try {
			Studentsolution result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Studentsolution findById(Integer id) {
		log.debug("getting Studentsolution instance with id: " + id);
		try {
			Studentsolution instance = entityManager.find(
					Studentsolution.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
