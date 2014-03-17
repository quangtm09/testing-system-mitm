package com.bosch.rts.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class UserHasInterviewSchedule.
 */
@Entity
@Table(name = "trts_user_has_interview_schedule")
public class UserHasInterviewSchedule implements java.io.Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = -8906666684275668263L;
	
	/** The uis user schedule id. */
	private Integer uisUserScheduleId;
	
	/** The user. */
	private User user;
	
	/** The interview schedule. */
	private InterviewSchedule interviewSchedule;

	/**
	 * Instantiates a new user has interview schedule.
	 */
	public UserHasInterviewSchedule() {
	}

	/**
	 * Instantiates a new user has interview schedule.
	 *
	 * @param user the user
	 * @param interviewSchedule the interview schedule
	 */
	public UserHasInterviewSchedule(User user,
			InterviewSchedule interviewSchedule) {
		this.user = user;
		this.interviewSchedule = interviewSchedule;
	}
	
	/**
	 * Instantiates a new user has interview schedule.
	 *
	 * @param user the user
	 */
	public UserHasInterviewSchedule(User user) {
		this.user = user;
	}

	/**
	 * Gets the uis user schedule id.
	 *
	 * @return the uis user schedule id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "uis_user_schedule_id", unique = true, nullable = false)
	public Integer getUisUserScheduleId() {
		return this.uisUserScheduleId;
	}

	/**
	 * Sets the uis user schedule id.
	 *
	 * @param uisUserScheduleId the new uis user schedule id
	 */
	public void setUisUserScheduleId(Integer uisUserScheduleId) {
		this.uisUserScheduleId = uisUserScheduleId;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uis_usr_user_id")
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
	 * Gets the interview schedule.
	 *
	 * @return the interview schedule
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uis_its_interview_schedule_id")
	public InterviewSchedule getInterviewSchedule() {
		return this.interviewSchedule;
	}

	/**
	 * Sets the interview schedule.
	 *
	 * @param interviewSchedule the new interview schedule
	 */
	public void setInterviewSchedule(InterviewSchedule interviewSchedule) {
		this.interviewSchedule = interviewSchedule;
	}

}
