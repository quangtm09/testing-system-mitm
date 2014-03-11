package business.impl;

import java.util.List;

import dao.PermissionDAO;

import model.Permission;
import business.ManagePermission;

public class ManagePermissionImpl implements ManagePermission{
	private PermissionDAO permissionDAO;
	
	
	public PermissionDAO getPermissionDAO() {
		return permissionDAO;
	}

	public void setPermissionDAO(PermissionDAO permissionDAO) {
		this.permissionDAO = permissionDAO;
	}

	@Override
	public List<Permission> getPermissions() {
		return permissionDAO.findAll();
	}

	@Override
	public Permission getPermissionByID(long permissionId) {
		return permissionDAO.findById(permissionId);
	}

}
