package com.bosch.rts.entity;

// Generated Jun 5, 2013 10:58:58 AM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 * The Class TechnicalResultLineAttribute.
 */
@Entity
@Table(name = "trts_technical_result_line_attribute")
public class TechnicalResultLineAttribute implements java.io.Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = -7669148641765703079L;
	
	/** The id. */
	private Integer id;	
	
	/** The name. */
	private String name;
	
	/** The label. */
	private String label;
	
	/** The description. */
	private String description;
	
	/** The control type. */
	private ControlType controlType;
	
	/** The bold. */
	private byte bold;
	
	/** The required. */
	private char required;
	
	/** The created on. */
	private Date createdOn;
	
	/** The created by. */
	private String createdBy;
	
	/** The updated on. */
	private Date updatedOn;
	
	/** The updated by. */
	private String updatedBy;
	
	/** The layout. */
	private String layout;
	
	/** The col span. */
	private byte colSpan;
	
	/** The position. */
	private byte position;
	
	/** The align. */
	private String align;
	
	/** The value. */
	private String value;
	
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
		if (!(obj instanceof TechnicalResultLineAttribute))
			return false;
		TechnicalResultLineAttribute other = (TechnicalResultLineAttribute) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}

	/** The technical result line. */
	private TechnicalResultLine technicalResultLine;	

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Instantiates a new technical result line attribute.
	 */
	public TechnicalResultLineAttribute() {
		colSpan = 1;
		position = 1;
	}

	/**
	 * Instantiates a new technical result line attribute.
	 *
	 * @param technicalResultLine the technical result line
	 * @param name the name
	 * @param controlType the control type
	 * @param bold the bold
	 * @param required the required
	 * @param createdOn the created on
	 * @param createdBy the created by
	 */
	public TechnicalResultLineAttribute(
			TechnicalResultLine technicalResultLine, String name,
			ControlType controlType, byte bold,
			char required, Date createdOn, String createdBy) {
		this.name = name;
		this.controlType = controlType;
		this.bold = bold;
		this.required = required;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
	}

	/**
	 * Instantiates a new technical result line attribute.
	 *
	 * @param technicalResultLine the technical result line
	 * @param controlType the control type
	 * @param name the name
	 * @param description the description
	 * @param align the align
	 * @param bold the bold
	 * @param required the required
	 * @param createdOn the created on
	 * @param createBy the create by
	 * @param updatedOn the updated on
	 * @param updatedBy the updated by
	 * @param layout the layout
	 * @param colSpan the col span
	 * @param value the value
	 */
	public TechnicalResultLineAttribute(TechnicalResultLine technicalResultLine, ControlType controlType, String name,
			String description, String align,
			byte bold, char required, Date createdOn, String createBy,
			Date updatedOn, String updatedBy, String layout, byte colSpan, String value) {
		this.technicalResultLine = technicalResultLine;
		this.name = name;
		this.description = description;
		this.controlType = controlType;
		this.bold = bold;
		this.required = required;
		this.createdOn = createdOn;
		this.createdBy = createBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.layout = layout;
		this.colSpan = colSpan;
		this.align = align;
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
	 * Gets the value.
	 *
	 * @return the value
	 */
	@Transient
	public String getValue() {
		return this.value;
	}

	/**
	 * Gets the technical result line.
	 *
	 * @return the technical result line
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "technical_result_line_id", nullable = true)
	public TechnicalResultLine getTechnicalResultLine() {
		return this.technicalResultLine;
	}

	/**
	 * Sets the technical result line.
	 *
	 * @param technicalResultLine the new technical result line
	 */
	public void setTechnicalResultLine(TechnicalResultLine technicalResultLine) {
		this.technicalResultLine = technicalResultLine;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@Column(name = "name", length = 100, nullable = false, unique = false) 
	@NotNull
	@Length(max = 100)
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
	 * Gets the control type.
	 *
	 * @return the control type
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type")
	public ControlType getControlType() {
		return this.controlType;
	}

	/**
	 * Sets the control type.
	 *
	 * @param controlType the new control type
	 */
	public void setControlType(ControlType controlType) {
		this.controlType = controlType;
	}

	/**
	 * Gets the bold.
	 *
	 * @return the bold
	 */
	@Column(name = "bold", nullable = false)
	public byte getBold() {
		return this.bold;
	}

	/**
	 * Sets the bold.
	 *
	 * @param bold the new bold
	 */
	public void setBold(byte bold) {
		this.bold = bold;
	}

	/**
	 * Gets the required.
	 *
	 * @return the required
	 */
	@Column(name = "required", nullable = false)
	public char getRequired() {
		return this.required;
	}

	/**
	 * Sets the required.
	 *
	 * @param required the new required
	 */
	public void setRequired(char required) {
		this.required = required;
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
	 * Gets the layout.
	 *
	 * @return the layout
	 */
	@Column(name = "layout", length = 15)
	@Length(max = 15)
	public String getLayout() {
		return this.layout;
	}

	/**
	 * Sets the layout.
	 *
	 * @param layout the new layout
	 */
	public void setLayout(String layout) {
		this.layout = layout;
	}
	
	/**
	 * Gets the col span.
	 *
	 * @return the col span
	 */
	public byte getColSpan() {
		return this.colSpan;
	}
	
	/**
	 * Sets the col span.
	 *
	 * @param colSpan the new col span
	 */
	@Column(name = "colspan")
	public void setColSpan(byte colSpan) {
		this.colSpan = colSpan;
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
	@Column(name = "position")
	public byte getPosition() {
		return position;
	}

	/**
	 * Sets the align.
	 *
	 * @param align the new align
	 */
	public void setAlign(String align) {
		this.align = align;
	}

	/**
	 * Gets the align.
	 *
	 * @return the align
	 */
	@Column(name = "align", nullable = true, length = 20)
	public String getAlign() {
		return align;
	}

	/**
	 * Sets the label.
	 *
	 * @param label the new label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	@Column(name = "label", length = 100)
	@Length(max = 100)
	public String getLabel() {
		return label;
	}

	
	
}
