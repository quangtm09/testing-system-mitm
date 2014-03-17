package com.bosch.rts.session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.bosch.rts.entity.TechnicalResult;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSQueries;

/**
 * The Class TechnicalResultList.
 */
@Name("technicalResultList")
public class TechnicalResultList extends EntityQuery<TechnicalResult> {

	/** serialVersionUID. */
	private static final long serialVersionUID = -2375807147340369062L;

	/** The Constant EJBQL. */
	private static final String EJBQL = "select technicalResult from TechnicalResult technicalResult";

	/** The Constant RESTRICTIONS. */
	private static final String[] RESTRICTIONS = {
			"lower(technicalResult.itrResult10Comment) like lower(concat(#{technicalResultList.technicalResult.itrResult10Comment},'%'))",
			"lower(technicalResult.itrResult10Description) like lower(concat(#{technicalResultList.technicalResult.itrResult10Description},'%'))",
			"lower(technicalResult.itrResult1Comment) like lower(concat(#{technicalResultList.technicalResult.itrResult1Comment},'%'))",
			"lower(technicalResult.itrResult2Comment) like lower(concat(#{technicalResultList.technicalResult.itrResult2Comment},'%'))",
			"lower(technicalResult.itrResult3Comment) like lower(concat(#{technicalResultList.technicalResult.itrResult3Comment},'%'))",
			"lower(technicalResult.itrResult4Comment) like lower(concat(#{technicalResultList.technicalResult.itrResult4Comment},'%'))",
			"lower(technicalResult.itrResult5Comment) like lower(concat(#{technicalResultList.technicalResult.itrResult5Comment},'%'))",
			"lower(technicalResult.itrResult6Comment) like lower(concat(#{technicalResultList.technicalResult.itrResult6Comment},'%'))",
			"lower(technicalResult.itrResult7Comment) like lower(concat(#{technicalResultList.technicalResult.itrResult7Comment},'%'))",
			"lower(technicalResult.itrResult8Comment) like lower(concat(#{technicalResultList.technicalResult.itrResult8Comment},'%'))",
			"lower(technicalResult.itrResult9Comment) like lower(concat(#{technicalResultList.technicalResult.itrResult9Comment},'%'))",
			"lower(technicalResult.itrResult9Description) like lower(concat(#{technicalResultList.technicalResult.itrResult9Description},'%'))",
			"lower(technicalResult.itrResultRemarks) like lower(concat(#{technicalResultList.technicalResult.itrResultRemarks},'%'))",
			"lower(technicalResult.itrResultStatus) like lower(concat(#{technicalResultList.technicalResult.itrResultStatus},'%'))", };

	/** The technical result. */
	private TechnicalResult technicalResult = new TechnicalResult();

	/**
	 * Instantiates a new technical result list.
	 */
	public TechnicalResultList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	/**
	 * Gets the technical result.
	 *
	 * @return the technical result
	 */
	public TechnicalResult getTechnicalResult() {
		return technicalResult;
	}
	
	/**
	 * Gets the technical result remarks.
	 *
	 * @param interviewScheduleId the interview schedule id
	 * @return the technical result remarks
	 */
	@SuppressWarnings("unchecked")
	public String getTechnicalResultRemarks(int interviewScheduleId) {
		String remarks="";
		List<TechnicalResult> resultList = new ArrayList<TechnicalResult>();
		
		String strQuery = "select technicalResult from TechnicalResult technicalResult "
			+ "where technicalResult.interviewSchedule.itsInterviewScheduleId = :interviewScheduleId";
	
		//setEjbql(strQuery);
		//resultList = getResultList();
		Query query = getEntityManager().createQuery(strQuery);
		query.setParameter("interviewScheduleId", interviewScheduleId);
		resultList = query.getResultList();
		
		if( resultList.size()>0 ) {
			TechnicalResult technicalResult = resultList.get(0);
			//remarks = technicalResult.getItrResultRemarks();
		}
		
		return remarks;
	}
	
	/**
	 * Gets the technical results by interview schedule id.
	 *
	 * @param interviewScheduleId the interview schedule id
	 * @return the technical results by interview schedule id
	 */
	@SuppressWarnings("unchecked")
	public List<TechnicalResult> getTechnicalResultsByInterviewScheduleId(int interviewScheduleId){		
		final String queryString = RTSQueries.getTechnicalResultByInterviewScheduleId();
		final Query query = getEntityManager().createQuery(queryString);
		query.setParameter(RTSConstants.ITS_INTERVIEW_SCHEDULE_ID, interviewScheduleId);
		List<TechnicalResult> resultList  = query.getResultList();		
		return resultList;
	}
	
	public TechnicalResult findTechnicalResultById(final int id){
		return getEntityManager().find(TechnicalResult.class, id);
		
	}
	
}
