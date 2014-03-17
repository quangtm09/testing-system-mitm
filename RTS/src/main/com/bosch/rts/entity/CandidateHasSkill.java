package com.bosch.rts.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class CandidateHasSkill.
 */
@Entity
@Table(name = "trts_candidate_has_skill")
public class CandidateHasSkill implements java.io.Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = -8499537252768759740L;
	
	/** The chs candidate skill id. */
	private Integer chsCandidateSkillId;
	
	/** The skill. */
	private Skill skill;
	
	/** The candidate. */
	private Candidate candidate;
	
	/** The chs_ exerinece_ years. */
	private Integer chs_Exerinece_Years;
	
	/** The skill level. */
	private String skillLevel;

	/**
	 * Instantiates a new candidate has skill.
	 */
	public CandidateHasSkill() {
	}

	/**
	 * Instantiates a new candidate has skill.
	 *
	 * @param skill the skill
	 * @param candidate the candidate
	 */
	public CandidateHasSkill(Skill skill, Candidate candidate) {
		this.skill = skill;
		this.candidate = candidate;
	}

	/**
	 * Gets the chs candidate skill id.
	 *
	 * @return the chs candidate skill id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "chs_candidate_skill_id", unique = true, nullable = false)
	public Integer getChsCandidateSkillId() {
		return this.chsCandidateSkillId;
	}

	/**
	 * Sets the chs candidate skill id.
	 *
	 * @param chsCandidateSkillId the new chs candidate skill id
	 */
	public void setChsCandidateSkillId(Integer chsCandidateSkillId) {
		this.chsCandidateSkillId = chsCandidateSkillId;
	}

	/**
	 * Gets the skill.
	 *
	 * @return the skill
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chs_skill_id")
	public Skill getSkill() {
		return this.skill;
	}

	/**
	 * Gets the chs_ exerinece_ years.
	 *
	 * @return the chs_ exerinece_ years
	 */
	@Column(name = "chs_experinece_years")
	public Integer getChs_Exerinece_Years() {
		return chs_Exerinece_Years;
	}

	/**
	 * Sets the chs_ exerinece_ years.
	 *
	 * @param chs_Exerinece_Years the new chs_ exerinece_ years
	 */
	public void setChs_Exerinece_Years(Integer chs_Exerinece_Years) {
		this.chs_Exerinece_Years = chs_Exerinece_Years;
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
	 * Gets the candidate.
	 *
	 * @return the candidate
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "chs_candidate_id")
	public Candidate getCandidate() {
		return this.candidate;
	}

	/**
	 * Sets the candidate.
	 *
	 * @param candidate the new candidate
	 */
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	/**
	 * Gets the skill level.
	 *
	 * @return the skill level
	 */
	@Column(name = "chs_skill_level", nullable = true)
	public String getSkillLevel() {
		return this.skillLevel;
	}
	
	/**
	 * Sets the skill level.
	 *
	 * @param skillLevel the new skill level
	 */
	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}
	
	

}
