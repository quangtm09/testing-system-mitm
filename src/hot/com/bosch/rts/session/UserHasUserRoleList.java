package com.bosch.rts.session;

import java.util.Arrays;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.bosch.rts.entity.UserHasUserRole;

/**
 * The Class UserHasUserRoleList.
 */
@Name("userHasUserRoleList")
public class UserHasUserRoleList extends EntityQuery<UserHasUserRole> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3169810171165401679L;

	/** The Constant EJBQL. */
	private static final String EJBQL = "select userHasUserRole from UserHasUserRole userHasUserRole";

	/** The Constant RESTRICTIONS. */
	private static final String[] RESTRICTIONS = {
			"userHasUserRole.user.usrUserId = #{userHasUserRoleList.userHaseUserRole.user.usrUserId}",
			"userHasUserRole.userRole.usrUserRoleId = #{userHasUserRoleList.userHaseUserRole.userRole.usrUserRoleId}" };

	/** The user hase user role. */
	private UserHasUserRole userHaseUserRole = new UserHasUserRole();

	/**
	 * Gets the user hase user role.
	 *
	 * @return the user hase user role
	 */
	public UserHasUserRole getUserHaseUserRole() {
		return userHaseUserRole;
	}

	/**
	 * Clear argrument.
	 */
	public void clearArgrument() {
		userHaseUserRole = new UserHasUserRole();
	}

	/**
	 * Instantiates a new user has user role list.
	 */
	public UserHasUserRoleList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

}
