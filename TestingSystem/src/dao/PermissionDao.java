/**
 *
 */
package dao;

import model.Permission;

/**
 * @author huynh.ai.loan
 *
 */
public interface PermissionDao extends Dao<Permission, Integer> {
	public Permission getPermissionByID(Integer permissionID);

	public void addPermission(Permission permission);

	public boolean deletePermission(Permission permission);

	public boolean updatePermission(Permission permission);

}
