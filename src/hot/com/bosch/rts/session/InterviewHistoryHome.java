package com.bosch.rts.session;

import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import com.bosch.rts.entity.Candidate;
import com.bosch.rts.entity.InterviewHistory;
import com.bosch.rts.entity.RecruitRequest;

/**
 * The Class InterviewHistoryHome.
 */
@Name("interviewHistoryHome")
public class InterviewHistoryHome extends EntityHome<InterviewHistory> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9055792649671212307L;
	
	/** The candidate home. */
	@In(create = true)
	CandidateHome candidateHome;
	
	/** The recruit request home. */
	@In(create = true)
	RecruitRequestHome recruitRequestHome;

	/**
	 * Sets the interview history ith interview history id.
	 *
	 * @param id the new interview history ith interview history id
	 */
	public void setInterviewHistoryIthInterviewHistoryId(Integer id) {
		setId(id);
	}

	/**
	 * Gets the interview history ith interview history id.
	 *
	 * @return the interview history ith interview history id
	 */
	public Integer getInterviewHistoryIthInterviewHistoryId() {
		return (Integer) getId();
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#createInstance()
	 */
	@Override
	protected InterviewHistory createInstance() {
		InterviewHistory interviewHistory = new InterviewHistory();
		return interviewHistory;
	}

	/**
	 * Load.
	 */
	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	/**
	 * Wire.
	 */
	public void wire() {
		getInstance();
		Candidate candidate = candidateHome.getDefinedInstance();
		if (candidate != null) {
			getInstance().setCandidate(candidate);
		}
		RecruitRequest recruitRequest = recruitRequestHome.getDefinedInstance();
		if (recruitRequest != null) {
			getInstance().setRecruitRequest(recruitRequest);
		}
	}

	/**
	 * Checks if is wired.
	 *
	 * @return true, if is wired
	 */
	public boolean isWired() {
		return true;
	}

	/**
	 * Gets the defined instance.
	 *
	 * @return the defined instance
	 */
	public InterviewHistory getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}
	
	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#setInstance(java.lang.Object)
	 */
	@Override
	public void setInstance(InterviewHistory instance) {
		super.setInstance(instance);
	} 
	
	/**
	 * Find interview history.
	 *
	 * @param candidateID the candidate id
	 * @param recruitRequestID the recruit request id
	 * @param status1 the status1
	 * @param status2 the status2
	 * @return the interview history
	 */
	public InterviewHistory findInterviewHistory(int candidateID, int recruitRequestID, String status1, String status2)
	{
		InterviewHistory interviewHistory = null;
		String queryStr = "select interviewHistory from InterviewHistory interviewHistory where "
			+ "interviewHistory.candidate.cddCandidateId = :candidateID"
			+ " and interviewHistory.recruitRequest.rcrRecruitRequestId = :recruitRequestID"
			+ " and ( interviewHistory.ithStatus= :status1 or interviewHistory.ithStatus= :status2)"; 				
		Query query = getEntityManager().createQuery(queryStr);		
		query.setParameter("candidateID", candidateID);
		query.setParameter("recruitRequestID", recruitRequestID);
		query.setParameter("status1", status1);
		query.setParameter("status2", status2);
		
		try {
			interviewHistory = (InterviewHistory) query.getSingleResult();	
		} catch (Exception e) {
			return null;
		}			
		return interviewHistory;
	}
	
	/**
	 * Find interview history.
	 *
	 * @param candidateID the candidate id
	 * @param status1 the status1
	 * @param status2 the status2
	 * @return the interview history
	 */
	public InterviewHistory findInterviewHistory(int candidateID, String status1, String status2)
	{
		InterviewHistory interviewHistory = null;
		String queryStr = "select interviewHistory from InterviewHistory interviewHistory where "
			+ "interviewHistory.candidate.cddCandidateId = :candidateID"
			+ " and ( interviewHistory.ithStatus= :status1 or interviewHistory.ithStatus= :status2)"; 				
		Query query = getEntityManager().createQuery(queryStr);		
		query.setParameter("candidateID", candidateID);
		query.setParameter("status1", status1);
		query.setParameter("status2", status2);
		
		try {
			interviewHistory = (InterviewHistory) query.getSingleResult();	
		} catch (Exception e) {
			return null;
		}			
		return interviewHistory;
	}
	
	/**
	 * Find interview history.
	 *
	 * @param candidateID the candidate id
	 * @param recruitRequestID the recruit request id
	 * @param status the status
	 * @return the interview history
	 */
	public InterviewHistory findInterviewHistory(int candidateID, int recruitRequestID, String status)
	{
		InterviewHistory interviewHistory = null;
		String queryStr = "select interviewHistory from InterviewHistory interviewHistory where "
			+ "interviewHistory.candidate.cddCandidateId = :candidateID"
			+ " and interviewHistory.recruitRequest.rcrRecruitRequestId = :recruitRequestID"
			+ " and interviewHistory.ithStatus= :status"; 				
		Query query = getEntityManager().createQuery(queryStr);		
		query.setParameter("candidateID", candidateID);
		query.setParameter("recruitRequestID", recruitRequestID);
		query.setParameter("status", status);
		
		try {
			interviewHistory = (InterviewHistory) query.getSingleResult();	
		} catch (Exception e) {
			return null;
		}			
		return interviewHistory;
	}	
}
