package dao;

import java.util.List;

import model.RolePermissionMap;

public interface RolePermissionMapDao extends Dao<RolePermissionMap, Integer> {
	public List<RolePermissionMap> searchPermissionByRole(Integer roleId,
			Integer permissionID);

	public void addRolePermissonByAccID(
			RolePermissionMap rolePermissionMap);

	public boolean updateRolePermissionByAccID(
			RolePermissionMap rolePermissionMap);

	public boolean deleteRolePermissionByAccID(
			RolePermissionMap rolePermissionMap);
}
