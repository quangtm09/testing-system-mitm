package com.bosch.rts.bus;

import com.bosch.rts.entity.Skill;

/**
 * The Class ChosenSkill.
 */
public class ChosenSkill {
	
	/** The skill. */
	Skill skill;
	
	/** The year1. */
	private int year1;
	
	/** The year2. */
	private int year2;	
	
	/** The skill level. */
	private String skillLevel;
	
	/**
	 * Instantiates a new chosen skill.
	 */
	public ChosenSkill() {
		year1 = 0;
		year2 = 0;
		skill = null;
		skillLevel = null;
	}

	/**
	 * Gets the skill level.
	 *
	 * @return the skill level
	 */
	public String getSkillLevel() {
		return skillLevel;
	}
	
	/**
	 * Sets the skill level.
	 *
	 * @param skillLevel the new skill level
	 */
	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}

	/**
	 * Gets the year1.
	 *
	 * @return the year1
	 */
	public int getYear1() {
		return year1;
	}

	/**
	 * Sets the year1.
	 *
	 * @param year1 the new year1
	 */
	public void setYear1(int year1) {
		this.year1 = year1;
	}

	/**
	 * Gets the year2.
	 *
	 * @return the year2
	 */
	public int getYear2() {
		return year2;
	}

	/**
	 * Sets the year2.
	 *
	 * @param year2 the new year2
	 */
	public void setYear2(int year2) {
		this.year2 = year2;
	}

	/**
	 * Instantiates a new chosen skill.
	 *
	 * @param skill the skill
	 */
	public ChosenSkill(Skill skill) {
		this.skill = skill;
		year1 = 0;
		year2 = 0;
	}

	/**
	 * Instantiates a new chosen skill.
	 *
	 * @param skill the skill
	 * @param defaultYear the default year
	 */
	public ChosenSkill(Skill skill, int defaultYear) {
		this.skill = skill;
		year1 = defaultYear;
		year2 = defaultYear;
	}
	
	/**
	 * Instantiates a new chosen skill.
	 *
	 * @param skill the skill
	 * @param defaultYear the default year
	 * @param skillLevel the skill level
	 */
	public ChosenSkill(Skill skill, int defaultYear, String skillLevel) {
		this.skill = skill;
		year1 = defaultYear;
		year2 = defaultYear;
		this.skillLevel = skillLevel;
	}

	/**
	 * Instantiates a new chosen skill.
	 *
	 * @param skill the skill
	 * @param year1 the year1
	 * @param year2 the year2
	 */
	public ChosenSkill(Skill skill, int year1, int year2) {
		this.skill = skill;
		this.year1 = year1;
		this.year2 = year2;
	}
	
	/**
	 * Instantiates a new chosen skill.
	 *
	 * @param skill the skill
	 * @param year1 the year1
	 * @param year2 the year2
	 * @param skillLevel the skill level
	 */
	public ChosenSkill(Skill skill, int year1, int year2, String skillLevel) {
		this.skill = skill;
		this.year1 = year1;
		this.year2 = year2;
		this.skillLevel = skillLevel;		
	}

	/**
	 * Gets the skill.
	 *
	 * @return the skill
	 */
	public Skill getSkill() {
		return skill;
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
	 * Gets the from year.
	 *
	 * @return the from year
	 */
	public int getFromYear() {
		return year1;
	}

	/**
	 * Sets the from year.
	 *
	 * @param fromYear the new from year
	 */
	public void setFromYear(int fromYear) {
		this.year1 = fromYear;
	}

	/**
	 * Gets the to year.
	 *
	 * @return the to year
	 */
	public int getToYear() {
		return year2;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public int getYear() {
		return year1;
	}

	/**
	 * Sets the to year.
	 *
	 * @param toYear the new to year
	 */
	public void setToYear(int toYear) {
		this.year2 = toYear;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Skill) {
			return skill.equals((Skill) arg0);
		}
		if (arg0 instanceof ChosenSkill) {
			return skill.getSklSkillId().equals(
					((ChosenSkill) arg0).getSkill().getSklSkillId());
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return skill.hashCode();
	}
}