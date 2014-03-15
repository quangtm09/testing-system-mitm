package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the studentsolution database table.
 * 
 */
@Entity
@Table(name="studentsolution")
@NamedQuery(name="Studentsolution.findAll", query="SELECT s FROM Studentsolution s")
public class Studentsolution implements Serializable {
	private static final long serialVersionUID = 1L;
	private int studentSolId;
	private String studentSolComment;
	private List<Squestionsolution> squestionsolutions;
	private Student student;
	private Tquestion tquestion;

	public Studentsolution() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="STUDENT_SOL_ID", unique=true, nullable=false)
	public int getStudentSolId() {
		return this.studentSolId;
	}

	public void setStudentSolId(int studentSolId) {
		this.studentSolId = studentSolId;
	}


	@Lob
	@Column(name="STUDENT_SOL_COMMENT")
	public String getStudentSolComment() {
		return this.studentSolComment;
	}

	public void setStudentSolComment(String studentSolComment) {
		this.studentSolComment = studentSolComment;
	}


	//bi-directional many-to-one association to Squestionsolution
	@OneToMany(mappedBy="studentsolution")
	public List<Squestionsolution> getSquestionsolutions() {
		return this.squestionsolutions;
	}

	public void setSquestionsolutions(List<Squestionsolution> squestionsolutions) {
		this.squestionsolutions = squestionsolutions;
	}

	public Squestionsolution addSquestionsolution(Squestionsolution squestionsolution) {
		getSquestionsolutions().add(squestionsolution);
		squestionsolution.setStudentsolution(this);

		return squestionsolution;
	}

	public Squestionsolution removeSquestionsolution(Squestionsolution squestionsolution) {
		getSquestionsolutions().remove(squestionsolution);
		squestionsolution.setStudentsolution(null);

		return squestionsolution;
	}


	//bi-directional many-to-one association to Student
	@ManyToOne
	@JoinColumn(name="STUDENT_ID")
	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}


	//bi-directional many-to-one association to Tquestion
	@ManyToOne
	@JoinColumn(name="TQUESTION_ID")
	public Tquestion getTquestion() {
		return this.tquestion;
	}

	public void setTquestion(Tquestion tquestion) {
		this.tquestion = tquestion;
	}

}