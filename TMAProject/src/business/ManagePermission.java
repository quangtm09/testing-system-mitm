package business;

import java.util.List;

import model.Permission;

public interface ManagePermission {
	public List<Permission> getPermissions();
	public Permission getPermissionByID(long permissionId);
}
