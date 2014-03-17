/**
 * UserBLO.java
 */
package com.bosch.rts.blo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.contexts.Contexts;

import com.bosch.rts.entity.OrgUnit;
import com.bosch.rts.entity.Privilege;
import com.bosch.rts.entity.RoleHasPrivilege;
import com.bosch.rts.entity.User;
import com.bosch.rts.entity.UserRole;
import com.bosch.rts.session.UserHome;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSQueries;
import com.bosch.rts.utilities.RTSUtils;

/**
 * The Class UserBLO.
 */
@Name("userBLO")
public class UserBLO implements Serializable {
	
	/** serialVersionUID. */
	private static final long serialVersionUID = 699546695412909204L;
	
	/** The user home. */
	@In(create = true)
	private UserHome userHome;
	
	/** The Constant LOGGED_IN_USER. */
	private static final String LOGGED_IN_USER = "loggedInUser";
	
	/** The entity manager. */
	@In
	private EntityManager entityManager;

	
		
	/**
	 * User has privilege.
	 *
	 * @param username the username
	 * @param privilege the privilege
	 * @param orgUnitId the org unit id
	 * @return true, if successful
	 */
	public boolean userHasPrivilege(final String username, final String privilege, final int orgUnitId) {
		final User loggedInUser = (User) Contexts.getSessionContext().get(LOGGED_IN_USER);
		if(loggedInUser == null){
			return false;
		}
		
		List<UserRole> roles = null;
		int userId = -1;
		if(loggedInUser.getUsrUserName().equalsIgnoreCase(username)){
			roles = loggedInUser.getUserRoles();
			userId = loggedInUser.getUsrUserId();
		} else {
			final User user = userHome.findUserByUsername(username);
			roles = user.getUserRoles();
			userId = user.getUsrUserId();
		}	

		
		if (roles != null && !roles.isEmpty()) {
			for(final UserRole userRole : roles){
				final Set<RoleHasPrivilege> privileges = userRole.getRoleHasPrivileges();
				for(final RoleHasPrivilege roleHasPrivilege : privileges){
					final String prvPrivilegeName = roleHasPrivilege.getPrivilege().getPrvPrivilegeName();
					if(prvPrivilegeName.equals(privilege)){
						return true;
					}
				}
			}		
		}
		
		if(orgUnitId > 0){
			final String queryStr = RTSQueries.getManagerOrgUnit();
			final Query query = entityManager.createQuery(queryStr);
			query.setParameter(RTSConstants.USR_USER_ID, userId);
			query.setParameter(RTSConstants.ORG_UNIT_ID, orgUnitId);
			List<OrgUnit> orgUnitList = query.getResultList();
			if (RTSUtils.isNotEmpty(orgUnitList)) {
				return true;
			}
			
		}
		
		
		/*User user = userHome.findUserByUsername(username);
		if (user != null) {
			// get roles
			Set<UserHasUserRole> roles = user.getUserHasUserRoles();
			if (RTSUtils.isNotEmpty(roles)) {
				// check registered OrgUnit
				if (user.getOrgUnit().getOrgUnitId() == orgUnitId) {
					UserHasUserRole userRoles = (UserHasUserRole) roles.toArray()[0];
					UserRole role = userRoles.getUserRole();
					// get privileges
					Set<RoleHasPrivilege> roleHasPrivileges = role.getRoleHasPrivileges();
					if (RTSUtils.isNotEmpty(roleHasPrivileges)) {
						Object[] privileges = roleHasPrivileges.toArray();
						
						for(Object privilegeObj : privileges){
							RoleHasPrivilege roleHasPrivilege = (RoleHasPrivilege) privilegeObj;
							Privilege pvl = roleHasPrivilege.getPrivilege();
							if (pvl.getPrvPrivilegeName().toLowerCase()
									.compareTo(privilege.toLowerCase()) == 0) {
								return true;
							}
						}						
					}
				}

				// manager of Org Unit
				final String queryStr = RTSQueries.getManagerOrgUnit();
				final Query query = entityManager.createQuery(queryStr);
				query.setParameter(RTSConstants.USR_USER_ID, user.getUsrUserId());
				query.setParameter(RTSConstants.ORG_UNIT_ID, orgUnitId);
				List<OrgUnit> orgUnitList = query.getResultList();
				if (RTSUtils.isNotEmpty(orgUnitList)) {
					return true;
				}
			}

			Calendar today = Calendar.getInstance();
			today.setTime(new Date());
			today.set(Calendar.AM_PM, Calendar.AM);
			today.set(Calendar.HOUR, 0);
			today.set(Calendar.MINUTE, 0);
			today.set(Calendar.SECOND, 0);
			today.set(Calendar.MILLISECOND, 0);

			String queryStr = RTSQueries.getPrivilegeTransfer();
			final Query query = entityManager.createQuery(queryStr);
			query.setParameter(RTSConstants.USER_ID, user.getUsrUserId());
			query.setParameter(RTSConstants.SQL_PRIVILEGE, privilege);
			query.setParameter(RTSConstants.ORG_UNIT_ID, orgUnitId);
			query.setParameter(RTSConstants.TODAY, today.getTime());
			List<PrivilegeTransfer> ptList = query.getResultList();
			return RTSUtils.isNotEmpty(ptList) ? true : false;			
		}*/
		return false;
	}
	
	/**
	 * Gets the privilege org unit set.
	 *
	 * @param username the username
	 * @param privilege the privilege
	 * @return the privilege org unit set
	 */
	public Set<OrgUnit> getPrivilegeOrgUnitSet(String username,
			String privilege) {
		// get user
		return new HashSet<OrgUnit>(getPrivilegeOrgUnitList(username,
				privilege));
	}
	
	/**
	 * Gets the privilege org unit list.
	 *
	 * @param username the username
	 * @param privilege the privilege
	 * @return the privilege org unit list
	 */
	@SuppressWarnings("unchecked")
	public List<OrgUnit> getPrivilegeOrgUnitList(String username, String privilege) {
		// get user
		User user = userHome.findUserByUsername(username);
		if (user != null) {
			// manager of Org Unit
			StringBuilder sql = new StringBuilder();
			sql.append("select distinct orgUnit from OrgUnit orgUnit ");
			sql.append("where orgUnit.manager.usrUserId = :usrUserId");
			final Query query = entityManager.createQuery(sql.toString());
			query.setParameter(RTSConstants.USR_USER_ID, user.getUsrUserId());			
			List<OrgUnit> orgUnitList = query.getResultList();
	
			return orgUnitList;

		}

		return new ArrayList<OrgUnit>();
	}
	
	
	/**
	 * Gets the manager org unit list.
	 *
	 * @param username the username
	 * @return the manager org unit list
	 */
	@SuppressWarnings("unchecked")
	public List<OrgUnit> getManagerOrgUnitList(final String username) {
		final User user = userHome.findUserByUsername(username);
		if (user != null) {
			final String sql = RTSQueries.getManagerOrgUnit();
			final Query query = entityManager.createQuery(sql);
			query.setParameter(RTSConstants.USR_USER_ID, user.getUsrUserId());
			final List<OrgUnit> orgUnits = query.getResultList();
			return orgUnits;
		}
		return new ArrayList<OrgUnit>();
	}

	/**
	 * User has privilege.
	 *
	 * @param username the username
	 * @param privilege the privilege
	 * @return true, if successful
	 */
	public boolean userHasPrivilege(final String username, final String privilegeValue) {
	
		final User loggedInUser = (User) Contexts.getSessionContext().get(LOGGED_IN_USER);
		if(loggedInUser == null){
			return false;
		}
		
		List<UserRole> roles = null;
		if(loggedInUser.getUsrUserName().equalsIgnoreCase(username)){
			roles = loggedInUser.getUserRoles();
		} else {
			final User user = userHome.findUserByUsername(username);
			roles = user.getUserRoles();
		}		

		/**
		 * Add all user roles to identity for further authentication
		 */
		if (roles != null && !roles.isEmpty()) {
			for(final UserRole userRole : roles){
				final Set<RoleHasPrivilege> privileges = userRole.getRoleHasPrivileges();
				for(final RoleHasPrivilege roleHasPrivilege : privileges){
					final Privilege privilege = roleHasPrivilege.getPrivilege();
					if(privilege != null){
						final String prvPrivilegeName = privilege.getPrvPrivilegeName();
						if(StringUtils.isNotEmpty(prvPrivilegeName) && prvPrivilegeName.equals(privilegeValue)){
							return true;
						}
					}					
				}
			}
			
		}
		
		/*User user = cachedUser.get(username);
		if(user == null){
			user = userHome.findUserByUsername(username);
			if(user != null){
				cachedUser.put(username, user);
			}			
		}		
		
		if (user != null) {
			
			try{
				// get roles
				final Set<UserHasUserRole> roles = user.getUserHasUserRoles();
				if (RTSUtils.isNotEmpty(roles)) {
					final UserHasUserRole userRoles = (UserHasUserRole) roles.toArray()[0];
					if(userRoles != null){
						final UserRole role = userRoles.getUserRole();
						// get privileges
						final Set<RoleHasPrivilege> roleHasPrivileges = role.getRoleHasPrivileges();
						if (RTSUtils.isNotEmpty(roleHasPrivileges)) {
							final Object[] privileges = roleHasPrivileges.toArray();
							for(final Object privilegeObj : privileges){
								final RoleHasPrivilege roleHasPrivilege = privilegeObj != null ? (RoleHasPrivilege)privilegeObj : null;
								if(roleHasPrivilege != null){
									final Privilege pvl = roleHasPrivilege.getPrivilege() != null ? roleHasPrivilege.getPrivilege() : null;
									if(pvl != null){
										final String prvPrivilegeName = pvl.getPrvPrivilegeName() != null ? pvl.getPrvPrivilegeName() : null;
										if(StringUtils.isNotBlank(prvPrivilegeName) && prvPrivilegeName.equals(privilege)){
											return true;
										}
									}	
								}																	
							}					
						}
					}
					
				}	
			} catch (Exception e) {
				log.error("Error in fetching user privilege: " + username, e);
			}
							

			Calendar today = Calendar.getInstance();
			today.setTime(new Date());
			today.set(Calendar.AM_PM, Calendar.AM);
			today.set(Calendar.HOUR, 0);
			today.set(Calendar.MINUTE, 0);
			today.set(Calendar.SECOND, 0);
			today.set(Calendar.MILLISECOND, 0);

			final String queryStr = RTSQueries.getPrivilegeTransferByPrivilege();
			final Query query = entityManager.createQuery(queryStr);
			query.setParameter(RTSConstants.USR_USER_ID, user.getUsrUserId());
			query.setParameter(RTSConstants.SQL_PRIVILEGE, privilege);
			query.setParameter(RTSConstants.SQL_TO_DATE, today.getTime());
			
			final List<PrivilegeTransfer> ptList = query.getResultList();
			
			return RTSUtils.isNotEmpty(ptList) ? true : false;			
		}*/
		
		
		return false;
	}	
	

}