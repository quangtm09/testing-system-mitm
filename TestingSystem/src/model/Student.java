package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the student database table.
 * 
 */
@Entity
@Table(name="student")
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	private String studentId;
	private String studentName;
	private List<Studentsolution> studentsolutions;
	private List<Studenttestmap> studenttestmaps;

	public Student() {
	}


	@Id
	@Column(name="STUDENT_ID", unique=true, nullable=false, length=10)
	public String getStudentId() {
		return this.studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}


	@Column(name="STUDENT_NAME", nullable=false, length=50)
	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	//bi-directional many-to-one association to Studentsolution
	@OneToMany(mappedBy="student")
	public List<Studentsolution> getStudentsolutions() {
		return this.studentsolutions;
	}

	public void setStudentsolutions(List<Studentsolution> studentsolutions) {
		this.studentsolutions = studentsolutions;
	}

	public Studentsolution addStudentsolution(Studentsolution studentsolution) {
		getStudentsolutions().add(studentsolution);
		studentsolution.setStudent(this);

		return studentsolution;
	}

	public Studentsolution removeStudentsolution(Studentsolution studentsolution) {
		getStudentsolutions().remove(studentsolution);
		studentsolution.setStudent(null);

		return studentsolution;
	}


	//bi-directional many-to-one association to Studenttestmap
	@OneToMany(mappedBy="student")
	public List<Studenttestmap> getStudenttestmaps() {
		return this.studenttestmaps;
	}

	public void setStudenttestmaps(List<Studenttestmap> studenttestmaps) {
		this.studenttestmaps = studenttestmaps;
	}

	public Studenttestmap addStudenttestmap(Studenttestmap studenttestmap) {
		getStudenttestmaps().add(studenttestmap);
		studenttestmap.setStudent(this);

		return studenttestmap;
	}

	public Studenttestmap removeStudenttestmap(Studenttestmap studenttestmap) {
		getStudenttestmaps().remove(studenttestmap);
		studenttestmap.setStudent(null);

		return studenttestmap;
	}

}