package com.bosch.rts.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;

import com.bosch.rts.entity.TechnicalResultLine;
import com.bosch.rts.entity.TechnicalResultLineAttribute;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSUtils;

// TODO: Auto-generated Javadoc
/**
 * CRUD for technical result line.
 *
 * @author KHB1HC
 */
@Name("technicalResultLineHome")
public class TechnicalResultLineHome extends EntityHome<TechnicalResultLine> {

	/** serialVersionUID. */
	private static final long serialVersionUID = -3844347106562694287L;
	
	/** The log. */
	@Logger
	private transient Log log;


	/** The status messages. */
	@In(create = true)
	private transient StatusMessages statusMessages;
	
	/** The technical result line list. */
	@In(create = true)
	private transient TechnicalResultLineList technicalResultLineList;
	
	/** The technical result line attribute list. */
	@In(create = true)
	private transient TechnicalResultLineAttributeList technicalResultLineAttributeList;
	
	
	/** The credentials. */
	@In(create = true)
	private transient Credentials credentials;
	
	/**
	 * Sets the technical result line id.
	 *
	 * @param id the new technical result line id
	 */
	public void setTechnicalResultLineId(Integer id) {
		setId(id);
	}

	/**
	 * Gets the technical result line id.
	 *
	 * @return the technical result line id
	 */
	public Integer getTechnicalResultLineId() {
		return (Integer) getId();
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#createInstance()
	 */
	@Override
	protected TechnicalResultLine createInstance() {
		TechnicalResultLine technicalResultLine = new TechnicalResultLine();
		return technicalResultLine;
	}
	
	/**
	 * New instance.
	 *
	 * @return the technical result line
	 */
	public TechnicalResultLine newInstance(){
		TechnicalResultLine technicalResultLine = new TechnicalResultLine();
		this.setInstance(technicalResultLine);
		return technicalResultLine;
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
	public TechnicalResultLine getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	/**
	 * Gets the line attributes.
	 *
	 * @return the line attributes
	 */
	public List<TechnicalResultLineAttribute> getLineAttributes() {
		return getInstance() == null 
				? null
				: new ArrayList<TechnicalResultLineAttribute>(getInstance().getTechnicalResultLineAttributes());
	}	
	
	/**
	 * Create new line.
	 *
	 * @return persisted if successfully, otherwise failure
	 */
	public String createLine(){
		isError = false;
		String result = RTSConstants.FAILURE; 
		final Date createdOn = new Date();	
		this.getInstance().setCreatedOn(createdOn);
		
		final String createdBy = credentials.getUsername();
		this.getInstance().setCreatedBy(createdBy);		
		
		try {
			statusMessages.clear();
			result =  this.persist();
		} catch (Exception e) {
			log.error("Error in creating line: " + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.line.create");
			setError(true);
		}	
		
		if(RTSConstants.PERSISTED.equals(result)){
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.INFO, "success.line.create");
		} else {
			setError(true);
		}
		
		return result;
	}
	
	/**
	 * Update line properties and its attribute.
	 *
	 * @return updated if successfully, otherwise failure
	 */
	public String updateLine(){
		String result = RTSConstants.FAILURE; 
		final Date updatedOn = new Date();	
		this.getInstance().setUpdatedOn(updatedOn);
		
		final String updatedBy = credentials.getUsername();
		this.getInstance().setUpdatedBy(updatedBy);		
	
		try {
			
			final List<TechnicalResultLineAttribute> oldAttributes = this.getInstance().getTechnicalResultLineAttributeList();
			if(RTSUtils.isNotEmpty(oldAttributes)){
				//some attributes selected
				if(RTSUtils.isNotEmpty(this.selectedAttributes)){
					oldAttributes.removeAll(this.selectedAttributes);
					if(RTSUtils.isNotEmpty(oldAttributes)){
						for(TechnicalResultLineAttribute oldAttribute : oldAttributes){
							this.getInstance().getTechnicalResultLineAttributes().remove(oldAttribute);
							oldAttribute.setTechnicalResultLine(null);
							getEntityManager().persist(oldAttribute);
						}
					}
					
					for(TechnicalResultLineAttribute attribute : this.selectedAttributes){
						attribute.setTechnicalResultLine(this.getInstance());
						getEntityManager().persist(attribute);
					}					
				} else {
					for(TechnicalResultLineAttribute oldAttribute : oldAttributes){
						this.getInstance().getTechnicalResultLineAttributes().remove(oldAttribute);
						oldAttribute.setTechnicalResultLine(null);
						getEntityManager().persist(oldAttribute);
					}					
				}
			} else {
				if(RTSUtils.isNotEmpty(this.selectedAttributes)){
					for(TechnicalResultLineAttribute attribute : this.selectedAttributes){
						attribute.setTechnicalResultLine(this.getInstance());
						getEntityManager().persist(attribute);
					}
				}
			}
			
		} catch (Exception e) {
			log.error("Error in updating line: " + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.line.update");
		}	
		
		try {
			result =  this.update();
		} catch (Exception e) {
			log.error("Error in updating line: " + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.line.update");
		}	
		
		if(RTSConstants.UPDATED.equals(result)){
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.INFO, "success.line.update");
			if(this.getInstance() != null){
				this.clearInstance();
				this.setId(null);
			}
		} else {
			setError(true);
		}
		
		return result;
	}
	
	/**
	 * Remove line and remove line for attributes.
	 * Reset instance before redirecting.
	 * @return removed if successfully, otherwise failure.
	 */
	public String deleteLine(){
		isError = false;
		String result = RTSConstants.FAILURE; 
		
		//remove line of attributes first
		if(RTSUtils.isNotEmpty(this.getInstance().getTechnicalResultLineAttributes())){
			for(TechnicalResultLineAttribute attribute : this.getInstance().getTechnicalResultLineAttributes()){
				this.getInstance().getTechnicalResultLineAttributes().remove(attribute);
				attribute.setTechnicalResultLine(null);
				try {		
					statusMessages.clear();
					getEntityManager().persist(attribute);
				} catch (Exception e) {
					log.error("Error in removing line for attribute: " + e);
					statusMessages.clear();
					statusMessages.addFromResourceBundle(Severity.ERROR, "error.line.attribute.delete");
					isError = true;
				}				
			}
		}
		
		//delete line
		try {		
			statusMessages.clear();
			result =  this.remove();
		} catch (Exception e) {
			log.error("Error in removing line: " + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.line.delete");
			setError(true);
		}	
		
		if(RTSConstants.REMOVED.equals(result)){
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.INFO, "success.line.delete");
			if(this.getInstance() != null){
				this.clearInstance();
				this.setId(null);
			}
		} else {
			setError(true);
		}		
		
		return result;
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
	
	/** The lines. */
	private List<TechnicalResultLine> lines = null;
	
	/**
	 * Gets the lines.
	 *
	 * @return the lines
	 */
	public List<TechnicalResultLine> getLines() {
		return this.lines;
	}

	/**
	 * Sets the lines.
	 *
	 * @param lines the new lines
	 */
	public void setLines(List<TechnicalResultLine> lines) {
		this.lines = lines;
	}

	/**
	 * Initialize values for lines for data-table display.
	 */
	public void onloadLines(){
		log.info("Method onloadLines() called.");
		RTSUtils.resetList(lines);
		lines = technicalResultLineList.getResultList();
		log.info("End of method onloadLines().");
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
	
	/** The attributes. */
	private List<TechnicalResultLineAttribute> attributes = null;
	
	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	public List<TechnicalResultLineAttribute> getAttributes() {
		return this.attributes;
	}

	/**
	 * Sets the attributes.
	 *
	 * @param attributes the new attributes
	 */
	public void setAttributes(List<TechnicalResultLineAttribute> attributes) {
		this.attributes = attributes;
	}

	/**
	 * Initialize values for attributes. This is used by pick-list elements selection.
	 */
	public void onloadAttributes(){
		log.info("Method onloadAttributes() called.");
		RTSUtils.resetList(attributes);
		attributes = technicalResultLineAttributeList.getNotUsedAttributes();
		if(attributes == null){
			attributes = new ArrayList<TechnicalResultLineAttribute>();
		}
		
		attributes.addAll(this.getInstance().getTechnicalResultLineAttributes());
		log.info("End of method onloadAttributes().");
	}
	
	/** The selected attributes. */
	private List<TechnicalResultLineAttribute> selectedAttributes = null;

	/**
	 * Gets the selected attributes.
	 *
	 * @return the selected attributes
	 */
	public List<TechnicalResultLineAttribute> getSelectedAttributes() {
		return this.selectedAttributes;
	}

	/**
	 * Sets the selected attributes.
	 *
	 * @param selectedAttributes the new selected attributes
	 */
	public void setSelectedAttributes(List<TechnicalResultLineAttribute> selectedAttributes) {
		this.selectedAttributes = selectedAttributes;
	}

	
	/**
	 * Initialize values for selected attributes. This is used by pick-list elements selection.
	 */
	public void onloadSelectedAttributes(){
		log.info("Method onloadSelectedAttributes() called.");
		RTSUtils.resetList(selectedAttributes);
		selectedAttributes = new ArrayList<TechnicalResultLineAttribute>(this.getInstance().getTechnicalResultLineAttributes());
		if(RTSUtils.isBlank(selectedAttributes)){
			selectedAttributes = new ArrayList<TechnicalResultLineAttribute>();
		}		
		log.info("selectedAttributes size: " + selectedAttributes.size());
		log.info("End of method onloadSelectedAttributes().");
	}	
		
}
