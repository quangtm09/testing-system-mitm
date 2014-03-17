package com.bosch.rts.entity;

// Generated Nov 14, 2011 9:29:48 AM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.NotNull;

/**
 * Privilege generated by hbm2java.
 */
@Entity
@Table(name = "trts_privilege")
public class Privilege implements java.io.Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = -3072763338539731544L;
	
	/** The prv privilege id. */
	private Integer prvPrivilegeId;
	
	/** The prv privilege name. */
	private String prvPrivilegeName;
	
	/** The privilege transfers. */
	private Set<PrivilegeTransfer> privilegeTransfers = new HashSet<PrivilegeTransfer>(0);
	
	/** The role has privileges. */
	private Set<RoleHasPrivilege> roleHasPrivileges = new HashSet<RoleHasPrivilege>(0);

	/**
	 * Instantiates a new privilege.
	 */
	public Privilege() {
	}

	/**
	 * Instantiates a new privilege.
	 *
	 * @param prvPrivilegeName the prv privilege name
	 * @param privilegeTransfers the privilege transfers
	 * @param roleHasPrivileges the role has privileges
	 */
	public Privilege(String prvPrivilegeName,
			Set<PrivilegeTransfer> privilegeTransfers,
			Set<RoleHasPrivilege> roleHasPrivileges) {
		this.prvPrivilegeName = prvPrivilegeName;
		this.privilegeTransfers = privilegeTransfers;
		this.roleHasPrivileges = roleHasPrivileges;
	}

	/**
	 * Gets the prv privilege id.
	 *
	 * @return the prv privilege id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "prv_privilege_id", unique = true, nullable = false)
	public Integer getPrvPrivilegeId() {
		return this.prvPrivilegeId;
	}

	/**
	 * Sets the prv privilege id.
	 *
	 * @param prvPrivilegeId the new prv privilege id
	 */
	public void setPrvPrivilegeId(Integer prvPrivilegeId) {
		this.prvPrivilegeId = prvPrivilegeId;
	}

	/**
	 * Gets the prv privilege name.
	 *
	 * @return the prv privilege name
	 */
	@Column(name = "prv_privilege_name", nullable = false)
	@NotNull
	public String getPrvPrivilegeName() {
		return this.prvPrivilegeName;
	}

	/**
	 * Sets the prv privilege name.
	 *
	 * @param prvPrivilegeName the new prv privilege name
	 */
	public void setPrvPrivilegeName(String prvPrivilegeName) {
		this.prvPrivilegeName = prvPrivilegeName;
	}

	/**
	 * Gets the privilege transfers.
	 *
	 * @return the privilege transfers
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "privilege")
	public Set<PrivilegeTransfer> getPrivilegeTransfers() {
		return this.privilegeTransfers;
	}

	/**
	 * Sets the privilege transfers.
	 *
	 * @param privilegeTransfers the new privilege transfers
	 */
	public void setPrivilegeTransfers(Set<PrivilegeTransfer> privilegeTransfers) {
		this.privilegeTransfers = privilegeTransfers;
	}

	/**
	 * Gets the role has privileges.
	 *
	 * @return the role has privileges
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "privilege")
	public Set<RoleHasPrivilege> getRoleHasPrivileges() {
		return this.roleHasPrivileges;
	}

	/**
	 * Sets the role has privileges.
	 *
	 * @param roleHasPrivileges the new role has privileges
	 */
	public void setRoleHasPrivileges(Set<RoleHasPrivilege> roleHasPrivileges) {
		this.roleHasPrivileges = roleHasPrivileges;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return prvPrivilegeName;

	}

}
