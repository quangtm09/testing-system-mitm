package test;

import java.util.List;

import model.Role;
import business.ManageRole;
import dao.RoleDAO;

public class RoleTest {
	private ManageRole manageRole;

	public ManageRole getManageRole() {
		return manageRole;
	}

	public void setManageRole(ManageRole manageRole) {
		this.manageRole = manageRole;
	}
	
	public void printAllRoles(){
		List<Role> roleList=manageRole.getRoles();
		for(Role role:roleList){
			System.out.println(role.getRoleName());
		}
	}
	
	public void getRoleById(long roleID){
		System.out.println(manageRole.findRoleById(roleID).getRoleName());
	}
}
