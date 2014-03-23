package dao.interfaces;

import java.util.List;

import model.Permission;

public interface PermissionDao {
	/*
	 * display all Permission
	 */
	List<Permission> getAllPermission();
}
