package model;// default package
// Generated Mar 12, 2014 10:45:56 PM by Hibernate Tools 3.6.0

/**
 * Testsectionitem generated by hbm2java
 */
public class Testsectionitem implements java.io.Serializable {

	private Integer testSecItemId;
	private Question question;
	private Testsection testsection;
	private Float testSecItemScore;

	public Testsectionitem() {
	}

	public Testsectionitem(Question question, Testsection testsection,
			Float testSecItemScore) {
		this.question = question;
		this.testsection = testsection;
		this.testSecItemScore = testSecItemScore;
	}

	public Integer getTestSecItemId() {
		return this.testSecItemId;
	}

	public void setTestSecItemId(Integer testSecItemId) {
		this.testSecItemId = testSecItemId;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Testsection getTestsection() {
		return this.testsection;
	}

	public void setTestsection(Testsection testsection) {
		this.testsection = testsection;
	}

	public Float getTestSecItemScore() {
		return this.testSecItemScore;
	}

	public void setTestSecItemScore(Float testSecItemScore) {
		this.testSecItemScore = testSecItemScore;
	}

}