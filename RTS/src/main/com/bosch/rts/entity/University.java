package com.bosch.rts.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.Length;

/**
 * The Class University.
 */
@Entity
@Table(name = "trts_university")
public class University implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1076340703225715842L;
	
	/** The id. */
	private Integer id;
	
	/** The name. */
	private String name;
	
	/** The faculties. */
	private Set<Faculty> faculties = new HashSet<Faculty>();

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue
	@Column(name = "uns_university_id", unique = true, nullable = false)
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
	@Length(max = 200)
	@Column(name = "uns_name", length = 100)
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
	 * Gets the faculties.
	 *
	 * @return the faculties
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "trts_university_has_faculty", joinColumns = { @JoinColumn(name = "uhf_university_id") }, inverseJoinColumns = { @JoinColumn(name = "uhf_faculty_id") })
	public Set<Faculty> getFaculties() {
		return faculties;
	}

	/**
	 * Sets the faculties.
	 *
	 * @param faculties the new faculties
	 */
	public void setFaculties(Set<Faculty> faculties) {
		this.faculties = faculties;
	}

}
