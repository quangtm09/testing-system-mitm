package com.bosch.rts.session;

import com.bosch.rts.entity.*;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

/**
 * The Class PrivilegeHome.
 */
@Name("privilegeHome")
public class PrivilegeHome extends EntityHome<Privilege> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7136207333188675803L;

	/**
	 * Sets the privilege prv privilege id.
	 *
	 * @param id the new privilege prv privilege id
	 */
	public void setPrivilegePrvPrivilegeId(Integer id) {
		setId(id);
	}

	/**
	 * Gets the privilege prv privilege id.
	 *
	 * @return the privilege prv privilege id
	 */
	public Integer getPrivilegePrvPrivilegeId() {
		return (Integer) getId();
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#createInstance()
	 */
	@Override
	protected Privilege createInstance() {
		Privilege privilege = new Privilege();
		return privilege;
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
	public Privilege getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	/**
	 * Gets the privilege transfers.
	 *
	 * @return the privilege transfers
	 */
	public List<PrivilegeTransfer> getPrivilegeTransfers() {
		return getInstance() == null ? null : new ArrayList<PrivilegeTransfer>(
				getInstance().getPrivilegeTransfers());
	}

	/**
	 * Gets the role has privileges.
	 *
	 * @return the role has privileges
	 */
	public List<RoleHasPrivilege> getRoleHasPrivileges() {
		return getInstance() == null ? null : new ArrayList<RoleHasPrivilege>(
				getInstance().getRoleHasPrivileges());
	}

	/**
	 * Find privilege by name.
	 *
	 * @param name the name
	 * @return the privilege
	 */
	public Privilege findPrivilegeByName(String name) {
		Privilege pvl = null;
		String queryStr = "select privilege from Privilege privilege where "
				+ "privilege.prvPrivilegeName = :pvlName";
		Query query = getEntityManager().createQuery(queryStr);
		query.setParameter("pvlName", name);
		List<Privilege> pvlList = null;
		pvlList = query.getResultList();
		if (pvlList != null && pvlList.size() > 0) {
			pvl = pvlList.get(0);
		}
		return pvl;
	}
}
