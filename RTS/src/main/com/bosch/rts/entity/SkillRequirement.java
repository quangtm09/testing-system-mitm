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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.Max;
import org.hibernate.validator.Min;

/**
 * The Class SkillRequirement.
 */
@Entity
@Table(name = "trts_skill_requirement")
public class SkillRequirement implements Serializable{

	/** serialVersionUID. */
	private static final long serialVersionUID = 6825931043643764945L;
	
	/** The number of persons. */
	private Integer numberOfPersons;
	
	/** The experience year from. */
	private Integer experienceYearFrom;
	
	/** The experience year to. */
	private Integer experienceYearTo;
	
	/** The qualifications. */
	private String qualifications;
	
	/** The skill requirement id. */
	private Integer skillRequirementId;
	
	/** The recruit request. */
	private RecruitRequest recruitRequest;
	
	/** The skill requirement has skills. */
	private Set<SkillRequirementHasSkill> skillRequirementHasSkills = new HashSet<SkillRequirementHasSkill>(0);

	/**
	 * Gets the experience year from.
	 *
	 * @return the experience year from
	 */
	@Column(name = "skr_experience_year_from")
	@Min(value=0)
	@Max(value=29)
	public Integer getExperienceYearFrom() {
		return experienceYearFrom;
	}

	/**
	 * Sets the experience year from.
	 *
	 * @param experienceYearFrom the new experience year from
	 */
	public void setExperienceYearFrom(Integer experienceYearFrom) {
		this.experienceYearFrom = experienceYearFrom;
	}

	/**
	 * Gets the experience year to.
	 *
	 * @return the experience year to
	 */
	@Column(name = "skr_experience_year_to")
	@Min(value=0)
	@Max(value=30)
	public Integer getExperienceYearTo() {
		return experienceYearTo;
	}

	/**
	 * Sets the experience year to.
	 *
	 * @param experienceYearTo the new experience year to
	 */
	public void setExperienceYearTo(Integer experienceYearTo) {
		this.experienceYearTo = experienceYearTo;
	}

	/**
	 * Gets the skill requirement id.
	 *
	 * @return the skill requirement id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "skr_skill_requirement_id", unique = true, nullable = false)
	public Integer getSkillRequirementId() {
		return skillRequirementId;
	}

	/**
	 * Sets the skill requirement id.
	 *
	 * @param skillRequirementId the new skill requirement id
	 */
	public void setSkillRequirementId(Integer skillRequirementId) {
		this.skillRequirementId = skillRequirementId;
	}

	/**
	 * Gets the number of persons.
	 *
	 * @return the number of persons
	 */
	@Column(name = "skr_number_of_persons")
	@Min(value=1)
	@Max(value=100)
	public Integer getNumberOfPersons() {
		return this.numberOfPersons;
	}

	/**
	 * Sets the number of persons.
	 *
	 * @param numberOfPersons the new number of persons
	 */
	public void setNumberOfPersons(Integer numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}

	/**
	 * Gets the qualifications.
	 *
	 * @return the qualifications
	 */
	@Column(name = "skr_qualifications", length = 250, nullable = true)
	public String getQualifications() {
		return qualifications;
	}

	/**
	 * Sets the qualifications.
	 *
	 * @param qualifications the new qualifications
	 */
	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	/**
	 * Gets the skill requirement has skills.
	 *
	 * @return the skill requirement has skills
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "skillRequirement", cascade = {
			CascadeType.PERSIST, CascadeType.REMOVE})
	public Set<SkillRequirementHasSkill> getSkillRequirementHasSkills() {
		return this.skillRequirementHasSkills;
	}

	/**
	 * Sets the skill requirement has skills.
	 *
	 * @param skillRequirementHasSkills the new skill requirement has skills
	 */
	public void setSkillRequirementHasSkills(
			Set<SkillRequirementHasSkill> skillRequirementHasSkills) {
		this.skillRequirementHasSkills = skillRequirementHasSkills;
	}

	/**
	 * Gets the recruit request.
	 *
	 * @return the recruit request
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "skr_recruit_request_id", nullable = false)
	public RecruitRequest getRecruitRequest() {
		return recruitRequest;
	}

	/**
	 * Sets the recruit request.
	 *
	 * @param recruitRequest the new recruit request
	 */
	public void setRecruitRequest(RecruitRequest recruitRequest) {
		this.recruitRequest = recruitRequest;
	}
	
}