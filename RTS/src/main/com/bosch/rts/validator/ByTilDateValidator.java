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
 * The Class ByTilDateValidator.
 */
public class ByTilDateValidator implements Validator
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
		Date dteBy = (Date) value;
		UIInput dteToComponent = (UIInput) component.getAttributes().get("dteTil");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MMM.yyyy");
		Date dteTil = null;
		try
		{
			dteTil = sdf.parse(dteToComponent.getSubmittedValue().toString());
		} catch (ParseException e)
		{
			log.error("Execute validate method getting error on ByTilDateValidator class------------------");
		}
		
		if (dteBy != null && dteTil != null && dteBy.after(dteTil))
		{
			throw new ValidatorException(new FacesMessage(
					"Required By Date must be equal or before Required Til Date"));
		}
	}
}
