package com.bosch.rts.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.jboss.seam.annotations.Logger;
import org.jboss.seam.log.Log;

/**
 * The Class FromToDateValidator.
 */
public class FromToDateValidator implements Validator
{

	/** The log. */
	@Logger
	private transient Log log;
	
	/* (non-Javadoc)
	 * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException
	{
		Date dteFrom = (Date) value;
		UIInput dteToComponent = (UIInput) component.getAttributes().get(
				"dteTo");
		SimpleDateFormat sdf = new SimpleDateFormat("MMM/dd/yyyy");
		Date dteTo = null;
		try
		{
			dteTo = sdf.parse(dteToComponent.getSubmittedValue().toString());
		} catch (ParseException e)
		{		
			log.error("Execute saveCV method getting error------------------");
		}

		if (dteFrom != null && dteTo != null && dteFrom.after(dteTo))
		{
			throw new ValidatorException(new FacesMessage(
					"From Date must be less than To Date"));
		}
	}
}
