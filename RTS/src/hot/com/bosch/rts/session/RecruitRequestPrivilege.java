/**
 * RecruitRequestPrivilege.java
 */
package com.bosch.rts.session;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.security.Credentials;

import com.bosch.rts.blo.UserBLO;
import com.bosch.rts.utilities.RTSRecruitRequestRoles;

/**
 * Recruit Request Module Privilege.
 *
 * @author khb1hc
 */
@Name(value="rrPrivilege")
@Scope(ScopeType.CONVERSATION)
public class RecruitRequestPrivilege implements Serializable{
	
	/** serialVersionUID. */
	private static final long serialVersionUID = -6832270469178681979L;

	/** The user blo. */
	@In(create = true)
	private transient UserBLO userBLO;
	
	/** The credentials. */
	@In(create = true)
	private transient Credentials credentials;

	/**
	 * Instantiates a new recruit request privilege.
	 */
	public RecruitRequestPrivilege() {
	}
	
	/** The is viewable recruit request. */
	private boolean isViewableRecruitRequest = false;
	
	/** The is addable recruit request. */
	private boolean isAddableRecruitRequest = false;
	
	/** The is editable recruit request. */
	private boolean isEditableRecruitRequest = false;	
	
	/** The is searchable recruit request. */
	private boolean isSearchableRecruitRequest = false;		
	
	/**
	 * Checks if is searchable recruit request.
	 *
	 * @return true, if is searchable recruit request
	 */
	public boolean isSearchableRecruitRequest() {
		if(!isSearchableRecruitRequest){
			isSearchableRecruitRequest = userBLO.userHasPrivilege(credentials.getUsername(),RTSRecruitRequestRoles.SEARCH_RECRUIT_REQUEST);
		}
		return isSearchableRecruitRequest;
	}

	/**
	 * Sets the searchable recruit request.
	 *
	 * @param isSearchableRecruitRequest the new searchable recruit request
	 */
	public void setSearchableRecruitRequest(boolean isSearchableRecruitRequest) {
		this.isSearchableRecruitRequest = isSearchableRecruitRequest;
	}

	/**
	 * Checks if is addable recruit request.
	 *
	 * @return true, if is addable recruit request
	 */
	public boolean isAddableRecruitRequest() {
		if(!isAddableRecruitRequest){
			isAddableRecruitRequest = userBLO.userHasPrivilege(credentials.getUsername(),RTSRecruitRequestRoles.ADD_RECRUIT_REQUEST);
		}
		return isAddableRecruitRequest;
	}

	/**
	 * Sets the addable recruit request.
	 *
	 * @param isAddableRecruitRequest the new addable recruit request
	 */
	public void setAddableRecruitRequest(boolean isAddableRecruitRequest) {
		this.isAddableRecruitRequest = isAddableRecruitRequest;
	}

	/**
	 * Checks if is editable recruit request.
	 *
	 * @return true, if is editable recruit request
	 */
	public boolean isEditableRecruitRequest() {
		if(!isEditableRecruitRequest){
			isEditableRecruitRequest = userBLO.userHasPrivilege(credentials.getUsername(),RTSRecruitRequestRoles.EDIT_RECRUIT_REQUEST);
		}
		return isEditableRecruitRequest;
	}

	/**
	 * Sets the editable recruit request.
	 *
	 * @param isEditableRecruitRequest the new editable recruit request
	 */
	public void setEditableRecruitRequest(boolean isEditableRecruitRequest) {
		this.isEditableRecruitRequest = isEditableRecruitRequest;
	}

	/**
	 * Checks if is viewable recruit request.
	 *
	 * @return true, if is viewable recruit request
	 */
	public boolean isViewableRecruitRequest() {
		if(!isViewableRecruitRequest){
			isViewableRecruitRequest = userBLO.userHasPrivilege(credentials.getUsername(),RTSRecruitRequestRoles.VIEW_RECRUIT_REQUEST);
		}		
		return isViewableRecruitRequest;
	}
 
	/**
	 * Sets the viewable recruit request.
	 *
	 * @param isViewableRecruitRequest the new viewable recruit request
	 */
	public void setViewableRecruitRequest(boolean isViewableRecruitRequest) {		
		this.isViewableRecruitRequest = isViewableRecruitRequest;
	}
}
