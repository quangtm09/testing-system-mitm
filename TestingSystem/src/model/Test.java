package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the test database table.
 * 
 */
@Entity
@Table(name="test")
@NamedQuery(name="Test.findAll", query="SELECT t FROM Test t")
public class Test implements Serializable {
	private static final long serialVersionUID = 1L;
	private int testId;
	private Date testCreationDate;
	private String testDesc;
	private int testDuration;
	private Date testStartTime;
	private List<Studenttestmap> studenttestmaps;
	private Lecturer lecturer;
	private List<Testreview> testreviews;
	private List<Testsection> testsections;

	public Test() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TEST_ID", unique=true, nullable=false)
	public int getTestId() {
		return this.testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="TEST_CREATION_DATE")
	public Date getTestCreationDate() {
		return this.testCreationDate;
	}

	public void setTestCreationDate(Date testCreationDate) {
		this.testCreationDate = testCreationDate;
	}


	@Lob
	@Column(name="TEST_DESC")
	public String getTestDesc() {
		return this.testDesc;
	}

	public void setTestDesc(String testDesc) {
		this.testDesc = testDesc;
	}


	@Column(name="TEST_DURATION")
	public int getTestDuration() {
		return this.testDuration;
	}

	public void setTestDuration(int testDuration) {
		this.testDuration = testDuration;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TEST_START_TIME")
	public Date getTestStartTime() {
		return this.testStartTime;
	}

	public void setTestStartTime(Date testStartTime) {
		this.testStartTime = testStartTime;
	}


	//bi-directional many-to-one association to Studenttestmap
	@OneToMany(mappedBy="test")
	public List<Studenttestmap> getStudenttestmaps() {
		return this.studenttestmaps;
	}

	public void setStudenttestmaps(List<Studenttestmap> studenttestmaps) {
		this.studenttestmaps = studenttestmaps;
	}

	public Studenttestmap addStudenttestmap(Studenttestmap studenttestmap) {
		getStudenttestmaps().add(studenttestmap);
		studenttestmap.setTest(this);

		return studenttestmap;
	}

	public Studenttestmap removeStudenttestmap(Studenttestmap studenttestmap) {
		getStudenttestmaps().remove(studenttestmap);
		studenttestmap.setTest(null);

		return studenttestmap;
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


	//bi-directional many-to-one association to Testreview
	@OneToMany(mappedBy="test")
	public List<Testreview> getTestreviews() {
		return this.testreviews;
	}

	public void setTestreviews(List<Testreview> testreviews) {
		this.testreviews = testreviews;
	}

	public Testreview addTestreview(Testreview testreview) {
		getTestreviews().add(testreview);
		testreview.setTest(this);

		return testreview;
	}

	public Testreview removeTestreview(Testreview testreview) {
		getTestreviews().remove(testreview);
		testreview.setTest(null);

		return testreview;
	}


	//bi-directional many-to-one association to Testsection
	@OneToMany(mappedBy="test")
	public List<Testsection> getTestsections() {
		return this.testsections;
	}

	public void setTestsections(List<Testsection> testsections) {
		this.testsections = testsections;
	}

	public Testsection addTestsection(Testsection testsection) {
		getTestsections().add(testsection);
		testsection.setTest(this);

		return testsection;
	}

	public Testsection removeTestsection(Testsection testsection) {
		getTestsections().remove(testsection);
		testsection.setTest(null);

		return testsection;
	}

}