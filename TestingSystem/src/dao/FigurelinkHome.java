package dao;
import model.*;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Figurelink.
 * @see .Figurelink
 * @author Hibernate Tools
 */
@Stateless
public class FigurelinkHome {

	private static final Log log = LogFactory.getLog(FigurelinkHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Figurelink transientInstance) {
		log.debug("persisting Figurelink instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Figurelink persistentInstance) {
		log.debug("removing Figurelink instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Figurelink merge(Figurelink detachedInstance) {
		log.debug("merging Figurelink instance");
		try {
			Figurelink result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Figurelink findById(Integer id) {
		log.debug("getting Figurelink instance with id: " + id);
		try {
			Figurelink instance = entityManager.find(Figurelink.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
