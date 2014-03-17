package com.bosch.rts.entity;


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

import org.hibernate.validator.Length;


/**
 * The Class InterviewSchedule.
 */
@Entity
@Table(name = "trts_interview_schedule")
public class InterviewSchedule implements java.io.Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 5753296451706395625L;
	
	/** The its interview schedule id. */
	private Integer itsInterviewScheduleId;
	
	/** The candidate. */
	private Candidate candidate;
	
	/** The recruit request. */
	private RecruitRequest recruitRequest;
	
	/** The its update time. */
	private Date itsUpdateTime;
	
	/** The its request number. */
	private Integer itsRequestNumber;
	
	/** The icalendar uid. */
	private String icalendarUID;			
	
	/** The its technical status. */
	private String itsTechnicalStatus;
	
	/** The its hr status. */
	private String itsHrStatus;	
	
	/** The its technical location. */
	private String itsTechnicalLocation;
	
	/** The its hr location. */
	private String itsHrLocation;	
	
	/** The its technical interview type. */
	private String itsTechnicalInterviewType;
	
	/** The its hr interview type. */
	private String itsHRInterviewType;		
	
	/** The its technical interview time. */
	private Date itsTechnicalInterviewTime;
	
	/** The its hr interview time. */
	private Date itsHrInterviewTime;	
	
	/** The its technical remark. */
	private String itsTechnicalRemark;
	
	/** The its hr remark. */
	private String itsHRRemark;
	
	/** The its apply for level. */
	private LevelType itsApplyForLevel;	
	
	/** The its created by. */
	private String itsCreatedBy;
	
	/** The its created on. */
	private Date itsCreatedOn;
	
	/** The its updated by. */
	private String itsUpdatedBy;
	
	/** The its updated on. */
	private Date itsUpdatedOn;	
	
	/** The its status. */
	private String itsStatus;	
	
	/** The user has interview schedules. */
	private Set<UserHasInterviewSchedule> userHasInterviewSchedules = new HashSet<UserHasInterviewSchedule>(0);
	
	/** The technical results. */
	private Set<TechnicalResult> technicalResults = new HashSet<TechnicalResult>(0);	
	
	/** The hr results. */
	private Set<HrResult> hrResults = new HashSet<HrResult>(0);
	
	/**
	 * Instantiates a new interview schedule.
	 */
	public InterviewSchedule() {
	}

	/**
	 * Instantiates a new interview schedule.
	 *
	 * @param itsTechnicalRemark the its technical remark
	 * @param itsTechnicalInterviewType the its technical interview type
	 */
	public InterviewSchedule(String itsTechnicalRemark, String itsTechnicalInterviewType) {
		this.itsTechnicalRemark = itsTechnicalRemark;
		this.itsTechnicalInterviewType = itsTechnicalInterviewType;
	}

	/**
	 * Instantiates a new interview schedule.
	 *
	 * @param candidate the candidate
	 * @param recruitRequest the recruit request
	 * @param itsTechnicalStatus the its technical status
	 * @param itTechnicalInterviewTime the it technical interview time
	 * @param itsUpdateTime the its update time
	 * @param itsRequestNumber the its request number
	 * @param itsRemark the its remark
	 * @param istInterviewType the ist interview type
	 * @param hrResults the hr results
	 * @param userHasInterviewSchedules the user has interview schedules
	 * @param technicalResults the technical results
	 */
	public InterviewSchedule(Candidate candidate,
			RecruitRequest recruitRequest, String itsTechnicalStatus,
			Date itTechnicalInterviewTime, Date itsUpdateTime,
			Integer itsRequestNumber, String itsRemark,
			String istInterviewType, Set<HrResult> hrResults,
			Set<UserHasInterviewSchedule> userHasInterviewSchedules,
			Set<TechnicalResult> technicalResults) {
		this.candidate = candidate;
		this.recruitRequest = recruitRequest;
		this.itsTechnicalStatus = itsTechnicalStatus;
		this.itsTechnicalInterviewTime = itTechnicalInterviewTime;
		this.itsUpdateTime = itsUpdateTime;
		this.itsRequestNumber = itsRequestNumber;
		this.hrResults = hrResults;
		this.userHasInterviewSchedules = userHasInterviewSchedules;
		this.technicalResults = technicalResults;
	}

	/**
	 * Gets the its interview schedule id.
	 *
	 * @return the its interview schedule id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "its_interview_schedule_id", unique = true, nullable = false)
	public Integer getItsInterviewScheduleId() {
		return this.itsInterviewScheduleId;
	}

	/**
	 * Sets the its interview schedule id.
	 *
	 * @param itsInterviewScheduleId the new its interview schedule id
	 */
	public void setItsInterviewScheduleId(Integer itsInterviewScheduleId) {
		this.itsInterviewScheduleId = itsInterviewScheduleId;
	}

	/**
	 * Gets the candidate.
	 *
	 * @return the candidate
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "its_cdd_candidate_id")
	public Candidate getCandidate() {
		return this.candidate;
	}

	/**
	 * Sets the candidate.
	 *
	 * @param candidate the new candidate
	 */
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	/**
	 * Gets the recruit request.
	 *
	 * @return the recruit request
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "its_rcr_recruit_request_id")
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
	 * Gets the its hr interview time.
	 *
	 * @return the its hr interview time
	 */
	@Column(name = "its_hr_interview_time")
	public Date getItsHrInterviewTime() {
		return itsHrInterviewTime;
	}

	/**
	 * Sets the its hr interview time.
	 *
	 * @param itsHrInterviewTime the new its hr interview time
	 */
	public void setItsHrInterviewTime(Date itsHrInterviewTime) {
		this.itsHrInterviewTime = itsHrInterviewTime;
	}

	/**
	 * Gets the its update time.
	 *
	 * @return the its update time
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "its_update_time", length = 19, insertable = false)
	public Date getItsUpdateTime() {
		return this.itsUpdateTime;
	}

	/**
	 * Sets the its update time.
	 *
	 * @param itsUpdateTime the new its update time
	 */
	public void setItsUpdateTime(Date itsUpdateTime) {
		this.itsUpdateTime = itsUpdateTime;
	}

	/**
	 * Gets the its request number.
	 *
	 * @return the its request number
	 */
	@Column(name = "its_request_number")
	public Integer getItsRequestNumber() {
		return this.itsRequestNumber;
	}

	/**
	 * Sets the its request number.
	 *
	 * @param itsRequestNumber the new its request number
	 */
	public void setItsRequestNumber(Integer itsRequestNumber) {
		this.itsRequestNumber = itsRequestNumber;
	}

	/**
	 * Gets the hr results.
	 *
	 * @return the hr results
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "interviewSchedule")
	public Set<HrResult> getHrResults() {
		return this.hrResults;
	}
	
	/**
	 * Sets the hr results.
	 *
	 * @param hrResults the new hr results
	 */
	public void setHrResults(Set<HrResult> hrResults) {
		this.hrResults = hrResults;
	}
	
	/**
	 * Gets the user has interview schedules.
	 *
	 * @return the user has interview schedules
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "interviewSchedule")
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
	 * Gets the technical results.
	 *
	 * @return the technical results
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "interviewSchedule")
	public Set<TechnicalResult> getTechnicalResults() {
		return this.technicalResults;
	}

	/**
	 * Sets the technical results.
	 *
	 * @param technicalResults the new technical results
	 */
	public void setTechnicalResults(Set<TechnicalResult> technicalResults) {
		this.technicalResults = technicalResults;
	}	
	
	/**
	 * Sets the its technical status.
	 *
	 * @param itsTechnicalStatus the new its technical status
	 */
	public void setItsTechnicalStatus(String itsTechnicalStatus) {
		this.itsTechnicalStatus = itsTechnicalStatus;
	}
	
	/**
	 * Gets the its technical status.
	 *
	 * @return the its technical status
	 */
	@Column(name = "its_technical_status", length = 50)
	@Length(max = 50)
	public String getItsTechnicalStatus() {
		return itsTechnicalStatus;
	}
	
	/**
	 * Gets the its status.
	 *
	 * @return the its status
	 */
	@Column(name = "its_status")
	public String getItsStatus() {
		return itsStatus;
	}

	/**
	 * Sets the its status.
	 *
	 * @param itsStatus the new its status
	 */
	public void setItsStatus(String itsStatus) {
		this.itsStatus = itsStatus;
	}

		

	/**
	 * Gets the its hr status.
	 *
	 * @return the its hr status
	 */
	@Column(name = "its_hr_status", length = 50)
	@Length(max = 50)
	public String getItsHrStatus() {
		return itsHrStatus;
	}

	/**
	 * Sets the its hr status.
	 *
	 * @param itsHrStatus the new its hr status
	 */
	public void setItsHrStatus(String itsHrStatus) {
		this.itsHrStatus = itsHrStatus;
	}

	/**
	 * Gets the its created by.
	 *
	 * @return the its created by
	 */
	@Column(name = "its_created_by")
	public String getItsCreatedBy() {
		return itsCreatedBy;
	}

	/**
	 * Sets the its created by.
	 *
	 * @param itsCreatedBy the new its created by
	 */
	public void setItsCreatedBy(String itsCreatedBy) {
		this.itsCreatedBy = itsCreatedBy;
	}

	/**
	 * Gets the its created on.
	 *
	 * @return the its created on
	 */
	@Column(name = "its_created_on")
	public Date getItsCreatedOn() {
		return itsCreatedOn;
	}

	/**
	 * Sets the its created on.
	 *
	 * @param itsCreatedOn the new its created on
	 */
	public void setItsCreatedOn(Date itsCreatedOn) {
		this.itsCreatedOn = itsCreatedOn;
	}

	/**
	 * Gets the its updated by.
	 *
	 * @return the its updated by
	 */
	@Column(name = "its_updated_by")
	public String getItsUpdatedBy() {
		return itsUpdatedBy;
	}

	/**
	 * Sets the its updated by.
	 *
	 * @param itsUpdatedBy the new its updated by
	 */
	public void setItsUpdatedBy(String itsUpdatedBy) {
		this.itsUpdatedBy = itsUpdatedBy;
	}

	/**
	 * Gets the its updated on.
	 *
	 * @return the its updated on
	 */
	@Column(name = "its_updated_on")
	public Date getItsUpdatedOn() {
		return itsUpdatedOn;
	}

	/**
	 * Sets the its updated on.
	 *
	 * @param itsUpdatedOn the new its updated on
	 */
	public void setItsUpdatedOn(Date itsUpdatedOn) {
		this.itsUpdatedOn = itsUpdatedOn;
	}
	

	/**
	 * Gets the interviewers.
	 *
	 * @return the interviewers
	 */
	@Transient
	public List<User> getInterviewers() {
		List<User> resultList = new ArrayList<User>();

		Set<UserHasInterviewSchedule> userScheduleList = getUserHasInterviewSchedules();
		if (userScheduleList == null || userScheduleList.size() == 0) {
			return resultList;
		}
		Object[] userScheduleArray = userScheduleList.toArray();
		for (Object obj : userScheduleArray) {
			UserHasInterviewSchedule userSchedule = (UserHasInterviewSchedule) obj;
			resultList.add(userSchedule.getUser());
		}

		return resultList;
	}
	
	/**
	 * Gets the technical result list.
	 *
	 * @return the technical result list
	 */
	@Transient
	public List<TechnicalResult> getTechnicalResultList() {
		List<TechnicalResult> resultList = new ArrayList<TechnicalResult>();

		Set<TechnicalResult> resultSet = this.getTechnicalResults();
		if (resultSet != null && !resultSet.isEmpty()) {
			Object[] technicalResultArray = resultSet.toArray();
			for (Object obj : technicalResultArray) {
				TechnicalResult technicalResult = (TechnicalResult) obj;
				resultList.add(technicalResult);
			}
		}

		return resultList;
	}
	
	/**
	 * Gets the hr result list.
	 *
	 * @return the hr result list
	 */
	@Transient
	public List<HrResult> getHrResultList() {
		List<HrResult> resultList = new ArrayList<HrResult>();

		Set<HrResult> resultSet = this.getHrResults();
		if (resultSet == null || resultSet.size() == 0) {
			return resultList;
		}
		Object[] hrResultArray = resultSet.toArray();
		for (Object obj : hrResultArray) {
			HrResult rrResult = (HrResult) obj;
			resultList.add(rrResult);
		}

		return resultList;
	}

	/**
	 * Gets the icalendar uid.
	 *
	 * @return the icalendar uid
	 */
	@Column(name = "its_icalendar_UID", length = 255)
	public String getIcalendarUID() {
		return icalendarUID;
	}

	/**
	 * Sets the icalendar uid.
	 *
	 * @param icalendarUID the new icalendar uid
	 */
	public void setIcalendarUID(String icalendarUID) {
		this.icalendarUID = icalendarUID;
	}
	
	/**
	 * Gets the its technical interview type.
	 *
	 * @return the its technical interview type
	 */
	@Column(name = "its_technical_interview_type", length = 10)
	public String getItsTechnicalInterviewType() {
		return itsTechnicalInterviewType;
	}

	/**
	 * Sets the its technical interview type.
	 *
	 * @param itsTechnicalInterviewType the new its technical interview type
	 */
	public void setItsTechnicalInterviewType(String itsTechnicalInterviewType) {
		this.itsTechnicalInterviewType = itsTechnicalInterviewType;
	}

	/**
	 * Gets the its hr interview type.
	 *
	 * @return the its hr interview type
	 */
	@Column(name = "its_hr_interview_type", length = 10)
	public String getItsHRInterviewType() {
		return itsHRInterviewType;
	}

	/**
	 * Sets the its hr interview type.
	 *
	 * @param itsHRInterviewType the new its hr interview type
	 */
	public void setItsHRInterviewType(String itsHRInterviewType) {
		this.itsHRInterviewType = itsHRInterviewType;
	}
	
	/**
	 * Gets the its technical location.
	 *
	 * @return the its technical location
	 */
	@Column(name = "its_technical_location")
	public String getItsTechnicalLocation() {
		return itsTechnicalLocation;
	}

	/**
	 * Sets the its technical location.
	 *
	 * @param itsTechnicalLocation the new its technical location
	 */
	public void setItsTechnicalLocation(String itsTechnicalLocation) {
		this.itsTechnicalLocation = itsTechnicalLocation;
	}

	/**
	 * Gets the its hr location.
	 *
	 * @return the its hr location
	 */
	@Column(name = "its_hr_location")
	public String getItsHrLocation() {
		return itsHrLocation;
	}

	/**
	 * Sets the its hr location.
	 *
	 * @param itsHrLocation the new its hr location
	 */
	public void setItsHrLocation(String itsHrLocation) {
		this.itsHrLocation = itsHrLocation;
	}

	/**
	 * Gets the its technical interview time.
	 *
	 * @return the its technical interview time
	 */
	@Column(name = "its_technical_interview_time")
	public Date getItsTechnicalInterviewTime() {
		return itsTechnicalInterviewTime;
	}

	/**
	 * Sets the its technical interview time.
	 *
	 * @param itsTechnicalInterviewTime the new its technical interview time
	 */
	public void setItsTechnicalInterviewTime(Date itsTechnicalInterviewTime) {
		this.itsTechnicalInterviewTime = itsTechnicalInterviewTime;
	}

	/**
	 * Gets the its technical remark.
	 *
	 * @return the its technical remark
	 */
	@Column(name = "its_technical_remark", length = 500)
	@Length(max = 500)
	public String getItsTechnicalRemark() {
		return itsTechnicalRemark;
	}

	/**
	 * Sets the its technical remark.
	 *
	 * @param itsTechnicalRemark the new its technical remark
	 */
	public void setItsTechnicalRemark(String itsTechnicalRemark) {
		this.itsTechnicalRemark = itsTechnicalRemark;
	}

	/**
	 * Gets the its hr remark.
	 *
	 * @return the its hr remark
	 */
	@Column(name = "its_hr_remark", length = 500)
	@Length(max = 500)
	public String getItsHRRemark() {
		return itsHRRemark;
	}

	/**
	 * Sets the its hr remark.
	 *
	 * @param itsHRRemark the new its hr remark
	 */
	public void setItsHRRemark(String itsHRRemark) {
		this.itsHRRemark = itsHRRemark;
	}
		

	/**
	 * Gets the technical status display string.
	 *
	 * @return the technical status display string
	 */
	@Transient
	public String getTechnicalStatusDisplayString() {
		String value = "";
		if(this.itsTechnicalStatus != null){
			value = this.itsTechnicalStatus;
		}
		String result = translateText(value);
		return result;
	}
	
	/**
	 * Gets the hR status display string.
	 *
	 * @return the hR status display string
	 */
	@Transient
	public String getHRStatusDisplayString() {
		String value = "";
		if(this.itsHrStatus != null){
			value = this.itsHrStatus;
		}		
		String result = translateText(value);
		return result;
	}
	
	/**
	 * Translate text.
	 *
	 * @param value the value
	 * @return the string
	 */
	private String translateText(String value){
		if (value.equals("NEW")) {
			return "To Interview";
		} else if (value.equals("TAKEN")) {
			return "Interviewed";
		} else if (value.equals("DECLINED")) {
			return "Declined";
		} else if (value.equals("CANCELED")) {
			return "Canceled";
		} else {
			return value;
		}
	}

	/**
	 * Deep copy.
	 *
	 * @return the interview schedule
	 */
	public InterviewSchedule deepCopy() {
		InterviewSchedule newIS = new InterviewSchedule();
		newIS.setCandidate(candidate);
		newIS.setIcalendarUID(icalendarUID);
		newIS.setItsInterviewScheduleId(itsInterviewScheduleId);
		newIS.setItsTechnicalInterviewTime(itsTechnicalInterviewTime);
		newIS.setItsHrInterviewTime(itsHrInterviewTime);
		newIS.setItsRequestNumber(itsRequestNumber);
		newIS.setItsUpdateTime(itsUpdateTime);
		newIS.setRecruitRequest(recruitRequest);
		newIS.setHrResults(hrResults);
		newIS.setTechnicalResults(technicalResults);
		newIS.setUserHasInterviewSchedules(userHasInterviewSchedules);
		return newIS;
	}

	/**
	 * Gets the its apply for level.
	 *
	 * @return the its apply for level
	 * 
	 */	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "its_apply_for_level")	
	public LevelType getItsApplyForLevel() {
		return itsApplyForLevel;
	}

	/**
	 * Sets the its apply for level.
	 *
	 * @param itsApplyForLevel the new its apply for level
	 */
	public void setItsApplyForLevel(LevelType itsApplyForLevel) {
		this.itsApplyForLevel = itsApplyForLevel;
	}
	
}
