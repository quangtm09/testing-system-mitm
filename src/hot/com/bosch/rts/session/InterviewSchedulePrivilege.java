/**
 * InterviewSchedulePrivilege.java
 */
package com.bosch.rts.session;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import com.bosch.rts.blo.UserBLO;
import com.bosch.rts.utilities.RTSInterviewRoles;
import com.bosch.rts.utilities.RTSRoles;

/**
 * Interview Schedule Module Privilege.
 *
 * @author khb1hc
 */
@Name(value = "schPrivilege")
@Scope(ScopeType.CONVERSATION)
public class InterviewSchedulePrivilege implements Serializable{

	/** serialVersionUID. */
	private static final long serialVersionUID = -491561957049468468L;

	/**
	 * Instantiates a new interview schedule privilege.
	 */
	public InterviewSchedulePrivilege() {}
	
	/** The user blo. */
	@In(create = true)
	private transient UserBLO userBLO;
	
	/** The credentials. */
	@In(create = true)
	private transient Credentials credentials;

	/** The identity. */
	@In(create=true)
	private transient Identity identity;
	
	/** The is searchable interview schedule. */
	private boolean isSearchableInterviewSchedule = false;
	
	/** The is editable interview schedule. */
	private boolean isEditableInterviewSchedule = false;	
	
	/** The is viewable technical feedback. */
	private boolean isViewableTechnicalFeedback = false;	
	
	/** The is addable interview schedule. */
	private boolean isAddableInterviewSchedule = false;	

	/**
	 * Checks if is addable interview schedule.
	 *
	 * @return true, if is addable interview schedule
	 */
	public boolean isAddableInterviewSchedule() {
		if(!isAddableInterviewSchedule){
			this.setAddableInterviewSchedule(userBLO.userHasPrivilege(credentials.getUsername(), RTSInterviewRoles.ADD_INTERVIEW_SCHEDULE));
		}
		return isAddableInterviewSchedule;
	}

	/**
	 * Sets the addable interview schedule.
	 *
	 * @param isAddableInterviewSchedule the new addable interview schedule
	 */
	public void setAddableInterviewSchedule(boolean isAddableInterviewSchedule) {
		this.isAddableInterviewSchedule = isAddableInterviewSchedule;
	}
	
	/**
	 * Checks if is searchable interview schedule.
	 *
	 * @return true, if is searchable interview schedule
	 */
	public boolean isSearchableInterviewSchedule() {
		if(!isSearchableInterviewSchedule){
			this.setSearchableInterviewSchedule(userBLO.userHasPrivilege(credentials.getUsername(), RTSInterviewRoles.SEARCH_INTERVIEW_SCHEDULE));
		}
		return isSearchableInterviewSchedule;
	}

	/**
	 * Sets the searchable interview schedule.
	 *
	 * @param isSearchableInterviewSchedule the new searchable interview schedule
	 */
	public void setSearchableInterviewSchedule(boolean isSearchableInterviewSchedule) {
		this.isSearchableInterviewSchedule = isSearchableInterviewSchedule;
	}

	/**
	 * Checks if is editable interview schedule.
	 *
	 * @return true, if is editable interview schedule
	 */
	public boolean isEditableInterviewSchedule() {
		if(!isEditableInterviewSchedule){
			this.setEditableInterviewSchedule(userBLO.userHasPrivilege(credentials.getUsername(), RTSInterviewRoles.EDIT_INTERVIEW_SCHEDULE));
		}
		return isEditableInterviewSchedule;
	}

	/**
	 * Sets the editable interview schedule.
	 *
	 * @param isEditableInterviewSchedule the new editable interview schedule
	 */
	public void setEditableInterviewSchedule(boolean isEditableInterviewSchedule) {
		this.isEditableInterviewSchedule = isEditableInterviewSchedule;
	}
	
	/**
	 * Checks if is viewable technical feedback.
	 *
	 * @return true, if is viewable technical feedback
	 */
	public boolean isViewableTechnicalFeedback() {
		if(!isViewableTechnicalFeedback){
			this.setViewableTechnicalFeedback(userBLO.userHasPrivilege(credentials.getUsername(), RTSInterviewRoles.VIEW_TECHNICAL_FEEDBACK));
		}
		return isViewableTechnicalFeedback;
	}

	/**
	 * Sets the viewable technical feedback.
	 *
	 * @param isViewableTechnicalFeedback the new viewable technical feedback
	 */
	public void setViewableTechnicalFeedback(boolean isViewableTechnicalFeedback) {
		this.isViewableTechnicalFeedback = isViewableTechnicalFeedback;
	}

	/**
	 * Onload addable interview schedule.
	 *
	 * @param USERNAME the username
	 */
	public void onloadAddableInterviewSchedule(final String USERNAME){
		if(!isAddableInterviewSchedule){
			isAddableInterviewSchedule = userBLO.userHasPrivilege(USERNAME, RTSInterviewRoles.ADD_INTERVIEW_SCHEDULE);
		}		
	}
	
	/**
	 * Onload searchable interview schedule.
	 *
	 * @param USERNAME the username
	 */
	public void onloadSearchableInterviewSchedule(final String USERNAME){
		if(!isSearchableInterviewSchedule){
			isSearchableInterviewSchedule = userBLO.userHasPrivilege(USERNAME, RTSInterviewRoles.SEARCH_INTERVIEW_SCHEDULE);
		}		
	}	
	
	/**
	 * Onload editable interview schedule.
	 *
	 * @param USERNAME the username
	 */
	public void onloadEditableInterviewSchedule(final String USERNAME){
		if(!isEditableInterviewSchedule){
			isEditableInterviewSchedule = userBLO.userHasPrivilege(USERNAME, RTSInterviewRoles.EDIT_INTERVIEW_SCHEDULE);
		}		
	}
	
	/**
	 * Onload viewable technical feedback.
	 *
	 * @param USERNAME the username
	 */
	public void onloadViewableTechnicalFeedback(final String USERNAME){
		if(!isViewableTechnicalFeedback){			
			isViewableTechnicalFeedback = userBLO.userHasPrivilege(USERNAME, RTSInterviewRoles.VIEW_TECHNICAL_FEEDBACK);
		}		
	}		
	
	/**
	 * Checks if is authenticated user.
	 *
	 * @return true, if is authenticated user
	 */
	public boolean isAuthenticatedUser(){		
		if(identity.hasRole(RTSRoles.ROLE_GRM)){
			return true;			
		}
		if(identity.hasRole(RTSRoles.ROLE_DH)){
			return true;	
		}
		if(identity.hasRole(RTSRoles.ROLE_PM)){
			return true;	
		}
		if(identity.hasRole(RTSRoles.ROLE_HR)){
			return true;	
		}
		if(identity.hasRole(RTSRoles.ROLE_INTEVIEWER)){
			return true;	
		}
		if(identity.hasRole(RTSRoles.ROLE_GM)){
			return true;	
		}	
		
		return false;	
	}
	
}