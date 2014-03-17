/**
 * /rts/src/main/com/bosch/rts/validator/InterviewScheduleDateValidator.java
 */
package com.bosch.rts.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.core.SeamResourceBundle;

/**
 * Technical interview time and HR validation
 * 
 * @author khb1hc
 * 
 */
@Name("interviewScheduleDateValidator")
@Scope(ScopeType.EVENT)
@BypassInterceptors
public class InterviewScheduleDateValidator{

	/**
	 * In case of both Technical and HR interview time are enabled
	 * @param arg0 faceContext
	 * @param component UIComponent
	 * @param value current technical interview time object value
	 * @throws ValidatorException
	 */
	public void validateTech(FacesContext arg0, UIComponent component, Object value) throws ValidatorException {
		Date technicalDate = (Date)value;
		Date hrDate = null;		
			
		UIInput hrDateInput = (UIInput) component.getAttributes().get("hrDateComponent");	
		String hrDateValue = (String) hrDateInput.getSubmittedValue();	
		if(hrDateValue == null || hrDateValue.isEmpty()){
			return;
		}
		
		final String pattern = SeamResourceBundle.getBundle().getString("calendar.pattern.long");
		final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);	
		
		try {
			hrDate = dateFormat.parse(hrDateValue);
		} catch (ParseException e) {
			((UIInput) component).setValid(false);
			throw new ValidatorException(new FacesMessage(SeamResourceBundle.getBundle().getString("int.sch.tech.hr.dates.invalid")));
		}	
		
		if (technicalDate.after(hrDate)) {
			((UIInput) component).setValid(false);
			throw new ValidatorException(new FacesMessage(SeamResourceBundle.getBundle().getString("int.sch.tech.hr.dates.compare.error")));
		}

	}
	
	/**
	 * In case of Technical interview time is enabled and HR interview time is disabled
	 * @param arg0 faceContext
	 * @param component UIComponent
	 * @param value current technical interview time object value
	 * @throws ValidatorException
	 */
	public void validateTech2(FacesContext arg0, UIComponent component, Object value) throws ValidatorException {
		Date technicalDate = (Date)value;
		Date hrDate = null;		
			
		UIInput hrDateInput = (UIInput) component.getAttributes().get("hrDateComponent2");	
		String hrDateValue = (String) hrDateInput.getSubmittedValue();		
		if(hrDateValue == null || hrDateValue.isEmpty()){
			Object hrObject =  hrDateInput.getValue();
			if(hrObject == null){
				return;
			} else {
				hrDate = (Date)hrObject;
			}
		} else {
			final String pattern = SeamResourceBundle.getBundle().getString("calendar.pattern.long");
			final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);	
			
			try {
				hrDate = dateFormat.parse(hrDateValue);
			} catch (ParseException e) {
				((UIInput) component).setValid(false);
				throw new ValidatorException(new FacesMessage(SeamResourceBundle.getBundle().getString("int.sch.tech.hr.dates.invalid")));
			}	
			
		}		
		
		if (technicalDate.after(hrDate)) {
			((UIInput) component).setValid(false);
			throw new ValidatorException(new FacesMessage(SeamResourceBundle.getBundle().getString("int.sch.tech.hr.dates.compare.error")));
		}

	}
	
	/**
	 * In case of Technical interview time is disabled and HR interview time is enabled
	 * @param arg0 faceContext
	 * @param component UIComponent
	 * @param value current HR interview time object value
	 * @throws ValidatorException
	 */
	public void validateHR(FacesContext arg0, UIComponent component, Object value) throws ValidatorException {
		Date hrDate = (Date)value;
		Date technicalDate = null;		
			
		UIInput techDateInput = (UIInput) component.getAttributes().get("technicalDateComponent");	
		String techDateValue = (String) techDateInput.getSubmittedValue();	
		if(techDateValue == null  || techDateValue.isEmpty()){
			Object techObject =  techDateInput.getValue();
			if(techObject == null){
				return;
			} else {
				technicalDate = (Date)techObject;
			}
		} else {
			final String pattern = SeamResourceBundle.getBundle().getString("calendar.pattern.long");
			final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);	
			
			try {
				technicalDate = dateFormat.parse(techDateValue);
			} catch (ParseException e) {
				((UIInput) component).setValid(false);
				throw new ValidatorException(new FacesMessage(SeamResourceBundle.getBundle().getString("int.sch.tech.hr.dates.invalid")));
			}
		}
		
		if (technicalDate.after(hrDate)) {
			((UIInput) component).setValid(false);
			throw new ValidatorException(new FacesMessage(SeamResourceBundle.getBundle().getString("int.sch.hr.tech.dates.compare.error")));
		}
	}

}