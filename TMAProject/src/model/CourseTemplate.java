package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseTemplate implements Serializable {

	private static final long serialVersionUID = 2579330640022050327L;
	private int id;
	private String name;
	private GregorianCalendar startDay;
	private GregorianCalendar endDay;
	private String description;
	private Set<Subject> subject = new HashSet<Subject>();

	public CourseTemplate() {

	}

	

	public Set<Subject> getSubject() {
		return subject;
	}



	public void setSubject(Set<Subject> subject) {
		this.subject = subject;
	}



	public GregorianCalendar getStartDay() {
		return startDay;
	}

	public void setStartDay(GregorianCalendar startDay) {
		this.startDay = startDay;
	}

	public GregorianCalendar getEndDay() {
		return endDay;
	}

	public void setEndDay(GregorianCalendar endDay) {
		this.endDay = endDay;
	}



	public CourseTemplate(String name, GregorianCalendar startDay,
			GregorianCalendar endDay, String description) {
		super();
		this.name = name;
		this.startDay = startDay;
		this.endDay = endDay;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CourseTemplate [id=" + id + ", name=" + name + ", startDay="
				+ startDay + ", endDay=" + endDay + ", description="
				+ description + ", subject=" + subject + "]";
	}

}
