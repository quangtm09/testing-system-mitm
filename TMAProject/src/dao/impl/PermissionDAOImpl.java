package dao.impl;

import model.Permission;
import dao.AbstractHibernateDaoSupport;
import dao.PermissionDAO;

public class PermissionDAOImpl extends AbstractHibernateDaoSupport<Permission, Long> implements PermissionDAO{

	protected PermissionDAOImpl() {
		super(Permission.class);
	}

	@Override
	public Permission getPermissionById(long permissionId) {
		return findById(permissionId);
	}

}
