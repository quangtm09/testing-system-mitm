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
 * Home object for domain model class Studenttestmap.
 * @see .Studenttestmap
 * @author Hibernate Tools
 */
@Stateless
public class StudenttestmapHome {

	private static final Log log = LogFactory.getLog(StudenttestmapHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Studenttestmap transientInstance) {
		log.debug("persisting Studenttestmap instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Studenttestmap persistentInstance) {
		log.debug("removing Studenttestmap instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Studenttestmap merge(Studenttestmap detachedInstance) {
		log.debug("merging Studenttestmap instance");
		try {
			Studenttestmap result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Studenttestmap findById(Integer id) {
		log.debug("getting Studenttestmap instance with id: " + id);
		try {
			Studenttestmap instance = entityManager.find(Studenttestmap.class,
					id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
