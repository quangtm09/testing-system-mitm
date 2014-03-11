package dao;

import model.Permission;

public interface PermissionDAO extends Dao<Permission, Long>{
	public Permission getPermissionById(long permissionId);
}
