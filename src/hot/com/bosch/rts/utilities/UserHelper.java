/*
 * com.bosch.rts.utilities.UserHelper.java
 */
package com.bosch.rts.utilities;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.SizeLimitExceededException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.bosch.rts.entity.OrgUnit;
import com.bosch.rts.entity.User;
import com.bosch.rts.session.OrgUnitList;

/**
 * The User Helper.
 *
 * @author khb1hc
 */
public final class UserHelper {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(UserHelper.class);

	/** The instance. */
	private static UserHelper instance = null;

	/**
	 * Instantiates a new request level helper.
	 */
	public UserHelper() {
	}

	/**
	 * Gets the single instance of RequestLevelHelper.
	 *
	 * @return single instance of RequestLevelHelper
	 */
	public static final synchronized UserHelper getInstance() {
		if (instance == null) {
			instance = new UserHelper();
		}
		return instance;
	}

	/** The Constant BCD_DEPARTMENT. */
	private static final String BCD_DEPARTMENT = "department";

	/** The Constant BCD_MAIL. */
	private static final String BCD_MAIL = "mail";

	/** The Constant BCD_FIRSTNAME. */
	private static final String BCD_FIRSTNAME = "givenName";

	/** The Constant BCD_LASTNAME. */
	private static final String BCD_LASTNAME = "sn";

	/** The Constant BCD_USER_NAME. */
	private static final String BCD_USER_NAME = "sAMAccountName";

	/** The Constant LDAP_CTX_FACTORY. */
	private static final String LDAP_CTX_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";

	/** The Constant PRICIPLE. */
	private static final String PRICIPLE = "bu91fe@de.bosch.com";

	/** The Constant CREDENTIALS. */
	private static final String CREDENTIALS = "darf!nix";

	/** The Constant SIMPLE. */
	private static final String SIMPLE = "simple";

	/** The Constant SSL. */
	private static final String SSL = "ssl";
	
	/** The Constant PROVIDER_URL. */
	private static final String PROVIDER_URL = "ldap://rb-bcd-gc-defe-luds-lb.rb-dirsvc.bosch-org.com:3268/DC=bosch,DC=com";	

	/** The get user by username. */
	private final Map<String, User> getUserByUsername = new HashMap<String, User>();
	
	/**
	 * Lookup user in bcd.
	 *
	 * @param userName the user name
	 * @return the user
	 * @throws NamingException the naming exception
	 */
	public User lookupUserInBcd(final String userName) throws NamingException {
		User user = this.getUserByUsername.get(userName);

		if (user == null) {
			try {
				final String base = "";
				final StringBuilder filter = new StringBuilder();
				filter.append("(&(objectClass=user)(").append(BCD_USER_NAME).append("=").append(userName).append("))");

				final String[] retAttrNames = { BCD_USER_NAME, BCD_LASTNAME, BCD_FIRSTNAME, BCD_MAIL, BCD_DEPARTMENT };
				final int COUNT_LIMIT = 1;

				final SearchControls sc = new SearchControls();
				sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
				sc.setReturningAttributes(retAttrNames);
				sc.setReturningObjFlag(true);
				sc.setCountLimit(COUNT_LIMIT);

				final NamingEnumeration<SearchResult> resultEnum =
						this.getDirContext().search(base, filter.toString(), sc);
				if (resultEnum != null) {
					String bcdUserName = null;
					String bcdFirstName = null;
					String bcdLastName = null;
					String bcdDepartment = null;
					String bcdMail = null;
					if (resultEnum.hasMore()) {
						final SearchResult searchResult = resultEnum.next();
						final Attributes attrs = searchResult.getAttributes();
						if (attrs.get(BCD_USER_NAME) != null
								&& attrs.get(BCD_FIRSTNAME) != null
								&& attrs.get(BCD_LASTNAME) != null
								&& attrs.get(BCD_DEPARTMENT) != null
								&& attrs.get(BCD_MAIL) != null) {
							bcdUserName = (String) attrs.get(BCD_USER_NAME).get(0);
							bcdFirstName = (String) attrs.get(BCD_FIRSTNAME).get(0);
							bcdLastName = (String) attrs.get(BCD_LASTNAME).get(0);
							bcdDepartment = (String) attrs.get(BCD_DEPARTMENT).get(0);
							bcdMail = (String) attrs.get(BCD_MAIL).get(0);

							user = new User();
							user.setUsrUserName(userName.toLowerCase());
							user.setUsrEmail(bcdMail);
							user.setUsrFullName(bcdLastName + " " + bcdFirstName);							
							
							/*user = new User(bcdUserName.toLowerCase(),
									bcdLastName, bcdFirstName, bcdMail,
									bcdDepartment);*/

							this.getUserByUsername.put(userName, user);
						}
					}
				}

			} catch (final SizeLimitExceededException e) {
				logger.error("Error in fetching username: \"" + userName + e);
			} catch (final NamingException e) {
				logger.error("Error in fetching username: \"" + userName +  e);
			} finally {
				if(this.dirContext != null){
					this.dirContext.close();
				}
			}
		}
		
		return user;
	}
	
	/**
	 * Lookup user in bcd.
	 *
	 * @param userName the user name
	 * @return the user
	 * @throws NamingException the naming exception
	 */
	public User lookupUserInBcd(final String userName, final boolean fetchOrgUnit) throws NamingException {
		User user = this.getUserByUsername.get(userName);

		if (user == null) {
			try {
				final String base = "";
				final StringBuilder filter = new StringBuilder();
				filter.append("(&(objectClass=user)(").append(BCD_USER_NAME).append("=").append(userName).append("))");

				final String[] retAttrNames = { BCD_USER_NAME, BCD_LASTNAME, BCD_FIRSTNAME, BCD_MAIL, BCD_DEPARTMENT };
				final int COUNT_LIMIT = 1;

				final SearchControls sc = new SearchControls();
				sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
				sc.setReturningAttributes(retAttrNames);
				sc.setReturningObjFlag(true);
				sc.setCountLimit(COUNT_LIMIT);

				final NamingEnumeration<SearchResult> resultEnum =
						this.getDirContext().search(base, filter.toString(), sc);
				if (resultEnum != null) {
					String bcdUserName = null;
					String bcdFirstName = null;
					String bcdLastName = null;
					String bcdDepartment = null;
					String bcdMail = null;
					if (resultEnum.hasMore()) {
						final SearchResult searchResult = resultEnum.next();
						final Attributes attrs = searchResult.getAttributes();
						if (attrs.get(BCD_USER_NAME) != null
								&& attrs.get(BCD_FIRSTNAME) != null
								&& attrs.get(BCD_LASTNAME) != null
								&& attrs.get(BCD_DEPARTMENT) != null
								&& attrs.get(BCD_MAIL) != null) {
							bcdUserName = (String) attrs.get(BCD_USER_NAME).get(0);
							bcdFirstName = (String) attrs.get(BCD_FIRSTNAME).get(0);
							bcdLastName = (String) attrs.get(BCD_LASTNAME).get(0);
							bcdDepartment = (String) attrs.get(BCD_DEPARTMENT).get(0);
							bcdMail = (String) attrs.get(BCD_MAIL).get(0);

							user = new User();
							user.setUsrUserName(userName.toLowerCase());
							user.setUsrEmail(bcdMail);
							user.setUsrFullName(bcdLastName + " " + bcdFirstName);
							if(StringUtils.isNotEmpty(bcdDepartment) && bcdDepartment.contains("/")){
								final String unitName = bcdDepartment.substring(bcdDepartment.lastIndexOf("/") + 1, bcdDepartment.length());		
								if(fetchOrgUnit){
									OrgUnitList orgUnitList = new OrgUnitList();
									final OrgUnit userOrgUnit = orgUnitList.getOrgUnitsByUnitName(unitName, Boolean.TRUE);
									if(userOrgUnit != null){
										user.setOrgUnit(userOrgUnit);
									}
								}
							}
							
							this.getUserByUsername.put(userName, user);
						}
					}
				}

			} catch (final SizeLimitExceededException e) {
				logger.error("Error in fetching username: \"" + userName + e);
			} catch (final NamingException e) {
				logger.error("Error in fetching username: \"" + userName +  e);
			} finally {
				if(this.dirContext != null){
					this.dirContext.close();
				}
			}
		}
		
		return user;
	}

	/** The dir context. */
	private DirContext dirContext = null;
	
	/**
	 * BCD context information.
	 *
	 * @return DirContext
	 * @throws NamingException the naming exception
	 */
	private DirContext getDirContext() throws NamingException {
		//Check if directory context is available, not to hit again.
		if(this.dirContext == null){
			
			final boolean sslEnabled = false;
	
			final Hashtable<String, String> props = new Hashtable<String, String>();
			props.put(Context.INITIAL_CONTEXT_FACTORY, LDAP_CTX_FACTORY);
			props.put(Context.SECURITY_AUTHENTICATION, SIMPLE);
			props.put(Context.SECURITY_PRINCIPAL, PRICIPLE);
			props.put(Context.SECURITY_CREDENTIALS, CREDENTIALS);
			props.put(Context.PROVIDER_URL,	PROVIDER_URL);
	
			if (sslEnabled) {
				props.put(Context.SECURITY_PROTOCOL, SSL);
			}
	
			try {
				this.dirContext = new InitialDirContext(props);
			} catch (final NamingException e) {
				logger.error("InitialDirContext for LDAP connection could not be instanciated: " + e);
			}
		}

		return this.dirContext;
	}
	
}