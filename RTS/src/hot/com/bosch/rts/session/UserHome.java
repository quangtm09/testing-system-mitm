package com.bosch.rts.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.core.Events;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;

import com.bosch.rts.entity.Candidate;
import com.bosch.rts.entity.OrgUnit;
import com.bosch.rts.entity.PrivilegeTransfer;
import com.bosch.rts.entity.RecruitRequest;
import com.bosch.rts.entity.User;
import com.bosch.rts.entity.UserHasInterviewSchedule;
import com.bosch.rts.entity.UserHasUserRole;
import com.bosch.rts.entity.UserRole;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSQueries;
import com.bosch.rts.utilities.RTSUtils;
import com.bosch.rts.utilities.UserHelper;

/**
 * The Class UserHome.
 *
 * @author TAQ1HC
 */
@Name("userHome")
public class UserHome extends EntityHome<User> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 8335310439661388523L;
	
	/** The log. */
	@Logger
	private transient Log log;
	
	/** The org unit home. */
	@In(create = true)
	private transient OrgUnitHome orgUnitHome;
	
	/** The user list. */
	@In(create = true)
	private transient UserList userList;
	
	/** The credentials. */
	@In(create = true)
	private transient Credentials credentials;
	
	/** The authenticator. */
	@In(create = true)
	private transient Authenticator authenticator;
	
	/** The status messages. */
	@In(create = true)
	private transient StatusMessages statusMessages;
	
	/** The user role list. */
	@In(create = true)
	private transient UserRoleList userRoleList;
	
	/** The existing email. */
	private User existingEmail;
	
	/** The existing userName in BCD. */
	private User existingUserNameInBCD;
	
	/** The existing userName in DB. */
	private User existingUserNameInDB;
	
	/** The existing ID Number. */
	private User existingIdNo;	

	/** The disable status of save button. */
	private boolean saveButtonStatus;
	
	/**
	 * Sets the user usr user id.
	 *
	 * @param id the new user id
	 */
	public void setUserUsrUserId(Integer id) {
		setId(id);
	}

	/**
	 * Gets the user usr user id.
	 *
	 * @return the user usr user id
	 */
	public Integer getUserUsrUserId() {
		return (Integer) getId();
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#createInstance()
	 */
	@Override
	protected User createInstance() {
		User user = new User();
		return user;
	}
	
	/**
	 * New instance.
	 *
	 * @return the user
	 */
	public User newInstance(){
		User user = new User();
		this.setInstance(user);
		return user;
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
		OrgUnit orgUnit = orgUnitHome.getDefinedInstance();
		if (orgUnit != null) {
			getInstance().setOrgUnit(orgUnit);
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
	public User getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	/**
	 * Gets the candidates.
	 *
	 * @return the candidates
	 */
	public List<Candidate> getCandidates() {
		return getInstance() == null ? null : new ArrayList<Candidate>(
				getInstance().getCandidates());
	}
	
	/**
	 * Gets the org units.
	 *
	 * @return the org units
	 */
	public List<OrgUnit> getOrgUnits() {
		return getInstance() == null ? null : new ArrayList<OrgUnit>(
				getInstance().getOrgUnits());
	} 

	/**
	 * Gets the privilege transfers for rot usr from user id.
	 *
	 * @return the privilege transfers for rot usr from user id
	 */
	public List<PrivilegeTransfer> getPrivilegeTransfersForRotUsrFromUserId() {
		return getInstance() == null ? null : new ArrayList<PrivilegeTransfer>(
				getInstance().getPrivilegeTransfersForRotUsrFromUserId());
	}

	/**
	 * Gets the privilege transfers for rot usr to user id.
	 *
	 * @return the privilege transfers for rot usr to user id
	 */
	public List<PrivilegeTransfer> getPrivilegeTransfersForRotUsrToUserId() {
		return getInstance() == null ? null : new ArrayList<PrivilegeTransfer>(
				getInstance().getPrivilegeTransfersForRotUsrToUserId());
	}

	/**
	 * Gets the recruit requests.
	 *
	 * @return the recruit requests
	 */
	public List<RecruitRequest> getRecruitRequests() {
		return getInstance() == null ? null : new ArrayList<RecruitRequest>(
				getInstance().getRecruitRequests());
	}

	/**
	 * Gets the user has interview schedules.
	 *
	 * @return the user has interview schedules
	 */
	public List<UserHasInterviewSchedule> getUserHasInterviewSchedules() {
		return getInstance() == null ? null
				: new ArrayList<UserHasInterviewSchedule>(getInstance()
						.getUserHasInterviewSchedules());
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

	/**
	 * Find user by username.
	 *
	 * @param username the username
	 * @return the user
	 */
	@SuppressWarnings("unchecked")
	public User findUserByUsername(final String username) {
		User user = null;
		final String sql = RTSQueries.getUserByUserName();
		final Query query = getEntityManager().createQuery(sql);		
		query.setParameter(RTSConstants.SQL_USR_NAME, username.toLowerCase());
		
		List<User> resultList = query.getResultList();
		if (resultList != null && resultList.size() > 0) {
			user = resultList.get(0);
		}		
		
		return user;
	}
	
	/**
	 * Find user by email.
	 *
	 * @param userEmail the user email
	 * @return the user
	 */
	@SuppressWarnings("unchecked")
	public User findUserByEmail(final String userEmail) {
		User user = null;
		final String sql = RTSQueries.getUserByEmail();
		final Query query = getEntityManager().createQuery(sql);		
		query.setParameter(RTSConstants.SQL_USR_EMAIL, userEmail.toLowerCase());
		
		List<User> resultList = query.getResultList();
		if (resultList != null && resultList.size() > 0) {
			user = resultList.get(0);
		}		
		
		return user;
	}
	
	/**
	 * Find user by id no.
	 *
	 * @param userId the user id
	 * @return the user
	 */
	@SuppressWarnings("unchecked")
	public User findUserByIdNo(final String userId) {
		User user = null;
		final String sql = RTSQueries.getUserByIdNo();
		final Query query = getEntityManager().createQuery(sql);		
		query.setParameter(RTSConstants.SQL_USER_ID_NUMBER, userId.toLowerCase());
		
		List<User> resultList = query.getResultList();
		if (resultList != null && resultList.size() > 0) {
			user = resultList.get(0);
		}		
		
		return user;
	}

	/**
	 * Find all user.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<User> findAllUser() {
		List<User> userList = null;
		String queryStr = "Select user from User user";
		Query query = getEntityManager().createQuery(queryStr);
		userList = query.getResultList();
		return userList;
	}
	
	/**
	 * Find all user.
	 *
	 * @param active the active
	 * @param approved the approved
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<User> findAllUser(final boolean active, final boolean approved) {
		List<User> userList = null;
		final String queryStr = RTSQueries.getUsers();
		final Query query = getEntityManager().createQuery(queryStr);
		query.setParameter("status", active);
		query.setParameter("approved", approved);
		userList = query.getResultList();
		return userList;
	}
	
	/**
	 * Find user by org unit id.
	 *
	 * @param orgUnitID the org unit id
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<User> findUserByOrgUnitID(int orgUnitID) {
		List<User> userList = null;
		String queryStr = "Select user from User user where user.orgUnit.orgUnitId = ?1";
		Query query = getEntityManager().createQuery(queryStr);
		query.setParameter(1, orgUnitID);
		userList = query.getResultList();
		return userList;
	}
	
	/**
	 * Find user by list of org unit id.
	 *
	 * @param orgUnitList the org unit list
	 * @param status the status
	 * @param approved the approved
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<User> findUserByListOfOrgUnitID(final List<OrgUnit> orgUnitList, final boolean status, final boolean approved) {
		
		if (RTSUtils.isNotEmpty(orgUnitList)) {
			StringBuilder sql = new StringBuilder();
			sql.append("select user from User user ");
			sql.append("where user.orgUnit.orgUnitId in (");
			final int ORG_UNIT_LIST_SIZE = orgUnitList.size();
			for (int counter = 0; counter < ORG_UNIT_LIST_SIZE; ++counter) {
				sql.append(orgUnitList.get(counter).getOrgUnitId());				
				sql.append(counter == ORG_UNIT_LIST_SIZE - 1 ? RTSConstants.RIGHT_BRACKET : RTSConstants.COLON);				
			}		
			sql.append(" and user.status = :status");
			sql.append(" and user.approved = :approved");
			
			final Query query = getEntityManager().createQuery(sql.toString());
			query.setParameter("status", status);
			query.setParameter("approved", approved);
			final List<User> userList = query.getResultList();
			return userList;
		}
		
		return new ArrayList<User>();
	}
	
	/**
	 * Validate user name and email.
	 *
	 * @return true, if successful
	 */
	public boolean validateUserNameAndEmail(){
		return false;
	}
	
	/**
	 * Fetch email in database.
	 *
	 * @param userEmail the user email
	 */
	public void fetchExistEmail(String userEmail){
		this.existingEmail = null;

		if (this.getInstance() != null) {
			existingEmail = findUserByEmail(userEmail);
		}
	}
	
	/**
	 * Fetch existed ID Number in database.
	 *
	 * @param IdNo the id no
	 */
	public void fetchExistIdNo(String IdNo){
		this.existingIdNo = null;		
		
		if(StringUtils.isNotEmpty(IdNo) && this.getInstance() != null){
			existingIdNo = findUserByIdNo(IdNo);
		}
	}
	
	/**
	 * Fetch username in both BCD and database.
	 *
	 * @param userName the user name
	 * @throws NamingException the naming exception
	 */
	public void fetchExistUserName(String userName) throws NamingException {	
		if(StringUtils.isNotEmpty(userName)){
			fetchExistUserNameInBCD(userName.trim().toLowerCase());
			fetchExistUserNameInDB(userName.trim().toLowerCase());
		}
				
	}
	
	/**
	 * Fetch username in BCD.
	 *
	 * @param userName the user name
	 * @throws NamingException the naming exception
	 */
	public void fetchExistUserNameInBCD(String userName) throws NamingException{
		final UserHelper userHelper = new UserHelper();
		final User user = userHelper.lookupUserInBcd(userName);
		
		if(user != null && user.getUsrFullName() != null && user.getUsrEmail() != null){
			this.existingUserNameInBCD = user;
		} else {
			this.existingUserNameInBCD = null;
		}
	}
	
	/**
	 * Fetch username in database.
	 *
	 * @param userName the user name
	 * @throws NamingException the naming exception
	 */
	public void fetchExistUserNameInDB(String userName) throws NamingException{
		User user = findUserByUsername(userName);		
		if(user != null && user.getUsrFullName() != null && user.getUsrEmail() != null){
			this.existingUserNameInDB = user;
		} else {
			this.existingUserNameInDB = null;
		}
		
	}
	
	/**
	 * Clear form.
	 */
	public void clearForm(){
		getInstance().setUsrEmail("");
		getInstance().setUsrFullName("");
		getInstance().setUsrIdNumber("");
		getInstance().setUsrUserName("");
		
		this.existingEmail = null;
		this.existingIdNo = null;
		this.existingUserNameInBCD = null;
		this.existingUserNameInDB = null;
	}
	
	/** The available users. */
	private List<User> availableUsers = null;
	
	/**
	 * Onload users.
	 */
	public void onloadUsers(){
		RTSUtils.resetList(availableUsers);
		availableUsers = findAllUser(true,true);
	}
	
	/**
	 * Onload users.
	 */
	public void onloadAllUsers(){
		RTSUtils.resetList(availableUsers);
		availableUsers = userList.getResultList();
	}
	
	/**
	 * Onload all user approvers.
	 */
	public void onloadAllUserApprovers(){
		RTSUtils.resetList(availableUsers);
		availableUsers = userList.getUserHasRoles(RTSConstants.USER_APPOVER_RIGHT_NUMBER);
	}
	
	/**
	 * Onload all recruitment approvers.
	 */
	public void onloadAllRecruitmentApprovers(){
		RTSUtils.resetList(availableUsers);
		availableUsers = userList.getUserHasRoles(RTSConstants.RECRUITMENT_APPOVER_RIGHT_NUMBER);
	}
	

	/**
	 * Sets the available users.
	 *
	 * @param availableUsers the new available users
	 */
	public void setAvailableUsers(List<User> availableUsers) {
		this.availableUsers = availableUsers;
	}

	/**
	 * Gets the available users.
	 *
	 * @return the available users
	 */
	public List<User> getAvailableUsers() {
		return availableUsers;
	}
	
	/**
	 * Create new user.
	 *
	 * @return the string
	 * @throws NamingException the naming exception
	 */
	public String createUser() throws NamingException{
		statusMessages.clear();
		String result = RTSConstants.FAILURE;
		
		final String username = credentials.getUsername();
		final Date currentDate = new Date();

		try {
			
			/* Validate user name & email & ID Number*/
			fetchExistEmail(getInstance().getUsrEmail());
			fetchExistIdNo(getInstance().getUsrIdNumber());
			fetchExistUserName(getInstance().getUsrUserName());

			/* New user is created if there is no existed email, ID Number, username and username must exist in LDAP server */
			if (existingEmail == null && existingUserNameInBCD != null && existingUserNameInDB == null && existingIdNo == null) {
				final String ntid = getInstance().getUsrUserName().trim().toLowerCase();
				getInstance().setUsrUserName(ntid);
				getInstance().setCreatedBy(username);
				getInstance().setCreatedOn(currentDate);
				getInstance().setStatus(false);
				getInstance().setUpdatedBy(username);
				getInstance().setUpdatedOn(currentDate);
				getInstance().setApproved(false);
				
				result = super.persist();
				statusMessages.addFromResourceBundle(
						Severity.INFO,
								"form.users.create.success");
			} else {
				statusMessages.clear();
				statusMessages.addFromResourceBundle(
						Severity.ERROR,
								"form.users.create.error");
			}

		} catch (Exception ex) {
			log.error("Error in creating new user. [" + ex.getMessage() + "]");
			statusMessages.addFromResourceBundle(Severity.ERROR, "form.users.create.error");
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.EntityHome#update()
	 */
	@Override
	public String update(){
		String result = RTSConstants.FAILURE; 
		boolean isError = false;
		
		final String ERROR_ROLE_UPDATE = "error.role.update";
		
		final Date updatedOn = new Date();	
		this.getInstance().setUpdatedOn(updatedOn);
		
		final String updatedBy = credentials.getUsername();
		this.getInstance().setUpdatedBy(updatedBy);	
		//this.getInstance().setStatus(false);
		this.getInstance().setApproved(false);	
		this.getInstance().setApprovedOn(null);
		
		try {
			final List<UserRole> oldUserRoles = this.getInstance().getUserRoles();		
		
			if(RTSUtils.isNotEmpty(oldUserRoles)){
				//some roles selected
				if(RTSUtils.isNotEmpty(this.selectedUserRoles)){
					oldUserRoles.removeAll(this.selectedUserRoles);
					if(RTSUtils.isNotEmpty(oldUserRoles)){
						for(UserRole oldUserRole : oldUserRoles){
							final Set<UserHasUserRole> userHasUserRoles = oldUserRole.getUserHasUserRoles();
							for(UserHasUserRole userHasUserRole : userHasUserRoles){
								final int userId = userHasUserRole.getUser().getUsrUserId();
								if(userId == this.getUserUsrUserId()){
									try{
										getEntityManager().remove(userHasUserRole);
									} catch (Exception e) {
										isError = true;
										break;
									}									
								}
							}					
						}
					}
					
					//Avoid of duplicated entity persistent
					final List<UserRole> tempUserRoles = this.getInstance().getUserRoles();
					if(RTSUtils.isNotEmpty(tempUserRoles)){
						this.selectedUserRoles.removeAll(tempUserRoles);
					}
					
					if(RTSUtils.isNotEmpty(tempUserRoles)){
						for(UserRole userRole : this.selectedUserRoles){
							UserHasUserRole userHasUserRole = new UserHasUserRole();
							userHasUserRole.setUserRole(userRole);
							userHasUserRole.setUser(this.getInstance());
							try{
								getEntityManager().persist(userHasUserRole);
							} catch (Exception e) {
								isError = true;
								break;
							}							
						}
					}
									
				} else {
					for(UserRole oldUserRole : oldUserRoles){
						final Set<UserHasUserRole> userHasUserRoles = oldUserRole.getUserHasUserRoles();
						for(UserHasUserRole userHasUserRole : userHasUserRoles){
							final int userId = userHasUserRole.getUser().getUsrUserId();
							if(userId == this.getUserUsrUserId()){
								try{
									getEntityManager().remove(userHasUserRole);
								} catch (Exception e) {
									isError = true;
									break;
								}								
							}
						}					
					}	
				}
			} else {
				if(RTSUtils.isNotEmpty(this.selectedUserRoles)){					
					for(UserRole userRole : this.selectedUserRoles){
						UserHasUserRole userHasUserRole = new UserHasUserRole();
						userHasUserRole.setUserRole(userRole);
						userHasUserRole.setUser(getInstance());		
						try{
							getEntityManager().persist(userHasUserRole);
						} catch (Exception e) {
							isError = true;
							break;
						}						
					}
				}
			}
			
		} catch (Exception e) {
			log.error("Error in updating user roles: " + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, ERROR_ROLE_UPDATE);	
			isError = true;
		}	
		
		if(!isError){
			try {		
				statusMessages.clear();
				result =  super.update();
				
				//reset roles if there are role changes of current logged-in user, 
				//redirect to the home page
				if(updatedBy.equalsIgnoreCase(this.getInstance().getUsrUserName().trim())){
					authenticator.resetPriviledges();					
					result = RTSConstants.PERSISTED;
				}			
				
				//send mail to approver
				final User updatedByUser = this.findUserByUsername(this.getInstance().getUpdatedBy());
				final User approvedByUser = this.findUserByUsername(this.getInstance().getApprovedBy());
				if(updatedByUser != null && approvedByUser != null){
					Events.instance().raiseEvent("sendForApprovingMail", this.getInstance(), updatedByUser, approvedByUser);
				} else {
					log.error("Error in sending for approving user status: - Can not find recipients ");
				}	
				
			} catch (Exception e) {
				log.error("Error in updating group: " + e);
				statusMessages.clear();
				statusMessages.addFromResourceBundle(Severity.ERROR, ERROR_ROLE_UPDATE);
				isError = true;
			}		
		}	
		
		return result;
	}
	
	/**
	 * Approve.
	 *
	 * @return the string
	 */
	public String approve(){
		String result = RTSConstants.FAILURE; 
		final Date currentDate = new Date();		
		//this.getInstance().setStatus(true);
		this.getInstance().setApproved(true);
		this.getInstance().setApprovedOn(currentDate);
		try {		
			statusMessages.clear();
			result =  super.update();
			
			//send mail to user creator that inform user has been approved
			//send mail to approver
			final User updatedByUser = this.findUserByUsername(this.getInstance().getUpdatedBy());
			final User approvedByUser = this.findUserByUsername(this.getInstance().getApprovedBy());
			
			//send notification mail to users
			final List<User> recipients = new ArrayList<User>();
			recipients.add(0, updatedByUser);		
			recipients.add(1, approvedByUser);		
			recipients.add(2, this.getInstance());
			
			RTSUtils.removeDuplicatedUsers(recipients);
			
			if(updatedByUser != null && approvedByUser != null){
				Events.instance().raiseEvent("sendApprovedMail", 
						this.getInstance(), 
						updatedByUser, 
						approvedByUser,
						recipients);
			} else {
				log.error("Error in sending email for user status approved: - Can not find recipients ");
			}			
		} catch (Exception e) {
			log.error("Error in approving user status: " + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, "form.user.approve.error");
		}	
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.EntityHome#remove()
	 */
	@Override
	public String remove(){
		return null;
	}
		
	/** The selected user roles. */
	private List<UserRole> selectedUserRoles = null;	
	
	/**
	 * Gets the selected user roles.
	 *
	 * @return the selectedUserRoles
	 */
	public List<UserRole> getSelectedUserRoles() {
		return this.selectedUserRoles;
	}

	/**
	 * Sets the selected user roles.
	 *
	 * @param selectedUserRoles the selectedUserRoles to set
	 */
	public void setSelectedUserRoles(List<UserRole> selectedUserRoles) {
		this.selectedUserRoles = selectedUserRoles;
	}
	
	/**
	 * Onload selected user roles.
	 */
	public void onloadSelectedUserRoles(){
		selectedUserRoles = new ArrayList<UserRole>();
		for(UserHasUserRole hasUserRole : this.getInstance().getUserHasUserRoles()){
			UserRole userRole = hasUserRole.getUserRole();
			selectedUserRoles.add(userRole);
		}		
	}

	/** The user roles. */
	private List<UserRole> userRoles = null;

	/**
	 * Gets the user roles.
	 *
	 * @return the userRoles
	 */
	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	/**
	 * Sets the user roles.
	 *
	 * @param userRoles the userRoles to set
	 */
	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	/**
	 * Onload user roles.
	 */
	public void onloadUserRoles(){
		RTSUtils.resetList(userRoles);
		userRoles = userRoleList.getUserRoles();
	}

	/**
	 * Gets the existing email.
	 *
	 * @return existingEmail
	 */
	public User getExistingEmail() {
		return existingEmail;
	}
	
	/**
	 * Sets the existing email.
	 *
	 * @param existingEmail the new existing email
	 */
	public void setExistingEmail(User existingEmail) {
		this.existingEmail = existingEmail;
	}

	/**
	 * Disabled status of save button is based on user input email, username and ID Number.
	 *
	 * @return saveButtonStatus
	 */
	public boolean isSaveButtonStatus() {
		if(existingEmail != null || existingUserNameInBCD == null || existingUserNameInDB != null || existingIdNo != null){
			saveButtonStatus = true;
		} else 
			saveButtonStatus = false;
		
		return saveButtonStatus;
	}

	/**
	 * Sets the save button status.
	 *
	 * @param saveButtonStatus the new save button status
	 */
	public void setSaveButtonStatus(boolean saveButtonStatus) {
		this.saveButtonStatus = saveButtonStatus;
	}

	/**
	 * Gets the existing user name in bcd.
	 *
	 * @return the existing user name in bcd
	 */
	public User getExistingUserNameInBCD() {
		if(this.getInstance().getUsrUserName() == null || this.getInstance().getUsrUserName() == ""){
			this.existingUserNameInBCD = new User();
		}
		
		return existingUserNameInBCD;
	}

	/**
	 * Sets the existing user name in bcd.
	 *
	 * @param existingUserNameInBCD the new existing user name in bcd
	 */
	public void setExistingUserNameInBCD(User existingUserNameInBCD) {
		this.existingUserNameInBCD = existingUserNameInBCD;
	}

	/**
	 * Gets the existing user name in db.
	 *
	 * @return the existing user name in db
	 */
	public User getExistingUserNameInDB() {
		return existingUserNameInDB;
	}

	/**
	 * Sets the existing user name in db.
	 *
	 * @param existingUserNameInDB the new existing user name in db
	 */
	public void setExistingUserNameInDB(User existingUserNameInDB) {
		this.existingUserNameInDB = existingUserNameInDB;
	}

	/**
	 * Gets the existing id no.
	 *
	 * @return the existing id no
	 */
	public User getExistingIdNo() {
		return existingIdNo;
	}

	/**
	 * Sets the existing id no.
	 *
	 * @param existingIdNo the new existing id no
	 */
	public void setExistingIdNo(User existingIdNo) {
		this.existingIdNo = existingIdNo;
	}
	
	
	/**
	 * Populate user to candidate form.
	 *
	 * @param usrUserId the user id
	 */
	public void populateUser(final String usrUserId){
		this.getInstance().setApprovedBy(usrUserId);			
	}	
	
	/**
	 * Synchronization by username
	 *
	 * @return the string
	 */
	public String synchronization(){
		String result = RTSConstants.FAILURE;
		final String submitedUserName = this.getInstance().getUsrUserName();
		if(StringUtils.isNotEmpty(submitedUserName)){
			final UserHelper userHelper = new UserHelper();
			User user = null;
			try {
				user = userHelper.lookupUserInBcd(submitedUserName, true);
			} catch (NamingException e) {
				e.printStackTrace();
			}
			
			if(user != null){
				this.setInstance(user);
			} 
		}
		
		result = RTSConstants.SUCCESS;
		
		return result;
		
	}
}