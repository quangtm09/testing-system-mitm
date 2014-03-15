package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Questionsolution;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Questionsolution.
 * @see .Questionsolution
 * @author Hibernate Tools
 */
@Stateless
public class QuestionsolutionHome {

	private static final Log log = LogFactory
			.getLog(QuestionsolutionHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Questionsolution transientInstance) {
		log.debug("persisting Questionsolution instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Questionsolution persistentInstance) {
		log.debug("removing Questionsolution instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Questionsolution merge(Questionsolution detachedInstance) {
		log.debug("merging Questionsolution instance");
		try {
			Questionsolution result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Questionsolution findById(Integer id) {
		log.debug("getting Questionsolution instance with id: " + id);
		try {
			Questionsolution instance = entityManager.find(
					Questionsolution.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
