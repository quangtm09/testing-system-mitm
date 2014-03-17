/**
 * 
 */
package com.bosch.rts.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 * @author khb1hc
 *
 */
@Entity
@Table(name = "trts_level_type")
public class LevelType implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7828369777578293637L;

	/**
	 * 
	 */
	public LevelType() {
	}
	
	public LevelType(Integer id, String name, String displayName, Character active, Integer order) {
		this.id = id;
		this.name = name;
		this.displayName = displayName;
		this.active = active;
		this.order = order;
	}
	
	public LevelType(Integer id, String name, String displayName, String description, Character active, Integer order) {
		this.id = id;
		this.name = name;
		this.displayName = displayName;
		this.description = description;
		this.active = active;
		this.order = order;
	}
	
	@Id
	@Column(name = "trts_level_type_id", unique = true, nullable = false)
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	
	@Column(name = "name", length = 50)
	@Length(max = 50)
	@NotNull
	private String name;
	
	@Column(name = "display_name", length = 50)
	@Length(max = 50)
	@NotNull
	private String displayName;
	
	@Column(name = "description", length = 50)
	@Length(max = 50)
	private String description;
	
	@Column(name = "active")
	@NotNull
	private Character active;
	
	@Column(name = "order")
	@NotNull
	private Integer order;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "itsApplyForLevel", cascade = CascadeType.ALL, targetEntity = InterviewSchedule.class)
	private Set<InterviewSchedule> interviewSchedules = new HashSet<InterviewSchedule>(0);
	
	/**
	 * @return the order
	 */
	public Integer getOrder() {
		return this.order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return this.displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the active
	 */
	public Character getActive() {
		return this.active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(Character active) {
		this.active = active;
	}

	public void setInterviewSchedules(Set<InterviewSchedule> interviewSchedules) {
		this.interviewSchedules = interviewSchedules;
	}
	
	public Set<InterviewSchedule> getInterviewSchedules() {
		return interviewSchedules;
	}
	
}
