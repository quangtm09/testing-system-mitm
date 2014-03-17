package com.bosch.rts.session;

import java.util.List;

import javax.persistence.Query;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.bosch.rts.entity.Skill;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSQueries;

/**
 * The Class SkillList.
 */
@Name("skillList")
public class SkillList extends EntityQuery<Skill> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7486456734845658729L;
	
	/** The Constant EJBQL. */
	private static final String EJBQL = "select skill from Skill skill";
	
	/** The Constant EJBQL. */
	private static final String ACTIVE_STATE = " where skill.activeState = 1 ";
	
	/** The Constant ASC_SORT_BY_SKILL_NAME. */
	private static final String ASC_SORT_BY_SKILL_NAME = " order by skill.sklName asc";

	/** The skill. */
	private Skill skill = new Skill();

	/**
	 * Instantiates a new skill list.
	 */
	public SkillList() {
		setEjbql(EJBQL + ACTIVE_STATE + ASC_SORT_BY_SKILL_NAME);
	}

	
	public List<Skill> loadAllSkills(){
		this.setEjbql(EJBQL + ASC_SORT_BY_SKILL_NAME);
		return this.getResultList();
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
	 * Gets the skill by skill name.
	 *
	 * @param skillName the skill name
	 * @return the skill by skill name
	 */
	@SuppressWarnings("unchecked")
	public List<Skill> getSkillBySkillName(final String skillName) {
		final String queryStr = RTSQueries.getSkillBySkillName();
		final Query query = getEntityManager().createQuery(queryStr);
		query.setParameter(RTSConstants.SKL_NAME, skillName);
		final List<Skill> skillList = query.getResultList();			
		return skillList;
	} 
}
