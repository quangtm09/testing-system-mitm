package com.bosch.rts.session;

import com.bosch.rts.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

/**
 * The Class PrivilegeTransferHome.
 */
@Name("privilegeTransferHome")
public class PrivilegeTransferHome extends EntityHome<PrivilegeTransfer> {

	/** serialVersionUID. */
	private static final long serialVersionUID = -5827892405124529687L;
	
	/** The privilege home. */
	@In(create = true)
	PrivilegeHome privilegeHome;
	
	/** The user home. */
	@In(create = true)
	UserHome userHome;

	/**
	 * Sets the privilege transfer rot privilege transfer id.
	 *
	 * @param id the new privilege transfer rot privilege transfer id
	 */
	public void setPrivilegeTransferRotPrivilegeTransferId(Integer id) {
		setId(id);
	}

	/**
	 * Gets the privilege transfer rot privilege transfer id.
	 *
	 * @return the privilege transfer rot privilege transfer id
	 */
	public Integer getPrivilegeTransferRotPrivilegeTransferId() {
		return (Integer) getId();
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#createInstance()
	 */
	@Override
	protected PrivilegeTransfer createInstance() {
		PrivilegeTransfer privilegeTransfer = new PrivilegeTransfer();
		return privilegeTransfer;
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
		Privilege privilege = privilegeHome.getDefinedInstance();
		if (privilege != null) {
			getInstance().setPrivilege(privilege);
		}
		User userByRotUsrFromUserId = userHome.getDefinedInstance();
		if (userByRotUsrFromUserId != null) {
			getInstance().setUserByRotUsrFromUserId(userByRotUsrFromUserId);
		}
		User userByRotUsrToUserId = userHome.getDefinedInstance();
		if (userByRotUsrToUserId != null) {
			getInstance().setUserByRotUsrToUserId(userByRotUsrToUserId);
		}
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
	public PrivilegeTransfer getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
