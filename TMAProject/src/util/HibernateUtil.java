package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static final ThreadLocal<Session> threadSession = new ThreadLocal<Session>();
	private static final ThreadLocal<Transaction> threadTransaction = new ThreadLocal<Transaction>();

	public static Session getSession() {
		Session s = (Session) threadSession.get();
		// Open a new Session, if this thread has none yet
		try {
			if (s == null) {
				s = sessionFactory.openSession();
				threadSession.set(s);
			}
		} catch (HibernateException ex) {
			throw new HibernateException(ex);
		}

		return s;
	}

	public static void closeSession() {
		try {
			Session s = (Session) threadSession.get();
			threadSession.set(null);
			if (s != null && s.isOpen())
				s.close();
		} catch (HibernateException ex) {
			throw new HibernateException(ex);
		}
	}

	public static void beginTransaction() {
		Transaction tx = (Transaction) threadTransaction.get();
		try {
			if (tx == null) {
				tx = getSession().beginTransaction();
				threadTransaction.set(tx);
			}
		} catch (HibernateException ex) {
			throw new HibernateException(ex);
		}
	}

	public static void commitTransaction() {
		Transaction tx = (Transaction) threadTransaction.get();
		try {
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
				tx.commit();
			}

			threadTransaction.set(null);
		} catch (StaleObjectStateException ex) {
			rollbackTransaction();
			throw new HibernateException(ex);
		} catch (Exception ex) {
			rollbackTransaction();
			throw new HibernateException(ex);
		}
	}

	public static void rollbackTransaction() {
		Transaction tx = (Transaction) threadTransaction.get();
		try {
			threadTransaction.set(null);
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
				tx.rollback();
				closeSession();
			}
		} catch (HibernateException ex) {
			throw new HibernateException(ex);
		}
	}

	public void setSessionFactory(SessionFactory sessionFactoryNew) {
		sessionFactory = sessionFactoryNew;
	}
}
