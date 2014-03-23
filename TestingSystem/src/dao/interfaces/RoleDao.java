package dao.interfaces;

import java.util.List;

import model.Permission;

public interface RoleDao {
	/*
	 * list Permission by RoleID
	 */
	List<Permission> getPermissionbyRole();
}
