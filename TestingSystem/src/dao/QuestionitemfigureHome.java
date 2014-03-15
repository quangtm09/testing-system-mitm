package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Questionitemfigure;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.ejb.*;
/**
 * Home object for domain model class Questionitemfigure.
 * @see .Questionitemfigure
 * @author Hibernate Tools
 */
@Stateless
public class QuestionitemfigureHome {

	private static final Log log = LogFactory
			.getLog(QuestionitemfigureHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Questionitemfigure transientInstance) {
		log.debug("persisting Questionitemfigure instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Questionitemfigure persistentInstance) {
		log.debug("removing Questionitemfigure instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Questionitemfigure merge(Questionitemfigure detachedInstance) {
		log.debug("merging Questionitemfigure instance");
		try {
			Questionitemfigure result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Questionitemfigure findById(Integer id) {
		log.debug("getting Questionitemfigure instance with id: " + id);
		try {
			Questionitemfigure instance = entityManager.find(
					Questionitemfigure.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
