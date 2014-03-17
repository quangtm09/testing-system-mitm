package com.bosch.rts.session;

import java.util.Locale;

import org.jboss.seam.annotations.Name;

@Name("calendarBean")
public class CalendarBean {

	
	private Locale locale;
	
	public CalendarBean(){
		setLocale(Locale.ENGLISH);
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Locale getLocale() {
		return locale;
	}
}
