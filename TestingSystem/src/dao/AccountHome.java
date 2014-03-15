package dao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Account;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.ejb.*;
/**
 * Home object for domain model class Account.
 * @see .Account
 * @author Hibernate Tools
 */
@Stateless
public class AccountHome {

	private static final Log log = LogFactory.getLog(AccountHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Account transientInstance) {
		log.debug("persisting Account instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Account persistentInstance) {
		log.debug("removing Account instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Account merge(Account detachedInstance) {
		log.debug("merging Account instance");
		try {
			Account result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Account findById(String id) {
		log.debug("getting Account instance with id: " + id);
		try {
			Account instance = entityManager.find(Account.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
