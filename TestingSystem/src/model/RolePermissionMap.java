package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the role_permission_map database table.
 * 
 */
@Entity
@Table(name="role_permission_map")
@NamedQuery(name="RolePermissionMap.findAll", query="SELECT r FROM RolePermissionMap r")
public class RolePermissionMap implements Serializable {
	private static final long serialVersionUID = 1L;
	private int rolePermissionMapId;
	private Timestamp rolePermissionGrantedDate;
	private Account account;
	private Permission permission;
	private Role role;

	public RolePermissionMap() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ROLE_PERMISSION_MAP_ID", unique=true, nullable=false)
	public int getRolePermissionMapId() {
		return this.rolePermissionMapId;
	}

	public void setRolePermissionMapId(int rolePermissionMapId) {
		this.rolePermissionMapId = rolePermissionMapId;
	}


	@Column(name="ROLE_PERMISSION_GRANTED_DATE")
	public Timestamp getRolePermissionGrantedDate() {
		return this.rolePermissionGrantedDate;
	}

	public void setRolePermissionGrantedDate(Timestamp rolePermissionGrantedDate) {
		this.rolePermissionGrantedDate = rolePermissionGrantedDate;
	}


	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="ACC_ID")
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}


	//bi-directional many-to-one association to Permission
	@ManyToOne
	@JoinColumn(name="PERMISSION_ID")
	public Permission getPermission() {
		return this.permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}


	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="ROLE_ID")
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}