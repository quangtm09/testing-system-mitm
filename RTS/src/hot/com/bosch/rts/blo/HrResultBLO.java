/*
 * 
 */
package com.bosch.rts.blo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Events;
import org.jboss.seam.international.StatusMessage;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;

import com.bosch.rts.entity.Candidate;
import com.bosch.rts.entity.CandidateStatus;
import com.bosch.rts.entity.HrResult;
import com.bosch.rts.entity.InterviewHistory;
import com.bosch.rts.entity.InterviewSchedule;
import com.bosch.rts.entity.User;
import com.bosch.rts.session.CandidateHome;
import com.bosch.rts.session.HrResultHome;
import com.bosch.rts.session.HrResultList;
import com.bosch.rts.session.InterviewHistoryHome;
import com.bosch.rts.session.InterviewScheduleHome;
import com.bosch.rts.session.UserHome;
import com.bosch.rts.utilities.Constants.InterviewScheduleStatus;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSUtils;


/**
 * The Class HrResultBLO.
 */
@Name("hrResultBLO")
@Scope(ScopeType.PAGE)
public class HrResultBLO implements Serializable
{
	
	/** serialVersionUID. */
	private static final long serialVersionUID = -8075625728729503822L;
	
	/** The log. */
	@Logger
	private transient Log log;
	
	/** The status messages. */
	@In
	private transient StatusMessages statusMessages;

	/** The hr result list. */
	@In(create = true)
	private transient HrResultList hrResultList;

	/** The hr result home. */
	@In(create = true)
	private transient HrResultHome hrResultHome;

	/** The interview schedule home. */
	@In(create = true)
	private transient InterviewScheduleHome interviewScheduleHome;

	/** The candidate home. */
	@In(create = true)
	private transient CandidateHome candidateHome;
	
	/** The interview history home. */
	@In(create = true)
	private transient InterviewHistoryHome interviewHistoryHome;

	// @In(create = true)
	// InterviewScheduleList interviewScheduleList;

	/** The logged in user. */
	@In(required = true)
	private User loggedInUser;

	/** The hr result. */
	@Out(scope = ScopeType.PAGE, required = false)
	private HrResult hrResult;

	/** The interview schedule. */
	//@Out(scope = ScopeType.PAGE, required = false)
	@Out(required = false)
	private transient InterviewSchedule interviewSchedule;
	
	@In(create = true)
	private transient UserHome userHome;

	/** The interviewer name. */
	private String interviewerName;
	
	/** The interview schedule id. */
	private Integer interviewScheduleId;
	
	/** The action. */
	private String action;
	
	/** The ihr foreign language. */
	private Boolean ihrForeignLanguage;	

	/**
	 * Gets the ihr foreign language.
	 *
	 * @return the ihr foreign language
	 */
	public Boolean getIhrForeignLanguage() {
		return ihrForeignLanguage;
	}

	/**
	 * Sets the ihr foreign language.
	 *
	 * @param ihrForeignLanguage the new ihr foreign language
	 */
	public void setIhrForeignLanguage(Boolean ihrForeignLanguage) {
		this.ihrForeignLanguage = ihrForeignLanguage;
	}
	
	/** The ihrforeign language know. */
	private String ihrforeignLanguageKnow;
	
	/**
	 * Gets the ihrforeign language know.
	 *
	 * @return the ihrforeign language know
	 */
	public String getIhrforeignLanguageKnow() {
		return ihrforeignLanguageKnow;
	}

	/**
	 * Sets the ihrforeign language know.
	 *
	 * @param ihrforeignLanguageKnow the new ihrforeign language know
	 */
	public void setIhrforeignLanguageKnow(String ihrforeignLanguageKnow) {
		this.ihrforeignLanguageKnow = ihrforeignLanguageKnow;
	}
	
	
	/**
	 * Load hr result.
	 */
	@Factory(value = "hrResult", autoCreate = true)
	public void loadHrResult()
	{
		if (action.equals(RTSConstants.VIEW) || action.equals(RTSConstants.EDIT))
		{
			List<HrResult> hrResults = new ArrayList<HrResult>();
			hrResults = hrResultList.getHrResultsByInterviewScheduleId(interviewScheduleId);
			if(RTSUtils.isNotEmpty(hrResults)){
				hrResult = hrResults.get(0);
			}

			if (hrResult == null)
			{
				hrResult = new HrResult();
				hrResult.setInterviewSchedule(interviewSchedule);
				//hrResult.setIhrUrsCreatedByUser(loggedInUser.getUsrUserId());
			}
		} else
		{
			hrResult = new HrResult();
			hrResult.setInterviewSchedule(interviewSchedule);
			//hrResult.set(loggedInUser.getUsrUserId());
		}
		
		//loading HRBLO
		if(hrResult != null){
			this.ihrForeignLanguage = hrResult.getIhrForeignLanguage();
			this.ihrforeignLanguageKnow = hrResult.getIhrforeignLanguageKnow();
		}
	}
	

	/**
	 * Load interview schedule.
	 */
	@Factory(value = "interviewSchedule", autoCreate = true)
	public void loadInterviewSchedule()
	{
		interviewScheduleHome.setId(this.interviewScheduleId);
		interviewSchedule = interviewScheduleHome.getInstance();
	}

	/**
	 * Save hr interview schedule.
	 *
	 * @return the string
	 */
	public String saveHRInterviewSchedule(){
		statusMessages.clear();
		String result = RTSConstants.FAILURE;
		boolean isError = false;
		
		final String ERROR_MESSAGE = "int.sch.error.hr.save";

		if (hrResult != null) {
			hrResultHome.clearInstance();
			
			final String ihrRecommended = hrResult.getIhrRecommended();
			
			if (ihrRecommended.equals(String.valueOf(0))) {
				interviewScheduleHome.getInstance().setItsHrStatus(InterviewScheduleStatus.TAKEN.dbString());
			} else if (ihrRecommended.equals(String.valueOf(1))) {
				interviewScheduleHome.getInstance().setItsHrStatus(InterviewScheduleStatus.DECLINED.dbString());
			} else if (ihrRecommended.equals(String.valueOf(2))) {
				interviewScheduleHome.getInstance().setItsHrStatus(InterviewScheduleStatus.TAKEN.dbString());
			}
			
			final String createdBy = loggedInUser.getUsrUserName();
			final Date createdOn = new Date();

			hrResult.setIhrForeignLanguage(this.ihrForeignLanguage);
			hrResult.setIhrforeignLanguageKnow(this.ihrforeignLanguageKnow);
			
			if (action.equals(RTSConstants.EDIT)){					
				hrResult.setUpdatedBy(createdBy);
				hrResult.setUpdatedOn(createdOn);
				hrResultHome.setInstance(hrResult);
				try{				
					final String HR_UPDATED = hrResultHome.update();
					if (!HR_UPDATED.equals(RTSConstants.UPDATED)) {						
						isError = true;
					}
				} catch (Exception e) {
					log.error("Error in updating HR interview schedule: hrResultHome update()." + e);
					statusMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, ERROR_MESSAGE);
					isError = true;
				}
				
				try{				
					final String HR_UPDATED = interviewScheduleHome.update();
					if (!HR_UPDATED.equals(RTSConstants.UPDATED)) {						
						isError = true;
					}
				} catch (Exception e) {
					log.error("Error in saving HR interview schedule." + e);
					statusMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, ERROR_MESSAGE);
					isError = true;
				}		
				
			} else if (action.equals(RTSConstants.ADD)) {
				
				hrResult.setCreatedBy(createdBy);			
				hrResult.setCreatedOn(createdOn);			
				hrResult.setUpdatedBy(createdBy);
				hrResult.setUpdatedOn(createdOn);
				hrResultHome.setInstance(hrResult);
				try{				
					final String HR_UPDATED = hrResultHome.persist();
					if (!HR_UPDATED.equals(RTSConstants.PERSISTED)) {						
						isError = true;
					}
				} catch (Exception e) {
					log.error("Error in creating new HR interview schedule: hrResultHome persisted()." + e);
					statusMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, ERROR_MESSAGE);
					isError = true;
				}
				//interviewScheduleHome.getInstance().setItsHrStatus(InterviewScheduleStatus.TAKEN.dbString());
				try{				
					final String HR_UPDATED = interviewScheduleHome.update();
					if (!HR_UPDATED.equals(RTSConstants.UPDATED)) {						
						isError = true;
					}
				} catch (Exception e) {
					log.error("Error in saving HR interview schedule." + e);
					statusMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, ERROR_MESSAGE);
					isError = true;
				}			
			}
			
			Candidate candidate = interviewSchedule.getCandidate();
			//final String ihrRecommended = hrResult.getIhrRecommended();
			
			if (!StringUtils.isBlank(ihrRecommended)){
				CandidateStatus cddStatus = null;
				
				if (ihrRecommended.equals(String.valueOf(0))) {
					cddStatus = CandidateStatus.HR_PASS;
				} else if (ihrRecommended.equals(String.valueOf(1))) {
					cddStatus = CandidateStatus.HR_FAIL;
				} else if (ihrRecommended.equals(String.valueOf(2))) {
					cddStatus = CandidateStatus.HR_PASS;
				}
				
				candidate.setCddStatus(cddStatus);
				candidateHome.clearInstance();
				candidateHome.setInstance(candidate);				
				try{				
					final String HR_CANDIDATE_UPDATED = candidateHome.update();
					if (!HR_CANDIDATE_UPDATED.equals(RTSConstants.UPDATED)) {						
						isError = true;
					}
				} catch (Exception e) {
					log.error("Error in saving HR interview schedule: candidate updated()." + e);
					statusMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, ERROR_MESSAGE);
					isError = true;
				}			
				
				// update interview history		
				int candidateID = candidateHome.getInstance().getCddCandidateId();				
				int recruitRequestID ;
				InterviewHistory interviewHst;
				
				if(interviewSchedule != null && interviewSchedule.getRecruitRequest() != null){
					recruitRequestID = interviewSchedule.getRecruitRequest().getRcrRecruitRequestId();
					
					interviewHst = interviewHistoryHome.findInterviewHistory(
							candidateID, 
							recruitRequestID,
							CandidateStatus.HR_PASS.toString(),
							CandidateStatus.HR_FAIL.toString());
				}else{
					interviewHst = interviewHistoryHome.findInterviewHistory(
							candidateID,
							CandidateStatus.HR_PASS.toString(),
							CandidateStatus.HR_FAIL.toString());
				}
				interviewHistoryHome.clearInstance();
				if (interviewHst != null) {
					interviewHistoryHome.setInstance(interviewHst);	
				} 													
				interviewHistoryHome.getInstance().setCandidate(
						interviewSchedule.getCandidate());
				
				if(interviewSchedule.getRecruitRequest() != null){
					interviewHistoryHome.getInstance().setRecruitRequest(
							interviewSchedule.getRecruitRequest());
				}			
				interviewHistoryHome.getInstance().setIthStatus(
						interviewSchedule.getCandidate().getCddStatus().dbString());
				interviewHistoryHome.getInstance().setIthRequestNumber(
						interviewSchedule.getCandidate().getCddRequestNumber());
				interviewHistoryHome.getInstance().setIthUpdateDate(new Date());
			
				if (interviewHst != null) {
					try{				
						final String HR_CANDIDATE_STATUS_UPDATED = interviewHistoryHome.update();
						if (!HR_CANDIDATE_STATUS_UPDATED.equals(RTSConstants.UPDATED)) {						
							isError = true;
						}
					} catch (Exception e) {
						log.error("Error in updating HR interview schedule: candidate status updated(): " + e);
						statusMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, ERROR_MESSAGE);
						isError = true;
					}
				} else {
					try{				
						final String HR_CANDIDATE_STATUS_UPDATED = interviewHistoryHome.persist();
						if (!HR_CANDIDATE_STATUS_UPDATED.equals(RTSConstants.PERSISTED)) {						
							isError = true;
						}
					} catch (Exception e) {
						log.error("Error in creating HR interview schedule: candidate status updated(): " + e);
						statusMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, ERROR_MESSAGE);
						isError = true;
					}
				}			
			}
			
			if(isError){
				log.error("Error in creating HR interview schedule - candidate Id: " + candidate.getCddCandidateId());
				statusMessages.addFromResourceBundle(StatusMessage.Severity.ERROR, ERROR_MESSAGE);
			} else {
				
				//send notification mail to feedbackBy
				final List<User> recipients = new ArrayList<User>();
				recipients.add(0, loggedInUser);		
				
				final User interviewCreatedBy = userHome.findUserByUsername(interviewSchedule.getItsUpdatedBy());
				if(interviewCreatedBy != null){
					recipients.add(1, interviewCreatedBy);
				}
				
				final User candidateCreatedBy = userHome.findUserByUsername(interviewSchedule.getCandidate().getCddUpdatedBy());
				if(candidateCreatedBy != null){
					recipients.add(2, candidateCreatedBy);
				}
				
				//technical interviewers
				recipients.addAll(interviewSchedule.getInterviewers());
				
				//remove duplicated recipients
				RTSUtils.removeDuplicatedUsers(recipients);
				
				//send email to owner once HR interviewer gave feedback
				Events.instance().raiseEvent("sendMailAfterHRInterviewed", 
						candidateCreatedBy,
						interviewCreatedBy,						
						loggedInUser, //feedbackBy
						hrResult, //feedbackBy
						recipients,
						action);	
				
				result = action.equals(RTSConstants.ADD) ? RTSConstants.PERSISTED : RTSConstants.UPDATED;
			}			
		}
		return result;
	}

	/**
	 * Gets the interview schedule id.
	 *
	 * @return the interview schedule id
	 */
	public Integer getInterviewScheduleId()
	{
		return interviewScheduleId;
	}

	/**
	 * Sets the interview schedule id.
	 *
	 * @param interviewScheduleId the new interview schedule id
	 */
	public void setInterviewScheduleId(Integer interviewScheduleId)
	{
		this.interviewScheduleId = interviewScheduleId;
	}

	/**
	 * Gets the action.
	 *
	 * @return the action
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * Sets the action.
	 *
	 * @param action the new action
	 */
	public void setAction(String action)
	{
		this.action = action;
	}

	/**
	 * Gets the interviewer name.
	 *
	 * @return the interviewer name
	 */
	public String getInterviewerName()
	{
		interviewerName = loggedInUser.getUsrFullName().toString();
		return interviewerName;
	}

	/**
	 * Sets the interviewer name.
	 *
	 * @param interviewerName the new interviewer name
	 */
	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}	
	
	/**
	 * Reset foreign language know.
	 */
	public void resetForeignLanguageKnow() {
		if (this.ihrForeignLanguage == false) {
			this.ihrforeignLanguageKnow = "";
		}
	}
}