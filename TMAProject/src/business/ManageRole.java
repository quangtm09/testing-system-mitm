package business;

import java.util.List;

import model.Role;

public interface ManageRole {
	public List<Role> getRoles();
	public Role findRoleById(long roleId);
	public Role findRoleByName(String roleName);
	public boolean updateRole(Role role);
}
