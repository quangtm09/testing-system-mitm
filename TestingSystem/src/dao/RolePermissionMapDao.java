package dao;

import java.util.List;

import model.Account;
import model.Permission;
import model.Role;
import model.RolePermissionMap;

public interface RolePermissionMapDao extends Dao<RolePermissionMap, Integer> {
	public static final String ROLE = "role";
	public static final String ACCOUNT ="account";
	public static final String PERMISSION ="permission";

	public List<RolePermissionMap> searchPermissionByRole(Role role);

	public void addRolePermissonByAccID(
			RolePermissionMap rolePermissionMap);

	public boolean updateRolePermissionByAccID(
			RolePermissionMap rolePermissionMap);

	public boolean deleteRolePermissionByAccID(
			RolePermissionMap rolePermissionMap);
	
	public List<RolePermissionMap> searchPermissionByAccount(Account account);
	
	public RolePermissionMap findRolePermissionByAccountAndPermission(Account account, Permission permission);
}
