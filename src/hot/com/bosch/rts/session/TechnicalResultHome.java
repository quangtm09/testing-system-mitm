/*
 * /rts/src/hot/com/bosch/rts/session/TechnicalResultHome.java
 */
package com.bosch.rts.session;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Events;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;

import com.bosch.rts.entity.Candidate;
import com.bosch.rts.entity.CandidateStatus;
import com.bosch.rts.entity.InterviewAssessmentTemplates;
import com.bosch.rts.entity.InterviewSchedule;
import com.bosch.rts.entity.OrgUnit;
import com.bosch.rts.entity.TechnicalResult;
import com.bosch.rts.entity.TechnicalResultAttributeValue;
import com.bosch.rts.entity.TechnicalResultGroup;
import com.bosch.rts.entity.TechnicalResultLine;
import com.bosch.rts.entity.TechnicalResultLineAttribute;
import com.bosch.rts.entity.User;
import com.bosch.rts.utilities.Constants.InterviewScheduleStatus;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSQueries;
import com.bosch.rts.utilities.RTSStatus;
import com.bosch.rts.utilities.RTSUtils;

/**
 * The Class TechnicalResultHome.
 * @author khb1hc
 */
@Name("technicalResultHome")
@Scope(ScopeType.PAGE)
public class TechnicalResultHome extends EntityHome<TechnicalResult> {

	/** serialVersionUID. */
	private static final long serialVersionUID = -943002343729546659L;	
	
	/** The status messages. */
	@In(create = true)
	private transient StatusMessages statusMessages;
	
	/** The interview schedule home. */
	@In(create = true)
	private transient InterviewScheduleHome interviewScheduleHome;
	
	/** The interview history home. */
	@In(create = true)
	private transient InterviewHistoryHome interviewHistoryHome;	
	
	/** The interview assessment templates list. */
	@In(create = true)
	private transient InterviewAssessmentTemplatesList interviewAssessmentTemplatesList;	

	/** The logged in user. */
	@In(scope = ScopeType.SESSION, required = false)
	private transient User loggedInUser;	
	
	/** The candidate home. */
	@In(create = true)
	private transient CandidateHome candidateHome;
	
	/** The user home. */
	@In(create = true)
	private transient UserHome userHome;
	
	/** The log. */
	@Logger
	private transient Log log;

	/**
	 * Sets the technical result itr technical result id.
	 *
	 * @param id the new technical result itr technical result id
	 */
	public void setTechnicalResultItrTechnicalResultId(Integer id) {
		setId(id);
	}

	/**
	 * Gets the technical result itr technical result id.
	 *
	 * @return the technical result itr technical result id
	 */
	public Integer getTechnicalResultItrTechnicalResultId() {
		return (Integer) getId();
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#createInstance()
	 */
	@Override
	protected TechnicalResult createInstance() {
		TechnicalResult technicalResult = new TechnicalResult();
		return technicalResult;
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
	 * Find and set interview assessment template for technical result.
	 * If in EDIT/VIEW mode, interview schedule has its own technical result object already, 
	 * Then apply the interview assessment template for interview result from value of interview schedule.
	 * Otherwise (CREATE mode), construct a new technical result object, then apply the interview assessment template 
	 * for interview result from value of interview schedule.
	 */
	public void onloadTechnicalResult(final char isNewTemplate){		
		
		final InterviewSchedule interviewSchedule = interviewScheduleHome.getInstance();		
		if(this.getInstance().getInterviewAssessmentTemplates() == null){
			if(interviewSchedule != null){		
				final int APPLIED_FOR_LEVEL = interviewSchedule.getItsApplyForLevel().getId();
				if(APPLIED_FOR_LEVEL > 0){
					final InterviewAssessmentTemplates template = interviewAssessmentTemplatesList.findInterviewAssessmentTemplates(APPLIED_FOR_LEVEL, isNewTemplate);
					this.getInstance().setInterviewAssessmentTemplates(template);
				}
			}	
		}	
	}
	
	/**
	 * Initialize the attribute values in EDIT/VIEW mode.
	 */
	public void onloadAttributeValues(){
		if(this.getInstance().getInterviewAssessmentTemplates() != null 
				&& RTSUtils.isNotEmpty(this.getInstance().getInterviewAssessmentTemplates().getGroupsList())){			
			
			if(this.getInstance().getInterviewSchedule() != null){
				final int INTERVIEW_SCHEDULE_ID = this.getInstance().getInterviewSchedule().getItsInterviewScheduleId();
				Map<Integer, TechnicalResultAttributeValue> atrributeValues = this.getAttributeValues(INTERVIEW_SCHEDULE_ID);
				if(atrributeValues != null && !atrributeValues.isEmpty()){
					for(TechnicalResultGroup group : this.getInstance().getInterviewAssessmentTemplates().getGroupsList()){
						if(group != null && RTSUtils.isNotEmpty(group.getLinesList())){
							for(TechnicalResultLine line : group.getLinesList()){
								if(line != null){
									for(final TechnicalResultLineAttribute attribute : line.getTechnicalResultLineAttributeList()){
										if(attribute != null){
											final int attributeId = attribute.getId();									 
											final TechnicalResultAttributeValue value = atrributeValues.get(attributeId);
											if(value != null){
												attribute.setValue(value.getValue());
											}							
										}								
									}
								}
									
							}					
						}			
					}
				}
		
			}
					
					
		}	 
	}
	
	/** The selected technical result groups. */
	private List<TechnicalResultGroup> selectedTechnicalResultGroups = null;
	
	/**
	 * Gets the selected technical result groups.
	 *
	 * @return the selected technical result groups
	 */
	public List<TechnicalResultGroup> getSelectedTechnicalResultGroups() {
		return this.selectedTechnicalResultGroups;
	}

	/**
	 * Sets the selected technical result groups.
	 *
	 * @param selectedTechnicalResultGroups the new selected technical result groups
	 */
	public void setSelectedTechnicalResultGroups(List<TechnicalResultGroup> selectedTechnicalResultGroups) {
		this.selectedTechnicalResultGroups = selectedTechnicalResultGroups;
	}
	
	/**
	 * Find Attribute Values.
	 *
	 * @param interviewScheduleId the interview schedule id
	 * @return attrubuteValues Map<Integer, TechnicalResultAttributeValue>
	 */
	@SuppressWarnings("unchecked")
	private Map<Integer, TechnicalResultAttributeValue> getAttributeValues(final int interviewScheduleId){
		Map<Integer, TechnicalResultAttributeValue> attrubuteValues = new HashMap<Integer, TechnicalResultAttributeValue>();
		final String sql = RTSQueries.findAttributeValues();	
		final Query query = getEntityManager().createQuery(sql);
		query.setParameter(RTSConstants.ITS_INTERVIEW_SCHEDULE_ID, interviewScheduleId);
		final List<TechnicalResultAttributeValue> values = query.getResultList();
		if(RTSUtils.isNotEmpty(values)){
			for(TechnicalResultAttributeValue value : values){
				attrubuteValues.put(value.getAttributeId(), value);
			}			
		}
		
		return attrubuteValues;
	}

	/**
	 * Wire.
	 */
	public void wire() {
		getInstance();
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
	public TechnicalResult getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}
	
	/**
	 * Check editable technical.
	 *
	 * @param _sch the _sch
	 * @return true, if successful
	 */
	public boolean checkEditableTechnical(final InterviewSchedule _sch){
		final String status = _sch.getItsTechnicalStatus();
		if(status.equalsIgnoreCase(RTSStatus.NEW)){
			return true;
		}
		
		return false;
	}
	
	/** The org unit list. */
	@In(create = true)
	private OrgUnitList orgUnitList;
	
	/**
	 * Gets the choose able org unit list.
	 *
	 * @return the choose able org unit list
	 */
	public List<OrgUnit> getChooseAbleOrgUnitList() {
		orgUnitList.setMaxResults(null);
		return orgUnitList.getResultList();
	}
	
	/**
	 * Give technical feedback.
	 *
	 * @return result
	 */
	public String giveTechnicalFeedback(){
		String result = RTSConstants.FAILURE;
		final String ERROR_MESSAGE = "feedback.technical.create.error";
		boolean isError = false;
		
		final Date createdOn = new Date();
		final String createdBy = loggedInUser.getUsrUserName();
		
		this.getInstance().setCreatedOn(createdOn);
		this.getInstance().setCreatedBy(createdBy);		
		this.getInstance().setUpdatedOn(createdOn);
		this.getInstance().setUpdatedBy(createdBy);	
		
		interviewScheduleHome.getInstance().setItsUpdatedOn(createdOn);
		interviewScheduleHome.getInstance().setItsUpdatedBy(createdBy);		
			
		final Candidate candidate = interviewScheduleHome.getInstance().getCandidate();
		if(candidate != null){			
			final int OVERALL_EVALUATION = Integer.valueOf(this.getInstance().getOverallEvaluation());

			switch (OVERALL_EVALUATION) {
			
			case 1:
				candidate.setCddStatus(CandidateStatus.TECHNICAL_PASS);
				this.getInstance().setPriority((short) 3); // high priority
				interviewScheduleHome.getInstance().setItsTechnicalStatus(InterviewScheduleStatus.TAKEN.dbString());
				break;
			case 2:
				candidate.setCddStatus(CandidateStatus.TECHNICAL_PASS);
				this.getInstance().setPriority((short) 2); //normal
				interviewScheduleHome.getInstance().setItsTechnicalStatus(InterviewScheduleStatus.TAKEN.dbString());
				break;			
			case 3:
				candidate.setCddStatus(CandidateStatus.TECHNICAL_PASS);
				this.getInstance().setPriority((short) 1); // low
				interviewScheduleHome.getInstance().setItsTechnicalStatus(InterviewScheduleStatus.TAKEN.dbString());
				break;
			case 4:
				candidate.setCddStatus(CandidateStatus.TECHNICAL_FAIL);
				interviewScheduleHome.getInstance().setItsTechnicalStatus(InterviewScheduleStatus.TAKEN.dbString());
				break;	
			case 5:
				candidate.setCddStatus(CandidateStatus.TECHNICAL_ON_HOLD);
				this.getInstance().setPriority((short) 1);
				interviewScheduleHome.getInstance().setItsTechnicalStatus(InterviewScheduleStatus.TAKEN.dbString());
				
				//refer to another Org. Unit
				if(candidate.getRecruitRequest() != null){
					candidate.setRecruitRequest(null);
				}
				break;
			default:
				log.error("Error in giving technical feedback: no overall evaluation found.");
				statusMessages.clear();
				statusMessages.addFromResourceBundle(Severity.ERROR, ERROR_MESSAGE);
			}
			
			try {
				statusMessages.clear();
				result = interviewScheduleHome.update();
			} catch (Exception e) {
				log.error("Error in giving technical feedback - update interview schedule id: " + interviewScheduleHome.getInstance().getItsInterviewScheduleId() + e);
				statusMessages.clear();
				statusMessages.addFromResourceBundle(Severity.ERROR, ERROR_MESSAGE);
				isError = true;
			}	
			
			this.getInstance().setInterviewSchedule(interviewScheduleHome.getInstance());
			try {
				statusMessages.clear();
				this.persist();	
			} catch (Exception e) {
				log.error("Error in giving technical feedback - interview schedule id: " + interviewScheduleHome.getInstance().getItsInterviewScheduleId() + e);
				statusMessages.clear();
				statusMessages.addFromResourceBundle(Severity.ERROR, ERROR_MESSAGE);
				isError = true;
			}	
			
			
			//update candidate
			candidateHome.setInstance(candidate);
			candidateHome.getInstance().setCddUpdatedDate(createdOn);
			candidateHome.getInstance().setCddUpdatedBy(createdBy);	
			try {					
				statusMessages.clear();
				candidateHome.update();
			} catch (Exception e) {
				log.error("Error in giving technical feedback - update candidate id: " + candidateHome.getInstance().getCddCandidateId() + e);
				statusMessages.clear();
				statusMessages.addFromResourceBundle(Severity.ERROR, ERROR_MESSAGE);
				isError = true;
			}	
		} else {
			log.error("Error in giving technical feedback: no candidate found.");
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, ERROR_MESSAGE);
			isError = true;
		}				
		
		//attribute values
		if(this.getInstance().getInterviewAssessmentTemplates() != null 
				&& RTSUtils.isNotEmpty(this.getInstance().getInterviewAssessmentTemplates().getGroupsList())){
			final int interviewScheduleId = interviewScheduleHome.getInstance().getItsInterviewScheduleId();
			for(TechnicalResultGroup group : this.getInstance().getInterviewAssessmentTemplates().getGroupsList()){
				if(group != null && RTSUtils.isNotEmpty(group.getLinesList())){
					for(TechnicalResultLine line : group.getLinesList()){
						if(line != null){
							for(TechnicalResultLineAttribute attribute : line.getTechnicalResultLineAttributeList()){
								if(attribute != null && !attribute.getControlType().getName().equals(RTSConstants.TEXT)){
									final int ATTRIBUTE_ID = attribute.getId();
									final String INPUT_VALUE = attribute.getValue();
									attribute.setValue(null);
										
									//Attribute value
									TechnicalResultAttributeValue attributeValue = new TechnicalResultAttributeValue();										
									attributeValue.setCreatedBy(createdBy);
									attributeValue.setCreatedOn(createdOn);									
									attributeValue.setValue(INPUT_VALUE);
									attributeValue.setInterviewScheduleId(interviewScheduleId);
									attributeValue.setAttributeId(ATTRIBUTE_ID);
									try{
										getEntityManager().persist(attributeValue);		
									} catch (Exception e) {
										log.error("Error in creating value of attribute id: " + ATTRIBUTE_ID + ". " + e); 
										statusMessages.clear();
										statusMessages.addFromResourceBundle(Severity.ERROR, ERROR_MESSAGE);
										isError = true;
									}																		
								}							
							}
						}						
					}			
				}			
			}			
		}		
		
		// update interview history
		interviewHistoryHome.clearInstance();		
		interviewHistoryHome.getInstance().setCandidate(candidateHome.getInstance());				
	
		if(interviewScheduleHome.getInstance().getRecruitRequest() != null){
			interviewHistoryHome.getInstance().setRecruitRequest(interviewScheduleHome.getInstance().getRecruitRequest());
		}
		
		interviewHistoryHome.getInstance().setIthStatus(candidateHome.getInstance().getCddStatus().dbString());
		interviewHistoryHome.getInstance().setIthRequestNumber(candidateHome.getInstance().getCddRequestNumber());			
		interviewHistoryHome.getInstance().setIthUpdateDate(createdOn);
		try {		
			statusMessages.clear();
			interviewHistoryHome.persist();
		} catch (Exception e) {
			log.error("Error in giving technical feedback - create interview history - interview schedule id: " + interviewScheduleHome.getInstance().getItsInterviewScheduleId()+ e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, ERROR_MESSAGE);
			isError = true;
			result = RTSConstants.FAILURE;
		}
		
		if(!isError){
			final String ownerBy = this.getInstance().getInterviewSchedule().getItsUpdatedBy();
			User onwer = null;
			try{
				onwer = userHome.findUserByUsername(ownerBy);
			} catch (Exception e) {
				log.error("Error in sending emails after technical feedback given - interview schedule id: " + interviewScheduleHome.getInstance().getItsInterviewScheduleId()+ e);
				result = RTSConstants.FAILURE;
			}			
			
			//send email to owner once interviewer gave feedback
			Events.instance().raiseEvent("sendMailAfterTechnicalResultCreated", 
					onwer,
					loggedInUser, 
					this.getInstance(), 
					this.getInstance().getInterviewSchedule().getInterviewers(),
					"created");	
			
			interviewHistoryHome.clearInstance();
			candidateHome.clearInstance();
			interviewScheduleHome.clearInstance();
			this.clearInstance();	
			
			result = RTSConstants.PERSISTED;
			
		} 
		
		return result;
	}

	/**
	 * Update technical feedback.
	 *
	 * @return result
	 */
	public String updateTechnicalFeedback(){
		
		String result = RTSConstants.FAILURE;
		final String ERROR_MESSAGE = "feedback.technical.update.error";
		
		final Date updatedOn = new Date();
		final String updatedBy = loggedInUser.getUsrUserName();
		
		this.getInstance().setCreatedOn(updatedOn);
		this.getInstance().setCreatedBy(updatedBy);
		
		this.getInstance().setInterviewSchedule(interviewScheduleHome.getInstance());
		try {
			statusMessages.clear();
			result = this.update();	
		} catch (Exception e) {
			log.error("Error in updaing technical feedback - interview schedule id: " + interviewScheduleHome.getInstance().getItsInterviewScheduleId() + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, ERROR_MESSAGE);
		}			
		
		interviewScheduleHome.getInstance().setItsTechnicalStatus(InterviewScheduleStatus.TAKEN.dbString());
		interviewScheduleHome.getInstance().setItsUpdatedOn(updatedOn);
		interviewScheduleHome.getInstance().setItsUpdatedBy(updatedBy);		
		try {
			statusMessages.clear();
			result = interviewScheduleHome.update();
		} catch (Exception e) {
			log.error("Error in upding technical feedback - update interview schedule id: " + interviewScheduleHome.getInstance().getItsInterviewScheduleId() + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, ERROR_MESSAGE);
		}		
				
		final Candidate candidate = interviewScheduleHome.getInstance().getCandidate();
		if(candidate != null){			
			
			switch (this.getInstance().getOverallEvaluation()) {
			
			case 1:
				candidate.setCddStatus(CandidateStatus.TECHNICAL_PASS);
				this.getInstance().setPriority((short) 3); // high priority
				interviewScheduleHome.getInstance().setItsTechnicalStatus(InterviewScheduleStatus.TAKEN.dbString());
				break;
			case 2:
				candidate.setCddStatus(CandidateStatus.TECHNICAL_PASS);
				this.getInstance().setPriority((short) 2); //normal
				interviewScheduleHome.getInstance().setItsTechnicalStatus(InterviewScheduleStatus.TAKEN.dbString());
				break;			
			case 3:
				candidate.setCddStatus(CandidateStatus.TECHNICAL_PASS);
				this.getInstance().setPriority((short) 1); // low
				interviewScheduleHome.getInstance().setItsTechnicalStatus(InterviewScheduleStatus.TAKEN.dbString());
				break;
			case 4:
				candidate.setCddStatus(CandidateStatus.TECHNICAL_FAIL);
				interviewScheduleHome.getInstance().setItsTechnicalStatus(InterviewScheduleStatus.TAKEN.dbString());
				break;	
			case 5:
				candidate.setCddStatus(CandidateStatus.TECHNICAL_ON_HOLD);
				this.getInstance().setPriority((short) 1);
				interviewScheduleHome.getInstance().setItsTechnicalStatus(InterviewScheduleStatus.TAKEN.dbString());
				
				//refer to another Org. Unit
				if(candidate.getRecruitRequest() != null){
					candidate.setRecruitRequest(null);
				}
				break;
			}	
			
			candidateHome.setInstance(candidate);
			candidateHome.getInstance().setCddUpdatedDate(updatedOn);
			candidateHome.getInstance().setCddUpdatedBy(updatedBy);	
			try {					
				statusMessages.clear();
				candidateHome.update();
			} catch (Exception e) {
				log.error("Error in updating technical feedback - update candidate id: " + candidateHome.getInstance().getCddCandidateId() + e);
				statusMessages.clear();
				statusMessages.addFromResourceBundle(Severity.ERROR, ERROR_MESSAGE);
			}	
		} else {
			log.error("Error in updating technical feedback: no candidate found.");
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, ERROR_MESSAGE);
		}	
		
		//attribute values
		if(this.getInstance().getInterviewAssessmentTemplates() != null 
				&& RTSUtils.isNotEmpty(this.getInstance().getInterviewAssessmentTemplates().getGroupsList())){
			final int interviewScheduleId = this.getInstance().getInterviewSchedule().getItsInterviewScheduleId();
			Map<Integer, TechnicalResultAttributeValue> atrributeValues = this.getAttributeValues(interviewScheduleId);
			if(atrributeValues != null && !atrributeValues.isEmpty()){
				for(TechnicalResultGroup group : this.getInstance().getInterviewAssessmentTemplates().getGroupsList()){
					if(group != null && RTSUtils.isNotEmpty(group.getLinesList())){
						for(TechnicalResultLine line : group.getLinesList()){
							if(line != null){
								for(TechnicalResultLineAttribute attribute : line.getTechnicalResultLineAttributeList()){
									if(attribute != null && !attribute.getControlType().getName().equals(RTSConstants.TEXT)){
										final int ATTRIBUTE_ID = attribute.getId();
										final String INPUT_VALUE = attribute.getValue();
										attribute.setValue(null);
											
										//Attribute value
										final TechnicalResultAttributeValue attributeValue = atrributeValues.get(ATTRIBUTE_ID);
										if(attributeValue != null){
											attributeValue.setUpdatedBy(updatedBy);
											attributeValue.setUpdatedOn(updatedOn);									
											attributeValue.setValue(INPUT_VALUE);
											attributeValue.setAttributeId(ATTRIBUTE_ID);
											attributeValue.setInterviewScheduleId(interviewScheduleId);
											try{
												getEntityManager().persist(attributeValue);		
											} catch (Exception e) {
												log.error("Error in updating value of attribute id: " + ATTRIBUTE_ID + ". " + e); 
												statusMessages.clear();
												statusMessages.addFromResourceBundle(Severity.ERROR, ERROR_MESSAGE);
											}		
										}
																										
									}							
								}
							}							
						}				
					}			
				}
			}
						
		}
		
		candidateHome.setInstance(interviewScheduleHome.getInstance().getCandidate());
		candidateHome.getInstance().setCddUpdatedDate(updatedOn);
		candidateHome.getInstance().setCddUpdatedBy(updatedBy);	
		try {					
			statusMessages.clear();
			candidateHome.update();
		} catch (Exception e) {
			log.error("Error in updating technical feedback - update candidate id: " + candidateHome.getInstance().getCddCandidateId() + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, ERROR_MESSAGE);
		}			
		
		// update interview history
		interviewHistoryHome.clearInstance();		
		interviewHistoryHome.getInstance().setCandidate(candidateHome.getInstance());				
	
		if(interviewScheduleHome.getInstance().getRecruitRequest() != null){
			interviewHistoryHome.getInstance().setRecruitRequest(interviewScheduleHome.getInstance().getRecruitRequest());
		}
		
		interviewHistoryHome.getInstance().setIthStatus(candidateHome.getInstance().getCddStatus().dbString());
		interviewHistoryHome.getInstance().setIthRequestNumber(candidateHome.getInstance().getCddRequestNumber());			
		interviewHistoryHome.getInstance().setIthUpdateDate(updatedOn);
		try {		
			statusMessages.clear();
			interviewHistoryHome.persist();
		} catch (Exception e) {
			log.error("Error in updating technical feedback - create interview history - interview schedule id: " + interviewScheduleHome.getInstance().getItsInterviewScheduleId() + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, ERROR_MESSAGE);
			result = RTSConstants.FAILURE;
		}
		
		final String ownerBy = this.getInstance().getInterviewSchedule().getItsUpdatedBy();
		User onwer = null;
		try{
			onwer = userHome.findUserByUsername(ownerBy);
		} catch (Exception e) {
			log.error("Error in sending emails after technical feedback given - interview schedule id: " + interviewScheduleHome.getInstance().getItsInterviewScheduleId()+ e);
			result = RTSConstants.FAILURE;
		}			
		
		//send email to owner once interviewer gave feedback
		Events.instance().raiseEvent("sendMailAfterTechnicalResultCreated", 
				onwer,
				loggedInUser, 
				this.getInstance(), 
				this.getInstance().getInterviewSchedule().getInterviewers(),
				"updated");		
		
		interviewHistoryHome.clearInstance();
		candidateHome.clearInstance();
		interviewScheduleHome.clearInstance();
		
		this.clearInstance();	
		
		return result;				
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
	
}
