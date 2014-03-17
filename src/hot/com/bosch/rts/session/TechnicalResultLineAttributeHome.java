package com.bosch.rts.session;

import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;

import com.bosch.rts.entity.ControlType;
import com.bosch.rts.entity.TechnicalResultLine;
import com.bosch.rts.entity.TechnicalResultLineAttribute;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSUtils;

/**
 * CRUD for Technical Result Attribute.
 *
 * @author KHB1HC
 */
@Name("technicalResultLineAttributeHome")
public class TechnicalResultLineAttributeHome extends EntityHome<TechnicalResultLineAttribute> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 8662067256040702326L;
		
	/** The technical result line home. */
	@In(create = true)
	private transient TechnicalResultLineHome technicalResultLineHome;
	
	/** The technical result line attribute list. */
	@In(create = true)
	private transient TechnicalResultLineAttributeList technicalResultLineAttributeList;	
	
	/** The log. */
	@Logger
	private transient Log log;

	/** The status messages. */
	@In(create = true)
	private transient StatusMessages statusMessages;	
	
	/** The credentials. */
	@In(create = true)
	private transient Credentials credentials;
	
	/** The control type list. */
	@In(create = true)
	private transient ControlTypeList controlTypeList;
	

	/**
	 * Sets the technical result line attribute id.
	 *
	 * @param id the new technical result line attribute id
	 */
	public void setTechnicalResultLineAttributeId(Integer id) {
		setId(id);
	}

	/**
	 * Gets the technical result line attribute id.
	 *
	 * @return the technical result line attribute id
	 */
	public Integer getTechnicalResultLineAttributeId() {
		return (Integer) getId();
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#createInstance()
	 */
	@Override
	protected TechnicalResultLineAttribute createInstance() {
		TechnicalResultLineAttribute technicalResultLineAttribute = new TechnicalResultLineAttribute();
		return technicalResultLineAttribute;
	}
	
	/**
	 * New instance.
	 *
	 * @return the technical result line attribute
	 */
	public TechnicalResultLineAttribute newInstance() {
		TechnicalResultLineAttribute technicalResultLineAttribute = new TechnicalResultLineAttribute();
		this.setInstance(technicalResultLineAttribute);
		return technicalResultLineAttribute;
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
		TechnicalResultLine technicalResultLine = technicalResultLineHome.getDefinedInstance();
		if (technicalResultLine != null) {
			getInstance().setTechnicalResultLine(technicalResultLine);
		}
	}

	/**
	 * Checks if is wired.
	 *
	 * @return true, if is wired
	 */
	public boolean isWired() {
		if (getInstance().getTechnicalResultLine() == null)
			return false;
		return true;
	}

	/**
	 * Gets the defined instance.
	 *
	 * @return the defined instance
	 */
	public TechnicalResultLineAttribute getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}
	
	/** The technical result line attributes. */
	private List<TechnicalResultLineAttribute> technicalResultLineAttributes = null;

	/**
	 * Gets the technical result line attributes.
	 *
	 * @return the technical result line attributes
	 */
	public List<TechnicalResultLineAttribute> getTechnicalResultLineAttributes() {
		return this.technicalResultLineAttributes;
	}

	/**
	 * Sets the technical result line attributes.
	 *
	 * @param technicalResultLineAttributes the new technical result line attributes
	 */
	public void setTechnicalResultLineAttributes(
			List<TechnicalResultLineAttribute> technicalResultLineAttributes) {
		this.technicalResultLineAttributes = technicalResultLineAttributes;
	}	

	/**
	 * Initialize values for attributes for data-table display.
	 */
	public void onloadAttributes(){
		RTSUtils.resetList(technicalResultLineAttributes);
		technicalResultLineAttributes = technicalResultLineAttributeList.getResultList();		
	}
	
	/**
	 * If page is error in form validating or system fails to CRUD entity,
	 * the page will keep the current instance with entered data. Otherwise, a new instance will be created.
	 */
	@Out(required = false, scope = ScopeType.EVENT)
	private boolean isError = false;

	/**
	 * Checks if is error.
	 *
	 * @return true, if is error
	 */
	public boolean isError() {
		return this.isError;
	}

	/**
	 * Sets the error.
	 *
	 * @param isError the new error
	 */
	public void setError(boolean isError) {
		this.isError = isError;
	}
	
	/**
	 * Create new attribute.
	 *
	 * @return persisted if successfully, otherwise failure
	 */
	public String createAttribute(){
		String result = RTSConstants.FAILURE; 
		isError = true;
		final Date createdOn = new Date();	
		this.getInstance().setCreatedOn(createdOn);
		
		final String createdBy = credentials.getUsername();
		this.getInstance().setCreatedBy(createdBy);		
		
		try {
			statusMessages.clear();
			result =  this.persist();
			if(RTSConstants.PERSISTED.equals(result)){
				statusMessages.clear();
				statusMessages.addFromResourceBundle(Severity.INFO, "success.attribute.create");
				
				if(this.getInstance() != null){
					this.clearInstance();
					this.setId(null);
				}				
			} else {
				this.setError(true);
			}
		} catch (Exception e) {			
			log.error("Error in creating line: " + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.attribute.create");
			this.setError(true);
		}	
		
		return result;
	}
	
	/**
	 * Update attribute properties and its value.
	 *
	 * @return updated if successfully, otherwise failure
	 */
	public String updateAttribute(){
		String result = RTSConstants.FAILURE; 
		isError = false;
		final Date updatedOn = new Date();	
		this.getInstance().setUpdatedOn(updatedOn);
		
		final String updatedBy = credentials.getUsername();
		this.getInstance().setUpdatedBy(updatedBy);		
		
		try {
			statusMessages.clear();
			result =  this.update();
			if(RTSConstants.UPDATED.equals(result)){
				statusMessages.clear();
				statusMessages.addFromResourceBundle(Severity.INFO, "success.attribute.update");
				if(this.getInstance() != null){
					this.clearInstance();
					this.setId(null);
				}
			} else {
				this.setError(true);
			}
		} catch (Exception e) {
			log.error("Error in updating attribute: " + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.attribute.update");		
			this.setError(true);
		}		
		
		return result;
	}
	
	/**
	 * Remove attribute.
	 * Reset instance before redirecting
	 * @return removed if successfully, otherwise failure.
	 */
	public String deleteAttribute(){
		String result = RTSConstants.FAILURE; 
		try {
			statusMessages.clear();
			result =  this.remove();			
		} catch (Exception e) {
			log.error("Error in deleting attribte: " + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.attribute.delete");
			this.setError(true);
		}	
		
		if(RTSConstants.REMOVED.equals(result)){
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.INFO, "success.attribute.delete");
			if(this.getInstance() != null){
				this.clearInstance();
				this.setId(null);
			} 
		} else {
			this.setError(true);
		}
		
		return result;
	}
	
	/** The from view. */
	private String fromView = null;

	/**
	 * Gets the from view.
	 *
	 * @return the from view
	 */
	public String getFromView() {
		return this.fromView;
	}

	/**
	 * Sets the from view.
	 *
	 * @param fromView the new from view
	 */
	public void setFromView(String fromView) {
		this.fromView = fromView;
	}	 
	
	/**
	 * Initialize control types for select-one-menu elements selection.
	 * @return controlTypes
	 */
	public List<ControlType> getControlTypes(){
		final List<ControlType> controlTypes = controlTypeList.getResultList();	
		return controlTypes;
	}

}
