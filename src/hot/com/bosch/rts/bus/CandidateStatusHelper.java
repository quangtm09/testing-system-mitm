/*
 * 
 */
package com.bosch.rts.bus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.bosch.rts.entity.CandidateStatus;

/**
 * This class is used to define which status can be changed by user to which
 * status.
 *
 * @author vut2hc
 * @author khb1hc
 */
@Name("candidateStatusHelper")
@Scope(ScopeType.SESSION)
public class CandidateStatusHelper implements Serializable {
	
	/** serialVersionUID. */
	private static final long serialVersionUID = -3209549419633190883L;

	/** The status map. */
	private Map<CandidateStatus, List<CandidateStatus>> statusMap = new HashMap<CandidateStatus, List<CandidateStatus>>(16);

	/**
	 * Instantiates a new candidate status helper.
	 */
	public CandidateStatusHelper() {
		
		CandidateStatus[] newNextStatuses = { 
				CandidateStatus.SCREENED,
				CandidateStatus.SHORT_LISTED,				
				CandidateStatus.TECHNICAL_SCHEDULED,
				CandidateStatus.TECHNICAL_ON_HOLD };
		
		CandidateStatus[] screenedNextStatuses = { 
				CandidateStatus.SHORT_LISTED,
				CandidateStatus.TECHNICAL_SCHEDULED,
				CandidateStatus.TECHNICAL_ON_HOLD };
		
		CandidateStatus[] shortListedNextStatuses = { 
				CandidateStatus.TECHNICAL_SCHEDULED,
				CandidateStatus.TECHNICAL_ON_HOLD };
		
		CandidateStatus[] techScheduledNextStatuses = { 
				CandidateStatus.TECHNICAL_ON_HOLD,
				CandidateStatus.TECHNICAL_PASS,
				CandidateStatus.TECHNICAL_FAIL};
		
		CandidateStatus[] techOnHoldNextStatuses = { 
				CandidateStatus.TECHNICAL_SCHEDULED,
				CandidateStatus.TECHNICAL_PASS,
				CandidateStatus.TECHNICAL_FAIL};
		
		CandidateStatus[] techPassNextStatuses = { 
				CandidateStatus.HR_SCHEDULED, 
				CandidateStatus.HR_ON_HOLD,
				CandidateStatus.TO_OFFER,
				CandidateStatus.OFFERED, 
				CandidateStatus.OFFER_ACCEPTED,
				CandidateStatus.OFFER_REFUSED, 
				CandidateStatus.SELECTED };
		
		CandidateStatus[] techFailNextStatuses = { 
				CandidateStatus.HR_SCHEDULED, 
				CandidateStatus.HR_ON_HOLD,
				CandidateStatus.TO_OFFER,
				CandidateStatus.OFFERED, 
				CandidateStatus.OFFER_ACCEPTED,
				CandidateStatus.OFFER_REFUSED, 
				CandidateStatus.SELECTED };	
		
		CandidateStatus[] hrScheduledNextStatuses = { 
				CandidateStatus.HR_ON_HOLD,
				CandidateStatus.TO_OFFER,
				CandidateStatus.OFFERED, 
				CandidateStatus.OFFER_ACCEPTED,
				CandidateStatus.OFFER_REFUSED, 
				CandidateStatus.SELECTED };
		
		CandidateStatus[] hrPassNextStatuses = { 
				CandidateStatus.HR_ON_HOLD,
				CandidateStatus.TO_OFFER,
				CandidateStatus.OFFERED, 
				CandidateStatus.OFFER_ACCEPTED,
				CandidateStatus.OFFER_REFUSED, 
				CandidateStatus.SELECTED };
		
		CandidateStatus[] hrFailNextStatuses = { 
				CandidateStatus.HR_ON_HOLD,
				CandidateStatus.TO_OFFER,
				CandidateStatus.OFFERED, 
				CandidateStatus.OFFER_ACCEPTED,
				CandidateStatus.OFFER_REFUSED, 
				CandidateStatus.SELECTED };
		
		CandidateStatus[] hrOnHoldNextStatuses = { 
				CandidateStatus.TO_OFFER,
				CandidateStatus.OFFERED, 
				CandidateStatus.OFFER_ACCEPTED,
				CandidateStatus.OFFER_REFUSED, 
				CandidateStatus.SELECTED };
		
		CandidateStatus[] toOfferNextStatuses = { 
				CandidateStatus.OFFERED, 
				CandidateStatus.OFFER_ACCEPTED,
				CandidateStatus.OFFER_REFUSED, 
				CandidateStatus.SELECTED };
		
		CandidateStatus[] offeredNextStatuses = { 
				CandidateStatus.HR_ON_HOLD,
				CandidateStatus.OFFER_ACCEPTED, 
				CandidateStatus.OFFER_REFUSED,
				CandidateStatus.SELECTED };
		
		CandidateStatus[] offerAcceptedNextStatuses = {
				CandidateStatus.OFFER_REFUSED, 
				CandidateStatus.HR_ON_HOLD,
				CandidateStatus.SELECTED };
		
		CandidateStatus[] offerRefusedNextStatuses = {
				CandidateStatus.OFFER_ACCEPTED, 
				CandidateStatus.OFFER_REFUSED,
				CandidateStatus.HR_ON_HOLD, 
				CandidateStatus.SELECTED };
		
		CandidateStatus[] selectedStatus = { 
				CandidateStatus.HR_ON_HOLD,
				CandidateStatus.JOINED};
		
		statusMap.put(CandidateStatus.NEW, Arrays.asList(newNextStatuses));
		statusMap.put(CandidateStatus.SCREENED, Arrays.asList(screenedNextStatuses));
		statusMap.put(CandidateStatus.SHORT_LISTED, Arrays.asList(shortListedNextStatuses));
		statusMap.put(CandidateStatus.TECHNICAL_SCHEDULED, Arrays.asList(techScheduledNextStatuses));
		statusMap.put(CandidateStatus.TECHNICAL_ON_HOLD, Arrays.asList(techOnHoldNextStatuses));
		statusMap.put(CandidateStatus.TECHNICAL_PASS, Arrays.asList(techPassNextStatuses));
		statusMap.put(CandidateStatus.TECHNICAL_FAIL, Arrays.asList(techFailNextStatuses));
		statusMap.put(CandidateStatus.HR_SCHEDULED, Arrays.asList(hrScheduledNextStatuses));
		statusMap.put(CandidateStatus.HR_ON_HOLD, Arrays.asList(hrOnHoldNextStatuses));
		statusMap.put(CandidateStatus.HR_PASS, Arrays.asList(hrPassNextStatuses));
		statusMap.put(CandidateStatus.HR_FAIL, Arrays.asList(hrFailNextStatuses));		
		statusMap.put(CandidateStatus.TO_OFFER, Arrays.asList(toOfferNextStatuses));
		statusMap.put(CandidateStatus.OFFERED, Arrays.asList(offeredNextStatuses));
		statusMap.put(CandidateStatus.OFFER_ACCEPTED, Arrays.asList(offerAcceptedNextStatuses));
		statusMap.put(CandidateStatus.OFFER_REFUSED, Arrays.asList(offerRefusedNextStatuses));
		statusMap.put(CandidateStatus.SELECTED, Arrays.asList(selectedStatus));		
	}

	/**
	 * Gets the next statuses.
	 *
	 * @param candidateStatus the candidate status
	 * @return the next statuses
	 */
	public List<CandidateStatus> getNextStatuses(CandidateStatus candidateStatus) {
		final List<CandidateStatus> statuses = statusMap.get(candidateStatus);
		if (statuses == null) {
			return new ArrayList<CandidateStatus>();
		}
		return statuses;
	}

	/**
	 * Checks if is can change to status.
	 *
	 * @param oldStatus the old status
	 * @param newStatus the new status
	 * @return true, if is can change to status
	 */
	public boolean isCanChangeToStatus(CandidateStatus oldStatus, CandidateStatus newStatus) {
		final List<CandidateStatus> oldNextStatuses = statusMap.get(oldStatus);
		for (final CandidateStatus cs : oldNextStatuses) {
			if (cs.equals(newStatus)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the status list.
	 *
	 * @return the status list
	 */
	public List<CandidateStatus> getStatusList() {
		return Arrays.asList(CandidateStatus.values());
	}
}
