package com.bosch.rts.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class SkillRequirementHasSkill.
 *
 * @author khb1hc
 */
@Entity
@Table(name = "trts_skill_requirement_has_skill")
public class SkillRequirementHasSkill implements java.io.Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1563767553442428204L;

	/** The shs skill requirement has skill id. */
	private Integer shsSkillRequirementHasSkillId;
	
	/** The skill. */
	private Skill skill;
	
	/** The skill requirement. */
	private SkillRequirement skillRequirement;
	
	/** The shs required experience years from. */
	private Integer shsRequiredExperienceYearsFrom;
	
	/** The shs required experience years to. */
	private Integer shsRequiredExperienceYearsTo;
	
	/** The shs skill level. */
	private String shsSkillLevel;
	

	/**
	 * Instantiates a new skill requirement has skill.
	 */
	public SkillRequirementHasSkill() {

	}

	/**
	 * Instantiates a new skill requirement has skill.
	 *
	 * @param skill the skill
	 * @param recruitRequest the recruit request
	 * @param shsRequiredExperienceYearsFrom the shs required experience years from
	 * @param shsRequiredExperienceYearsTo the shs required experience years to
	 * @param shsSkillLevel the shs skill level
	 */
	public SkillRequirementHasSkill(Skill skill,
			SkillRequirement recruitRequest,
			Integer shsRequiredExperienceYearsFrom,
			Integer shsRequiredExperienceYearsTo,
			String shsSkillLevel) {
		this.skill = skill;
		this.skillRequirement = recruitRequest;
		this.shsRequiredExperienceYearsFrom = shsRequiredExperienceYearsFrom;
		this.shsRequiredExperienceYearsTo = shsRequiredExperienceYearsTo;
		this.shsSkillLevel = shsSkillLevel;		
	}	

	/**
	 * Gets the shs skill requirement has skill id.
	 *
	 * @return the shs skill requirement has skill id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "shs_skill_requirement_has_skill_id", unique = true, nullable = false)
	public Integer getShsSkillRequirementHasSkillId() {
		return this.shsSkillRequirementHasSkillId;
	}

	/**
	 * Sets the shs skill requirement has skill id.
	 *
	 * @param shsSkillRequirementHasSkillId the new shs skill requirement has skill id
	 */
	public void setShsSkillRequirementHasSkillId(
			Integer shsSkillRequirementHasSkillId) {
		this.shsSkillRequirementHasSkillId = shsSkillRequirementHasSkillId;
	}
	
	
	/**
	 * Gets the shs skill level.
	 *
	 * @return the shs skill level
	 */
	@Column(name = "shs_skill_level")
	public String getShsSkillLevel() {
		return shsSkillLevel;
	}

	/**
	 * Sets the shs skill level.
	 *
	 * @param shsSkillLevel the new shs skill level
	 */
	public void setShsSkillLevel(String shsSkillLevel) {
		this.shsSkillLevel = shsSkillLevel;
	}

	/**
	 * Gets the skill.
	 *
	 * @return the skill
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shs_skill_id")
	public Skill getSkill() {
		return this.skill;
	}

	/**
	 * Sets the skill.
	 *
	 * @param skill the new skill
	 */
	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	/**
	 * Gets the skill requirement.
	 *
	 * @return the skill requirement
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shs_skill_requirment_id", nullable = false)
	public SkillRequirement getSkillRequirement() {
		return this.skillRequirement;
	}

	/**
	 * Sets the skill requirement.
	 *
	 * @param skillRequirement the new skill requirement
	 */
	public void setSkillRequirement(SkillRequirement skillRequirement) {
		this.skillRequirement = skillRequirement;
	}

	/**
	 * Gets the shs required experience years from.
	 *
	 * @return the shs required experience years from
	 */
	@Column(name = "shs_required_experience_years_from")
	public Integer getShsRequiredExperienceYearsFrom() {
		return this.shsRequiredExperienceYearsFrom;
	}

	/**
	 * Sets the shs required experience years from.
	 *
	 * @param shsRequiredExperienceYearsFrom the new shs required experience years from
	 */
	public void setShsRequiredExperienceYearsFrom(
			Integer shsRequiredExperienceYearsFrom) {
		this.shsRequiredExperienceYearsFrom = shsRequiredExperienceYearsFrom;
	}

	/**
	 * Gets the shs required experience years to.
	 *
	 * @return the shs required experience years to
	 */
	@Column(name = "shs_required_experience_years_to")
	public Integer getshsRequiredExperienceYearsTo() {
		return this.shsRequiredExperienceYearsTo;
	}

	/**
	 * Sets the shs required experience years to.
	 *
	 * @param shsRequiredExperienceYearsTo the new shs required experience years to
	 */
	public void setShsRequiredExperienceYearsTo(
			Integer shsRequiredExperienceYearsTo) {
		 this.shsRequiredExperienceYearsTo = shsRequiredExperienceYearsTo;
	}

	/*@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof SkillRequirementHasSkill) {
			SkillRequirementHasSkill compareSkill = (SkillRequirementHasSkill) arg0;
			if (skill != null && skillRequirement != null) {
				if (skill.equals(compareSkill.getSkill())
						&& skillRequirement.equals(compareSkill.getSkillRequirement())) {
					return true;
				}
			}

			if (this.shsSkillRequirementHasSkillId != null) {
				return this.shsSkillRequirementHasSkillId.equals(compareSkill
						.getShsSkillRequirementHasSkillId());
			}
		}
		return false;
	}*/

	
}
