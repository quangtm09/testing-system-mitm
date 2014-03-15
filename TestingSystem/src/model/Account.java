package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@Table(name="account")
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	private String accId;
	private String accPwd;
	private User user;
	private List<AccountRoleMap> accountRoleMaps1;
	private List<AccountRoleMap> accountRoleMaps2;
	private List<RolePermissionMap> rolePermissionMaps;

	public Account() {
	}


	@Id
	@Column(name="ACC_ID", unique=true, nullable=false, length=100)
	public String getAccId() {
		return this.accId;
	}

	public void setAccId(String accId) {
		this.accId = accId;
	}


	@Column(name="ACC_PWD", nullable=false, length=100)
	public String getAccPwd() {
		return this.accPwd;
	}

	public void setAccPwd(String accPwd) {
		this.accPwd = accPwd;
	}


	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER_ID")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	//bi-directional many-to-one association to AccountRoleMap
	@OneToMany(mappedBy="account1")
	public List<AccountRoleMap> getAccountRoleMaps1() {
		return this.accountRoleMaps1;
	}

	public void setAccountRoleMaps1(List<AccountRoleMap> accountRoleMaps1) {
		this.accountRoleMaps1 = accountRoleMaps1;
	}

	public AccountRoleMap addAccountRoleMaps1(AccountRoleMap accountRoleMaps1) {
		getAccountRoleMaps1().add(accountRoleMaps1);
		accountRoleMaps1.setAccount1(this);

		return accountRoleMaps1;
	}

	public AccountRoleMap removeAccountRoleMaps1(AccountRoleMap accountRoleMaps1) {
		getAccountRoleMaps1().remove(accountRoleMaps1);
		accountRoleMaps1.setAccount1(null);

		return accountRoleMaps1;
	}


	//bi-directional many-to-one association to AccountRoleMap
	@OneToMany(mappedBy="account2")
	public List<AccountRoleMap> getAccountRoleMaps2() {
		return this.accountRoleMaps2;
	}

	public void setAccountRoleMaps2(List<AccountRoleMap> accountRoleMaps2) {
		this.accountRoleMaps2 = accountRoleMaps2;
	}

	public AccountRoleMap addAccountRoleMaps2(AccountRoleMap accountRoleMaps2) {
		getAccountRoleMaps2().add(accountRoleMaps2);
		accountRoleMaps2.setAccount2(this);

		return accountRoleMaps2;
	}

	public AccountRoleMap removeAccountRoleMaps2(AccountRoleMap accountRoleMaps2) {
		getAccountRoleMaps2().remove(accountRoleMaps2);
		accountRoleMaps2.setAccount2(null);

		return accountRoleMaps2;
	}


	//bi-directional many-to-one association to RolePermissionMap
	@OneToMany(mappedBy="account")
	public List<RolePermissionMap> getRolePermissionMaps() {
		return this.rolePermissionMaps;
	}

	public void setRolePermissionMaps(List<RolePermissionMap> rolePermissionMaps) {
		this.rolePermissionMaps = rolePermissionMaps;
	}

	public RolePermissionMap addRolePermissionMap(RolePermissionMap rolePermissionMap) {
		getRolePermissionMaps().add(rolePermissionMap);
		rolePermissionMap.setAccount(this);

		return rolePermissionMap;
	}

	public RolePermissionMap removeRolePermissionMap(RolePermissionMap rolePermissionMap) {
		getRolePermissionMaps().remove(rolePermissionMap);
		rolePermissionMap.setAccount(null);

		return rolePermissionMap;
	}

}