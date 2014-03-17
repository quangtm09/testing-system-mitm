package com.bosch.rts.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.bosch.rts.entity.ControlType;

/**
 * The Class ControlTypeList.
 */
@Name("controlTypeList")
public class ControlTypeList extends EntityQuery<ControlType> {

	/** serialVersionUID. */
	private static final long serialVersionUID = -4183370294026223778L;

	/** The Constant EJBQL. */
	private static final String EJBQL = "select controlType from ControlType controlType";
	
	/** The control type. */
	private ControlType controlType = new ControlType();

	/**
	 * Instantiates a new control type list.
	 */
	public ControlTypeList() {
		setEjbql(EJBQL);
	}

	/**
	 * Gets the control type.
	 *
	 * @return the control type
	 */
	public ControlType getControlType() {
		return controlType;
	}
}
