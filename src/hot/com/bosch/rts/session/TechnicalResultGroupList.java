package com.bosch.rts.session;

import java.util.List;

import javax.persistence.Query;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.bosch.rts.entity.TechnicalResultGroup;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSQueries;

/**
 * The Class TechnicalResultGroupList.
 */
@Name("technicalResultGroupList")
public class TechnicalResultGroupList extends EntityQuery<TechnicalResultGroup> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 7061743813855997815L;

	/** The Constant EJBQL. */
	private static final String EJBQL = "select technicalResultGroup from TechnicalResultGroup technicalResultGroup";

	/** The technical result group. */
	private TechnicalResultGroup technicalResultGroup = new TechnicalResultGroup();

	/**
	 * Instantiates a new technical result group list.
	 */
	public TechnicalResultGroupList() {
		setEjbql(EJBQL);
		setOrder(RTSConstants.NAME);
	}

	/**
	 * Gets the technical result group.
	 *
	 * @return the technical result group
	 */
	public TechnicalResultGroup getTechnicalResultGroup() {
		return technicalResultGroup;
	}	
	
	/**
	 * Gets the not used groups.
	 *
	 * @return the not used groups
	 */
	@SuppressWarnings("unchecked")
	public List<TechnicalResultGroup> getNotUsedGroups(){
		final String sql = RTSQueries.getNotUsedGroups();
		final Query query = getEntityManager().createQuery(sql);
		final List<TechnicalResultGroup> groups = query.getResultList();
		return groups;
	}
	
}
