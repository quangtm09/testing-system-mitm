package com.bosch.rts.session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.bosch.rts.entity.User;
import com.bosch.rts.utilities.RTSQueries;

/**
 * The Class UserList.
 */
@Name("userList")
public class UserList extends EntityQuery<User> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 6075042610958164565L;

	/** The Constant EJBQL. */
	private static final String EJBQL = "select user from User user";
	
	/** The Constant ASC_SORT. */
	private static final String ASC_SORT = "usrFullName";

	/** The Constant RESTRICTIONS. */
	private static final String[] RESTRICTIONS = {
			"lower(user.usrEmail) like lower(concat(#{userList.user.usrEmail},'%'))",
			"lower(user.usrFullName) like lower(concat(#{userList.user.usrFullName},'%'))",
			"lower(user.usrUserName) like lower(concat(#{userList.user.usrUserName},'%'))",			
			};

	/** The user. */
	private User user = new User();

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Instantiates a new user list.
	 */
	public UserList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setOrder(ASC_SORT);
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	
	public List<User> getUserHasRoles(final int roleId){
		List<User> users = new ArrayList<User>();
		final String sql = RTSQueries.getUserHasRoles();
		final Query query = getEntityManager().createQuery(sql);
		query.setParameter("usrUserRoleId", roleId);
		users = query.getResultList();
		return users;
	}

}
