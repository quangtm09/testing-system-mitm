package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Squestionsolution;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Squestionsolution.
 * @see .Squestionsolution
 * @author Hibernate Tools
 */
@Stateless
public class SquestionsolutionHome {

	private static final Log log = LogFactory
			.getLog(SquestionsolutionHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Squestionsolution transientInstance) {
		log.debug("persisting Squestionsolution instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Squestionsolution persistentInstance) {
		log.debug("removing Squestionsolution instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Squestionsolution merge(Squestionsolution detachedInstance) {
		log.debug("merging Squestionsolution instance");
		try {
			Squestionsolution result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Squestionsolution findById(Integer id) {
		log.debug("getting Squestionsolution instance with id: " + id);
		try {
			Squestionsolution instance = entityManager.find(
					Squestionsolution.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
