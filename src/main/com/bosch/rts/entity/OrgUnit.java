package com.bosch.rts.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.validator.Length;

/**
 * The Class OrgUnit.
 */
@Entity
@Table(name = "org_unit")
public class OrgUnit implements java.io.Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = -1537865827369043874L;
	
	/** The org unit id. */
	private int orgUnitId;
	
	/** The unit name. */
	private String unitName;
	
	/** The parent id. */
	private Integer parentId;
	
	/** The type id. */
	private Integer typeId;
	
	/** The level path. */
	private String levelPath;
	
	/** The manager. */
	private User manager;
	
	/** The active state. */
	private Boolean activeState;

	/**
	 * Instantiates a new org unit.
	 */
	public OrgUnit() {
	}

	/**
	 * Instantiates a new org unit.
	 *
	 * @param orgUnitId the org unit id
	 */
	public OrgUnit(int orgUnitId) {
		this.orgUnitId = orgUnitId;
	}

	/**
	 * Instantiates a new org unit.
	 *
	 * @param orgUnitId the org unit id
	 * @param unitName the unit name
	 * @param parentId the parent id
	 * @param typeId the type id
	 * @param levelPath the level path
	 * @param manager the manager
	 * @param activeState the active state
	 */
	public OrgUnit(int orgUnitId, String unitName, Integer parentId,
			Integer typeId, String levelPath, User manager,
			Boolean activeState) {
		this.orgUnitId = orgUnitId;
		this.unitName = unitName;
		this.parentId = parentId;
		this.typeId = typeId;
		this.levelPath = levelPath;
		this.manager = manager;
		this.activeState = activeState;
	}

	/**
	 * Gets the org unit id.
	 *
	 * @return the org unit id
	 */
	@Id
	@Column(name = "org_unit_id", unique = true, nullable = false)
	public int getOrgUnitId() {
		return this.orgUnitId;
	}

	/**
	 * Sets the org unit id.
	 *
	 * @param orgUnitId the new org unit id
	 */
	public void setOrgUnitId(int orgUnitId) {
		this.orgUnitId = orgUnitId;
	}

	/**
	 * Gets the unit name.
	 *
	 * @return the unit name
	 */
	@Column(name = "unit_name", length = 100)
	@Length(max = 100)
	public String getUnitName() {
		return this.unitName;
	}

	/**
	 * Sets the unit name.
	 *
	 * @param unitName the new unit name
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * Gets the parent id.
	 *
	 * @return the parent id
	 */
	@Column(name = "parent_id")
	public Integer getParentId() {
		return this.parentId;
	}

	/**
	 * Sets the parent id.
	 *
	 * @param parentId the new parent id
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * Gets the type id.
	 *
	 * @return the type id
	 */
	@Column(name = "type_id")
	public Integer getTypeId() {
		return this.typeId;
	}

	/**
	 * Sets the type id.
	 *
	 * @param typeId the new type id
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 * Gets the level path.
	 *
	 * @return the level path
	 */
	@Column(name = "level_path", length = 200)
	@Length(max = 200)
	public String getLevelPath() {
		return this.levelPath;
	}

	/**
	 * Sets the level path.
	 *
	 * @param levelPath the new level path
	 */
	public void setLevelPath(String levelPath) {
		this.levelPath = levelPath;
	}

	/**
	 * Gets the manager.
	 *
	 * @return the manager
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id")
	public User getManager() {
		return manager;
	}

	/**
	 * Sets the manager.
	 *
	 * @param manager the new manager
	 */
	public void setManager(User manager) {
		this.manager = manager;
	}

	/**
	 * Gets the active state.
	 *
	 * @return the active state
	 */
	@Column(name = "active_state")
	public Boolean getActiveState() {
		return this.activeState;
	}

	/**
	 * Sets the active state.
	 *
	 * @param activeState the new active state
	 */
	public void setActiveState(Boolean activeState) {
		this.activeState = activeState;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.unitName;
	}
}
