package com.bosch.rts.session;

import java.util.List;

import javax.persistence.Query;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.bosch.rts.entity.University;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSQueries;

/**
 * The Class UniversityList.
 */
@Name("universityList")
public class UniversityList extends EntityQuery<University> {
	
	/** serialVersionUID. */
	private static final long serialVersionUID = -1354712625467411693L;
	
	/** The Constant EJB. */
	private static final String EJB = "select university from University university";
	
	/** The Constant ASC_SORT. */
	private static final String ASC_SORT = "uns_name";

	/**
	 * Instantiates a new university list.
	 */
	public UniversityList() {
		setEjbql(EJB);
		setOrder(ASC_SORT);
	}
	
	/**
	 * Gets the university by name.
	 *
	 * @param universityName the university name
	 * @return the university by name
	 */
	@SuppressWarnings("unchecked")
	public List<University> getUniversityByName(String universityName){		
		final String queryString = RTSQueries.getUniversity();
		final Query query = getEntityManager().createQuery(queryString);
		query.setParameter(RTSConstants.NAME, universityName);
		List<University> resultList = query.getResultList();		
		return resultList;
	}
}
