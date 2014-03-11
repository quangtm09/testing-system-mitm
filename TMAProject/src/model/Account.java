package model;

import java.io.Serializable;

public class Account implements Serializable{
	private static final long serialVersionUID = -8752786123385660570L;
	private long accountId;
	private String accountName;
	private String password;
	private Role role;
	private Person person;
	
	public Account(){}
	
	public Account(String accountName, String password) {
		super();
		this.accountName = accountName;
		this.password = password;
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
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}
