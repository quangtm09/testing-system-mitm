/**
 * 
 */
package dao.interfaces;

import java.util.List;

/**
 * @author loanh
 *
 */
public interface RolePermissionMapDao {
	/*
	 * assign Role and Permission for Account
	 */
	public void insertRoleAndPermissionByAccID(String accountID, int roleID, int permissionID);
	
	/*
	 * list Role and Permission for account
	 */
	List<RolePermissionMapDao> getPermissionForAccID(String accountID);
	
	/*
	 * update Role and Permission for Account
	 */
	public void updateRoleAndPermissionByAccID(String accountID, int roleID, int permissionID);
	
	/*
	 * delete All Role and Permission by AccID
	 */
	public void deleteRoleAndPermissionByAccID(String accountID);
	
}
