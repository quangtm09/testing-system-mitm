package model;

import java.io.Serializable;

public class Permission implements Serializable{
	private static final long serialVersionUID = 3106004082241182856L;
	private long permissionId;
	private String permissionName;
	private Boolean permissionValue;
	
	public Permission(){};

	public Permission(String permissionName,Boolean permissionValue){
		this.permissionName=permissionName;
		this.permissionValue=permissionValue;
	}
	public long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(long permissionId) {
		this.permissionId = permissionId;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public Boolean getPermissionValue() {
		return permissionValue;
	}

	public void setPermissionValue(Boolean permissionValue) {
		this.permissionValue = permissionValue;
	}

	

}
