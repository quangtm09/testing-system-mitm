package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Tquestionfigure;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Tquestionfigure.
 * @see .Tquestionfigure
 * @author Hibernate Tools
 */
@Stateless
public class TquestionfigureHome {

	private static final Log log = LogFactory.getLog(TquestionfigureHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Tquestionfigure transientInstance) {
		log.debug("persisting Tquestionfigure instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Tquestionfigure persistentInstance) {
		log.debug("removing Tquestionfigure instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Tquestionfigure merge(Tquestionfigure detachedInstance) {
		log.debug("merging Tquestionfigure instance");
		try {
			Tquestionfigure result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tquestionfigure findById(Integer id) {
		log.debug("getting Tquestionfigure instance with id: " + id);
		try {
			Tquestionfigure instance = entityManager.find(
					Tquestionfigure.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
