package controller.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RoleBean implements Serializable{
	private long roleId;
	private String roleName;
	private String viewPermission;
	private String loginPermission;
	private String editPermission;
	private String managePermission;
	
	
	public RoleBean(){}
	
	public RoleBean(long roleId, String roleName, String viewPermission,
			String loginPermission, String editPermission,
			String managePermission) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.viewPermission = viewPermission;
		this.loginPermission = loginPermission;
		this.editPermission = editPermission;
		this.managePermission = managePermission;
	}



	public RoleBean(String roleName) {
		this.roleName = roleName;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getViewPermission() {
		return viewPermission;
	}

	public void setViewPermission(String viewPermission) {
		this.viewPermission = viewPermission;
	}

	public String getLoginPermission() {
		return loginPermission;
	}

	public void setLoginPermission(String loginPermission) {
		this.loginPermission = loginPermission;
	}

	public String getEditPermission() {
		return editPermission;
	}

	public void setEditPermission(String editPermission) {
		this.editPermission = editPermission;
	}

	public String getManagePermission() {
		return managePermission;
	}

	public void setManagePermission(String managePermission) {
		this.managePermission = managePermission;
	}

	
}
