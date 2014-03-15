package model;

// default package
// Generated Mar 15, 2014 7:27:54 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RolePermissionMap generated by hbm2java
 */
@Entity
@Table(name = "role_permission_map")
public class RolePermissionMap implements java.io.Serializable {

	private Integer rolePermissionMapId;
	private Role role;
	private Account account;
	private Permission permission;
	private Date rolePermissionGrantedDate;

	public RolePermissionMap() {
	}

	public RolePermissionMap(Role role, Account account, Permission permission,
			Date rolePermissionGrantedDate) {
		this.role = role;
		this.account = account;
		this.permission = permission;
		this.rolePermissionGrantedDate = rolePermissionGrantedDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ROLE_PERMISSION_MAP_ID", unique = true, nullable = false)
	public Integer getRolePermissionMapId() {
		return this.rolePermissionMapId;
	}

	public void setRolePermissionMapId(Integer rolePermissionMapId) {
		this.rolePermissionMapId = rolePermissionMapId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID")
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACC_ID")
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERMISSION_ID")
	public Permission getPermission() {
		return this.permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ROLE_PERMISSION_GRANTED_DATE", length = 19)
	public Date getRolePermissionGrantedDate() {
		return this.rolePermissionGrantedDate;
	}

	public void setRolePermissionGrantedDate(Date rolePermissionGrantedDate) {
		this.rolePermissionGrantedDate = rolePermissionGrantedDate;
	}

}
