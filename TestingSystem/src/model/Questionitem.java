package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the questionitem database table.
 * 
 */
@Entity
@Table(name="questionitem")
@NamedQuery(name="Questionitem.findAll", query="SELECT q FROM Questionitem q")
public class Questionitem implements Serializable {
	private static final long serialVersionUID = 1L;
	private int questItemId;
	private String questItemFace;
	private String questItemValue;
	private Question question;
	private List<Questionitemfigure> questionitemfigures;
	private List<Questioniteminfo> questioniteminfos;
	private List<Questionsolution> questionsolutions1;
	private List<Questionsolution> questionsolutions2;
	private List<Questionsolution> questionsolutions3;

	public Questionitem() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="QUEST_ITEM_ID", unique=true, nullable=false)
	public int getQuestItemId() {
		return this.questItemId;
	}

	public void setQuestItemId(int questItemId) {
		this.questItemId = questItemId;
	}


	@Lob
	@Column(name="QUEST_ITEM_FACE")
	public String getQuestItemFace() {
		return this.questItemFace;
	}

	public void setQuestItemFace(String questItemFace) {
		this.questItemFace = questItemFace;
	}


	@Lob
	@Column(name="QUEST_ITEM_VALUE")
	public String getQuestItemValue() {
		return this.questItemValue;
	}

	public void setQuestItemValue(String questItemValue) {
		this.questItemValue = questItemValue;
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


	//bi-directional many-to-one association to Questionitemfigure
	@OneToMany(mappedBy="questionitem")
	public List<Questionitemfigure> getQuestionitemfigures() {
		return this.questionitemfigures;
	}

	public void setQuestionitemfigures(List<Questionitemfigure> questionitemfigures) {
		this.questionitemfigures = questionitemfigures;
	}

	public Questionitemfigure addQuestionitemfigure(Questionitemfigure questionitemfigure) {
		getQuestionitemfigures().add(questionitemfigure);
		questionitemfigure.setQuestionitem(this);

		return questionitemfigure;
	}

	public Questionitemfigure removeQuestionitemfigure(Questionitemfigure questionitemfigure) {
		getQuestionitemfigures().remove(questionitemfigure);
		questionitemfigure.setQuestionitem(null);

		return questionitemfigure;
	}


	//bi-directional many-to-one association to Questioniteminfo
	@OneToMany(mappedBy="questionitem")
	public List<Questioniteminfo> getQuestioniteminfos() {
		return this.questioniteminfos;
	}

	public void setQuestioniteminfos(List<Questioniteminfo> questioniteminfos) {
		this.questioniteminfos = questioniteminfos;
	}

	public Questioniteminfo addQuestioniteminfo(Questioniteminfo questioniteminfo) {
		getQuestioniteminfos().add(questioniteminfo);
		questioniteminfo.setQuestionitem(this);

		return questioniteminfo;
	}

	public Questioniteminfo removeQuestioniteminfo(Questioniteminfo questioniteminfo) {
		getQuestioniteminfos().remove(questioniteminfo);
		questioniteminfo.setQuestionitem(null);

		return questioniteminfo;
	}


	//bi-directional many-to-one association to Questionsolution
	@OneToMany(mappedBy="questionitem1")
	public List<Questionsolution> getQuestionsolutions1() {
		return this.questionsolutions1;
	}

	public void setQuestionsolutions1(List<Questionsolution> questionsolutions1) {
		this.questionsolutions1 = questionsolutions1;
	}

	public Questionsolution addQuestionsolutions1(Questionsolution questionsolutions1) {
		getQuestionsolutions1().add(questionsolutions1);
		questionsolutions1.setQuestionitem1(this);

		return questionsolutions1;
	}

	public Questionsolution removeQuestionsolutions1(Questionsolution questionsolutions1) {
		getQuestionsolutions1().remove(questionsolutions1);
		questionsolutions1.setQuestionitem1(null);

		return questionsolutions1;
	}


	//bi-directional many-to-one association to Questionsolution
	@OneToMany(mappedBy="questionitem2")
	public List<Questionsolution> getQuestionsolutions2() {
		return this.questionsolutions2;
	}

	public void setQuestionsolutions2(List<Questionsolution> questionsolutions2) {
		this.questionsolutions2 = questionsolutions2;
	}

	public Questionsolution addQuestionsolutions2(Questionsolution questionsolutions2) {
		getQuestionsolutions2().add(questionsolutions2);
		questionsolutions2.setQuestionitem2(this);

		return questionsolutions2;
	}

	public Questionsolution removeQuestionsolutions2(Questionsolution questionsolutions2) {
		getQuestionsolutions2().remove(questionsolutions2);
		questionsolutions2.setQuestionitem2(null);

		return questionsolutions2;
	}


	//bi-directional many-to-one association to Questionsolution
	@OneToMany(mappedBy="questionitem3")
	public List<Questionsolution> getQuestionsolutions3() {
		return this.questionsolutions3;
	}

	public void setQuestionsolutions3(List<Questionsolution> questionsolutions3) {
		this.questionsolutions3 = questionsolutions3;
	}

	public Questionsolution addQuestionsolutions3(Questionsolution questionsolutions3) {
		getQuestionsolutions3().add(questionsolutions3);
		questionsolutions3.setQuestionitem3(this);

		return questionsolutions3;
	}

	public Questionsolution removeQuestionsolutions3(Questionsolution questionsolutions3) {
		getQuestionsolutions3().remove(questionsolutions3);
		questionsolutions3.setQuestionitem3(null);

		return questionsolutions3;
	}

}