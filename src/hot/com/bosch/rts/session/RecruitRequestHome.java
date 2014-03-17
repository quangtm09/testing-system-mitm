/*
 * /rts/src/hot/com/bosch/rts/session/RecruitRequestHome.java
 */
package com.bosch.rts.session;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Events;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;

import com.bosch.rts.bus.ChosenSkill;
import com.bosch.rts.bus.FileUploadBean;
import com.bosch.rts.bus.SkillPickBean;
import com.bosch.rts.entity.Candidate;
import com.bosch.rts.entity.InterviewHistory;
import com.bosch.rts.entity.InterviewSchedule;
import com.bosch.rts.entity.OrgUnit;
import com.bosch.rts.entity.RecruitRequest;
import com.bosch.rts.entity.RecruitRequestHasAttachment;
import com.bosch.rts.entity.RecruitRequestHasSkill;
import com.bosch.rts.entity.SkillRequirement;
import com.bosch.rts.entity.SkillRequirementHasSkill;
import com.bosch.rts.entity.User;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSQueries;
import com.bosch.rts.utilities.RTSUtils;
import com.bosch.rts.utilities.UploadFileInfo;

/**
 * Recruit Request Home for Recruit Request module.
 *
 * @author khb1hc
 */
@Name("recruitRequestHome")
@Scope(value = ScopeType.CONVERSATION)
public class RecruitRequestHome extends EntityHome<RecruitRequest> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 7318435537267803339L;	
	
	/** The logged in user. */
	@In(scope = ScopeType.SESSION, required = false)
	private transient User loggedInUser;
		
	/** The log. */
	@Logger
	private transient Log log;	
	
	
	/** The status messages. */
	@In
	private transient StatusMessages statusMessages;		
	
	/** The recruit request list. */
	@In(required=false, create = true)
	private transient RecruitRequestList recruitRequestList;
	
	/** The org unit list. */
	@In(required=false, create = true)
	private transient OrgUnitList orgUnitList;
	
	/** The org unit list. */
	@In(required=false, create = true)
	private transient UserHome userHome;	
	
	/** The org unit list. */
	@In(required=false, create = true)
	private transient UserList userList;
	
	/** The skill pick bean. */
	@In(create = true, required = false)
	private SkillPickBean skillPickBean;
	
	/** The recruit request has skill home. */
	@In(create = true)
	private RecruitRequestHasSkillHome recruitRequestHasSkillHome;
	
	/** The messages. */
	@In
	private Map<String, String> messages;	
	
	/** The recruit requests. */
	@In(required=false, scope = ScopeType.CONVERSATION)
	@Out(required=false, scope = ScopeType.CONVERSATION)
	private List<RecruitRequest> recruitRequests = null;
		
	/** The org units. */
	@In(create = true, required = false)
	@Out(required=false)
	private List<OrgUnit> orgUnits = null;
	
	/** The file upload bean. */
	private FileUploadBean fileUploadBean = new FileUploadBean();
	
	/** The tmp attachments. */
	private Set<RecruitRequestHasAttachment> tmpAttachments  = new HashSet<RecruitRequestHasAttachment>();
	
	/**
	 * Gets the tmp attachments.
	 *
	 * @return the tmp attachments
	 */
	public Set<RecruitRequestHasAttachment> getTmpAttachments() {
		return tmpAttachments;
	}

	/**
	 * Sets the tmp attachments.
	 *
	 * @param tmpAttachments the new tmp attachments
	 */
	public void setTmpAttachments(Set<RecruitRequestHasAttachment> tmpAttachments) {
		this.tmpAttachments = tmpAttachments;
	}
	
	/**
	 * Gets the file upload bean.
	 *
	 * @return the file upload bean
	 */
	public FileUploadBean getFileUploadBean() {
		return fileUploadBean;
	}

	/**
	 * Sets the file upload bean.
	 *
	 * @param fileUploadBean the new file upload bean
	 */
	public void setFileUploadBean(FileUploadBean fileUploadBean) {
		this.fileUploadBean = fileUploadBean;
	}
	
	/**
	 * Gets the org units.
	 *
	 * @return the org units
	 */
	public List<OrgUnit> getOrgUnits() {
		return orgUnits;
	}

	/**
	 * Sets the org units.
	 *
	 * @param orgUnits the new org units
	 */
	public void setOrgUnits(List<OrgUnit> orgUnits) {
		this.orgUnits = orgUnits;
	}
	
	/**
	 * Initialise org units.
	 */
	public void initialiseOrgUnits(){
		RTSUtils.resetList(orgUnits);		
		orgUnits = orgUnitList.getResultList();
		
		RTSUtils.resetList(selectedRecipients);		
		
	}

	/**
	 * Gets the recruit requests.
	 *
	 * @return the recruit requests
	 */
	public List<RecruitRequest> getRecruitRequests() {
		return recruitRequests;
	}

	/**
	 * Sets the recruit requests.
	 *
	 * @param recruitRequests the new recruit requests
	 */
	public void setRecruitRequests(List<RecruitRequest> recruitRequests) {
		this.recruitRequests = recruitRequests;
	}

	/**
	 * Sets the recruit request rcr recruit request id.
	 *
	 * @param id the new recruit request rcr recruit request id
	 */
	public void setRecruitRequestRcrRecruitRequestId(Integer id) {
		setId(id);
	}

	/**
	 * Gets the recruit request rcr recruit request id.
	 *
	 * @return the recruit request rcr recruit request id
	 */
	public Integer getRecruitRequestRcrRecruitRequestId() {
		return (Integer) getId();
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#createInstance()
	 */
	protected RecruitRequest createInstance() {
		return new RecruitRequest();
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
	 * New instance.
	 *
	 * @return the recruit request
	 */
	public RecruitRequest newInstance(){
		final RecruitRequest recruitRequest = createInstance();
		setInstance(recruitRequest);
		return recruitRequest;
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
	public RecruitRequest getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	/**
	 * Gets the candidates.
	 *
	 * @return the candidates
	 */
	public List<Candidate> getCandidates() {
		return getInstance() == null ? null : new ArrayList<Candidate>(
				getInstance().getCandidates());
	}

	/**
	 * Gets the interview histories.
	 *
	 * @return the interview histories
	 */
	public List<InterviewHistory> getInterviewHistories() {
		return getInstance() == null ? null : new ArrayList<InterviewHistory>(
				getInstance().getInterviewHistories());
	}

	/**
	 * Gets the interview schedules.
	 *
	 * @return the interview schedules
	 */
	public List<InterviewSchedule> getInterviewSchedules() {
		return getInstance() == null ? null : new ArrayList<InterviewSchedule>(
				getInstance().getInterviewSchedules());
	}
	
	/**
	 * Find recruit request by org unit for interview schedule list.
	 *
	 * @param orgUnitId the org unit id
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<RecruitRequest> findRecruitRequestByOrgUnitForInterviewScheduleList(
			int orgUnitId) {		
		final String queryStr = RTSQueries.getRecruitRequestByOrgUnitForInterviewScheduleList();
		Query query = getEntityManager().createQuery(queryStr);
		query.setParameter(RTSConstants.ORG_UNIT_ID, orgUnitId);
		List<RecruitRequest> requestList = query.getResultList();
		return requestList;
	}

	/**
	 * Find all recruit request for interview schedule list.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<RecruitRequest> findAllRecruitRequestForInterviewScheduleList() {
		final String queryStr = RTSQueries.getAllRecruitRequestForInterviewScheduleList();
		final Query query = getEntityManager().createQuery(queryStr);
		List<RecruitRequest> requestList = query.getResultList();
		return requestList;
	}
	
	/**
	 * View recruit request by id.
	 *
	 * @param id the id
	 * @return the recruit request
	 */
	public RecruitRequest viewRecruitRequestById(final int id) {		
		return recruitRequestList.findRecruitRequestById(id);
	}
	

	/**
	 * Fetch recruit requests values.
	 */
	public void onloadApprovalList(){		
		this.setApprovedBy(loggedInUser);
		this.setStatus(RTSConstants.OPEN);
	}
	
	/**
	 * Fetch recruit requests values.
	 */
	public void fetchRecruitRequestsValues(){		
		doSearch();
		
		//auto resetting status if valid date after today
		//this.resetStatus();
	}
	
	/**
	 * Search.
	 */
	@Begin(join=true)
	public void search(){
		doSearch();
	}
	
	/**
	 * Do search.
	 */
	private void doSearch(){
		statusMessages.clear();		
		RTSUtils.resetList(recruitRequests);
		
		String validateMsg = null;		
		
		List<Integer> orgUnitIds = null;
		if(this.orgUnit != null){
			orgUnitIds = new ArrayList<Integer>();
			final String levelPath = this.orgUnit.getLevelPath();
			orgUnitIds = recruitRequestList.getOrgUnitIds(levelPath);		
		}				
			
		final String recruitRequestName = this.recruitRequestName != null ? this.recruitRequestName.trim() : null;
		final String status = this.status != null ? this.status : null;
		final Date requestedDate = this.requestedDate != null ? this.requestedDate : null;
		final Date closedDate = this.closedDate != null ? this.closedDate : null;
		
		try {	
			recruitRequests = recruitRequestList.searchRecruitRequest(
					orgUnitIds, 
					recruitRequestName, 
					status, 
					requestedDate, 
					closedDate, 
					this.approvedBy);				
		} catch (Exception e) {					
			validateMsg = "error.recruit.request.search";		
			log.error("Error in searching recruit request. " + e);
		}				
	
		
		if(!StringUtils.isBlank(validateMsg)){
			statusMessages.addFromResourceBundle(Severity.ERROR, validateMsg);	
		}		
	}
		
	/** The recruit request name. */
	private String recruitRequestName = null;	
	
	/** The recruit request. */
	private RecruitRequest recruitRequest = null;
	
	/** The requested date. */
	private Date requestedDate = null;
	
	/** The closed date. */
	private Date closedDate = null;
	
	/**
	 * Gets the requested date.
	 *
	 * @return the requested date
	 */
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

	/** The status. */
	private String status = null;	

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
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(final String status) {
		this.status = status;
	}
	
	/**
	 * Gets the recruit request.
	 *
	 * @return the recruit request
	 */
	public RecruitRequest getRecruitRequest() {
		return recruitRequest;
	}

	/**
	 * Sets the recruit request.
	 *
	 * @param recruitRequest the new recruit request
	 */
	public void setRecruitRequest(final RecruitRequest recruitRequest) {
		this.recruitRequest = recruitRequest;
	}
	
	
	/**
	 * Create a new recruit request.
	 *
	 * @return success if recruit request successfully created, otherwise failure
	 */
    public String createRecruitRequest() {		
    	
		statusMessages.clear();
		String returnMsg = RTSConstants.FAILURE;		
		
		try {					
			this.getInstance().setStatus(RTSConstants.OPEN);
							
			if(RTSUtils.isNotEmpty(recruitRequestSkillsRequests)){
				this.getInstance().setSkillRequirements(recruitRequestSkillsRequests);		
			}		
			
			final Date currentDate = new Date();
			final String username = loggedInUser.getUsrUserName();
			this.getInstance().setCreatedDate(currentDate);
			this.getInstance().setCreatedBy(username);
			this.getInstance().setUpdatedDate(currentDate);
			this.getInstance().setUpdatedBy(username);				
			this.getInstance().setUser(loggedInUser);					
			
			final String folderName = messages.get("com.bosch.storage.recruit_request_doc");				
			recruitRequestDocumentSaver.createFolder(folderName);
			
			this.initUpdateDocument(this.getFileUploadBean());	
			this.getInstance().getAttachments().clear();
			this.getInstance().setAttachments(this.getTmpAttachments());	
			
			setRecruitRequestRcrRecruitRequestId(null);			
			if(this.getInstance().getNumberOfPersons() == null || this.getInstance().getNumberOfPersons() <= 0){
				this.getInstance().setNumberOfPersons(0);
			}
			
			this.getInstance().setNumberRecruited(0);
			this.getInstance().setNumberToOffer(0);
			this.getInstance().setNumberOfferSent(0);
			this.getInstance().setNumberOfferConfirmed(0);
			this.getInstance().setNumberOfferRefused(0);
			this.getInstance().setNumberCandidateJoined(0);
			
			returnMsg = super.persist();	
			
			this.saveChosenSkill();
			
			//send notification mail to owner
			final List<User> recipients = new ArrayList<User>();
			recipients.add(0, loggedInUser);				
			Events.instance().raiseEvent("sendMailAfterRecruitRequestCreated", loggedInUser, this.getInstance(), recipients, "created");
			
			//Send mail to ORGH HEAD for approval
			final List<User> approvers = new ArrayList<User>();
			approvers.add(0, this.getInstance().getApprovedBy());
			Events.instance().raiseEvent("sendMailAfterRecruitRequestCreated", loggedInUser, this.getInstance(), approvers, "approval");
			
			if(RTSConstants.PERSISTED.equals(returnMsg)){
				statusMessages.clear();
				this.clearInstance();
				statusMessages.addFromResourceBundle(Severity.INFO, "recruit.request.create.success");
			}				
			
		} catch (Exception e) {
			log.error("Error in creating new recruit request: " + e);
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.recruit.request.create");
		}		
	
		
		return returnMsg;
	}
        
    
    /**
     * Update recruit request.
     *
     * @return the string
     */
    public String updateRecruitRequest() {		
		statusMessages.clear();
		String returnMsg = RTSConstants.FAILURE;			
					
		try {
			
			this.deleteOldSkillRequirements();			
		
			this.getInstance().setSkillRequirements(recruitRequestSkillsRequests);
		
			final Date currentDate = new Date();
			final String username = loggedInUser.getUsrUserName();
			this.getInstance().setUpdatedDate(currentDate);
			this.getInstance().setUpdatedBy(username);				
			this.getInstance().setUser(loggedInUser);				
			
			final String folderName = messages.get("com.bosch.storage.recruit_request_doc");				
			recruitRequestDocumentSaver.createFolder(folderName);
			
			this.initUpdateDocument(this.getFileUploadBean());
								
			for(RecruitRequestHasAttachment att : this.getInstance().getAttachments()){
				getEntityManager().remove(att);	
			}					
			this.getInstance().getAttachments().clear();
			
			final String basePath = folderName + System.getProperty("file.seperator");
			
			for(UploadFileInfo fileInfo : this.getFileUploadBean().getRemovedFiles() ){						
				recruitRequestDocumentSaver.doDeleteDocument(fileInfo.getName(), basePath);						
			}
			
			this.getInstance().setAttachments(this.getTmpAttachments());
			
			returnMsg = super.update();
			
			this.updateRequiredSkill();
			this.saveChosenSkill();					
			
			//send notification mail to owner
			final List<User> recipients = new ArrayList<User>();
			recipients.add(0, loggedInUser);				
			Events.instance().raiseEvent("sendMailAfterRecruitRequestCreated", loggedInUser, this.getInstance(), recipients, "updated");
			
			//Send mail to ORGH HEAD for approval
			final List<User> approvers = new ArrayList<User>();
			approvers.add(0, this.getInstance().getApprovedBy());
			Events.instance().raiseEvent("sendMailAfterRecruitRequestCreated", loggedInUser, this.getInstance(), approvers, "approval");
			
			if(RTSConstants.PERSISTED.equals(returnMsg)){
				statusMessages.clear();
				this.clearInstance();
				statusMessages.addFromResourceBundle(Severity.INFO, "recruit.request.create.success");
			}	
			
			if(RTSConstants.UPDATED.equals(returnMsg)){
				statusMessages.clear();
				statusMessages.addFromResourceBundle(Severity.INFO, "recruit.request.update.success");	
				this.clearInstance();
			}				
		} catch (Exception e) {
			log.error("Error in updating new recruit request: " + e);
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.recruit.request.update");
		}
		
		return returnMsg;
	}
    
	
	/** The edit skill requirement. */
	private boolean editSkillRequirement = false;
		
	/** The old skill requirement list. */
	private List<SkillRequirement> oldSkillRequirementList = new ArrayList<SkillRequirement>();
	
	/** The temporary skill requirement set. */
	private Set<SkillRequirement> temporarySkillRequirementSet = new HashSet<SkillRequirement>();
	
	/** The recruit request document saver. */
	@In(create = true)
	private RecruitRequestDocumentSaver recruitRequestDocumentSaver;

	/**
	 * Checks if is edits the skill requirement.
	 *
	 * @return true, if is edits the skill requirement
	 */
	public boolean isEditSkillRequirement() {
		return editSkillRequirement;
	}

	/**
	 * Sets the edits the skill requirement.
	 *
	 * @param editSkillRequirement the new edits the skill requirement
	 */
	public void setEditSkillRequirement(boolean editSkillRequirement) {
		this.editSkillRequirement = editSkillRequirement;
	}	

	/**
	 * Gets the skill requirement.
	 *
	 * @return the skill requirement
	 */
	public SkillRequirement getSkillRequirement() {
		return skillRequirement;
	}
	
	/**
	 * New skill requirement.
	 *
	 * @return the skill requirement
	 */
	public SkillRequirement newSkillRequirement() {
		return new SkillRequirement();
	}


	/**
	 * Sets the skill requirement.
	 *
	 * @param skillRequirement the new skill requirement
	 */
	public void setSkillRequirement(SkillRequirement skillRequirement) {
		this.skillRequirement = skillRequirement;
	}
	
	/**
	 * Gets the total number of person require.
	 *
	 * @return the total number of person require
	 *//*
	private int getTotalNumberOfPersonRequire() {
		int numberOfCandidate = 0;
		if(getInstance().getSkillRequirements() != null){
			for (SkillRequirement skr : getInstance().getSkillRequirements()) {
				numberOfCandidate += skr.getNumberOfPersons();
			}
		}
		
		return numberOfCandidate;
	}*/
		
	/**
	 * Inits the update.
	 */
	public void initUpdate() {
		Map<Integer, ChosenSkill> chosenSkillMap = new HashMap<Integer, ChosenSkill>();
		for (RecruitRequestHasSkill rrHasSkill : this.getInstance()
				.getRecruitRequestHasSkills()) {
			chosenSkillMap.put(
					rrHasSkill.getSkill().getSklSkillId(), new ChosenSkill(rrHasSkill.getSkill(), rrHasSkill
							.getRhsRequiredExperienceYearsFrom(), rrHasSkill
							.getRhsRequiredExperienceYearsTo(), rrHasSkill.getSkillLevel())
					);
		}
		skillPickBean.setChosenSkillMap(chosenSkillMap);		
	}

	/**
	 * Delete old skill requirements.
	 */
	private void deleteOldSkillRequirements(){
		if(!RTSUtils.isBlank(oldSkillRequirementList)){
			for(SkillRequirement removedSkill : oldSkillRequirementList){
				for(SkillRequirementHasSkill removedHasSkill 
						: removedSkill.getSkillRequirementHasSkills()){					
					this.getEntityManager().remove(removedHasSkill);					
				}
				
				this.getEntityManager().remove(removedSkill);
			}			
		}
	}
	
	/**
	 * Checks if is user create the recruit request.
	 *
	 * @return true, if is user create the recruit request
	 */
	public boolean isUserCreateTheRecruitRequest() {
		boolean isCreatedBy = false;
		
		if (this.getInstance() != null) {
			if(this.getInstance().getUser() != null ){
				final String createdUsername = this.getInstance().getUser().getUsrUserName();
				final String loggedInUsername = loggedInUser.getUsrUserName();				
				isCreatedBy = createdUsername.equalsIgnoreCase(loggedInUsername) ? true : false;				
			}			
		}
		return isCreatedBy;
	} 
	
	/**
	 * Rr created by.
	 *
	 * @param createdById the created by id
	 * @return true, if successful
	 */
	public boolean rrCreatedBy(final int createdById) {
		boolean isCreatedBy = false;		
		if (createdById > 0) {
			final int loggedIn = this.loggedInUser.getUsrUserId();
			if (loggedIn == createdById) {
				isCreatedBy = true;
			}
		}
		
		return isCreatedBy;
	}

	/**
	 * Adds the skill requirement.
	 */
	public void addSkillRequirement() {		
		if (skillPickBean != null) {			
			if(!RTSUtils.isBlank(skillRequirement.getSkillRequirementHasSkills())){
				RTSUtils.resetSet(oldRequirementHasSkills);
				if(oldRequirementHasSkills == null){
					oldRequirementHasSkills = new  HashSet<SkillRequirementHasSkill>();
				}
				oldRequirementHasSkills.addAll(skillRequirement.getSkillRequirementHasSkills());
				skillRequirement.getSkillRequirementHasSkills().clear();
			}
			
			Map<Integer, ChosenSkill> chosenSkillMap = skillPickBean.getChosenSkillMap();
			if (chosenSkillMap != null && !chosenSkillMap.isEmpty()) {
				for (ChosenSkill chosenSkill : chosenSkillMap.values()) {					
					SkillRequirementHasSkill skillRequirementHasSkill = new SkillRequirementHasSkill();
					skillRequirementHasSkill.setSkill(chosenSkill.getSkill());
					skillRequirementHasSkill.setSkillRequirement(skillRequirement);
					skillRequirementHasSkill.setShsRequiredExperienceYearsFrom(chosenSkill.getFromYear());
					skillRequirementHasSkill.setShsRequiredExperienceYearsTo(chosenSkill.getToYear());
					
					skillRequirementHasSkill.setShsSkillLevel(chosenSkill.getSkillLevel());
					
					skillRequirement.getSkillRequirementHasSkills().add(skillRequirementHasSkill);
				}
			}
		}
		
		skillRequirement.setRecruitRequest(getInstance());	
		if(recruitRequestSkillsRequests == null){
			recruitRequestSkillsRequests = new HashSet<SkillRequirement>();
		}
		recruitRequestSkillsRequests.add(skillRequirement);	
		
		skillRequirement = new SkillRequirement();
		
		skillPickBean.resetSkillBean();
		statusMessages.addFromResourceBundle(Severity.INFO, "recruit.request.has.skill");
	}

	
	/**
	 * Edits the skill requirement.
	 *
	 * @param skillReq the skill req
	 */
	public void editSkillRequirement(final SkillRequirement skillReq) {
		statusMessages.clear();
		this.skillPickBean.setIsCreationMode(false);
		this.skillRequirement = skillReq;
		this.initEditSkillRequirement(skillReq);		
	}

	/**
	 * Inits the edit skill requirement.
	 *
	 * @param skillRequirement the skill requirement
	 */
	private void initEditSkillRequirement(final SkillRequirement skillRequirement) {		
		if(skillRequirement != null){
			Map<Integer, ChosenSkill> chosenSkills = new HashMap<Integer, ChosenSkill>();
			for (SkillRequirementHasSkill srHasSkill : skillRequirement.getSkillRequirementHasSkills()) {
				chosenSkills.put(srHasSkill.getSkill().getSklSkillId(),
						new ChosenSkill(srHasSkill.getSkill(), 
								srHasSkill.getShsRequiredExperienceYearsFrom(), 
								srHasSkill.getshsRequiredExperienceYearsTo()));
			}
			skillPickBean.setChosenSkillMap(chosenSkills);
		}
		
	}
	
	/**
	 * Delete skill requirement.
	 *
	 * @param skillReq the skill req
	 */
	public void deleteSkillRequirement(final SkillRequirement skillReq) {
		statusMessages.clear();			
		if(this.recruitRequestSkillsRequests != null  && !recruitRequestSkillsRequests.isEmpty()){
			oldSkillRequirementList.add(skillReq);
			recruitRequestSkillsRequests.remove(skillReq);			
		}
	}

	/**
	 * Gets the skill requirement list.
	 *
	 * @return the skill requirement list
	 */
	public Set<SkillRequirement> getSkillRequirementList() {
		return temporarySkillRequirementSet;
	}

	/**
	 * Close skill requirement form.
	 */
	public void closeSkillRequirementForm() {
		if (isEditSkillRequirement()) {
			editSkillRequirement = false;
			addSkillRequirement();
		}
		statusMessages.clear();
	}

	/**
	 * Sets the skill requirement list.
	 *
	 * @param skillRequirementList the new skill requirement list
	 */
	public void setSkillRequirementList(final Set<SkillRequirement> skillRequirementList) {
		getInstance().setSkillRequirements(skillRequirementList);
	}
		
	/** The recruit request skills requests. */
	private Set<SkillRequirement> recruitRequestSkillsRequests = new HashSet<SkillRequirement>();
	
	/** The skill requirement. */
	private SkillRequirement skillRequirement = new SkillRequirement();
	
	/** The old requirement has skills. */
	private Set<SkillRequirementHasSkill> oldRequirementHasSkills = new HashSet<SkillRequirementHasSkill>();

	/**
	 * Gets the recruit request skills requests.
	 *
	 * @return the recruit request skills requests
	 */
	public Set<SkillRequirement> getRecruitRequestSkillsRequests() {		
		return recruitRequestSkillsRequests;
	}

	/**
	 * Sets the recruit request skills requests.
	 *
	 * @param recruitRequestSkillsRequests the new recruit request skills requests
	 */
	public void setRecruitRequestSkillsRequests(
			Set<SkillRequirement> recruitRequestSkillsRequests) {
		this.recruitRequestSkillsRequests = recruitRequestSkillsRequests;
	}
	
	/**
	 * Reset required skils.
	 */
	public void resetRequiredSkils(){
		statusMessages.clear();
		skillRequirement = new SkillRequirement();		
		this.skillPickBean.resetSkillBean();
		this.skillPickBean.setIsCreationMode(true);
	}
	
	/** The is error. */
	private boolean isError = false;

	/**
	 * Checks if is error.
	 *
	 * @return true, if is error
	 */
	public boolean isError() {
		return isError;
	}

	/**
	 * Sets the error.
	 *
	 * @param isError the new error
	 */
	public void setError(boolean isError) {
		this.isError = isError;
	}

	/**
	 * Gets the old requirement has skills.
	 *
	 * @return the old requirement has skills
	 */
	public Set<SkillRequirementHasSkill> getOldRequirementHasSkills() {
		return oldRequirementHasSkills;
	}

	/**
	 * Sets the old requirement has skills.
	 *
	 * @param oldRequirementHasSkills the new old requirement has skills
	 */
	public void setOldRequirementHasSkills(
			Set<SkillRequirementHasSkill> oldRequirementHasSkills) {
		this.oldRequirementHasSkills = oldRequirementHasSkills;
	}
		
	/**
	 * Fetch file upload bean.
	 */
	public void fetchFileUploadBean()
	{
		ArrayList<UploadFileInfo> fileInfos = new ArrayList<UploadFileInfo>();
		
		for ( RecruitRequestHasAttachment attachment : this.getInstance().getAttachments()){
			UploadFileInfo fileInfo = new UploadFileInfo();			
			fileInfo.setName(attachment.getAttachmentName());			
			fileInfo.setLength(attachment.getAttachmentLength());			
			fileInfo.setStatus(attachment.getAttachmentStatus());						
			fileInfo.setRecruitRequestAtt(attachment);			
			fileInfos.add(fileInfo);			
		}
				
		this.getFileUploadBean().setFiles(fileInfos);		
		
		final int availableFile = this.getFileUploadBean().getUploadsAvailable() - this.getInstance().getAttachments().size();
		
		this.getFileUploadBean().setUploadsAvailable(availableFile);
	}
	
	/**
	 * Inits the update document.
	 *
	 * @param fileUploadBean the file upload bean
	 */
	private void initUpdateDocument(FileUploadBean fileUploadBean){	
		
		for( UploadFileInfo uploadFileInfo : fileUploadBean.getFiles()){
			
			RecruitRequestHasAttachment attachment = new RecruitRequestHasAttachment();
			final Date currentDate = new Date();
			final String currentUserName = loggedInUser.getUsrUserName();
			
			if(uploadFileInfo.getRecruitRequestAtt() != null 
					&& uploadFileInfo.getRecruitRequestAtt().getAttachmentName() != null 
					&& !uploadFileInfo.getRecruitRequestAtt().getAttachmentName().isEmpty()){		
				
				attachment.setAttachmentName(
						uploadFileInfo.getRecruitRequestAtt().getAttachmentName());
				
				if(uploadFileInfo.getRecruitRequestAtt().getAttachmentCreatedOn() == null){
					attachment.setAttachmentCreatedOn(currentDate);
					attachment.setAttachmentCreatedBy(currentUserName);
				}
				else{					
					attachment.setAttachmentCreatedOn(
							uploadFileInfo.getRecruitRequestAtt().getAttachmentCreatedOn());
					attachment.setAttachmentCreatedBy(
							uploadFileInfo.getRecruitRequestAtt().getAttachmentCreatedBy());
				}				
				
				attachment.setAttachmentUpdatedOn(
						uploadFileInfo.getRecruitRequestAtt().getAttachmentUpdatedOn());
				attachment.setAttachmentUpdatedBy(
						uploadFileInfo.getRecruitRequestAtt().getAttachmentUpdatedBy());
				attachment.setAttachmentLength(
						uploadFileInfo.getRecruitRequestAtt().getAttachmentLength());
				attachment.setAttachmentStatus(uploadFileInfo.getStatus());				
			
				attachment.setRecruitRequest(this.getInstance());
				
				this.getTmpAttachments().add(attachment);
				
			} else{
				final String basePath = messages.get("com.bosch.storage.recruit_request_doc") + RTSConstants.BACK_SLASH;
				
				final String storageName = recruitRequestDocumentSaver.saveDocument(
						uploadFileInfo.getName(), uploadFileInfo, basePath);
				
				attachment.setAttachmentName(storageName);	
				attachment.setAttachmentCreatedOn(currentDate);
				attachment.setAttachmentCreatedBy(currentUserName);
				attachment.setAttachmentUpdatedOn(currentDate);
				attachment.setAttachmentUpdatedBy(currentUserName);
				attachment.setAttachmentLength(uploadFileInfo.getLength());
				attachment.setAttachmentStatus(uploadFileInfo.getStatus());
				attachment.setRecruitRequest(this.getInstance());
				
				this.getTmpAttachments().add(attachment);
				
			}
			
		}	
	}
	
	/** The from view. */
	@Out(required = false)
	@In(required = false)
	private String fromView = null;

	/**
	 * Gets the from view.
	 *
	 * @return the from view
	 */
	public String getFromView() {
		return fromView;
	}

	/**
	 * Sets the from view.
	 *
	 * @param fromView the new from view
	 */
	public void setFromView(String fromView) {
		this.fromView = fromView;
	}
	
	/** The created user. */
	private User createdUser = null;

	/**
	 * Gets the created user.
	 *
	 * @return the created user
	 */
	public User getCreatedUser() {
		return createdUser;
	}

	/**
	 * Sets the created user.
	 *
	 * @param createdUser the new created user
	 */
	public void setCreatedUser(User createdUser) {
		this.createdUser = createdUser;
	}
	
	/**
	 * Update required skill.
	 */
	private void updateRequiredSkill() {
		Map<Integer, ChosenSkill> chosenSkillMap = skillPickBean.getChosenSkillMap();
		List<RecruitRequestHasSkill> removeList = new ArrayList<RecruitRequestHasSkill>(0);
		for (RecruitRequestHasSkill rrHasSkill : this.getInstance().getRecruitRequestHasSkills()) {
			if (chosenSkillMap.containsKey(rrHasSkill.getSkill().getSklSkillId())) {
				ChosenSkill temp = chosenSkillMap.get(rrHasSkill.getSkill().getSklSkillId());
				recruitRequestHasSkillHome.clearInstance();
				recruitRequestHasSkillHome.setInstance(rrHasSkill);
				recruitRequestHasSkillHome.getInstance().setRhsRequiredExperienceYearsFrom(temp.getFromYear());
				recruitRequestHasSkillHome.getInstance().setRhsRequiredExperienceYearsTo(temp.getToYear());
				recruitRequestHasSkillHome.getInstance().setSkillLevel(temp.getSkillLevel());
				recruitRequestHasSkillHome.update();
				chosenSkillMap.remove(rrHasSkill.getSkill().getSklSkillId());
			} else {
				removeList.add(rrHasSkill);
			}
		}
		removeSkill(removeList);
	}
	
	/**
	 * Removes the skill.
	 *
	 * @param rrHasSkillList the rr has skill list
	 */
	private void removeSkill(List<RecruitRequestHasSkill> rrHasSkillList) {
		for (RecruitRequestHasSkill _rrHasSkill : rrHasSkillList) {
			recruitRequestHasSkillHome.setId(_rrHasSkill.getRhsRecruitRequestSkillId());
			recruitRequestHasSkillHome.remove();
		}
	}
	
	/**
	 * Save chosen skill.
	 */
	private void saveChosenSkill() {
		if (skillPickBean != null) {
			Map<Integer, ChosenSkill> chosenSkillMap = skillPickBean.getChosenSkillMap();
			if (chosenSkillMap != null) {
				for (ChosenSkill chosenSkill : chosenSkillMap.values()) {
					recruitRequestHasSkillHome.clearInstance();
					recruitRequestHasSkillHome.getInstance().setRecruitRequest(this.getInstance());
					recruitRequestHasSkillHome.getInstance().setSkill(chosenSkill.getSkill());
					recruitRequestHasSkillHome.getInstance().setRhsRequiredExperienceYearsFrom(chosenSkill.getFromYear());
					recruitRequestHasSkillHome.getInstance().setRhsRequiredExperienceYearsTo(chosenSkill.getToYear());
					recruitRequestHasSkillHome.getInstance().setSkillLevel(chosenSkill.getSkillLevel());					
					recruitRequestHasSkillHome.persist();
				}
			}
		}
	}
	
	/**
	 * Auto complete recruit requests.
	 *
	 * @return the list
	 */
	public List<String> autoCompleteRecruitRequests(){		
		List<String> autoRR = new ArrayList<String>();			
		if(this.getRecruitRequestName() != null){
			final String recruitRequestName = this.getRecruitRequestName();			
			autoRR = recruitRequestList.getRecruitRequestNamesByName(recruitRequestName);
		}
		return autoRR;
	}
	
	/**
	 * Reset status.
	 */
	private void resetStatus(){
		if(RTSUtils.isNotEmpty(this.recruitRequests)){
			Calendar cal = Calendar.getInstance();  
			cal.setTime(new Date());  
			cal.add(Calendar.DAY_OF_YEAR, -1);
			Date yesterday = cal.getTime();  			
			for (RecruitRequest rr : this.recruitRequests) {				
				if(rr.getClosedDate().before(yesterday)){
					rr.setStatus(RTSConstants.CLOSED);
				}
			}
		}
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
	 * Gets the org unit.
	 *
	 * @return the org unit
	 */
	public OrgUnit getOrgUnit() {
		return orgUnit;
	}

	/** The org unit. */
	private OrgUnit orgUnit = null;
	
	/**
	 * Validate expected valid date.
	 *
	 * @param expectedDate the expected date
	 * @param validDate the valid date
	 * @return true, if successful
	 */
	private boolean validateExpectedValidDate(Date expectedDate, Date validDate){
		boolean result = false;
		if(expectedDate != null && validDate != null){
			if(expectedDate.after(validDate)){
				result = true;
			}
		}
		return result;
		
	}
	
	/**
	 * Onload create recruit request.
	 */
	private void onloadCreateRecruitRequest(){		
		if(orgUnit != null){
			getInstance().setOrgUnit(orgUnit);
		}
		
		if(recruitRequestName != null && !recruitRequestName.isEmpty()){
			getInstance().setRecruitRequestName(recruitRequestName);
		}

		
		if(requestedDate != null){
			getInstance().setRequestedDate(requestedDate);
		}
		
		if(closedDate != null){
			getInstance().setClosedDate(closedDate);
		}
	}
	
	/**
	 * Reset search.
	 */
	public void resetSearch() {
		orgUnit = null;
		recruitRequestName = null;
		status =  null;		
		requestedDate = null;
		closedDate = null;
		approvedBy = null;
	}
	
	/**
	 * Reset rr instance.
	 */
	public void resetRRInstance(){
		if(this.getInstance() != null){
			clearInstance();
		}		
		
		//clear fileupload
		if (RTSUtils.isNotEmpty(fileUploadBean.getFiles())) {
			fileUploadBean.getFiles().clear();
		}
		onloadCreateRecruitRequest();
	}
	
	/**
	 * Checks if is rendered footer space.
	 *
	 * @return true, if is rendered footer space
	 */
	public boolean isRenderedFooterSpace(){
		return true;
	}
	
	/**
	 * Populate user to candidate form.
	 *
	 * @param usrUserId the user id
	 * @param userCase the user case
	 */
	public void populateUser(final String usrUserId){	
		this.getInstance().setHandledBy(usrUserId);		
	}	
	
	public void setSelectedRecipients(List<String> selectedRecipients) {
		this.selectedRecipients = selectedRecipients;
	}

	public List<String> getSelectedRecipients() {
		return selectedRecipients;
	}

	private List<String> selectedRecipients = new ArrayList<String>();
	
	private User approvedBy;

	public void setApprovedBy(User approvedBy) {
		this.approvedBy = approvedBy;
	}

	public User getApprovedBy() {
		return approvedBy;
	}
	
	public String changeRecruitRequestStatus(final String status){
		statusMessages.clear();
		
		String result = RTSConstants.FAILURE;
		final Date approvedDate = new Date(); 
		
		this.getInstance().setStatus(status);
		this.getInstance().setApprovedDate(approvedDate);
		this.getInstance().setApprovedBy(loggedInUser);
		try {
			result = super.update();	
		} catch (Exception e) {
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.recruit.request.approval");	
			log.error("Error in changing Recruit Request status: " + e.getMessage());
		}
		
		final String onwnerBy = StringUtils.isNotEmpty(this.getInstance().getUpdatedBy()) 
				? this.getInstance().getUpdatedBy() 
				: this.getInstance().getCreatedBy();
				
		User onwer = null;	
		try {
			onwer = userHome.findUserByUsername(onwnerBy);
		} catch (Exception e) {
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.recruit.request.approval.username");	
			log.error("Error in finding user by username: " + e.getMessage());
			result = RTSConstants.FAILURE;
		}		
		
		//send notification mails to owner and approver
		final List<User> recipients = new ArrayList<User>();
		recipients.add(0, onwer);				
		recipients.add(1, loggedInUser);	
		
		final List<User> notifiers = new ArrayList<User>();
		if(RTSUtils.isNotEmpty(selectedRecipients)){
			
			for(final String notifier : selectedRecipients){
				if(notifier.equals(String.valueOf(2))){
					//HR
					final List<User> users = userList.getUserHasRoles(6);
					if(RTSUtils.isNotEmpty(users)){
						notifiers.addAll(users);
					}
					
				} 
				if(notifier.equals(String.valueOf(4))){
					//Send mail to handled by person
					if(StringUtils.isNotEmpty(this.getInstance().getHandledBy())){
						final User handledBy = userHome.findUserByUsername(this.getInstance().getHandledBy());
						if(handledBy != null){
							notifiers.add(handledBy);
						}						
					}
				}
			}
			
		}
		
		recipients.addAll(notifiers);	
		
		//remove duplicated recipients
		final Set<Integer> userIds = new HashSet<Integer>();
		for (Iterator<User> it = recipients.listIterator(); it.hasNext();) {
			User user = it.next();
			if (userIds.add(user.getUsrUserId()) == false) {
				it.remove();
			}
		}
		
		Events.instance().raiseEvent("sendMailAfterRecruitRequestCreated", onwer, this.getInstance(), recipients, status);	
		return result;	
		
	}
	
	public String closeRecruitRequest(){
		
		statusMessages.clear();
		
		String result = RTSConstants.FAILURE;
		final Date closedDate = new Date(); 
		
		this.getInstance().setStatus(RTSConstants.CLOSED);
		this.getInstance().setUpdatedDate(closedDate);
		this.getInstance().setUpdatedBy(loggedInUser.getUsrUserName());
		
		try {
			result = super.update();	
		} catch (Exception e) {
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.recruit.request.approval");	
			log.error("Error in closing Recruit Request: " + e.getMessage());
		}
		
		final String onwnerBy = StringUtils.isNotEmpty(this.getInstance().getUpdatedBy()) 
				? this.getInstance().getUpdatedBy() 
				: this.getInstance().getCreatedBy();
		
		User onwer = null;	
		try {
			onwer = userHome.findUserByUsername(onwnerBy);
		} catch (Exception e) {
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.recruit.request.close");	
			log.error("Error in finding user by username: " + e.getMessage());
			result = RTSConstants.FAILURE;
		}		
		
		//send notification mails to owner and closed by
		final List<User> recipients = new ArrayList<User>();
		recipients.add(0, onwer);				
		recipients.add(1, loggedInUser);	
		
		final List<User> notifiers = new ArrayList<User>();
		if(RTSUtils.isNotEmpty(selectedRecipients)){
			
			for(final String notifier : selectedRecipients){
				if(notifier.equals(String.valueOf(2))){
					//HR
					final List<User> users = userList.getUserHasRoles(6);
					if(RTSUtils.isNotEmpty(users)){
						notifiers.addAll(users);
					}
					
				} 
				
				if(notifier.equals(String.valueOf(4))){
					//Send mail to handled by person
					if(StringUtils.isNotEmpty(this.getInstance().getHandledBy())){
						final User handledBy = userHome.findUserByUsername(this.getInstance().getHandledBy());
						if(handledBy != null){
							notifiers.add(handledBy);
						}						
					}
				}
			}		
		}
		
		recipients.addAll(notifiers);	
		
		//remove duplicated recipients
		final Set<Integer> userIds = new HashSet<Integer>();
		for (Iterator<User> it = recipients.listIterator(); it.hasNext();) {
			User user = it.next();
			if (userIds.add(user.getUsrUserId()) == false) {
				it.remove();
			}
		}
		
		Events.instance().raiseEvent("sendMailAfterRecruitRequestCreated", onwer, this.getInstance(), recipients, RTSConstants.CLOSED);	
		
		return result;
	}
	
	
	public String assignRecruitRequest(){
		
		statusMessages.clear();
		
		String result = RTSConstants.FAILURE;
		final Date handledByDate = new Date(); 
		
		this.getInstance().setUpdatedDate(handledByDate);
		this.getInstance().setUpdatedBy(loggedInUser.getUsrUserName());
		this.getInstance().setUser(loggedInUser);
		
		try {
			result = super.update();	
		} catch (Exception e) {
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.recruit.request.assign");	
			log.error("Error in assigning Recruit Request: " + e.getMessage());
		}
		
		final String onwnerBy = StringUtils.isNotEmpty(this.getInstance().getUpdatedBy()) 
				? this.getInstance().getUpdatedBy() 
				: this.getInstance().getCreatedBy();
		
		User onwer = null;	
		try {
			onwer = userHome.findUserByUsername(onwnerBy);
		} catch (Exception e) {
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.recruit.request.approval.username");	
			log.error("Error in finding user by username: " + e.getMessage());
			result = RTSConstants.FAILURE;
		}		
		
		//send notification mails to owner and closed by
		final List<User> recipients = new ArrayList<User>();
		recipients.add(0, onwer);				
		recipients.add(1, loggedInUser);	
		
		final List<User> notifiers = new ArrayList<User>();
		if(RTSUtils.isNotEmpty(selectedRecipients)){
			
			for(final String notifier : selectedRecipients){
				if(notifier.equals(String.valueOf(2))){
					//HR
					final List<User> users = userList.getUserHasRoles(6);
					if(RTSUtils.isNotEmpty(users)){
						notifiers.addAll(users);
					}
					
				} 
				
				if(notifier.equals(String.valueOf(4))){
					//Send mail to handled by person
					if(StringUtils.isNotEmpty(this.getInstance().getHandledBy())){
						final User handledBy = userHome.findUserByUsername(this.getInstance().getHandledBy());
						if(handledBy != null){
							notifiers.add(handledBy);
						}						
					}
				}
			}			
		}
		
		recipients.addAll(notifiers);
		
		//remove duplicated recipients
		final Set<Integer> userIds = new HashSet<Integer>();
		for (Iterator<User> it = recipients.listIterator(); it.hasNext();) {
			User user = it.next();
			if (userIds.add(user.getUsrUserId()) == false) {
				it.remove();
			}
		}
		
		Events.instance().raiseEvent("sendMailAfterRecruitRequestCreated", onwer, this.getInstance(), recipients, RTSConstants.ASSIGNED);	
		
		return result;
	}

	private List<RecruitRequest> rejectedRecruitRequests = new ArrayList<RecruitRequest>();
	private List<RecruitRequest> approvedRecruitRequests = new ArrayList<RecruitRequest>();
	
	public void onloadRejectedRecruitRequests(){
		rejectedRecruitRequests.clear();
		try {	
			rejectedRecruitRequests = recruitRequestList.searchRecruitRequest(
					null, 
					null, 
					"Rejected", 
					null, 
					null, 
					loggedInUser);				
		} catch (Exception e) {					
			log.error("Error in searching approved recruit requests. " + e);
		}
	}
	
	public void onloadApprovedRecruitRequests(){
		approvedRecruitRequests.clear();
		try {	
			approvedRecruitRequests = recruitRequestList.searchRecruitRequest(
					null, 
					null, 
					"Approved", 
					null, 
					null, 
					loggedInUser);				
		} catch (Exception e) {					
			log.error("Error in searching approved recruit requests. " + e);
		}
	}

	public void setRejectedRecruitRequests(List<RecruitRequest> rejectedRecruitRequests) {
		this.rejectedRecruitRequests = rejectedRecruitRequests;
	}

	public List<RecruitRequest> getRejectedRecruitRequests() {
		return rejectedRecruitRequests;
	}

	public void setApprovedRecruitRequests(List<RecruitRequest> approvedRecruitRequests) {
		this.approvedRecruitRequests = approvedRecruitRequests;
	}

	public List<RecruitRequest> getApprovedRecruitRequests() {
		return approvedRecruitRequests;
	}
	
}