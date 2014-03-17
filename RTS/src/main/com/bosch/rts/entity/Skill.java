/*
 * 
 */
package com.bosch.rts.entity;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Pattern;

/**
 * Candidate Skill
 */
@Entity
@Table(name = "trts_skill", uniqueConstraints =  @UniqueConstraint(columnNames={"skl_name"}))
public class Skill implements java.io.Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = -6980247227381533725L;
	
	/** The skl skill id. */
	private Integer sklSkillId;
	
	/** The skl name. */
	private String sklName;
	
	/** The skl description. */
	private String sklDescription;
	
	private Boolean activeState;
	
	/** The created on. */
	private Date createdOn;
	
	/** The created by. */
	private String createdBy;
	
	/** The updated on. */
	private Date updatedOn;
	
	/** The updated by. */
	private String updatedBy;
	
	/** The recruit request has skills. */
	private Set<RecruitRequestHasSkill> recruitRequestHasSkills = new HashSet<RecruitRequestHasSkill>(0);
	
	/** The candidate has skills. */
	private Set<CandidateHasSkill> candidateHasSkills = new HashSet<CandidateHasSkill>(0);

	/**
	 * Instantiates a new skill.
	 */
	public Skill() {
	}
	

	/**
	 * @return the activeState
	 */
	@Column(name = "active_state")
	@NotNull
	public Boolean getActiveState() {
		return this.activeState;
	}


	/**
	 * @param activeState the activeState to set
	 */
	public void setActiveState(Boolean activeState) {
		this.activeState = activeState;
	}


	/**
	 * Gets the updated on.
	 *
	 * @return the updated on
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "created_on", length = 0)
	@NotNull
	public Date getCreatedOn() {
		return this.createdOn;
	}


	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	
	/** Gets the created by.
	 *
	 * @return the created by
	 */
	@Column(name = "created_by", length = 12)
	@Length(max = 12)
	@NotNull
	public String getCreatedBy() {
		return this.createdBy;
	}


	/**
	 * @param createdBy the createdBy to set
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
	 * @param updatedOn the updatedOn to set
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}



	/** Gets the updated by.
	 *
	 * @return the updated by
	 */
	@Column(name = "updated_by", length = 12)
	@Length(max = 12)
	public String getUpdatedBy() {
		return this.updatedBy;
	}


	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}


	/**
	 * Instantiates a new skill.
	 *
	 * @param sklName the skl name
	 * @param sklDescription the skl description
	 * @param sklExperienceYearsFrom the skl experience years from
	 * @param sklExperienceYearsTo the skl experience years to
	 * @param recruitRequestHasSkills the recruit request has skills
	 * @param candidateHasSkills the candidate has skills
	 */
	public Skill(String sklName, String sklDescription,
			int sklExperienceYearsFrom, int sklExperienceYearsTo,
			Set<RecruitRequestHasSkill> recruitRequestHasSkills,
			Set<CandidateHasSkill> candidateHasSkills) {
		this.sklName = sklName;
		this.sklDescription = sklDescription;
		this.recruitRequestHasSkills = recruitRequestHasSkills;
		this.candidateHasSkills = candidateHasSkills;
	}

	/**
	 * Gets the skl skill id.
	 *
	 * @return the skl skill id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "skl_skill_id", unique = true, nullable = false)
	public Integer getSklSkillId() {
		return this.sklSkillId;
	}

	/**
	 * Sets the skl skill id.
	 *
	 * @param sklSkillId the new skl skill id
	 */
	public void setSklSkillId(Integer sklSkillId) {
		this.sklSkillId = sklSkillId;
	}

	/**
	 * Gets the skl name.
	 *
	 * @return the skl name
	 */
	@Column(name = "skl_name", length = 25, unique = true)
	@NotNull
	@Pattern(regex="[a-zA-Z0-9._ ]*",message="{com.bosch.ui.skillname.invalid}")
	
	public String getSklName() {
		return this.sklName;
	}

	/**
	 * Sets the skl name.
	 *
	 * @param sklName the new skl name
	 */
	public void setSklName(String sklName) {
		this.sklName = sklName;
	}

	/**
	 * Gets the skl description.
	 *
	 * @return the skl description
	 */
	@Column(name = "skl_description", length = 100)
	@Pattern(regex="[a-zA-Z0-9._ ]*",message="{com.bosch.ui.skill.desc.invalid}")
	public String getSklDescription() {
		return this.sklDescription;
	}

	/**
	 * Sets the skl description.
	 *
	 * @param sklDescription the new skl description
	 */
	public void setSklDescription(String sklDescription) {
		this.sklDescription = sklDescription;
	}

	/**
	 * Gets the recruit request has skills.
	 *
	 * @return the recruit request has skills
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "skill")
	public Set<RecruitRequestHasSkill> getRecruitRequestHasSkills() {
		return this.recruitRequestHasSkills;
	}

	/**
	 * Sets the recruit request has skills.
	 *
	 * @param recruitRequestHasSkills the new recruit request has skills
	 */
	public void setRecruitRequestHasSkills(
			Set<RecruitRequestHasSkill> recruitRequestHasSkills) {
		this.recruitRequestHasSkills = recruitRequestHasSkills;
	}

	/**
	 * Gets the candidate has skills.
	 *
	 * @return the candidate has skills
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "skill")
	public Set<CandidateHasSkill> getCandidateHasSkills() {
		return this.candidateHasSkills;
	}

	/**
	 * Sets the candidate has skills.
	 *
	 * @param candidateHasSkills the new candidate has skills
	 */
	public void setCandidateHasSkills(Set<CandidateHasSkill> candidateHasSkills) {
		this.candidateHasSkills = candidateHasSkills;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return sklName.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object arg) {
		if (arg instanceof Skill) {
			Skill skill = (Skill) arg;
			if (this.sklSkillId != null && skill.sklSkillId != null) {
				return this.sklSkillId.intValue() == skill.sklSkillId.intValue();
			} else if (this.getSklName() != null & skill.getSklName() != null) {
				return this.getSklName().equalsIgnoreCase(skill.getSklName());
			}
		}
		return false;
	}
}
