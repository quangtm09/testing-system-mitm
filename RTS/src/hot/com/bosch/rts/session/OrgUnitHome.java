package com.bosch.rts.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;
import com.bosch.rts.entity.OrgUnit;

/**
 * The Class OrgUnitHome.
 */
@Name("orgUnitHome")
public class OrgUnitHome extends EntityHome<OrgUnit> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1892747383487695841L;

	/**
	 * Sets the org unit org unit id.
	 *
	 * @param id the new org unit org unit id
	 */
	public void setOrgUnitOrgUnitId(Integer id) {
		setId(id);
	}

	/**
	 * Gets the org unit org unit id.
	 *
	 * @return the org unit org unit id
	 */
	public Integer getOrgUnitOrgUnitId() {
		return (Integer) getId();
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#createInstance()
	 */
	@Override
	protected OrgUnit createInstance() {
		OrgUnit orgUnit = new OrgUnit();
		return orgUnit;
	}

	/**
	 * Load.
	 */
	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	/**
	 * Wire.
	 */
	public void wire() {
		getInstance();
	}

	/**
	 * Checks if is wired.
	 *
	 * @return true, if is wired
	 */
	public boolean isWired() {
		return true;
	}

	/**
	 * Gets the defined instance.
	 *
	 * @return the defined instance
	 */
	public OrgUnit getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
