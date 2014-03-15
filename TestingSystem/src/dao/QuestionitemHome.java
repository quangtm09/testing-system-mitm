package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Questionitem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.ejb.*;
/**
 * Home object for domain model class Questionitem.
 * @see .Questionitem
 * @author Hibernate Tools
 */
@Stateless
public class QuestionitemHome {

	private static final Log log = LogFactory.getLog(QuestionitemHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Questionitem transientInstance) {
		log.debug("persisting Questionitem instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Questionitem persistentInstance) {
		log.debug("removing Questionitem instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Questionitem merge(Questionitem detachedInstance) {
		log.debug("merging Questionitem instance");
		try {
			Questionitem result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Questionitem findById(Integer id) {
		log.debug("getting Questionitem instance with id: " + id);
		try {
			Questionitem instance = entityManager.find(Questionitem.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
