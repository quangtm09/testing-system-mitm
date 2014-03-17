/**
 * com.bosch.rts.utilities.RTSQueries.java
 */
package com.bosch.rts.utilities;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.bosch.rts.entity.CandidateStatus;
import com.bosch.rts.entity.OrgUnit;
import com.bosch.rts.entity.RecruitRequest;
import com.bosch.rts.entity.Skill;
import com.bosch.rts.entity.User;

/**
 * The RTS HQL Queries.
 *
 * @author khb1hc
 * 
 * HQL queries
 */
public class RTSQueries {
	
	/** The Constant CANDIDATE_QUERY. */
	private static final String CANDIDATE_QUERY = "select candidate from Candidate candidate "; 
	
	/** The Constant RECRUIT_REQUEST_QUERY. */
	private static final String RECRUIT_REQUEST_QUERY = "select recruitRequest from RecruitRequest recruitRequest ";
	
	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public static final String getUsers() {
		final StringBuffer sql = new StringBuffer();	
		sql.append("select user from User user ");
		sql.append("where user.status = :status ");
		sql.append("and user.approved = :approved ");
		sql.append("order by user.usrFullName asc");
		return sql.toString();	
	}
	
	/**
	 * Gets the recruit request by org unit id.
	 *
	 * @return the recruit request by org unit id
	 */
	public static final String getRecruitRequestByOrgUnitLevelPath() {
		final StringBuffer sql = new StringBuffer();	
		sql.append("select recruitRequest from RecruitRequest recruitRequest ");
		sql.append("where recruitRequest.orgUnit.levelPath LIKE concat(:levelPath, '%') ");
		sql.append("and recruitRequest.status = 'Approved' ");
		sql.append("order by recruitRequest.recruitRequestName ASC");
		return sql.toString();	
	}
	
	/**
	 * Gets the recruit request by org unit id.
	 *
	 * @return the recruit request by org unit id
	 */
	public static final String getRecruitRequestReportByOrgUnitLevelPath() {
		final StringBuffer sql = new StringBuffer();	
		sql.append("select recruitRequest from RecruitRequest recruitRequest ");
		sql.append("where recruitRequest.orgUnit.levelPath LIKE concat(:levelPath, '%') ");
		sql.append("order by recruitRequest.recruitRequestName ASC");
		return sql.toString();	
	}
		
	/**
	 * Search recruit request.
	 *
	 * @param orgUnitIds the org unit ids
	 * @param recruitRequestName the recruit request name
	 * @param status the status
	 * @param requestedDate the requested date
	 * @param closedDate the closed date
	 * @param approvedBy the approved by
	 * @return the string
	 */
	public static final String searchRecruitRequest(final List<Integer> orgUnitIds,
			final String recruitRequestName, final String status,
			final Date requestedDate, final Date closedDate, final User approvedBy){
		final StringBuffer sql = new StringBuffer();
		sql.append("select recruitRequest from RecruitRequest recruitRequest ");
		sql.append("where recruitRequest.rcrRecruitRequestId <> -1 ");	
				
		if(RTSUtils.isNotEmpty(orgUnitIds)){
			sql.append(" and recruitRequest.orgUnit.orgUnitId IN (");
			for(final int orgUnitId : orgUnitIds){
				sql.append(orgUnitId);
				sql.append(',');
			}

			final int commaIndex = sql.lastIndexOf(",");
			if (commaIndex != -1) {
				sql.replace(commaIndex, commaIndex + 1, ") ");
			}
		}
		
		if(!StringUtils.isBlank(recruitRequestName)){
			sql.append(" and lower(recruitRequest.recruitRequestName) like lower(concat(:recruitRequestName,'%')) ");			
		}
		
		if(!StringUtils.isBlank(status)){
			sql.append(" and recruitRequest.status = :status");		
		}
		
		if(requestedDate != null){
			sql.append(" and recruitRequest.requestedDate >= :requestedDate");					
		}
		
		if(closedDate != null){
			sql.append(" and recruitRequest.closedDate <= :closedDate");						
		}
		
		if(approvedBy != null){
			sql.append(" and recruitRequest.approvedBy.usrUserName = :usrUserName");		
		}
		
		sql.append(RTSConstants.SQL_ORDER_BY);
		sql.append(RTSConstants.SQL_RECRUIT_REQUEST_CREATED_DATE);
		sql.append(RTSConstants.SPACER);
		sql.append(RTSConstants.DESCENDING_ORDER);				
		
		return sql.toString();
	}
	
	/**
	 * Gets the org unit ids query.
	 *
	 * @return the org unit ids query
	 */
	public static final String getOrgUnitIdsQuery(){
		final StringBuffer sql = new StringBuffer();
		sql.append("select orgUnit.orgUnitId from OrgUnit orgUnit ");
		sql.append("where  orgUnit.levelPath like concat(:levelPath, '%')");
		//sql.append("where orgUnit.levelPath = :levelPath");
		//sql.append("where  orgUnit.levelPath like '0001%'");
		return sql.toString();
	}
	
	/**
	 * Gets the org unit ids query.
	 *
	 * @param levelPath the level path
	 * @return the org unit ids query
	 */
	public static final String getOrgUnitIdsQuery(final String levelPath){
		final StringBuffer sql = new StringBuffer();
		sql.append("select orgUnit.orgUnitId from OrgUnit orgUnit ");
		sql.append("where orgUnit.levelPath like ['"+levelPath+ "%']");
		
		return sql.toString();
	}	
	
	
	/**
	 * Gets the user by user name.
	 *
	 * @return the user by user name
	 */
	public static String getUserByUserName(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select user from User user ");
		sql.append("where user.usrUserName = :usrName ");		
		sql.append("order by user.usrFullName asc");		
		return sql.toString();	
	}
	
	/**
	 * Gets the user by email.
	 *
	 * @return the user by email
	 */
	public static String getUserByEmail(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select user from User user ");
		sql.append("where user.usrEmail = :usrEmail ");		
		sql.append("order by user.usrFullName asc");		
		return sql.toString();	
	}
	
	/**
	 * Gets the user by email_ Like Query.
	 *
	 * @return the user by email_ Like Query
	 */
	public static String getUserByEmail_LikeQuery(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select user from User user ");
		sql.append("where lower(user.usrEmail) like lower(concat(:usrEmail,'%')) ");		
		sql.append("order by user.usrEmail asc");		
		return sql.toString();	
	}
	
	/**
	 * Gets the user by id no.
	 *
	 * @return the user by id no
	 */
	public static String getUserByIdNo(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select user from User user ");
		sql.append("where user.usrIdNumber = :usrIdNumber ");
		return sql.toString();	
	}
	
	/**
	 * Gets the candidate by email.
	 *
	 * @return the candidate by email
	 */
	public static String getCandidateByEmail(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select ccd from Candidate ccd where ccd.cddEmail = :cddEmail order by ccd.cddName");
		return sql.toString();	
	}
	
	/**
	 * Gets the candidate by email2.
	 *
	 * @return the candidate by email2
	 */
	public static String getCandidateByEmail2(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select ccd from Candidate ccd where ccd.cddEmail = :cddEmail and ccd.cddCandidateId <> :cddCandidateId order by ccd.cddName");
		return sql.toString();	
	}
	
	/**
	 * Gets the candidate by name.
	 *
	 * @return the candidate by name
	 */
	public static String getCandidateByName(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select ccd from Candidate ccd where ccd.cddName = :cddName");
		return sql.toString();	
	}
	
	/**
	 * Gets the candidate by name_ like query.
	 *
	 * @return the candidate by name_ like query
	 */
	public static String getCandidateByName_LikeQuery(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select distinct cdd.cddName as cdd from Candidate cdd ");
		sql.append("where lower(cdd.cddName) like lower(concat(:cddName,'%')) ");
		sql.append("order by cdd.cddName asc");
		return sql.toString();	
	}
	
	
	/**
	 * Gets the active level types.
	 *
	 * @return the active level types
	 */
	public static String getActiveLevelTypes(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select levelType from LevelType levelType ");
		sql.append("where levelType.active = 'Y' ");
		sql.append("order by levelType.order asc");
		return sql.toString();	
	}

	/**
	 * Gets the candidate by email_ like query.
	 *
	 * @return the candidate by email_ like query
	 */
	public static String getCandidateByEmail_LikeQuery(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select ccd.cddEmail as ccd from Candidate ccd ");
		sql.append("where lower(ccd.cddEmail) like lower(concat(:cddEmail,'%')) ");
		sql.append("order by ccd.cddEmail asc");
		return sql.toString();	
	}
	
	/**
	 * Gets the candidate by name2.
	 *
	 * @return the candidate by name2
	 */
	public static String getCandidateByName2(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select ccd from Candidate ccd where ccd.cddName = :cddName and ccd.cddCandidateId <> :cddCandidateId");
		return sql.toString();	
	}
	
	/**
	 * Gets the candidate by email and candidate id.
	 *
	 * @return the candidate by email and candidate id
	 */
	public static String getCandidateByEmailAndCandidateId(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select ccd from Candidate ccd where ccd.cddEmail = :cddEmail and ccd.cddCandidateId <> :cddCandidateId order by ccd.cddName");
		return sql.toString();	
	}
	
	/**
	 * Gets the candidate by org unit.
	 *
	 * @return the candidate by org unit
	 */
	public static String getCandidateByOrgUnit(){
		final StringBuffer sql = new StringBuffer();	
		sql.append(CANDIDATE_QUERY);
		sql.append("where candidate.orgUnit.orgUnitId = :orgUnitId");
		return sql.toString();	
	}
	
	/**
	 * Gets the candidate by status.
	 *
	 * @return the candidate by status
	 */
	public static String getCandidateByStatus(){
		final StringBuffer sql = new StringBuffer();	
		sql.append(CANDIDATE_QUERY);
		sql.append("where candidate.cddStatus <> :cddStatus1 ");
		sql.append("and candidate.cddStatus <> :cddStatus2");
		return sql.toString();	
	}
	
	/**
	 * Gets the email existed.
	 *
	 * @return the email existed
	 */
	public static String getEmailExisted(){
		final StringBuffer sql = new StringBuffer();	
		sql.append(CANDIDATE_QUERY);
		sql.append("where candidate.cddEmail = :cddEmail");
		return sql.toString();	
	}
	
	/**
	 * Gets the candidate for interviewer.
	 *
	 * @return the candidate for interviewer
	 */
	public static String getCandidateForInterviewer(){ 
		final StringBuffer sql = new StringBuffer();	
		sql.append("select userHasIS from UserHasInterviewSchedule userHasIS ");
		sql.append("where userHasIS.user.usrUserId = :usrUserId");
		return sql.toString();	
	}
	
	/**
	 * Gets the interview schedule id.
	 *
	 * @return the interview schedule id
	 */
	public static String getInterviewScheduleId(){ 
		final StringBuffer sql = new StringBuffer();	
		sql.append("select interview from InterviewSchedule interview ");
		sql.append("where interview.candidate.cddCandidateId = :cddCandidateId");
		return sql.toString();	
	}
	
	/**
	 * Gets the all recruit request for interview schedule list.
	 *
	 * @return the all recruit request for interview schedule list
	 */
	public static String getAllRecruitRequestForInterviewScheduleList(){ 
		final StringBuffer sql = new StringBuffer();	
		sql.append(RECRUIT_REQUEST_QUERY);
		sql.append("order by recruitRequest.recruitRequestName");
		return sql.toString();	
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
	 * @return the string
	 */
	public static String searchInterviewSchedules(final String candidateName,
			final String recruitRequestName, final Date fromDate, final Date toDate,
			final String technicalInterviewStatus, final String hrInterviewStatus){ 
		final StringBuffer sql = new StringBuffer();		
		sql.append("select interviewSchedule from InterviewSchedule interviewSchedule ");
		sql.append("where interviewSchedule.itsInterviewScheduleId != -1 ");
		
		if(!StringUtils.isBlank(candidateName)){		
			sql.append("and lower(interviewSchedule.candidate.cddName) like lower(concat(:cddName,'%')) ");
		}		
		if(!StringUtils.isBlank(recruitRequestName)){
			sql.append("and lower(interviewSchedule.recruitRequest.recruitRequestName) like lower(concat(:recruitRequestName,'%')) ");
		}
		if(fromDate != null){
			sql.append("and interviewSchedule.itsTechnicalInterviewTime >= :fromDate ");
		}
		if(toDate != null){
			sql.append("and interviewSchedule.itsTechnicalInterviewTime <= :toDate ");
		}
		if(!StringUtils.isBlank(technicalInterviewStatus)){
			sql.append("and interviewSchedule.itsTechnicalStatus = :itsTechnicalStatus ");
		}
		if(!StringUtils.isBlank(hrInterviewStatus)){
			sql.append("and interviewSchedule.itsHrStatus = :itsHrStatus ");
		}
		
		sql.append("order by interviewSchedule.itsTechnicalInterviewTime desc");
		return sql.toString();	
	}
	
	/**
	 * Gets the recruit requests order by name.
	 *
	 * @return the recruit requests order by name
	 */
	public static final String getRecruitRequestsOrderByName() {
		final StringBuffer sql = new StringBuffer();	
		sql.append("select recruitRequest from RecruitRequest recruitRequest ");	
		sql.append("order by recruitRequest.recruitRequestName asc");	
		return sql.toString();	
	} 
	
	/**
	 * Gets the recruit requests by name.
	 *
	 * @return the recruit requests by name
	 */
	public static final String getRecruitRequestsByName() {
		final StringBuffer sql = new StringBuffer();	
		sql.append("select recruitRequest from RecruitRequest recruitRequest ");	
		sql.append("where recruitRequest.recruitRequestName = :recruitRequestName ");
		sql.append("order by recruitRequest.recruitRequestName asc");	
		return sql.toString();			
	}
	
	/**
	 * Gets the recruit request names by name.
	 *
	 * @return the recruit request names by name
	 */
	public static final String getRecruitRequestNamesByName() {
		final StringBuffer sql = new StringBuffer();
		sql.append("select distinct recruitRequest.recruitRequestName as recruitRequest from RecruitRequest recruitRequest ");
		sql.append("where lower(recruitRequest.recruitRequestName) like lower(concat(:recruitRequestName,'%')) ");
		sql.append("order by recruitRequest.recruitRequestName asc");
		return sql.toString();
	}
	
	
	/**
	 * Gets the org units by name.
	 *
	 * @return the org units by name
	 */
	public static final String getOrgUnitsByName() {
		final StringBuffer sql = new StringBuffer();	
		sql.append("select orgUnit from OrgUnit orgUnit ");	
		sql.append("where lower(orgUnit.unitName) like '%' || :unitName || '%'");
		sql.append("order by orgUnit.unitName asc");	
		return sql.toString();			
	}
	
	/**
	 * Gets the org units by name.
	 *
	 * @return the org units by name
	 */
	public static final String getOrgUnitsByRole() {
		final StringBuffer sql = new StringBuffer();	
		sql.append("select orgUnit from OrgUnit orgUnit ");	
		sql.append("where lower(orgUnit.unitName) like '%' || :unitName || '%' ");
		sql.append("and orgUnit.activeState = :activeState ");	
		sql.append("order by orgUnit.unitName asc");	
		return sql.toString();			
	}
	
	/**
	 * Gets the org units by name.
	 *
	 * @return the org units by name
	 */
	public static final String getOrgUnitsByUnitName() {
		final StringBuffer sql = new StringBuffer();	
		sql.append("select orgUnit from OrgUnit orgUnit ");	
		sql.append("where orgUnit.unitName = :unitName ");
		
		return sql.toString();			
	}
	
	
	/**
	 * Gets the candidates by skill spec.
	 *
	 * @param cddName the cdd name
	 * @param rcrRecruitRequestId the rcr recruit request id
	 * @param cddPriority the cdd priority
	 * @param cddStatus the cdd status
	 * @param cddPhoneNo the cdd phone no
	 * @param cddDegree1 the cdd degree1
	 * @return the candidates by skill spec
	 */
	public static final String getCandidatesBySkillSpec(final String cddName, final int rcrRecruitRequestId,
			final int cddPriority, final CandidateStatus cddStatus, final String cddPhoneNo, final String cddDegree1) {
		final StringBuffer sql = new StringBuffer();	
		sql.append(CANDIDATE_QUERY);
		sql.append("where candidate.cddCandidateId <> -1 ");
		if(!StringUtils.isBlank(cddName)){
			sql.append("and lower(candidate.cddName) like lower(:cddName) ");
		}
		if(rcrRecruitRequestId > 0){
			sql.append("and candidate.recruitRequest.rcrRecruitRequestId = :rcrRecruitRequestId ");
		}
		if(cddPriority > 0){
			sql.append("and candidate.cddPriority = :cddPriority ");
		}
		if(cddStatus != null){
			sql.append("and candidate.cddStatus = :cddStatus ");
		}
		if(!StringUtils.isBlank(cddPhoneNo)){
			sql.append("and candidate.cddPhoneNo = :cddPhoneNo "); 
		}
		if(!StringUtils.isBlank(cddDegree1)){
			sql.append("and candidate.cddDegree1 = :cddDegree1 ");
		}		
		
		sql.append("order by candidate.cddName asc");	
		return sql.toString();	
	}
	
	/**
	 * Search candidates by info based criteria.
	 *
	 * @param cddName the cdd name
	 * @param recruitRequestName the recruit request name
	 * @param cddEmail the cdd email
	 * @param cddPhoneNo the cdd phone no
	 * @param cddStatus the cdd status
	 * @return the string
	 */
	public static final String searchCandidatesByInfoBasedCriteria(final String cddName, final String recruitRequestName,
			 final String cddEmail, final String cddPhoneNo, final CandidateStatus cddStatus) {
		final StringBuffer sql = new StringBuffer();	
		sql.append(CANDIDATE_QUERY);
		sql.append("where candidate.cddCandidateId <> -1 ");
		if(!StringUtils.isBlank(cddName)){
			sql.append("and lower(candidate.cddName) like lower(concat(:cddName,'%')) ");			
		}	
		if(!StringUtils.isBlank(recruitRequestName)){
			sql.append("and lower(candidate.recruitRequest.recruitRequestName) like lower(concat(:recruitRequestName,'%')) ");
		}		
		if(!StringUtils.isBlank(cddEmail)){
			sql.append("and lower(candidate.cddEmail) like lower(concat(:cddEmail,'%')) ");
		}		
		if(!StringUtils.isBlank(cddPhoneNo)){
			sql.append("and candidate.cddPhoneNo like concat(:cddPhoneNo,'%') ");
		}	
		if(cddStatus != null){
			sql.append("and lower(candidate.cddStatus) like lower(concat(:cddStatus,'%')) ");
		}
		
		sql.append("order by candidate.cddUpdatedDate desc and candidate.cddName asc");	
		return sql.toString();	
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
	 * @return the string
	 */
	public static final String searchCandidates(final Date createdFrom, final Date createdTo, final String createdBy,
			final OrgUnit orgUnit, final RecruitRequest recruitRequest, final String source,
			final CandidateStatus cddStatus, final String handledBy, final String shortListBy, 
			final List<Skill> competencies, final String priority) {
		final StringBuffer sql = new StringBuffer();	
		
		if(RTSUtils.isNotEmpty(competencies)){	
					
			sql.append("select distinct candidateHasSkills.candidate as candidate "); 
			sql.append("from CandidateHasSkill candidateHasSkills ");
			sql.append("where candidateHasSkills.chsCandidateSkillId <> -1 ");				
			sql.append("and candidateHasSkills.skill.id in (");	
			for (final Skill skill : competencies) {
				sql.append(skill.getSklSkillId() + ",");
			}
			sql.deleteCharAt(sql.length()-1);	
			sql.append(") ");			
			
			if(createdFrom != null){
				sql.append("and candidateHasSkills.candidate.cddUpdatedDate >= :createdFrom ");	
			}	
			if(createdTo != null){
				sql.append("and candidateHasSkills.candidate.cddUpdatedDate <= :createdTo ");	
			}
			if(createdBy != null){
				sql.append("and candidateHasSkills.candidate.cddUpdatedBy = :cddUpdatedBy ");	
			}
			if(orgUnit != null){
				sql.append("and candidateHasSkills.candidate.orgUnit = :orgUnit ");
			}	
			if(recruitRequest != null){
				sql.append("and candidateHasSkills.candidate.recruitRequest = :recruitRequest ");
			}
			if(source != null){
				sql.append("and candidateHasSkills.candidate.cddSource = :cddSource ");
			}
			if(cddStatus != null){
				sql.append("and candidateHasSkills.candidate.cddStatus = :cddStatus ");
			}			
			if(handledBy != null){
				sql.append("and candidateHasSkills.candidate.handledBy = :handledBy ");
			}
			if(shortListBy != null){
				sql.append("and candidateHasSkills.candidate.shortListBy = :shortListBy ");
			}				
			if(priority != null){
				sql.append("and candidateHasSkills.candidate.priority = :priority ");
			}	
			
			sql.append("order by candidateHasSkills.candidate.cddName asc");
		} else {
			sql.append(CANDIDATE_QUERY);
			sql.append("where candidate.cddCandidateId <> -1 ");
			if(createdFrom != null){
				sql.append("and candidate.cddUpdatedDate >= :createdFrom ");	
			}	
			if(createdTo != null){
				sql.append("and candidate.cddUpdatedDate <= :createdTo ");	
			}
			if(createdBy != null){
				sql.append("and candidate.cddUpdatedBy = :cddUpdatedBy ");	
			}
			if(orgUnit != null){
				sql.append("and candidate.orgUnit = :orgUnit ");
			}	
			if(recruitRequest != null){
				sql.append("and candidate.recruitRequest = :recruitRequest ");
			}
			if(cddStatus != null){
				sql.append("and candidate.cddStatus = :cddStatus ");
			}	
			if(source != null){
				sql.append("and candidate.cddSource = :cddSource ");
			}
			if(handledBy != null){
				sql.append("and candidate.handledBy = :handledBy ");
			}
			if(shortListBy != null){
				sql.append("and candidate.shortListBy = :shortListBy ");
			}		
			
			if(priority != null){
				sql.append("and candidate.priority = :priority ");
			}	
					
			sql.append("order by candidate.cddName asc");
		}	
		
			
		return sql.toString();	
	}
	
	/**
	 * Gets the candidateby skills.
	 *
	 * @param skills the skills
	 * @param yoeFrom the yoe from
	 * @param yoeTo the yoe to
	 * @return the candidateby skills
	 */
	public static final String getCandidatebySkills(final List<Skill> skills,
			final float yoeFrom, final float yoeTo) {
		final StringBuffer sql = new StringBuffer();	
		sql.append("select distinct candidateHasSkills.candidate as candidate "); 
		sql.append("from CandidateHasSkill candidateHasSkills ");
		sql.append("where candidateHasSkills.chsCandidateSkillId <> -1 ");
		if(yoeFrom > 0){
			sql.append("and candidateHasSkills.candidate.cddYearOfExperience >= :yoeFrom ");
		}
		if(yoeTo > 0){
			sql.append("and candidateHasSkills.candidate.cddYearOfExperience <= :yoeTo ");
		}
		
		if(RTSUtils.isNotEmpty(skills)){
			sql.append("and candidateHasSkills.skill.id in (");	
			for (final Skill skill : skills) {
				sql.append(skill.getSklSkillId() + ",");
			}
			
			final int commaIndex = sql.lastIndexOf(",");
			if (commaIndex != -1) {
				sql.replace(commaIndex, commaIndex + 1, ") ");
			}
		}		
	
		sql.append("order by candidateHasSkills.candidate.cddUpdatedDate desc and candidateHasSkills.candidate.cddName asc");
		return sql.toString();	
	}
	
	/**
	 * Gets the candidateby skills2.
	 *
	 * @param skills the skills
	 * @param yoeFrom the yoe from
	 * @param yoeTo the yoe to
	 * @return the candidateby skills2
	 */
	public static final String getCandidatebySkills2(final List<Skill> skills,
			final float yoeFrom, final float yoeTo) {
		final StringBuffer sql = new StringBuffer();	
		sql.append("select distinct candidateHasSkills.candidate as candidate "); 
		sql.append("from CandidateHasSkill candidateHasSkills ");
		sql.append("where candidateHasSkills.chsCandidateSkillId <> -1 ");
		if(yoeFrom > 0){
			sql.append("and candidateHasSkills.candidate.cddYearOfExperience >= :yoeFrom ");
		}
		if(yoeTo > 0){
			sql.append("and candidateHasSkills.candidate.cddYearOfExperience <= :yoeTo ");
		}
		
		if(RTSUtils.isNotEmpty(skills)){
			sql.append("and candidateHasSkills.skill.id in (");	
			for (final Skill skill : skills) {
				sql.append(skill.getSklSkillId() + ",");
			}
			
			final int commaIndex = sql.lastIndexOf(",");
			if (commaIndex != -1) {
				sql.replace(commaIndex, commaIndex + 1, ") ");
			}
		}		
	
		sql.append("order by candidateHasSkills.candidate.cddUpdatedDate desc and candidateHasSkills.candidate.cddName asc");
		return sql.toString();	
	}
		
	/**
	 * Gets the manager org unit.
	 *
	 * @return the manager org unit
	 */
	public static final String getManagerOrgUnit() {
		final StringBuffer sql = new StringBuffer();	
		sql.append("select distinct orgUnit from OrgUnit orgUnit ");						
		sql.append("where orgUnit.manager.usrUserId = :usrUserId ");
		sql.append("and orgUnit.orgUnitId = :orgUnitId ");		
		return sql.toString();	
	}
	
	/**
	 * Gets the privilege transfer.
	 *
	 * @return the privilege transfer
	 */
	public static final String getPrivilegeTransfer() {
		final StringBuffer sql = new StringBuffer();	
		sql.append("select pt from PrivilegeTransfer pt ");
		sql.append("where pt.userByRotUsrToUserId.usrUserId = :userID ");
		sql.append("and pt.privilege.prvPrivilegeName = :privilege ");
		sql.append("and pt.orgUnit.orgUnitId = :orgUnitId ");
		sql.append("and pt.rotFromDate <= :today ");
		sql.append("and pt.rotToDate >= :today ");
		
		return sql.toString();	
	}
	
	/**
	 * Gets the privilege transfer by privilege.
	 *
	 * @return the privilege transfer by privilege
	 */
	public static final String getPrivilegeTransferByPrivilege() {
		final StringBuffer sql = new StringBuffer();	
		sql.append("select pt from PrivilegeTransfer pt ");
		sql.append("where pt.userByRotUsrToUserId.usrUserId = :usrUserId ");
		sql.append("and pt.privilege.prvPrivilegeName = :privilege ");
		sql.append("and pt.rotFromDate <= :toDate ");
		sql.append("and pt.rotToDate >= :toDate ");
		
		return sql.toString();	
	}
	
	
	/**
	 * Gets the university.
	 *
	 * @return the university
	 */
	public static final String getUniversity(){		
		final StringBuffer sql = new StringBuffer();		
		sql.append("select unit from University unit ");
		sql.append("where unit.name = :name ");
		
		return sql.toString();	
	}
	
	/**
	 * Gets the faculty.
	 *
	 * @return the faculty
	 */
	public static final String getFaculty(){		
		final StringBuffer sql = new StringBuffer();		
		sql.append("select faculty from Faculty faculty ");
		sql.append("where faculty.name = :name ");
		
		return sql.toString();	
	}
	
	/**
	 * Gets the skill requirement.
	 *
	 * @return the skill requirement
	 */
	public static final String getSkillRequirement(){		
		final StringBuffer sql = new StringBuffer();		
		sql.append("select sr from SkillRequirement sr ");
		sql.append("where SR.recruitRequest.rcrRecruitRequestId = :rcrRecruitRequestId ");
		
		return sql.toString();	
	}
	
	/**
	 * Gets the interview schedule by candidate id.
	 *
	 * @return the interview schedule by candidate id
	 */
	public static String getInterviewScheduleByCandidateId(){ 
		final StringBuffer sql = new StringBuffer();	
		sql.append("select interview from InterviewSchedule interview ");
		sql.append("where interview.candidate.cddCandidateId = :cddCandidateId");
		return sql.toString();	
	}
	
	/**
	 * Gets the candidate by org unit for interview schedule.
	 *
	 * @return the candidate by org unit for interview schedule
	 */
	public static String getCandidateByOrgUnitForInterviewSchedule(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select candidate from Candidate candidate ");
		sql.append("where candidate.recruitRequest.orgUnit.orgUnitId = :orgUnitId");
		sql.append(" and candidate.cddStatus <> 'TECHNICAL_SCHEDULED'");
		sql.append(" and candidate.cddStatus <> 'TECHNICAL_PASS'");
		return sql.toString();
	}
	
	/**
	 * Gets the candidate by list of org unit for interview schedule.
	 *
	 * @param orgUnitList the org unit list
	 * @return the candidate by list of org unit for interview schedule
	 */
	public static String getCandidateByListOfOrgUnitForInterviewSchedule(final List<OrgUnit> orgUnitList){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select candidate from Candidate candidate ");
		sql.append("where candidate.orgUnit.orgUnitId in (");	
		
		final int orgUnitListSize = orgUnitList.size();
		for (int i = 0; i < orgUnitListSize; ++i) {
			sql.append(orgUnitList.get(i).getOrgUnitId());
			if (i == orgUnitList.size() - 1) {
				sql.append(RTSConstants.RIGHT_BRACKET);
			} else {
				sql.append(RTSConstants.COLON);
			}
		}
		
		sql.append(" and candidate.cddStatus <> 'TECHNICAL_SCHEDULED'");
		sql.append(" and candidate.cddStatus <> 'TECHNICAL_PASS'");		
		return sql.toString();
	}
	
	/**
	 * Gets the all candidate for interview schedule list.
	 *
	 * @return the all candidate for interview schedule list
	 */
	public static String getAllCandidateForInterviewScheduleList(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select candidate from Candidate candidate");
		return sql.toString();
	}
	
	/**
	 * Gets the recruit request by org unit for interview schedule list.
	 *
	 * @return the recruit request by org unit for interview schedule list
	 */
	public static String getRecruitRequestByOrgUnitForInterviewScheduleList(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select recruitRequest from RecruitRequest recruitRequest ");
		sql.append("where recruitRequest.orgUnit.orgUnitId = :orgUnitId");
		return sql.toString();
	}
	
	/**
	 * Gets the candidate by id.
	 *
	 * @return the candidate by id
	 */
	public static String getCandidateById(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select candidate from Candidate candidate ");
		sql.append("where candidate.cddCandidateId = :cddCandidateId");
		return sql.toString();
	}
	
	/**
	 * Gets the interview schedule by interview id.
	 *
	 * @return the interview schedule by interview id
	 */
	public static String getInterviewScheduleByInterviewId(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select interview from InterviewSchedule interview ");
		sql.append("where interview.itsInterviewScheduleId = :itsInterviewScheduleId");
		return sql.toString();
	}
	
	/**
	 * Gets the skill by skill name.
	 *
	 * @return the skill by skill name
	 */
	public static String getSkillBySkillName(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select skill from Skill skill ");
		sql.append("where skill.sklName = :sklName");
		return sql.toString();
	}
	
	/**
	 * Find interview assessment templates query.
	 *
	 * @param status the status
	 * @return the string
	 */
	public static String findInterviewAssessmentTemplatesQuery(final char status){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select interviewAssessmentTemplates from InterviewAssessmentTemplates interviewAssessmentTemplates");
		sql.append(" where interviewAssessmentTemplates.appliedForLevel = :appliedForLevel");
		if(status == 'Y'){
			sql.append(" and interviewAssessmentTemplates.active = :status");
		}
		
		return sql.toString();
	}
	
	/**
	 * Gets the HR result by interview schedule id.
	 *
	 * @return the HR result by interview schedule id
	 */
	public static String getHrResultByInterviewScheduleId(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select hrResult from HrResult hrResult ");
		sql.append("where hrResult.interviewSchedule.itsInterviewScheduleId = :itsInterviewScheduleId");
		return sql.toString();
	}
	
	/**
	 * Gets the technical result by interview schedule id.
	 *
	 * @return the technical result by interview schedule id
	 */
	public static String getTechnicalResultByInterviewScheduleId(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select technicalResult from TechnicalResult technicalResult ");
		sql.append("where technicalResult.interviewSchedule.itsInterviewScheduleId = :itsInterviewScheduleId");
		return sql.toString();
	}
	
	/**
	 * Find attribute values.
	 *
	 * @return the string
	 */
	public static String findAttributeValues(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select technicalResultAttributeValue from TechnicalResultAttributeValue technicalResultAttributeValue ");
		sql.append("where technicalResultAttributeValue.interviewScheduleId = :itsInterviewScheduleId ");
		return sql.toString();
	}
	
	/**
	 * Gets the not used attributes.
	 *
	 * @return the not used attributes
	 */
	public static String getNotUsedAttributes(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select technicalResultLineAttribute from TechnicalResultLineAttribute technicalResultLineAttribute ");
		sql.append("where technicalResultLineAttribute.technicalResultLine is null ");	
		return sql.toString();
	}
	
	/**
	 * Gets the not used lines.
	 *
	 * @return the not used lines
	 */
	public static String getNotUsedLines(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select technicalResultLine from TechnicalResultLine technicalResultLine ");
		sql.append("where technicalResultLine.technicalResultGroup is null ");	
		return sql.toString();
	}
	
	/**
	 * Gets the not used groups.
	 *
	 * @return the not used groups
	 */
	public static String getNotUsedGroups(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select technicalResultGroup from TechnicalResultGroup technicalResultGroup ");
		sql.append("where technicalResultGroup.interviewAssessmentTemplates is null ");	
		return sql.toString();
	}	
	
	/**
	 * Gets the valid date current date.
	 *
	 * @return the valid date current date
	 */
	public static String getValidDateCurrentDate(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select recruitRequest from RecruitRequest recruitRequest ");
		sql.append("where recruitRequest.status != :status ");	
		//sql.append("and recruitRequest.validDate  <  CURDATE()");
		sql.append("and recruitRequest.validDate  <  :validDate");
		return sql.toString();
	}	
	
	
	/**
	 * Gets the user has roles.
	 *
	 * @return the user has roles
	 */	
	/*public static String getUserHasRoles(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select user from User user, UserHasUserRole uhr ");
		sql.append("where user.usrUserId = uhr.user.usrUserId ");	
		sql.append("and uhr.uhrUserHasRoleId = :uhrUserHasRoleId ");
		sql.append("and user.status = 1 ");
		sql.append("and user.approved = 1");
		return sql.toString();
	}*/
	
	public static String getUserHasRoles(){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select user from User user, UserHasUserRole uhr ");
		sql.append("where user.usrUserId = uhr.user.usrUserId ");	
		sql.append("and uhr.userRole.usrUserRoleId = :usrUserRoleId ");
		sql.append("and user.status = 1 ");
		sql.append("and user.approved = 1 ");
		sql.append("order by user.usrFullName asc");
		return sql.toString();
	}
	
	/**
	 * Search interview schedules by interviewer.
	 *
	 * @param usrUserName the usr user name
	 * @param candidateName the candidate name
	 * @param recruitRequestName the recruit request name
	 * @param fromDate the from date
	 * @param toDate the to date
	 * @param technicalInterviewStatus the technical interview status
	 * @param hrInterviewStatus the hr interview status
	 * @return the string
	 */
	public static String searchInterviewSchedulesByInterviewer(final String usrUserName, final String candidateName,
			final String recruitRequestName, final Date fromDate, final Date toDate,
			final String technicalInterviewStatus, final String hrInterviewStatus){
		final StringBuffer sql = new StringBuffer();	
		sql.append("select distinct userHasIS.interviewSchedule as interviewSchedule "); 
		sql.append("from UserHasInterviewSchedule userHasIS ");
		sql.append("where userHasIS.uisUserScheduleId <> -1 ");
		
		if (!StringUtils.isBlank(usrUserName)) {
			sql.append("and lower(userHasIS.user.usrUserName) like lower(concat(:usrUserName,'%')) ");
		}		
		if (!StringUtils.isBlank(candidateName)) {
			sql.append("and lower(userHasIS.interviewSchedule.candidate.cddName) like lower(concat(:cddName,'%')) ");
		}
		if (!StringUtils.isBlank(recruitRequestName)) {
			sql.append("and lower(userHasIS.interviewSchedule.recruitRequest.recruitRequestName) like lower(concat(:recruitRequestName,'%')) ");
		}
		if (fromDate != null) {
			sql.append("and userHasIS.interviewSchedule.itsTechnicalInterviewTime >= :fromDate ");
		}
		if (toDate != null) {
			sql.append("and userHasIS.interviewSchedule.itsTechnicalInterviewTime <= :toDate ");
		}
		if (!StringUtils.isBlank(technicalInterviewStatus)) {
			sql.append("and userHasIS.interviewSchedule.itsTechnicalStatus = :itsTechnicalStatus ");
		}
		if (!StringUtils.isBlank(hrInterviewStatus)) {
			sql.append("and userHasIS.interviewSchedule.itsHrStatus = :itsHrStatus ");
		}
		sql.append("order by userHasIS.interviewSchedule.itsTechnicalInterviewTime desc");
		return sql.toString();	
	}
	
}