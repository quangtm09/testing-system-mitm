package com.bosch.rts.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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

import org.hibernate.annotations.Sort;
import org.hibernate.validator.Length;
import org.hibernate.validator.Max;
import org.hibernate.validator.Min;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Pattern;

import com.bosch.rts.validator.AttachmentComparator;

/**
 * RecruitRequest entity.
 */
@Entity
@Table(name = "trts_recruit_request")
public class RecruitRequest implements java.io.Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 2674643117060384452L;

	/** The rcr recruit request id. */
	private Integer rcrRecruitRequestId;
	
	/** The user. */
	private User user;
	
	/** The description. */
	private String description;
	
	/** The skill description. */
	private String skillDescription;
	
	/** The recruit request name. */
	private String recruitRequestName;
	
	/** The fulfilled candidate last update. */
	private Date fulfilledCandidateLastUpdate;
	
	/** The additional skills. */
	private String additionalSkills;
	
	/** The additional training. */
	private String additionalTraining;
	
	/** The job position. */
	private String jobPosition;	
	
	/** The experience year. */
	private Integer experienceYear;
	
	/** The status. */
	private String status;
	
	/** The created by. */
	private String createdBy;
	
	/** The updated by. */
	private String updatedBy;
	
	/** The created date. */
	private Date createdDate;	
	
	/** The requested date. */
	private Date requestedDate;
	
	/** The closed date. */
	private Date closedDate;
	
	/** The approved date. */
	private Date approvedDate;
	
	/** The updated date. */
	private Date updatedDate;
	
	/** The org unit. */
	private OrgUnit orgUnit;	
	
	/** The number of persons. */
	private Integer numberOfPersons;
	
	/** The experience year from. */
	private Integer experienceYearFrom;
	
	/** The experience year to. */
	private Integer experienceYearTo;
	
	/** The qualifications. */
	private String qualifications;
	
	/** The handled by. */
	private String handledBy = null; //	rcr_handled_by
	
	/** The number recruited. */
	private Integer numberRecruited; //	rcr_number_recruited
	
	/** The number recruited. */
	private Integer numberToOffer; //	rcr_number_to_offer
	
	/** The number recruited. */
	private Integer numberOfferSent; //	rcr_number_offer_sent
	
	/** The number recruited. */
	private Integer numberOfferConfirmed; //	rcr_number_offer_confirmed 
	
	/** The number recruited. */
	private Integer numberOfferRefused; //	rcr_number_offer_refused 
	
	/** The number recruited. */
	private Integer numberCandidateJoined; //	rcr_number_candidate_joined 
	
	/** The approved by. */
	private User approvedBy;

	/** The attachments. */
	private Set<RecruitRequestHasAttachment> attachments  = new HashSet<RecruitRequestHasAttachment>();

	/**
	 * Gets the attachments.
	 *
	 * @return the attachments
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recruitRequest", cascade = CascadeType.ALL)
	@Sort(comparator=AttachmentComparator.class)
	public Set<RecruitRequestHasAttachment> getAttachments() {
		return attachments;
	}

	/**
	 * Sets the attachments.
	 *
	 * @param attachments the new attachments
	 */
	public void setAttachments(Set<RecruitRequestHasAttachment> attachments) {
		this.attachments = attachments;
	}
		
	/**
	 * Gets the org unit.
	 *
	 * @return the org unit
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rcr_org_unit_id")
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

	/** The interview histories. */
	private Set<InterviewHistory> interviewHistories = new HashSet<InterviewHistory>(0);

	/** The skill requirements. */
	private Set<SkillRequirement> skillRequirements = new HashSet<SkillRequirement>(0);

	/** The recruit request has skills. */
	private Set<RecruitRequestHasSkill> recruitRequestHasSkills = new HashSet<RecruitRequestHasSkill>(0);

	/** The interview schedules. */
	private Set<InterviewSchedule> interviewSchedules = new HashSet<InterviewSchedule>(0);

	/** The candidates. */
	private Set<Candidate> candidates = new HashSet<Candidate>(0);

	/**
	 * Instantiates a new recruit request.
	 */
	public RecruitRequest() {
		experienceYearFrom = 0;
		experienceYearTo = 0;
	}

	/**
	 * Instantiates a new recruit request.
	 *
	 * @param user the user
	 * @param numberOfCandidates the number of candidates
	 * @param fulfilledCandidate the fulfilled candidate
	 * @param description the description
	 * @param skillDescription the skill description
	 * @param recruitRequestName the recruit request name
	 * @param fulfilledCandidateLastUpdate the fulfilled candidate last update
	 * @param additionalSkills the additional skills
	 * @param additionalTraining the additional training
	 * @param jobPosition the job position
	 * @param qualifications the qualifications
	 * @param experienceYear the experience year
	 * @param status the status
	 * @param createdBy the created by
	 * @param updatedBy the updated by
	 * @param createdDate the created date
	 * @param requestedDate the requested date
	 * @param closedDate the closed date
	 * @param updatedDate the updated date
	 * @param interviewHistories the interview histories
	 * @param skillRequirements the skill requirements
	 * @param recruitRequestHasSkills the recruit request has skills
	 * @param interviewSchedules the interview schedules
	 * @param candidates the candidates
	 */
	public RecruitRequest(User user, 
			String description, String skillDescription, String recruitRequestName,
			Date fulfilledCandidateLastUpdate, String additionalSkills, String additionalTraining,
			String jobPosition, String qualifications, Integer experienceYear,
			String status, String createdBy, String updatedBy,
			Date createdDate/*, Date expectedDate*/, Date requestedDate, Date closedDate, Date updatedDate, 
			Set<InterviewHistory> interviewHistories, Set<SkillRequirement> skillRequirements,
			Set<RecruitRequestHasSkill> recruitRequestHasSkills, 
			Set<InterviewSchedule> interviewSchedules, Set<Candidate> candidates) {
		this.user = user;
		this.description = description;
		this.skillDescription = skillDescription;
		this.recruitRequestName = recruitRequestName;
		this.fulfilledCandidateLastUpdate = fulfilledCandidateLastUpdate;
		this.additionalSkills = additionalSkills;
		this.additionalTraining = additionalTraining;
		this.jobPosition = jobPosition;
		this.qualifications = qualifications;
		this.experienceYear = experienceYear;
		this.status = status;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdDate = createdDate;
		this.requestedDate = requestedDate;
		this.closedDate = closedDate;
		this.updatedDate = updatedDate;
		this.interviewHistories = interviewHistories;
		this.skillRequirements = skillRequirements;
		this.recruitRequestHasSkills = recruitRequestHasSkills;
		this.interviewSchedules = interviewSchedules;
		this.candidates = candidates;
	}

	/**
	 * Gets the rcr recruit request id.
	 *
	 * @return the rcr recruit request id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "rcr_recruit_request_id", unique = true, nullable = false)
	public Integer getRcrRecruitRequestId() {
		return this.rcrRecruitRequestId;
	}

	/**
	 * Sets the rcr recruit request id.
	 *
	 * @param rcrRecruitRequestId the new rcr recruit request id
	 */
	public void setRcrRecruitRequestId(Integer rcrRecruitRequestId) {
		this.rcrRecruitRequestId = rcrRecruitRequestId;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rcr_usr_user_id")
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
	 * Gets the description.
	 *
	 * @return the description
	 */
	@Column(name = "rcr_description", length = 500)
	@Length(max = 500)
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the skill description.
	 *
	 * @return the skill description
	 */
	@Column(name = "rcr_skill_description", length = 100)
	@Length(max = 100)
	public String getSkillDescription() {
		return this.skillDescription;
	}

	/**
	 * Sets the skill description.
	 *
	 * @param skillDescription the new skill description
	 */
	public void setSkillDescription(String skillDescription) {
		this.skillDescription = skillDescription;
	}

	/**
	 * Gets the recruit request name.
	 *
	 * @return the recruit request name
	 */
	@Column(name = "rcr_name", unique = true, length = 30)
	@Length(max = 30)
	@Pattern(regex="[a-zA-Z0-9._ ]*",message="{recruit.request.name.invalid}")
	public String getRecruitRequestName() {
		return this.recruitRequestName;
	}

	/**
	 * Sets the recruit request name.
	 *
	 * @param recruitRequestName the new recruit request name
	 */
	public void setRecruitRequestName(String recruitRequestName) {
		this.recruitRequestName = recruitRequestName;
	}

	/**
	 * Gets the fulfilled candidate last update.
	 *
	 * @return the fulfilled candidate last update
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "rcr_fulfilled_candidate_last_update", length = 0)
	public Date getFulfilledCandidateLastUpdate() {
		return this.fulfilledCandidateLastUpdate;
	}

	/**
	 * Sets the fulfilled candidate last update.
	 *
	 * @param fulfilledCandidateLastUpdate the new fulfilled candidate last update
	 */
	public void setFulfilledCandidateLastUpdate(
			Date fulfilledCandidateLastUpdate) {
		this.fulfilledCandidateLastUpdate = fulfilledCandidateLastUpdate;
	}

	/**
	 * Gets the additional skills.
	 *
	 * @return the additional skills
	 */
	@Column(name = "rcr_additional_skills", length = 250)
	@Length(max = 250)
	public String getAdditionalSkills() {
		return this.additionalSkills;
	}

	/**
	 * Sets the additional skills.
	 *
	 * @param additionalSkills the new additional skills
	 */
	public void setAdditionalSkills(String additionalSkills) {
		this.additionalSkills = additionalSkills;
	}

	/**
	 * Gets the additional training.
	 *
	 * @return the additional training
	 */
	@Column(name = "rcr_additional_training", length = 250)
	@Length(max = 250)
	public String getAdditionalTraining() {
		return this.additionalTraining;
	}

	/**
	 * Sets the additional training.
	 *
	 * @param additionalTraining the new additional training
	 */
	public void setAdditionalTraining(String additionalTraining) {
		this.additionalTraining = additionalTraining;
	}
	
	/**
	 * Gets the job position.
	 *
	 * @return the job position
	 */
	@Column(name = "rcr_job_position", length = 30)	
	@NotNull
	@Pattern(regex="[a-zA-Z0-9._ +*#]*",message="{recruit.request.name.invalid}")
	@Length(max = 30)
	public String getJobPosition() {
		return jobPosition;
	}

	/**
	 * Sets the job position.
	 *
	 * @param jobPosition the new job position
	 */
	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}
	
	/**
	 * Gets the number of persons.
	 *
	 * @return the number of persons
	 */
	@Column(name = "rcr_number_of_persons")
	@Min(value=0)
	@Max(value=100)
	public Integer getNumberOfPersons() {
		return numberOfPersons;
	}

	/**
	 * Sets the number of persons.
	 *
	 * @param numberOfPersons the new number of persons
	 */
	public void setNumberOfPersons(Integer numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}

	/**
	 * Gets the experience year from.
	 *
	 * @return the experience year from
	 */
	@Column(name = "rcr_experience_year_from")
	@Min(value=0)
	@Max(value=30)
	public Integer getExperienceYearFrom() {
		return experienceYearFrom;
	}

	/**
	 * Sets the experience year from.
	 *
	 * @param experienceYearFrom the new experience year from
	 */
	public void setExperienceYearFrom(Integer experienceYearFrom) {
		this.experienceYearFrom = experienceYearFrom;
	}

	/**
	 * Gets the experience year to.
	 *
	 * @return the experience year to
	 */
	@Column(name = "rcr_experience_year_to")
	@Min(value=0)
	@Max(value=30)
	public Integer getExperienceYearTo() {
		return experienceYearTo;
	}

	/**
	 * Sets the experience year to.
	 *
	 * @param experienceYearTo the new experience year to
	 */
	public void setExperienceYearTo(Integer experienceYearTo) {
		this.experienceYearTo = experienceYearTo;
	}

	/**
	 * Gets the qualifications.
	 *
	 * @return the qualifications
	 */
	@Column(name = "rcr_qualifications", length = 250)
	@Length(max = 250)
	public String getQualifications() {
		return this.qualifications;
	}

	/**
	 * Sets the qualifications.
	 *
	 * @param qualifications the new qualifications
	 */
	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}
	
	/**
	 * Gets the requested date.
	 *
	 * @return the requested date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "rcr_requested_date", length = 0)
	public Date getRequestedDate() {
		return requestedDate;
	}

	/**
	 * Sets the requested date.
	 *
	 * @param requestedDate the new requested date
	 */
	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}

	/**
	 * Gets the closed date.
	 *
	 * @return the closed date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "rcr_closed_date", length = 0)
	public Date getClosedDate() {
		return closedDate;
	}

	/**
	 * Sets the closed date.
	 *
	 * @param closedDate the new closed date
	 */
	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}
	
	/**
	 * Gets the approved date.
	 *
	 * @return the approved date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "rcr_approved_date", length = 0)
	public Date getApprovedDate() {
		return approvedDate;
	}

	/**
	 * Sets the approved date.
	 *
	 * @param approvedDate the new approved date
	 */
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	/**
	 * Gets the experience year.
	 *
	 * @return the experience year
	 */
	@Column(name = "rcr_experience_year")
	public Integer getExperienceYear() {
		return this.experienceYear;
	}
	
	/**
	 * Sets the experience year.
	 *
	 * @param experienceYear the new experience year
	 */
	public void setExperienceYear(Integer experienceYear) {
		this.experienceYear = experienceYear;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	@Column(name = "rcr_status", length = 10)
	@Length(max = 10)
	public String getStatus() {
		return this.status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	@Column(name = "rcr_created_by", length = 10)
	@Length(max = 10)
	public String getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy the new created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the updated by.
	 *
	 * @return the updated by
	 */
	@Column(name = "rcr_updated_by", length = 10)
	@Length(max = 10)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	/**
	 * Sets the updated by.
	 *
	 * @param updatedBy the new updated by
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "rcr_created_date", length = 0)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	/**
	 * Sets the created date.
	 *
	 * @param createdDate the new created date
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	/**
	 * Gets the updated date.
	 *
	 * @return the updated date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "rcr_updated_date", length = 0)
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	/**
	 * Sets the updated date.
	 *
	 * @param updatedDate the new updated date
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * Gets the interview histories.
	 *
	 * @return the interview histories
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recruitRequest")
	public Set<InterviewHistory> getInterviewHistories() {
		return this.interviewHistories;
	}

	/**
	 * Sets the interview histories.
	 *
	 * @param interviewHistories the new interview histories
	 */
	public void setInterviewHistories(
			Set<InterviewHistory> interviewHistories) {
		this.interviewHistories = interviewHistories;
	}

	/**
	 * Gets the skill requirements.
	 *
	 * @return the skill requirements
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recruitRequest", cascade = {
			CascadeType.PERSIST, CascadeType.REMOVE })
	public Set<SkillRequirement> getSkillRequirements() {		
		return this.skillRequirements;
	}
		
	/**
	 * Sets the skill requirements.
	 *
	 * @param skillRequirements the new skill requirements
	 */
	public void setSkillRequirements(Set<SkillRequirement> skillRequirements) {
		this.skillRequirements = skillRequirements;
	}

	/**
	 * Gets the recruit request has skills.
	 *
	 * @return the recruit request has skills
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recruitRequest")
	public Set<RecruitRequestHasSkill> getRecruitRequestHasSkills() {
		return this.recruitRequestHasSkills;
	}

	/**
	 * Sets the recruit request has skills.
	 *
	 * @param recruitRequestHasSkills the new recruit request has skills
	 */
	public void setRecruitRequestHasSkills(
			Set<RecruitRequestHasSkill> recruitRequestHasSkills) {
		this.recruitRequestHasSkills = recruitRequestHasSkills;
	}

	/**
	 * Gets the interview schedules.
	 *
	 * @return the interview schedules
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recruitRequest")
	public Set<InterviewSchedule> getInterviewSchedules() {
		return this.interviewSchedules;
	}

	/**
	 * Sets the interview schedules.
	 *
	 * @param interviewSchedules the new interview schedules
	 */
	public void setInterviewSchedules(
			Set<InterviewSchedule> interviewSchedules) {
		this.interviewSchedules = interviewSchedules;
	}

	/**
	 * Gets the candidates.
	 *
	 * @return the candidates
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "recruitRequest")
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
		
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return recruitRequestName;
	}

	public void setHandledBy(String handledBy) {
		this.handledBy = handledBy;
	}
	
	@Column(name = "rcr_handled_by", length = 50)
	@Length(max = 50)
	public String getHandledBy() {
		return handledBy;
	}

	public void setNumberRecruited(Integer numberRecruited) {
		this.numberRecruited = numberRecruited;
	}
	
	@Column(name = "rcr_number_recruited")
	public Integer getNumberRecruited() {
		return numberRecruited;
	}
	
	/**
	 * @return the numberToOffer
	 */
	@Column(name = "rcr_number_to_offer")
	public Integer getNumberToOffer() {
		return this.numberToOffer;
	}

	/**
	 * @param numberToOffer the numberToOffer to set
	 */
	public void setNumberToOffer(Integer numberToOffer) {
		this.numberToOffer = numberToOffer;
	}

	/**
	 * @return the numberOfferSent
	 */
	@Column(name = "rcr_number_offer_sent")
	public Integer getNumberOfferSent() {
		return this.numberOfferSent;
	}

	/**
	 * @param numberOfferSent the numberOfferSent to set
	 */
	public void setNumberOfferSent(Integer numberOfferSent) {
		this.numberOfferSent = numberOfferSent;
	}

	/**
	 * @return the numberOfferConfirmed
	 */
	@Column(name = "rcr_number_offer_confimred")
	public Integer getNumberOfferConfirmed() {
		return this.numberOfferConfirmed;
	}

	/**
	 * @param numberOfferConfirmed the numberOfferConfirmed to set
	 */
	public void setNumberOfferConfirmed(Integer numberOfferConfirmed) {
		this.numberOfferConfirmed = numberOfferConfirmed;
	}

	/**
	 * @return the numberOfferRefused
	 */
	@Column(name = "rcr_number_offer_refused")
	public Integer getNumberOfferRefused() {
		return this.numberOfferRefused;
	}

	/**
	 * @param numberOfferRefused the numberOfferRefused to set
	 */
	public void setNumberOfferRefused(Integer numberOfferRefused) {
		this.numberOfferRefused = numberOfferRefused;
	}

	/**
	 * @return the numberCandidateJoined
	 */
	@Column(name = "rcr_number_candidate_joined")
	public Integer getNumberCandidateJoined() {
		return this.numberCandidateJoined;
	}

	/**
	 * @param numberCandidateJoined the numberCandidateJoined to set
	 */
	public void setNumberCandidateJoined(Integer numberCandidateJoined) {
		this.numberCandidateJoined = numberCandidateJoined;
	}

	public void setApprovedBy(User approvedBy) {
		this.approvedBy = approvedBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rcr_approved_by")
	@NotNull
	public User getApprovedBy() {
		return approvedBy;
	}

}