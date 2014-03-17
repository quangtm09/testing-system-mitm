package com.bosch.rts.bus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.core.Events;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;

import com.bosch.rts.entity.Candidate;
import com.bosch.rts.entity.CandidateStatus;
import com.bosch.rts.entity.RecruitRequest;
import com.bosch.rts.entity.User;
import com.bosch.rts.session.CandidateHome;
import com.bosch.rts.session.InterviewScheduleList;
import com.bosch.rts.session.RecruitRequestHome;
import com.bosch.rts.session.RecruitRequestList;
import com.bosch.rts.session.TechnicalResultList;
import com.bosch.rts.utilities.RTSConstants;

/**
 * The Class CandidateBussinessComponent.
 */
@Name("candidateBussinessBean")
public class CandidateBussinessComponent implements Serializable {	
	
	/** serialVersionUID. */
	private static final long serialVersionUID = -4593841645480768917L;
	
	/** The status messages. */
	@In
	private transient StatusMessages statusMessages;
	
	/** The log. */
	@Logger
	private transient Log log;
	
	/** The candidate home. */
	@In(create=true, required = true)
	private transient CandidateHome candidateHome;
	
	/** The candidate status helper. */
	@In(create = true)
	private transient CandidateStatusHelper candidateStatusHelper;
	
	@In(create = true)
	private transient RecruitRequestHome recruitRequestHome;
	
	/** The technical result list. */
	@In(create = true)
	private transient TechnicalResultList technicalResultList;
	
	/** The interview schedule list. */
	@In(create = true)
	private InterviewScheduleList interviewScheduleList;

	/** The send to user. */
	private User sendToUser;
	
	/** The candidate status. */
	private CandidateStatus candidateStatus;
	
	/** The new request. */
	private RecruitRequest newRequest;
	
	/** The status description. */
	private String statusDescription;
	
	/** The request description. */
	private String requestDescription;
	
	/** The join date. */
	private Date joinDate = new Date();
	
	/** The technical result remarks. */
	private String technicalResultRemarks = "";

	/**
	 * Gets the join date.
	 *
	 * @return the join date
	 */
	public Date getJoinDate() {
		return joinDate;
	}

	/**
	 * Sets the join date.
	 *
	 * @param joinDate the new join date
	 */
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	/**
	 * Gets the status description.
	 *
	 * @return the status description
	 */
	public String getStatusDescription() {
		return statusDescription;
	}

	/**
	 * Sets the status description.
	 *
	 * @param statusDescription the new status description
	 */
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	/**
	 * Gets the request description.
	 *
	 * @return the request description
	 */
	public String getRequestDescription() {
		return requestDescription;
	}

	/**
	 * Sets the request description.
	 *
	 * @param requestDescription the new request description
	 */
	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
	}

	/**
	 * Gets the candidate status.
	 *
	 * @return the candidate status
	 */
	public CandidateStatus getCandidateStatus() {
		return candidateStatus;
	}

	/**
	 * Sets the candidate status.
	 *
	 * @param candidateStatus the new candidate status
	 */
	public void setCandidateStatus(CandidateStatus candidateStatus) {
		this.candidateStatus = candidateStatus;
	}

	/**
	 * Gets the new request.
	 *
	 * @return the new request
	 */
	public RecruitRequest getNewRequest() {
		return newRequest;
	}

	/**
	 * Sets the new request.
	 *
	 * @param newRequest the new new request
	 */
	public void setNewRequest(RecruitRequest newRequest) {
		this.newRequest = newRequest;
	}
	
	/**
	 * Change candidate status.
	 *
	 * @return the string
	 */
	public String changeCandidateStatus() {
		statusMessages.clear();
		String returnMsg = RTSConstants.FAILURE;
		boolean isError = false;
		RecruitRequest recruitRequest = null;
		try {
			CandidateStatus oldStatus = candidateHome.getInstance().getCddStatus();
			if (candidateStatus == null) {
				statusMessages.addFromResourceBundle(Severity.ERROR, "candidate.status.changed.required");
				isError = true;
			} else {
				final Candidate candidate = candidateHome.getInstance();
				if(candidate != null){
					recruitRequest = candidate.getRecruitRequest();
					if(recruitRequest != null && recruitRequest.equals(newRequest)){
						statusMessages.addFromResourceBundle(Severity.ERROR, "candidate.status.changed.same");
						isError = true;
					}
					
					
				}				
			}
			if(!isError){
				if (candidateStatusHelper.isCanChangeToStatus(oldStatus, candidateStatus)) {
					candidateHome.getInstance().setCddStatus(candidateStatus);
					
					if(recruitRequest != null){						
						if(candidateStatus.equals(CandidateStatus.OFFER_ACCEPTED)){
							final int numberOfferConfirmed = recruitRequest.getNumberOfferConfirmed();		
							
							if(!oldStatus.equals(CandidateStatus.SELECTED)){
								recruitRequest.setNumberOfferConfirmed(numberOfferConfirmed+1);
								final int numberRecruited = recruitRequest.getNumberRecruited();
								recruitRequest.setNumberRecruited(numberRecruited+1);
							} 
							
						} else if(candidateStatus.equals(CandidateStatus.SELECTED)){
							final int numberSelected = recruitRequest.getNumberRecruited();
							if(!oldStatus.equals(CandidateStatus.OFFER_ACCEPTED)){
								recruitRequest.setNumberRecruited(numberSelected+1);
							} 
						}	else if(candidateStatus.equals(CandidateStatus.JOINED)){
							final int numberCandidateJoined = recruitRequest.getNumberCandidateJoined();
							if(!oldStatus.equals(CandidateStatus.OFFER_ACCEPTED) && !oldStatus.equals(CandidateStatus.SELECTED)){
								final int numberOfferConfirmed = recruitRequest.getNumberOfferConfirmed();
								recruitRequest.setNumberRecruited(numberOfferConfirmed+1);
							} 
							recruitRequest.setNumberCandidateJoined(numberCandidateJoined+1);
						}	
					}
					
					recruitRequestHome.getInstance();
					recruitRequestHome.clearInstance();
					recruitRequestHome.setInstance(recruitRequest);
					recruitRequestHome.update();
					
					if (candidateStatus.equals(CandidateStatus.JOINED)) {						
						candidateHome.getInstance().setCddJoiningDate(joinDate);
					} else {
						candidateHome.getInstance().setCddJoiningDate(null);
					}		
					
					
					//Events.instance().raiseEvent("candidate_status_changed",
						//	candidateHome.getInstance().getCddCandidateId(), oldStatus);
					
					returnMsg = candidateHome.update();		
					statusMessages.clear();
					
					if(RTSConstants.UPDATED.equals(returnMsg)){
						statusMessages.addFromResourceBundle(Severity.INFO, "candidate.status.changed.success");			
					} else {
						statusMessages.addFromResourceBundle(Severity.ERROR, "candidate.status.changed.failure");		
						log.error("Error in candidate status change.");
					}
				}
			}			
		} catch (Exception e) {
			log.error("Error in changing candidate status. " + e);
			statusMessages.addFromResourceBundle(Severity.ERROR, "candidate.status.changed.failure");	
		}
		
		return returnMsg;
	}

	/**
	 * Transfer candidate.
	 *
	 * @return the string
	 */
	public String transferCandidate() {
		statusMessages.clear();
		String returnMsg = RTSConstants.FAILURE;
		boolean isError = false;
		try {
			if (newRequest == null) {
				statusMessages.addFromResourceBundle(Severity.ERROR, "com.bosch.bus.requestrequired");
				isError = true;
			}
			
			if(!isError){
				CandidateStatus oldStatus = candidateHome.getInstance().getCddStatus();				
				candidateHome.getInstance().setCddStatus(CandidateStatus.NEW);
				candidateHome.getInstance().setRecruitRequest(newRequest);
				
				candidateHome.getInstance().setCddRequestNumber(candidateHome.getInstance().getCddRequestNumber() + 1);
				try{
					final String candidateTransfered = candidateHome.update();
					statusMessages.clear();
					
					if(RTSConstants.UPDATED.equals(candidateTransfered)){						
						Candidate candidate = candidateHome.getInstance();
						final int interviewScheduleId = interviewScheduleList
								.getInterviewScheduleId(candidate.getCddCandidateId());
						setTechnicalResultRemarks(technicalResultList
								.getTechnicalResultRemarks(interviewScheduleId));

						Events.instance().raiseEvent("candidate.transfer.changed.success",
								candidateHome.getCandidateCddCandidateId(), oldStatus);
						
						statusMessages.addFromResourceBundle(Severity.INFO, "candidate.transfer.changed.success");
						returnMsg = RTSConstants.SUCCESS;
					} else {
						statusMessages.addFromResourceBundle(Severity.ERROR, "candidate.transfer.changed.failure");		
						log.error("Error in transfering to other request.");
					}
				} catch (Exception e) {
					log.error("Error in transfering to other request.", e);
					statusMessages.addFromResourceBundle(Severity.ERROR, "candidate.transfer.changed.failure");
				}								
			}
			
		} catch (Exception e) {
			log.error("Error in transfering to other request.", e);
			statusMessages.addFromResourceBundle(Severity.ERROR, "candidate.transfer.changed.failure");
		}
		
		return returnMsg;
	}

	/**
	 * Get transferable requests based on organization unit level path.
	 *
	 * @param candidate the candidate
	 * @return the transferable request
	 */
	public List<RecruitRequest> getTransferableRequest(final Candidate candidate) {
		List<RecruitRequest> recruitRequests = new ArrayList<RecruitRequest>();
		if(candidate != null){
			final RecruitRequestList recruitRequestList = (RecruitRequestList) Component.getInstance("recruitRequestList");
			final String LEVEL_PATH = candidate.getOrgUnit().getLevelPath();
			recruitRequests  = recruitRequestList.getRecruitRequestByOrgUnitLevelPath(LEVEL_PATH);
		}
		
		return recruitRequests;
	}

	/**
	 * Sets the send to user.
	 *
	 * @param sendTo the send to
	 * @return the user
	 */
	public User setSendToUser(User sendTo) {
		return this.sendToUser = sendTo;
	}

	/**
	 * Gets the send to user.
	 *
	 * @return the send to user
	 */
	public User getSendToUser() {
		return sendToUser;
	}

	/**
	 * Sets the technical result remarks.
	 *
	 * @param technicalResultRemarks the new technical result remarks
	 */
	public void setTechnicalResultRemarks(String technicalResultRemarks) {
		this.technicalResultRemarks = technicalResultRemarks;
	}

	/**
	 * Gets the technical result remarks.
	 *
	 * @return the technical result remarks
	 */
	public String getTechnicalResultRemarks() {
		return technicalResultRemarks;
	}
	
	/**
	 * Reset status messages.
	 */
	public void resetStatusMessages(){
		statusMessages.clear();
	}
}
