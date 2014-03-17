/*
 * /rts/src/main/com/bosch/rts/validator/CandidateValidator.java
 */
package com.bosch.rts.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.core.SeamResourceBundle;
import org.jboss.seam.faces.FacesMessages;

import com.bosch.rts.entity.User;

/**
 * Candidate Module: validate form fields
 * @author khb1hc
 */
@Name("candidateValidator")
@Scope(ScopeType.EVENT)
@BypassInterceptors
public class CandidateValidator {	
	
	/**
	 * Validate handled by.
	 *
	 * @param context the context
	 * @param component the component
	 * @param value the value
	 */
	public void validateHandledBy(FacesContext context, UIComponent component, Object value) {
		String handledBy = (String) value;
		
		if (handledBy != null && !handledBy.isEmpty()) {
			final EntityManager entityManager = (EntityManager) org.jboss.seam.Component.getInstance("entityManager");

			@SuppressWarnings("unchecked")
			final List<User> users = entityManager
					.createQuery("select user from User user where lower(user.usrUserName) = lower(:usrUserName) ")
					.setParameter("usrUserName", handledBy.trim())
					.getResultList();

			if (users == null || users.isEmpty()) {
				((UIInput) component).setValid(false);
				throw new ValidatorException(new FacesMessage(SeamResourceBundle.getBundle().getString("error.invalid.handled.by")));
			} else if (users.size() > 1) {
				((UIInput) component).setValid(false);
				throw new ValidatorException(new FacesMessage(SeamResourceBundle.getBundle().getString("error.unique.handled.by")));
			}
			
		}	
    }
	
	/**
	 * Validate short list by.
	 *
	 * @param context the context
	 * @param component the component
	 * @param value the value
	 */
	public void validateShortListBy(FacesContext context, UIComponent component, Object value) {
		String shortListBy = (String) value;
		if (shortListBy != null && !shortListBy.isEmpty()) {
			final EntityManager entityManager = (EntityManager) org.jboss.seam.Component.getInstance("entityManager");
			
			@SuppressWarnings("unchecked")
			final List<User> users = entityManager
					.createQuery("select user from User user where lower(user.usrUserName) = lower(:usrUserName) ")
					.setParameter("usrUserName", shortListBy.trim())
					.getResultList();

			if (users == null || users.isEmpty()) {
				((UIInput) component).setValid(false);
				throw new ValidatorException(new FacesMessage(SeamResourceBundle.getBundle().getString("error.invalid.shortList.by")));
			} else if (users.size() > 1) {
				((UIInput) component).setValid(false);
				throw new ValidatorException(new FacesMessage(SeamResourceBundle.getBundle().getString("error.unique.shortList.by")));
			}
		}	
    }
	
	/**
	 * Validate date of birth.
	 *
	 * @param context the context
	 * @param component the component
	 * @param value the value
	 */
	public void validateDOB(FacesContext context, UIComponent component, Object value) {
		String dob = (String) value;
		if (dob != null && !dob.isEmpty()) {
			final String pattern = SeamResourceBundle.getBundle().getString("calendar.pattern");
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
			Date dobDate = null;
			boolean isDate = false;
			try {
				dobDate = dateFormat.parse(dob);
				isDate = true;
			} catch (ParseException e) {				 
			}
			
			final int MIN_YEAR = Integer.valueOf(SeamResourceBundle.getBundle().getString("candidate.min.age"));
			final int MAX_YEAR = Integer.valueOf(SeamResourceBundle.getBundle().getString("candidate.max.age"));
			
			Calendar minCal = GregorianCalendar.getInstance();
			minCal.add(Calendar.YEAR, -MIN_YEAR); 				
			
			Calendar maxCal = GregorianCalendar.getInstance();
			maxCal.add(Calendar.YEAR, -MAX_YEAR);
			
			Calendar currentCal = GregorianCalendar.getInstance();
						
			if(isDate){					
				currentCal.setTime(dobDate);				
			} else {
				int yearOfBirth = 0;	
				try { 
					yearOfBirth = Integer.parseInt(dob); 
					currentCal.set(Calendar.YEAR, yearOfBirth);
			    } catch(NumberFormatException e) { 
			    	((UIInput) component).setValid(false);
			    	FacesMessages.instance().addToControlFromResourceBundle(component.getId(), "error.invalid.dob");
			    	return;
			    }				
			}
						
			if(currentCal.after(minCal)){
				((UIInput) component).setValid(false);
				FacesMessages.instance().addToControlFromResourceBundle(component.getId(), "candidate.dob.min.error", MIN_YEAR);
			} else if(currentCal.before(maxCal)){
				((UIInput) component).setValid(false);	
				FacesMessages.instance().addToControlFromResourceBundle(component.getId(), "candidate.dob.max.error", MAX_YEAR);			
								
			}
						
		}	
	}
	
	public void validateYearsOfExp(FacesContext context, UIComponent component, Object value) {		
		if(value == null){
			return;
		}
		
		final String years = String.valueOf(value);
		if(years == null || years.trim().length() <= 0){
			return;
		}
		Float yeo;
		try { 
			yeo = Float.parseFloat(years);
	    } catch(NumberFormatException e) { 
	    	((UIInput) component).setValid(false);	
			FacesMessages.instance().addToControlFromResourceBundle(component.getId(), "candidate.yeo.error");
			return;
	    }
	    
	    if(yeo < 0 || yeo > 30){
	    	((UIInput) component).setValid(false);	
			FacesMessages.instance().addToControlFromResourceBundle(component.getId(), "candidate.yeo.error");
			return;
	    }
		
	}
	
	public void validateRelYearsOfExp(FacesContext context, UIComponent component, Object value) {		
		if(value == null){
			return;
		}
		
		final String years = String.valueOf(value);
		if(years == null || years.trim().length() <= 0){
			return;
		}
		
		Float yeo;
		try { 
			yeo = Float.parseFloat(years);
	    } catch(NumberFormatException e) { 
	    	((UIInput) component).setValid(false);	
			FacesMessages.instance().addToControlFromResourceBundle(component.getId(), "candidate.yeo.error");
			return;
	    }
	    
	    if(yeo < 0 || yeo > 30){
	    	((UIInput) component).setValid(false);	
			FacesMessages.instance().addToControlFromResourceBundle(component.getId(), "candidate.ryeo.error");
			return;
	    }
		
	}
	

}
