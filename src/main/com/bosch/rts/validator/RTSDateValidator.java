/**
 * 
 */
package com.bosch.rts.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Class RTSDateValidator.
 *
 * @author khb1hc
 */
public class RTSDateValidator {
	
	/** The Constant DATE_FORTMAT. */
	private static final String DATE_FORTMAT = "dd.MMM.yyyy";

	/**
	 * Checks if is this date valid.
	 *
	 * @param dateToValidate the date to validate
	 * @return true, if is this date valid
	 */
	public static boolean isThisDateValid(String dateToValidate) {

		if (dateToValidate == null) {
			return true;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORTMAT);
		sdf.setLenient(false);

		try {
			sdf.parse(dateToValidate);
		} catch (ParseException e) {
			return false;
		}

		return true;
	}
	
	/**
	 * Compare dates.
	 *
	 * @param expectedDate the expected date
	 * @param validDate the valid date
	 * @return true, if successful
	 */
	public static boolean compareDates(Date expectedDate, Date validDate) {
		if(expectedDate != null && validDate != null){
			if(expectedDate.after(validDate)){
				return false;
			}			
		}
		return true;
	}
	
	/**
	 * Validate dates.
	 *
	 * @param expectedDate the expected date
	 * @param validDate the valid date
	 * @return the string
	 */
	public static String validateDates(Date expectedDate, Date validDate){
		String errorMsg = null;
		if(!RTSDateValidator.isThisDateValid(expectedDate != null ? expectedDate.toString() : null)){
			errorMsg = "error.recruit.request.invalid.expectedDate";
		}
		
		if(!RTSDateValidator.isThisDateValid(validDate != null ? validDate.toString() : null)){
			errorMsg = "error.recruit.request.invalid.validDate";
		}
		
		return errorMsg;
	}	
	
	
	/*public static void main(String[] args) {
		RTSDateValidator dateValidator = new RTSDateValidator();
		dateValidator.isThisDateValid("30.Jun.2012", "dd.MMM.yyyy");
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 2013);
		
		 
		System.out.println("com: " + dateValidator.compareDates(new Date(), cal.getTime()));
		
	}*/
}