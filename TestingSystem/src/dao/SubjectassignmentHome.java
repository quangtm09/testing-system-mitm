package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Subjectassignment.
 * @see .Subjectassignment
 * @author Hibernate Tools
 */
@Stateless
public class SubjectassignmentHome {

	private static final Log log = LogFactory
			.getLog(SubjectassignmentHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Subjectassignment transientInstance) {
		log.debug("persisting Subjectassignment instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Subjectassignment persistentInstance) {
		log.debug("removing Subjectassignment instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Subjectassignment merge(Subjectassignment detachedInstance) {
		log.debug("merging Subjectassignment instance");
		try {
			Subjectassignment result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Subjectassignment findById(Integer id) {
		log.debug("getting Subjectassignment instance with id: " + id);
		try {
			Subjectassignment instance = entityManager.find(
					Subjectassignment.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
