package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import business.impl.CourseTemplateBOimpl;

public class Subject implements Serializable {
	private static final long serialVersionUID = -2025545296581545700L;
	private int subjectId;
	private String subjectName;
	private Person person;
	private String subjectDescription;
	private Set<CourseTemplate> courseTemplate = new HashSet<CourseTemplate>();
	private List<Material> materials;
	
	public String getSubjectDescription() {
		return subjectDescription;
	}

	public void setSubjectDescription(String subjectDescription) {
		this.subjectDescription = subjectDescription;
	}

	public Set<CourseTemplate> getCourseTemplate() {
		return courseTemplate;
	}

	public void setCourseTemplate(Set<CourseTemplate> courseTemplate) {
		this.courseTemplate = courseTemplate;
	}

	public Subject() {
		super();
	}

	public Subject(String subjectName, String subjectDescription) {
		super();
		this.subjectName = subjectName;
		this.subjectDescription = subjectDescription;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

}
