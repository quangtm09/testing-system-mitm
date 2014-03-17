package com.bosch.rts.session;

import java.util.List;

import javax.persistence.Query;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.bosch.rts.entity.SkillRequirement;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSQueries;

/**
 * The Class SkillRequirementList.
 */
@Name("skillRequirementList")
public class SkillRequirementList extends EntityQuery<SkillRequirement> {
	
	/** serialVersionUID. */
	private static final long serialVersionUID = 3435287410988402032L;
	
	/** The Constant EJB. */
	private static final String EJB = "select sr from SkillRequirement sr";
	
	/** The Constant ASC_SORT. */
	private static final String ASC_SORT = "skr_skill_requirement_id";

	/**
	 * Instantiates a new skill requirement list.
	 */
	public SkillRequirementList() {
		setEjbql(EJB);
		setOrder(ASC_SORT);
	}
	
	/**
	 * Gets the skill requirement.
	 *
	 * @param RRId the rR id
	 * @return the skill requirement
	 */
	@SuppressWarnings("unchecked")
	public List<SkillRequirement> getSkillRequirement(int RRId) {		
		final String sql = RTSQueries.getSkillRequirement();
		final Query query = getEntityManager().createQuery(sql);		
		query.setParameter(RTSConstants.RECRUIT_REQUEST_ID, RRId);
		List<SkillRequirement> recruitRequests =  query.getResultList();		
		return recruitRequests;
	}
}
