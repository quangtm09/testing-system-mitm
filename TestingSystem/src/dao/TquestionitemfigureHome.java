package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Tquestionitemfigure;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Tquestionitemfigure.
 * @see .Tquestionitemfigure
 * @author Hibernate Tools
 */
@Stateless
public class TquestionitemfigureHome {

	private static final Log log = LogFactory
			.getLog(TquestionitemfigureHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Tquestionitemfigure transientInstance) {
		log.debug("persisting Tquestionitemfigure instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Tquestionitemfigure persistentInstance) {
		log.debug("removing Tquestionitemfigure instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Tquestionitemfigure merge(Tquestionitemfigure detachedInstance) {
		log.debug("merging Tquestionitemfigure instance");
		try {
			Tquestionitemfigure result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tquestionitemfigure findById(Integer id) {
		log.debug("getting Tquestionitemfigure instance with id: " + id);
		try {
			Tquestionitemfigure instance = entityManager.find(
					Tquestionitemfigure.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
