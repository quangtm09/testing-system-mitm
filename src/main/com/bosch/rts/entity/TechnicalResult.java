package com.bosch.rts.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

import com.bosch.rts.utilities.RTSConstants;

/**
 * The Class TechnicalResult.
 *
 * @author NMG1HC
 */
@Entity
@Table(name = "trts_technical_result")
public class TechnicalResult implements java.io.Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 8143020554727349126L;
	
	/** The technical result id. */
	private Integer technicalResultId;
	
	/** The interview schedule. */
	private InterviewSchedule interviewSchedule;
	
	/** The priority. */
	private short priority;	
	
	/** The overall evaluation. */
	private short overallEvaluation;
	
	/** The total experience. */
	private Float totalExperience;
	
	/** The relevant experience. */
	private Float relevantExperience;
	
	/** The level recommended. */
	private String levelRecommended;
	
	/** The recruited for. */
	private String recruitedFor;
	
	/** The for location. */
	private String forLocation;
	
	/** The join by. */
	private Date joinBy;
	
	/** The reviewed for promotion. */
	private Date reviewedForPromotion;
	
	/** The created by. */
	private String createdBy;
	
	/** The created on. */
	private Date createdOn;
	
	/** The updated by. */
	private String updatedBy;
	
	/** The updated on. */
	private Date updatedOn;	
	
	/** The reason for rejection. */
	private String reasonForRejection;
	
	/** The interview assessment templates. */
	private InterviewAssessmentTemplates interviewAssessmentTemplates;

	/**
	 * Gets the interview assessment templates.
	 *
	 * @return the interview assessment templates
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "template_id")	
	public InterviewAssessmentTemplates getInterviewAssessmentTemplates() {
		return this.interviewAssessmentTemplates;
	}

	/**
	 * Sets the interview assessment templates.
	 *
	 * @param interviewAssessmentTemplates the new interview assessment templates
	 */
	public void setInterviewAssessmentTemplates(InterviewAssessmentTemplates interviewAssessmentTemplates) {
		this.interviewAssessmentTemplates = interviewAssessmentTemplates;
	}

	/**
	 * Instantiates a new technical result.
	 */
	public TechnicalResult() {
	}

	/**
	 * Instantiates a new technical result.
	 *
	 * @param interviewSchedule the interview schedule
	 * @param priority the priority
	 * @param overallEvaluation the overall evaluation
	 * @param createdBy the created by
	 * @param createdOn the created on
	 */
	public TechnicalResult(InterviewSchedule interviewSchedule, short priority,
			short overallEvaluation, String createdBy, Date createdOn) {
		this.interviewSchedule = interviewSchedule;
		this.priority = priority;
		this.overallEvaluation = overallEvaluation;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	/**
	 * Instantiates a new technical result.
	 *
	 * @param interviewSchedule the interview schedule
	 * @param priority the priority
	 * @param overallEvaluation the overall evaluation
	 * @param totalExperience the total experience
	 * @param relevantExperience the relevant experience
	 * @param levelRecommended the level recommended
	 * @param recruitedFor the recruited for
	 * @param forLocation the for location
	 * @param joinBy the join by
	 * @param reviewedForPromotion the reviewed for promotion
	 * @param createdBy the created by
	 * @param createdOn the created on
	 * @param updatedBy the updated by
	 * @param updatedOn the updated on
	 * @param reasonForRejection the reason for rejection
	 * @param interviewAssessmentTemplates the interview assessment templates
	 */
	public TechnicalResult(InterviewSchedule interviewSchedule, short priority,
			short overallEvaluation,
			Float totalExperience, Float relevantExperience,
			String levelRecommended, String recruitedFor, String forLocation,
			Date joinBy, Date reviewedForPromotion, String createdBy,
			Date createdOn, String updatedBy, Date updatedOn,
			String reasonForRejection,
			InterviewAssessmentTemplates interviewAssessmentTemplates) {
		this.interviewSchedule = interviewSchedule;
		this.priority = priority;
		this.overallEvaluation = overallEvaluation;
		this.totalExperience = totalExperience;
		this.relevantExperience = relevantExperience;
		this.levelRecommended = levelRecommended;
		this.recruitedFor = recruitedFor;
		this.forLocation = forLocation;
		this.joinBy = joinBy;
		this.reviewedForPromotion = reviewedForPromotion;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.reasonForRejection = reasonForRejection;
		this.interviewAssessmentTemplates = interviewAssessmentTemplates;
	}

	/**
	 * Gets the technical result id.
	 *
	 * @return the technical result id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "technical_result_id", unique = true, nullable = false)
	public Integer getTechnicalResultId() {
		return this.technicalResultId;
	}

	/**
	 * Sets the technical result id.
	 *
	 * @param technicalResultId the new technical result id
	 */
	public void setTechnicalResultId(Integer technicalResultId) {
		this.technicalResultId = technicalResultId;
	}

	/**
	 * Gets the interview schedule.
	 *
	 * @return the interview schedule
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "interview_schedule_id")
	public InterviewSchedule getInterviewSchedule() {
		return interviewSchedule;
	}

	/**
	 * Sets the interview schedule.
	 *
	 * @param interviewSchedule the new interview schedule
	 */
	public void setInterviewSchedule(InterviewSchedule interviewSchedule) {
		this.interviewSchedule = interviewSchedule;
	}

	/**
	 * Gets the priority.
	 *
	 * @return the priority
	 */
	@Column(name = "priority", nullable = false)
	public short getPriority() {
		return this.priority;
	}

	/**
	 * Sets the priority.
	 *
	 * @param priority the new priority
	 */
	public void setPriority(short priority) {
		this.priority = priority;
	}
	
	/**
	 * Gets the overall evaluation.
	 *
	 * @return the overall evaluation
	 */
	@Column(name = "overall_evaluation", nullable = false)
	public short getOverallEvaluation() {
		return this.overallEvaluation;
	}

	/**
	 * Sets the overall evaluation.
	 *
	 * @param overallEvaluation the new overall evaluation
	 */
	public void setOverallEvaluation(short overallEvaluation) {
		this.overallEvaluation = overallEvaluation;
	}

	/**
	 * Gets the total experience.
	 *
	 * @return the total experience
	 */
	@Column(name = "total_experience", precision = 12, scale = 0)
	public Float getTotalExperience() {
		return this.totalExperience;
	}

	/**
	 * Sets the total experience.
	 *
	 * @param totalExperience the new total experience
	 */
	public void setTotalExperience(Float totalExperience) {
		this.totalExperience = totalExperience;
	}

	/**
	 * Gets the relevant experience.
	 *
	 * @return the relevant experience
	 */
	@Column(name = "relevant_experience", precision = 12, scale = 0)
	public Float getRelevantExperience() {
		return this.relevantExperience;
	}

	/**
	 * Sets the relevant experience.
	 *
	 * @param relevantExperience the new relevant experience
	 */
	public void setRelevantExperience(Float relevantExperience) {
		this.relevantExperience = relevantExperience;
	}

	/**
	 * Gets the level recommended.
	 *
	 * @return the level recommended
	 */
	@Column(name = "level_recommended", length = 10)
	@Length(max = 10)
	public String getLevelRecommended() {
		return this.levelRecommended;
	}

	/**
	 * Sets the level recommended.
	 *
	 * @param levelRecommended the new level recommended
	 */
	public void setLevelRecommended(String levelRecommended) {
		this.levelRecommended = levelRecommended;
	}

	/**
	 * Gets the recruited for.
	 *
	 * @return the recruited for
	 */
	@Column(name = "recruited_for", length = 45)
	@Length(max = 45)
	public String getRecruitedFor() {
		return this.recruitedFor;
	}

	/**
	 * Sets the recruited for.
	 *
	 * @param recruitedFor the new recruited for
	 */
	public void setRecruitedFor(String recruitedFor) {
		this.recruitedFor = recruitedFor;
	}

	/**
	 * Gets the for location.
	 *
	 * @return the for location
	 */
	@Column(name = "for_location", length = 50)
	@Length(max = 50)
	public String getForLocation() {
		return this.forLocation;
	}

	/**
	 * Sets the for location.
	 *
	 * @param forLocation the new for location
	 */
	public void setForLocation(String forLocation) {
		this.forLocation = forLocation;
	}

	/**
	 * Gets the join by.
	 *
	 * @return the join by
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "join_by", length = 0)
	public Date getJoinBy() {
		return this.joinBy;
	}

	/**
	 * Sets the join by.
	 *
	 * @param joinBy the new join by
	 */
	public void setJoinBy(Date joinBy) {
		this.joinBy = joinBy;
	}

	/**
	 * Gets the reviewed for promotion.
	 *
	 * @return the reviewed for promotion
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reviewed_for_promotion", length = 0)
	public Date getReviewedForPromotion() {
		return this.reviewedForPromotion;
	}

	/**
	 * Sets the reviewed for promotion.
	 *
	 * @param reviewedForPromotion the new reviewed for promotion
	 */
	public void setReviewedForPromotion(Date reviewedForPromotion) {
		this.reviewedForPromotion = reviewedForPromotion;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
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
	 * @param createdBy the new created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the created on.
	 *
	 * @return the created on
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "created_on", nullable = false, length = 0)
	@NotNull
	public Date getCreatedOn() {
		return this.createdOn;
	}

	/**
	 * Sets the created on.
	 *
	 * @param createdOn the new created on
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * Gets the updated by.
	 *
	 * @return the updated by
	 */
	@Column(name = "updated_by", length = 12)
	@Length(max = 12)
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
	 * Gets the updated on.
	 *
	 * @return the updated on
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_on", length = 0)
	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	/**
	 * Sets the updated on.
	 *
	 * @param updatedOn the new updated on
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	/**
	 * Gets the reason for rejection.
	 *
	 * @return the reason for rejection
	 */
	@Column(name = "reason_for_rejection", length = 45)
	@Length(max = 45)
	public String getReasonForRejection() {
		return this.reasonForRejection;
	}

	/**
	 * Sets the reason for rejection.
	 *
	 * @param reasonForRejection the new reason for rejection
	 */
	public void setReasonForRejection(String reasonForRejection) {
		this.reasonForRejection = reasonForRejection;
	}
	
	/**
	 * Technical status.
	 *
	 * @return the string
	 */
	@Transient
	public String technicalStatus(){
		String result = RTSConstants.FAIL;		
		if (overallEvaluation == 1 || overallEvaluation == 2
				|| overallEvaluation == 3) {
			result = RTSConstants.PASSED;
		}
		
		return result;
	}
	
}
