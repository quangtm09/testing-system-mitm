package com.bosch.rts.entity;

// Generated Jun 5, 2013 10:58:58 AM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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

/**
 * The Class TechnicalResultGroup.
 */
@Entity
@Table(name = "trts_technical_result_group")
public class TechnicalResultGroup implements java.io.Serializable {

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
		if (!(obj instanceof TechnicalResultGroup))
			return false;
		TechnicalResultGroup other = (TechnicalResultGroup) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}

	/** serialVersionUID. */
	private static final long serialVersionUID = -700655560246146192L;	
	
	/** The id. */
	private Integer id;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	/** The value. */
	private String value;
	
	/** The colspan. */
	private byte colspan;
	
	/** The background. */
	private String background;
	
	/** The color. */
	private String color;
	
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

	/** The technical result lines. */
	private Set<TechnicalResultLine> technicalResultLines  = new HashSet<TechnicalResultLine>();
	
	/** The interview assessment templates. */
	private InterviewAssessmentTemplates interviewAssessmentTemplates;

	/**
	 * Instantiates a new technical result group.
	 */
	public TechnicalResultGroup() {
		background = "#8CD1D1";
		color = "#474747";
		colspan = 1;
		position = 1;
	}

	/**
	 * Instantiates a new technical result group.
	 *
	 * @param name the name
	 * @param colspan the colspan
	 * @param background the background
	 * @param color the color
	 * @param position the position
	 * @param createdOn the created on
	 * @param createdBy the created by
	 */
	public TechnicalResultGroup(String name,
			byte colspan, String background, String color, byte position,
			Date createdOn, String createdBy) {
		this.name = name;
		this.colspan = colspan;
		this.background = background;
		this.color = color;
		this.position = position;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
	}

	/**
	 * Instantiates a new technical result group.
	 *
	 * @param interviewAssessmentTemplates the interview assessment templates
	 * @param name the name
	 * @param description the description
	 * @param value the value
	 * @param colspan the colspan
	 * @param background the background
	 * @param color the color
	 * @param position the position
	 * @param createdOn the created on
	 * @param createdBy the created by
	 * @param updatedOn the updated on
	 * @param updatedBy the updated by
	 */
	public TechnicalResultGroup(InterviewAssessmentTemplates interviewAssessmentTemplates, String name,
			String description, String value, byte colspan, String background,
			String color, byte position, Date createdOn, String createdBy,
			Date updatedOn, String updatedBy) {
		this.name = name;
		this.description = description;
		this.value = value;
		this.colspan = colspan;
		this.background = background;
		this.color = color;
		this.position = position;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.interviewAssessmentTemplates = interviewAssessmentTemplates;
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
	@Column(name = "name", nullable = false, length = 45, unique = true)
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
	 * Gets the value.
	 *
	 * @return the value
	 */
	@Column(name = "value", length = 200)
	@Length(max = 200)
	public String getValue() {
		return this.value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
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
	 * Gets the background.
	 *
	 * @return the background
	 */
	@Column(name = "background", nullable = false, length = 7)
	@NotNull
	@Length(max = 7)
	public String getBackground() {
		return this.background;
	}

	/**
	 * Sets the background.
	 *
	 * @param background the new background
	 */
	public void setBackground(String background) {
		this.background = background;
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	@Column(name = "color", nullable = false, length = 7)
	@NotNull
	@Length(max = 7)
	public String getColor() {
		return this.color;
	}

	/**
	 * Sets the color.
	 *
	 * @param color the new color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	@Column(name = "position", nullable = false)
	public byte getPosition() {
		return this.position;
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
	 * Gets the technical result lines.
	 *
	 * @return the technical result lines
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "technicalResultGroup", cascade = CascadeType.ALL)
	public Set<TechnicalResultLine> getTechnicalResultLines() {
		return technicalResultLines;
	}

	/**
	 * Sets the technical result lines.
	 *
	 * @param technicalResultLines the new technical result lines
	 */
	public void setTechnicalResultLines(
			Set<TechnicalResultLine> technicalResultLines) {
		this.technicalResultLines = technicalResultLines;
	}	
	
	/**
	 * Sets the interview assessment templates.
	 *
	 * @param interviewAssessmentTemplates the interviewAssessmentTemplates to set
	 */
	public void setInterviewAssessmentTemplates(InterviewAssessmentTemplates interviewAssessmentTemplates) {
		this.interviewAssessmentTemplates = interviewAssessmentTemplates;
	}

	/**
	 * Gets the interview assessment templates.
	 *
	 * @return the interviewAssessmentTemplates
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "interview_assessment_templates_id")	
	public InterviewAssessmentTemplates getInterviewAssessmentTemplates() {
		return interviewAssessmentTemplates;
	}

	/**
	 * Gets the lines list.
	 *
	 * @return the lines list
	 */
	@SuppressWarnings("unchecked")
	@Transient
	public List<TechnicalResultLine> getLinesList(){		
		List<TechnicalResultLine> lines = null;
		if(this.technicalResultLines != null && !this.technicalResultLines.isEmpty()){
			lines = new ArrayList<TechnicalResultLine>(this.technicalResultLines);
			final BeanComparator beanComparator = new BeanComparator("position");
			Collections.sort(lines, beanComparator);
			
		}
		return lines;
	}
	
	
}