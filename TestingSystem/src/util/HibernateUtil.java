package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static Session session = null;
	private static Transaction tx = null;

	public static Session getSession() {
		try {
			if (session == null) {
				session = sessionFactory.openSession();
			}
		} catch (final HibernateException ex) {
			throw new HibernateException(ex);
		}

		return session;
	}

	public static void closeSession() {
		try {
			if (session != null && session.isOpen()) {
				session.close();
			}
		} catch (final HibernateException ex) {
			throw new HibernateException(ex);
		}
	}

	public static void beginTransaction() {
		try {
			if (tx == null) {
				tx = getSession().beginTransaction();
			}
		} catch (final HibernateException ex) {
			throw new HibernateException(ex);
		}
	}

	public static void commitTransaction() {
		try {
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
				tx.commit();
			}
		} catch (final StaleObjectStateException ex) {
			rollbackTransaction();
			throw new HibernateException(ex);
		} catch (final Exception ex) {
			rollbackTransaction();
			throw new HibernateException(ex);
		}
	}

	public static void rollbackTransaction() {
		try {
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
				tx.rollback();
				closeSession();
			}
		} catch (final HibernateException ex) {
			throw new HibernateException(ex);
		}
	}

	public void setSessionFactory(final SessionFactory sessionFactoryNew) {
		sessionFactory = sessionFactoryNew;
	}

	static{
		try{
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (final Exception ex){
			ex.printStackTrace();
			System.err.println("Can't init session factory.");
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}

}


