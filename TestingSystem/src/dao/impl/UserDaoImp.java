package dao.impl;

// default package
// Generated Mar 15, 2014 7:36:49 PM by Hibernate Tools 3.4.0.CR1
import java.util.Date;
//import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Account;
import model.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import util.HibernateUtil;
import dao.interfaces.UserDao;

/**
 * Home object for domain model class User.
 * 
 * @see .User
 * @author Hibernate Tools
 */
@Stateless
public class UserDaoImp implements UserDao {

	private static final Log log = LogFactory.getLog(UserDaoImp.class);
	private static SessionFactory sessionfactory;
	@PersistenceContext
	private EntityManager entityManager;

	public void persist(User transientInstance) {
		log.debug("persisting User instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(User persistentInstance) {
		log.debug("removing User instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public User findById(String id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = entityManager.find(User.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public User findUserByUserID(String userID) {
		log.debug("getting User by userID: " + userID);
		try {
			// TODO Auto-generated method stub
			StringBuilder sql = new StringBuilder("");
			sql.append(" SELECT ");
			sql.append(" User_ID userId, ");
			sql.append(" FNAME fname, ");
			sql.append(" LNAME lname, ");
			sql.append(" EMAIL email , ");
			sql.append(" Mobile mobile, ");
			sql.append(" BDate bdate, ");
			sql.append(" Address address ");
			sql.append(" FROM USER");
			sql.append(" WHERE ");
			sql.append("User_ID = :userID");
			sessionfactory = HibernateUtil.getSessionFactory();
			Session session = sessionfactory.getCurrentSession();
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql.toString());
			query.setResultTransformer(Transformers.aliasToBean(User.class))
					.setParameter("userID", userID);
			log.debug("get successful");
			return (User) query.uniqueResult();
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
	public List<Account> getListAccountByUserID(String userID) {
		log.debug("getting User by userID: " + userID);
		try {
			// TODO Auto-generated method stub
			StringBuilder sql = new StringBuilder("");
			sql.append(" SELECT ");
			sql.append(" user.User_ID userId, ");
			sql.append(" user.FNAME fname, ");
			sql.append(" user.LNAME lname, ");
			sql.append(" user.EMAIL email , ");
			sql.append(" user.Mobile mobile, ");
			sql.append(" user.BDate bdate, ");
			sql.append(" user.Address address ,");
			sql.append(" acc.ACC_ID accId ");
			sql.append(" FROM USER user");
			sql.append(" LEFT JOIN ACCOUNT acc");
			sql.append(" ON user.User_ID = acc.User_ID");
			sql.append(" WHERE");
			sql.append(" user.User_ID = :userID");
			sessionfactory = HibernateUtil.getSessionFactory();
			Session session = sessionfactory.getCurrentSession();
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
					.setParameter("userID", userID);
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

	@SuppressWarnings({ "null" })
	@Override
	public void insertUser(String userId, String fName, String lName,
			String email, String mobile, Date bdate, String address) {
		// TODO Auto-generated method stub
		log.debug("Insert User into Database: " + userId);
		try {
			StringBuilder sql = new StringBuilder("");
			sql.append("INSERT INTO USER (User_Id, FName, LName, Email, Mobile, Date, Address) VALUES ('"
					+ userId
					+ "','"
					+ fName
					+ "','"
					+ lName
					+ "','"
					+ email
					+ "','" + mobile + "','" + bdate + "','" + address + "')");
			sessionfactory = HibernateUtil.getSessionFactory();
			Session session = sessionfactory.getCurrentSession();
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.executeUpdate();
			session.getTransaction().commit();
			log.info("Insert User successfully");
		} catch (RuntimeException re) {
			sessionfactory.getCurrentSession().getTransaction().rollback();
			log.error("Insert failed", re);
			throw re;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	@Override
	public void deleteUser(String userID) {
		// TODO Auto-generated method stub
		log.debug("Delete User " + userID);
		try {
			StringBuilder sql = new StringBuilder("");
			sql.append("DELETE FROM USER WHERE User_Id = :userId");
			sessionfactory = HibernateUtil.getSessionFactory();
			Session session = sessionfactory.getCurrentSession();
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql.toString());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
					.setParameter("userId", userID);
			query.executeUpdate();
			session.getTransaction().commit();
			log.info("Delete User Successfully");
		} catch (RuntimeException re) {
			sessionfactory.getCurrentSession().getTransaction().rollback();
			log.error("Delete failed", re);
			throw re;
		} finally {
			HibernateUtil.closeSession();
		}
	}
}
