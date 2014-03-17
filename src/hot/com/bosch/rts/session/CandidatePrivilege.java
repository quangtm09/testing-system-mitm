/**
 * CandidatePrivilege.java
 */
package com.bosch.rts.session;

import java.io.Serializable;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.security.Credentials;

import com.bosch.rts.blo.UserBLO;
import com.bosch.rts.utilities.RTSCandidateRoles;

/**
 * Candidate Module Privilege.
 *
 * @author khb1hc
 */
@Name(value = "caPrivilege")
@Scope(ScopeType.CONVERSATION)
public class CandidatePrivilege implements Serializable{

	/** serialVersionUID. */
	private static final long serialVersionUID = 6507714632347704365L;

	/**
	 * Instantiates a new candidate privilege.
	 */
	public CandidatePrivilege() {}

	/** The user blo. */
	@In(create = true)
	private transient UserBLO userBLO;
	
	/** The credentials. */
	@In(create = true)
	private transient Credentials credentials;
	
	/** The is viewable candidate. */
	private boolean isViewableCandidate = false;
	
	/** The is addable candidate. */
	private boolean isAddableCandidate = false;
	
	/** The is editable candidate. */
	private boolean isEditableCandidate = false;	
	
	/** The is searchable candidate. */
	private boolean isSearchableCandidate = false; 
	
	/** The is transferable candidate. */
	private boolean isTransferableCandidate = false;	 
	
	/** The is changeable candidate status. */
	private boolean isChangeableCandidateStatus = false;	
	
	/**
	 * Checks if is changeable candidate status.
	 *
	 * @return true, if is changeable candidate status
	 */
	public boolean isChangeableCandidateStatus() {
		if(!isChangeableCandidateStatus){		
			isChangeableCandidateStatus = userBLO.userHasPrivilege(credentials.getUsername(),RTSCandidateRoles.CHANGE_CANDIDATE_STATUS);						
		}
		
		return isChangeableCandidateStatus;
	}
	
	/**
	 * Sets the changeable candidate status.
	 *
	 * @param isChangeableCandidateStatus the new changeable candidate status
	 */
	public void setChangeableCandidateStatus(boolean isChangeableCandidateStatus) {
		this.isChangeableCandidateStatus = isChangeableCandidateStatus;
	}
	
	/**
	 * Checks if is transferable candidate.
	 *
	 * @return true, if is transferable candidate
	 */
	public boolean isTransferableCandidate() {
		if(!isTransferableCandidate){
			isTransferableCandidate = userBLO.userHasPrivilege(credentials.getUsername(),RTSCandidateRoles.TRANSFER_CANDIDATE);			
		}
		
		return isTransferableCandidate;
	}
	
	/**
	 * Sets the transferable candidate.
	 *
	 * @param isTransferableCandidate the new transferable candidate
	 */
	public void setTransferableCandidate(boolean isTransferableCandidate) {
		this.isTransferableCandidate = isTransferableCandidate;
	}
	
	/**
	 * Checks if is viewable candidate.
	 *
	 * @return true, if is viewable candidate
	 */
	public boolean isViewableCandidate() {
		if(!isViewableCandidate){
			isViewableCandidate = userBLO.userHasPrivilege(credentials.getUsername(),RTSCandidateRoles.VIEW_CANDIDATE);						
		}
		
		return isViewableCandidate;
	}
	
	/**
	 * Sets the viewable candidate.
	 *
	 * @param isViewableCandidate the new viewable candidate
	 */
	public void setViewableCandidate(boolean isViewableCandidate) {
		this.isViewableCandidate = isViewableCandidate;
	}
	
	/**
	 * Checks if is addable candidate.
	 *
	 * @return true, if is addable candidate
	 */
	public boolean isAddableCandidate() {
		if(!isAddableCandidate){
			isAddableCandidate = userBLO.userHasPrivilege(credentials.getUsername(),RTSCandidateRoles.ADD_CANDIDATE);
		}
		return isAddableCandidate;
	}
	
	/**
	 * Sets the addable candidate.
	 *
	 * @param isAddableCandidate the new addable candidate
	 */
	public void setAddableCandidate(boolean isAddableCandidate) {
		this.isAddableCandidate = isAddableCandidate;
	}
	
	/**
	 * Checks if is editable candidate.
	 *
	 * @return true, if is editable candidate
	 */
	public boolean isEditableCandidate() {		
		if(!isEditableCandidate){
			isEditableCandidate = userBLO.userHasPrivilege(credentials.getUsername(),RTSCandidateRoles.EDIT_CANDIDATE);				
		}
		
		return isEditableCandidate;
	}
	
	/**
	 * Sets the editable candidate.
	 *
	 * @param isEditableCandidate the new editable candidate
	 */
	public void setEditableCandidate(boolean isEditableCandidate) {
		this.isEditableCandidate = isEditableCandidate;
	}
	
	/**
	 * Checks if is searchable candidate.
	 *
	 * @return true, if is searchable candidate
	 */
	public boolean isSearchableCandidate() {
		if(!isSearchableCandidate){			
			isSearchableCandidate = userBLO.userHasPrivilege(credentials.getUsername(),RTSCandidateRoles.SEARCH_CANDIDATE);						
		}
		
		return isSearchableCandidate;
	}
	
	/**
	 * Sets the searchable candidate.
	 *
	 * @param isSearchableCandidate the new searchable candidate
	 */
	public void setSearchableCandidate(boolean isSearchableCandidate) {
		this.isSearchableCandidate = isSearchableCandidate;
	}		
	
	public void setViewableReport(boolean isViewableReport) {
		this.isViewableReport = isViewableReport;
	}

	public boolean isViewableReport() {
		if(!isViewableReport){			
			isViewableReport = userBLO.userHasPrivilege(credentials.getUsername(), RTSCandidateRoles.VIEW_REPORT);						
		}
		return isViewableReport;
	}

	/** The is addable candidate. */
	private boolean isViewableReport = false;
}