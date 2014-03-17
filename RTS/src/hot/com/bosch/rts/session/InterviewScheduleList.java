package com.bosch.rts.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.lang3.StringUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import com.bosch.rts.blo.UserBLO;
import com.bosch.rts.entity.Candidate;
import com.bosch.rts.entity.CandidateStatus;
import com.bosch.rts.entity.InterviewSchedule;
import com.bosch.rts.entity.RecruitRequest;
import com.bosch.rts.entity.User;
import com.bosch.rts.entity.UserHasInterviewSchedule;
import com.bosch.rts.utilities.Constants.HRStatuses;
import com.bosch.rts.utilities.Constants.InterviewScheduleStatus;
import com.bosch.rts.utilities.Constants.PrivilegeConstants;
import com.bosch.rts.utilities.Constants.TechnicalStatuses;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSQueries;
import com.bosch.rts.utilities.RTSRoles;
import com.bosch.rts.utilities.RTSUtils;

/**
 * The Class InterviewScheduleList.
 */
@Name("interviewScheduleList")
public class InterviewScheduleList extends EntityQuery<InterviewSchedule> implements Serializable{

	/** serialVersionUID. */
	private static final long serialVersionUID = -3907797740564074510L;

	/** The candidate home. */
	@In(create = true)
	private CandidateHome candidateHome;
	
	/** The recruit request home. */
	@In(create = true)
	private RecruitRequestHome recruitRequestHome;
	
	/** The identity. */
	@In
	private Identity identity;
	
	/** The logged in user. */
	@In(scope = ScopeType.SESSION, required = false)
	private User loggedInUser;	
	
	/** The user blo. */
	@In(create = true)
	private UserBLO userBLO;

	/** The candidate list. */
	private List<Candidate> candidateList;
	
	/** The recruit request list. */
	private List<RecruitRequest> recruitRequestList;
	
	/** The interview round list. */
	private List<String> interviewRoundList;
	
	/** The status list. */
	private List<String> statusList;
	
	/** The search result list. */
	private List<InterviewSchedule> searchResultList;

	/** The Constant EJBQL. */
	private static final String EJBQL = "select interviewSchedule from InterviewSchedule interviewSchedule";
	
	/** The Constant ASC_SORT. */
	private static final String ASC_SORT = " order by interviewSchedule.itsTechnicalInterviewTime desc";

	/** The interview schedule. */
	private InterviewSchedule interviewSchedule = new InterviewSchedule();
	
	/** The credentials. */
	@In(create = true)
	private Credentials credentials;

	/**
	 * Instantiates a new interview schedule list.
	 */
	public InterviewScheduleList() {
		setEjbql(EJBQL + ASC_SORT);
	}

	/**
	 * Gets the interview schedule.
	 *
	 * @return the interview schedule
	 */
	public InterviewSchedule getInterviewSchedule() {
		return interviewSchedule;
	}

	/**
	 * Gets the candidate list.
	 *
	 * @return the candidate list
	 */
	@SuppressWarnings("unchecked")
	public List<Candidate> getCandidateList() {
		if(!RTSUtils.isNotEmpty(candidateList)){
			if (identity.hasRole(RTSRoles.ROLE_GM) || identity.hasRole(RTSRoles.ROLE_HR)) {
				candidateList = candidateHome.findAllCandidateForInterviewScheduleList();
			} else if (identity.hasRole(RTSRoles.ROLE_INTEVIEWER)
					&& !userBLO
							.userHasPrivilege(loggedInUser.getUsrUserName(),
									PrivilegeConstants.SEARCH_INTERVIEW_SCHEDULE
											.toString())) {
				candidateList = getCandidateForInterviewer();
			} else {
				candidateList = candidateHome.findCandidateByUser(loggedInUser);		
			}
			
			if(RTSUtils.isNotEmpty(candidateList)){
				final BeanComparator beanComparator = new BeanComparator(RTSConstants.SQL_CDD_NAME);
				Collections.sort(candidateList, beanComparator);	
			}
		}
		
		return candidateList;
	}
		
	/**
	 * Search interview schedules.
	 *
	 * @param candidateName the candidate name
	 * @param recruitRequestName the recruit request name
	 * @param fromDate the from date
	 * @param toDate the to date
	 * @param technicalInterviewStatus the technical interview status
	 * @param hrInterviewStatus the hr interview status
	 * @return the list
	 */	
	@SuppressWarnings("unchecked")
	public List<InterviewSchedule> searchInterviewSchedules(final String candidateName,
			final String recruitRequestName, final Date fromDate, final Date toDate,
			final String technicalInterviewStatus, final String hrInterviewStatus) {

		List<InterviewSchedule> interviewSchedules = null;
		String sql = null;
		Query query = null;

		final String userName = credentials.getUsername();
		// Filter Interview schedule by role
		if (identity.hasRole(RTSRoles.ROLE_GM) || identity.hasRole(RTSRoles.ROLE_HR)
				|| identity.hasRole(RTSRoles.ROLE_DH) || identity.hasRole(RTSRoles.ROLE_GRM)
				|| identity.hasRole(RTSRoles.ROLE_PM)) {

			sql = RTSQueries.searchInterviewSchedules(candidateName, recruitRequestName, fromDate, toDate,
					technicalInterviewStatus, hrInterviewStatus);			
			query = getEntityManager().createQuery(sql);

		} else if (identity.hasRole(RTSRoles.ROLE_INTEVIEWER)
				&& userBLO.userHasPrivilege(loggedInUser.getUsrUserName(),
						PrivilegeConstants.SEARCH_INTERVIEW_SCHEDULE.toString())) {
			sql = RTSQueries.searchInterviewSchedulesByInterviewer(userName, candidateName, recruitRequestName,
					fromDate, toDate, technicalInterviewStatus, hrInterviewStatus);
			
			query = getEntityManager().createQuery(sql);
			if (!StringUtils.isBlank(userName)) {
				query.setParameter(RTSConstants.USR_USER_NAME, userName);
			}
		} else {// Admin
			sql = "";
		}
		if (!StringUtils.isBlank(sql)) {	

			if (!StringUtils.isBlank(candidateName)) {
				query.setParameter(RTSConstants.SQL_CDD_NAME, candidateName);
			}
			if (!StringUtils.isBlank(recruitRequestName)) {
				query.setParameter(RTSConstants.RECRUIT_REQUEST_NAME, recruitRequestName);
			}
			if (fromDate != null) {
				query.setParameter(RTSConstants.SQL_FROM_DATE, fromDate);
			}
			if (toDate != null) {
				query.setParameter(RTSConstants.SQL_TO_DATE, toDate);
			}
			if (!StringUtils.isBlank(technicalInterviewStatus)) {
				query.setParameter(RTSConstants.SQL_ITS_TECHNICAL_STATUS, technicalInterviewStatus);
			}
			if (!StringUtils.isBlank(hrInterviewStatus)) {
				query.setParameter(RTSConstants.SQL_ITS_HR_STATUS, hrInterviewStatus);
			}
			interviewSchedules = query.getResultList();
		}
		return interviewSchedules;
	}

	/**
	 * Sets the candidate list.
	 *
	 * @param candidateList the new candidate list
	 */
	public void setCandidateList(List<Candidate> candidateList) {
		this.candidateList = candidateList;
	}

	
	//OrgUnit
	/**
	 * Gets the recruit request list.
	 *
	 * @return the recruit request list
	 */
	@SuppressWarnings("unchecked")
	public List<RecruitRequest> getRecruitRequestList() {
		if(!RTSUtils.isNotEmpty(recruitRequestList)){
			if (identity.hasRole(RTSRoles.ROLE_GM)) {
				recruitRequestList = recruitRequestHome
						.findAllRecruitRequestForInterviewScheduleList();
			} else {
				recruitRequestList = recruitRequestHome
						.findRecruitRequestByOrgUnitForInterviewScheduleList(loggedInUser
								.getOrgUnit().getOrgUnitId());
			}
			
			if(RTSUtils.isNotEmpty(recruitRequestList)){
				final BeanComparator rcrComparator = new BeanComparator(RTSConstants.SQL_RECRUIT_REQUEST_NAME);
				Collections.sort(recruitRequestList, rcrComparator);
			}
		}		
		return recruitRequestList;
	}

	/**
	 * Sets the recruit request list.
	 *
	 * @param recruitRequestList the new recruit request list
	 */
	public void setRecruitRequestList(List<RecruitRequest> recruitRequestList) {
		this.recruitRequestList = recruitRequestList;
	}

	/**
	 * Gets the interview round list.
	 *
	 * @return the interview round list
	 */
	public List<String> getInterviewRoundList() {
		interviewRoundList = new ArrayList<String>();		
		interviewRoundList.add(0, RTSConstants.SCH_TECHNICAL);
		interviewRoundList.add(1, RTSConstants.SCH_HR);
		return interviewRoundList;
	}

	/**
	 * Sets the interview round list.
	 *
	 * @param interviewRoundList the new interview round list
	 */
	public void setInterviewRoundList(List<String> interviewRoundList) {
		this.interviewRoundList = interviewRoundList;
	}

	/**
	 * Gets the status list.
	 *
	 * @return the status list
	 */
	public List<String> getStatusList() {
		statusList = new ArrayList<String>();
		for (InterviewScheduleStatus interviewScheduleStatus : InterviewScheduleStatus.values()) {
			statusList.add(interviewScheduleStatus.toString());
		}
		return statusList;
	}
	
	/**
	 * Gets the technical statuses.
	 *
	 * @return the technical statuses
	 */
	public List<String> getTechnicalStatuses(){
		List<String> statuses = new ArrayList<String>();
		
		for(final TechnicalStatuses technicalStatus : TechnicalStatuses.values()){
			statuses.add(technicalStatus.getStatus());
		}
		return statuses;
	}
	
	/**
	 * Gets the technical statuses.
	 * @return the technical statuses
	 */
	public List<String> getInterviewScheduleTechnicalStatuses(){
		List<String> statuses = new ArrayList<String>();
		
		for(final TechnicalStatuses technicalStatus : TechnicalStatuses.values()){			
			if(!technicalStatus.equals(TechnicalStatuses.TAKEN)){				
				statuses.add(technicalStatus.getStatus());
			} 			
		}
		
		return statuses;
		
	}
	
	/**
	 * Gets the hr statuses.
	 *
	 * @return the hr statuses
	 */
	public List<String> getHrStatuses(){
		List<String> statuses = new ArrayList<String>();
		
		for(final HRStatuses hrStatuses : HRStatuses.values()){
			statuses.add(hrStatuses.getStatus());
		}
		return statuses;
	}

	/**
	 * Sets the status list.
	 *
	 * @param statusList the new status list
	 */
	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	/**
	 * Gets the search result list.
	 *
	 * @return the search result list
	 */
	public List<InterviewSchedule> getSearchResultList() {
		if (searchResultList == null) {
			return new ArrayList<InterviewSchedule>();
		}
		return searchResultList;
	}

	/**
	 * Sets the search result list.
	 *
	 * @param searchResultList the new search result list
	 */
	public void setSearchResultList(List<InterviewSchedule> searchResultList) {
		this.searchResultList = searchResultList;
	}
	
	/**
	 * Initialise interview schedules.
	 *
	 * @return the list
	 */
	public List<InterviewSchedule> initialiseInterviewSchedules(){
		List<InterviewSchedule> interviewSchedules = super.getResultList();
		return interviewSchedules;
	}
	
	/**
	 * Gets the data list.
	 *
	 * @return the data list
	 */
	public List<InterviewSchedule> getDataList() {
		return getSearchResultList();
	}

	/**
	 * Gets the candidate for interviewer.
	 *
	 * @return the candidate for interviewer
	 */
	@SuppressWarnings("unchecked")
	private List<Candidate> getCandidateForInterviewer() {
		List<Candidate> resultList = new ArrayList<Candidate>();
		final String strQuery = RTSQueries.getCandidateForInterviewer();;
		final Query query = getEntityManager().createQuery(strQuery);
		query.setParameter(RTSConstants.USER_ID, loggedInUser.getUsrUserId().intValue());
		final List<UserHasInterviewSchedule> userHasISList = query.getResultList();
		if (RTSUtils.isNotEmpty(userHasISList)) {
			for(UserHasInterviewSchedule userHasIS : userHasISList){
				Candidate cdd = userHasIS.getInterviewSchedule().getCandidate();
				if (cdd.getCddStatus().dbString().equalsIgnoreCase(CandidateStatus.TECHNICAL_SCHEDULED.dbString())) {
					resultList.add(cdd);
				}
			}
		}
		return resultList;
	}

	/**
	 * Gets the interview schedule id.
	 *
	 * @param candidateId the candidate id
	 * @return the interview schedule id
	 */
	@SuppressWarnings("unchecked")
	public int getInterviewScheduleId(int candidateId) {
		int interviewScheduleId = 0;

		final String strQuery = RTSQueries.getInterviewScheduleId();;
		final Query query = getEntityManager().createQuery(strQuery);
		query.setParameter(RTSConstants.SQL_CANDIDATE_ID, candidateId);
		final List<InterviewSchedule> resultList = query.getResultList();

		if (RTSUtils.isNotEmpty(resultList)) {
			InterviewSchedule interviewSchedule = resultList.get(0);
			interviewScheduleId = (int) interviewSchedule.getItsInterviewScheduleId();
		}

		return interviewScheduleId;
	}
	
	/**
	 * Gets the interview schedule by interview id.
	 *
	 * @param interviewId the interview id
	 * @return the interview schedule by interview id
	 */
	@SuppressWarnings("unchecked")
	public InterviewSchedule getInterviewScheduleByInterviewId(int interviewId) {
		InterviewSchedule interviewSchedule = null;

		final String strQuery = RTSQueries.getInterviewScheduleByInterviewId();		
		final Query query = getEntityManager().createQuery(strQuery);
		query.setParameter(RTSConstants.ITS_INTERVIEW_SCHEDULE_ID, interviewId);
		List<InterviewSchedule> resultList = query.getResultList();

		if (RTSUtils.isNotEmpty(resultList)) {
			interviewSchedule = resultList.get(0);
		}

		return interviewSchedule;
	}
	
	/**
	 * Gets the interview schedule by candidate id.
	 *
	 * @param candidateId the candidate id
	 * @return the interview schedule by candidate id
	 */
	@SuppressWarnings("unchecked")
	public List<InterviewSchedule> getInterviewScheduleByCandidateId(int candidateId){		
		final String queryString = RTSQueries.getInterviewScheduleByCandidateId();
		final Query query = getEntityManager().createQuery(queryString);
		query.setParameter(RTSConstants.SQL_CANDIDATE_ID, candidateId);
		final List<InterviewSchedule> resultList = query.getResultList();		
		return resultList;
	}
	
	/*public List<InterviewSchedule> getInterviewScheduleByInterviewer(int interviewId){		
		final String queryString = RTSQueries.getInterviewScheduleByCandidateId();
		final Query query = getEntityManager().createQuery(queryString);
		query.setParameter(RTSConstants.SQL_CANDIDATE_ID, candidateId);
		final List<InterviewSchedule> resultList = query.getResultList();		
		return resultList;
	}*/
}
