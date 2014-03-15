package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Tquestioninfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Tquestioninfo.
 * @see .Tquestioninfo
 * @author Hibernate Tools
 */
@Stateless
public class TquestioninfoHome {

	private static final Log log = LogFactory.getLog(TquestioninfoHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Tquestioninfo transientInstance) {
		log.debug("persisting Tquestioninfo instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Tquestioninfo persistentInstance) {
		log.debug("removing Tquestioninfo instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Tquestioninfo merge(Tquestioninfo detachedInstance) {
		log.debug("merging Tquestioninfo instance");
		try {
			Tquestioninfo result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tquestioninfo findById(Integer id) {
		log.debug("getting Tquestioninfo instance with id: " + id);
		try {
			Tquestioninfo instance = entityManager
					.find(Tquestioninfo.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
