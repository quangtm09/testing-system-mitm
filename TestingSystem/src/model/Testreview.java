package model;

// default package
// Generated Mar 15, 2014 7:27:54 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Testreview generated by hbm2java
 */
@Entity
@Table(name = "testreview")
public class Testreview implements java.io.Serializable {

	private Integer testRevId;
	private Test test;
	private Lecturer lecturer;
	private String testRevComment;
	private Date testRevDate;
	private Boolean testRevApproved;

	public Testreview() {
	}

	public Testreview(Test test, Lecturer lecturer, String testRevComment,
			Date testRevDate, Boolean testRevApproved) {
		this.test = test;
		this.lecturer = lecturer;
		this.testRevComment = testRevComment;
		this.testRevDate = testRevDate;
		this.testRevApproved = testRevApproved;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TEST_REV_ID", unique = true, nullable = false)
	public Integer getTestRevId() {
		return this.testRevId;
	}

	public void setTestRevId(Integer testRevId) {
		this.testRevId = testRevId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEST_ID")
	public Test getTest() {
		return this.test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LECTR_ID")
	public Lecturer getLecturer() {
		return this.lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}

	@Column(name = "TEST_REV_COMMENT", length = 65535)
	public String getTestRevComment() {
		return this.testRevComment;
	}

	public void setTestRevComment(String testRevComment) {
		this.testRevComment = testRevComment;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TEST_REV_DATE", length = 10)
	public Date getTestRevDate() {
		return this.testRevDate;
	}

	public void setTestRevDate(Date testRevDate) {
		this.testRevDate = testRevDate;
	}

	@Column(name = "TEST_REV_APPROVED")
	public Boolean getTestRevApproved() {
		return this.testRevApproved;
	}

	public void setTestRevApproved(Boolean testRevApproved) {
		this.testRevApproved = testRevApproved;
	}

}
