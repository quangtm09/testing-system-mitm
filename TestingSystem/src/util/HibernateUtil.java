package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static final ThreadLocal<Session> threadSession = new ThreadLocal<Session>();
	private static final ThreadLocal<Transaction> threadTransaction = new ThreadLocal<Transaction>();

	public static Session getSession() {
		Session s = threadSession.get();
		// Open a new Session, if this thread has none yet
		try {
			if (s == null) {
				s = sessionFactory.openSession();
				threadSession.set(s);
			}
		} catch (final HibernateException ex) {
			throw new HibernateException(ex);
		}

		return s;
	}

	public static void closeSession() {
		try {
			final Session s = threadSession.get();
			threadSession.set(null);
			if (s != null && s.isOpen()) {
				s.close();
			}
		} catch (final HibernateException ex) {
			throw new HibernateException(ex);
		}
	}

	public static void beginTransaction() {
		Transaction tx = threadTransaction.get();
		try {
			if (tx == null) {
				tx = getSession().beginTransaction();
				threadTransaction.set(tx);
			}
		} catch (final HibernateException ex) {
			throw new HibernateException(ex);
		}
	}

	public static void commitTransaction() {
		final Transaction tx = threadTransaction.get();
		try {
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
				tx.commit();
			}

			threadTransaction.set(null);
		} catch (final StaleObjectStateException ex) {
			rollbackTransaction();
			throw new HibernateException(ex);
		} catch (final Exception ex) {
			rollbackTransaction();
			throw new HibernateException(ex);
		}
	}

	public static void rollbackTransaction() {
		final Transaction tx = threadTransaction.get();
		try {
			threadTransaction.set(null);
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


