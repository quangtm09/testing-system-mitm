/*
 * /rts/src/hot/com/bosch/rts/session/Authenticator.java
 */
package com.bosch.rts.session;

import java.util.List;
import java.util.Set;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.contexts.Context;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.faces.Redirect;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import waffle.servlet.WindowsPrincipal;

import com.bosch.rts.entity.Privilege;
import com.bosch.rts.entity.RoleHasPrivilege;
import com.bosch.rts.entity.User;
import com.bosch.rts.entity.UserRole;

/**
 * The Class Authenticator.
 */
@Name("authenticator")
public class Authenticator {
	
	@Logger
	private transient Log log;
	
	/** The Constant NEGOTIATE_SECURITY_FILTER_PRINCIPAL. */
	private static final String NEGOTIATE_SECURITY_FILTER_PRINCIPAL = "waffle.servlet.NegotiateSecurityFilter.PRINCIPAL";
	
	/** The Constant LOGGED_IN_USER. */
	private static final String LOGGED_IN_USER = "loggedInUser";
	
	/** The Constant BACK_SLASH. */
	private static final String BACK_SLASH = "\\";	
	
		
	/** The credentials. */
	@In
	private Credentials credentials;
	
	/** The user home. */
	@In(create = true)
	private UserHome userHome;
	
	/** The identity. */
	@In
	private Identity identity; 
	
	/** The session context. */
	@In
	private Context sessionContext;
	
	/** The redirect. */
	@In
	private Redirect redirect;

	/**
	 * Authenticate.
	 *
	 * @return true, if successful
	 */
	public boolean authenticate() {		
		final String username = credentials.getUsername();
		log.info("Authenticating user: " + username);
		
		final User loggedInUser = userHome.findUserByUsername(username);
		if (loggedInUser != null) {	
			final List<UserRole> roles = loggedInUser.getUserRoles();			
			
			/**
			 * Add all user roles to identity for further authentication
			 */
			if (roles != null && !roles.isEmpty()) {
				for(final UserRole userRole : roles){	
					final String roleName  = userRole.getUsrRoleName();					
					log.info("Authenticated user " + username + " with role: " + roleName);
					identity.addRole(roleName.toLowerCase());					
										
					final Set<RoleHasPrivilege> privileges = userRole.getRoleHasPrivileges();
					if(privileges != null && !privileges.isEmpty()){
						for(final RoleHasPrivilege roleHasPrivilege : privileges){
							final Privilege privilege = roleHasPrivilege.getPrivilege();
							if(privilege != null){
								final String privildege = privilege.getPrvPrivilegeName();
								//log.info("Authenticated user " + username + " with priviledge: " + privildege);
							}					
						}
					}
					
				}
				
			}
			
			if(!loggedInUser.getStatus() && loggedInUser.getApproved()){
				identity.addRole("disabledUser");
				FacesMessages.instance().clear();
				FacesMessages.instance().addFromResourceBundle("error.inactive.user");				
			} else {
				FacesMessages.instance().clear();
			}
			
			Contexts.getSessionContext().set(LOGGED_IN_USER, loggedInUser);
			return true;			
		}
		
		return false;
	}

	/**
	 * Do single sign on.
	 */
	public void doSingleSignOn() {
		
		if (!identity.isLoggedIn()) {			
			
			redirect.captureCurrentView();			
			
			final WindowsPrincipal windowsPrincipal = (WindowsPrincipal) sessionContext.get(NEGOTIATE_SECURITY_FILTER_PRINCIPAL);
			
			if(windowsPrincipal != null){
				String userFullName = windowsPrincipal.getName();
				String userName = null;
				if(userFullName != null && userFullName.trim().length() > 0){
					if(userFullName.lastIndexOf(BACK_SLASH) != -1){
						final int backSplashLastIndex = userFullName.lastIndexOf(BACK_SLASH);
						userName = userFullName.substring(backSplashLastIndex + 1);
					} else {
						userName = userFullName;
					}
				}
				
				credentials.setUsername(userName);
				try{
					identity.login();
					redirect.returnToCapturedView();			
				} catch (Exception e) {
					log.error("Error in doing sign on: " + e);
				}
			} else {
				redirect.setViewId("/index.html");
			}
			
						
		}
		/*if (credentials.getUsername() != null && !credentials.getUsername().isEmpty()) {
			// Setting logged User
			loggedInUser = userHome.findUserByUsername(credentials.getUsername());
		}	*/
	}
	
	/**
	 * Reset priviledges.
	 */
	public void resetPriviledges(){
		identity.logout();
	}
	
	public void checkDisabledUser(){
		final User loggedInUser = (User) Contexts.getSessionContext().get(LOGGED_IN_USER);
		if(loggedInUser != null){
			if(!loggedInUser.getStatus() && loggedInUser.getApproved()){
				FacesMessages.instance().clear();
			
			}
		}
	}
}