/*
 * /rts/src/hot/com/bosch/rts/session/RecruitRequestReportList.java
 */
package com.bosch.rts.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.bosch.rts.entity.OrgUnit;
import com.bosch.rts.entity.RecruitRequest;
import com.bosch.rts.entity.User;
import com.bosch.rts.utilities.RTSReportQueries;

/**
 * Recruit Request Report List for Recruit Request report module.
 *
 * @author khb1hc
 */
@Name("recruitRequestReportList")
public class RecruitRequestReportList {

	/** serialVersionUID. */
	private static final long serialVersionUID = -7776384819770855260L;

	/** The entity manager. */
	@In(create = true)
	private EntityManager entityManager;
	
	/**
	 * Gets the recruit requests.
	 *
	 * @param createdFrom the created from
	 * @param createdTo the created to
	 * @param requestedFrom the requested from
	 * @param requestedTo the requested to
	 * @param closedFrom the closed from
	 * @param closedTo the closed to
	 * @param createdBy the created by
	 * @param approvedBy the approved by
	 * @param handledBy the handled by
	 * @param orgUnit the org unit
	 * @param recruitRequestName the recruit request name
	 * @param status the status
	 * @return the recruit requests
	 */
	@SuppressWarnings("unchecked")
	public List<RecruitRequest> getRecruitRequests(
			final Date createdFrom, 
			final Date createdTo, 
			final Date requestedFrom, 
			final Date requestedTo, 
			final Date closedFrom, 
			final Date closedTo, 
			final String createdBy,
			final User approvedBy,
			final String handledBy,	
			final OrgUnit orgUnit,					
			final String recruitRequestName, 
			final String status){
		
		List<RecruitRequest> recruitRequests = new ArrayList<RecruitRequest>();
		final String sql = RTSReportQueries.getRecruitRequest(
				createdFrom,
				createdTo, 
				requestedFrom, 
				requestedTo, 
				closedFrom, 
				closedTo, 
				createdBy,
				approvedBy,
				handledBy,	
				orgUnit,					
				recruitRequestName, 
				status);
		
		final Query query = entityManager.createQuery(sql);
		if(createdFrom != null){
			query.setParameter("createdFrom", createdFrom);	
		}	
		if(createdTo != null){
			query.setParameter("createdTo", createdTo);
		}
		if(requestedFrom != null){
			query.setParameter("requestedFrom", requestedFrom);	
		}	
		if(requestedTo != null){
			query.setParameter("requestedTo", requestedTo);	
		}	
		if(closedFrom != null){
			query.setParameter("closedFrom", closedFrom);
		}	
		if(closedTo != null){
			query.setParameter("closedTo", closedTo);	
		}			
		if(createdBy != null){
			query.setParameter("createdBy", createdBy);	
		}
		if(approvedBy != null){
			query.setParameter("approvedBy", approvedBy);
		}
		if(handledBy != null){
			query.setParameter("handledBy", handledBy);
		}		
		if(orgUnit != null){
			query.setParameter("orgUnit", orgUnit);
		}	
		if(StringUtils.isNotEmpty(recruitRequestName)){
			query.setParameter("recruitRequestName", recruitRequestName);
		}
		if(StringUtils.isNotEmpty(status)){
			query.setParameter("status", status);
		}		
		
		recruitRequests = query.getResultList();	
		
		return recruitRequests;
		
	}
	
	/**
	 * Gets the recruit requests.
	 *
	 * @param selectedOrgUnits the selected org units
	 * @return the recruit requests
	 */
	@SuppressWarnings("unchecked")
	public List<RecruitRequest> getRecruitRequests(final String selectedOrgUnits){
		List<RecruitRequest> recruitRequests = new ArrayList<RecruitRequest>();
		final String sql = RTSReportQueries.getRecruitRequest(selectedOrgUnits);
		final Query query = entityManager.createQuery(sql);
		recruitRequests = query.getResultList();		
		return recruitRequests;
		
	}
	
}