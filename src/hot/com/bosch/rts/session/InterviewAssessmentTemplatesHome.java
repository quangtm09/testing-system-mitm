package com.bosch.rts.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;

import com.bosch.rts.entity.InterviewAssessmentTemplates;
import com.bosch.rts.entity.TechnicalResultGroup;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSUtils;

/**
 * CRUD for interview assessment templates.
 *
 * @author KHB1HC
 */
@Name("interviewAssessmentTemplatesHome")
public class InterviewAssessmentTemplatesHome extends EntityHome<InterviewAssessmentTemplates> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1732073545759546097L;

	/**
	 * Sets the trts interview assessment templates id.
	 *
	 * @param id the new trts interview assessment templates id
	 */
	public void setTrtsInterviewAssessmentTemplatesId(Integer id) {
		setId(id);
	}

	/**
	 * Gets the trts interview assessment templates id.
	 *
	 * @return the trts interview assessment templates id
	 */
	public Integer getTrtsInterviewAssessmentTemplatesId() {
		return (Integer) getId();
	}

	/** The log. */
	@Logger
	private transient Log log;

	/** The status messages. */
	@In(create = true)
	private transient StatusMessages statusMessages;
	
	/** The credentials. */
	@In(create = true)
	private transient Credentials credentials;
	
	/** The interview assessment templates list. */
	@In(create = true)
	private transient InterviewAssessmentTemplatesList interviewAssessmentTemplatesList;	
	
	/** The technical result group list. */
	@In(create = true)
	private transient TechnicalResultGroupList technicalResultGroupList;
	
	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#createInstance()
	 */
	@Override
	protected InterviewAssessmentTemplates createInstance() {
		InterviewAssessmentTemplates interviewAssessmentTemplates = new InterviewAssessmentTemplates();
		return interviewAssessmentTemplates;
	}
	
	/**
	 * New instance.
	 *
	 * @return the interview assessment templates
	 */
	public InterviewAssessmentTemplates newInstance() {
		InterviewAssessmentTemplates interviewAssessmentTemplates = new InterviewAssessmentTemplates();
		this.setInstance(interviewAssessmentTemplates);
		return interviewAssessmentTemplates;
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
	public InterviewAssessmentTemplates getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}
	
	/**
	 * If page is error in form validating or system fails to CRUD entity,
	 * the page will keep the current instance with entered data. Otherwise, a new instance will be created.
	 */
	@Out(required = false, scope = ScopeType.EVENT)
	public boolean isError = false;

	/**
	 * Checks if is error.
	 *
	 * @return true, if is error
	 */
	public boolean isError() {
		return this.isError;
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
	 * Create new template.
	 *
	 * @return persisted if successfully, otherwise failure
	 */
	public String createTemplate(){
		String result = RTSConstants.FAILURE; 
		isError = false;
		final Date createdOn = new Date();	
		this.getInstance().setCreatedOn(createdOn);
		
		final String createdBy = credentials.getUsername();
		this.getInstance().setCreatedBy(createdBy);
		
		try {
			statusMessages.clear();
			result =  this.persist();
		} catch (Exception e) {
			log.error("Error in creating template: " + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.template.create");
			setError(true);
		}	
		
		if(RTSConstants.PERSISTED.equals(result)){
			this.resetInstance(Severity.INFO, "success.template.create");	
		} else {
			setError(true);
		}
		
		return result;
	}
	
	/**
	 * Update template properties and its groups.
	 *
	 * @return updated if successfully, otherwise failure
	 */
	public String updateTemplate(){
		String result = RTSConstants.FAILURE; 
		final Date updatedOn = new Date();	
		this.getInstance().setUpdatedOn(updatedOn);
		
		final String updatedBy = credentials.getUsername();
		this.getInstance().setUpdatedBy(updatedBy);		
		
		try {
			
			final List<TechnicalResultGroup> oldGroups = this.getInstance().getGroupsList();
			if(RTSUtils.isNotEmpty(oldGroups)){
				//some groups selected
				if(RTSUtils.isNotEmpty(this.selectedGroups)){
					oldGroups.removeAll(this.selectedGroups);
					if(RTSUtils.isNotEmpty(oldGroups)){
						for(TechnicalResultGroup oldGroup : oldGroups){
							this.getInstance().getTechnicalResultGroups().remove(oldGroup);
							oldGroup.setInterviewAssessmentTemplates(null);
							getEntityManager().persist(oldGroup);
						}
					}
					
					for(TechnicalResultGroup group : this.selectedGroups){
						group.setInterviewAssessmentTemplates(this.getInstance());
						getEntityManager().persist(group);
					}					
				} else {
					for(TechnicalResultGroup oldGroup : oldGroups){
						this.getInstance().getTechnicalResultGroups().remove(oldGroup);
						oldGroup.setInterviewAssessmentTemplates(null);
						getEntityManager().persist(oldGroup);
					}					
				}
			} else {
				if(RTSUtils.isNotEmpty(this.selectedGroups)){
					for(TechnicalResultGroup group : this.selectedGroups){
						group.setInterviewAssessmentTemplates(this.getInstance());
						getEntityManager().persist(group);
					}
				}
			}
			
		} catch (Exception e) {
			log.error("Error in updating template: " + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.template.update");			
		}	
		
		try {		
			statusMessages.clear();
			result =  this.update();
		} catch (Exception e) {
			log.error("Error in updating template: " + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.template.update");
		}
		
		if(RTSConstants.UPDATED.equals(result)){
			this.resetInstance(Severity.INFO, "success.template.update");			
		} else {
			setError(true);
		}
				
		return result;
	}
	
	/**
	 * Remove template and remove template for groups.
	 * Reset instance before redirecting.
	 * @return removed if successfully, otherwise failure.
	 */
	public String removeTemplate(){
		String result = RTSConstants.FAILURE;		
		isError = false;	
		//update template for groups
		if(RTSUtils.isNotEmpty(this.getInstance().getTechnicalResultGroups())){
			for(TechnicalResultGroup group : this.getInstance().getTechnicalResultGroups()){
				this.getInstance().getTechnicalResultGroups().remove(group);
				group.setInterviewAssessmentTemplates(null);
				try {		
					statusMessages.clear();
					getEntityManager().persist(group);
				} catch (Exception e) {
					log.error("Error in removing template for groups: " + e);
					statusMessages.clear();
					statusMessages.addFromResourceBundle(Severity.ERROR, "error.template.group.delete");
					isError = true;
				}				
			}
		}
		
		try {		
			statusMessages.clear();
			result =  this.remove();
		} catch (Exception e) {
			log.error("Error in removing template: " + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.template.delete");
			isError = true;
		}		
		
		if(RTSConstants.REMOVED.equals(result)){
			this.resetInstance(Severity.INFO, "success.template.delete");
		} else {
			setError(true);
		}
		
		return result;
	}
	
	/**
	 * Initialize groups for data-table display.
	 */
	public void onloadAssessmentTemplates(){
		RTSUtils.resetList(assessmentTemplates);
		assessmentTemplates = interviewAssessmentTemplatesList.getResultList();
	}

	/** The assessment templates. */
	private List<InterviewAssessmentTemplates> assessmentTemplates = null;

	/**
	 * Gets the assessment templates.
	 *
	 * @return the assessment templates
	 */
	public List<InterviewAssessmentTemplates> getAssessmentTemplates() {
		return this.assessmentTemplates;
	}

	/**
	 * Sets the assessment templates.
	 *
	 * @param assessmentTemplates the new assessment templates
	 */
	public void setAssessmentTemplates(
			List<InterviewAssessmentTemplates> assessmentTemplates) {
		this.assessmentTemplates = assessmentTemplates;
	}
	
	/** The from view. */
	private String fromView = null;

	/**
	 * Gets the from view.
	 *
	 * @return the from view
	 */
	public String getFromView() {
		return this.fromView;
	}

	/**
	 * Sets the from view.
	 *
	 * @param fromView the new from view
	 */
	public void setFromView(String fromView) {
		this.fromView = fromView;
	}
	
	/** The groups. */
	private List<TechnicalResultGroup> groups = null;

	/**
	 * Gets the groups.
	 *
	 * @return the groups
	 */
	public List<TechnicalResultGroup> getGroups() {
		return this.groups;
	}

	/**
	 * Sets the groups.
	 *
	 * @param groups the new groups
	 */
	public void setGroups(List<TechnicalResultGroup> groups) {
		this.groups = groups;
	}
	
	/**
	 * Initialize groups for pick-list elements selection.
	 */
	public void onloadGroups(){
		log.info("Method onloadGroups() called.");
		RTSUtils.resetList(groups);
		groups = technicalResultGroupList.getNotUsedGroups();
		if(groups == null){
			groups = new ArrayList<TechnicalResultGroup>();
		}
		
		groups.addAll(this.getInstance().getTechnicalResultGroups());
		
		log.info("Template groups size: " + groups.size());
		log.info("End of method onloadGroups().");	
	}
	
	/** The selected groups. */
	private List<TechnicalResultGroup> selectedGroups = null;

	/**
	 * Gets the selected groups.
	 *
	 * @return the selected groups
	 */
	public List<TechnicalResultGroup> getSelectedGroups() {
		return this.selectedGroups;
	}

	/**
	 * Sets the selected groups.
	 *
	 * @param selectedGroups the new selected groups
	 */
	public void setSelectedGroups(List<TechnicalResultGroup> selectedGroups) {
		this.selectedGroups = selectedGroups;
	}
	
	/**
	 * Initialize groups for selected pick-list elements display.
	 */
	public void onloadSelectedGroups(){
		log.info("Method onloadSelectedGroups() called.");
		RTSUtils.resetList(selectedGroups);
		selectedGroups = new ArrayList<TechnicalResultGroup>(this.getInstance().getTechnicalResultGroups());
		if(RTSUtils.isBlank(selectedGroups)){
			selectedGroups = new ArrayList<TechnicalResultGroup>();
		}		
		
		log.info("selectedGroups size: " + selectedGroups.size());
		log.info("End of method onloadSelectedGroups().");
	}
	
	/**
	 * Reset current instance before redirecting to target page.
	 *
	 * @param severity StatusMessages severity
	 * @param message String message
	 */
	private void resetInstance(final Severity severity, final String message){
		statusMessages.clear();
		statusMessages.addFromResourceBundle(severity, message);
		if(this.getInstance() != null){
			this.clearInstance();
			this.setId(null);
		}
	}
}
