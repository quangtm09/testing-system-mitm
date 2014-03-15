package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Question;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.ejb.*;
/**
 * Home object for domain model class Question.
 * @see .Question
 * @author Hibernate Tools
 */
@Stateless
public class QuestionHome {

	private static final Log log = LogFactory.getLog(QuestionHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Question transientInstance) {
		log.debug("persisting Question instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Question persistentInstance) {
		log.debug("removing Question instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Question merge(Question detachedInstance) {
		log.debug("merging Question instance");
		try {
			Question result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Question findById(Integer id) {
		log.debug("getting Question instance with id: " + id);
		try {
			Question instance = entityManager.find(Question.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
