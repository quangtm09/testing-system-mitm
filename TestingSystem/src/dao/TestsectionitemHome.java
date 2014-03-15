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
 * Home object for domain model class Testsectionitem.
 * @see .Testsectionitem
 * @author Hibernate Tools
 */
@Stateless
public class TestsectionitemHome {

	private static final Log log = LogFactory.getLog(TestsectionitemHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Testsectionitem transientInstance) {
		log.debug("persisting Testsectionitem instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Testsectionitem persistentInstance) {
		log.debug("removing Testsectionitem instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Testsectionitem merge(Testsectionitem detachedInstance) {
		log.debug("merging Testsectionitem instance");
		try {
			Testsectionitem result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Testsectionitem findById(Integer id) {
		log.debug("getting Testsectionitem instance with id: " + id);
		try {
			Testsectionitem instance = entityManager.find(
					Testsectionitem.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
