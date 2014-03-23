package dao.impl;

// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Account;
import model.RolePermissionMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import util.HibernateUtil;
import dao.interfaces.RolePermissionMapDao;

/**
 * Home object for domain model class RolePermissionMap.
 * 
 * @see .RolePermissionMap
 * @author Hibernate Tools
 */
@Stateless
public class RolePermissionMapDaoImp implements RolePermissionMapDao {

	private static SessionFactory sessionfactory;
	private static final Log log = LogFactory
			.getLog(RolePermissionMapDaoImp.class);

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

	@Override
	public void insertRoleAndPermissionByAccID(String accountID, int roleID,
			int permissionID) {
		// TODO Auto-generated method stub
		log.debug("Insert Role and Permission By accID: " + accountID);
		try {
			StringBuilder sql = new StringBuilder("");
			sql.append("INSERT INTO ROLE_PERMISSION_MAP (ROLE_ID, PERMISSION_ID, ACC_ID, ROLE_PERMISSION_GRANTED_DATE) VALUES ("
					+ roleID
					+ ","
					+ permissionID
					+ ",'"
					+ accountID
					+ "',CURRENT_TIMESTAMP)");
			sessionfactory = HibernateUtil.getSessionFactory();
			Session session = sessionfactory.getCurrentSession();
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.executeUpdate();
			session.getTransaction().commit();
			log.debug("insert successful");
		} catch (RuntimeException re) {
			sessionfactory.getCurrentSession().getTransaction().rollback();
			log.error("insert failed", re);
			throw re;
		} finally {
			HibernateUtil.closeSession();
		}

	}

	@Override
	public List<RolePermissionMapDao> getPermissionForAccID(String accountID) {
		// TODO Auto-generated method stub
		log.debug("getting Permissions for Account: " + accountID);
		try {
			// TODO Auto-generated method stub
			StringBuilder sql = new StringBuilder("");
			sql.append(" SELECT ");
			sql.append(" rpm.ROLE_ID roleId, ");
			sql.append(" rpm.ACC_ID accId, ");
			sql.append(" rpm.ROLE_PERMISSION_GRANTED_DATE rp_date, ");
			sql.append(" rpm.PERMISSION_ID permissionId, ");
			sql.append(" p.PERMISSION_NAME permissionName, ");
			sql.append(" p.PERMISSION_VALUE permissionValue, ");
			sql.append(" p.PERMISSION_DESC permissionDesc ");
			sql.append(" FROM ROLE_PERMISSION_MAP rpm");
			sql.append(" LEFT JOIN PERMISSION p");
			sql.append(" ON rpm.PERMISSION_ID = p.PERMISSION_ID");
			sql.append(" WHERE");
			sql.append(" rpm.ACC_ID = :accID");
			sessionfactory = HibernateUtil.getSessionFactory();
			Session session = sessionfactory.getCurrentSession();
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
					.setParameter("accID", accountID);
			log.debug("get successful");
			return (List<RolePermissionMapDao>) query.list();
		} catch (RuntimeException re) {
			sessionfactory.getCurrentSession().getTransaction().rollback();
			log.error("get failed", re);
			throw re;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	@Override
	public void updateRoleAndPermissionByAccID(String accountID, int roleID,
			int permissionID) {
		// TODO Auto-generated method stub
		log.debug("Update Role and Permission For AccountID " + accountID);
		try {
			StringBuilder sql = new StringBuilder("");
			sql.append(" UPDATE ROLE_PERMISSION_MAP");
			sql.append(" SET ROLE_ID = :roleID,");
			sql.append(" PERMISSION_ID = :permissionID");
			sql.append(" WHERE ACC_ID = :accID");
			sessionfactory = HibernateUtil.getSessionFactory();
			Session session = sessionfactory.getCurrentSession();
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
					.setParameter("roleID", roleID)
					.setParameter("permissionID", permissionID)
					.setParameter("accID", accountID);
			query.executeUpdate();
			session.getTransaction().commit();
			log.debug("Update successfully");
		} catch (RuntimeException re) {
			sessionfactory.getCurrentSession().getTransaction().rollback();
			log.error("Update failed", re);
			throw re;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	@Override
	public void deleteRoleAndPermissionByAccID(String accountID) {
		// TODO Auto-generated method stub
		log.debug("Delete Role And Permission " + accountID);
		try {
			StringBuilder sql = new StringBuilder("");
			sql.append("DELETE FROM ROLE_PERMISSION_MAP WHERE ACC_ID = :accID");
			sessionfactory = HibernateUtil.getSessionFactory();
			Session session = sessionfactory.getCurrentSession();
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
					.setParameter("accID", accountID);
			query.executeUpdate();
			session.getTransaction().commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			sessionfactory.getCurrentSession().getTransaction().rollback();
			log.error("delete failed", re);
			throw re;
		} finally {
			HibernateUtil.closeSession();
		}
	}
}
