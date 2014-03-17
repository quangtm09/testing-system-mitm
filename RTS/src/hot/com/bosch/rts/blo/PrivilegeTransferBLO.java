/**
 * 
 */
package com.bosch.rts.blo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;
import org.jboss.seam.transaction.Transaction;

import com.bosch.rts.entity.OrgUnit;
import com.bosch.rts.entity.Privilege;
import com.bosch.rts.entity.PrivilegeTransfer;
import com.bosch.rts.entity.RoleHasPrivilege;
import com.bosch.rts.entity.User;
import com.bosch.rts.entity.UserHasUserRole;
import com.bosch.rts.entity.UserRole;
import com.bosch.rts.session.PrivilegeHome;
import com.bosch.rts.session.PrivilegeTransferHome;
import com.bosch.rts.session.UserHome;

/**
 * The Class PrivilegeTransferBLO.
 *
 * @author bit71hc
 */
@Name("privilegeTransferBLO")
public class PrivilegeTransferBLO {
	
	/** The identity. */
	@In
	Identity identity;
	
	/** The logged in user. */
	@In(scope = ScopeType.SESSION, required = false)
	User loggedInUser;
	
	/** The user home. */
	@In(create = true)
	UserHome userHome;
	
	/** The privilege transfer home. */
	@In(create = true)
	PrivilegeTransferHome privilegeTransferHome;
	
	/** The privilege home. */
	@In(create = true)
	PrivilegeHome privilegeHome;
	
	/** The user blo. */
	@In(create = true)
	UserBLO userBLO;

	/** The privilege list. */
	private List<String> privilegeList;
	
	/** The selected privileges. */
	private List<String> selectedPrivileges;
	
	/** The user list. */
	private List<User> userList;
	
	/** The from date. */
	private Date fromDate;
	
	/** The to date. */
	private Date toDate;
	
	/** The description. */
	private String description;
	
	/** The selected user. */
	private User selectedUser;
	
	/** The updating. */
	private boolean updating;
	
	/** The id of selected pt. */
	private int idOfSelectedPT;
	
	/** The updating pt. */
	private PrivilegeTransfer updatingPT;
	
	/** The org unit. */
	private OrgUnit orgUnit;
	
	/** The log. */
	@Logger
	private transient Log log;

	/**
	 * Gets the privilege list.
	 *
	 * @return the privilege list
	 */
	public List<String> getPrivilegeList() {
		privilegeList = new ArrayList<String>();
		User user = userHome.findUserByUsername(loggedInUser.getUsrUserName());
		if (user != null) {
			// get roles
			Set<UserHasUserRole> roles = user.getUserHasUserRoles();
			if (roles != null && roles.size() > 0) {
				UserHasUserRole userRoles = (UserHasUserRole) roles.toArray()[0];
				UserRole role = userRoles.getUserRole();
				// get privileges
				Set<RoleHasPrivilege> roleHasPrivileges = role
						.getRoleHasPrivileges();
				if (roleHasPrivileges != null && roleHasPrivileges.size() > 0) {
					Object[] privileges = roleHasPrivileges.toArray();
					for (int i = 0; i < privileges.length; ++i) {
						RoleHasPrivilege roleHasPrivilege = (RoleHasPrivilege) privileges[i];
						Privilege pvl = roleHasPrivilege.getPrivilege();
						privilegeList.add(pvl.toString().replace('_', ' '));
					}
				}
			}
		}

		return privilegeList;
	}

	/**
	 * Sets the privilege list.
	 *
	 * @param privilegeList the new privilege list
	 */
	public void setPrivilegeList(List<String> privilegeList) {
		this.privilegeList = privilegeList;
	}

	/**
	 * Gets the selected privileges.
	 *
	 * @return the selected privileges
	 */
	public List<String> getSelectedPrivileges() {
		return selectedPrivileges;
	}

	/**
	 * Sets the selected privileges.
	 *
	 * @param selectedPrivileges the new selected privileges
	 */
	public void setSelectedPrivileges(List<String> selectedPrivileges) {
		this.selectedPrivileges = selectedPrivileges;
	}

	/**
	 * Gets the user list.
	 *
	 * @return the user list
	 */
	public List<User> getUserList() {
		return userList;
	}
	
	/**
	 * Fill value for user list.
	 */
	public void fillValueForUserList() {
		if (userList == null || userList.isEmpty()) {
			userList = new ArrayList<User>();
		} else {
			userList.clear();
		}
		
		List<OrgUnit> orgUnitList = userBLO.getManagerOrgUnitList(
				loggedInUser.getUsrUserName().toString());
		userList = userHome.findUserByListOfOrgUnitID(orgUnitList, true, true);
		for (int i = 0; i < userList.size(); ++i) {
			final User tempUser = userList.get(i);
			if (tempUser.getUsrUserId().intValue() == loggedInUser
					.getUsrUserId().intValue()) {
				userList.remove(i);
				break;
			}
		}
	}

	/**
	 * Sets the user list.
	 *
	 * @param userList the new user list
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	/**
	 * Gets the from date.
	 *
	 * @return the from date
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * Sets the from date.
	 *
	 * @param fromDate the new from date
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * Gets the to date.
	 *
	 * @return the to date
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * Sets the to date.
	 *
	 * @param toDate the new to date
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the selected user.
	 *
	 * @return the selected user
	 */
	public User getSelectedUser() {
		if (!updating) {
			if (userList != null && userList.size() > 0) { 
				selectedUser = userList.get(0);
			}
		}
		return selectedUser;
	}

	/**
	 * Sets the selected user.
	 *
	 * @param selectedUser the new selected user
	 */
	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	/**
	 * Checks if is updating.
	 *
	 * @return true, if is updating
	 */
	public boolean isUpdating() {
		return updating;
	}

	/**
	 * Sets the updating.
	 *
	 * @param updating the new updating
	 */
	public void setUpdating(boolean updating) {
		this.updating = updating;
	}

	/**
	 * Gets the id of selected pt.
	 *
	 * @return the id of selected pt
	 */
	public int getIdOfSelectedPT() {
		return idOfSelectedPT;
	}

	/**
	 * Sets the id of selected pt.
	 *
	 * @param idOfSelectedPT the new id of selected pt
	 */
	public void setIdOfSelectedPT(int idOfSelectedPT) {
		this.idOfSelectedPT = idOfSelectedPT;
		if (updating) {
			privilegeTransferHome.setId(idOfSelectedPT);
			updatingPT = privilegeTransferHome.getInstance();
			if (updatingPT == null) {
				return;
			} else {
				selectedUser = updatingPT.getUserByRotUsrToUserId();
				fromDate = updatingPT.getRotFromDate();
				toDate = updatingPT.getRotToDate();
				description = updatingPT.getRotDescription();
				orgUnit = updatingPT.getOrgUnit();
			}
		}
	}

	/**
	 * Gets the updating pt.
	 *
	 * @return the updating pt
	 */
	public PrivilegeTransfer getUpdatingPT() {
		return updatingPT;
	}

	/**
	 * Sets the updating pt.
	 *
	 * @param updatingPT the new updating pt
	 */
	public void setUpdatingPT(PrivilegeTransfer updatingPT) {
		this.updatingPT = updatingPT;
	}

	/**
	 * Gets the org unit.
	 *
	 * @return the org unit
	 */
	public OrgUnit getOrgUnit() {
		return orgUnit;
	}

	/**
	 * Sets the org unit.
	 *
	 * @param orgUnit the new org unit
	 */
	public void setOrgUnit(OrgUnit orgUnit) {
		this.orgUnit = orgUnit;
	}

	/**
	 * Save.
	 *
	 * @return the string
	 */
	public String save() {
		if (selectedPrivileges == null) {
			return "failed";
		}

		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		today.set(Calendar.AM_PM, Calendar.AM);
		today.set(Calendar.HOUR, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);

		Calendar from = Calendar.getInstance();
		from.setTime(fromDate);
		from.set(Calendar.HOUR, 0);
		from.set(Calendar.MINUTE, 0);
		from.set(Calendar.SECOND, 0);
		from.set(Calendar.MILLISECOND, 0);

		Calendar to = Calendar.getInstance();
		to.setTime(toDate);
		to.set(Calendar.HOUR, 23);
		to.set(Calendar.MINUTE, 59);
		to.set(Calendar.SECOND, 59);
		to.set(Calendar.MILLISECOND, 999);

		// if (from.getTimeInMillis() < today.getTimeInMillis()) {
		// FacesMessages.instance().clear();
		// FacesMessages.instance().addFromResourceBundle(
		// org.jboss.seam.international.StatusMessage.Severity.ERROR,
		// "com.bosch.rts.blo.PrivilegeTransferBLO.futureFromDate");
		// return "failed";
		// }
		// if (to.getTimeInMillis() < today.getTimeInMillis()) {
		// FacesMessages.instance().clear();
		// FacesMessages.instance().addFromResourceBundle(
		// org.jboss.seam.international.StatusMessage.Severity.ERROR,
		// "com.bosch.rts.blo.PrivilegeTransferBLO.futureToDate");
		// return "failed";
		// }

		if (to.getTimeInMillis() < from.getTimeInMillis()) {
			FacesMessages.instance().clear();
			FacesMessages
					.instance()
					.addFromResourceBundle(
							org.jboss.seam.international.StatusMessage.Severity.ERROR,
							"com.bosch.rts.blo.PrivilegeTransferBLO.FromDateBeforeToDate");
			return "failed";
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		FacesMessages.instance().clear();
		StringBuilder resultMessages = new StringBuilder("Privileges: ");

		for (int i = 0; i < selectedPrivileges.size(); ++i) {
			privilegeTransferHome.clearInstance();
			String pvlTemp = selectedPrivileges.get(i).replace(' ', '_');
			// int firstIndex = pvlTemp.indexOf("(");
			// privilegeTransferHome.getInstance().setPrivilege(
			// privilegeHome.findPrivilegeByName(pvlTemp.substring(0,
			// firstIndex - 1)));
			privilegeTransferHome.getInstance().setPrivilege(
					privilegeHome.findPrivilegeByName(pvlTemp));
			privilegeTransferHome.getInstance().setRotDescription(description);

			privilegeTransferHome.getInstance().setRotFromDate(from.getTime());
			privilegeTransferHome.getInstance().setRotToDate(to.getTime());
			privilegeTransferHome.getInstance().setUserByRotUsrFromUserId(
					loggedInUser);
			privilegeTransferHome.getInstance().setUserByRotUsrToUserId(
					selectedUser);
			privilegeTransferHome.getInstance().setOrgUnit(orgUnit);
			String result = privilegeTransferHome.persist();
			if (result.equals("persisted")) {
				resultMessages.append(selectedPrivileges.get(i).replace('_',
						' '));
				resultMessages.append(", ");
				// FacesMessages
				// .instance()
				// .addFromResourceBundle(
				// org.jboss.seam.international.StatusMessage.Severity.INFO,
				// "com.bosch.rts.blo.PrivilegeTransferBLO.transferSuccessfully",
				// selectedPrivileges.get(i).replace('_', ' '),
				// selectedUser.toString(), sdf.format(fromDate),
				// sdf.format(toDate));
			} else
				try {
					{
						// if any problem occur we should rollback transaction
						// rather than only display error message
						Transaction.instance().rollback();
						FacesMessages
								.instance()
								.addFromResourceBundle(
										org.jboss.seam.international.StatusMessage.Severity.ERROR,
										"com.bosch.rts.blo.PrivilegeTransferBLO.transferFailed",
										selectedPrivileges.get(i).replace('_',
												' '), selectedUser.getUsrFullName());
						return "fail";
					}
				} catch (Exception e) {
					log.error("Execute save privilege transfer method getting error------------------");
				}
		}
		// no problem occur
		// and some privileges are selected
		if (selectedPrivileges.size() > 0) {
			FacesMessages.instance().clear();
			resultMessages.replace(
					resultMessages.length() - 2,
					resultMessages.length() - 1,
					" has been transferred successfully to "
							+ selectedUser.getUsrFullName());
			resultMessages.append("from " + sdf.format(fromDate) + " to "
					+ sdf.format(toDate) + "!");
			FacesMessages.instance().add(Severity.INFO,
					resultMessages.toString());
		}

		return "persisted";
	}

	
	/**
	 * Update.
	 *
	 * @return the string
	 */
	public String update() {
		// if (toDate.getTime() < fromDate.getTime()) {
		// FacesMessages.instance().clear();
		// FacesMessages
		// .instance()
		// .addFromResourceBundle(
		// org.jboss.seam.international.StatusMessage.Severity.ERROR,
		// "com.bosch.rts.blo.PrivilegeTransferBLO.FromDateBeforeToDate");
		// return "failed";
		// }

		Calendar today = Calendar.getInstance();
		today.setTime(new Date());
		today.set(Calendar.AM_PM, Calendar.AM);
		today.set(Calendar.HOUR, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);

		Calendar from = Calendar.getInstance();
		from.setTime(fromDate);
		from.set(Calendar.HOUR, 0);
		from.set(Calendar.MINUTE, 0);
		from.set(Calendar.SECOND, 0);
		from.set(Calendar.MILLISECOND, 0);
		Calendar to = Calendar.getInstance();
		to.setTime(toDate);
		to.set(Calendar.HOUR, 23);
		to.set(Calendar.MINUTE, 59);
		to.set(Calendar.SECOND, 59);
		to.set(Calendar.MILLISECOND, 999);
		//
		// if (to.getTimeInMillis() < today.getTimeInMillis()) {
		// FacesMessages.instance().clear();
		// FacesMessages.instance().addFromResourceBundle(
		// org.jboss.seam.international.StatusMessage.Severity.ERROR,
		// "com.bosch.rts.blo.PrivilegeTransferBLO.futureToDate");
		// return "failed";
		// }

		if (to.getTimeInMillis() < from.getTimeInMillis()) {
			FacesMessages.instance().clear();
			FacesMessages
					.instance()
					.addFromResourceBundle(
							org.jboss.seam.international.StatusMessage.Severity.ERROR,
							"com.bosch.rts.blo.PrivilegeTransferBLO.FromDateBeforeToDate");
			return "failed";
		}

		privilegeTransferHome.clearInstance();
		privilegeTransferHome.setInstance(updatingPT);
		privilegeTransferHome.getInstance().setRotDescription(description);

		privilegeTransferHome.getInstance().setRotFromDate(from.getTime());
		privilegeTransferHome.getInstance().setRotToDate(to.getTime());
		privilegeTransferHome.getInstance().setUserByRotUsrToUserId(
				selectedUser);
		privilegeTransferHome.getInstance().setOrgUnit(orgUnit);
		String result = privilegeTransferHome.update();
		if (result.equals("updated")) {
			FacesMessages.instance().clear();
			FacesMessages
					.instance()
					.addFromResourceBundle(
							org.jboss.seam.international.StatusMessage.Severity.INFO,
							"com.bosch.rts.blo.PrivilegeTransferBLO.updateSuccessfully",
							updatingPT.getPrivilege().toString());
		} else {
			FacesMessages.instance().clear();
			FacesMessages.instance().addFromResourceBundle(
					org.jboss.seam.international.StatusMessage.Severity.ERROR,
					"com.bosch.rts.blo.PrivilegeTransferBLO.updateFailed",
					updatingPT.getPrivilege().toString());
		}
		return result;
	}

	/**
	 * Removes the pt.
	 *
	 * @param pt the pt
	 */
	public void removePT(PrivilegeTransfer pt) {
		privilegeTransferHome.clearInstance();
		privilegeTransferHome.setInstance(pt);
		String result = privilegeTransferHome.remove();
		if (result.equals("removed")) {
			FacesMessages.instance().clear();
			FacesMessages
					.instance()
					.addFromResourceBundle(
							org.jboss.seam.international.StatusMessage.Severity.INFO,
							"com.bosch.rts.blo.PrivilegeTransferBLO.removeSuccessfully",
							pt.getPrivilege().toString(),
							pt.getUserByRotUsrToUserId().toString());
		} else {
			FacesMessages.instance().clear();
			FacesMessages.instance().addFromResourceBundle(
					org.jboss.seam.international.StatusMessage.Severity.ERROR,
					"com.bosch.rts.blo.PrivilegeTransferBLO.removeFailed",
					pt.getPrivilege().toString(),
					pt.getUserByRotUsrToUserId().toString());
		}
	}
}
