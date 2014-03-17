package com.bosch.rts.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.bosch.rts.entity.Privilege;

/**
 * The Class PrivilegeList.
 */
@Name("privilegeList")
public class PrivilegeList extends EntityQuery<Privilege> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 9123156718615148732L;

	/** The Constant EJBQL. */
	private static final String EJBQL = "select privilege from Privilege privilege";


	/** The privilege. */
	private Privilege privilege = new Privilege();

	/**
	 * Instantiates a new privilege list.
	 */
	public PrivilegeList() {
		setEjbql(EJBQL);
		setOrder("prvPrivilegeName");
	}			
	
	/**
	 * Gets the privilege.
	 *
	 * @return the privilege
	 */
	public Privilege getPrivilege() {
		return privilege;
	}
}
