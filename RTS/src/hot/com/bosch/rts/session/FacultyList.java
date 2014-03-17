package com.bosch.rts.session;

import java.util.List;

import javax.persistence.Query;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.bosch.rts.entity.Faculty;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSQueries;

/**
 * The Class FacultyList.
 */
@Name("facultyList")
public class FacultyList extends EntityQuery<Faculty> {
	
	/** serialVersionUID. */
	private static final long serialVersionUID = 3985799668939746455L;

	/** The Constant EJB. */
	private static final String EJB = "select faculty from Faculty faculty";
	
	/** The Constant ASC_SORT. */
	private static final String ASC_SORT = "fct_faculty_name";

	/**
	 * Instantiates a new faculty list.
	 */
	public FacultyList() {
		setEjbql(EJB);
		setOrder(ASC_SORT);
	}
		
	/**
	 * Gets the faculty by name.
	 *
	 * @param facultyName the faculty name
	 * @return the faculty by name
	 */
	@SuppressWarnings("unchecked")
	public List<Faculty> getFacultyByName(String facultyName){		
		final String queryString = RTSQueries.getFaculty();
		final Query query = getEntityManager().createQuery(queryString);
		query.setParameter(RTSConstants.NAME, facultyName);
		List<Faculty> resultList = query.getResultList();		
		return resultList;
	}
}
