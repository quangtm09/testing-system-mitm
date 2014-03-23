/**
 * /rts/src/main/com/bosch/rts/validator/RecruitRequestDateComparator.java
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
 * Dates comparator for Recruit Request module
 * 
 * @author khb1hc
 * 
 */
@Name("recruitRequestDateComparator")
@org.jboss.seam.annotations.faces.Validator
@BypassInterceptors
public class RecruitRequestDateComparator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.validator.Validator#validate(javax.faces.context.FacesContext
	 * , javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public void validate(FacesContext arg0, UIComponent component, Object value) throws ValidatorException {
			
		Date requestedDate = (Date) value;
		
		UIInput closedDateUiInput = (UIInput) component.getAttributes().get("closedDateComponent");		
		final String closedDateValue = (String) closedDateUiInput.getSubmittedValue();
		if(closedDateValue == null || closedDateValue.trim().length() <= 0){
			return;
		}
		
		final String pattern = SeamResourceBundle.getBundle().getString("calendar.pattern");	
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
		Date closedDate = null;
		try {
			closedDate = dateFormat.parse(closedDateValue);
		} catch (ParseException e) {
			((UIInput) component).setValid(false); 
			throw new ValidatorException(new FacesMessage(SeamResourceBundle.getBundle("messages", Locale.ENGLISH).getString("recruit.request.invalid.date")));			
		}		
		
		//Check if requested date is after closed date
		if (requestedDate.after(closedDate)) {
			((UIInput) component).setValid(false);
			throw new ValidatorException(new FacesMessage(SeamResourceBundle.getBundle("messages", Locale.ENGLISH).getString("error.recruit.request.expectedDate.validDate")));
		}
		
		
		/*UIInput approvedDateUiInput = (UIInput) component.getAttributes().get("approvedDateComponent");		
		String approvedDateValue = (String) approvedDateUiInput.getSubmittedValue();
		if(approvedDateValue == null){
			return;
		}
		
		if(approvedDateValue != null && !approvedDateValue.trim().isEmpty()){
			Date approvedDate;
			try {
				approvedDate = dateFormat.parse(approvedDateValue);
			} catch (ParseException e) {
				return;
			}
			
			//Check if requested date is after approved date
			if (requestedDate.after(approvedDate)) {
				((UIInput) component).setValid(false);
				throw new ValidatorException(new FacesMessage(SeamResourceBundle.getBundle().getString("error.recruit.request.requested.validDate")));
			}	
			
		}	*/
		
	}
	


}