package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the testreview database table.
 * 
 */
@Entity
@Table(name="testreview")
@NamedQuery(name="Testreview.findAll", query="SELECT t FROM Testreview t")
public class Testreview implements Serializable {
	private static final long serialVersionUID = 1L;
	private int testRevId;
	private byte testRevApproved;
	private String testRevComment;
	private Date testRevDate;
	private Lecturer lecturer;
	private Test test;

	public Testreview() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TEST_REV_ID", unique=true, nullable=false)
	public int getTestRevId() {
		return this.testRevId;
	}

	public void setTestRevId(int testRevId) {
		this.testRevId = testRevId;
	}


	@Column(name="TEST_REV_APPROVED")
	public byte getTestRevApproved() {
		return this.testRevApproved;
	}

	public void setTestRevApproved(byte testRevApproved) {
		this.testRevApproved = testRevApproved;
	}


	@Lob
	@Column(name="TEST_REV_COMMENT")
	public String getTestRevComment() {
		return this.testRevComment;
	}

	public void setTestRevComment(String testRevComment) {
		this.testRevComment = testRevComment;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="TEST_REV_DATE")
	public Date getTestRevDate() {
		return this.testRevDate;
	}

	public void setTestRevDate(Date testRevDate) {
		this.testRevDate = testRevDate;
	}


	//bi-directional many-to-one association to Lecturer
	@ManyToOne
	@JoinColumn(name="LECTR_ID")
	public Lecturer getLecturer() {
		return this.lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
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