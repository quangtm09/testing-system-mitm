package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the testsectionitem database table.
 * 
 */
@Entity
@Table(name="testsectionitem")
@NamedQuery(name="Testsectionitem.findAll", query="SELECT t FROM Testsectionitem t")
public class Testsectionitem implements Serializable {
	private static final long serialVersionUID = 1L;
	private int testSecItemId;
	private float testSecItemScore;
	private Question question;
	private Testsection testsection;

	public Testsectionitem() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TEST_SEC_ITEM_ID", unique=true, nullable=false)
	public int getTestSecItemId() {
		return this.testSecItemId;
	}

	public void setTestSecItemId(int testSecItemId) {
		this.testSecItemId = testSecItemId;
	}


	@Column(name="TEST_SEC_ITEM_SCORE")
	public float getTestSecItemScore() {
		return this.testSecItemScore;
	}

	public void setTestSecItemScore(float testSecItemScore) {
		this.testSecItemScore = testSecItemScore;
	}


	//bi-directional many-to-one association to Question
	@ManyToOne
	@JoinColumn(name="QUESTION_ID")
	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}


	//bi-directional many-to-one association to Testsection
	@ManyToOne
	@JoinColumn(name="TEST_SEC_ID")
	public Testsection getTestsection() {
		return this.testsection;
	}

	public void setTestsection(Testsection testsection) {
		this.testsection = testsection;
	}

}