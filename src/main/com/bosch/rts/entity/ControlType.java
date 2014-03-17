package com.bosch.rts.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.Length;


/**
 * The Class ControlType.
 *
 * @author KHB1HC
 */
@Entity
@Table(name= "trts_control_type")
public class ControlType implements Serializable{
	
	/** serialVersionUID. */
	private static final long serialVersionUID = 4332755475758455452L;
	
	/** The id. */
	private int id;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	/** The technical result line attributes. */
	private Set<TechnicalResultLineAttribute> technicalResultLineAttributes  = new HashSet<TechnicalResultLineAttribute>(0);
	
	/**
	 * Instantiates a new control type.
	 */
	public ControlType(){
		
	}
	
	/**
	 * Instantiates a new control type.
	 *
	 * @param name the name
	 * @param description the description
	 * @param technicalResultLineAttributes the technical result line attributes
	 */
	public ControlType(String name, String description, Set<TechnicalResultLineAttribute> technicalResultLineAttributes){
		this.name = name;
		this.description = description;
		this.technicalResultLineAttributes = technicalResultLineAttributes;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	@Column(name = "name", nullable = true, length = 45)
	@Length(max = 45)
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
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
	 * Gets the description.
	 *
	 * @return the description
	 */
	@Column(name = "description", nullable = true, length = 45)
	@Length(max = 45)
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the technical result line attributes.
	 *
	 * @param technicalResultLineAttributes the new technical result line attributes
	 */
	public void setTechnicalResultLineAttributes(
			Set<TechnicalResultLineAttribute> technicalResultLineAttributes) {
		this.technicalResultLineAttributes = technicalResultLineAttributes;
	}

	/**
	 * Gets the technical result line attributes.
	 *
	 * @return the technical result line attributes
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "controlType")
	public Set<TechnicalResultLineAttribute> getTechnicalResultLineAttributes() {
		return technicalResultLineAttributes;
	}
}