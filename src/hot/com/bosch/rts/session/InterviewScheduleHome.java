package com.bosch.rts.session;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Query;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.lang3.StringUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.core.Events;
import org.jboss.seam.core.SeamResourceBundle;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.framework.EntityNotFoundException;
import org.jboss.seam.international.StatusMessage;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import com.bosch.rts.blo.UserBLO;
import com.bosch.rts.entity.Candidate;
import com.bosch.rts.entity.CandidateStatus;
import com.bosch.rts.entity.HrResult;
import com.bosch.rts.entity.InterviewSchedule;
import com.bosch.rts.entity.LevelType;
import com.bosch.rts.entity.OrgUnit;
import com.bosch.rts.entity.RecruitRequest;
import com.bosch.rts.entity.TechnicalResult;
import com.bosch.rts.entity.User;
import com.bosch.rts.entity.UserHasInterviewSchedule;
import com.bosch.rts.utilities.Constants.InterviewScheduleStatus;
import com.bosch.rts.utilities.Constants.PrivilegeConstants;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSInterviewRoles;
import com.bosch.rts.utilities.RTSQueries;
import com.bosch.rts.utilities.RTSRoles;
import com.bosch.rts.utilities.RTSStatus;
import com.bosch.rts.utilities.RTSUtils;
import com.bosch.rts.validator.RTSDateValidator;

/**
 * The Class InterviewScheduleHome.
 */
@Name("interviewScheduleHome")
@Scope(ScopeType.CONVERSATION)
public class InterviewScheduleHome extends EntityHome<InterviewSchedule> {

	/** serialVersionUID. */
	private static final long serialVersionUID = -5277676772507816845L;

	/** The log. */
	@Logger
	private transient Log log;

	/** The status messages. */
	@In
	private transient StatusMessages statusMessages;

	/** The Constant currentLocale. */
	private static final Locale currentLocale = Locale.ENGLISH;

	/** The candidate home. */
	@In(create = true)
	private CandidateHome candidateHome;

	/** The recruit request home. */
	@In(create = true)
	private RecruitRequestHome recruitRequestHome;

	/** The interview schedule list. */
	@In(create = true)
	private InterviewScheduleList interviewScheduleList;
	
	/** The pre hr status. */
	private String preHRStatus;
	
	/** The interview schedules. */
	@DataModel
	private List<InterviewSchedule> interviewSchedules = null;

	/**
	 * Gets the interview schedules.
	 *
	 * @return the interview schedules
	 */
	public List<InterviewSchedule> getInterviewSchedules() {
		return interviewSchedules;
	}

	/**
	 * Sets the interview schedules.
	 *
	 * @param interviewSchedules the new interview schedules
	 */
	public void setInterviewSchedules(List<InterviewSchedule> interviewSchedules) {
		this.interviewSchedules = interviewSchedules;
	}

	/** The recruit request list. */
	@In(create = true)
	private RecruitRequestList recruitRequestList;

	/** The hr result list. */
	@In(create = true)
	private HrResultList hrResultList;
	
	/** The technical result list. */
	@In(create = true)
	private TechnicalResultList technicalResultList;
	
	/**
	 * Search interview schedules.
	 */
	@Begin(join = true)
	public void searchInterviewSchedules() {
		doSearch();
	}
	
	/** The contact number1. */
	private String contactNumber1;
	
	/** The contact number2. */
	private String contactNumber2;
	
	public String getContactNumber1() {
		String contactNumberRaw = null;
		if (this.getInstance() != null && this.getInstance().getCandidate() != null
				&& this.getInstance().getCandidate().getCddPhoneNo() != null) {
			contactNumberRaw = this.getInstance().getCandidate().getCddPhoneNo();
		}
		contactNumber1 = candidateHome.parseContactNumber(0, contactNumberRaw);
		return contactNumber1;
	}

	public String getContactNumber2() {
		String contactNumberRaw = null;
		if (this.getInstance() != null && this.getInstance().getCandidate() != null
				&& this.getInstance().getCandidate().getCddPhoneNo() != null) {
			contactNumberRaw = this.getInstance().getCandidate().getCddPhoneNo();
		}
		contactNumber2 = candidateHome.parseContactNumber(1, contactNumberRaw);
		return contactNumber2;
	}
	
	/**
	 * Do search.
	 */
	private void doSearch(){
		statusMessages.clear();
		RTSUtils.resetList(interviewSchedules);

		String validateMsg = null;

		Date fromTechnicalDate = null;
		if (this.fromDate != null) {
			fromTechnicalDate = this.fromDate;
		}
		Date toTechnicalDate = null;
		if (this.toDate != null) {
			toTechnicalDate = this.toDate;
		}

		if (!RTSDateValidator.compareDates(fromTechnicalDate, toTechnicalDate)) {
			validateMsg = "error.interview.schedule.technicalFromDate.technicalToDate";
		}

		if (StringUtils.isBlank(validateMsg)) {
			final String candidateName = this.candidateName != null ? this.candidateName.trim() : null;
			final String recruitRequestName = this.recruitRequestName != null ? this.recruitRequestName.trim() : null;
			final Date fromDate = this.fromDate != null ? this.fromDate : null;
			final Date toDate = this.toDate != null ? this.toDate : null;

			final String technicalStatus = this.technicalStatus != null ? translateSearchingStatus(this.technicalStatus)
					: null;

			final String hrStatus = this.hrStatus != null ? translateSearchingStatus(this.hrStatus) : null;

			interviewSchedules = interviewScheduleList.searchInterviewSchedules(candidateName, recruitRequestName,
					fromDate, toDate, technicalStatus, hrStatus);

		}
		if (!StringUtils.isBlank(validateMsg)) {
			statusMessages.addFromResourceBundle(Severity.ERROR, SeamResourceBundle.getBundle().getString(validateMsg));
		}
	}

	/**
	 * Translate searching status.
	 *
	 * @param status the status
	 * @return the string
	 */
	private String translateSearchingStatus(final String status) {		
		String result = status;
		if (result.equals(RTSConstants.ROUND_TO_INTERVIEW)) {
			result = RTSConstants.ROUND_NEW.toUpperCase(currentLocale);
		} else if (result.equals(RTSConstants.ROUND_INTERVIEWED)) {
			result = RTSConstants.ROUND_TAKEN.toUpperCase(currentLocale);
		} else if (result.equals(RTSConstants.ROUND_DECLINED)) {
			result = RTSConstants.ROUND_DECLINED.toUpperCase(currentLocale);
		} else if (result.equals(RTSConstants.ROUND_CANCELED)) {
			result = RTSConstants.ROUND_CANCELED.toUpperCase(currentLocale);
		}
		return result;
	}

	/**
	 * Initialise interview schedules.
	 */
	public void initialiseInterviewSchedules() {
		doSearch();
	}

	/**
	 * Sets the interview schedule its interview schedule id.
	 *
	 * @param id the new interview schedule its interview schedule id
	 */
	public void setInterviewScheduleItsInterviewScheduleId(Integer id) {
		setId(id);
	}

	/**
	 * Gets the interview schedule its interview schedule id.
	 *
	 * @return the interview schedule its interview schedule id
	 */
	public Integer getInterviewScheduleItsInterviewScheduleId() {
		return (Integer) getId();
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#createInstance()
	 */
	@Override
	protected InterviewSchedule createInstance() {
		InterviewSchedule interviewSchedule = new InterviewSchedule();
		return interviewSchedule;
	}

	/**
	 * Load.
	 */
	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	/**
	 * Wire.
	 */
	public void wire() {
		this.getInstance();
		Candidate candidate;
		try {
			candidate = candidateHome.getDefinedInstance();
		} catch (EntityNotFoundException ex) {
			log.error("Error in finding entity. ", ex);
			return;
		}
		if (candidate != null) {
			this.getInstance().setCandidate(candidate);
		}
		final RecruitRequest recruitRequest = recruitRequestHome.getDefinedInstance();
		if (recruitRequest != null) {
			this.getInstance().setRecruitRequest(recruitRequest);
		}
	}

	/**
	 * Checks if is wired.
	 *
	 * @return true, if is wired
	 */
	public boolean isWired() {
		return true;
	}

	/**
	 * Gets the defined instance.
	 *
	 * @return the defined instance
	 */
	public InterviewSchedule getDefinedInstance() {
		return isIdDefined() ? this.getInstance() : null;
	}

	/**
	 * Gets the user has interview schedules.
	 *
	 * @return the user has interview schedules
	 */
	public List<UserHasInterviewSchedule> getUserHasInterviewSchedules() {
		return this.getInstance() == null ? null : new ArrayList<UserHasInterviewSchedule>(this.getInstance()
				.getUserHasInterviewSchedules());
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.EntityHome#persist()
	 */
	@Override
	public String persist() {
		return super.persist();
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.EntityHome#update()
	 */
	@Override
	public String update() {
		return super.update();
	}

	/** The selected candidate. */
	private Candidate selectedCandidate = null;

	/**
	 * Gets the selected candidate.
	 *
	 * @return the selected candidate
	 */
	public Candidate getSelectedCandidate() {
		return selectedCandidate;
	}

	/**
	 * Sets the selected candidate.
	 *
	 * @param selectedCandidate the new selected candidate
	 */
	public void setSelectedCandidate(Candidate selectedCandidate) {
		this.selectedCandidate = selectedCandidate;
	}

	/** The selected recruit request. */
	private RecruitRequest selectedRecruitRequest = null;

	/**
	 * Gets the selected recruit request.
	 *
	 * @return the selected recruit request
	 */
	public RecruitRequest getSelectedRecruitRequest() {
		return selectedRecruitRequest;
	}

	/**
	 * Sets the selected recruit request.
	 *
	 * @param selectedRecruitRequest the new selected recruit request
	 */
	public void setSelectedRecruitRequest(RecruitRequest selectedRecruitRequest) {
		this.selectedRecruitRequest = selectedRecruitRequest;
	}

	/** The selected interview round. */
	private int selectedInterviewRound = -1;

	/**
	 * Gets the selected interview round.
	 *
	 * @return the selected interview round
	 */
	public int getSelectedInterviewRound() {
		return selectedInterviewRound;
	}

	/**
	 * Sets the selected interview round.
	 *
	 * @param selectedInterviewRound the new selected interview round
	 */
	public void setSelectedInterviewRound(int selectedInterviewRound) {
		this.selectedInterviewRound = selectedInterviewRound;
	}

	/** The selected interview status. */
	private String selectedInterviewStatus = null;

	/**
	 * Gets the selected interview status.
	 *
	 * @return the selected interview status
	 */
	public String getSelectedInterviewStatus() {
		return selectedInterviewStatus;
	}

	/**
	 * Sets the selected interview status.
	 *
	 * @param selectedInterviewStatus the new selected interview status
	 */
	public void setSelectedInterviewStatus(String selectedInterviewStatus) {
		this.selectedInterviewStatus = selectedInterviewStatus;
	}

	/** The from date. */
	private Date fromDate = null;

	/**
	 * Gets the from date.
	 *
	 * @return the from date
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * Sets the from date.
	 *
	 * @param fromDate the new from date
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/** The to date. */
	private Date toDate = null;

	/**
	 * Gets the to date.
	 *
	 * @return the to date
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * Sets the to date.
	 *
	 * @param toDate the new to date
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	/** The user blo. */
	@In(create = true)
	private transient UserBLO userBLO;

	/** The credentials. */
	@In(create = true)
	private Credentials credentials;

	/**
	 * Checks for hr result.
	 *
	 * @param interviewSchduleId the interview schdule id
	 * @return true, if successful
	 */
	private boolean hasHrResult(final Integer interviewSchduleId) {
		boolean result = false;
		if (interviewSchduleId != null && Integer.valueOf(interviewSchduleId) > 0) {
			List<HrResult> hrResults = new ArrayList<HrResult>();
			hrResults = hrResultList.getHrResultsByInterviewScheduleId(Integer.valueOf(interviewSchduleId));
			if (RTSUtils.isNotEmpty(hrResults)) {
				result = true;
			}
		}

		return result;
	}
	
	/**
	 * Able give hr result.
	 *
	 * @param interviewSchduleId the interview schdule id
	 * @return true, if successful
	 */
	public boolean ableGiveHrResult(final Integer interviewSchduleId) {
		boolean result = false;
		if (interviewSchduleId != null && Integer.valueOf(interviewSchduleId) > 0) {
			final List<TechnicalResult> technicalResults = technicalResultList.getTechnicalResultsByInterviewScheduleId(
					Integer.valueOf(interviewSchduleId));
			if (RTSUtils.isNotEmpty(technicalResults) && technicalResults.get(0) != null) {				
				switch (technicalResults.get(0).getOverallEvaluation()) {

				case 1:
					result = true;
					break;
				case 2: 
					result = true;
					break;
				case 3: 
					result = true;
					break;
				case 4: 
					result = false;
					break;
				case 5: 
					result = false;
					break;	
				default:
					result = false;
					break;
				}
			} 			
		}

		return result;
	}

	/**
	 * Check if user can edit HR result.
	 *
	 * @param interviewSchedule the interview schedule
	 * @return true, if successful
	 */
	public boolean editableHrResult(final InterviewSchedule interviewSchedule) {
		if (interviewSchedule != null && interviewSchedule.getItsHrStatus() != null
				&& interviewSchedule.getItsHrStatus().equalsIgnoreCase(RTSStatus.TAKEN)
				&& userBLO.userHasPrivilege(credentials.getUsername(), RTSInterviewRoles.EDIT_HR_FEEDBACK)
				&& hasHrResult(interviewSchedule.getItsInterviewScheduleId())) {
			return true;
		}

		return false;
	}

	/**
	 * Check if user can view HR result.
	 *
	 * @param interviewSchedule the interview schedule
	 * @return true, if successful
	 */
	public boolean viewableHrResult(final InterviewSchedule interviewSchedule) {
		if (interviewSchedule != null && interviewSchedule.getItsHrStatus() != null
				&& interviewSchedule.getItsHrStatus().equalsIgnoreCase(RTSStatus.TAKEN)
				&& userBLO.userHasPrivilege(credentials.getUsername(), RTSInterviewRoles.VIEW_HR_FEEDBACK)
				&& !userBLO.userHasPrivilege(credentials.getUsername(), RTSInterviewRoles.EDIT_HR_FEEDBACK)
				&& hasHrResult(interviewSchedule.getItsInterviewScheduleId())) {
			return true;
		}
		return false;
	}

	/**
	 * Check if user can give HR result.
	 *
	 * @param interviewSchedule the interview schedule
	 * @return true, if successful
	 */
	public boolean giveableHRResult(final InterviewSchedule interviewSchedule) {
		if (interviewSchedule != null
				&& interviewSchedule.getItsHrStatus() != null
				&& interviewSchedule.getCandidate() != null
				&& interviewSchedule.getCandidate().getCddStatus() != null
				&& (!interviewSchedule.getItsHrStatus().equalsIgnoreCase(RTSStatus.TAKEN) && !interviewSchedule	.getItsHrStatus().equalsIgnoreCase(RTSStatus.CANCELED))
				&& userBLO.userHasPrivilege(credentials.getUsername(), RTSInterviewRoles.ADD_HR_FEEDBACK)
				&& ableGiveHrResult(interviewSchedule.getItsInterviewScheduleId())) {
			return true;
		}


		return false;
	}

	/**
	 * Checks if is adds the hr feedback.
	 *
	 * @return true, if is adds the hr feedback
	 */
	public boolean isAddHRFeedback() {
		return userBLO.userHasPrivilege(credentials.getUsername(), RTSInterviewRoles.ADD_HR_FEEDBACK);
	}

	/** The is viewable technical result. */
	private boolean isViewableTechnicalResult = false;

	/**
	 * Sets the viewable technical result.
	 *
	 * @param isViewableTechnicalResult the new viewable technical result
	 */
	public void setViewableTechnicalResult(boolean isViewableTechnicalResult) {
		this.isViewableTechnicalResult = isViewableTechnicalResult;
	}

	/**
	 * Checks if is viewable technical result.
	 *
	 * @return true, if is viewable technical result
	 */
	public boolean isViewableTechnicalResult() {

		if (!isViewableTechnicalResult) {
			final String status = this.getInstance().getItsTechnicalStatus() != null ? this.getInstance()
					.getItsTechnicalStatus() : null;
			if (status != null) {
				final boolean viewableTechnicalFeedback = userBLO.userHasPrivilege(credentials.getUsername(),
						RTSInterviewRoles.VIEW_TECHNICAL_FEEDBACK);
				if (status.equalsIgnoreCase(RTSStatus.TAKEN)
						&& (RTSUtils.isNotEmpty(this.getInstance().getTechnicalResults())) && viewableTechnicalFeedback) {
					return true;
				}
			}
			return false;
		}
		return true;
	}

	/** The is editable technical result. */
	private boolean isEditableTechnicalResult = false;

	/**
	 * Sets the editable technical result.
	 *
	 * @param isEditableTechnicalResult the new editable technical result
	 */
	public void setEditableTechnicalResult(boolean isEditableTechnicalResult) {
		this.isEditableTechnicalResult = isEditableTechnicalResult;
	}

	/**
	 * Checks if is editable technical result.
	 *
	 * @return true, if is editable technical result
	 */
	public boolean isEditableTechnicalResult() {
		if (!isEditableTechnicalResult) {
			final String status = this.getInstance().getItsTechnicalStatus() != null ? this.getInstance()
					.getItsTechnicalStatus() : null;
			if (status != null) {
				final boolean editableTechnicalFeedback = userBLO.userHasPrivilege(credentials.getUsername(),
						RTSInterviewRoles.EDIT_TECHNICAL_FEEDBACK);
				if (status.equalsIgnoreCase(RTSStatus.TAKEN)
						&& (RTSUtils.isNotEmpty(this.getInstance().getTechnicalResults())) && editableTechnicalFeedback) {
					return true;
				}
			}
			return false;
		}
		return true;
	}

	/** The is addable technical result. */
	private boolean isAddableTechnicalResult = false;

	/**
	 * Sets the addable technical result.
	 *
	 * @param isAddableTechnicalResult the new addable technical result
	 */
	public void setAddableTechnicalResult(boolean isAddableTechnicalResult) {
		this.isAddableTechnicalResult = isAddableTechnicalResult;
	}

	/**
	 * Checks if is addable technical result.
	 *
	 * @return true, if is addable technical result
	 */
	public boolean isAddableTechnicalResult() {
		if (!isAddableTechnicalResult) {
			final String status = this.getInstance().getItsTechnicalStatus() != null ? this.getInstance()
					.getItsTechnicalStatus() : null;

			if (status != null) {
				final boolean addableTechnicalFeedback = userBLO.userHasPrivilege(credentials.getUsername(),
						RTSInterviewRoles.ADD_TECHNICAL_FEEDBACK);
				if (status.equalsIgnoreCase(RTSStatus.NEW) && this.hasAccessRights(false) && addableTechnicalFeedback) {
					return true;
				}

			}
			return false;
		}

		return true;

	}

	/**
	 * Check role to identify able to edit this technical interview or not.
	 *
	 * @param currentInterviewSchedule the current interview schedule
	 * @return isAddable
	 */
	public boolean checkAddableTechnicalResult(InterviewSchedule interviewSchedule) {
		boolean isAddable = false;

		if (interviewSchedule != null) {
			final String TECHNICAL_STATUS = interviewSchedule.getItsTechnicalStatus();
			System.out.println("TECHNICAL_STATUS: " + TECHNICAL_STATUS);
			System.out.println("interviewSchedule: " + interviewSchedule.getCandidate().getCddName());
			System.out.println("interviewSchedule.getItsStatus(): " + interviewSchedule.getItsStatus());
			
			if (StringUtils.isNotEmpty(TECHNICAL_STATUS)) {
				if((TECHNICAL_STATUS.equalsIgnoreCase(InterviewScheduleStatus.NEW.dbString()) 
						|| TECHNICAL_STATUS.equalsIgnoreCase(InterviewScheduleStatus.DECLINED.dbString()))
						&& interviewSchedule.getItsStatus().equalsIgnoreCase(RTSConstants.OPEN)){
					final List<User> interviewers = interviewSchedule.getInterviewers();
					if (RTSUtils.isNotEmpty(interviewers)) {
						for (final User interviewer : interviewers) {
							if (interviewer.getUsrUserName().equalsIgnoreCase(credentials.getUsername())) {
								isAddable = true;
								break;
							}
						}

					}

				}
				
			}
		}

		System.out.println("isAddable: " + isAddable);
		return isAddable;
	}
	
	public boolean renderedInterviewSchedule(final InterviewSchedule interviewSchedule, final boolean hrmanager){
		boolean result = false;
		
		if(interviewSchedule != null){
			if(hrmanager){
				result = true;
			} else {
				result = RTSUtils.isInterviewer(interviewSchedule, credentials.getUsername());
				
			}
		}
		
		
		return result;
	} 
	
	
	public boolean checkEditableTechnicalResult(final InterviewSchedule interviewSchedule) {
		boolean result = false;
		if (interviewSchedule != null && RTSUtils.isNotEmpty(interviewSchedule.getTechnicalResultList())) {
			
			final TechnicalResult technicalResult = interviewSchedule.getTechnicalResultList().get(0);
			
			if (technicalResult != null) {
				
				final String TECHNICAL_STATUS = interviewSchedule.getItsTechnicalStatus();
				final String HR_STATUS = interviewSchedule.getItsHrStatus();
				final String STATUS = interviewSchedule.getItsStatus();
				
				System.out.println("========================================================");
				
				System.out.println("TECHNICAL_STATUS: " + TECHNICAL_STATUS);
				System.out.println("HR_STATUS: " + HR_STATUS);
				System.out.println("STATUS: " + STATUS);

				if (StringUtils.isNotEmpty(TECHNICAL_STATUS) && StringUtils.isNotEmpty(HR_STATUS) && StringUtils.isNotEmpty(STATUS)) {
					if(TECHNICAL_STATUS.equalsIgnoreCase(InterviewScheduleStatus.TAKEN.dbString()) 
							&& !HR_STATUS.equalsIgnoreCase(InterviewScheduleStatus.TAKEN.dbString())
							&& STATUS.equalsIgnoreCase(RTSConstants.OPEN)){
						final List<User> interviewers = interviewSchedule.getInterviewers();
						if (RTSUtils.isNotEmpty(interviewers)) {
							for (final User interviewer : interviewers) {
								if (interviewer.getUsrUserName().equalsIgnoreCase(credentials.getUsername())) {
									result = true;
									break;
								}
							}

						}

						/**
						 * and (_sch.itsTechnicalStatus eq 'TAKEN') 
											and (_sch.itsHrStatus ne 'TAKEN') 
											and (_sch.itsStatus eq 'Open')
						 */
						
					}
					
				}

			}
						
		}
		return result;
	}
	
	private int technicalResultItrTechnicalResultId;

	/**
	 * @return the technicalResultItrTechnicalResultId
	 */
	public int getTechnicalResultItrTechnicalResultId() {
		return this.technicalResultItrTechnicalResultId;
	}

	/**
	 * @param technicalResultItrTechnicalResultId the technicalResultItrTechnicalResultId to set
	 */
	public void setTechnicalResultItrTechnicalResultId(
			int technicalResultItrTechnicalResultId) {
		this.technicalResultItrTechnicalResultId = technicalResultItrTechnicalResultId;
	}

	/**
	 * Check viewable technical result.
	 *
	 * @param currentInterviewSchedule the current interview schedule
	 * @return true, if successful
	 */
	public boolean checkViewableTechnicalResult(InterviewSchedule interviewSchedule) {
		boolean isViewable = false;
		
			if (interviewSchedule != null && RTSUtils.isNotEmpty(interviewSchedule.getTechnicalResultList())) {				
				
				final TechnicalResult technicalResult = interviewSchedule.getTechnicalResultList().get(0);
				if(technicalResult != null){
					//final TechnicalResult technicalResult = technicalResultList.findTechnicalResultById(TECHNICAL_RESULT_ID);
					final String TECHNICAL_STATUS = interviewSchedule.getItsTechnicalStatus();
					if(StringUtils.isNotEmpty(TECHNICAL_STATUS) && !TECHNICAL_STATUS.equalsIgnoreCase(InterviewScheduleStatus.NEW.dbString()) && technicalResult != null){
						final String HR_STATUS = interviewSchedule.getItsHrStatus();
						final String STATUS = interviewSchedule.getItsStatus();
						//if(StringUtils.isNotEmpty(HR_STATUS) && HR_STATUS.equalsIgnoreCase(InterviewScheduleStatus.TAKEN.dbString())){
							
							if(StringUtils.isNotEmpty(STATUS) && (STATUS.equalsIgnoreCase(RTSConstants.OPEN) || STATUS.equalsIgnoreCase(RTSConstants.CLOSED)) ){
								
								final boolean isInterviewer = RTSUtils.isInterviewer(interviewSchedule, credentials.getUsername());	
														
								if(!isInterviewer && identity.hasRole(RTSRoles.ROLE_HR)){
									isViewable = true;
								}
							} else if(StringUtils.isNotEmpty(HR_STATUS) && HR_STATUS.equalsIgnoreCase(InterviewScheduleStatus.TAKEN.dbString())){
								final boolean isInterviewer = RTSUtils.isInterviewer(interviewSchedule, credentials.getUsername());	
								
								if(isInterviewer || (!isInterviewer && identity.hasRole(RTSRoles.ROLE_HR)) ){
									isViewable = true;
								}
							}
						
						}				

				}
				
							}	
		
		return isViewable;
	}

	/**
	 * Checks if is adds the technical feedback.
	 *
	 * @return true, if is adds the technical feedback
	 */
	public boolean isAddTechnicalFeedback() {
		return userBLO.userHasPrivilege(credentials.getUsername(), RTSInterviewRoles.ADD_TECHNICAL_FEEDBACK);
	}

	/**
	 * Sets the from view.
	 *
	 * @param fromView the new from view
	 */
	public void setFromView(String fromView) {
		this.fromView = fromView;
	}

	/**
	 * Gets the from view.
	 *
	 * @return the from view
	 */
	public String getFromView() {
		return fromView;
	}

	/** The from view. */
	private String fromView = null;

	/**
	 * Reset selected interview.
	 */
	public void resetSelectedInterview() {
		if (this.getInstance() != null) {
			this.clearInstance();
		}
	}

	/** The selected interview type. */
	private String selectedInterviewType = null;

	/**
	 * Gets the selected interview type.
	 *
	 * @return the selected interview type
	 */
	public String getSelectedInterviewType() {
		return selectedInterviewType;
	}

	/**
	 * Sets the selected interview type.
	 *
	 * @param selectedInterviewType the new selected interview type
	 */
	public void setSelectedInterviewType(String selectedInterviewType) {
		this.selectedInterviewType = selectedInterviewType;
	}

	/** The updating. */
	private boolean updating;

	/** The interview history home. */
	@In(create = true)
	private transient InterviewHistoryHome interviewHistoryHome;

	/** The identity. */
	@In(create = true)
	private transient Identity identity;

	/** The logged in user. */
	@In(scope = ScopeType.SESSION, required = false)
	private transient User loggedInUser;

	/** The candidates. */
	private List<Candidate> candidates = null;

	/**
	 * Onload candidates.
	 *
	 * @param isUpdating the is updating
	 */
	@SuppressWarnings("unchecked")
	public void onloadCandidates(boolean isUpdating) {
		RTSUtils.resetList(candidates);

		if (identity.hasRole(RTSRoles.ROLE_GM) || identity.hasRole(RTSRoles.ROLE_HR)) {
			candidates = candidateHome.findAllCandidateForInterviewSchedule();
		} else {
			final List<OrgUnit> orgUnitList = userBLO.getPrivilegeOrgUnitList(loggedInUser.getUsrUserName(),
					PrivilegeConstants.ADD_INTERVIEW_SCHEDULE.toString());

			if (RTSUtils.isNotEmpty(orgUnitList)) {
				candidates = candidateHome.findCandidateByListOfOrgUnitForInterviewSchedule(orgUnitList);
			}

		}

		if (RTSUtils.isNotEmpty(candidates)) {
			final BeanComparator candidateComparator = new BeanComparator(RTSConstants.SQL_CDD_NAME);
			Collections.sort(candidates, candidateComparator);
		} else {
			candidates = new ArrayList<Candidate>();
		}
	}

	/**
	 * Gets the candidates.
	 *
	 * @return the candidates
	 */
	public List<Candidate> getCandidates() {
		return candidates;
	}

	/**
	 * Sets the candidates.
	 *
	 * @param candidates the new candidates
	 */
	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	/** The user home. */
	@In(create = true)
	private transient UserHome userHome;

	/** The selected interviewers. */
	private List<User> selectedInterviewers = new ArrayList<User>();

	/**
	 * Gets the selected interviewers.
	 *
	 * @return the selected interviewers
	 */
	public List<User> getSelectedInterviewers() {
		return selectedInterviewers;
	}

	/**
	 * Sets the selected interviewers.
	 *
	 * @param selectedInterviewers the new selected interviewers
	 */
	public void setSelectedInterviewers(List<User> selectedInterviewers) {
		this.selectedInterviewers = selectedInterviewers;
	}

	/** The interviewer list. */
	private List<User> interviewerList = new ArrayList<User>();

	/**
	 * Checks if is updating.
	 *
	 * @return true, if is updating
	 */
	public boolean isUpdating() {
		return updating;
	}

	/**
	 * Sets the updating.
	 *
	 * @param updating the new updating
	 */
	public void setUpdating(boolean updating) {
		this.updating = updating;
	}
	
	public void onloadInterviewList(){
		/*if (identity.hasRole(RTSRoles.ROLE_GM) || identity.hasRole(RTSRoles.ROLE_HR)) {
			interviewerList = userHome.findAllUser(true, true);
		} else {
			final List<OrgUnit> orgUnitList = userBLO.getPrivilegeOrgUnitList(loggedInUser.getUsrUserName(),
					PrivilegeConstants.ADD_INTERVIEW_SCHEDULE.toString());
			if(RTSUtils.isNotEmpty(orgUnitList)){				
				interviewerList = userHome.findUserByListOfOrgUnitID(orgUnitList, true, true);
			}			
		}*/
		RTSUtils.resetList(interviewerList);
		interviewerList = userHome.findAllUser(true, true);

		/*if (RTSUtils.isNotEmpty(interviewerList)) {
			final BeanComparator interviewerComparator = new BeanComparator(RTSConstants.SQL_USR_FULL_NAME);
			Collections.sort(interviewerList, interviewerComparator);
		}*/
		
		
		
		
		
	}

	/**
	 * Gets the interviewer list.
	 *
	 * @return the interviewer list
	 */
	public List<User> getInterviewerList() {
		return interviewerList;
	}

	/**
	 * Sets the interviewer list.
	 *
	 * @param interviewerList the new interviewer list
	 */
	public void setInterviewerList(List<User> interviewerList) {
		this.interviewerList = interviewerList;
	}

	/** The is interview schedule added. */
	private boolean isInterviewScheduleAdded = false;

	/**
	 * Checks if is interview schedule added.
	 *
	 * @return true, if is interview schedule added
	 */
	public boolean isInterviewScheduleAdded() {
		return this.isInterviewScheduleAdded;
	}

	/**
	 * Sets the interview schedule added.
	 *
	 * @param isInterviewScheduleAdded the new interview schedule added
	 */
	public void setInterviewScheduleAdded(boolean isInterviewScheduleAdded) {
		this.isInterviewScheduleAdded = isInterviewScheduleAdded;
	}

	/**
	 * Schedule an interview for candidate.
	 *
	 * @return persisted if successfully, otherwise failure
	 */
	public String createInterviewSchedule() {
		statusMessages.clear();
		boolean isError = false;
		
		String result = RTSConstants.FAILURE;
		final String ERROR_MESSAGE_CREATION = "int.sch.error.create";

		Candidate currCandidate = null;
		if (this.instance.getCandidate() != null) {
			currCandidate = this.instance.getCandidate();
			
			//Set old interview schedules with status 'Closed'
			final Set<InterviewSchedule> interviewSchedules =  currCandidate.getInterviewSchedules();
			if(RTSUtils.isNotEmpty(interviewSchedules)){
				for(InterviewSchedule schedule : interviewSchedules){				
					schedule.setItsStatus(RTSConstants.CLOSED);
					getEntityManager().persist(schedule);
					statusMessages.clear();
				}
			}
			
		}
		
		isInterviewScheduleAdded = false;
		statusMessages.clear();

		// reset interviewer list
		RTSUtils.resetList(oldInterviewers);
		RTSUtils.resetList(unChangedInterviewers);
		RTSUtils.resetList(newInterviewers);
		try {

			this.getInstance().setItsTechnicalStatus(InterviewScheduleStatus.NEW.dbString());
			this.getInstance().setItsHrStatus(InterviewScheduleStatus.NEW.dbString());

			this.getInstance().setItsRequestNumber(currCandidate.getCddRequestNumber());

			final Date currentDate = new Date();
			final String username = credentials.getUsername();
			this.getInstance().setItsCreatedBy(username);
			this.getInstance().setItsUpdatedBy(username);
			this.getInstance().setItsCreatedOn(currentDate);
			this.getInstance().setItsUpdatedOn(currentDate);
			this.getInstance().setItsStatus(RTSConstants.OPEN);
			
			final String icalendarUID = UUID.randomUUID().toString();
			this.getInstance().setIcalendarUID(icalendarUID);
			try {
				final String INTERVIEW_SCHEDULE = super.persist();

				statusMessages.clear();

				if (!RTSConstants.PERSISTED.equals(INTERVIEW_SCHEDULE)) {
					isError = true;
				}
			} catch (Exception e) {
				log.error("Error in creating interview schedule - add technical schedule." + e);
				statusMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, ERROR_MESSAGE_CREATION,
						currCandidate.getCddPrefix() + " " + currCandidate.getCddName());
				isError = true;
			}

			if (RTSUtils.isNotEmpty(selectedInterviewers)) {
				for (final User interviewer : selectedInterviewers) {
					UserHasInterviewSchedule interviewerSchedule = new UserHasInterviewSchedule();
					interviewerSchedule.setUser(interviewer);
					interviewerSchedule.setInterviewSchedule(this.getInstance());
					try {
						getEntityManager().persist(interviewerSchedule);
						statusMessages.clear();
					} catch (Exception e) {
						log.error("Error in creating interview schedule - interviewers." + e);
						statusMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, ERROR_MESSAGE_CREATION,
								currCandidate.getCddPrefix() + " " + currCandidate.getCddName());
						isError = true;
						break;
					}
				}
			}

			// update candidate status
			currCandidate.setCddStatus(CandidateStatus.TECHNICAL_SCHEDULED);
			candidateHome.setInstance(currCandidate);
			try {
				final String CANDIDATE_STATUS = candidateHome.update();
				statusMessages.clear();
				if (!RTSConstants.UPDATED.equals(CANDIDATE_STATUS)) {
					isError = true;
				}
			} catch (Exception e) {
				log.error("Error in creating interview schedule - update candidate status." + e);
				statusMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, ERROR_MESSAGE_CREATION,
						currCandidate.getCddPrefix() + " " + currCandidate.getCddName());
				isError = true;
			}

			// update interview history
			interviewHistoryHome.clearInstance();
			interviewHistoryHome.getInstance().setCandidate(currCandidate);

			if (this.getInstance().getRecruitRequest() != null) {
				interviewHistoryHome.getInstance().setRecruitRequest(this.getInstance().getRecruitRequest());
			}

			interviewHistoryHome.getInstance().setIthStatus(currCandidate.getCddStatus().dbString());
			interviewHistoryHome.getInstance().setIthRequestNumber(currCandidate.getCddRequestNumber());

			interviewHistoryHome.getInstance().setIthUpdateDate(currentDate);
			try {
				final String INTERVIEW_HISTORY = interviewHistoryHome.persist();
				statusMessages.clear();
				if (!RTSConstants.PERSISTED.equals(INTERVIEW_HISTORY)) {
					isError = true;
				}
			} catch (Exception e) {
				log.error("Error in creating interview schedule - add interview history." + e);
				statusMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, ERROR_MESSAGE_CREATION,
						currCandidate.getCddPrefix() + " " + currCandidate.getCddName());
				isError = true;
			}

		} catch (Exception e) {
			log.error("Error in creating new interview schedule.");
			isError = true;
			statusMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, ERROR_MESSAGE_CREATION,
					currCandidate.getCddPrefix() + " " + currCandidate.getCddName());
		}

		if (isError) {
			log.error("Error in creating new interview schedule.");
			statusMessages.addFromResourceBundle(Severity.ERROR, ERROR_MESSAGE_CREATION,
					currCandidate.getCddPrefix() + " " + currCandidate.getCddName());
		} else {
			result = RTSConstants.PERSISTED;
			if (RTSUtils.isNotEmpty(selectedInterviewers)) {
				RTSUtils.resetList(newInterviewers);
				newInterviewers = new ArrayList<User>(selectedInterviewers);
				
				final String ownerEmail = loggedInUser.getUsrEmail();			
				final String location = this.getInstance().getItsTechnicalLocation();
				final String appliedForRequest = this.getInstance().getItsApplyForLevel().getDisplayName();
				
				String recruitName = "";
				if(this.getInstance().getRecruitRequest() != null){
					recruitName = this.getInstance().getRecruitRequest().getRecruitRequestName();
				}
				
				final String technicalStatus = this.getInstance().getItsTechnicalStatus();
				final String technicalRemark = this.getInstance().getItsTechnicalRemark();
				final String technicalMode = this.getInstance().getItsTechnicalInterviewType();
				final Date technicalTime = this.getInstance().getItsTechnicalInterviewTime();
				
				final Date date = this.getInstance().getItsTechnicalInterviewTime();				
				Calendar calendar = GregorianCalendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.HOUR, 2);
				
				final String startTime = RTSUtils.getTime(date);
				final String endTime = RTSUtils.getTime(calendar.getTime());				
				final String candidateName = this.getInstance().getCandidate().getCddName();
				
				Events.instance().raiseEvent("interviewScheduleEvent", ownerEmail, candidateName, 
						newInterviewers, this.getInstance().getIcalendarUID(), 
						startTime, 
						technicalTime,
						endTime, 
						RTSUtils.getTime(new Date()), 
						location,
						"REQUEST",
						appliedForRequest,
						recruitName,
						technicalStatus,
						technicalRemark,
						technicalMode,
						null,
						null,
						newInterviewers,
						false,
						false,
						false, 
						this.getInstance().getItsInterviewScheduleId());				
			}
		}

		isInterviewScheduleAdded = true;
		
		return result;
	}

	/** The selected technical status display text. */
	private String selectedTechnicalStatusDisplayText;
	
	/** The selected hr status display text. */
	private String selectedHRStatusDisplayText;

	/**
	 * Gets the selected technical status display text.
	 *
	 * @return the selected technical status display text
	 */
	public String getSelectedTechnicalStatusDisplayText() {
		return selectedTechnicalStatusDisplayText;
	}

	/**
	 * Sets the selected technical status display text.
	 *
	 * @param selectedTechnicalStatusDisplayText the new selected technical status display text
	 */
	public void setSelectedTechnicalStatusDisplayText(String selectedTechnicalStatusDisplayText) {
		this.selectedTechnicalStatusDisplayText = selectedTechnicalStatusDisplayText;
	}

	/**
	 * Gets the selected hr status display text.
	 *
	 * @return the selected hr status display text
	 */
	public String getSelectedHRStatusDisplayText() {
		return selectedHRStatusDisplayText;
	}

	/**
	 * Sets the selected hr status display text.
	 *
	 * @param selectedHRStatusDisplayText the new selected hr status display text
	 */
	public void setSelectedHRStatusDisplayText(String selectedHRStatusDisplayText) {
		this.selectedHRStatusDisplayText = selectedHRStatusDisplayText;
	}

	/** The status list. */
	private List<String> statusList;

	/**
	 * Gets the status list.
	 *
	 * @return the status list
	 */
	public List<String> getStatusList() {
		statusList = new ArrayList<String>();
		statusList.add(InterviewScheduleStatus.NEW.toString());
		statusList.add(InterviewScheduleStatus.CANCELED.toString());
		statusList.add(InterviewScheduleStatus.DECLINED.toString());
		statusList.add(InterviewScheduleStatus.TAKEN.toString());

		return statusList;
	}

	/**
	 * Sets the status list.
	 *
	 * @param statusList the new status list
	 */
	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	/**
	 * Checks if is status_ to string.
	 *
	 * @param value the value
	 * @return the string
	 */
	private String ISStatus_ToString(String value) {
		if (value != null) {
			if (value.equalsIgnoreCase(InterviewScheduleStatus.NEW.dbString())) {
				return InterviewScheduleStatus.NEW.toString();
			} else if (value.equalsIgnoreCase(InterviewScheduleStatus.TAKEN.dbString())) {
				return InterviewScheduleStatus.TAKEN.toString();
			} else if (value.equalsIgnoreCase(InterviewScheduleStatus.DECLINED.dbString())) {
				return InterviewScheduleStatus.DECLINED.toString();
			} else if (value.equalsIgnoreCase(InterviewScheduleStatus.CANCELED.dbString())) {
				return InterviewScheduleStatus.CANCELED.toString();
			}else if (value.equalsIgnoreCase(InterviewScheduleStatus.TECHNICAL_ON_HOLD.dbString())) {
				return InterviewScheduleStatus.TECHNICAL_ON_HOLD.toString();
			} else if (value.equalsIgnoreCase(InterviewScheduleStatus.HR_ON_HOLD.dbString())) {
				return InterviewScheduleStatus.HR_ON_HOLD.toString();
			} else {
				return value;
			}
		}
		return value;
	}

	/**
	 * Checks if is status_ to db string.
	 *
	 * @param value the value
	 * @return the string
	 */
	private String ISStatus_ToDBString(String value) {
		if (value != null) {
			if (value.equalsIgnoreCase(InterviewScheduleStatus.NEW.toString())) {
				return InterviewScheduleStatus.NEW.dbString();
			} else if (value.equalsIgnoreCase(InterviewScheduleStatus.TAKEN.toString())) {
				return InterviewScheduleStatus.TAKEN.dbString();
			} else if (value.equalsIgnoreCase(InterviewScheduleStatus.DECLINED.toString())) {
				return InterviewScheduleStatus.DECLINED.dbString();
			} else if (value.equalsIgnoreCase(InterviewScheduleStatus.CANCELED.dbString())) {
				return InterviewScheduleStatus.CANCELED.toString();
			}else if (value.equalsIgnoreCase(InterviewScheduleStatus.TECHNICAL_ON_HOLD.dbString())) {
				return InterviewScheduleStatus.TECHNICAL_ON_HOLD.toString();
			}else if (value.equalsIgnoreCase(InterviewScheduleStatus.HR_ON_HOLD.dbString())) {
				return InterviewScheduleStatus.HR_ON_HOLD.toString();
			}else {
				return value;
			}
		}
		return value;
	}

	/**
	 * Update candidate interview schedule.
	 *
	 * @return updated if successfully, otherwise failure
	 */
	public String updateInterviewSchedule() {
		statusMessages.clear();
		String errorMsg = null;
		String result = RTSConstants.FAILURE;
		final String ERROR_MESSAGE = "int.sch.error.update"; 	
		
		if (StringUtils.isBlank(errorMsg)) {
			
			boolean isError = false;
			boolean canceled = false;
			
			// reset interviewer list
			RTSUtils.resetList(newInterviewers);
			RTSUtils.resetList(oldInterviewers);
			RTSUtils.resetList(unChangedInterviewers);

			Candidate currCandidate = null;
			if (this.instance.getCandidate() != null) {
				currCandidate = this.instance.getCandidate();
			}

			try {
				this.getInstance().setItsTechnicalStatus(ISStatus_ToDBString(selectedTechnicalStatusDisplayText));
				this.getInstance().setItsHrStatus(ISStatus_ToDBString(selectedHRStatusDisplayText));

				final Date currentDate = new Date();
				final String username = credentials.getUsername();
				this.getInstance().setItsUpdatedBy(username);
				this.getInstance().setItsUpdatedOn(currentDate);

				if (selectedTechnicalStatusDisplayText != null
						&& selectedTechnicalStatusDisplayText.equalsIgnoreCase(InterviewScheduleStatus.CANCELED
								.toString())) {
					this.getInstance().setItsHrStatus(this.getInstance().getItsTechnicalStatus());
				}

				// delete old interviewers
				Set<UserHasInterviewSchedule> interviewers = this.getInstance().getUserHasInterviewSchedules();

				if (RTSUtils.isNotEmpty(interviewers)) {
					if (RTSUtils.isNotEmpty(oldInterviewers)) {
						oldInterviewers.clear();
					} else {
						oldInterviewers = new ArrayList<User>(interviewers.size());
					}

					for (UserHasInterviewSchedule userHasInterviewSchedule : interviewers) {
						// for sending modified interviewers
						final User userHasInterview = userHasInterviewSchedule.getUser();
						if (userHasInterview != null && userHasInterview.getUsrUserId() > 0) {
							oldInterviewers.add(userHasInterview);							
						}

						getEntityManager().remove(userHasInterviewSchedule);
					}
					this.getInstance().getUserHasInterviewSchedules().clear();
				}

				try {
					final String OLD_INTERVIEWERS = this.update();
					if (!RTSConstants.UPDATED.equals(OLD_INTERVIEWERS)) {
						isError = true;
					}
				} catch (Exception e) {
					log.error("Error in updating interview schedule - remove old interviewers. " + e);
					statusMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, ERROR_MESSAGE,
							selectedCandidate.getCddPrefix() + " " + selectedCandidate.getCddName());
					isError = true;
				}
				
				// update candidate status
				if (selectedTechnicalStatusDisplayText != null
						&& selectedHRStatusDisplayText != null
						&& (selectedTechnicalStatusDisplayText.equalsIgnoreCase(InterviewScheduleStatus.CANCELED.toString()) 
								|| selectedHRStatusDisplayText.equalsIgnoreCase(InterviewScheduleStatus.CANCELED.toString()))) {
					canceled = true;
					currCandidate.setCddStatus(CandidateStatus.NEW);
					candidateHome.setInstance(currCandidate);
					candidateHome.update();

					// / update interview history
					interviewHistoryHome.clearInstance();
					interviewHistoryHome.getInstance().setCandidate(currCandidate);
					if (this.getInstance().getRecruitRequest() != null) {

						interviewHistoryHome.getInstance().setRecruitRequest(this.getInstance().getRecruitRequest());
					}
					interviewHistoryHome.getInstance().setIthStatus(currCandidate.getCddStatus().dbString());
					interviewHistoryHome.getInstance().setIthRequestNumber(currCandidate.getCddRequestNumber());
					interviewHistoryHome.getInstance().setIthUpdateDate(currentDate);
					try {
						final String INTERVIEWERS_HISTORY = interviewHistoryHome.persist();
						if (!RTSConstants.PERSISTED.equals(INTERVIEWERS_HISTORY)) {
							isError = true;
						}
					} catch (Exception e) {
						log.error("Error in updating interview schedule - interview history." + e);
						statusMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, ERROR_MESSAGE,
								currCandidate.getCddPrefix() + " " + currCandidate.getCddName());
						isError = true;
					}
				}

				// add new interviewer
				if (RTSUtils.isNotEmpty(selectedInterviewers)) {
					for (final User interviewer : selectedInterviewers) {
						UserHasInterviewSchedule _interviewer = new UserHasInterviewSchedule();
						_interviewer.setUser(interviewer);
						_interviewer.setInterviewSchedule(this.getInstance());

						try {
							getEntityManager().persist(_interviewer);

						} catch (Exception e) {
							log.error("Error in updating interview schedule - new interviewers." + e);
							statusMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, ERROR_MESSAGE,
									currCandidate.getCddPrefix() + " " + currCandidate.getCddName());
							isError = true;
							break;
						}
					}
				}

			} catch (Exception e) {
				log.error("Error in updating interview schedule." + e);
				statusMessages.addFromResourceBundle(Severity.ERROR,
						"int.sch.error.update", currCandidate.getCddPrefix() + " " + currCandidate.getCddName());
				isError = true;
			}

			// Catch error
			if (isError) {
				log.error("Error in updating interview schedule.");
				statusMessages.addFromResourceBundle(Severity.ERROR,
						"int.sch.error.update", currCandidate.getCddPrefix() + " " + currCandidate.getCddName());
			} else {
				result = RTSConstants.UPDATED;

				// new interviewers be added
				if (RTSUtils.isNotEmpty(oldInterviewers)) {
					for (User currUser : selectedInterviewers) {
						if (!oldInterviewers.contains(currUser)) {
							newInterviewers.add(currUser);
						}
					}
				} else {// import candidate maybe have not interviewers
					newInterviewers = selectedInterviewers;
				}

				// loading unchanged interviewer list
				if (RTSUtils.isNotEmpty(selectedInterviewers)) {
					for (User oldUser : oldInterviewers) {
						if (this.selectedInterviewers.contains(oldUser)) {
							unChangedInterviewers.add(oldUser);
						}
					}
				}

				// loading removing interviewer list
				final List<User> emailInterviewers = this.getSelectedInterviewers() != null ? this
						.getSelectedInterviewers() : null;
				if (RTSUtils.isNotEmpty(oldInterviewers)) {
					for (Iterator<User> oldInterviewerIterator = oldInterviewers.iterator(); oldInterviewerIterator
							.hasNext();) {
						final int OLD_INTERVIEWER_ID = oldInterviewerIterator.next().getUsrUserId();
						if (RTSUtils.isNotEmpty(emailInterviewers)) {
							for (Iterator<User> selectedInterviewerIterator = emailInterviewers.iterator(); selectedInterviewerIterator
									.hasNext();) {
								final int SELECTED_INTERVIEWER_ID = selectedInterviewerIterator.next().getUsrUserId();
								if (OLD_INTERVIEWER_ID == SELECTED_INTERVIEWER_ID) {
									oldInterviewerIterator.remove();
									break;
								}
							}
						}
					}
				}

				// change technical status
				boolean changedStatus = false;
				if (!this.getInterviewStatus()
						.equalsIgnoreCase(ISStatus_ToDBString(selectedTechnicalStatusDisplayText))) {
					final User owner = this.getInterviewScheduleOwner();
					if (owner != null) {
						if (RTSUtils.isNotEmpty(unChangedInterviewers)) {
							if (!unChangedInterviewers.contains(owner)) {
								unChangedInterviewers.add(owner);
							}
						} else {
							unChangedInterviewers.add(owner);
						}
					}
					changedStatus = true;
					newInterviewers.clear();
				}

				//Change time
				boolean changedTime_Location = false;
				if (this.getInstance().getItsTechnicalInterviewTime() != null
						&& !this.getInstance().getItsTechnicalInterviewTime().equals(this.interviewDate)) {
					changedTime_Location = true;
				}
				
				//Change location 
				if (changedTime_Location != true && this.getInstance().getItsTechnicalLocation() != null
						&& !this.getInstance().getItsTechnicalLocation().equals(this.technicalLocation)) {
					changedTime_Location = true;
				}				
				
				final String ownerEmail = loggedInUser.getUsrEmail();			
				final String location = this.getInstance().getItsTechnicalLocation();
				final String appliedForRequest = this.getInstance().getItsApplyForLevel().getDisplayName();
				
				String recruitName = "";
				if(this.getInstance().getRecruitRequest() != null){
					recruitName = this.getInstance().getRecruitRequest().getRecruitRequestName();
				}
				
				final String technicalStatus = this.getInstance().getItsTechnicalStatus();
				final String technicalRemark = this.getInstance().getItsTechnicalRemark();
				final String technicalMode = this.getInstance().getItsTechnicalInterviewType();
				final Date technicalTime = this.getInstance().getItsTechnicalInterviewTime();
				
				final Date date = this.getInstance().getItsTechnicalInterviewTime();
				Calendar calendar = GregorianCalendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.HOUR, 2);
				
				final String startTime = RTSUtils.getTime(date);
				final String endTime = RTSUtils.getTime(calendar.getTime());
				
				final String candidateName = this.getInstance().getCandidate().getCddName();
				
				Events.instance().raiseEvent("interviewScheduleEvent", ownerEmail, candidateName, 
						this.getSelectedInterviewers(), this.getInstance().getIcalendarUID(), 
						startTime, 
						technicalTime,
						endTime, 
						RTSUtils.getTime(new Date()), 
						location,
						"UPDATE",
						appliedForRequest,
						recruitName,
						technicalStatus,
						technicalRemark,
						technicalMode,
						oldInterviewers,
						unChangedInterviewers,
						newInterviewers,
						changedStatus,
						changedTime_Location, 
						canceled,
						this.getInstance().getItsInterviewScheduleId());	
				
				
			/*	Events.instance().raiseEvent("sendInterviewEmails", this.getInstance(), this.getSelectedInterviewers(),
						changedStatus, changedTime_Location, oldInterviewers, unChangedInterviewers, newInterviewers, this.getInstance().getIcalendarUID());*/
			}

		} else {
			statusMessages.addFromResourceBundle(Severity.ERROR, 
					SeamResourceBundle.getBundle().getString(errorMsg));	
		}
		return result;
	}

	/**
	 * Assign an interview schedule.
	 */
	public void assignInterviewSchedule() {
		selectedInterviewers = this.getInstance().getInterviewers();
		selectedTechnicalStatusDisplayText = ISStatus_ToString(this.getInstance().getItsTechnicalStatus());
		selectedHRStatusDisplayText = ISStatus_ToString(this.getInstance().getItsHrStatus());
		
		preHRStatus = selectedHRStatusDisplayText;

		this.setInterviewDate(this.getInstance().getItsTechnicalInterviewTime());
		this.setInterviewStatus(this.getInstance().getItsTechnicalStatus());		
		this.setTechnicalLocation(this.getInstance().getItsTechnicalLocation());

	}

	/**
	 * Initialize a recruit request on page load.
	 */
	public void initialiseRecruitRequest() {
		this.instance.setRecruitRequest(null);
		if (this.getInstance().getCandidate() != null) {
			if (this.getInstance().getCandidate().getRecruitRequest() != null) {
				final RecruitRequest rr = this.getInstance().getCandidate().getRecruitRequest();
				this.instance.setRecruitRequest(rr);
			}
		}
	}

	/** The candidate name. */
	private String candidateName;
	
	/** The recruit request name. */
	private String recruitRequestName;

	/** The technical status. */
	private String technicalStatus;
	
	/** The hr status. */
	private String hrStatus;

	/**
	 * Gets the candidate name.
	 *
	 * @return the candidate name
	 */
	public String getCandidateName() {
		return candidateName;
	}

	/**
	 * Sets the candidate name.
	 *
	 * @param candidateName the new candidate name
	 */
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	/**
	 * Gets the recruit request name.
	 *
	 * @return the recruit request name
	 */
	public String getRecruitRequestName() {
		return recruitRequestName;
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
	 * Gets the technical status.
	 *
	 * @return the technical status
	 */
	public String getTechnicalStatus() {
		return technicalStatus;
	}

	/**
	 * Sets the technical status.
	 *
	 * @param technicalStatus the new technical status
	 */
	public void setTechnicalStatus(String technicalStatus) {
		this.technicalStatus = technicalStatus;
	}

	/**
	 * Gets the hr status.
	 *
	 * @return the hr status
	 */
	public String getHrStatus() {
		return hrStatus;
	}

	/**
	 * Sets the hr status.
	 *
	 * @param hrStatus the new hr status
	 */
	public void setHrStatus(String hrStatus) {
		this.hrStatus = hrStatus;
	}

	/** The candidate list. */
	@In(create = true, required = false)
	private CandidateList candidateList;

	/**
	 * Auto complete recruit request names.
	 *
	 * @return the list
	 */
	public List<String> autoCompleteRecruitRequestNames() {
		List<String> autoRR = new ArrayList<String>();
		if (this.getRecruitRequestName() != null) {
			final String recruitRequestName = this.getRecruitRequestName();
			autoRR = recruitRequestList.getRecruitRequestNamesByName(recruitRequestName);
		}
		return autoRR;
	}

	/**
	 * Auto complete candidate names.
	 *
	 * @return the list
	 */
	public List<String> autoCompleteCandidateNames() {
		List<String> autoCandidates = new ArrayList<String>();
		if (this.getCandidateName() != null) {
			final String candidateName = this.getCandidateName();
			autoCandidates = candidateList.getCandidateByName_LikeQuery(candidateName);
		}
		return autoCandidates;
	}

	/**
	 * Reset hr status.
	 */
	public void resetHRStatus() {
		if (this.selectedTechnicalStatusDisplayText != null
				&& this.selectedTechnicalStatusDisplayText.toString().equalsIgnoreCase(
						InterviewScheduleStatus.CANCELED.toString())) {
			this.selectedHRStatusDisplayText = this.selectedTechnicalStatusDisplayText;
		}else{
			this.selectedHRStatusDisplayText = preHRStatus;
		}
		
	}

	// change status(review later)
	/**
	 * Checks for access rights.
	 *
	 * @param updatingResult the updating result
	 * @return true, if successful
	 */
	public boolean hasAccessRights(Boolean updatingResult) {
		// check for interview schedule
		final InterviewSchedule selectedIS = this.getInstance();

		if (selectedIS == null || (updating && updatingResult == null)) {
			return false;
		} else {
			if ((!updating && !selectedIS.getItsTechnicalStatus().equalsIgnoreCase(
					InterviewScheduleStatus.NEW.dbString()))
					|| (updating && !selectedIS.getItsTechnicalStatus().equalsIgnoreCase(
							InterviewScheduleStatus.TAKEN.dbString()))) {
				return false;
			}
		}
		// check for role
		if (identity.hasRole(RTSRoles.ROLE_GM)) {
			return true;
		} else {
			if (userBLO.getPrivilegeOrgUnitList(loggedInUser.getUsrUserName(),
					PrivilegeConstants.ADD_TECHNICAL_FEEDBACK.toString()).contains(
					selectedIS.getCandidate().getOrgUnit())) {
				return true;
			} else if (userBLO.getPrivilegeOrgUnitList(loggedInUser.getUsrUserName(),
					PrivilegeConstants.EDIT_TECHNICAL_FEEDBACK.toString()).contains(
					selectedIS.getCandidate().getOrgUnit())) {
				return true;
			}
		}

		// normal interviewer has no access to edit
		if (updating) {
			return false;
		}

		// normal interviewer, add interview
		Object[] userHasIS = selectedIS.getUserHasInterviewSchedules().toArray();
		if (userHasIS.length > 0) {
			final int userHasISLength = userHasIS.length;
			for (int i = 0; i < userHasISLength; ++i) {
				UserHasInterviewSchedule userIS = (UserHasInterviewSchedule) userHasIS[i];

				if (userIS.getUser().getUsrUserId().intValue() == loggedInUser.getUsrUserId().intValue()) {
					return true;
				}
			}
		}

		return false;
	}

	/** The selected candidate id. */
	int selectedCandidateId;

	/**
	 * Gets the selected candidate id.
	 *
	 * @return the selected candidate id
	 */
	public int getSelectedCandidateId() {
		return selectedCandidateId;
	}

	/**
	 * Sets the selected candidate id.
	 *
	 * @param selectedCandidateId the new selected candidate id
	 */
	public void setSelectedCandidateId(int selectedCandidateId) {
		this.selectedCandidateId = selectedCandidateId;
	}

	/**
	 * Onload create interview schedule.
	 */
	public void onloadCreateInterviewSchedule() {
		if (selectedCandidateId > 0) {
			List<Candidate> candidates = new ArrayList<Candidate>();
			candidates = candidateList.getCandidateById(selectedCandidateId);

			if (RTSUtils.isNotEmpty(candidates)) {
				Candidate candidate = candidates.get(0) != null ? candidates.get(0) : null;

				if (candidate != null) {
					this.getInstance().setCandidate(candidate);

				}

			}
		}
		if (this.getInstance().getCandidate() != null && this.getInstance().getCandidate().getRecruitRequest() != null) {

			this.getInstance().setRecruitRequest(this.getInstance().getCandidate().getRecruitRequest());

		}
	}

	/** The old interviewers. */
	private List<User> oldInterviewers = new ArrayList<User>();

	/** The un changed interviewers. */
	private List<User> unChangedInterviewers = new ArrayList<User>();

	/** The new interviewers. */
	private List<User> newInterviewers = new ArrayList<User>();

	/**
	 * Gets the old interviewers.
	 *
	 * @return the old interviewers
	 */
	public List<User> getOldInterviewers() {
		return this.oldInterviewers;
	}

	/**
	 * Sets the old interviewers.
	 *
	 * @param oldInterviewers the new old interviewers
	 */
	public void setOldInterviewers(List<User> oldInterviewers) {
		this.oldInterviewers = oldInterviewers;
	}

	/**
	 * Gets the un changed interviewers.
	 *
	 * @return the un changed interviewers
	 */
	public List<User> getUnChangedInterviewers() {
		return unChangedInterviewers;
	}

	/**
	 * Sets the un changed interviewers.
	 *
	 * @param unChangedInterviewers the new un changed interviewers
	 */
	public void setUnChangedInterviewers(List<User> unChangedInterviewers) {
		this.unChangedInterviewers = unChangedInterviewers;
	}

	/**
	 * Gets the new interviewers.
	 *
	 * @return the new interviewers
	 */
	public List<User> getNewInterviewers() {
		return newInterviewers;
	}

	/**
	 * Sets the new interviewers.
	 *
	 * @param newInterviewers the new new interviewers
	 */
	public void setNewInterviewers(List<User> newInterviewers) {
		this.newInterviewers = newInterviewers;
	}

	/**
	 * Checks if is deleted interviewer.
	 *
	 * @param currInterviewers the curr interviewers
	 * @param interviewer the interviewer
	 * @return true, if is deleted interviewer
	 */
	public boolean isDeletedInterviewer(List<User> currInterviewers, User interviewer) {
		boolean result = true;

		for (User _inter : currInterviewers) {
			if (interviewer.equal(interviewer, _inter)) {
				result = false;
			}
		}
		return result;
	}

	/** The interview schedule owner. */
	User interviewScheduleOwner = null;

	/**
	 * Gets the interview schedule owner.
	 *
	 * @return the interview schedule owner
	 */
	@SuppressWarnings("unchecked")
	public User getInterviewScheduleOwner() {
		User user = null;
		String userName = this.getInstance().getItsCreatedBy() != null ? this.getInstance().getItsCreatedBy() : null;
		final String sql = RTSQueries.getUserByUserName();
		final Query query = getEntityManager().createQuery(sql);
		query.setParameter(RTSConstants.SQL_USR_NAME, userName.toLowerCase());

		List<User> resultList = query.getResultList();
		if (RTSUtils.isNotEmpty(resultList)) {
			user = resultList.get(0);
		}

		return user;
	}

	/**
	 * Sets the interview schedule owner.
	 *
	 * @param interviewScheduleOwner the new interview schedule owner
	 */
	public void setInterviewScheduleOwner(User interviewScheduleOwner) {
		this.interviewScheduleOwner = interviewScheduleOwner;
	}

	/**
	 * Gets the interview schedule by id.
	 *
	 * @param interviewId the interview id
	 * @return the interview schedule by id
	 */
	public InterviewSchedule getInterviewScheduleById(int interviewId) {
		InterviewSchedule interviewSchedule = null;
		interviewSchedule = interviewScheduleList.getInterviewScheduleByInterviewId(interviewId);

		return interviewSchedule;
	}

	/**
	 * Editable technical information.
	 *
	 * @param schedule the interview schedule
	 * @return true, if successful
	 */
	public boolean editableTechinalInf(InterviewSchedule schedule) {
		boolean result = false;
		if (schedule != null && schedule.getItsTechnicalStatus() != null
				&& schedule.getItsCreatedBy() != null && !schedule.getItsStatus().equalsIgnoreCase("Closed")
				&& (!schedule.getItsTechnicalStatus().equalsIgnoreCase("TAKEN") 
						&& !schedule.getItsTechnicalStatus().equalsIgnoreCase("CANCELED") 
					)
				&& (schedule.getItsUpdatedBy().equalsIgnoreCase(credentials.getUsername())
						|| identity.hasRole(RTSRoles.ROLE_INTEVIEWER) 
						|| identity.hasRole(RTSRoles.ROLE_GM)) 
					) {
			result = true;
		}

		return result;
	}

	/**
	 * Editable HR information.
	 *
	 * @param schedule the interview schedule
	 * @return true, if successful
	 */
	public boolean editableHrInf(InterviewSchedule schedule) {
		boolean result = false;
		if (schedule != null
				&& schedule.getItsHrStatus() != null
				&& schedule.getItsCreatedBy() != null
				&& !schedule.getItsStatus().equalsIgnoreCase("Closed")
				&& (!schedule.getItsHrStatus().equalsIgnoreCase("TAKEN") && !schedule.getItsHrStatus().equalsIgnoreCase("CANCELED"))
				&& (schedule.getItsUpdatedBy().equalsIgnoreCase(credentials.getUsername())
						|| identity.hasRole(RTSRoles.ROLE_HR) || identity.hasRole(RTSRoles.ROLE_GM))) {
			result = true;
		}

		return result;
	}

	/**
	 * Reset search form.
	 */
	public void resetSearchForm() {
		candidateName = null;
		recruitRequestName = null;
		fromDate = null;
		toDate = null;
		technicalStatus = null;
		hrStatus = null;
	}

	/**
	 * Onload create interview schedule with filter.
	 */
	public void onloadCreateInterviewScheduleWithFilter() {
		if (candidateName != null && !candidateName.isEmpty()) {
			final List<Candidate> candidates = candidateList.getCandidateByName(candidateName);
			if (RTSUtils.isNotEmpty(candidates)) {
				getInstance().setCandidate(candidates.get(0));
			}
		}
	}

	/** The interview date. */
	Date interviewDate;
	
	/** The interview status. */
	String interviewStatus;
	
	/** The technical location. */
	String technicalLocation;	
	
	/**
	 * Gets the technical location.
	 *
	 * @return the technical location
	 */
	public String getTechnicalLocation() {
		return technicalLocation;
	}

	/**
	 * Sets the technical location.
	 *
	 * @param technicalLocation the new technical location
	 */
	public void setTechnicalLocation(String technicalLocation) {
		this.technicalLocation = technicalLocation;
	}

	/**
	 * Gets the interview date.
	 *
	 * @return the interview date
	 */
	public Date getInterviewDate() {
		return interviewDate;
	}

	/**
	 * Sets the interview date.
	 *
	 * @param interviewDate the new interview date
	 */
	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}

	/**
	 * Gets the interview status.
	 *
	 * @return the interview status
	 */
	public String getInterviewStatus() {
		return interviewStatus;
	}

	/**
	 * Sets the interview status.
	 *
	 * @param interviewStatus the new interview status
	 */
	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	/**
	 * Gets the pre hr status.
	 *
	 * @return the pre hr status
	 */
	public String getPreHRStatus() {
		return preHRStatus;
	}

	/**
	 * Sets the pre hr status.
	 *
	 * @param preHRStatus the new pre hr status
	 */
	public void setPreHRStatus(String preHRStatus) {
		this.preHRStatus = preHRStatus;
	}
	
	/**
	 * Reset interview instance.
	 */
	public void resetInterviewInstance() {
		if (this.getInstance() != null) {
			clearInstance();
		}
	}
	
	@In(create = true)
	private transient LevelTypeList levelTypeList;
	
	public void setAppliedForLevels(List<LevelType> appliedForLevels) {
		this.appliedForLevels = appliedForLevels;
	}

	public List<LevelType> getAppliedForLevels() {
		return appliedForLevels;
	}

	private List<LevelType> appliedForLevels;
	
	public void onLoadAppliedForLevels(){
		appliedForLevels = levelTypeList.getAppliedForLevels();
	}
	
}