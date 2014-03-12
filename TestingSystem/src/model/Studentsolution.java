package model;// default package
// Generated Mar 12, 2014 10:45:56 PM by Hibernate Tools 3.6.0

import java.util.HashSet;
import java.util.Set;

/**
 * Studentsolution generated by hbm2java
 */
public class Studentsolution implements java.io.Serializable {

	private Integer studentSolId;
	private Student student;
	private Tquestion tquestion;
	private String studentSolComment;
	private Set<Squestionsolution> squestionsolutions = new HashSet<Squestionsolution>(
			0);

	public Studentsolution() {
	}

	public Studentsolution(Student student, Tquestion tquestion,
			String studentSolComment, Set<Squestionsolution> squestionsolutions) {
		this.student = student;
		this.tquestion = tquestion;
		this.studentSolComment = studentSolComment;
		this.squestionsolutions = squestionsolutions;
	}

	public Integer getStudentSolId() {
		return this.studentSolId;
	}

	public void setStudentSolId(Integer studentSolId) {
		this.studentSolId = studentSolId;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Tquestion getTquestion() {
		return this.tquestion;
	}

	public void setTquestion(Tquestion tquestion) {
		this.tquestion = tquestion;
	}

	public String getStudentSolComment() {
		return this.studentSolComment;
	}

	public void setStudentSolComment(String studentSolComment) {
		this.studentSolComment = studentSolComment;
	}

	public Set<Squestionsolution> getSquestionsolutions() {
		return this.squestionsolutions;
	}

	public void setSquestionsolutions(Set<Squestionsolution> squestionsolutions) {
		this.squestionsolutions = squestionsolutions;
	}

}
