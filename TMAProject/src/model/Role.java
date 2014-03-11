package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Role implements Serializable{
	private static final long serialVersionUID = -294756845485982170L;
	private long roleId;
	private String roleName;
	private List<Permission> permissionLists=new ArrayList<Permission>();
	
	public Role(){}
	
	public Role(String roleName) {
		super();
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

	public List<Permission> getPermissionLists() {
		return permissionLists;
	}

	public void setPermissionLists(List<Permission> permissionLists) {
		this.permissionLists = permissionLists;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
