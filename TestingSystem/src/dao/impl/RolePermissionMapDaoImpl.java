package dao.impl;

import java.util.List;

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

	protected RolePermissionMapDaoImpl() {
		super(RolePermissionMap.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<RolePermissionMap> searchPermissionByRole(final Integer roleId,
			final Integer permissionID) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public void addRolePermissonByAccID(
			final RolePermissionMap rolePermissionMap) {
		// TODO Auto-generated method stub
		log.info("Add Role Permission By Account ID "+ rolePermissionMap.getAccount().getAccId());
		this.save(rolePermissionMap);
	}

	@Override
	public boolean updateRolePermissionByAccID(
			final RolePermissionMap rolePermissionMap) {
		// TODO Auto-generated method stub
		log.info("Update Role Permission For Account ID "+ rolePermissionMap.getAccount().getAccId());
		return this.update(rolePermissionMap);
	}

	@Override
	public boolean deleteRolePermissionByAccID(
			final RolePermissionMap rolePermissionMap) {
		// TODO Auto-generated method stub
		log.info("Delete Role Permission For Account ID "+ rolePermissionMap.getAccount().getAccId());
		return this.delete(rolePermissionMap);
	}

}
