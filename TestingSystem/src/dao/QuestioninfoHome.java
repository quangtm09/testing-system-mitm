package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Questioninfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Questioninfo.
 * @see .Questioninfo
 * @author Hibernate Tools
 */
@Stateless
public class QuestioninfoHome {

	private static final Log log = LogFactory.getLog(QuestioninfoHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Questioninfo transientInstance) {
		log.debug("persisting Questioninfo instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Questioninfo persistentInstance) {
		log.debug("removing Questioninfo instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Questioninfo merge(Questioninfo detachedInstance) {
		log.debug("merging Questioninfo instance");
		try {
			Questioninfo result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Questioninfo findById(Integer id) {
		log.debug("getting Questioninfo instance with id: " + id);
		try {
			Questioninfo instance = entityManager.find(Questioninfo.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
