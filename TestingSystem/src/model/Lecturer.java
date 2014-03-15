package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the lecturer database table.
 * 
 */
@Entity
@Table(name="lecturer")
@NamedQuery(name="Lecturer.findAll", query="SELECT l FROM Lecturer l")
public class Lecturer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String lectrId;
	private String lectrEmail;
	private String lectrFone;
	private String lectrName;
	private List<Questionowner> questionowners;
	private List<Sharedquestion> sharedquestions1;
	private List<Sharedquestion> sharedquestions2;
	private List<Subjectassignment> subjectassignments;
	private List<Test> tests;
	private List<Testreview> testreviews;
	private List<Tquestionowner> tquestionowners;

	public Lecturer() {
	}


	@Id
	@Column(name="LECTR_ID", unique=true, nullable=false, length=10)
	public String getLectrId() {
		return this.lectrId;
	}

	public void setLectrId(String lectrId) {
		this.lectrId = lectrId;
	}


	@Column(name="LECTR_EMAIL", length=100)
	public String getLectrEmail() {
		return this.lectrEmail;
	}

	public void setLectrEmail(String lectrEmail) {
		this.lectrEmail = lectrEmail;
	}


	@Column(name="LECTR_FONE", length=12)
	public String getLectrFone() {
		return this.lectrFone;
	}

	public void setLectrFone(String lectrFone) {
		this.lectrFone = lectrFone;
	}


	@Column(name="LECTR_NAME", nullable=false, length=100)
	public String getLectrName() {
		return this.lectrName;
	}

	public void setLectrName(String lectrName) {
		this.lectrName = lectrName;
	}


	//bi-directional many-to-one association to Questionowner
	@OneToMany(mappedBy="lecturer")
	public List<Questionowner> getQuestionowners() {
		return this.questionowners;
	}

	public void setQuestionowners(List<Questionowner> questionowners) {
		this.questionowners = questionowners;
	}

	public Questionowner addQuestionowner(Questionowner questionowner) {
		getQuestionowners().add(questionowner);
		questionowner.setLecturer(this);

		return questionowner;
	}

	public Questionowner removeQuestionowner(Questionowner questionowner) {
		getQuestionowners().remove(questionowner);
		questionowner.setLecturer(null);

		return questionowner;
	}


	//bi-directional many-to-one association to Sharedquestion
	@OneToMany(mappedBy="lecturer1")
	public List<Sharedquestion> getSharedquestions1() {
		return this.sharedquestions1;
	}

	public void setSharedquestions1(List<Sharedquestion> sharedquestions1) {
		this.sharedquestions1 = sharedquestions1;
	}

	public Sharedquestion addSharedquestions1(Sharedquestion sharedquestions1) {
		getSharedquestions1().add(sharedquestions1);
		sharedquestions1.setLecturer1(this);

		return sharedquestions1;
	}

	public Sharedquestion removeSharedquestions1(Sharedquestion sharedquestions1) {
		getSharedquestions1().remove(sharedquestions1);
		sharedquestions1.setLecturer1(null);

		return sharedquestions1;
	}


	//bi-directional many-to-one association to Sharedquestion
	@OneToMany(mappedBy="lecturer2")
	public List<Sharedquestion> getSharedquestions2() {
		return this.sharedquestions2;
	}

	public void setSharedquestions2(List<Sharedquestion> sharedquestions2) {
		this.sharedquestions2 = sharedquestions2;
	}

	public Sharedquestion addSharedquestions2(Sharedquestion sharedquestions2) {
		getSharedquestions2().add(sharedquestions2);
		sharedquestions2.setLecturer2(this);

		return sharedquestions2;
	}

	public Sharedquestion removeSharedquestions2(Sharedquestion sharedquestions2) {
		getSharedquestions2().remove(sharedquestions2);
		sharedquestions2.setLecturer2(null);

		return sharedquestions2;
	}


	//bi-directional many-to-one association to Subjectassignment
	@OneToMany(mappedBy="lecturer")
	public List<Subjectassignment> getSubjectassignments() {
		return this.subjectassignments;
	}

	public void setSubjectassignments(List<Subjectassignment> subjectassignments) {
		this.subjectassignments = subjectassignments;
	}

	public Subjectassignment addSubjectassignment(Subjectassignment subjectassignment) {
		getSubjectassignments().add(subjectassignment);
		subjectassignment.setLecturer(this);

		return subjectassignment;
	}

	public Subjectassignment removeSubjectassignment(Subjectassignment subjectassignment) {
		getSubjectassignments().remove(subjectassignment);
		subjectassignment.setLecturer(null);

		return subjectassignment;
	}


	//bi-directional many-to-one association to Test
	@OneToMany(mappedBy="lecturer")
	public List<Test> getTests() {
		return this.tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}

	public Test addTest(Test test) {
		getTests().add(test);
		test.setLecturer(this);

		return test;
	}

	public Test removeTest(Test test) {
		getTests().remove(test);
		test.setLecturer(null);

		return test;
	}


	//bi-directional many-to-one association to Testreview
	@OneToMany(mappedBy="lecturer")
	public List<Testreview> getTestreviews() {
		return this.testreviews;
	}

	public void setTestreviews(List<Testreview> testreviews) {
		this.testreviews = testreviews;
	}

	public Testreview addTestreview(Testreview testreview) {
		getTestreviews().add(testreview);
		testreview.setLecturer(this);

		return testreview;
	}

	public Testreview removeTestreview(Testreview testreview) {
		getTestreviews().remove(testreview);
		testreview.setLecturer(null);

		return testreview;
	}


	//bi-directional many-to-one association to Tquestionowner
	@OneToMany(mappedBy="lecturer")
	public List<Tquestionowner> getTquestionowners() {
		return this.tquestionowners;
	}

	public void setTquestionowners(List<Tquestionowner> tquestionowners) {
		this.tquestionowners = tquestionowners;
	}

	public Tquestionowner addTquestionowner(Tquestionowner tquestionowner) {
		getTquestionowners().add(tquestionowner);
		tquestionowner.setLecturer(this);

		return tquestionowner;
	}

	public Tquestionowner removeTquestionowner(Tquestionowner tquestionowner) {
		getTquestionowners().remove(tquestionowner);
		tquestionowner.setLecturer(null);

		return tquestionowner;
	}

}