/*
 * 
 */
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

import com.bosch.rts.entity.CandidateHasSkill;
import com.bosch.rts.entity.RecruitRequestHasSkill;
import com.bosch.rts.entity.Skill;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSUtils;

/**
 * The Class SkillHome.
 */
@Name("skillHome")
public class SkillHome extends EntityHome<Skill> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5551349691802058737L;
	
	/** The log. */
	@Logger
	private transient Log log;

	/** The status messages. */
	@In(create = true)
	private transient StatusMessages statusMessages;	
	
	/** The credentials. */
	@In(create = true)
	private transient Credentials credentials;
	
	/** The skill list. */
	@In(create = true)
	private transient SkillList skillList;

	/**
	 * Sets the skill skl skill id.
	 *
	 * @param id the new skill skl skill id
	 */
	public void setSkillSklSkillId(Integer id) {
		setId(id);
	}

	/**
	 * Gets the skill skl skill id.
	 *
	 * @return the skill skl skill id
	 */
	public Integer getSkillSklSkillId() {
		return (Integer) getId();
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#createInstance()
	 */
	@Override
	protected Skill createInstance() {
		Skill skill = new Skill();
		return skill;
	}
		
	/**
	 * New instance.
	 *
	 * @return the skill
	 */
	public Skill newInstance() {
		Skill skill = new Skill();
		this.setInstance(skill);
		return skill;
	}
	
	/**
	 * If page is error in form validating or system fails to CRUD entity,
	 * the page will keep the current instance with entered data. Otherwise, a new instance will be created.
	 */
	@Out(required = false, scope = ScopeType.EVENT)
	private boolean isError = false;

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
	 * Load.
	 */
	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}
	
	/** The from view. */
	private String fromView = null;

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
	public Skill getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	/**
	 * Gets the candidate has skills.
	 *
	 * @return the candidate has skills
	 */
	public List<CandidateHasSkill> getCandidateHasSkills() {
		return getInstance() == null ? null : new ArrayList<CandidateHasSkill>(
				getInstance().getCandidateHasSkills());
	}

	/**
	 * Gets the recruit request has skills.
	 *
	 * @return the recruit request has skills
	 */
	public List<RecruitRequestHasSkill> getRecruitRequestHasSkills() {
		return getInstance() == null ? null
				: new ArrayList<RecruitRequestHasSkill>(getInstance()
						.getRecruitRequestHasSkills());
	}
	
	/**
	 * Creates the skill.
	 *
	 * @return the string
	 */
	public String createSkill(){
		String result = RTSConstants.FAILURE; 
		final Date createdOn = new Date();	
		this.getInstance().setCreatedOn(createdOn);
		
		final String createdBy = credentials.getUsername();
		this.getInstance().setCreatedBy(createdBy);		
		
		try {
			statusMessages.clear();
			result =  this.persist();
			if(RTSConstants.PERSISTED.equals(result)){
				statusMessages.clear();
				statusMessages.addFromResourceBundle(Severity.INFO, "success.attribute.create");
				
				if(this.getInstance() != null){
					this.clearInstance();
					this.setId(null);
				}				
			}
		} catch (Exception e) {			
			log.error("Error in creating skill: " + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.attribute.create");
		}
		
		return result;
	}
	
	/**
	 * Update skill.
	 *
	 * @return the string
	 */
	public String updateSkill(){
		String result = RTSConstants.FAILURE; 
		final Date updatedOn = new Date();	
		this.getInstance().setUpdatedOn(updatedOn);
		
		final String updatedBy = credentials.getUsername();
		this.getInstance().setUpdatedBy(updatedBy);		
		
		try {
			statusMessages.clear();
			result =  this.update();
			if(RTSConstants.UPDATED.equals(result)){
				statusMessages.clear();
				statusMessages.addFromResourceBundle(Severity.INFO, "success.attribute.update");
				if(this.getInstance() != null){
					this.clearInstance();
					this.setId(null);
				}
			}
		} catch (Exception e) {
			log.error("Error in updating attribute: " + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.attribute.update");		
		}		
		
		return result;
	}
	
	/**
	 * Removes the skill.
	 *
	 * @return the string
	 */
	public String removeSkill(){
		String result = RTSConstants.FAILURE; 
		
		final Date updatedOn = new Date();	
		this.getInstance().setUpdatedOn(updatedOn);
		
		final String updatedBy = credentials.getUsername();
		this.getInstance().setUpdatedBy(updatedBy);		
		
		try {
			statusMessages.clear();
			result =  this.remove();
			if(RTSConstants.REMOVED.equals(result)){
				statusMessages.clear();
				statusMessages.addFromResourceBundle(Severity.INFO, "success.attribute.update");
				if(this.getInstance() != null){
					this.clearInstance();
					this.setId(null);
				}
			}
		} catch (Exception e) {
			log.error("Error in removing skill: " + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.attribute.update");		
		}		
		
		return result;
	}
	
	/**
	 * Sets the skills.
	 *
	 * @param skills the new skills
	 */
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	/**
	 * Gets the skills.
	 *
	 * @return the skills
	 */
	public List<Skill> getSkills() {
		return skills;
	}

	/** The skills. */
	private List<Skill> skills = null;
	
	/**
	 * Onload skills.
	 */
	public void onloadSkills(){
		RTSUtils.resetList(skills);
		skills = skillList.loadAllSkills();	
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
	
	public String loadConstraints(){
		String result = RTSConstants.FAILURE;
		result= RTSConstants.SUCCESS;
		
		return result;
		
	}
	
}
