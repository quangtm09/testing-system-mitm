package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Tquestioniteminfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Tquestioniteminfo.
 * @see .Tquestioniteminfo
 * @author Hibernate Tools
 */
@Stateless
public class TquestioniteminfoHome {

	private static final Log log = LogFactory
			.getLog(TquestioniteminfoHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Tquestioniteminfo transientInstance) {
		log.debug("persisting Tquestioniteminfo instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Tquestioniteminfo persistentInstance) {
		log.debug("removing Tquestioniteminfo instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Tquestioniteminfo merge(Tquestioniteminfo detachedInstance) {
		log.debug("merging Tquestioniteminfo instance");
		try {
			Tquestioniteminfo result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tquestioniteminfo findById(Integer id) {
		log.debug("getting Tquestioniteminfo instance with id: " + id);
		try {
			Tquestioniteminfo instance = entityManager.find(
					Tquestioniteminfo.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
