package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the studenttestmap database table.
 * 
 */
@Entity
@Table(name="studenttestmap")
@NamedQuery(name="Studenttestmap.findAll", query="SELECT s FROM Studenttestmap s")
public class Studenttestmap implements Serializable {
	private static final long serialVersionUID = 1L;
	private int studentTestId;
	private Date studentTestEnddate;
	private Time studentTestEndtime;
	private Date studentTestStartdate;
	private Time studentTestStarttime;
	private byte studentTestVisible;
	private Student student;
	private Test test;

	public Studenttestmap() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="STUDENT_TEST_ID", unique=true, nullable=false)
	public int getStudentTestId() {
		return this.studentTestId;
	}

	public void setStudentTestId(int studentTestId) {
		this.studentTestId = studentTestId;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="STUDENT_TEST_ENDDATE")
	public Date getStudentTestEnddate() {
		return this.studentTestEnddate;
	}

	public void setStudentTestEnddate(Date studentTestEnddate) {
		this.studentTestEnddate = studentTestEnddate;
	}


	@Column(name="STUDENT_TEST_ENDTIME")
	public Time getStudentTestEndtime() {
		return this.studentTestEndtime;
	}

	public void setStudentTestEndtime(Time studentTestEndtime) {
		this.studentTestEndtime = studentTestEndtime;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="STUDENT_TEST_STARTDATE")
	public Date getStudentTestStartdate() {
		return this.studentTestStartdate;
	}

	public void setStudentTestStartdate(Date studentTestStartdate) {
		this.studentTestStartdate = studentTestStartdate;
	}


	@Column(name="STUDENT_TEST_STARTTIME")
	public Time getStudentTestStarttime() {
		return this.studentTestStarttime;
	}

	public void setStudentTestStarttime(Time studentTestStarttime) {
		this.studentTestStarttime = studentTestStarttime;
	}


	@Column(name="STUDENT_TEST_VISIBLE")
	public byte getStudentTestVisible() {
		return this.studentTestVisible;
	}

	public void setStudentTestVisible(byte studentTestVisible) {
		this.studentTestVisible = studentTestVisible;
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


	//bi-directional many-to-one association to Test
	@ManyToOne
	@JoinColumn(name="TEST_ID")
	public Test getTest() {
		return this.test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

}