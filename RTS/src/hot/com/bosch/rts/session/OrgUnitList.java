package com.bosch.rts.session;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.bosch.rts.entity.OrgUnit;
import com.bosch.rts.entity.User;
import com.bosch.rts.utilities.RTSQueries;
import com.bosch.rts.utilities.RTSUtils;

/**
 * The Class OrgUnitList.
 */
@Name("orgUnitList")
public class OrgUnitList extends EntityQuery<OrgUnit> {
	
	/** serialVersionUID. */
	private static final long serialVersionUID = 4633746776346027824L;

	/** The Constant EJBQL. */
	private static final String EJBQL = "select orgUnit from OrgUnit orgUnit";
	
	/** The Constant EJBQL. */
	private static final String ACTIVE_STATE = " where orgUnit.activeState = 1";

	/** The org unit. */
	private OrgUnit orgUnit = new OrgUnit();

	/**
	 * Instantiates a new org unit list.
	 */
	public OrgUnitList() {
		setEjbql(EJBQL + ACTIVE_STATE);
		setOrder("orgUnit.unitName");
	}

	/**
	 * Gets the org unit.
	 *
	 * @return the org unit
	 */
	public OrgUnit getOrgUnit() {
		return orgUnit;
	}
	
	/**
	 * Gets the org units by name.
	 *
	 * @param orgUnitName the org unit name
	 * @return the org units by name
	 */
	@SuppressWarnings("unchecked")
	public List<OrgUnit> getOrgUnitsByName(String orgUnitName){
		List<OrgUnit> orgUnits = new ArrayList<OrgUnit>();		
		if(orgUnitName != null){
			final String sql = RTSQueries.getOrgUnitsByName();
			final Query query = getEntityManager().createQuery(sql);
			query.setParameter("unitName", orgUnitName);
			orgUnits = query.getResultList();			
		}
		return orgUnits;
	} 
	
	/**
	 * Gets the org units by name.
	 *
	 * @param orgUnitName the org unit name
	 * @return the org units by name
	 */
	@SuppressWarnings("unchecked")
	public OrgUnit getOrgUnitsByUnitName(String orgUnitName, final Boolean activeState){
		
		final String sql = RTSQueries.getOrgUnitsByUnitName();
		final Query query = getEntityManager().createQuery(sql);
		query.setParameter("unitName", orgUnitName);
		query.setParameter("activeState", activeState);
		List<OrgUnit> orgUnits = query.getResultList();			
		
		if(RTSUtils.isNotEmpty(orgUnits)){
			return orgUnits.get(0);
		}
	
		return null;
	} 
	
	/**
	 * Gets the all org units.
	 *
	 * @return the all org units
	 */
	public List<OrgUnit> getAllOrgUnits()
	{
		List<OrgUnit> result = new ArrayList<OrgUnit>();
		setEjbql(EJBQL);
		result = this.getResultList();
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<User> getOrgUnitManagers(final int orgUnitId){
		List<User> managers = new ArrayList<User>();		
		final String sql = "select orgUnit.manager from OrgUnit orgUnit where orgUnit.orgUnitId = :orgUnitId";
		final Query query = getEntityManager().createQuery(sql);
		query.setParameter("orgUnitId", orgUnitId);
		managers = query.getResultList();
		return managers;
	} 
	
	@SuppressWarnings("unchecked")
	public List<OrgUnit> getOrgUnits(final Boolean activeState){
		List<OrgUnit> orgUnits = new ArrayList<OrgUnit>();		
		final String sql = "select orgUnit from OrgUnit orgUnit where activeState = :activeState order by unitName asc";
		final Query query = getEntityManager().createQuery(sql);
		query.setParameter("activeState", activeState);
		orgUnits = query.getResultList();
		return orgUnits;
	} 
	
	@SuppressWarnings("unchecked")
	public List<OrgUnit> getOrgUnitByRole(final String unitName, final boolean activeState){	
		List<OrgUnit> orgUnits = new ArrayList<OrgUnit>();		
		final String sql = RTSQueries.getOrgUnitsByRole();
		final Query query = getEntityManager().createQuery(sql);
		query.setParameter("unitName", unitName);
		query.setParameter("activeState", activeState);
		orgUnits = query.getResultList();
		return orgUnits;
	}
	
}
