package com.bosch.rts.session;

import java.util.List;

import javax.persistence.Query;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.bosch.rts.entity.InterviewAssessmentTemplates;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSQueries;
import com.bosch.rts.utilities.RTSUtils;

/**
 * The Class InterviewAssessmentTemplatesList.
 */
@Name("interviewAssessmentTemplatesList")
public class InterviewAssessmentTemplatesList extends EntityQuery<InterviewAssessmentTemplates> {

	/** serialVersionUID. */
	private static final long serialVersionUID = -2454016123374994353L;

	/** The Constant EJBQL. */
	private static final String EJBQL = "select interviewAssessmentTemplates from InterviewAssessmentTemplates interviewAssessmentTemplates";
	

	/** The interview assessment templates. */
	private InterviewAssessmentTemplates interviewAssessmentTemplates = new InterviewAssessmentTemplates();

	/**
	 * Instantiates a new interview assessment templates list.
	 */
	public InterviewAssessmentTemplatesList() {
		setEjbql(EJBQL);
		setOrder(RTSConstants.NAME);
	}

	/**
	 * Gets the interview assessment templates.
	 *
	 * @return the interview assessment templates
	 */
	public InterviewAssessmentTemplates getInterviewAssessmentTemplates() {
		return interviewAssessmentTemplates;
	}
	
	/**
	 * Find interview assessment templates.
	 *
	 * @param level the level
	 * @param status the status, if status equals to 'Y', load new template.
	 * Otherwise, load current template
	 * @return the interview assessment templates
	 */
	@SuppressWarnings("unchecked")
	public InterviewAssessmentTemplates findInterviewAssessmentTemplates(final int level, final char status){
		final String sql = RTSQueries.findInterviewAssessmentTemplatesQuery(status);
		final Query query = getEntityManager().createQuery(sql);
		query.setParameter(RTSConstants.APPLIED_FOR_LEVEL, level);
		if(status == 'Y'){
			query.setParameter(RTSConstants.STATUS, status);
		}
		final List<InterviewAssessmentTemplates> list = query.getResultList();
		if(RTSUtils.isNotEmpty(list)){
			return list.get(0);
		}
		
		return null;
	}
	
}
