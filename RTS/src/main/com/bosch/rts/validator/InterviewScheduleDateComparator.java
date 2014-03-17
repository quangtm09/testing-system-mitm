/**
 * /rts/src/main/com/bosch/rts/validator/InterviewScheduleDateComparator.java
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
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.core.SeamResourceBundle;

/**
 * Dates comparator
 * 
 * @author khb1hc
 * 
 */
@Name("interviewScheduleDateComparator")
@org.jboss.seam.annotations.faces.Validator
@BypassInterceptors
public class InterviewScheduleDateComparator implements Validator {

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
		Date technicalDate = (Date) value;		
		
		Date technicalAssessmentDate = null;
		
		UIInput uiInput = (UIInput) component.getAttributes().get("hrDateComponent");		
		String hrDateValue = (String) uiInput.getSubmittedValue();		
		
		if(hrDateValue == null || hrDateValue.isEmpty()){
			Object disabledHrDateObject = uiInput.getValue();
			if(disabledHrDateObject == null){
				return;
			}	
			Date disabledHrDateValue = (Date) disabledHrDateObject;
					
			if (technicalDate.compareTo(disabledHrDateValue) > 0) {
				uiInput.setValid(false);
				throw new ValidatorException(new FacesMessage(SeamResourceBundle.getBundle().getString("int.sch.tech.hr.dates.compare.error")));
			}
			
		} else {
			final String pattern = SeamResourceBundle.getBundle().getString("calendar.pattern.long");
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
			try {
				technicalAssessmentDate = dateFormat.parse(hrDateValue);
			} catch (ParseException e) {
				uiInput.setValid(false);
				throw new ValidatorException(new FacesMessage(SeamResourceBundle.getBundle().getString("int.sch.tech.hr.dates.invalid")));
			}	
			
			if (technicalDate.compareTo(technicalAssessmentDate) > 0) {
				uiInput.setValid(false);
				throw new ValidatorException(new FacesMessage(SeamResourceBundle.getBundle().getString("int.sch.tech.hr.dates.compare.error")));
			}
		}
		
	}

}
