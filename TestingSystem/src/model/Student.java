package model;

// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Student generated by hbm2java
 */
@Entity
@Table(name = "student")
public class Student implements java.io.Serializable {

	private String studentId;
	private String studentName;
	private Set<Studentsolution> studentsolutions = new HashSet<Studentsolution>(
			0);
	private Set<Studenttestmap> studenttestmaps = new HashSet<Studenttestmap>(0);

	public Student() {
	}

	public Student(String studentId, String studentName) {
		this.studentId = studentId;
		this.studentName = studentName;
	}

	public Student(String studentId, String studentName,
			Set<Studentsolution> studentsolutions,
			Set<Studenttestmap> studenttestmaps) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentsolutions = studentsolutions;
		this.studenttestmaps = studenttestmaps;
	}

	@Id
	@Column(name = "STUDENT_ID", unique = true, nullable = false, length = 10)
	public String getStudentId() {
		return this.studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	@Column(name = "STUDENT_NAME", nullable = false, length = 50)
	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
	public Set<Studentsolution> getStudentsolutions() {
		return this.studentsolutions;
	}

	public void setStudentsolutions(Set<Studentsolution> studentsolutions) {
		this.studentsolutions = studentsolutions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
	public Set<Studenttestmap> getStudenttestmaps() {
		return this.studenttestmaps;
	}

	public void setStudenttestmaps(Set<Studenttestmap> studenttestmaps) {
		this.studenttestmaps = studenttestmaps;
	}

}
