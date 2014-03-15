package dao;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.RolePermissionMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class RolePermissionMap.
 * @see .RolePermissionMap
 * @author Hibernate Tools
 */
@Stateless
public class RolePermissionMapHome {

	private static final Log log = LogFactory
			.getLog(RolePermissionMapHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(RolePermissionMap transientInstance) {
		log.debug("persisting RolePermissionMap instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(RolePermissionMap persistentInstance) {
		log.debug("removing RolePermissionMap instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public RolePermissionMap merge(RolePermissionMap detachedInstance) {
		log.debug("merging RolePermissionMap instance");
		try {
			RolePermissionMap result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public RolePermissionMap findById(Integer id) {
		log.debug("getting RolePermissionMap instance with id: " + id);
		try {
			RolePermissionMap instance = entityManager.find(
					RolePermissionMap.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
