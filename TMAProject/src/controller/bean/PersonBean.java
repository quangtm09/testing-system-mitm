package controller.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PersonBean implements Serializable{
	private long personId;
	private String fullName;
	private String cellPhone;
	private String email;
	private long accountId;
	
	public PersonBean(){}
	
	public PersonBean(long personId,String fullName, String cellPhone, String email) {
		this.personId=personId;
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

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	
	
}
