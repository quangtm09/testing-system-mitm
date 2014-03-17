/**
 * RTSUtils.java
 */
package com.bosch.rts.utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import org.jboss.seam.core.SeamResourceBundle;

import com.bosch.rts.entity.InterviewSchedule;
import com.bosch.rts.entity.User;

/**
 * The Class RTSUtils.
 *
 * @author khb1hc
 */
public class RTSUtils {

	/**
	 * Default construtor.
	 */
	public RTSUtils() {
		
	}
	
	/**
	 * Reset list.
	 *
	 * @param <E> the element type
	 * @param list the list
	 */
	public static <E> void resetList(List<E> list){
		if(list != null && !list.isEmpty()){
			list.clear();
		} else {
			list = new ArrayList<E>();
		}
	}	
	
	/**
	 * Reset set.
	 *
	 * @param <E> the element type
	 * @param set the set
	 */
	public static <E> void resetSet(Set<E> set){
		if(set != null && !set.isEmpty()){
			set.clear();
		} else {
			set = new HashSet<E>();
		}
	}	

	/**
	 * Checks if is blank.
	 *
	 * @param <E> the element type
	 * @param set the set
	 * @return true, if is blank
	 */
	public static <E> boolean isBlank(Set<E> set){
		if(set == null || set.isEmpty()){
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if is blank.
	 *
	 * @param <E> the element type
	 * @param list the list
	 * @return true, if is blank
	 */
	public static <E> boolean isBlank(List<E> list){
		if(list == null || list.isEmpty()){
			return true;
		}
		return false;
	}	
	
	/**
	 * Checks if is not empty.
	 *
	 * @param <E> the element type
	 * @param list the list
	 * @return true, if is not empty
	 */
	public static <E> boolean isNotEmpty(List<E> list){
		if(list != null && !list.isEmpty()){
			return true;
		}
		return false;
	}	
	
	/**
	 * Checks if is not empty.
	 *
	 * @param <E> the element type
	 * @param set the set
	 * @return true, if is not empty
	 */
	public static <E> boolean isNotEmpty(Set<E> set){
		if(set != null && !set.isEmpty()){
			return true;
		}
		return false;
	}	
	
	public static String getTime(final Date date) {	
		final ResourceBundle resourceBundle = SeamResourceBundle.getBundle("messages", Locale.ENGLISH);
		final String serverTimeZone = resourceBundle.getString("int.sch.server.time.zone");
		Calendar cal =  GregorianCalendar.getInstance(Locale.ENGLISH);
		cal.setTime(date);
		cal.add(Calendar.HOUR, Integer.valueOf(serverTimeZone));
		if (cal.get(Calendar.HOUR) == 12) {
			cal.set(Calendar.AM_PM, 0);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmm'00'");
		return sdf.format(cal.getTime());
	}
	
	public static Date convertDate(final String date) {		
		Date convertedDate = null;
		final String pattern = SeamResourceBundle.getBundle().getString("calendar.pattern");
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		try{
			convertedDate = sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return convertedDate;
	}
	
	
	public static List<User> removeDuplicatedUsers(List<User> users){
		final Set<Integer> userIds = new HashSet<Integer>();
		for (Iterator<User> it = users.listIterator(); it.hasNext();) {
			User user = it.next();
			if (userIds.add(user.getUsrUserId()) == false) {
				it.remove();
			}
		}
		
		return users;
	}
	
	public static boolean isInterviewer(final InterviewSchedule interviewSchedule, final String username){
		boolean result = false;
		final List<User> interviewers = interviewSchedule.getInterviewers();
		if (RTSUtils.isNotEmpty(interviewers)) {
			for (final User interviewer : interviewers) {
				if (interviewer.getUsrUserName().equalsIgnoreCase(username)) {
					result = true;
					break;
				}
			}		
		}

		
		return result;
		
	}
}