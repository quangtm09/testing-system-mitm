package com.bosch.rts.session;

import java.util.List;

import javax.persistence.Query;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.bosch.rts.entity.User;
import com.bosch.rts.entity.UserRole;

/**
 * The Class UserRoleList.
 */
@Name("userRoleList")
public class UserRoleList extends EntityQuery<UserRole> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 8134932089074702849L;

	/** The Constant EJBQL. */
	private static final String EJBQL = "select userRole from UserRole userRole inner join userRole.userHasUserRoles userHasUserRoles";
	
	/** The Constant HQL. */
	private static final String HQL = "select userRole from UserRole userRole";

	/** The user role. */
	private UserRole userRole = new UserRole();
	
	/** The user. */
	private User user = new User();

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Clear agrument.
	 */
	public void clearAgrument() {
		user = new User();
		userRole = new UserRole();
	}

	/**
	 * Instantiates a new user role list.
	 */
	public UserRoleList() {
		setEjbql(EJBQL);
	}

	/**
	 * Gets the user role.
	 *
	 * @return the user role
	 */
	public UserRole getUserRole() {
		return userRole;
	}
	
	/**
	 * Gets the user roles.
	 *
	 * @return the user roles
	 */
	@SuppressWarnings("unchecked")
	public List<UserRole> getUserRoles(){
		final Query query = getEntityManager().createQuery(HQL);
		final List<UserRole> userRoles = query.getResultList();
		return userRoles;
	}

}
