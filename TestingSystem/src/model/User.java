package model;

// default package
// Generated Mar 15, 2014 7:27:54 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user")
public class User implements java.io.Serializable {

	private String userId;
	private String fname;
	private String lname;
	private String email;
	private String mobile;
	private Date bdate;
	private String address;
	private Set<Account> accounts = new HashSet<Account>(0);
//	private Set<Logs> logses = new HashSet<Logs>(0);

	public User() {
	}

	public User(String userId, String fname, String lname, String email,
			String mobile, Date bdate, String address) {
		this.userId = userId;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.mobile = mobile;
		this.bdate = bdate;
		this.address = address;
	}

	public User(String userId, String fname, String lname, String email,
			String mobile, Date bdate, String address, Set<Account> accounts) {
		this.userId = userId;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.mobile = mobile;
		this.bdate = bdate;
		this.address = address;
		this.accounts = accounts;
//		this.logses = logses;
	}

	@Id
	@Column(name = "USER_ID", unique = true, nullable = false, length = 10)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "FNAME", nullable = false, length = 100)
	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	@Column(name = "LNAME", length = 100)
	public String getLname() {
		return this.lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	@Column(name = "EMAIL", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "MOBILE", length = 12)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BDATE", length = 10)
	public Date getBdate() {
		return this.bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	@Column(name = "ADDRESS", length = 65535)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//	public Set<Logs> getLogses() {
//		return this.logses;
//	}
//
//	public void setLogses(Set<Logs> logses) {
//		this.logses = logses;
//	}

}
