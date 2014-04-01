package dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import model.Permission;
import model.User;
import dao.AbstractHibernateDaoSupport;
import dao.PermissionDao;

public class PermissionDaoImpl extends
		AbstractHibernateDaoSupport<Permission, Integer> implements
		PermissionDao {
	private static final Logger log = Logger.getLogger(PermissionDaoImpl.class);
	public PermissionDaoImpl() {
		super(Permission.class);
	}

	@Override
	public Permission getPermissionByID(final Integer permissionID) {
		// TODO Auto-generated method stub
		log.info("Get Permission by ID: "+ permissionID);
		return this.findById(permissionID);
	}

	@Override
	public void addPermission(final Permission permission) {
		// TODO Auto-generated method stub
		log.info("Add New Permission "+ permission.getPermissionId());
		this.save(permission);
	}

	@Override
	public boolean deletePermission(final Permission permission) {
		// TODO Auto-generated method stub
		log.info("Delete Permission: "+ permission.getPermissionId());
		return this.delete(permission);
	}

	@Override
	public boolean updatePermission(final Permission permission) {
		// TODO Auto-generated method stub
		log.info("Update Permission"+ permission.getPermissionId());
		return this.update(permission);
	}

}
