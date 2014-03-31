package dao.impl;

import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Permission;
import model.Role;
import model.RolePermissionMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import dao.AbstractHibernateDaoSupport;
import dao.RolePermissionMapDao;

public class RolePermissionMapDaoImpl extends
		AbstractHibernateDaoSupport<RolePermissionMap, Integer> implements
		RolePermissionMapDao {
	private static final Log log = LogFactory
			.getLog(RolePermissionMapDaoImpl.class);

	public RolePermissionMapDaoImpl() {
		super(RolePermissionMap.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addRolePermissonByAccID(
			final RolePermissionMap rolePermissionMap) {
		// TODO Auto-generated method stub
		RolePermissionMapDaoImpl.log.info("Add Role Permission By Account ID "
				+ rolePermissionMap.getAccount().getAccId());
		this.save(rolePermissionMap);
	}

	@Override
	public boolean updateRolePermissionByAccID(
			final RolePermissionMap rolePermissionMap) {
		// TODO Auto-generated method stub
		RolePermissionMapDaoImpl.log
				.info("Update Role Permission For Account ID "
						+ rolePermissionMap.getAccount().getAccId());
		return this.update(rolePermissionMap);
	}

	@Override
	public boolean deleteRolePermissionByAccID(
			final RolePermissionMap rolePermissionMap) {
		// TODO Auto-generated method stub
		RolePermissionMapDaoImpl.log
				.info("Delete Role Permission For Account ID "
						+ rolePermissionMap.getAccount().getAccId());
		return this.delete(rolePermissionMap);
	}

	@Override
	public List<RolePermissionMap> searchPermissionByRole(final Role role) {
//		final Criteria criteria = AbstractHibernateDaoSupport.getSession()
//				.createCriteria(RolePermissionMap.class);
//		criteria.add(Restrictions.eq("role", role));
//		criteria.setProjection(Projections
//				.projectionList()
//				// .add(Projections.property(RolePermissionMapDao.PERMISSION),"permission").add(Projections.groupProperty(RolePermissionMapDao.PERMISSION)));
//				.add(Projections.groupProperty(RolePermissionMapDao.PERMISSION)));
//		List<RolePermissionMap> results = criteria.list();
		List<RolePermissionMap> results = new ArrayList<RolePermissionMap>();
		try {
			final String queryString = "from RolePermissionMap "
					+ " as model where model."
					+ RolePermissionMapDaoImpl.ROLE + "= :propertyVal"
					+ " group by model." + RolePermissionMapDaoImpl.PERMISSION ;
			final Query queryObject = AbstractHibernateDaoSupport.getSession()
					.createQuery(queryString);
			queryObject.setParameter("propertyVal", role);
			results = queryObject.list();
		} catch (final RuntimeException re) {
			re.printStackTrace();
		}
		return results;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see dao.RolePermissionMapDao#searchPermissionByAccount(model.Account)
	 */
	@Override
	public List<RolePermissionMap> searchPermissionByAccount(Account account) {
		// TODO Auto-generated method stub
		final List<RolePermissionMap> results = this.findByProperty(
				RolePermissionMapDao.ACCOUNT, account);
		return results;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * dao.RolePermissionMapDao#findRolePermissionByAccountAndPermission(model
	 * .Account, model.Permission)
	 */
	@Override
	public RolePermissionMap findRolePermissionByAccountAndPermission(
			Account account, Permission permission) {
		// TODO Auto-generated method stub

		// final Criteria criteria =
		// AbstractHibernateDaoSupport.getSession().createCriteria(RolePermissionMap.class);
		// Criterion accountCri = Restrictions.eq(RolePermissionMapDao.ACCOUNT,
		// account);
		// Criterion permissionCri =
		// Restrictions.eq(RolePermissionMapDao.PERMISSION, permission);
		// criteria.add(Restrictions.and(accountCri, permissionCri));
		// RolePermissionMap result =
		// (RolePermissionMap)criteria.uniqueResult();
		RolePermissionMap result = new RolePermissionMap();
		try {
			final String queryString = "from RolePermissionMap "
					+ " as model where model."
					+ RolePermissionMapDaoImpl.ACCOUNT + "= :propertyVal1"
					+ " and model." + RolePermissionMapDaoImpl.PERMISSION
					+ "= :propertyVal2";
			final Query queryObject = AbstractHibernateDaoSupport.getSession()
					.createQuery(queryString);
			queryObject.setParameter("propertyVal1", account);
			queryObject.setParameter("propertyVal2", permission);
			result = (RolePermissionMap) queryObject.uniqueResult();
		} catch (final RuntimeException re) {
			re.printStackTrace();
		}
		return result;
	}

}
