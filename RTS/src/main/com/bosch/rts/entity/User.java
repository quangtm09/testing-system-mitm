package com.bosch.rts.entity;

// Generated Nov 14, 2011 9:29:48 AM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.Email;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * User generated by hbm2java.
 */
@Entity
@Table(name = "trts_user")
public class User implements java.io.Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = -3463861803425840860L;

	/** The usr user id. */
	private Integer usrUserId;
	
	/** The org unit. */
	private OrgUnit orgUnit;
	
	/** The usr user name. */
	private String usrUserName;
	
	/** The usr full name. */
	private String usrFullName;
	
	/** The usr email. */
	private String usrEmail;
	
	/** The usr prefix. */
	private String usrPrefix;
	
	/** The usr id number. */
	private String usrIdNumber;
	
	/** The created on. */
	private Date createdOn;
	
	/** The created by. */
	private String createdBy;
	
	/** The updated on. */
	private Date updatedOn;
	
	/** The updated by. */
	private String updatedBy;
	
	/** The approved by. */
	private String approvedBy;
	
	/** The approved nn. */
	private Date approvedOn;
	
	/** The status. */
	private Boolean approved;
	
	/** The status. */
	private Boolean status;

	/** The user has interview schedules. */
	private Set<UserHasInterviewSchedule> userHasInterviewSchedules = new HashSet<UserHasInterviewSchedule>(0);
	
	/** The recruit requests. */
	private Set<RecruitRequest> recruitRequests = new HashSet<RecruitRequest>(0);
	
	/** The privilege transfers for rot usr from user id. */
	private Set<PrivilegeTransfer> privilegeTransfersForRotUsrFromUserId = new HashSet<PrivilegeTransfer>(0);
	
	/** The user has user roles. */
	private Set<UserHasUserRole> userHasUserRoles = new HashSet<UserHasUserRole>(0);
	
	/** The org units. */
	private Set<OrgUnit> orgUnits = new HashSet<OrgUnit>(0);
	
	/** The privilege transfers for rot usr to user id. */
	private Set<PrivilegeTransfer> privilegeTransfersForRotUsrToUserId = new HashSet<PrivilegeTransfer>(0);
	
	/** The candidates. */
	private Set<Candidate> candidates = new HashSet<Candidate>(0);
	
	/** The privilege list. */
	private List<String> privilegeList = new ArrayList<String>();

	/**
	 * Instantiates a new user.
	 */
	public User() {
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param usrUserName the usr user name
	 * @param usrFullName the usr full name
	 * @param usrEmail the usr email
	 * @param usrPrefix the usr prefix
	 * @param usrIdnumber the usr idnumber
	 * @param userHasInterviewSchedules the user has interview schedules
	 * @param recruitRequests the recruit requests
	 * @param privilegeTransfersForRotUsrFromUserId the privilege transfers for rot usr from user id
	 * @param userHasUserRoles the user has user roles
	 * @param privilegeTransfersForRotUsrToUserId the privilege transfers for rot usr to user id
	 * @param candidates the candidates
	 */
	public User(String usrUserName, String usrFullName, String usrEmail,
			String usrPrefix, String usrIdnumber,
			Set<UserHasInterviewSchedule> userHasInterviewSchedules,
			Set<RecruitRequest> recruitRequests,
			Set<PrivilegeTransfer> privilegeTransfersForRotUsrFromUserId,
			Set<UserHasUserRole> userHasUserRoles,		
			Set<PrivilegeTransfer> privilegeTransfersForRotUsrToUserId,
			Set<Candidate> candidates) {
		this.usrUserName = usrUserName;
		this.usrFullName = usrFullName;
		this.usrEmail = usrEmail;
		this.usrPrefix = usrPrefix;
		this.usrIdNumber = usrIdnumber;
		this.userHasInterviewSchedules = userHasInterviewSchedules;
		this.recruitRequests = recruitRequests;
		this.privilegeTransfersForRotUsrFromUserId = privilegeTransfersForRotUsrFromUserId;
		this.userHasUserRoles = userHasUserRoles;
		this.privilegeTransfersForRotUsrToUserId = privilegeTransfersForRotUsrToUserId;
		this.candidates = candidates;
	}

	/**
	 * Gets the usr id number.
	 *
	 * @return the usr id number
	 */
	@Column(name = "usr_id_number", unique = true, nullable = true)
	@Length(max = 12)
	public String getUsrIdNumber() {
		return usrIdNumber;
	}

	/**
	 * Sets the usr id number.
	 *
	 * @param usrIdNumber the new usr id number
	 */
	public void setUsrIdNumber(String usrIdNumber) {
		this.usrIdNumber = usrIdNumber;
	}

	/**
	 * Gets the usr user id.
	 *
	 * @return the usr user id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "usr_user_id", unique = true, nullable = false)
	public Integer getUsrUserId() {
		return this.usrUserId;
	}

	/**
	 * Sets the usr user id.
	 *
	 * @param usrUserId the new usr user id
	 */
	public void setUsrUserId(Integer usrUserId) {
		this.usrUserId = usrUserId;
	}

	/**
	 * Gets the usr user name.
	 *
	 * @return the usr user name
	 */
	@Column(name = "usr_user_name", length = 15)
	@Length(max = 15)
	@NotNull
	public String getUsrUserName() {
		return this.usrUserName;
	}

	/**
	 * Sets the usr user name.
	 *
	 * @param usrUserName the new usr user name
	 */
	public void setUsrUserName(String usrUserName) {
		this.usrUserName = usrUserName;
	}

	/**
	 * Gets the usr full name.
	 *
	 * @return the usr full name
	 */
	@Column(name = "usr_full_name", length = 50)
	@Length(max = 50)
	@NotNull
	public String getUsrFullName() {
		return this.usrFullName;
	}

	/**
	 * Sets the usr full name.
	 *
	 * @param usrFullName the new usr full name
	 */
	public void setUsrFullName(String usrFullName) {
		this.usrFullName = usrFullName;
	}

	/**
	 * Gets the usr email.
	 *
	 * @return the usr email
	 */
	@Column(name = "usr_email", length = 50)
	@Length(max = 50)
	@Email
	@NotNull
	public String getUsrEmail() {
		return this.usrEmail;
	}

	/**
	 * Sets the usr email.
	 *
	 * @param usrEmail the new usr email
	 */
	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}

	/**
	 * Gets the usr prefix.
	 *
	 * @return the usr prefix
	 */
	@Column(name = "usr_prefix", length = 5)
	@Length(max = 5)
	@NotNull
	public String getUsrPrefix() {
		return this.usrPrefix;
	}

	/**
	 * Sets the usr prefix.
	 *
	 * @param usrPrefix the new usr prefix
	 */
	public void setUsrPrefix(String usrPrefix) {
		this.usrPrefix = usrPrefix;
	}

	/**
	 * Gets the user has interview schedules.
	 *
	 * @return the user has interview schedules
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserHasInterviewSchedule> getUserHasInterviewSchedules() {
		return this.userHasInterviewSchedules;
	}

	/**
	 * Sets the user has interview schedules.
	 *
	 * @param userHasInterviewSchedules the new user has interview schedules
	 */
	public void setUserHasInterviewSchedules(
			Set<UserHasInterviewSchedule> userHasInterviewSchedules) {
		this.userHasInterviewSchedules = userHasInterviewSchedules;
	}

	/**
	 * Gets the recruit requests.
	 *
	 * @return the recruit requests
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<RecruitRequest> getRecruitRequests() {
		return this.recruitRequests;
	}

	/**
	 * Sets the recruit requests.
	 *
	 * @param recruitRequests the new recruit requests
	 */
	public void setRecruitRequests(Set<RecruitRequest> recruitRequests) {
		this.recruitRequests = recruitRequests;
	}

	/**
	 * Gets the privilege transfers for rot usr from user id.
	 *
	 * @return the privilege transfers for rot usr from user id
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByRotUsrFromUserId")
	public Set<PrivilegeTransfer> getPrivilegeTransfersForRotUsrFromUserId() {
		return this.privilegeTransfersForRotUsrFromUserId;
	}

	/**
	 * Sets the privilege transfers for rot usr from user id.
	 *
	 * @param privilegeTransfersForRotUsrFromUserId the new privilege transfers for rot usr from user id
	 */
	public void setPrivilegeTransfersForRotUsrFromUserId(
			Set<PrivilegeTransfer> privilegeTransfersForRotUsrFromUserId) {
		this.privilegeTransfersForRotUsrFromUserId = privilegeTransfersForRotUsrFromUserId;
	}

	/**
	 * Gets the user has user roles.
	 *
	 * @return the user has user roles
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserHasUserRole> getUserHasUserRoles() {
		return this.userHasUserRoles;
	}

	/**
	 * Sets the user has user roles.
	 *
	 * @param userHasUserRoles the new user has user roles
	 */
	public void setUserHasUserRoles(Set<UserHasUserRole> userHasUserRoles) {
		this.userHasUserRoles = userHasUserRoles;
	}

	/**
	 * Gets the org units.
	 *
	 * @return the org units
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "manager")
	public Set<OrgUnit> getOrgUnits() {
		return orgUnits;
	}

	/**
	 * Sets the org units.
	 *
	 * @param orgUnits the new org units
	 */
	public void setOrgUnits(Set<OrgUnit> orgUnits) {
		this.orgUnits = orgUnits;
	}

	/**
	 * Gets the org unit.
	 *
	 * @return the org unit
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usr_org_unit_id")
	public OrgUnit getOrgUnit() {
		return orgUnit;
	}

	/**
	 * Sets the org unit.
	 *
	 * @param orgUnit the new org unit
	 */
	public void setOrgUnit(OrgUnit orgUnit) {
		this.orgUnit = orgUnit;
	}

	/**
	 * Gets the privilege transfers for rot usr to user id.
	 *
	 * @return the privilege transfers for rot usr to user id
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByRotUsrToUserId")
	public Set<PrivilegeTransfer> getPrivilegeTransfersForRotUsrToUserId() {
		return this.privilegeTransfersForRotUsrToUserId;
	}

	/**
	 * Sets the privilege transfers for rot usr to user id.
	 *
	 * @param privilegeTransfersForRotUsrToUserId the new privilege transfers for rot usr to user id
	 */
	public void setPrivilegeTransfersForRotUsrToUserId(
			Set<PrivilegeTransfer> privilegeTransfersForRotUsrToUserId) {
		this.privilegeTransfersForRotUsrToUserId = privilegeTransfersForRotUsrToUserId;
	}

	/**
	 * Gets the candidates.
	 *
	 * @return the candidates
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Candidate> getCandidates() {
		return this.candidates;
	}

	/**
	 * Sets the candidates.
	 *
	 * @param candidates the new candidates
	 */
	public void setCandidates(Set<Candidate> candidates) {
		this.candidates = candidates;
	}

	/**
	 * Gets the privilege list.
	 *
	 * @return the privilege list
	 */
	@Transient
	public List<String> getPrivilegeList() {
		if (this.privilegeList == null) {
			this.privilegeList = new ArrayList<String>();
		} else {
			this.privilegeList.clear();
		}
		for (UserHasUserRole userHasUserRole : this.userHasUserRoles) {
			Set<RoleHasPrivilege> roleHasPrivileges = userHasUserRole.getUserRole().getRoleHasPrivileges();
			for (RoleHasPrivilege rhP : roleHasPrivileges) {
				if (!this.privilegeList.contains(rhP.getPrivilege().getPrvPrivilegeName())) {
					this.privilegeList.add(rhP.getPrivilege().getPrvPrivilegeName().replace("_", " "));

					//khb1hc: disable sort
					Collections.sort(this.privilegeList);
				}
			}
		}
		return this.privilegeList;
	}

	/**
	 * Sets the privilege list.
	 *
	 * @param privilegeList the new privilege list
	 */
	public void setPrivilegeList(List<String> privilegeList) {
		this.privilegeList = privilegeList;
	}

	/**
	 * Equal.
	 *
	 * @param user1 the user1
	 * @param user2 the user2
	 * @return true, if successful
	 */
	public boolean equal(User user1, User user2) {
		boolean result = false;
		if (user1.getUsrUserName() != null
				&& user2.getUsrUserName() != null
				&& user1.getUsrUserName().equalsIgnoreCase(
						user2.getUsrUserName())) {
			result = true;
		}
		return result;
	}
	
	/**
	 * Gets the created on.
	 *
	 * @return the createdOn
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "created_on", length = 0)
	public Date getCreatedOn() {
		return this.createdOn;
	}

	/**
	 * Sets the created on.
	 *
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the createdBy
	 */
	@Column(name = "created_by", nullable = false, length = 12)
	@NotNull
	@Length(max = 12)
	public String getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the updated on.
	 *
	 * @return the updatedOn
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_on", length = 0)
	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	/**
	 * Sets the updated on.
	 *
	 * @param updatedOn the updatedOn to set
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * Gets the updated by.
	 *
	 * @return the updatedBy
	 */
	@Column(name = "updated_by", length = 12)
	@Length(max = 12)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	/**
	 * Sets the updated by.
	 *
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	/**
	 * Gets the updated by.
	 *
	 * @return the updatedBy
	 */
	@Column(name = "approved_by", length = 12)
	@Length(max = 12)
	public String getApprovedBy() {
		return this.approvedBy;
	}

	/**
	 * Sets the updated by.
	 *
	 * @param updatedBy the updatedBy to set
	 */
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	
	/**
	 * Gets the user roles.
	 *
	 * @return the user roles
	 */
	@Transient
	public List<UserRole> getUserRoles(){
		List<UserRole> userRoles = new ArrayList<UserRole>();
		final Set<UserHasUserRole> userHasUserRoles = this.getUserHasUserRoles();
		if(userHasUserRoles != null && !userHasUserRoles.isEmpty()){
			for(UserHasUserRole userHasUserRole : userHasUserRoles){
				final UserRole userRole = userHasUserRole.getUserRole();
				userRoles.add(userRole);
			}
		}
		
		return userRoles;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	@Column(name = "status")
	public Boolean getStatus() {
		return status;
	}

	public void setApprovedOn(Date approvedOn) {
		this.approvedOn = approvedOn;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "approved_on", length = 19)
	public Date getApprovedOn() {
		return approvedOn;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	@Column(name = "approved")
	public Boolean getApproved() {
		return approved;
	}
	
}