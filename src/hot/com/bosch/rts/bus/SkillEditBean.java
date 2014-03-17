package com.bosch.rts.bus;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import com.bosch.rts.session.SkillHome;

/**
 * The Class SkillEditBean.
 */
@Name("skillEditBean")
@Scope(ScopeType.CONVERSATION)
public class SkillEditBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6648946339205177697L;
	
	/** The log. */
	@Logger
	private transient Log log;

	/** The action. */
	String action;

	/**
	 * Gets the action.
	 *
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Sets the action.
	 *
	 * @param action the new action
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/** The skill home. */
	@In
	SkillHome skillHome;

	/**
	 * Update skill.
	 */
	public void updateSkill() {
		try {
			skillHome.update();
		} catch (Exception e) {
			log.error("Execute updateSkill method getting error------------------");
		}
	}

	/**
	 * Adds the skill.
	 */
	public void addSkill() {
		try {
			skillHome.persist();
		} catch (Exception e) {
			log.error("Execute addSkill method getting error------------------");
		}
	}

	/**
	 * Validate skill name.
	 *
	 * @return true, if successful
	 */
	public boolean validateSkillName() {
		EntityManager entityManager = (EntityManager) Component
				.getInstance("entityManager");
		String sql = "select t from  Skill t where lower( t.sklName ) = lower(:value)";
		if (skillHome.isIdDefined()) {
			sql += " and t.sklSkillId <> :id";
		}
		Query query = entityManager.createQuery(sql);
		query.setParameter("value", skillHome.getInstance().getSklSkillId());
		if (skillHome.isIdDefined()) {
			query.setParameter("id", skillHome.getId());
		}
		List<?> temp = query.getResultList();
		if (temp.size() > 0) {
			return false;
		}
		return true;
	}

}
