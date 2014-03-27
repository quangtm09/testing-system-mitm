package dao;

import java.util.List;

import model.Role;
import model.RolePermissionMap;

public interface RolePermissionMapDao extends Dao<RolePermissionMap, Integer> {
	public static final String ROLE = "role";

	public List<RolePermissionMap> searchPermissionByRole(Role role);

	public void addRolePermissonByAccID(
			RolePermissionMap rolePermissionMap);

	public boolean updateRolePermissionByAccID(
			RolePermissionMap rolePermissionMap);

	public boolean deleteRolePermissionByAccID(
			RolePermissionMap rolePermissionMap);
}
