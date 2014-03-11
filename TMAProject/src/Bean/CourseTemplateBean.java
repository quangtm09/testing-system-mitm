package Bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Subject;

public class CourseTemplateBean {
	private int id;
	private String name;
	private String startDay;
	private String startEnd;
	private String description;
	private String SubjectError;
	private Set<Subject> subject = new HashSet<Subject>();
	private Set<Integer> inter = new HashSet<Integer>();
	public CourseTemplateBean() {

	}

	public String getSubjectError() {
		return SubjectError;
	}

	public void setSubjectError(String subjectError) {
		SubjectError = subjectError;
	}

	public Set<Integer> getInter() {
		return inter;
	}

	public void setInter(Set<Integer> inter) {
		this.inter = inter;
	}

	public CourseTemplateBean(int id, String name, String startDay,
			String startEnd, String description) {
		super();
		this.id = id;
		this.name = name;
		this.startDay = startDay;
		this.startEnd = startEnd;
		this.description = description;
	}

	public CourseTemplateBean(String name, String startDay, String startEnd,
			String description, Set<Subject> subject) {
		super();
		this.name = name;
		this.startDay = startDay;
		this.startEnd = startEnd;
		this.description = description;
		this.subject = subject;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getStartEnd() {
		return startEnd;
	}

	public void setStartEnd(String startEnd) {
		this.startEnd = startEnd;
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

	public Set<Subject> getSubject() {
		return subject;
	}

	public void setSubject(Set<Subject> subject) {
		this.subject = subject;
	}

}
