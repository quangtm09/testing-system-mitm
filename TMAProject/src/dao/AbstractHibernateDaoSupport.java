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
	private Class<C> classPersistent;

	protected AbstractHibernateDaoSupport(Class<C> classPersistent) {
		this.classPersistent = classPersistent;
	}

	public Class<C> getPersistentClass() {
		return classPersistent;
	}

	/**
	 * find by criteria
	 */
	@SuppressWarnings("unchecked")
	protected final List<C> findByCriteria(Criterion... criterion) {
		Criteria crit = getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}

		return crit.list();
	}

	/**
	 * find all object
	 */
	public final List<C> findAll() {
		return findByCriteria();
	}

	/**
	 * find by id
	 */
	@SuppressWarnings("unchecked")
	public final C findById(ID id) {
		C entity = null;

		try {
			entity = (C) getSession().get(getPersistentClass(), id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return entity;
	}

	/**
	 * find by class property
	 */
	@SuppressWarnings("unchecked")
	public final List<C> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from " + classPersistent.getSimpleName()
					+ " as model where model." + propertyName
					+ "= :propertyVal";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter("propertyVal", value);
			return queryObject.list();
		} catch (RuntimeException re) {
			re.printStackTrace();
			return new ArrayList<C>();
		}
	}

	/**
	 * find by class property
	 */
	@SuppressWarnings("unchecked")
	public final List<C> findByPropertyIgnoreCase(String propertyName,
			Object value) {
		try {
			String queryString = "from " + classPersistent.getSimpleName()
					+ " as model where upper(model." + propertyName
					+ ")= :propertyVal";
			Query queryObject = getSession().createQuery(queryString);
			value = ((String) value).toUpperCase();
			queryObject.setParameter("propertyVal", value);
			return queryObject.list();
		} catch (RuntimeException re) {
			re.printStackTrace();
			return new ArrayList<C>();
		}
	}

	/**
	 * Save object to database
	 */
	public final boolean save(C obj) {
		try {
			this.getSession().save(obj);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * update object to database
	 */
	public final boolean update(C obj) {
		try {
			this.getSession().update(obj);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * delete object to database
	 */
	public final boolean delete(C obj) {
		if (obj == null) {
			return false;
		}

		try {
			this.getSession().delete(obj);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * merge with database
	 */
	@SuppressWarnings("unchecked")
	public final C merge(C obj) {
		try {
			C result = (C) this.getSession().merge(obj);
			return result;
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * flush object to database
	 */
	public final boolean flush() {
		try {
			this.getSession().flush();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public final boolean close() {
		try {
			Session session = this.getSession();

			if (session != null) {
				session.flush();
				session.close();
			}

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public Session getSession() {
		return HibernateUtil.getSession();
	}

}
