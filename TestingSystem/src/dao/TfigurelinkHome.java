package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Tfigurelink;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Tfigurelink.
 * @see .Tfigurelink
 * @author Hibernate Tools
 */
@Stateless
public class TfigurelinkHome {

	private static final Log log = LogFactory.getLog(TfigurelinkHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Tfigurelink transientInstance) {
		log.debug("persisting Tfigurelink instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Tfigurelink persistentInstance) {
		log.debug("removing Tfigurelink instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Tfigurelink merge(Tfigurelink detachedInstance) {
		log.debug("merging Tfigurelink instance");
		try {
			Tfigurelink result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tfigurelink findById(Integer id) {
		log.debug("getting Tfigurelink instance with id: " + id);
		try {
			Tfigurelink instance = entityManager.find(Tfigurelink.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
