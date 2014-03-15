package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the questionsolution database table.
 * 
 */
@Entity
@Table(name="questionsolution")
@NamedQuery(name="Questionsolution.findAll", query="SELECT q FROM Questionsolution q")
public class Questionsolution implements Serializable {
	private static final long serialVersionUID = 1L;
	private int questSolId;
	private byte questSolOrder;
	private Question question;
	private Questionitem questionitem1;
	private Questionitem questionitem2;
	private Questionitem questionitem3;

	public Questionsolution() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="QUEST_SOL_ID", unique=true, nullable=false)
	public int getQuestSolId() {
		return this.questSolId;
	}

	public void setQuestSolId(int questSolId) {
		this.questSolId = questSolId;
	}


	@Column(name="QUEST_SOL_ORDER")
	public byte getQuestSolOrder() {
		return this.questSolOrder;
	}

	public void setQuestSolOrder(byte questSolOrder) {
		this.questSolOrder = questSolOrder;
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


	//bi-directional many-to-one association to Questionitem
	@ManyToOne
	@JoinColumn(name="QUEST_ITEM_ID")
	public Questionitem getQuestionitem1() {
		return this.questionitem1;
	}

	public void setQuestionitem1(Questionitem questionitem1) {
		this.questionitem1 = questionitem1;
	}


	//bi-directional many-to-one association to Questionitem
	@ManyToOne
	@JoinColumn(name="NEXT_QUEST_ITEM_ID")
	public Questionitem getQuestionitem2() {
		return this.questionitem2;
	}

	public void setQuestionitem2(Questionitem questionitem2) {
		this.questionitem2 = questionitem2;
	}


	//bi-directional many-to-one association to Questionitem
	@ManyToOne
	@JoinColumn(name="PREVIOUS_QUEST_ITEM_ID")
	public Questionitem getQuestionitem3() {
		return this.questionitem3;
	}

	public void setQuestionitem3(Questionitem questionitem3) {
		this.questionitem3 = questionitem3;
	}

}