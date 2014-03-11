package business.impl;

import java.util.List;

import dao.RoleDAO;

import model.Role;
import business.ManageRole;

public class ManageRoleImpl implements ManageRole{
	private RoleDAO roleDAO;
	
	
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	@Override
	public List<Role> getRoles() {
		return roleDAO.findAll();
	}

	@Override
	public Role findRoleById(long roleId) {
		return roleDAO.findById(roleId);
	}

	@Override
	public Role findRoleByName(String roleName) {
		return roleDAO.getRoleByName(roleName);
	}

	@Override
	public boolean updateRole(Role role) {
		// TODO Auto-generated method stub
		return roleDAO.update(role);
	}

}
