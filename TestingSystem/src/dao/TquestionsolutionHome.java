package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Tquestionsolution;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Tquestionsolution.
 * @see .Tquestionsolution
 * @author Hibernate Tools
 */
@Stateless
public class TquestionsolutionHome {

	private static final Log log = LogFactory
			.getLog(TquestionsolutionHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Tquestionsolution transientInstance) {
		log.debug("persisting Tquestionsolution instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Tquestionsolution persistentInstance) {
		log.debug("removing Tquestionsolution instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Tquestionsolution merge(Tquestionsolution detachedInstance) {
		log.debug("merging Tquestionsolution instance");
		try {
			Tquestionsolution result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tquestionsolution findById(Integer id) {
		log.debug("getting Tquestionsolution instance with id: " + id);
		try {
			Tquestionsolution instance = entityManager.find(
					Tquestionsolution.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
