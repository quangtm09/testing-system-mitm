package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import model.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Testreview.
 * @see .Testreview
 * @author Hibernate Tools
 */
@Stateless
public class TestreviewHome {

	private static final Log log = LogFactory.getLog(TestreviewHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Testreview transientInstance) {
		log.debug("persisting Testreview instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Testreview persistentInstance) {
		log.debug("removing Testreview instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Testreview merge(Testreview detachedInstance) {
		log.debug("merging Testreview instance");
		try {
			Testreview result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Testreview findById(Integer id) {
		log.debug("getting Testreview instance with id: " + id);
		try {
			Testreview instance = entityManager.find(Testreview.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
