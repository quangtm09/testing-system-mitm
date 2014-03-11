package controller.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AccountBean implements Serializable{
	private long accountId;
	private String accountName;
	private String password;
	private String roleName;
	private long personId;
	
	public AccountBean(){}
	
	public AccountBean(long accountId, String accountName, String password,
			String roleName, long personId) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.password = password;
		this.roleName = roleName;
		this.personId = personId;
	}


	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
