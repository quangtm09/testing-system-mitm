package com.bosch.rts.session;

import java.util.List;

import javax.persistence.Query;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.bosch.rts.entity.HrResult;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSQueries;

/**
 * The Class HrResultList.
 */
@Name("hrResultList")
public class HrResultList extends EntityQuery<HrResult>
{

	/** serialVersionUID. */
	private static final long serialVersionUID = 6586725107878220624L;

	/** The Constant EJBQL. */
	private static final String EJBQL = "select hrResult from HrResult hrResult";

	/**
	 * Instantiates a new hr result list.
	 */
	public HrResultList()
	{
		setEjbql(EJBQL);
		setMaxResults(25);
	}
	
	/**
	 * Gets the hr results by interview schedule id.
	 *
	 * @param interviewScheduleId the interview schedule id
	 * @return the hr results by interview schedule id
	 */
	@SuppressWarnings("unchecked")
	public List<HrResult> getHrResultsByInterviewScheduleId(int interviewScheduleId){		
		final String queryString = RTSQueries.getHrResultByInterviewScheduleId();
		final Query query = getEntityManager().createQuery(queryString);
		query.setParameter(RTSConstants.ITS_INTERVIEW_SCHEDULE_ID, interviewScheduleId);
		List<HrResult> resultList  = query.getResultList();		
		return resultList;
	}
}
