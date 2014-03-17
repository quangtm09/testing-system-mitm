/*
 * com.bosch.rts.session.CandidateList.java
 */
package com.bosch.rts.session;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.bosch.rts.bus.CandidateListBean;
import com.bosch.rts.entity.Candidate;
import com.bosch.rts.entity.CandidateStatus;
import com.bosch.rts.entity.OrgUnit;
import com.bosch.rts.entity.RecruitRequest;
import com.bosch.rts.entity.Skill;
import com.bosch.rts.entity.User;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSQueries;
import com.bosch.rts.utilities.RTSUtils;

/**
 * The Class CandidateList.
 */
@Name("candidateList")
public class CandidateList extends EntityQuery<Candidate> {

	/** serialVersionUID. */
	private static final long serialVersionUID = -1085726179096149690L;

	/** The Constant EJBQL. */
	private static final String EJBQL = "select candidate from Candidate candidate";
	
	/** The Constant UPDATED_DATE_ORDER. */
	private static final String UPDATED_DATE_ORDER = " order by candidate.cddUpdatedDate desc and candidate.cddName asc";
	
	/*private static final String ASC_SORT = "cddName";*/

	/** The candidate. */
	private Candidate candidate = new Candidate();
	
	/**
	 * Instantiates a new candidate list.
	 */
	public CandidateList() {
		setEjbql(EJBQL + UPDATED_DATE_ORDER);
	}

	/**
	 * Gets the candidate.
	 *
	 * @return the candidate
	 */
	public Candidate getCandidate() {
		return candidate;
	}
	
	/** The candidate list bean. */
	@In(create=true)
	private CandidateListBean candidateListBean;

	
	/**
	 * Search candidates by skill spec.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Candidate> searchCandidatesBySkillSpec() {
		final List<Skill> chosenSkills = candidateListBean.getSelectedSkills();
		final float yoeFrom = candidateListBean.getYoeFrom() > 0 ? candidateListBean.getYoeFrom() : 0;
		final float yoeTo = candidateListBean.getYoeTo() > 0 ? candidateListBean.getYoeTo() : 0;
		
		final String sql = RTSQueries.getCandidatebySkills(chosenSkills, yoeFrom, yoeTo);		
		final Query query = getEntityManager().createQuery(sql);
		if(yoeFrom > 0){
			query.setParameter(RTSConstants.SQL_YOE_FROM, yoeFrom);
		}
		if(yoeTo > 0){
			query.setParameter(RTSConstants.SQL_YOE_TO, yoeTo);
		}			
		
		final List<Candidate> candidates = query.getResultList();
		return candidates;		
	}
	
	/**
	 * Gets the candidate by name.
	 *
	 * @param candidateName the candidate name
	 * @return the candidate by name
	 */
	@SuppressWarnings("unchecked")
	public List<Candidate> getCandidateByName(String candidateName){		
		final String queryString = RTSQueries.getCandidateByName();
		final Query query = getEntityManager().createQuery(queryString);
		query.setParameter(RTSConstants.SQL_CDD_NAME, candidateName);
		List<Candidate> resultList  = query.getResultList();		
		return resultList;
	}
	
	/**
	 * Gets the candidate by name_ like query.
	 *
	 * @param candidateName the candidate name
	 * @return the candidate by name_ like query
	 */
	@SuppressWarnings("unchecked")
	public List<String> getCandidateByName_LikeQuery(String candidateName){		
		final String queryString = RTSQueries.getCandidateByName_LikeQuery();
		final Query query = getEntityManager().createQuery(queryString);
		query.setParameter(RTSConstants.SQL_CDD_NAME, candidateName);
		List<String> resultList  = query.getResultList();		
		return resultList;
	}
	
	/**
	 * Gets the candidate by email_ like query.
	 *
	 * @param email the email
	 * @return the candidate by email_ like query
	 */
	@SuppressWarnings("unchecked")
	public List<String> getCandidateByEmail_LikeQuery(String email){		
		final String queryString = RTSQueries.getCandidateByEmail_LikeQuery();
		final Query query = getEntityManager().createQuery(queryString);
		query.setParameter(RTSConstants.SQL_CCD_EMAIL, email);
		List<String> resultList  = query.getResultList();		
		return resultList;
	}
	
	/**
	 * Gets the candidate by name2.
	 *
	 * @param candidateId the candidate id
	 * @param candidateName the candidate name
	 * @return the candidate by name2
	 */
	@SuppressWarnings("unchecked")
	public List<Candidate> getCandidateByName2(int candidateId, String candidateName){		
		final String queryString = RTSQueries.getCandidateByName2();
		final Query query = getEntityManager().createQuery(queryString);
		query.setParameter(RTSConstants.SQL_CANDIDATE_ID, candidateId);
		query.setParameter(RTSConstants.SQL_CDD_NAME, candidateName);
		List<Candidate> resultList  = query.getResultList();		
		return resultList;
	}
	
	/**
	 * Gets the candidate by email.
	 *
	 * @param candidateEmail the candidate email
	 * @return the candidate by email
	 */
	@SuppressWarnings("unchecked")
	public List<Candidate> getCandidateByEmail(String candidateEmail){		
		final String queryString = RTSQueries.getCandidateByEmail();
		final Query query = getEntityManager().createQuery(queryString);
		query.setParameter(RTSConstants.SQL_CCD_EMAIL, candidateEmail);
		List<Candidate> resultList = query.getResultList();		
		return resultList;
	}

	/**
	 * Gets the candidate by email.
	 *
	 * @param candidateId the candidate id
	 * @param candidateEmail the candidate email
	 * @return the candidate by email
	 */
	@SuppressWarnings("unchecked")
	public List<Candidate> getCandidateByEmail(int candidateId, String candidateEmail){		
		final String queryString = RTSQueries.getCandidateByEmailAndCandidateId();
		final Query query = getEntityManager().createQuery(queryString);
		query.setParameter(RTSConstants.SQL_CANDIDATE_ID, candidateId);
		query.setParameter(RTSConstants.SQL_CCD_EMAIL, candidateEmail);		
		List<Candidate> resultList = query.getResultList();		
		return resultList;
	}
	
	/**
	 * Gets the candidate by email2.
	 *
	 * @param candidateId the candidate id
	 * @param candidateEmail the candidate email
	 * @return the candidate by email2
	 */
	@SuppressWarnings("unchecked")
	public List<Candidate> getCandidateByEmail2(int candidateId, String candidateEmail){		
		final String queryString = RTSQueries.getCandidateByEmail2();
		final Query query = getEntityManager().createQuery(queryString);
		query.setParameter(RTSConstants.SQL_CANDIDATE_ID, candidateId);
		query.setParameter(RTSConstants.SQL_CCD_EMAIL, candidateEmail);
		List<Candidate> resultList = query.getResultList();		
		return resultList;
	}
	
	/**
	 * Search candidates by info based criteria.
	 *
	 * @param candidateName the candidate name
	 * @param recruitRequestName the recruit request name
	 * @param email the email
	 * @param phone the phone
	 * @param status the status
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Candidate> searchCandidatesByInfoBasedCriteria(final String candidateName, final String recruitRequestName,
			final String email, final String phone, final CandidateStatus status){					
		final String sql = RTSQueries.searchCandidatesByInfoBasedCriteria(candidateName, recruitRequestName, email, phone, status);
		final Query query = getEntityManager().createQuery(sql);
		if(!StringUtils.isBlank(candidateName)){
			query.setParameter(RTSConstants.SQL_CDD_NAME, candidateName);
		}
		if(!StringUtils.isBlank(recruitRequestName)){
			query.setParameter(RTSConstants.RECRUIT_REQUEST_NAME, recruitRequestName);
		}
		if(!StringUtils.isBlank(email)){
			query.setParameter(RTSConstants.SQL_CDD_EMAIL, email);
		}
		if(!StringUtils.isBlank(phone)){
			query.setParameter(RTSConstants.SQL_PHONE_NO, phone);
		}	
		if(status != null){
			query.setParameter(RTSConstants.SQL_CDD_STATUS, status.dbString());
		}		

		List<Candidate> candidates = query.getResultList();
		return candidates;
	}
	
	
	/**
	 * Search candidates.
	 *
	 * @param createdFrom the created from
	 * @param createdTo the created to
	 * @param orgUnit the org unit
	 * @param recruitRequest the recruit request
	 * @param source the source
	 * @param status the status
	 * @param handledBy the handled by
	 * @param shortListBy the short list by
	 * @param competencies the competencies
	 * @param priority the priority
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Candidate> searchCandidates(final Date createdFrom, final Date createdTo, final String createdBy,
			final OrgUnit orgUnit, final RecruitRequest recruitRequest, final String source,
			final CandidateStatus cddStatus, final String handledBy, final String shortListBy, 
			final List<Skill> competencies, final String priority){	
		
		final String sql = RTSQueries.searchCandidates(createdFrom, createdTo, createdBy,
				orgUnit, recruitRequest, source, cddStatus, handledBy, shortListBy, competencies, priority);
		final Query query = getEntityManager().createQuery(sql);
		if(createdFrom != null){
			query.setParameter("createdFrom", createdFrom);	
		}	
		if(createdTo != null){
			query.setParameter("createdTo", createdTo);	
		}
		if(createdBy != null){
			query.setParameter("cddUpdatedBy", createdBy);	
		}
		if(orgUnit != null){
			query.setParameter("orgUnit", orgUnit);	
		}	
		if(recruitRequest != null){
			query.setParameter("recruitRequest", recruitRequest);	
		}
		if(cddStatus != null){
			query.setParameter("cddStatus", cddStatus);
		}
		if(source != null){
			query.setParameter("cddSource", source);
		}
		if(handledBy != null){
			query.setParameter("handledBy", handledBy);
		}
		if(shortListBy != null){
			query.setParameter("shortListBy", shortListBy);
		}
		if(priority != null){
			query.setParameter("priority", priority);
		}
		
		List<Candidate> candidates = query.getResultList();
		return candidates;
	}
	
	/**
	 * Reset candidate.
	 *
	 * @param recruitRequest the recruit request
	 */
	public void resetCandidate(RecruitRequest recruitRequest){
		this.getCandidate().setRecruitRequest(recruitRequest);
	} 
		
	/**
	 * Gets the candidate by id.
	 *
	 * @param candidateId the candidate id
	 * @return the candidate by id
	 */
	@SuppressWarnings("unchecked")
	public List<Candidate> getCandidateById(int candidateId){		
		final String queryString = RTSQueries.getCandidateById();
		final Query query = getEntityManager().createQuery(queryString);
		query.setParameter(RTSConstants.SQL_CANDIDATE_ID, candidateId);
		List<Candidate> resultList = query.getResultList();		
		return resultList;
	}
	
	/**
	 * Find candidate by org unit for interview schedule.
	 *
	 * @param orgUnitID the org unit id
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Candidate> findCandidateByOrgUnitForInterviewSchedule(final int orgUnitID) {
		if(orgUnitID > 0){
			final String queryStr = RTSQueries.getCandidateByOrgUnitForInterviewSchedule();		
			final Query query = getEntityManager().createQuery(queryStr);
			query.setParameter(RTSConstants.ORG_UNIT_ID, orgUnitID);
			final List<Candidate> candidateList = query.getResultList();
			return candidateList;
		}
		return null;
	}

	/**
	 * Find candidate by list of org unit for interview schedule.
	 *
	 * @param orgUnitList the org unit list
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Candidate> findCandidateByListOfOrgUnitForInterviewSchedule(final List<OrgUnit> orgUnitList) {
		if(RTSUtils.isNotEmpty(orgUnitList)){
			final String queryStr = RTSQueries.getCandidateByListOfOrgUnitForInterviewSchedule(orgUnitList);
			final Query query = getEntityManager().createQuery(queryStr);
			final List<Candidate> candidateList = query.getResultList();
			return candidateList;
		}
		return null;	
	}
	
	/**
	 * Find all candidate for interview schedule.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Candidate> findAllCandidateForInterviewSchedule() {
		final String sql = RTSQueries.getCandidateByStatus();
		final Query query = getEntityManager().createQuery(sql);
		query.setParameter(RTSConstants.SQL_CDD_STATUS1, CandidateStatus.TECHNICAL_SCHEDULED);
		query.setParameter(RTSConstants.SQL_CDD_STATUS2, CandidateStatus.TECHNICAL_PASS);		
		final List<Candidate> candidateList = query.getResultList();		
		return candidateList;
	}
	
	/**
	 * Find candidate by org unit for interview schedule list.
	 *
	 * @param orgUnitID the org unit id
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Candidate> findCandidateByOrgUnitForInterviewScheduleList(final int orgUnitID) {
		final String queryStr = RTSQueries.getCandidateByOrgUnit();
		final Query query = getEntityManager().createQuery(queryStr);
		query.setParameter(RTSConstants.ORG_UNIT_ID, orgUnitID);
		final List<Candidate> candidateList = query.getResultList();
		return candidateList;
	}
	
	/**
	 * Find candidate by user.
	 *
	 * @param user the user
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Candidate> findCandidateByUser(final User user) {
		String sql  = RTSQueries.getCandidateByOrgUnit();
		Query query = getEntityManager().createQuery(sql);
		query.setParameter(RTSConstants.ORG_UNIT_ID, user.getOrgUnit().getOrgUnitId());
		final List<Candidate> candidateList = query.getResultList();
		
		final Set<OrgUnit> orgUnitList = user.getOrgUnits();
		for (Object obj : orgUnitList.toArray()) {
			OrgUnit orgUnit =  (OrgUnit) obj;
			if (orgUnit.getOrgUnitId() != user.getOrgUnit().getOrgUnitId()) {
				sql = RTSQueries.getCandidateByOrgUnit();
				query = getEntityManager().createQuery(sql);
				query.setParameter(RTSConstants.ORG_UNIT_ID, orgUnit.getOrgUnitId());
				candidateList.addAll(query.getResultList());
			}
		}		
			
		return candidateList;
	}
	
	/**
	 * Find all candidate for interview schedule list.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Candidate> findAllCandidateForInterviewScheduleList() {
		final String queryStr = RTSQueries.getAllCandidateForInterviewScheduleList();		
		final Query query = getEntityManager().createQuery(queryStr);
		final List<Candidate> candidateList = query.getResultList();
		return candidateList;
	}
	
	/**
	 * Check email existed.
	 *
	 * @param email the email
	 * @return the candidate
	 */
	@SuppressWarnings("unchecked")
	public Candidate checkEmailExisted(final String email) {
		final String queryStr = RTSQueries.getEmailExisted();
		final Query query = getEntityManager().createQuery(queryStr);
		query.setParameter(RTSConstants.SQL_CCD_EMAIL, email);
		final List<Candidate> candidateList = query.getResultList();		
		return RTSUtils.isNotEmpty(candidateList) ? candidateList.get(0) : null;
	}
}
