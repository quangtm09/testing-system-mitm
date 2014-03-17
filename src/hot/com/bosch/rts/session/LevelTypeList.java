package com.bosch.rts.session;

import java.util.List;

import javax.persistence.Query;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.bosch.rts.entity.LevelType;
import com.bosch.rts.utilities.RTSQueries;

/**
 */
@Name("levelTypeList")
public class LevelTypeList extends EntityQuery<LevelType> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3891184164646587407L;

	/** The Constant EJBQL. */
	private static final String EJBQL = "select levelType from LevelType levelType";

	
	public LevelTypeList(){
		setEjbql(EJBQL);
	}
	
	@SuppressWarnings("unchecked")
	public List<LevelType> getAppliedForLevels(){
		final String sql = RTSQueries.getActiveLevelTypes();
		final Query query = getEntityManager().createQuery(sql);
		return query.getResultList();	
	}
}
