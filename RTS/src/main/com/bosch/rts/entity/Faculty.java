package com.bosch.rts.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

/**
 * The Class Faculty.
 */
@Entity
@Table(name = "trts_faculty", catalog = "rts")
public class Faculty implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4632412029497880150L;
	
	/** The id. */
	private Integer id;
	
	/** The name. */
	private String name;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue
	@Column(name = "fct_faculty_id", unique = true, nullable = false)
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
	@Length(max = 100)
	@Column(name = "fct_faculty_name", length = 100)
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

}
