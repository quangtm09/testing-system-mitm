package com.bosch.rts.session;

import com.bosch.rts.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

/**
 * The Class TechnicalResultAttributeValueHome.
 */
@Name("technicalResultAttributeValueHome")
public class TechnicalResultAttributeValueHome extends
		EntityHome<TechnicalResultAttributeValue> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1538751234316570718L;

	/**
	 * Sets the technical result attribute value id.
	 *
	 * @param id the new technical result attribute value id
	 */
	public void setTechnicalResultAttributeValueId(Integer id) {
		setId(id);
	}

	/**
	 * Gets the technical result attribute value id.
	 *
	 * @return the technical result attribute value id
	 */
	public Integer getTechnicalResultAttributeValueId() {
		return (Integer) getId();
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#createInstance()
	 */
	@Override
	protected TechnicalResultAttributeValue createInstance() {
		TechnicalResultAttributeValue technicalResultAttributeValue = new TechnicalResultAttributeValue();
		return technicalResultAttributeValue;
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
	public TechnicalResultAttributeValue getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
