package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name="role")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	private int roleId;
	private String roleDesc;
	private String roleName;
	private List<AccountRoleMap> accountRoleMaps;
	private List<RolePermissionMap> rolePermissionMaps;

	public Role() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ROLE_ID", unique=true, nullable=false)
	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	@Lob
	@Column(name="ROLE_DESC")
	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}


	@Column(name="ROLE_NAME", nullable=false, length=255)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	//bi-directional many-to-one association to AccountRoleMap
	@OneToMany(mappedBy="role")
	public List<AccountRoleMap> getAccountRoleMaps() {
		return this.accountRoleMaps;
	}

	public void setAccountRoleMaps(List<AccountRoleMap> accountRoleMaps) {
		this.accountRoleMaps = accountRoleMaps;
	}

	public AccountRoleMap addAccountRoleMap(AccountRoleMap accountRoleMap) {
		getAccountRoleMaps().add(accountRoleMap);
		accountRoleMap.setRole(this);

		return accountRoleMap;
	}

	public AccountRoleMap removeAccountRoleMap(AccountRoleMap accountRoleMap) {
		getAccountRoleMaps().remove(accountRoleMap);
		accountRoleMap.setRole(null);

		return accountRoleMap;
	}


	//bi-directional many-to-one association to RolePermissionMap
	@OneToMany(mappedBy="role")
	public List<RolePermissionMap> getRolePermissionMaps() {
		return this.rolePermissionMaps;
	}

	public void setRolePermissionMaps(List<RolePermissionMap> rolePermissionMaps) {
		this.rolePermissionMaps = rolePermissionMaps;
	}

	public RolePermissionMap addRolePermissionMap(RolePermissionMap rolePermissionMap) {
		getRolePermissionMaps().add(rolePermissionMap);
		rolePermissionMap.setRole(this);

		return rolePermissionMap;
	}

	public RolePermissionMap removeRolePermissionMap(RolePermissionMap rolePermissionMap) {
		getRolePermissionMaps().remove(rolePermissionMap);
		rolePermissionMap.setRole(null);

		return rolePermissionMap;
	}

}