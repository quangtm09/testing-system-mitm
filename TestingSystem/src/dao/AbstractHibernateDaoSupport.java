package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import util.HibernateUtil;

public abstract class AbstractHibernateDaoSupport<C, ID extends Serializable>
		implements Dao<C, ID> {
	private final Class<C> classPersistent;

	protected AbstractHibernateDaoSupport(final Class<C> classPersistent) {
		this.classPersistent = classPersistent;
	}

	public Class<C> getPersistentClass() {
		return this.classPersistent;
	}

	/**
	 * find by criteria
	 */
	@SuppressWarnings("unchecked")
	protected final List<C> findByCriteria(final Criterion... criterion) {
		final Criteria crit = getSession().createCriteria(this.getPersistentClass());
		for (final Criterion c : criterion) {
			crit.add(c);
		}

		return crit.list();
	}

	/**
	 * find all object
	 */
	@Override
	public final List<C> findAll() {
		return this.findByCriteria();
	}

	/**
	 * find by id
	 */
	@Override
	@SuppressWarnings("unchecked")
	public final C findById(final ID id) {
		C entity = null;

		try {
			entity = (C) getSession().get(this.getPersistentClass(), id);
		} catch (final Exception ex) {
			ex.printStackTrace();
		}

		return entity;
	}

	/**
	 * find by class property
	 */
	@SuppressWarnings("unchecked")
	public final List<C> findByProperty(final String propertyName, final Object value) {
		try {
			final String queryString = "from " + this.classPersistent.getSimpleName()
					+ " as model where model." + propertyName
					+ "= :propertyVal";
			final Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter("propertyVal", value);
			return queryObject.list();
		} catch (final RuntimeException re) {
			re.printStackTrace();
			return new ArrayList<C>();
		}
	}

	/**
	 * find by class property
	 */
	@SuppressWarnings("unchecked")
	public final List<C> findByPropertyIgnoreCase(final String propertyName,
			Object value) {
		try {
			final String queryString = "from " + this.classPersistent.getSimpleName()
					+ " as model where upper(model." + propertyName
					+ ")= :propertyVal";
			final Query queryObject = getSession().createQuery(queryString);
			value = ((String) value).toUpperCase();
			queryObject.setParameter("propertyVal", value);
			return queryObject.list();
		} catch (final RuntimeException re) {
			re.printStackTrace();
			return new ArrayList<C>();
		}
	}

	/**
	 * Save object to database
	 */
	@Override
	public final boolean save(final C obj) {
		try {
			AbstractHibernateDaoSupport.getSession().save(obj);
			return true;
		} catch (final Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * update object to database
	 */
	@Override
	public final boolean update(final C obj) {
		try {
			AbstractHibernateDaoSupport.getSession().update(obj);
			return true;
		} catch (final Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * delete object to database
	 */
	@Override
	public final boolean delete(final C obj) {
		if (obj == null) {
			return false;
		}

		try {
			AbstractHibernateDaoSupport.getSession().delete(obj);
			return true;
		} catch (final Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * merge with database
	 */
	@Override
	@SuppressWarnings("unchecked")
	public final C merge(final C obj) {
		try {
			final C result = (C) AbstractHibernateDaoSupport.getSession().merge(obj);
			return result;
		} catch (final RuntimeException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * flush object to database
	 */
	@Override
	public final boolean flush() {
		try {
			AbstractHibernateDaoSupport.getSession().flush();
			return true;
		} catch (final Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public final boolean close() {
		try {
			final Session session = AbstractHibernateDaoSupport.getSession();

			if (session != null) {
				session.flush();
				session.close();
			}

			return true;
		} catch (final Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static Session getSession() {
		return HibernateUtil.getSession();
	}

}
