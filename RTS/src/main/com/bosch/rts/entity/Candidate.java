/*
 * com.bosch.rts.entity.Candidate.java
 */
package com.bosch.rts.entity;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import org.apache.commons.beanutils.BeanComparator;
import org.hibernate.validator.Email;
import org.hibernate.validator.Length;
import org.hibernate.validator.Max;
import org.hibernate.validator.Min;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Pattern;

/**
 * Candidate Entity
 */
@Entity
@Table(name = "trts_candidate")
public class Candidate implements java.io.Serializable, Comparable<Candidate> {
	
	/** serialVersionUID. */
	private static final long serialVersionUID = 5934541561262155895L;
	
	/** The cdd candidate id. */
	private Integer cddCandidateId;
	
	/** The recruit request. */
	private RecruitRequest recruitRequest;
	
	/** The user. */
	private User user;
	
	/** The cdd name. */
	private String cddName;
	
	/** The cdd identity card. */
	private String cddIdentityCard;
	
/*	*//** The cdd dob. *//*
	private Date cddDob;*/
	
	private String cddDob;
	
	/** The cdd email. */
	private String cddEmail;
	
	/** The cdd address. */
	private String cddAddress;
	
	/** The cdd degree1. */
	private String cddDegree1;
	
	/** The cdd degree2. */
	private String cddDegree2;
	
	/** The cdd degree3. */
	private String cddDegree3;
	
	/** The cdd expected position. */
	private String cddExpectedPosition;
	
	/** The cdd year of experience. */
	private Float cddYearOfExperience;
	
	/** The cdd relevant experience. */
	private Float cddRelevantExperience;
	
	/** The cdd source. */
	private String cddSource;
	
	/** The cdd phone no. */
	private String cddPhoneNo;
	
	/** The cdd status. */
	private CandidateStatus cddStatus;
	
	/** The cdd priority. */
	private Integer cddPriority;
	
	/** The cdd current level. */
	private String cddCurrentLevel;
	
	/** The cdd prefix. */
	private String cddPrefix;
	
	/** The cdd graduation university. */
	private String cddGraduationUniversity;
	
	/** The cdd graduation university2. */
	private String cddGraduationUniversity2;
	
	/** The cdd graduation university3. */
	private String cddGraduationUniversity3;
	
	/** The cdd graduation faculty. */
	private String cddGraduationFaculty;
	
	/** The cdd skill description. */
	private String cddSkillDescription;
	
	/** The cdd skill set. */
	private String cddSkillSet;
	
	/** The cdd year of graduation. */
	private Date cddYearOfGraduation;
	
	/** The cdd year of graduation2. */
	private Date cddYearOfGraduation2;
	
	/** The cdd year of graduation3. */
	private Date cddYearOfGraduation3;
	
	/** The cdd request number. */
	private Integer cddRequestNumber;
	
	/** The ccd cv name. */
	private String ccdCVName;
	
	/** The cdd joining date. */
	private Date cddJoiningDate;
	
	/** The cdd created date. */
	private Date cddCreatedDate;
	
	/** The J p_ consultancy_ name. */
	private String JP_Consultancy_Name;
	
	/** The cdd employee name. */
	private String cddEmployeeName;
	
	/** The cdd employee id. */
	private String cddEmployeeId;
	
	/** The cdd created by. */
	private String cddCreatedBy;
	
	/** The cdd updated by. */
	private String cddUpdatedBy;
	
	/** The cdd updated date. */
	private Date cddUpdatedDate;

	/** The interview histories. */
	private Set<InterviewHistory> interviewHistories = new HashSet<InterviewHistory>(0);
	
	/** The candidate has skills. */
	private Set<CandidateHasSkill> candidateHasSkills = new HashSet<CandidateHasSkill>(0);
	
	/** The interview schedules. */
	private Set<InterviewSchedule> interviewSchedules = new HashSet<InterviewSchedule>(0);	
	
	/** The attachments. */
	private Set<CandidateHasAttachment> attachments  = new HashSet<CandidateHasAttachment>();
	
	/** The current company. */
	private String currentCompany = null; //	ccd_current_company
	
	/** The current position. */
	private String currentPosition = null; //	ccd_current_position	
		
	/** The position applied. */
	private String positionApplied = null; //	ccd_position_applied
	
	/** The time of cv received. */
	private Date timeOfCvReceived = null; //	ccd_time_of_CV_received
	
	/** The time of cv sent. */
	private Date timeOfCvSent = null; //	ccd_time_of_CV_sent
	
	/** The handled by. */
	private String handledBy = null; //	cdd_handled_by

	/** The short list by. */
	private String shortListBy = null; //	cdd_short_list_by
	
	/** The short list result. */
	private String shortListResult = null; //	cdd_short_list_result
	
	/** The tested on. */
	private Date testedOn = null; //	cdd_tested_on
	
	/** The tested result. */
	private String testedResult = null; //	cdd_test_result	
	
	/** The graduation time. */
	private Integer graduationTime;  //cdd_graduation_time	
			
	/**
	 * Gets the time of CV received.
	 *
	 * @return the timeOfCvReceived
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ccd_time_of_CV_received", length = 19)
	public Date getTimeOfCvReceived() {
		return this.timeOfCvReceived;
	}

	/**
	 * Instantiates a new candidate.
	 *
	 * @param cddCandidateId the cdd candidate id
	 * @param recruitRequest the recruit request
	 * @param user the user
	 * @param cddName the cdd name
	 * @param cddIdentityCard the cdd identity card
	 * @param cddDob the cdd dob
	 * @param cddEmail the cdd email
	 * @param cddAddress the cdd address
	 * @param cddDegree1 the cdd degree1
	 * @param cddDegree2 the cdd degree2
	 * @param cddDegree3 the cdd degree3
	 * @param cddExpectedPosition the cdd expected position
	 * @param cddYearOfExperience the cdd year of experience
	 * @param cddRelevantExperience the cdd relevant experience
	 * @param cddSource the cdd source
	 * @param cddPhoneNo the cdd phone no
	 * @param cddStatus the cdd status
	 * @param cddPriority the cdd priority
	 * @param cddCurrentLevel the cdd current level
	 * @param cddPrefix the cdd prefix
	 * @param cddGraduationUniversity the cdd graduation university
	 * @param cddGraduationUniversity2 the cdd graduation university2
	 * @param cddGraduationUniversity3 the cdd graduation university3
	 * @param cddGraduationFaculty the cdd graduation faculty
	 * @param cddSkillDescription the cdd skill description
	 * @param cddSkillSet the cdd skill set
	 * @param cddYearOfGraduation the cdd year of graduation
	 * @param cddYearOfGraduation2 the cdd year of graduation2
	 * @param cddYearOfGraduation3 the cdd year of graduation3
	 * @param cddRequestNumber the cdd request number
	 * @param ccdCVName the ccd cv name
	 * @param cddJoiningDate the cdd joining date
	 * @param cddCreatedDate the cdd created date
	 * @param jP_Consultancy_Name the j p_ consultancy_ name
	 * @param cddEmployeeName the cdd employee name
	 * @param cddEmployeeId the cdd employee id
	 * @param cddCreatedBy the cdd created by
	 * @param cddUpdatedBy the cdd updated by
	 * @param cddUpdatedDate the cdd updated date
	 * @param interviewHistories the interview histories
	 * @param candidateHasSkills the candidate has skills
	 * @param interviewSchedules the interview schedules
	 * @param attachments the attachments
	 * @param currentCompany the current company
	 * @param currentPosition the current position
	 * @param positionApplied the position applied
	 * @param timeOfCvReceived the time of cv received
	 * @param timeOfCvSent the time of cv sent
	 * @param handledBy the handled by
	 * @param shortListBy the short list by
	 * @param shortListResult the short list result
	 * @param testedOn the tested on
	 * @param testedResult the tested result
	 * @param orgUnit the org unit
	 * @param graduationTime the graduation time
	 */
	public Candidate(Integer cddCandidateId, RecruitRequest recruitRequest,
			User user, String cddName, String cddIdentityCard, String cddDob,
			String cddEmail, String cddAddress, String cddDegree1,
			String cddDegree2, String cddDegree3, String cddExpectedPosition,
			Float cddYearOfExperience, Float cddRelevantExperience,
			String cddSource, String cddPhoneNo, CandidateStatus cddStatus,
			Integer cddPriority, String cddCurrentLevel, String cddPrefix,
			String cddGraduationUniversity, String cddGraduationUniversity2,
			String cddGraduationUniversity3, String cddGraduationFaculty,
			String cddSkillDescription, String cddSkillSet,
			Date cddYearOfGraduation, Date cddYearOfGraduation2,
			Date cddYearOfGraduation3, Integer cddRequestNumber,
			String ccdCVName, Date cddJoiningDate, Date cddCreatedDate,
			String jP_Consultancy_Name, String cddEmployeeName,
			String cddEmployeeId, String cddCreatedBy, String cddUpdatedBy,
			Date cddUpdatedDate, Set<InterviewHistory> interviewHistories,
			Set<CandidateHasSkill> candidateHasSkills,
			Set<InterviewSchedule> interviewSchedules,
			Set<CandidateHasAttachment> attachments, String currentCompany,
			String currentPosition, String positionApplied,
			Date timeOfCvReceived, Date timeOfCvSent, String handledBy,
			String shortListBy, String shortListResult, Date testedOn,
			String testedResult, OrgUnit orgUnit, Integer graduationTime) {
		super();
		this.cddCandidateId = cddCandidateId;
		this.recruitRequest = recruitRequest;
		this.user = user;
		this.cddName = cddName;
		this.cddIdentityCard = cddIdentityCard;
		this.setCddDob(cddDob);
		this.cddEmail = cddEmail;
		this.cddAddress = cddAddress;
		this.cddDegree1 = cddDegree1;
		this.cddDegree2 = cddDegree2;
		this.cddDegree3 = cddDegree3;
		this.cddExpectedPosition = cddExpectedPosition;
		this.cddYearOfExperience = cddYearOfExperience;
		this.cddRelevantExperience = cddRelevantExperience;
		this.cddSource = cddSource;
		this.cddPhoneNo = cddPhoneNo;
		this.cddStatus = cddStatus;
		this.cddPriority = cddPriority;
		this.cddCurrentLevel = cddCurrentLevel;
		this.cddPrefix = cddPrefix;
		this.cddGraduationUniversity = cddGraduationUniversity;
		this.cddGraduationUniversity2 = cddGraduationUniversity2;
		this.cddGraduationUniversity3 = cddGraduationUniversity3;
		this.cddGraduationFaculty = cddGraduationFaculty;
		this.cddSkillDescription = cddSkillDescription;
		this.cddSkillSet = cddSkillSet;
		this.cddYearOfGraduation = cddYearOfGraduation;
		this.cddYearOfGraduation2 = cddYearOfGraduation2;
		this.cddYearOfGraduation3 = cddYearOfGraduation3;
		this.cddRequestNumber = cddRequestNumber;
		this.ccdCVName = ccdCVName;
		this.cddJoiningDate = cddJoiningDate;
		this.cddCreatedDate = cddCreatedDate;
		this.JP_Consultancy_Name = jP_Consultancy_Name;
		this.cddEmployeeName = cddEmployeeName;
		this.cddEmployeeId = cddEmployeeId;
		this.cddCreatedBy = cddCreatedBy;
		this.cddUpdatedBy = cddUpdatedBy;
		this.cddUpdatedDate = cddUpdatedDate;
		this.interviewHistories = interviewHistories;
		this.candidateHasSkills = candidateHasSkills;
		this.interviewSchedules = interviewSchedules;
		this.attachments = attachments;
		this.currentCompany = currentCompany;
		this.currentPosition = currentPosition;
		this.positionApplied = positionApplied;
		this.timeOfCvReceived = timeOfCvReceived;
		this.timeOfCvSent = timeOfCvSent;
		this.handledBy = handledBy;
		this.shortListBy = shortListBy;
		this.shortListResult = shortListResult;
		this.testedOn = testedOn;
		this.testedResult = testedResult;
		this.orgUnit = orgUnit;
		this.graduationTime = graduationTime;
	}

	/**
	 * Sets the time of cv received.
	 *
	 * @param timeOfCvReceived the timeOfCvReceived to set
	 */
	public void setTimeOfCvReceived(Date timeOfCvReceived) {
		this.timeOfCvReceived = timeOfCvReceived;
	}

	/**
	 * Gets the time of cv sent.
	 *
	 * @return the timeOfCvSent
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ccd_time_of_CV_sent", length = 19)
	public Date getTimeOfCvSent() {
		return this.timeOfCvSent;
	}

	/**
	 * Sets the time of cv sent.
	 *
	 * @param timeOfCvSent the timeOfCvSent to set
	 */
	public void setTimeOfCvSent(Date timeOfCvSent) {
		this.timeOfCvSent = timeOfCvSent;
	}

	/**
	 * Gets the current company.
	 *
	 * @return the currentCompany
	 */
	@Column(name = "ccd_current_company", length = 50)
	@Length(max = 50)
	@Pattern(regex="[a-zA-Z[0-9]-/() ,.]*", message = "#{messages['invalid.candidate.current.company']}")
	public String getCurrentCompany() {
		return this.currentCompany;
	}

	/**
	 * Sets the current company.
	 *
	 * @param currentCompany the currentCompany to set
	 */
	public void setCurrentCompany(String currentCompany) {
		this.currentCompany = currentCompany;
	}

	/**
	 * Gets the current position.
	 *
	 * @return the currentPosition
	 */
	@Column(name = "ccd_current_position", length = 50)
	@Length(max = 50)
	@Pattern(regex="[a-zA-Z[0-9]-/() ,.]*", message = "#{messages['invalid.candidate.current.position']}")
	public String getCurrentPosition() {
		return this.currentPosition;
	}

	/**
	 * Sets the current position.
	 *
	 * @param currentPosition the currentPosition to set
	 */
	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}	

	/**
	 * Gets the position applied.
	 *
	 * @return the positionApplied
	 */
	@Column(name = "ccd_position_applied", length = 50)
	@Length(max = 50)
	public String getPositionApplied() {
		return this.positionApplied;
	}

	/**
	 * Sets the position applied.
	 *
	 * @param positionApplied the positionApplied to set
	 */
	public void setPositionApplied(String positionApplied) {
		this.positionApplied = positionApplied;
	}
	
	/**
	 * Gets the handled by.
	 *
	 * @return the handledBy
	 */
	@Column(name = "cdd_handled_by", length = 50)
	@Length(max = 50)
	public String getHandledBy() {
		return this.handledBy;
	}

	/**
	 * Sets the handled by.
	 *
	 * @param handledBy the handledBy to set
	 */
	public void setHandledBy(String handledBy) {
		this.handledBy = handledBy;
	}

	/**
	 * Gets the short list by.
	 *
	 * @return the shortListBy
	 */
	@Column(name = "cdd_short_list_by", length = 50)
	@Length(max = 50)
	public String getShortListBy() {
		return this.shortListBy;
	}

	/**
	 * Sets the short list by.
	 *
	 * @param shortListBy the shortListBy to set
	 */
	public void setShortListBy(String shortListBy) {
		this.shortListBy = shortListBy;
	}

	/**
	 * Gets the short list result.
	 *
	 * @return the shortListResult
	 */
	@Column(name = "cdd_short_list_result", length = 50)
	@Length(max = 50)
	public String getShortListResult() {
		return this.shortListResult;
	}

	/**
	 * Sets the short list result.
	 *
	 * @param shortListResult the shortListResult to set
	 */
	public void setShortListResult(String shortListResult) {
		this.shortListResult = shortListResult;
	}

	/**
	 * Gets the tested on.
	 *
	 * @return the testedOn
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cdd_tested_on", length = 19)
	public Date getTestedOn() {
		return this.testedOn;
	}

	/**
	 * Sets the tested on.
	 *
	 * @param testedOn the testedOn to set
	 */
	public void setTestedOn(Date testedOn) {
		this.testedOn = testedOn;
	}

	/**
	 * Gets the tested result.
	 *
	 * @return the testedResult
	 */
	@Column(name = "cdd_tested_result", length = 50)
	@Length(max = 50)
	public String getTestedResult() {
		return this.testedResult;
	}

	/**
	 * Sets the tested result.
	 *
	 * @param testedResult the testedResult to set
	 */
	public void setTestedResult(String testedResult) {
		this.testedResult = testedResult;
	}

	/**
	 * Instantiates a new candidate.
	 */
	public Candidate() {
		cddYearOfExperience = Float.valueOf(0);
	}	

	/**
	 * Instantiates a new candidate.
	 *
	 * @param cddName the cdd name
	 * @param cddEmail the cdd email
	 * @param cddStatus the cdd status
	 * @param cddPrefix the cdd prefix
	 * @param cddCreatedDate the cdd created date
	 * @param cddCreatedBy the cdd created by
	 * @param orgUnit the org unit
	 */
	public Candidate(String cddName, String cddEmail,
			CandidateStatus cddStatus, String cddPrefix, Date cddCreatedDate,
			String cddCreatedBy, OrgUnit orgUnit) {
		super();
		this.cddName = cddName;
		this.cddEmail = cddEmail;
		this.cddStatus = cddStatus;
		this.cddPrefix = cddPrefix;
		this.cddCreatedDate = cddCreatedDate;
		this.cddCreatedBy = cddCreatedBy;
		this.orgUnit = orgUnit;
	}

	/**
	 * Gets the cdd candidate id.
	 *
	 * @return the cdd candidate id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cdd_candidate_id", unique = true, nullable = false)
	public Integer getCddCandidateId() {
		return this.cddCandidateId;
	}
	
	/**
	 * Gets the attachments.
	 *
	 * @return the attachments
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "candidate", cascade = CascadeType.ALL)
	public Set<CandidateHasAttachment> getAttachments() {
		return attachments;
	}

	/**
	 * Sets the attachments.
	 *
	 * @param attachments the new attachments
	 */
	public void setAttachments(Set<CandidateHasAttachment> attachments) {
		this.attachments = attachments;
	}

	/**
	 * Sets the cdd candidate id.
	 *
	 * @param cddCandidateId the new cdd candidate id
	 */
	public void setCddCandidateId(Integer cddCandidateId) {
		this.cddCandidateId = cddCandidateId;
	}

	/**
	 * Gets the recruit request.
	 *
	 * @return the recruit request
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cdd_candidate_recruit_request")
	public RecruitRequest getRecruitRequest() {
		return this.recruitRequest;
	}

	/**
	 * Sets the recruit request.
	 *
	 * @param recruitRequest the new recruit request
	 */
	public void setRecruitRequest(RecruitRequest recruitRequest) {
		this.recruitRequest = recruitRequest;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cdd_referral_user_id")
	public User getUser() {
		return this.user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the cdd name.
	 *
	 * @return the cdd name
	 */
	@Column(name = "cdd_name", length = 50)
	@NotNull
	@Pattern(regex="[a-zA-Z. 0-9]*", message = "#{messages['invalid.candidate.name']}")
	@Length(max = 50)
	public String getCddName() {
		return this.cddName;
	}

	/**
	 * Sets the cdd name.
	 *
	 * @param cddName the new cdd name
	 */
	public void setCddName(String cddName) {
		this.cddName = cddName;
	}

	/**
	 * Gets the cdd identity card.
	 *
	 * @return the cdd identity card
	 */
	@Column(name = "cdd_identity_card", length = 35)
	@Pattern(regex="[0-9]*", message = "#{messages['invalid.candidate.identity.number']}")
	@Length(max = 9)
	public String getCddIdentityCard() {
		return this.cddIdentityCard;
	}

	/**
	 * Sets the cdd identity card.
	 *
	 * @param cddIdentityCard the new cdd identity card
	 */
	public void setCddIdentityCard(String cddIdentityCard) {
		this.cddIdentityCard = cddIdentityCard;
	}

	/**
	 * Gets the cdd dob.
	 *
	 * @return the cdd dob
	 */
	/*@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cdd_dob", length = 0)
	public Date getCddDob() {
		return this.cddDob;
	}

	*//**
	 * Sets the cdd dob.
	 *
	 * @param cddDob the new cdd dob
	 *//*
	public void setCddDob(Date cddDob) {
		this.cddDob = cddDob;
	}*/

	/**
	 * Gets the cdd email.
	 *
	 * @return the cdd email
	 */
	@Column(name = "cdd_email", length = 50)
	@Length(max=50)
	@NotNull
	@Email
	public String getCddEmail() {
		return this.cddEmail;
	}

	/**
	 * Sets the cdd email.
	 *
	 * @param cddEmail the new cdd email
	 */
	public void setCddEmail(String cddEmail) {
		this.cddEmail = cddEmail;
	}

	/**
	 * Gets the cdd address.
	 *
	 * @return the cdd address
	 */
	@Column(name = "cdd_address", length = 100)
	@Pattern(regex="[a-zA-Z[0-9]-/() ,.]*", message = "#{messages['invalid.candidate.address']}")
	@Length(max=100)
	public String getCddAddress() {
		return this.cddAddress;
	}

	/**
	 * Sets the cdd address.
	 *
	 * @param cddAddress the new cdd address
	 */
	public void setCddAddress(String cddAddress) {
		this.cddAddress = cddAddress;
	}

	/**
	 * Gets the cdd degree1.
	 *
	 * @return the cdd degree1
	 */
	@Column(name = "cdd_degree1", length = 30)
	public String getCddDegree1() {
		return this.cddDegree1;
	}

	/**
	 * Sets the cdd degree1.
	 *
	 * @param cddDegree1 the new cdd degree1
	 */
	public void setCddDegree1(String cddDegree1) {
		this.cddDegree1 = cddDegree1;
	}

	/**
	 * Gets the cdd degree2.
	 *
	 * @return the cdd degree2
	 */
	@Column(name = "cdd_degree2", length = 30)
	public String getCddDegree2() {
		return this.cddDegree2;
	}

	/**
	 * Sets the cdd degree2.
	 *
	 * @param cddDegree2 the new cdd degree2
	 */
	public void setCddDegree2(String cddDegree2) {
		this.cddDegree2 = cddDegree2;
	}

	/**
	 * Gets the cdd degree3.
	 *
	 * @return the cdd degree3
	 */
	@Column(name = "cdd_degree3", length = 30)
	public String getCddDegree3() {
		return this.cddDegree3;
	}

	/**
	 * Sets the cdd degree3.
	 *
	 * @param cddDegree3 the new cdd degree3
	 */
	public void setCddDegree3(String cddDegree3) {
		this.cddDegree3 = cddDegree3;
	}

	/**
	 * Gets the cdd expected position.
	 *
	 * @return the cdd expected position
	 */
	@Column(name = "cdd_expected_position", length = 20)
	public String getCddExpectedPosition() {
		return this.cddExpectedPosition;
	}

	/**
	 * Sets the cdd expected position.
	 *
	 * @param cddExpectedPosition the new cdd expected position
	 */
	public void setCddExpectedPosition(String cddExpectedPosition) {
		this.cddExpectedPosition = cddExpectedPosition;
	}

	/**
	 * Gets the cdd year of experience.
	 *
	 * @return the cdd year of experience
	 */
	@Column(name = "cdd_year_of_experience")
	public Float getCddYearOfExperience() {
		return this.cddYearOfExperience;
	}

	/**
	 * Sets the cdd year of experience.
	 *
	 * @param cddYearOfExperience the new cdd year of experience
	 */
	public void setCddYearOfExperience(Float cddYearOfExperience) {
		this.cddYearOfExperience = cddYearOfExperience;
	}

	/**
	 * Gets the cdd relevant experience.
	 *
	 * @return the cdd relevant experience
	 */
	@Column(name = "cdd_relevant_experience")
//	@Pattern(regex="[[0-9].]*", message = "#{messages['invalid.candidate.overall.relevant.experience']}")
	public Float getCddRelevantExperience() {
		return cddRelevantExperience;
	}

	/**
	 * Sets the cdd relevant experience.
	 *
	 * @param cddRelevantExperience the new cdd relevant experience
	 */
	public void setCddRelevantExperience(Float cddRelevantExperience) {
		this.cddRelevantExperience = cddRelevantExperience;
	}

	/**
	 * Gets the cdd source.
	 *
	 * @return the cdd source
	 */
	@Column(name = "cdd_source", length = 25)
	public String getCddSource() {
		return this.cddSource;
	}

	/**
	 * Sets the cdd source.
	 *
	 * @param cddSource the new cdd source
	 */
	public void setCddSource(String cddSource) {
		this.cddSource = cddSource;
	}

	/**
	 * Gets the cdd phone no.
	 *
	 * @return the cdd phone no
	 */
	@Column(name = "cdd_phone_no", length = 100)
	@Length(max = 100)
	public String getCddPhoneNo() {
		return this.cddPhoneNo;
	}

	/**
	 * Sets the cdd phone no.
	 *
	 * @param cddPhoneNo the new cdd phone no
	 */
	public void setCddPhoneNo(String cddPhoneNo) {
		this.cddPhoneNo = cddPhoneNo;
	}

	/**
	 * Gets the cdd status.
	 *
	 * @return the cdd status
	 */
	@Column(name = "cdd_status", length = 20)
	@Enumerated(EnumType.STRING)
	@NotNull
	public CandidateStatus getCddStatus() {
		return this.cddStatus;
	}

	/**
	 * Sets the cdd status.
	 *
	 * @param cddStatus the new cdd status
	 */
	public void setCddStatus(CandidateStatus cddStatus) {
		this.cddStatus = cddStatus;
	}

	/**
	 * Gets the cdd priority.
	 *
	 * @return the cdd priority
	 */
	@Column(name = "cdd_priority")
	@Max(value = 4)
	@Min(value = 1)
	public Integer getCddPriority() {
		return this.cddPriority;
	}

	/**
	 * Sets the cdd priority.
	 *
	 * @param cddPriority the new cdd priority
	 */
	public void setCddPriority(Integer cddPriority) {
		this.cddPriority = cddPriority;
	}

	/**
	 * Gets the cdd current level.
	 *
	 * @return the cdd current level
	 */
	@Column(name = "cdd_current_level", length = 30)
	public String getCddCurrentLevel() {
		return this.cddCurrentLevel;
	}

	/**
	 * Sets the cdd current level.
	 *
	 * @param cddCurrentLevel the new cdd current level
	 */
	public void setCddCurrentLevel(String cddCurrentLevel) {
		this.cddCurrentLevel = cddCurrentLevel;
	}

	/**
	 * Gets the cdd prefix.
	 *
	 * @return the cdd prefix
	 */
	@Column(name = "cdd_prefix", length = 5)
	@NotNull
	public String getCddPrefix() {
		return this.cddPrefix;
	}

	/**
	 * Sets the cdd prefix.
	 *
	 * @param cddPrefix the new cdd prefix
	 */
	public void setCddPrefix(String cddPrefix) {
		this.cddPrefix = cddPrefix;
	}

	/**
	 * Gets the cdd graduation university.
	 *
	 * @return the cdd graduation university
	 */
	@Column(name = "cdd_graduation_university", length = 30)
	public String getCddGraduationUniversity() {
		return this.cddGraduationUniversity;
	}

	/**
	 * Sets the cdd graduation university.
	 *
	 * @param cddGraduationUniversity the new cdd graduation university
	 */
	public void setCddGraduationUniversity(String cddGraduationUniversity) {
		this.cddGraduationUniversity = cddGraduationUniversity;
	}

	/**
	 * Gets the cdd graduation university2.
	 *
	 * @return the cdd graduation university2
	 */
	@Column(name = "cdd_graduation_university2", length = 30)
	public String getCddGraduationUniversity2() {
		return this.cddGraduationUniversity2;
	}

	/**
	 * Sets the cdd graduation university2.
	 *
	 * @param cddGraduationUniversity the new cdd graduation university2
	 */
	public void setCddGraduationUniversity2(String cddGraduationUniversity) {
		this.cddGraduationUniversity2 = cddGraduationUniversity;
	}

	/**
	 * Gets the cdd graduation university3.
	 *
	 * @return the cdd graduation university3
	 */
	@Column(name = "cdd_graduation_university3", length = 30)
	public String getCddGraduationUniversity3() {
		return this.cddGraduationUniversity3;
	}

	/**
	 * Sets the cdd graduation university3.
	 *
	 * @param cddGraduationUniversity the new cdd graduation university3
	 */
	public void setCddGraduationUniversity3(String cddGraduationUniversity) {
		this.cddGraduationUniversity3 = cddGraduationUniversity;
	}

	/**
	 * Gets the cdd graduation faculty.
	 *
	 * @return the cdd graduation faculty
	 */
	@Column(name = "cdd_graduation_faculty", length = 20)
	public String getCddGraduationFaculty() {
		return this.cddGraduationFaculty;
	}

	/**
	 * Sets the cdd graduation faculty.
	 *
	 * @param cddGraduationFaculty the new cdd graduation faculty
	 */
	public void setCddGraduationFaculty(String cddGraduationFaculty) {
		this.cddGraduationFaculty = cddGraduationFaculty;
	}

	/**
	 * Gets the cdd skill description.
	 *
	 * @return the cdd skill description
	 */
	@Column(name = "cdd_skill_description", length = 500)
	@Length(max = 500)
	@Pattern(regex="[a-zA-Z[0-9]-/() ,.]*", message = "#{messages['invalid.candidate.current.description']}")
	public String getCddSkillDescription() {
		return this.cddSkillDescription;
	}

	/**
	 * Sets the cdd skill description.
	 *
	 * @param cddSkillDescription the new cdd skill description
	 */
	public void setCddSkillDescription(String cddSkillDescription) {
		this.cddSkillDescription = cddSkillDescription;
	}

	/**
	 * Gets the cdd skill set.
	 *
	 * @return the cdd skill set
	 */
	@Column(name = "cdd_skill_set", length = 400)
	public String getCddSkillSet() {
		return cddSkillSet;
	}

	/**
	 * Sets the cdd skill set.
	 *
	 * @param cddSkillSet the new cdd skill set
	 */
	public void setCddSkillSet(String cddSkillSet) {
		this.cddSkillSet = cddSkillSet;
	}

	/**
	 * Gets the cdd year of graduation.
	 *
	 * @return the cdd year of graduation
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cdd_year_of_graduation", length = 19)
	public Date getCddYearOfGraduation() {
		return this.cddYearOfGraduation;
	}

	/**
	 * Sets the cdd year of graduation.
	 *
	 * @param cddYearOfGraduation the new cdd year of graduation
	 */
	public void setCddYearOfGraduation(Date cddYearOfGraduation) {
		this.cddYearOfGraduation = cddYearOfGraduation;
	}

	/**
	 * Gets the cdd year of graduation2.
	 *
	 * @return the cdd year of graduation2
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cdd_year_of_graduation2", length = 19)
	public Date getCddYearOfGraduation2() {
		return this.cddYearOfGraduation2;
	}

	/**
	 * Sets the cdd year of graduation2.
	 *
	 * @param cddYearOfGraduation the new cdd year of graduation2
	 */
	public void setCddYearOfGraduation2(Date cddYearOfGraduation) {
		this.cddYearOfGraduation2 = cddYearOfGraduation;
	}

	/**
	 * Gets the cdd year of graduation3.
	 *
	 * @return the cdd year of graduation3
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cdd_year_of_graduation3", length = 19)
	public Date getCddYearOfGraduation3() {
		return this.cddYearOfGraduation3;
	}

	/**
	 * Sets the cdd year of graduation3.
	 *
	 * @param cddYearOfGraduation the new cdd year of graduation3
	 */
	public void setCddYearOfGraduation3(Date cddYearOfGraduation) {
		this.cddYearOfGraduation3 = cddYearOfGraduation;
	}

	/**
	 * Gets the cdd request number.
	 *
	 * @return the cdd request number
	 */
	@Column(name = "cdd_request_number", nullable = true)
	public Integer getCddRequestNumber() {
		return this.cddRequestNumber;
	}

	/**
	 * Sets the cdd request number.
	 *
	 * @param cddRequestNumber the new cdd request number
	 */
	public void setCddRequestNumber(Integer cddRequestNumber) {
		this.cddRequestNumber = cddRequestNumber;
	}

	/**
	 * Gets the cdd joining date.
	 *
	 * @return the cdd joining date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cdd_joining_date", length = 19)
	public Date getCddJoiningDate() {
		return cddJoiningDate;
	}

	/**
	 * Sets the cdd joining date.
	 *
	 * @param cddJoiningDate the new cdd joining date
	 */
	public void setCddJoiningDate(Date cddJoiningDate) {
		this.cddJoiningDate = cddJoiningDate;
	}

	/**
	 * Gets the interview histories.
	 *
	 * @return the interview histories
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "candidate")
	public Set<InterviewHistory> getInterviewHistories() {
		return this.interviewHistories;
	}

	/**
	 * Sets the interview histories.
	 *
	 * @param interviewHistories the new interview histories
	 */
	public void setInterviewHistories(Set<InterviewHistory> interviewHistories) {
		this.interviewHistories = interviewHistories;
	}

	/**
	 * Gets the candidate has skills.
	 *
	 * @return the candidate has skills
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "candidate")
	public Set<CandidateHasSkill> getCandidateHasSkills() {
		return this.candidateHasSkills;
	}

	/**
	 * Sets the candidate has skills.
	 *
	 * @param candidateHasSkills the new candidate has skills
	 */
	public void setCandidateHasSkills(Set<CandidateHasSkill> candidateHasSkills) {
		this.candidateHasSkills = candidateHasSkills;
	}

	/**
	 * Gets the interview schedules.
	 *
	 * @return the interview schedules
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "candidate")
	public Set<InterviewSchedule> getInterviewSchedules() {
		return this.interviewSchedules;
	}

	/**
	 * Sets the interview schedules.
	 *
	 * @param interviewSchedules the new interview schedules
	 */
	public void setInterviewSchedules(Set<InterviewSchedule> interviewSchedules) {
		this.interviewSchedules = interviewSchedules;
	}

	/**
	 * Gets the ccd cv name.
	 *
	 * @return the ccd cv name
	 */
	@Column(name = "ccd_cv_name", nullable = true, unique = true)
	public String getCcdCVName() {
		return ccdCVName;
	}

	/**
	 * Sets the ccd cv name.
	 *
	 * @param ccdCVName the new ccd cv name
	 */
	public void setCcdCVName(String ccdCVName) {
		this.ccdCVName = ccdCVName;
	}

	/**
	 * Gets the j p_ consultancy_ name.
	 *
	 * @return the j p_ consultancy_ name
	 */
	@Column(name = "cdd_JP_Consultancy_Name", length = 100)
	public String getJP_Consultancy_Name() {
		return JP_Consultancy_Name;
	}

	/**
	 * Sets the jp_ consultancy_ name.
	 *
	 * @param jP_Consultancy_Name the new JP consultancy name
	 */
	public void setJP_Consultancy_Name(String jP_Consultancy_Name) {
		JP_Consultancy_Name = jP_Consultancy_Name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return cddName + " - " + cddEmail;
	}
	
	/** The org unit. */
	private OrgUnit orgUnit;
	
	/**
	 * Gets the org unit.
	 *
	 * @return the org unit
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cdd_org_unit_id")
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
	 * Gets the cdd created date.
	 *
	 * @return the cdd created date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cdd_created_date", length = 19)
	public Date getCddCreatedDate() {
		return cddCreatedDate;
	}

	/**
	 * Sets the cdd created date.
	 *
	 * @param cddCreatedDate the new cdd created date
	 */
	public void setCddCreatedDate(Date cddCreatedDate) {
		this.cddCreatedDate = cddCreatedDate;
	}	
	
	/**
	 * Gets the cdd updated date.
	 *
	 * @return the cdd updated date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cdd_updated_date", length = 19)
	public Date getCddUpdatedDate() {
		return cddUpdatedDate;
	}

	/**
	 * Sets the cdd updated date.
	 *
	 * @param cddUpdatedDate the new cdd updated date
	 */
	public void setCddUpdatedDate(Date cddUpdatedDate) {
		this.cddUpdatedDate = cddUpdatedDate;
	}
	
	/**
	 * Gets the cdd created by.
	 *
	 * @return the cdd created by
	 */
	@Column(name = "cdd_created_by", length = 10)
	@Length(max = 10)
	@NotNull
	public String getCddCreatedBy() {
		return cddCreatedBy;
	}

	/**
	 * Sets the cdd created by.
	 *
	 * @param cddCreatedBy the new cdd created by
	 */
	public void setCddCreatedBy(String cddCreatedBy) {
		this.cddCreatedBy = cddCreatedBy;
	}
	
	/**
	 * Gets the cdd updated by.
	 *
	 * @return the cdd updated by
	 */
	@Column(name = "cdd_updated_by", length = 10)
	@Length(max = 10)
	public String getCddUpdatedBy() {
		return cddUpdatedBy;
	}

	/**
	 * Sets the cdd updated by.
	 *
	 * @param cddUpdatedBy the new cdd updated by
	 */
	public void setCddUpdatedBy(String cddUpdatedBy) {
		this.cddUpdatedBy = cddUpdatedBy;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Candidate o) {
		return this.cddName.compareToIgnoreCase(o.cddName);
	}

	/**
	 * Gets the cdd employee name.
	 *
	 * @return the cdd employee name
	 */
	@Column(name = "cdd_employee_name", length = 50)
	@Length(max = 50)
	@Pattern(regex="[a-zA-Z[0-9]-/(). ]*", message = "#{messages['invalid.candidate.employee.name']}")
	public String getCddEmployeeName() {
		return cddEmployeeName;
	}

	/**
	 * Sets the cdd employee name.
	 *
	 * @param cddEmployeeName the new cdd employee name
	 */
	public void setCddEmployeeName(String cddEmployeeName) {
		this.cddEmployeeName = cddEmployeeName;
	}

	/**
	 * Gets the cdd employee id.
	 *
	 * @return the cdd employee id
	 */
	@Column(name = "cdd_employee_id", length = 10)
	@Length(max = 10)
	@Pattern(regex="[a-zA-Z[0-9]]*", message = "#{messages['invalid.candidate.employee.id']}")
	public String getCddEmployeeId() {
		return cddEmployeeId;
	}

	/**
	 * Sets the cdd employee id.
	 *
	 * @param cddEmployeeId the new cdd employee id
	 */
	public void setCddEmployeeId(String cddEmployeeId) {
		this.cddEmployeeId = cddEmployeeId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || this.cddEmail == null || obj.getClass() != this.getClass()) {
			return false;
		}

		Candidate candidate = (Candidate) obj;
		return (this.cddEmail.equals(candidate.cddEmail));
	}

	/**
	 * Sets the graduation time.
	 *
	 * @param graduationTime the graduationTime to set
	 */
	public void setGraduationTime(Integer graduationTime) {
		this.graduationTime = graduationTime;
	}

	/**
	 * Gets the graduation time.
	 *
	 * @return the graduationTime
	 */
	@Column(name = "cdd_graduation_time")
	public Integer getGraduationTime() {
		return graduationTime;
	}

	public void setCddDob(String cddDob) {
		this.cddDob = cddDob;
	}

	@Column(name = "cdd_dob")
	public String getCddDob() {
		return cddDob;
	}
	
	@SuppressWarnings("unchecked")
	@Transient
	public TechnicalResult getLatestTechnicalResult(){
		final Set<InterviewSchedule> interviewSchedules = this.getInterviewSchedules();
		if(interviewSchedules != null && !interviewSchedules.isEmpty()){
			final List<InterviewSchedule> schedules = new ArrayList<InterviewSchedule>();
			schedules.addAll(interviewSchedules);
			BeanComparator interviewScheduleIdComparator = new BeanComparator();
			interviewScheduleIdComparator.setProperty("itsInterviewScheduleId");
			Collections.sort(schedules, interviewScheduleIdComparator);
			final InterviewSchedule interviewSchedule = schedules.get(schedules.size()-1);
			if(interviewSchedule != null){
				if(interviewSchedule.getTechnicalResultList() != null && !interviewSchedule.getTechnicalResultList().isEmpty()){								
					final TechnicalResult resultEntity = interviewSchedule.getTechnicalResultList().get(0);
					return resultEntity;
				}
			}	
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Transient
	public InterviewSchedule getLatestInterviewSchedule(){
		final Set<InterviewSchedule> interviewSchedules = this.getInterviewSchedules();
		if(interviewSchedules != null && !interviewSchedules.isEmpty()){
			final List<InterviewSchedule> schedules = new ArrayList<InterviewSchedule>();
			schedules.addAll(interviewSchedules);
			BeanComparator interviewScheduleIdComparator = new BeanComparator();
			interviewScheduleIdComparator.setProperty("itsInterviewScheduleId");
			Collections.sort(schedules, interviewScheduleIdComparator);
			return schedules.get(schedules.size()-1);			
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Transient
	public HrResult getLatestHrResult(){
		final Set<InterviewSchedule> interviewSchedules = this.getInterviewSchedules();
		if(interviewSchedules != null && !interviewSchedules.isEmpty()){
			final List<InterviewSchedule> schedules = new ArrayList<InterviewSchedule>();
			schedules.addAll(interviewSchedules);
			BeanComparator interviewScheduleIdComparator = new BeanComparator();
			interviewScheduleIdComparator.setProperty("itsInterviewScheduleId");
			Collections.sort(schedules, interviewScheduleIdComparator);
			final InterviewSchedule interviewSchedule = schedules.get(schedules.size()-1);
			if(interviewSchedule != null){
				if(interviewSchedule.getHrResults() != null && !interviewSchedule.getHrResults().isEmpty()){	
					return interviewSchedule.getHrResultList().get(0);
				}
			}	
		}
		
		return null;
	}
	
}