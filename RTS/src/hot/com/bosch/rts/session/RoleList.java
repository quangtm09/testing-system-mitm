package com.bosch.rts.session;

import java.util.Arrays;

import org.jboss.seam.framework.EntityQuery;

import com.bosch.rts.entity.UserRole;

/**
 * The Class RoleList.
 */
public class RoleList extends EntityQuery<UserRole> {
	
	/** serialVersionUID. */
	private static final long serialVersionUID = 3548291517188852707L;

	/** The Constant EJBQL. */
	private static final String EJBQL = "select userRole from UserRole userRole";

	/** The Constant RESTRICTIONS. */
	private static final String[] RESTRICTIONS = {
			"lower(user.usrEmail) like lower(concat(#{userList.user.usrEmail},'%'))",
			"lower(user.usrFullName) like lower(concat(#{userList.user.usrFullName},'%'))",
			"lower(user.usrUserName) like lower(concat(#{userList.user.usrUserName},'%'))", };


	/**
	 * Instantiates a new role list.
	 */
	public RoleList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

}
