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
 * Home object for domain model class Subjectandtopic.
 * @see .Subjectandtopic
 * @author Hibernate Tools
 */
@Stateless
public class SubjectandtopicHome {

	private static final Log log = LogFactory.getLog(SubjectandtopicHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Subjectandtopic transientInstance) {
		log.debug("persisting Subjectandtopic instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Subjectandtopic persistentInstance) {
		log.debug("removing Subjectandtopic instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Subjectandtopic merge(Subjectandtopic detachedInstance) {
		log.debug("merging Subjectandtopic instance");
		try {
			Subjectandtopic result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Subjectandtopic findById(Integer id) {
		log.debug("getting Subjectandtopic instance with id: " + id);
		try {
			Subjectandtopic instance = entityManager.find(
					Subjectandtopic.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
