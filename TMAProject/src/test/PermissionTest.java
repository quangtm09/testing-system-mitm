package test;

import java.util.List;

import model.Permission;
import business.ManagePermission;

public class PermissionTest {
	private ManagePermission managePermission;

	public ManagePermission getManagePermission() {
		return managePermission;
	}

	public void setManagePermission(ManagePermission managePermission) {
		this.managePermission = managePermission;
	}
	
	public void printAllPermission(){
		List<Permission> permissList=managePermission.getPermissions();
		for(Permission permission:permissList){
			System.out.println(permission.getPermissionName());
		}
	}
	
	public void getPermissionByID(long permissionId){
		System.out.println(managePermission.getPermissionByID(permissionId).getPermissionName());
	}
}
