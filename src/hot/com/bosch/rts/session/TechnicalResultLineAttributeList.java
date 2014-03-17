package com.bosch.rts.session;

import java.util.List;

import javax.persistence.Query;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.bosch.rts.entity.TechnicalResultLineAttribute;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSQueries;

/**
 * The Class TechnicalResultLineAttributeList.
 */
@Name("technicalResultLineAttributeList")
public class TechnicalResultLineAttributeList extends EntityQuery<TechnicalResultLineAttribute> {

	/** serialVersionUID. */
	private static final long serialVersionUID = -4183370294026223778L;

	/** The Constant EJBQL. */
	private static final String EJBQL = "select technicalResultLineAttribute from TechnicalResultLineAttribute technicalResultLineAttribute";	
	
	/** The technical result line attribute. */
	private TechnicalResultLineAttribute technicalResultLineAttribute = new TechnicalResultLineAttribute();

	/**
	 * Instantiates a new technical result line attribute list.
	 */
	public TechnicalResultLineAttributeList() {
		setEjbql(EJBQL);
		setOrder(RTSConstants.NAME);
	}

	/**
	 * Gets the technical result line attribute.
	 *
	 * @return the technical result line attribute
	 */
	public TechnicalResultLineAttribute getTechnicalResultLineAttribute() {
		return technicalResultLineAttribute;
	}

	/**
	 * Gets the not used attributes.
	 *
	 * @return the not used attributes
	 */
	@SuppressWarnings("unchecked")
	public List<TechnicalResultLineAttribute> getNotUsedAttributes(){
		final String sql = RTSQueries.getNotUsedAttributes();
		final Query query = getEntityManager().createQuery(sql);
		final List<TechnicalResultLineAttribute> attributes = query.getResultList();
		return attributes;
	}
}
