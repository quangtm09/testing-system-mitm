package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the permission database table.
 * 
 */
@Entity
@Table(name="permission")
@NamedQuery(name="Permission.findAll", query="SELECT p FROM Permission p")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;
	private int permissionId;
	private String permissionDesc;
	private String permissionName;
	private byte permissionValue;
	private List<RolePermissionMap> rolePermissionMaps;

	public Permission() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PERMISSION_ID", unique=true, nullable=false)
	public int getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}


	@Lob
	@Column(name="PERMISSION_DESC")
	public String getPermissionDesc() {
		return this.permissionDesc;
	}

	public void setPermissionDesc(String permissionDesc) {
		this.permissionDesc = permissionDesc;
	}


	@Column(name="PERMISSION_NAME", nullable=false, length=255)
	public String getPermissionName() {
		return this.permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}


	@Column(name="PERMISSION_VALUE", nullable=false)
	public byte getPermissionValue() {
		return this.permissionValue;
	}

	public void setPermissionValue(byte permissionValue) {
		this.permissionValue = permissionValue;
	}


	//bi-directional many-to-one association to RolePermissionMap
	@OneToMany(mappedBy="permission")
	public List<RolePermissionMap> getRolePermissionMaps() {
		return this.rolePermissionMaps;
	}

	public void setRolePermissionMaps(List<RolePermissionMap> rolePermissionMaps) {
		this.rolePermissionMaps = rolePermissionMaps;
	}

	public RolePermissionMap addRolePermissionMap(RolePermissionMap rolePermissionMap) {
		getRolePermissionMaps().add(rolePermissionMap);
		rolePermissionMap.setPermission(this);

		return rolePermissionMap;
	}

	public RolePermissionMap removeRolePermissionMap(RolePermissionMap rolePermissionMap) {
		getRolePermissionMaps().remove(rolePermissionMap);
		rolePermissionMap.setPermission(null);

		return rolePermissionMap;
	}

}