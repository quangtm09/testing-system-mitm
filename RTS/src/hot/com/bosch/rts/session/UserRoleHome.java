package com.bosch.rts.session;

import com.bosch.rts.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

/**
 * The Class UserRoleHome.
 */
@Name("userRoleHome")
public class UserRoleHome extends EntityHome<UserRole> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 6000049585224386154L;

	/**
	 * Sets the user role usr user role id.
	 *
	 * @param id the new user role usr user role id
	 */
	public void setUserRoleUsrUserRoleId(Integer id) {
		setId(id);
	}

	/**
	 * Gets the user role usr user role id.
	 *
	 * @return the user role usr user role id
	 */
	public Integer getUserRoleUsrUserRoleId() {
		return (Integer) getId();
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#createInstance()
	 */
	@Override
	protected UserRole createInstance() {
		UserRole userRole = new UserRole();
		return userRole;
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
	public UserRole getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
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
	 * Gets the user has user roles.
	 *
	 * @return the user has user roles
	 */
	public List<UserHasUserRole> getUserHasUserRoles() {
		return getInstance() == null ? null : new ArrayList<UserHasUserRole>(
				getInstance().getUserHasUserRoles());
	}

}
