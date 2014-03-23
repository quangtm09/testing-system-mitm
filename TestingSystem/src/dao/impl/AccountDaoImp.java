package dao.impl;

import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Account;
// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import util.HibernateUtil;
import dao.interfaces.AccountDao;

/**
 * Home object for domain model class Account.
 * 
 * @see .Account
 * @author Hibernate Tools
 */
@Stateless
public class AccountDaoImp implements AccountDao {

	private static final Log log = LogFactory.getLog(AccountDaoImp.class);
	private static SessionFactory sessionfactory;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> findAccountByAccID(String accountID) {
		log.debug("getting Account by AccountID: " + accountID);
		try {
			// TODO Auto-generated method stub
			StringBuilder sql = new StringBuilder("");
			sql.append(" SELECT ");
			sql.append(" acc.ACC_ID accId ,");
			sql.append(" user.User_ID userId, ");
			sql.append(" user.FNAME fname, ");
			sql.append(" user.LNAME lname, ");
			sql.append(" user.EMAIL email , ");
			sql.append(" user.Mobile mobile, ");
			sql.append(" user.BDate bdate, ");
			sql.append(" user.Address address ");
			sql.append(" FROM USER user, ACCOUNT acc");
			sql.append(" WHERE");
			sql.append(" user.User_ID = acc.User_ID");
			sql.append(" AND acc.ACC_ID = :acc_id");
			sessionfactory = HibernateUtil.getSessionFactory();
			Session session = sessionfactory.getCurrentSession();
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
					.setParameter("acc_id", accountID);
			log.debug("get successful");
			return (List<Account>) query.list();
		} catch (RuntimeException re) {
			sessionfactory.getCurrentSession().getTransaction().rollback();
			log.error("get failed", re);
			throw re;
		} finally {
			HibernateUtil.closeSession();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> findAccountsByRoleID(String roleID) {
		log.debug("getting Accounts by RoleID: " + roleID);
		try {
			// TODO Auto-generated method stub
			StringBuilder sql = new StringBuilder("");
			sql.append(" SELECT ");
			sql.append(" acc.ACC_ID accId ,");
			sql.append(" acc.User_ID userId, ");
			sql.append(" role.ROLE_NAME roleName, ");
			sql.append(" role.ROLE_DESC roleDesc, ");
			sql.append(" role.ROLE_ID roleID ");
			sql.append(" FROM ROLE role, ACCOUNT acc");
			sql.append(" WHERE");
			sql.append(" role.ROLE_ID = :roleID");
			sessionfactory = HibernateUtil.getSessionFactory();
			Session session = sessionfactory.getCurrentSession();
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
					.setParameter("roleID", roleID);
			log.debug("get successful");
			return (List<Account>) query.list();
		} catch (RuntimeException re) {
			sessionfactory.getCurrentSession().getTransaction().rollback();
			log.error("get failed", re);
			throw re;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	@Override
	public void deleteAccount(String accountID) {
		// TODO Auto-generated method stub
		log.debug("Delete Account " + accountID);
		try {
			StringBuilder sql = new StringBuilder("");
			sql.append("DELETE FROM ACCOUNT WHERE ACC_ID = :accID");
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

	@Override
	public void insertAccount(String accountID, String userID, String accountPwd) {
		// TODO Auto-generated method stub
		log.debug("Insert Account into Database: " + accountID);
		try {
			StringBuilder sql = new StringBuilder("");
			sql.append("INSERT INTO ACCOUNT (Acc_Id, User_Id, Acc_Pwd) VALUES ('"
					+ accountID + "','" + userID + "','" + accountPwd + "')");
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
	public void changePassword(String accountID, String accountPwd) {
		// TODO Auto-generated method stub
		log.debug("Change Password For AccountID " + accountID);
		try {
			StringBuilder sql = new StringBuilder("");
			sql.append(" UPDATE ACCOUNT");
			sql.append(" SET ACC_PWD = :accPwd");
			sql.append(" WHERE ACC_ID = :accID");
			sessionfactory = HibernateUtil.getSessionFactory();
			Session session = sessionfactory.getCurrentSession();
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
					.setParameter("accPwd", accountPwd)
					.setParameter("accID", accountID);
			query.executeUpdate();
			session.getTransaction().commit();
			log.debug("Change Password successfully");
		} catch (RuntimeException re) {
			sessionfactory.getCurrentSession().getTransaction().rollback();
			log.error("change Password failed", re);
			throw re;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean checkAccount(String accountID, String accountPwd) {
		// TODO Auto-generated method stub
		List<Account> acc = findAccountByAccIDandPwd(accountID, accountPwd);
		Iterator<Account> iterator = acc.iterator();
		if (iterator.hasNext()) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> findAccountByAccIDandPwd(String accountID,
			String accountPwd) {
		log.debug("find Account by AccID and Pwd: " + accountID);
		try {
			// TODO Auto-generated method stub
			StringBuilder sql = new StringBuilder("");
			sql.append(" SELECT ");
			sql.append(" acc.ACC_ID accId ,");
			sql.append(" acc.ACC_PWD accPwd ");
			sql.append(" FROM  ACCOUNT acc");
			sql.append(" WHERE");
			sql.append(" UCASE(acc.ACC_ID) = UCASE(:accID)");
			sql.append(" AND acc.ACC_PWD = :accPWD");
			sessionfactory = HibernateUtil.getSessionFactory();
			Session session = sessionfactory.getCurrentSession();
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
					.setParameter("accID", accountID)
					.setParameter("accPWD", accountPwd);
			log.debug("get successful");
			return (List<Account>) query.list();
		} catch (RuntimeException re) {
			sessionfactory.getCurrentSession().getTransaction().rollback();
			log.error("get failed", re);
			throw re;
		} finally {
			HibernateUtil.closeSession();
		}
	}
}
