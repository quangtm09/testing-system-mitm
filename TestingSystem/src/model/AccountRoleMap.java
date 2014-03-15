package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the account_role_map database table.
 * 
 */
@Entity
@Table(name="account_role_map")
@NamedQuery(name="AccountRoleMap.findAll", query="SELECT a FROM AccountRoleMap a")
public class AccountRoleMap implements Serializable {
	private static final long serialVersionUID = 1L;
	private int accRoleId;
	private Timestamp accRoleGrantedDate;
	private Account account1;
	private Account account2;
	private Role role;

	public AccountRoleMap() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ACC_ROLE_ID", unique=true, nullable=false)
	public int getAccRoleId() {
		return this.accRoleId;
	}

	public void setAccRoleId(int accRoleId) {
		this.accRoleId = accRoleId;
	}


	@Column(name="ACC_ROLE_GRANTED_DATE")
	public Timestamp getAccRoleGrantedDate() {
		return this.accRoleGrantedDate;
	}

	public void setAccRoleGrantedDate(Timestamp accRoleGrantedDate) {
		this.accRoleGrantedDate = accRoleGrantedDate;
	}


	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="ACC_ID")
	public Account getAccount1() {
		return this.account1;
	}

	public void setAccount1(Account account1) {
		this.account1 = account1;
	}


	//bi-directional many-to-one association to Account
	@ManyToOne
	@JoinColumn(name="CREATOR_ACC_ROLE_ID")
	public Account getAccount2() {
		return this.account2;
	}

	public void setAccount2(Account account2) {
		this.account2 = account2;
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