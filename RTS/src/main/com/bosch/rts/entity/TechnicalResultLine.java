package com.bosch.rts.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.beanutils.BeanComparator;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * TechincalResultLine generated by hbm2java.
 */
@Entity
@Table(name = "trts_technical_result_line")
public class TechnicalResultLine implements java.io.Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 3269053181105453377L;
	
	/** The id. */
	private Integer id;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	/** The colspan. */
	private byte colspan;
	
	/** The position. */
	private byte position;
	
	/** The created on. */
	private Date createdOn;
	
	/** The created by. */
	private String createdBy;
	
	/** The updated on. */
	private Date updatedOn;
	
	/** The updated by. */
	private String updatedBy;
	
	/** The technical result group. */
	private TechnicalResultGroup technicalResultGroup;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TechnicalResultLine))
			return false;
		TechnicalResultLine other = (TechnicalResultLine) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}

	/** The technical result line attributes. */
	private Set<TechnicalResultLineAttribute> technicalResultLineAttributes = new HashSet<TechnicalResultLineAttribute>(0);

	/**
	 * Instantiates a new technical result line.
	 */
	public TechnicalResultLine() {
		colspan = 1;
		position = 1;
	}

	/**
	 * Instantiates a new technical result line.
	 *
	 * @param name the name
	 * @param colspan the colspan
	 * @param position the position
	 * @param createdOn the created on
	 * @param createdBy the created by
	 * @param technicalResultGroup the technical result group
	 */
	public TechnicalResultLine(String name, byte colspan, byte position, Date createdOn,
			String createdBy, TechnicalResultGroup technicalResultGroup) {
		this.name = name;
		this.colspan = colspan;
		this.position = position;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.technicalResultGroup = technicalResultGroup;
	}

	/**
	 * Instantiates a new technical result line.
	 *
	 * @param name the name
	 * @param description the description
	 * @param colspan the colspan
	 * @param position the position
	 * @param createdOn the created on
	 * @param createdBy the created by
	 * @param updatedOn the updated on
	 * @param updatedBy the updated by
	 * @param technicalResultGroup the technical result group
	 * @param technicalResultLineAttributes the technical result line attributes
	 */
	public TechnicalResultLine(
			String name,
			String description,
			byte colspan,
			byte position,
			Date createdOn,
			String createdBy,
			Date updatedOn,
			String updatedBy,
			TechnicalResultGroup technicalResultGroup,
			Set<TechnicalResultLineAttribute> technicalResultLineAttributes) {
		this.name = name;
		this.description = description;
		this.colspan = colspan;
		this.position = position;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.technicalResultGroup = technicalResultGroup;
		this.technicalResultLineAttributes = technicalResultLineAttributes;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@Column(name = "name", nullable = false, length = 45)
	@NotNull
	@Length(max = 45)
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	@Column(name = "description", length = 200)
	@Length(max = 200)
	public String getDescription() {
		return this.description;
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
	 * Gets the colspan.
	 *
	 * @return the colspan
	 */
	@Column(name = "colspan", nullable = false)
	public byte getColspan() {
		return this.colspan;
	}

	/**
	 * Sets the colspan.
	 *
	 * @param colspan the new colspan
	 */
	public void setColspan(byte colspan) {
		this.colspan = colspan;
	}

	/**
	 * Gets the created on.
	 *
	 * @return the created on
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "created_on", nullable = false, length = 0)
	@NotNull
	public Date getCreatedOn() {
		return this.createdOn;
	}

	/**
	 * Sets the created on.
	 *
	 * @param createdOn the new created on
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	@Column(name = "created_by", nullable = false, length = 12)
	@NotNull
	@Length(max = 12)
	public String getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy the new created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the updated on.
	 *
	 * @return the updated on
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_on", length = 0)
	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	/**
	 * Sets the updated on.
	 *
	 * @param updatedOn the new updated on
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * Gets the updated by.
	 *
	 * @return the updated by
	 */
	@Column(name = "updated_by", length = 12)
	@Length(max = 12)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	/**
	 * Sets the updated by.
	 *
	 * @param updatedBy the new updated by
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	/**
	 * Gets the technical result group.
	 *
	 * @return the technical result group
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "technical_result_group_id")
	public TechnicalResultGroup getTechnicalResultGroup() {
		return technicalResultGroup;
	}

	/**
	 * Sets the technical result group.
	 *
	 * @param technicalResultGroup the new technical result group
	 */
	public void setTechnicalResultGroup(TechnicalResultGroup technicalResultGroup) {
		this.technicalResultGroup = technicalResultGroup;
	}

	/**
	 * Gets the technical result line attributes.
	 *
	 * @return the technical result line attributes
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "technicalResultLine")
	public Set<TechnicalResultLineAttribute> getTechnicalResultLineAttributes() {
		return this.technicalResultLineAttributes;
	}

	/**
	 * Sets the technical result line attributes.
	 *
	 * @param technicalResultLineAttributes the new technical result line attributes
	 */
	public void setTechnicalResultLineAttributes(Set<TechnicalResultLineAttribute> technicalResultLineAttributes) {
		this.technicalResultLineAttributes = technicalResultLineAttributes;
	}

	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(byte position) {
		this.position = position;
	}

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	@Column(name = "position", nullable = false)
	public byte getPosition() {
		return position;
	}
	
	/**
	 * Gets the technical result line attribute list.
	 *
	 * @return the technical result line attribute list
	 */
	@Transient
	public List<TechnicalResultLineAttribute> getTechnicalResultLineAttributeList(){
		List<TechnicalResultLineAttribute> technicalResultLineAttributeList = null;
		if(this.technicalResultLineAttributes != null){
			technicalResultLineAttributeList = new ArrayList<TechnicalResultLineAttribute>(this.technicalResultLineAttributes);
			final BeanComparator beanComparator = new BeanComparator("position");
			Collections.sort(technicalResultLineAttributeList, beanComparator);
		}
		
		return technicalResultLineAttributeList;
	}
}
