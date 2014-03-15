package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Questiontopic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Questiontopic.
 * @see .Questiontopic
 * @author Hibernate Tools
 */
@Stateless
public class QuestiontopicHome {

	private static final Log log = LogFactory.getLog(QuestiontopicHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Questiontopic transientInstance) {
		log.debug("persisting Questiontopic instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Questiontopic persistentInstance) {
		log.debug("removing Questiontopic instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Questiontopic merge(Questiontopic detachedInstance) {
		log.debug("merging Questiontopic instance");
		try {
			Questiontopic result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Questiontopic findById(Integer id) {
		log.debug("getting Questiontopic instance with id: " + id);
		try {
			Questiontopic instance = entityManager
					.find(Questiontopic.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
