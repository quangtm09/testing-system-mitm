/**
 * /rts/src/main/com/bosch/rts/validator/TechinicalDateComparator.java
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
@Name("techinicalDateComparator")
@org.jboss.seam.annotations.faces.Validator
@BypassInterceptors
public class TechinicalDateComparator implements Validator {

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
		
		Date joinedDate = (Date) value;
		
		UIInput uiInput = (UIInput) component.getAttributes().get("promotionDateComponent");
		if(uiInput == null){
			return;
		}
	
		String promotionDateValue = (String) uiInput.getSubmittedValue();
		final String pattern = SeamResourceBundle.getBundle().getString("calendar.pattern");
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
		Date promotionDate;
		try {
			promotionDate = dateFormat.parse(promotionDateValue);
		} catch (ParseException e) {			
			return;
		}
		if (joinedDate == null || promotionDate == null) {
			return;
		}
		if (joinedDate.compareTo(promotionDate) > 0) {
			uiInput.setValid(false);
			throw new ValidatorException(new FacesMessage(SeamResourceBundle.getBundle().getString("feedback.reviewed.promotion.error")));
		}
	}

}
