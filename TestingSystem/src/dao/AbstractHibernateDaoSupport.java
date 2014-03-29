package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import util.HibernateUtil;
import util.StringPool;

public abstract class AbstractHibernateDaoSupport<C, ID extends Serializable>
implements Dao<C, ID> {
	private final Class<C> classPersistent;

	private static final Log log = LogFactory
			.getLog(AbstractHibernateDaoSupport.class);

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
		final Criteria crit = AbstractHibernateDaoSupport.getSession()
				.createCriteria(this.getPersistentClass());
		for (final Criterion c : criterion) {
			crit.add(c);
		}

		final List<C> critList = crit.list();

		return critList;
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
		AbstractHibernateDaoSupport.log.debug("Find By ID " + id);
		try {
			entity = (C) AbstractHibernateDaoSupport.getSession().get(
					this.getPersistentClass(), id);
			AbstractHibernateDaoSupport.log.debug("Find successful");
		} catch (final Exception ex) {
			ex.printStackTrace();
			AbstractHibernateDaoSupport.log.error("Find failed " + ex);
		}

		return entity;
	}

	/**
	 * find by class property
	 */
	@SuppressWarnings("unchecked")
	public final List<C> findByProperty(final String propertyName,
			final Object value) {
		AbstractHibernateDaoSupport.log.debug("Find By Property "
				+ propertyName);
		try {
			final String queryString = "from "
					+ this.classPersistent.getSimpleName()
					+ " as model where model." + propertyName
					+ "= :propertyVal";
			final Query queryObject = AbstractHibernateDaoSupport.getSession()
					.createQuery(queryString);
			queryObject.setParameter("propertyVal", value);
			final List<C> list = queryObject.list();
			AbstractHibernateDaoSupport.log.debug("Find successful");
			return list;
		} catch (final RuntimeException re) {
			re.printStackTrace();
			AbstractHibernateDaoSupport.log.error("Find failed " + re);
			return new ArrayList<C>();
		}
	}

	/**
	 * find by class property
	 */
	@SuppressWarnings("unchecked")
	public final List<C> findByPropertyIgnoreCase(final String propertyName,
			Object value) {
		AbstractHibernateDaoSupport.log.debug("Find By Property Ignore Case "
				+ propertyName);
		try {
			final String queryString = "from "
					+ this.classPersistent.getSimpleName()
					+ " as model where upper(model." + propertyName
					+ ")= :propertyVal";
			final Query queryObject = AbstractHibernateDaoSupport.getSession()
					.createQuery(queryString);
			value = ((String) value).toUpperCase();
			queryObject.setParameter("propertyVal", value);
			final List<C> list = queryObject.list();
			AbstractHibernateDaoSupport.log.debug("Find successful");
			return list;
		} catch (final RuntimeException re) {
			re.printStackTrace();
			AbstractHibernateDaoSupport.log.error("Find failed " + re);
			return new ArrayList<C>();
		}
	}

	/**
	 * Save object to database
	 */
	@Override
	public final boolean save(final C obj) {
		log.debug("Save Object " + obj);
		try {
			getSession();
			HibernateUtil.beginTransaction();
			getSession().save(obj);
			HibernateUtil.commitTransaction();
			this.flush();
			getSession().clear();

			AbstractHibernateDaoSupport.log.debug("Save successful");
			return true;
		} catch (final Exception ex) {
			ex.printStackTrace();
			AbstractHibernateDaoSupport.log.error("Save failed" + ex);
			return false;
		}
	}

	/**
	 * update object to database
	 */
	@Override
	public final boolean update(final C obj) {
		log.debug("Update Object " + obj);
		try {
			HibernateUtil.beginTransaction();
			getSession().update(obj);
			HibernateUtil.commitTransaction();
			this.flush();
			getSession().clear();

			log.debug("Update successful");
			return true;
		} catch (final Exception ex) {
			ex.printStackTrace();
			AbstractHibernateDaoSupport.log.error("Update failed" + ex);
			return false;
		}
	}

	/**
	 * delete object to database
	 */
	@Override
	public final boolean delete(final C obj) {
		log.debug("Delete Object " + obj);
		if (obj == null) {
			log.debug("No data is deleted");
			return false;
		}
		try {
			HibernateUtil.beginTransaction();
			getSession().delete(obj);
			HibernateUtil.commitTransaction();
			this.flush();
			getSession().clear();

			log.debug("Delete successful");
			return true;
		} catch (final Exception ex) {
			ex.printStackTrace();
			log.error("Delete failed");
			return false;
		}
	}

	/**
	 * merge with database
	 */
	@Override
	@SuppressWarnings("unchecked")
	public final C merge(final C obj) {
		AbstractHibernateDaoSupport.log.debug("Merge Object " + obj);
		try {
			final C result = (C) AbstractHibernateDaoSupport.getSession()
					.merge(obj);
			this.flush();
			AbstractHibernateDaoSupport.getSession().clear();
			AbstractHibernateDaoSupport.log.debug("Merge successful");
			return result;
		} catch (final RuntimeException ex) {
			ex.printStackTrace();
			AbstractHibernateDaoSupport.log.error("Merge failed" + ex);
			return null;
		}
	}

	/**
	 * flush object to database
	 */
	@Override
	public final boolean flush() {
		AbstractHibernateDaoSupport.log.debug("Flush Object");
		try {
			AbstractHibernateDaoSupport.getSession().flush();
			AbstractHibernateDaoSupport.log.debug("Flush successful");
			return true;
		} catch (final Exception ex) {
			ex.printStackTrace();
			AbstractHibernateDaoSupport.log.error("Flush failed");
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

	public static String criteriaStringForDynamicQuery(String criteriaString) {
		criteriaString = criteriaString.toLowerCase();
		// allow to search with ? and * option
		if (criteriaString.contains(StringPool.STAR)) {
			criteriaString = criteriaString.replace(StringPool.STAR, StringPool.PERCENT);
		}
		if (criteriaString.contains(StringPool.QUESTION)) {
			criteriaString = criteriaString.replace(StringPool.QUESTION, StringPool.UNDERLINE);
		}
		criteriaString = StringPool.PERCENT + criteriaString + StringPool.PERCENT;
		return criteriaString;
	}

}
