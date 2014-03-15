package dao;
// default package
// Generated Mar 15, 2014 7:36:49 PM by Hibernate Tools 3.4.0.CR1

import model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Logs.
 * @see .Logs
 * @author Hibernate Tools
 */
@Stateless
public class LogsHome {

	private static final Log log = LogFactory.getLog(LogsHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Logs transientInstance) {
		log.debug("persisting Logs instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Logs persistentInstance) {
		log.debug("removing Logs instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Logs merge(Logs detachedInstance) {
		log.debug("merging Logs instance");
		try {
			Logs result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Logs findById(Integer id) {
		log.debug("getting Logs instance with id: " + id);
		try {
			Logs instance = entityManager.find(Logs.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
