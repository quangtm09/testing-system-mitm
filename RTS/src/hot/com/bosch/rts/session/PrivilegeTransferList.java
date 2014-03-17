package com.bosch.rts.session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.framework.EntityQuery;

import com.bosch.rts.entity.DummyAllUser;
import com.bosch.rts.entity.Privilege;
import com.bosch.rts.entity.PrivilegeTransfer;
import com.bosch.rts.entity.RoleHasPrivilege;
import com.bosch.rts.entity.User;
import com.bosch.rts.entity.UserHasUserRole;
import com.bosch.rts.entity.UserRole;

/**
 * The Class PrivilegeTransferList.
 */
@Name("privilegeTransferList")
public class PrivilegeTransferList extends EntityQuery<PrivilegeTransfer> {

	/** The logged in user. */
	@In(scope = ScopeType.SESSION, required = false)
	User loggedInUser;
	
	/** The user home. */
	@In(create = true)
	UserHome userHome;
	
	/** The privilege home. */
	@In(create = true)
	PrivilegeHome privilegeHome;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8524756342201562593L;

	/** The max results. */
	public static int MAX_RESULTS = 20;
	
	/** The order number. */
	private int orderNumber;
	
	/** The current page. */
	private int currentPage;

	/** The privilege list. */
	private List<String> privilegeList;
	
	/** The user list. */
	private List<User> userList;
	
	/** The selected privilege. */
	private String selectedPrivilege = "All Privilege";
	
	/** The selected user. */
	private User selectedUser = DummyAllUser.getInstance();
	
	/** The from date. */
	private Date fromDate;
	
	/** The to date. */
	private Date toDate;
	
	/** The privilege for search. */
	private String privilegeForSearch;

	/** The Constant EJBQL. */
	private static final String EJBQL = "select privilegeTransfer from PrivilegeTransfer privilegeTransfer";
	
	/** The Constant RESTRICTIONS. */
	private static final String[] RESTRICTIONS = {
			"privilegeTransfer.userByRotUsrFromUserId.usrUserId = #{loggedInUser.usrUserId}",
			"privilegeTransfer.privilege.prvPrivilegeName like #{privilegeTransferList.privilegeForSearch}",
			"privilegeTransfer.userByRotUsrToUserId.usrUserId = #{privilegeTransferList.selectedUser.usrUserId}",
			"privilegeTransfer.rotFromDate >= #{privilegeTransferList.fromDate}",
			"privilegeTransfer.rotToDate <= #{privilegeTransferList.toDate}", };

	/**
	 * Instantiates a new privilege transfer list.
	 */
	public PrivilegeTransferList() {
		setEjbql(EJBQL);
		setRestrictionLogicOperator("and");
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	/**
	 * Gets the order number.
	 *
	 * @return the order number
	 */
	public int getOrderNumber() {
		return ++orderNumber;
	}

	/**
	 * Sets the order number.
	 *
	 * @param orderNumber the new order number
	 */
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * Gets the current page.
	 *
	 * @return the current page
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * Sets the current page.
	 *
	 * @param currentPage the new current page
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * Gets the privilege list.
	 *
	 * @return the privilege list
	 */
	public List<String> getPrivilegeList() {
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
	 * Gets the user list.
	 *
	 * @return the user list
	 */
	public List<User> getUserList() {
		fillValueForUserList();
		return userList;
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
	 * Gets the selected privilege.
	 *
	 * @return the selected privilege
	 */
	public String getSelectedPrivilege() {
		return selectedPrivilege;
	}

	/**
	 * Sets the selected privilege.
	 *
	 * @param selectedPrivilege the new selected privilege
	 */
	public void setSelectedPrivilege(String selectedPrivilege) {
		this.selectedPrivilege = selectedPrivilege;
	}

	/**
	 * Gets the selected user.
	 *
	 * @return the selected user
	 */
	public User getSelectedUser() {
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
	 * Gets the privilege for search.
	 *
	 * @return the privilege for search
	 */
	public String getPrivilegeForSearch() {
		return privilegeForSearch;
	}

	/**
	 * Sets the privilege for search.
	 *
	 * @param privilegeForSearch the new privilege for search
	 */
	public void setPrivilegeForSearch(String privilegeForSearch) {
		this.privilegeForSearch = privilegeForSearch;
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

		userList = userHome.findUserByOrgUnitID(loggedInUser.getOrgUnit().getOrgUnitId());
		for (int i = 0; i < userList.size(); ++i) {
			final User tempUser = userList.get(i);
			if (tempUser.getUsrUserId().intValue() == loggedInUser
					.getUsrUserId().intValue()) {
				userList.remove(i);
				break;
			}
		}
		userList.add(0, DummyAllUser.getInstance());
	}

	/**
	 * Fill value for pvl list.
	 */
	public void fillValueForPvlList() {
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
						privilegeList.add(pvl.toString());
					}
				}
			}
		}
		privilegeList.add(0, "All Privilege");
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.EntityQuery#getResultList()
	 */
	@Override
	public List<PrivilegeTransfer> getResultList() {
		// TODO Auto-generated method stub
		List<String> restrictions = new ArrayList<String>();

		privilegeForSearch = selectedPrivilege.replace(' ', '_');

		// current logging in user
		restrictions.add(RESTRICTIONS[0]);

		// privilege
		if (!(selectedPrivilege == null || selectedPrivilege
				.equals("All Privilege"))) {
			restrictions.add(RESTRICTIONS[1]);
		}

		// user
		if (!(selectedUser == null || selectedUser instanceof DummyAllUser)) {
			restrictions.add(RESTRICTIONS[2]);
		}

		// date
		if (fromDate != null) {
			Calendar from = Calendar.getInstance();
			from.setTime(fromDate);
			from.set(Calendar.HOUR, 0);
			from.set(Calendar.MINUTE, 0);
			from.set(Calendar.SECOND, 0);
			from.set(Calendar.MILLISECOND, 0);
			fromDate = from.getTime();
			System.out.println(fromDate);
			restrictions.add(RESTRICTIONS[3]);
		}
		if (toDate != null) {
			Calendar to = Calendar.getInstance();
			to.setTime(toDate);
			to.set(Calendar.HOUR, 23);
			to.set(Calendar.MINUTE, 59);
			to.set(Calendar.SECOND, 59);
			to.set(Calendar.MILLISECOND, 999);
			toDate = to.getTime();
			System.out.println(toDate);
			restrictions.add(RESTRICTIONS[4]);
		}

		setRestrictionExpressionStrings(restrictions);
		if (currentPage == 0) {
			currentPage = 1;
		}

		super.refresh();
		List<PrivilegeTransfer> searchResultList = super.getResultList();
		super.getEntityManager().flush();
		if (searchResultList == null) {
			searchResultList = new ArrayList<PrivilegeTransfer>();
		}

		return searchResultList;
	}

	/**
	 * Btn search clicked.
	 */
	public void btnSearchClicked() {
		FacesMessages.instance().clear();
		getResultList();
		orderNumber = 0;
	}

}
