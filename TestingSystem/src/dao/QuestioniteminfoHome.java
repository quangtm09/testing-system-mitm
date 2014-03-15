package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Questioniteminfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * Home object for domain model class Questioniteminfo.
 * @see .Questioniteminfo
 * @author Hibernate Tools
 */
@Stateless
public class QuestioniteminfoHome {

	private static final Log log = LogFactory
			.getLog(QuestioniteminfoHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Questioniteminfo transientInstance) {
		log.debug("persisting Questioniteminfo instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Questioniteminfo persistentInstance) {
		log.debug("removing Questioniteminfo instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Questioniteminfo merge(Questioniteminfo detachedInstance) {
		log.debug("merging Questioniteminfo instance");
		try {
			Questioniteminfo result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Questioniteminfo findById(Integer id) {
		log.debug("getting Questioniteminfo instance with id: " + id);
		try {
			Questioniteminfo instance = entityManager.find(
					Questioniteminfo.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
