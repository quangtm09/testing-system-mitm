package dao;
import model.*;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class AccountRoleMap.
 * @see .AccountRoleMap
 * @author Hibernate Tools
 */
@Stateless
public class AccountRoleMapHome {

	private static final Log log = LogFactory.getLog(AccountRoleMapHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(AccountRoleMap transientInstance) {
		log.debug("persisting AccountRoleMap instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(AccountRoleMap persistentInstance) {
		log.debug("removing AccountRoleMap instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public AccountRoleMap merge(AccountRoleMap detachedInstance) {
		log.debug("merging AccountRoleMap instance");
		try {
			AccountRoleMap result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AccountRoleMap findById(Integer id) {
		log.debug("getting AccountRoleMap instance with id: " + id);
		try {
			AccountRoleMap instance = entityManager.find(AccountRoleMap.class,
					id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
