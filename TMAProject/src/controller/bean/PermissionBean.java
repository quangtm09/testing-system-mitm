package controller.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PermissionBean implements Serializable{
	private long permissionId;
	private String permissionName;
	private Boolean permissionValue;
	
	public PermissionBean(){};

	public PermissionBean(String permissionName,Boolean permissionValue){
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
