package dao.impl;

import java.util.List;

import model.Role;
import model.RolePermissionMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
		RolePermissionMapDaoImpl.log.info("Add Role Permission By Account ID "+ rolePermissionMap.getAccount().getAccId());
		this.save(rolePermissionMap);
	}

	@Override
	public boolean updateRolePermissionByAccID(
			final RolePermissionMap rolePermissionMap) {
		// TODO Auto-generated method stub
		RolePermissionMapDaoImpl.log.info("Update Role Permission For Account ID "+ rolePermissionMap.getAccount().getAccId());
		return this.update(rolePermissionMap);
	}

	@Override
	public boolean deleteRolePermissionByAccID(
			final RolePermissionMap rolePermissionMap) {
		// TODO Auto-generated method stub
		RolePermissionMapDaoImpl.log.info("Delete Role Permission For Account ID "+ rolePermissionMap.getAccount().getAccId());
		return this.delete(rolePermissionMap);
	}


	@Override
	public List<RolePermissionMap> searchPermissionByRole(final Role role) {
		final List<RolePermissionMap> results = this.findByProperty(RolePermissionMapDao.ROLE, role);
		return results;
	}

}
