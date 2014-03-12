package model;// default package
// Generated Mar 12, 2014 10:45:56 PM by Hibernate Tools 3.6.0

import java.util.Date;

/**
 * Studenttestmap generated by hbm2java
 */
public class Studenttestmap implements java.io.Serializable {

	private Integer studentTestId;
	private Test test;
	private Student student;
	private Date studentTestStartdate;
	private Date studentTestStarttime;
	private Date studentTestEnddate;
	private Date studentTestEndtime;
	private Boolean studentTestVisible;

	public Studenttestmap() {
	}

	public Studenttestmap(Test test, Student student,
			Date studentTestStartdate, Date studentTestStarttime,
			Date studentTestEnddate, Date studentTestEndtime,
			Boolean studentTestVisible) {
		this.test = test;
		this.student = student;
		this.studentTestStartdate = studentTestStartdate;
		this.studentTestStarttime = studentTestStarttime;
		this.studentTestEnddate = studentTestEnddate;
		this.studentTestEndtime = studentTestEndtime;
		this.studentTestVisible = studentTestVisible;
	}

	public Integer getStudentTestId() {
		return this.studentTestId;
	}

	public void setStudentTestId(Integer studentTestId) {
		this.studentTestId = studentTestId;
	}

	public Test getTest() {
		return this.test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Date getStudentTestStartdate() {
		return this.studentTestStartdate;
	}

	public void setStudentTestStartdate(Date studentTestStartdate) {
		this.studentTestStartdate = studentTestStartdate;
	}

	public Date getStudentTestStarttime() {
		return this.studentTestStarttime;
	}

	public void setStudentTestStarttime(Date studentTestStarttime) {
		this.studentTestStarttime = studentTestStarttime;
	}

	public Date getStudentTestEnddate() {
		return this.studentTestEnddate;
	}

	public void setStudentTestEnddate(Date studentTestEnddate) {
		this.studentTestEnddate = studentTestEnddate;
	}

	public Date getStudentTestEndtime() {
		return this.studentTestEndtime;
	}

	public void setStudentTestEndtime(Date studentTestEndtime) {
		this.studentTestEndtime = studentTestEndtime;
	}

	public Boolean getStudentTestVisible() {
		return this.studentTestVisible;
	}

	public void setStudentTestVisible(Boolean studentTestVisible) {
		this.studentTestVisible = studentTestVisible;
	}

}