/**
 * com.bosch.rts.utilities.RTSReportQueries.java
 */
package com.bosch.rts.utilities;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.bosch.rts.entity.OrgUnit;
import com.bosch.rts.entity.User;

/**
 * HQL statements for report.
 *
 * @author KHB1HC
 */
public class RTSReportQueries {		
	
	
	/**
	 * Gets the recruit request.
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
	 * @return the recruit request
	 */
	public static String getRecruitRequest(
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
		
		final StringBuffer sql = new StringBuffer();	
		sql.append("select rr from RecruitRequest rr ");
		sql.append("where rr.rcrRecruitRequestId != 0 ");
		
		if(createdFrom != null){
			sql.append("and rr.updatedDate >= :createdFrom ");	
		}	
		if(createdTo != null){
			sql.append("and rr.updatedDate <= :createdTo ");	
		}
		if(requestedFrom != null){
			sql.append("and rr.requestedDate >= :requestedFrom ");	
		}	
		if(requestedTo != null){
			sql.append("and rr.requestedDate <= :requestedTo ");	
		}	
		if(closedFrom != null){
			sql.append("and rr.closedDate >= :closedFrom ");	
		}	
		if(closedTo != null){
			sql.append("and rr.closedDate <= :closedTo ");	
		}			
		if(createdBy != null){
			sql.append("and rr.createdBy = :createdBy ");	
		}
		if(approvedBy != null){
			sql.append("and rr.approvedBy = :approvedBy ");
		}
		if(handledBy != null){
			sql.append("and rr.handledBy = :handledBy ");
		}
		
		if(orgUnit != null){
			sql.append("and rr.orgUnit = :orgUnit ");
		}	
		if(StringUtils.isNotEmpty(recruitRequestName)){
			sql.append("and rr.recruitRequestName = :recruitRequestName ");
		}
		if(StringUtils.isNotEmpty(status)){
			sql.append("and rr.status = :status ");
		}	
		
		sql.append("order by rr.updatedDate desc");
		
		return sql.toString();	
	}
	
	/**
	 * Gets the recruit request.
	 *
	 * @param selectedOrgUnits the selected org units
	 * @return the recruit request
	 */
	public static String getRecruitRequest(final String selectedOrgUnits){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select rcr from RecruitRequest rcr ");
		sql.append("where rcr.rcrRecruitRequestId != 0"); //orgUnit
		
		if(StringUtils.isNotEmpty(selectedOrgUnits)){
			sql.append(" and rcr.orgUnit.orgUnitId in ");
			sql.append("(");
			sql.append(selectedOrgUnits);
			sql.append(")");
		}
		
		return sql.toString();	
	}
	
}