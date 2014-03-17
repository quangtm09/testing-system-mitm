package com.bosch.rts.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import org.jboss.seam.log.Log;

import com.bosch.rts.entity.RecruitRequest;
import com.bosch.rts.entity.User;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSQueries;

/**
 * The Class RecruitRequestList.
 */
@Name("recruitRequestList")
public class RecruitRequestList extends EntityQuery<RecruitRequest> {
	
	/** serialVersionUID. */
	
	@Logger
	private transient Log log;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2561164092575007827L;

	/** The date4 compare. */
	private Date date4Compare;

	/** The Constant EJBQL. */
	private static final String EJBQL = "select recruitRequest from RecruitRequest recruitRequest";
	
	/** The Constant CREATED_DATE_ORDER_DESC. */
	private static final String CREATED_DATE_ORDER_DESC = " order by recruitRequest.updatedDate desc";

	/** The recruit request. */
	private RecruitRequest recruitRequest = new RecruitRequest();
	
	/**
	 * Instantiates a new recruit request list.
	 */
	public RecruitRequestList() {
		setEjbql(EJBQL + CREATED_DATE_ORDER_DESC);		
	}
	
	/**
	 * Instantiates a new recruit request list.
	 *
	 * @param orderColumn the order column
	 */
	public RecruitRequestList(final String orderColumn) {
		setEjbql(EJBQL);
		setOrder(RTSConstants.SQL_RECRUIT_REQUEST_NAME);
	}
	
	/**
	 * Gets the recruit requests order by name.
	 *
	 * @return the recruit requests order by name
	 */
	@SuppressWarnings("unchecked")
	public List<RecruitRequest> getRecruitRequestsOrderByName(){
		final String sql = RTSQueries.getRecruitRequestsOrderByName();
		final Query query = getEntityManager().createQuery(sql);
		List<RecruitRequest> recruitRequests = query.getResultList();
		return recruitRequests;
	}
	
	/**
	 * Gets the recruit requests by name.
	 *
	 * @param recruitRequestsName the recruit requests name
	 * @return the recruit requests by name
	 */
	@SuppressWarnings("unchecked")
	public List<RecruitRequest> getRecruitRequestsByName(String recruitRequestsName){
		List<RecruitRequest> recruitRequests = new ArrayList<RecruitRequest>();		
		if(recruitRequestsName != null){
			final String sql = RTSQueries.getRecruitRequestsByName();
			final Query query = getEntityManager().createQuery(sql);
			query.setParameter(RTSConstants.SQL_RECRUIT_REQUEST_NAME, recruitRequestsName);
			recruitRequests = query.getResultList();			
		}
		return recruitRequests;
	} 
	
	/**
	 * Gets the recruit request names by name.
	 *
	 * @param recruitRequestsName the recruit requests name
	 * @return the recruit request names by name
	 */
	@SuppressWarnings("unchecked")
	public List<String> getRecruitRequestNamesByName(String recruitRequestsName){
		List<String> recruitRequests = new ArrayList<String>();		
		if(recruitRequestsName != null){
			final String sql = RTSQueries.getRecruitRequestNamesByName();
			final Query query = getEntityManager().createQuery(sql);
			query.setParameter(RTSConstants.SQL_RECRUIT_REQUEST_NAME, recruitRequestsName);
			recruitRequests = query.getResultList();			
		}
		return recruitRequests;
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
	 * Gets the date4 compare.
	 *
	 * @return the date4 compare
	 */
	public Date getDate4Compare() {
		return date4Compare;
	}

	/**
	 * Sets the date4 compare.
	 *
	 * @param date4Compare the new date4 compare
	 */
	public void setDate4Compare(Date date4Compare) {
		this.date4Compare = date4Compare;
	}
	
	/**
	 * Gets the available recruit requests.
	 *
	 * @return the available recruit requests
	 */
	public List<RecruitRequest> getAvailableRecruitRequests() {
		List<RecruitRequest> listRecruitRequests = new ArrayList<RecruitRequest>();
		date4Compare = new Date();
		String strQuery = "select rr from RecruitRequest rr where rr.rcrExpectedDate >= #{recruitRequestList.date4Compare}";

		setEjbql(strQuery);

		listRecruitRequests = getResultList();

		return listRecruitRequests;
	}
	
	/**
	 * Gets the recruit requests by org unit id.
	 *
	 * @param orgUnitId the org unit id
	 * @return the recruit requests by org unit id
	 */
	@SuppressWarnings("unchecked")
	public List<RecruitRequest> getRecruitRequestByOrgUnitLevelPath(final String LEVEL_PATH) {			
		final String sql = RTSQueries.getRecruitRequestByOrgUnitLevelPath();		
		final Query query = getEntityManager().createQuery(sql);		
		query.setParameter(RTSConstants.SQL_LEVEL_PATH, LEVEL_PATH);
		List<RecruitRequest> recruitRequests =  query.getResultList();		
		return recruitRequests;
	}
	
	/**
	 * Gets the recruit requests by org unit id.
	 *
	 * @param orgUnitId the org unit id
	 * @return the recruit requests by org unit id
	 */
	@SuppressWarnings("unchecked")
	public List<RecruitRequest> getRecruitRequestReportByOrgUnitLevelPath(final String LEVEL_PATH) {			
		final String sql = RTSQueries.getRecruitRequestByOrgUnitLevelPath();		
		final Query query = getEntityManager().createQuery(sql);		
		query.setParameter(RTSConstants.SQL_LEVEL_PATH, LEVEL_PATH);
		List<RecruitRequest> recruitRequests =  query.getResultList();		
		return recruitRequests;
	}
	
	//new implement
	/**
	 * Search recruit request.
	 *
	 * @param orgUnitIds the org unit ids
	 * @param recruitRequestName the recruit request name
	 * @param status the status
	 * @param requestedDate the requested date
	 * @param closedDate the closed date
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<RecruitRequest> searchRecruitRequest(final List<Integer> orgUnitIds,
			final String recruitRequestName, final String status,
			final Date requestedDate, final Date closedDate, final User approveUser){
		try{
			final Query hql = getEntityManager().createQuery(
					RTSQueries.searchRecruitRequest(orgUnitIds, 
							recruitRequestName, 
							status, 
							requestedDate, 
							closedDate,
							approveUser));			
			
			if(!StringUtils.isBlank(recruitRequestName)){
				hql.setParameter(RTSConstants.RECRUIT_REQUEST_NAME, recruitRequestName);
			}
			if(!StringUtils.isBlank(status)){
				hql.setParameter(RTSConstants.STATUS, status);
			}
			if(requestedDate != null){
				hql.setParameter(RTSConstants.REQUESTED_DATE, requestedDate);
			}
			if(closedDate != null){
				hql.setParameter(RTSConstants.CLOSED_DATE, closedDate);
			}	
			if(approveUser != null){
				hql.setParameter(RTSConstants.USR_USER_NAME, approveUser.getUsrUserName());
			}	
			
			List<RecruitRequest> recruitRequests = hql.getResultList();
			return recruitRequests;
		} catch (Exception e) {
			log.error("Error in searching recruit request. " + e.getMessage());
			return null;
		}		
	}
	
	/**
	 * Gets the org unit ids.
	 *
	 * @param levelPath the level path
	 * @return the org unit ids
	 */
	public List<Integer> getOrgUnitIds(final String levelPath){
		try{
			final String hql = RTSQueries.getOrgUnitIdsQuery();
			final Query query = getEntityManager().createQuery(hql);			
			query.setParameter(RTSConstants.SQL_LEVEL_PATH, levelPath);			
			List<Integer> orgUnitIds = query.getResultList();	
			System.out.println("orgUnitIds: " + orgUnitIds.size());
			return orgUnitIds;
		} catch (Exception e) {
			log.error("Error in getting org unit ids. " + e.getMessage());
			return null;
		}		
	}
	
	/**
	 * Find recruit request by id.
	 *
	 * @param recruitRequestId the recruit request id
	 * @return the recruit request
	 */
	public RecruitRequest findRecruitRequestById(final int recruitRequestId){
		return getEntityManager().find(RecruitRequest.class, recruitRequestId);
	}
	
	 /**
 	 * Suggest by name.
 	 *
 	 * @param value the value
 	 * @return the list
 	 */
 	public List<RecruitRequest> suggestByName(Object value){  
		recruitRequest = new RecruitRequest();  
		recruitRequest.setRecruitRequestName(((String)value));
		List<RecruitRequest> li = getResultList();  
		
		return li;  
	 }	
	
	 
}