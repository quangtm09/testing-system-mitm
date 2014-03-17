package com.bosch.rts.validator;

import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.core.SeamResourceBundle;

import com.bosch.rts.entity.CandidateHasSkill;
import com.bosch.rts.entity.RecruitRequestHasSkill;
import com.bosch.rts.entity.Skill;

/**
 * The Class SkillValidator.
 */
@Name("skillValidator")
@Scope(ScopeType.EVENT)
@BypassInterceptors
public class SkillValidator implements Validator {

	/* (non-Javadoc)
	 * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		String value = (String) arg2;
		if(value == null || value.trim().length() <= 0){
			return;
		}
		
		if (!validateSkillName(value.trim(), -1)) {
			((UIInput) arg1).setValid(false);
			throw new ValidatorException(new FacesMessage(SeamResourceBundle.getBundle().getString("com.bosch.ui.skillname.invalid")));
		}

	}
	
	/* (non-Javadoc)
	 * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	public void validateEditMode(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		String value = (String) arg2;
		if(value == null || value.trim().length() <= 0){
			return;
		}
		
		final Object skillNameObj = arg1.getAttributes().get("skillNameTextField");	
		if(skillNameObj == null){
			return;
		}
		
		if(skillNameObj.toString().equalsIgnoreCase(value.trim())){
			return;
		}
		
		if (!validateSkillName(value.trim(), Integer.valueOf(skillNameObj.toString()))) {
			((UIInput) arg1).setValid(false);
			throw new ValidatorException(new FacesMessage(SeamResourceBundle.getBundle().getString("com.bosch.ui.skillname.invalid")));
		}

	}

	/**
	 * Validate skill name.
	 *
	 * @param value the value
	 * @return true, if successful
	 */
	public boolean validateSkillName(String value, int skillId) {
		EntityManager entityManager = (EntityManager) Component	.getInstance("entityManager");
		String sql = "select t from  Skill t where lower( t.sklName ) = lower(:value)";
		if (skillId != -1) {
			sql += " and t.sklSkillId <> :sklSkillId";
		}
		
		final Query query = entityManager.createQuery(sql);
		query.setParameter("value", value);
		if (skillId != -1) {
			query.setParameter("sklSkillId", skillId);
		}
		List<?> temp = query.getResultList();
		if (temp != null && !temp.isEmpty()) {
			return false;
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	public void validateActiveState(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		if(arg2 == null){
			return;
		}
		
		//if new active state is true, ignore validation
		final boolean newActiveState = ((Boolean) arg2).booleanValue();		
		if(newActiveState){
			return;
		}		
		
		//get skill id from Richfaces form
		final Object activeStateObj = arg1.getAttributes().get("activeStateMenu");	
		if(activeStateObj == null){
			return;
		}		
		
		//get skill entity
		final EntityManager entityManager = (EntityManager) Component.getInstance("entityManager");
		String sql = "select t from Skill t where t.sklSkillId = :sklSkillId";
		final Query query = entityManager.createQuery(sql);
		query.setParameter("sklSkillId", Integer.valueOf(activeStateObj.toString()));

		List<?> temp = query.getResultList();
		if(temp != null  && !temp.isEmpty()){
			final Skill skill = (Skill)temp.get(0);
			
			final boolean oldActiveState = skill.getActiveState();
			
			boolean error = false;
			
			//If new active state is false,
			if(newActiveState != oldActiveState){
				final Set<CandidateHasSkill> candidateHasSkills = skill.getCandidateHasSkills();
				if(candidateHasSkills != null && !candidateHasSkills.isEmpty()){
					error = true;
				} else {
					final Set<RecruitRequestHasSkill> requestHasSkills = skill.getRecruitRequestHasSkills();
					if(requestHasSkills != null && !requestHasSkills.isEmpty()){
						error = true;
					}
				}
			}			
			
			if (error) {
				((UIInput) arg1).setValid(false);
				throw new ValidatorException(new FacesMessage(SeamResourceBundle.getBundle().getString("form.edit.error")));
			}
			
		}		
		
	}
	

}
