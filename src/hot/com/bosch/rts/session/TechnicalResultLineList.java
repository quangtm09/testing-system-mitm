package com.bosch.rts.session;

import java.util.List;

import javax.persistence.Query;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.bosch.rts.entity.TechnicalResultLine;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSQueries;

/**
 * The Class TechnicalResultLineList.
 */
@Name("technicalResultLineList")
public class TechnicalResultLineList extends EntityQuery<TechnicalResultLine> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 8144330844169724214L;

	/** The Constant EJBQL. */
	private static final String EJBQL = "select technicalResultLine from TechnicalResultLine technicalResultLine";

	/** The technical result line. */
	private TechnicalResultLine technicalResultLine = new TechnicalResultLine();

	/**
	 * Instantiates a new technical result line list.
	 */
	public TechnicalResultLineList() {
		setEjbql(EJBQL);
		setOrder(RTSConstants.NAME);
	}

	/**
	 * Gets the technical result line.
	 *
	 * @return the technical result line
	 */
	public TechnicalResultLine getTechnicalResultLine() {
		return technicalResultLine;
	}
	
	/**
	 * Gets the not used lines.
	 *
	 * @return the not used lines
	 */
	@SuppressWarnings("unchecked")
	public List<TechnicalResultLine> getNotUsedLines(){
		final String sql = RTSQueries.getNotUsedLines();
		final Query query = getEntityManager().createQuery(sql);
		final List<TechnicalResultLine> lines = query.getResultList();
		return lines;
	}
}
