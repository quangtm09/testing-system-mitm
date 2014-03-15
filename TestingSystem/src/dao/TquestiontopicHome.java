package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Tquestiontopic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Tquestiontopic.
 * @see .Tquestiontopic
 * @author Hibernate Tools
 */
@Stateless
public class TquestiontopicHome {

	private static final Log log = LogFactory.getLog(TquestiontopicHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Tquestiontopic transientInstance) {
		log.debug("persisting Tquestiontopic instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Tquestiontopic persistentInstance) {
		log.debug("removing Tquestiontopic instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Tquestiontopic merge(Tquestiontopic detachedInstance) {
		log.debug("merging Tquestiontopic instance");
		try {
			Tquestiontopic result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tquestiontopic findById(Integer id) {
		log.debug("getting Tquestiontopic instance with id: " + id);
		try {
			Tquestiontopic instance = entityManager.find(Tquestiontopic.class,
					id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
