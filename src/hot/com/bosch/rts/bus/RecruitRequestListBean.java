package com.bosch.rts.bus;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.bosch.rts.entity.RecruitRequest;
import com.bosch.rts.entity.User;
import com.bosch.rts.session.RecruitRequestList;

/**
 * The Class RecruitRequestListBean.
 */
@Name("recruitRequestListBean")
public class RecruitRequestListBean implements java.io.Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8771730509289836804L;
	
	/** The recruit request list. */
	@In(create = true)
	RecruitRequestList recruitRequestList;
	
	/** The logged in user. */
	@In(scope = ScopeType.SESSION, value = "loggedInUser")
	User loggedInUser;
	
	/** The max result. */
	int maxResult = 20;

	/**
	 * Gets the max result.
	 *
	 * @return the max result
	 */
	public int getMaxResult() {
		return maxResult;
	}

	/**
	 * Sets the max result.
	 *
	 * @param maxResult the new max result
	 */
	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	/**
	 * Instantiates a new recruit request list bean.
	 */
	public RecruitRequestListBean() {
	}

	/**
	 * Search.
	 */
	public void search() {
		recruitRequestList.setOrderColumn(null);
		recruitRequestList.setMaxResults(maxResult);
		recruitRequestList.setFirstResult(0);
		recruitRequestList.getResultList();
	}

	/**
	 * Sets the order column.
	 *
	 * @param propertyPath the new order column
	 */
	public void setOrderColumn(String propertyPath) {
		if (propertyPath.equalsIgnoreCase(recruitRequestList.getOrderColumn())) {
			if (recruitRequestList.getOrderDirection().equalsIgnoreCase("desc")) {
				recruitRequestList.setOrderDirection("asc");
			} else {
				recruitRequestList.setOrderDirection("desc");
			}
		} else {
			recruitRequestList.setOrderColumn(propertyPath);
			recruitRequestList.setOrderDirection("desc");
		}
		recruitRequestList.setFirstResult(0);
	}

	/**
	 * Gets the order direction.
	 *
	 * @return the order direction
	 */
	public String getOrderDirection() {
		return recruitRequestList.getOrderDirection();
	}

	/**
	 * Gets the order column.
	 *
	 * @return the order column
	 */
	public String getOrderColumn() {
		return recruitRequestList.getOrderColumn();
	}

	/**
	 * Checks if is user create the recruit request.
	 *
	 * @param recruitRequest the recruit request
	 * @return true, if is user create the recruit request
	 */
	public boolean isUserCreateTheRecruitRequest(RecruitRequest recruitRequest) {
		if (recruitRequest != null) {
			if (recruitRequest.getUser().getUsrUserId()
					.equals(loggedInUser.getUsrUserId())) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
}
