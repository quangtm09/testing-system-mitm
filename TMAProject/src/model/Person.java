package model;

import java.io.Serializable;

public class Person implements Serializable{
	private static final long serialVersionUID = -344723665611336901L;
	private long personId;
	private String fullName;
	private String cellPhone;
	private String email;
	private Account account;
	
	public Person(){}
	
	public Person( String fullName,String cellPhone, String email) {
		super();
		this.fullName = fullName;
		this.cellPhone = cellPhone;
		this.email = email;
	}

	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
}
