/*
 * 
 */
package com.bosch.rts.validator;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.faces.FacesMessages;

import com.bosch.rts.entity.OrgUnit;
import com.bosch.rts.entity.RecruitRequest;

/**
 * Custom Validator for Recruit Request Name.
 * @author KHB1HC
 */
@Name("rrcNameValidator")
@org.jboss.seam.annotations.faces.Validator
@BypassInterceptors
public class RecruitRequestCreationNameValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.validator.Validator#validate(javax.faces.context.FacesContext
	 * , javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public void validate(FacesContext arg0, UIComponent component, Object value) throws ValidatorException {
		if(value == null){
			return;
		}		
		
		final String rcrNameInput = String.valueOf(value);		
		
		UIInput orgUnitUiInput = (UIInput) component.getAttributes().get("orgUnitComponent");		
		OrgUnit orgUnit = (OrgUnit) orgUnitUiInput.getValue();	
		
		final EntityManager entityManager = (EntityManager) org.jboss.seam.Component.getInstance("entityManager");

		final StringBuilder sql = new StringBuilder();
		sql.append("select recruitRequest from RecruitRequest recruitRequest ");
		sql.append("where lower(recruitRequest.recruitRequestName) = lower(:recruitRequestName) ");
		sql.append("and recruitRequest.orgUnit.orgUnitId = :orgUnitId ");
		
		@SuppressWarnings("unchecked")
		final List<RecruitRequest> recruitRequests = entityManager
				.createQuery(sql.toString())
				.setParameter("recruitRequestName", rcrNameInput.trim())
				.setParameter("orgUnitId", orgUnit.getOrgUnitId())
				.getResultList();

		if (recruitRequests != null && !recruitRequests.isEmpty()) {
			((UIInput) component).setValid(false);
			FacesMessages.instance().addToControlFromResourceBundle(component.getId(), "recruit.request.name.existed");
		} 	

		
	}

}
