package com.bosch.rts.session;


import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.security.Credentials;

import com.bosch.rts.blo.UserBLO;
import com.bosch.rts.entity.HrResult;
import com.bosch.rts.entity.InterviewSchedule;
import com.bosch.rts.utilities.RTSConstants;

/**
 * The Class HrResultHome.
 */
@Name("hrResultHome")
public class HrResultHome extends EntityHome<HrResult> {
	
	/** serialVersionUID. */
	private static final long serialVersionUID = -2695604304647233827L;

	/**
	 * Sets the hr result ihr hr result id.
	 *
	 * @param id the new hr result ihr hr result id
	 */
	public void setHrResultIhrHrResultId(Integer id) {
		setId(id);
	}

	/**
	 * Gets the hr result ihr hr result id.
	 *
	 * @return the hr result ihr hr result id
	 */
	public Integer getHrResultIhrHrResultId() {
		return (Integer) getId();
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#createInstance()
	 */
	@Override
	protected HrResult createInstance() {
		HrResult hrResult = new HrResult();
		return hrResult;
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
	public HrResult getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}
	
	/** The credentials. */
	@In(create=true)
	private Credentials credentials;
	
	/** The user blo. */
	@In(create=true)
	private UserBLO userBLO;
		
	/**
	 * Check editable hr result.
	 *
	 * @param _sch the _sch
	 * @return true, if successful
	 */
	public boolean checkEditableHrResult(final InterviewSchedule _sch){
		String userName = credentials.getUsername();
		int orgUnitId = _sch.getCandidate().getOrgUnit().getOrgUnitId();
		
		if( (_sch.getItsHrStatus().equalsIgnoreCase(RTSConstants.NEW))
			&& (_sch.getCandidate().getCddStatus().dbString().equals("TECHNICAL_PASS"))
			&& (userBLO.userHasPrivilege(userName, "ADD_HR_FEEDBACK", orgUnitId)) ){
			return true;
		}
		
		return false;		
	}
}
