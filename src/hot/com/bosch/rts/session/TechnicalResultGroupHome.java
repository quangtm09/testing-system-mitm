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

import com.bosch.rts.entity.TechnicalResultGroup;
import com.bosch.rts.entity.TechnicalResultLine;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSUtils;

/**
 * CRUD for technical results groups.
 *
 * @author KHB1HC
 */
@Name("technicalResultGroupHome")
public class TechnicalResultGroupHome extends EntityHome<TechnicalResultGroup> {

	/** serialVersionUID. */
	private static final long serialVersionUID = -177774286534542266L;
	
	/** The log. */
	@Logger
	private transient Log log;

	/** The status messages. */
	@In(create = true)
	private transient StatusMessages statusMessages;
	
	/** The technical result group list. */
	@In(create = true)
	private transient TechnicalResultGroupList technicalResultGroupList;
	
	/** The technical result line list. */
	@In(create = true)
	private transient TechnicalResultLineList technicalResultLineList;
	
	/** The credentials. */
	@In(create = true)
	private transient Credentials credentials;

	/**
	 * Sets the technical result group id.
	 *
	 * @param id the new technical result group id
	 */
	public void setTechnicalResultGroupId(Integer id) {
		setId(id);
	}

	/**
	 * Gets the technical result group id.
	 *
	 * @return the technical result group id
	 */
	public Integer getTechnicalResultGroupId() {
		return (Integer) getId();
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#createInstance()
	 */
	@Override
	protected TechnicalResultGroup createInstance() {
		TechnicalResultGroup technicalResultGroup = new TechnicalResultGroup();
		return technicalResultGroup;
	}
	
	/**
	 * New instance.
	 *
	 * @return the technical result group
	 */
	public TechnicalResultGroup newInstance() {
		TechnicalResultGroup technicalResultGroup = new TechnicalResultGroup();
		this.setInstance(technicalResultGroup);
		return technicalResultGroup;
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
	public TechnicalResultGroup getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}
	
	/** The technical result groups. */
	private List<TechnicalResultGroup> technicalResultGroups = null;
	
	/**
	 * Gets the technical result groups.
	 *
	 * @return the technical result groups
	 */
	public List<TechnicalResultGroup> getTechnicalResultGroups() {
		return this.technicalResultGroups;
	}

	/**
	 * Sets the technical result groups.
	 *
	 * @param technicalResultGroups the new technical result groups
	 */
	public void setTechnicalResultGroups(List<TechnicalResultGroup> technicalResultGroups) {
		this.technicalResultGroups = technicalResultGroups;
	}

	/**
	 * Initialize groups for data-table display.
	 */
	public void onloadTechnicalResultGroups(){
		RTSUtils.resetList(technicalResultGroups);
		technicalResultGroups = technicalResultGroupList.getResultList();		
	}
	
	/**
	 * Create new group.
	 *
	 * @return persisted if successfully, otherwise failure
	 */
	public String createTechnicalResultGroup(){
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
			log.error("Error in creating group: " + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.group.create");
			isError = true;
		}	
		
		if(RTSConstants.PERSISTED.equals(result)){
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.INFO, "success.group.create");
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
	 * Update group properties and its lines.
	 *
	 * @return updated if successfully, otherwise failure
	 */
	public String updateTechnicalResultGroup(){
		String result = RTSConstants.FAILURE; 
		final Date updatedOn = new Date();	
		this.getInstance().setUpdatedOn(updatedOn);
		
		final String updatedBy = credentials.getUsername();
		this.getInstance().setUpdatedBy(updatedBy);				
		
		try {
		
			final List<TechnicalResultLine> oldLines = this.getInstance().getLinesList();
			if(RTSUtils.isNotEmpty(oldLines)){
				//some lines selected
				if(RTSUtils.isNotEmpty(this.selectedLines)){
					oldLines.removeAll(this.selectedLines);
					if(RTSUtils.isNotEmpty(oldLines)){
						for(TechnicalResultLine oldLine : oldLines){
							this.getInstance().getTechnicalResultLines().remove(oldLine);
							oldLine.setTechnicalResultGroup(null);
							getEntityManager().persist(oldLine);
						}
					}
					
					for(TechnicalResultLine line : this.selectedLines){
						line.setTechnicalResultGroup(this.getInstance());
						getEntityManager().persist(line);
					}					
				} else {
					for(TechnicalResultLine oldLine : oldLines){
						this.getInstance().getTechnicalResultLines().remove(oldLine);
						oldLine.setTechnicalResultGroup(null);
						getEntityManager().persist(oldLine);
					}					
				}
			} else {
				if(RTSUtils.isNotEmpty(this.selectedLines)){
					for(TechnicalResultLine line : this.selectedLines){
						line.setTechnicalResultGroup(this.getInstance());
						getEntityManager().persist(line);
					}
				}
			}
			
		} catch (Exception e) {
			log.error("Error in updating lines of group: " + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.group.update");	
		}	
		
		try {		
			statusMessages.clear();
			result =  this.update();
		} catch (Exception e) {
			log.error("Error in updating group: " + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.group.update");
		}	
		
		if(RTSConstants.UPDATED.equals(result)){
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.INFO, "success.group.update");
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
	 * Remove group and remove group for lines.
	 * Reset instance before redirecting.
	 * @return removed if successfully, otherwise failure.
	 */
	public String removeTechnicalResultGroup(){
		isError = false;
		String result = RTSConstants.FAILURE;	
		
		//remove group for lines frist
		if(RTSUtils.isNotEmpty(this.getInstance().getTechnicalResultLines())){
			for(TechnicalResultLine line : this.getInstance().getTechnicalResultLines()){
				this.getInstance().getTechnicalResultLines().remove(line);
				line.setTechnicalResultGroup(null);
				try {		
					statusMessages.clear();
					getEntityManager().persist(line);
				} catch (Exception e) {
					log.error("Error in removing group for lines: " + e);
					statusMessages.clear();
					statusMessages.addFromResourceBundle(Severity.ERROR, "error.group.line.delete");
					setError(true);
				}				
			}
		}
		
		//delete group
		try {		
			statusMessages.clear();
			result =  this.remove();
		} catch (Exception e) {
			log.error("Error in removing group: " + e);
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.group.delete");
			setError(true);
		}		
		
		if(RTSConstants.REMOVED.equals(result)){
			statusMessages.clear();
			statusMessages.addFromResourceBundle(Severity.INFO, "success.group.delete");
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
	 * Initialize lines for pick-list elements selection.
	 */
	public void onloadLines(){		
		log.info("Method onloadLines() called.");
		RTSUtils.resetList(lines);
		lines = technicalResultLineList.getNotUsedLines();
		if(lines == null){
			lines = new ArrayList<TechnicalResultLine>();
		}
		
		lines.addAll(this.getInstance().getTechnicalResultLines());
		log.info("End of method onloadLines().");
	}	
	 
	/** The selected lines. */
	public List<TechnicalResultLine> selectedLines = null;

	/**
	 * Gets the selected lines.
	 *
	 * @return the selected lines
	 */
	public List<TechnicalResultLine> getSelectedLines() {
		return this.selectedLines;
	}

	/**
	 * Sets the selected lines.
	 *
	 * @param selectedLines the new selected lines
	 */
	public void setSelectedLines(List<TechnicalResultLine> selectedLines) {
		this.selectedLines = selectedLines;
	}
	
	/**
	 * Initialize lines for selected pick-list elements display.
	 */
	public void onloadSelectedLines(){		
		log.info("Method onloadSelectedLines() called.");
		RTSUtils.resetList(selectedLines);
		selectedLines = new ArrayList<TechnicalResultLine>(this.getInstance().getTechnicalResultLines());
		if(RTSUtils.isBlank(selectedLines)){
			selectedLines = new ArrayList<TechnicalResultLine>();
		}		
		
		log.info("selectedAttributes size: " + selectedLines.size());
		log.info("End of method onloadSelectedLines().");
	}
	
}