package dao;

import model.Role;

public interface RoleDAO extends Dao<Role, Long>{
	public String ROLE_NAME="roleName";
	public Role getRolebyId(long roleId);
	public Role getRoleByName(String roleName);
	public boolean updateRole(Role role);
	
}
