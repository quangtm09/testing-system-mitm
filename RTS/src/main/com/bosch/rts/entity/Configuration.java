package com.bosch.rts.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

/**
 * The Class Configuration.
 */
@Entity
@Table(name = "trts_configuration", catalog = "rts")
public class Configuration implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1736056572937371720L;
	
	/** The id. */
	private Integer id;
	
	/** The name. */
	private String name;
	
	/** The value. */
	private String value;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cfg_id", unique = true, nullable = false)
	public Integer getId() {
		return id;
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
	@Length(max = 30)
	@Column(name = "cfg_name", unique = true, nullable = false)
	public String getName() {
		return name;
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
	 * Gets the value.
	 *
	 * @return the value
	 */
	@Length(max = 30)
	@Column(name = "cfg_value", nullable = false)
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
