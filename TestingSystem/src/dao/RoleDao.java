package dao;

import model.Role;

public interface RoleDao extends Dao<Role, Integer>{
	public boolean saveRole(Role role);
	public Role getRoleById(Integer roleId);
	public boolean updateRole(Role role);
	public boolean deleteRole(Role role);
}
