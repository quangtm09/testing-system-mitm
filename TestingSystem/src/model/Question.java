package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the question database table.
 * 
 */
@Entity
@Table(name="question")
@NamedQuery(name="Question.findAll", query="SELECT q FROM Question q")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;
	private int questionId;
	private String questionDesc;
	private String questionLevel;
	private String questionType;
	private List<Questionfigure> questionfigures;
	private List<Questioninfo> questioninfos;
	private List<Questionitem> questionitems;
	private List<Questionowner> questionowners;
	private List<Questionsolution> questionsolutions;
	private List<Questiontopic> questiontopics;
	private List<Sharedquestion> sharedquestions;
	private List<Testsectionitem> testsectionitems;

	public Question() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="QUESTION_ID", unique=true, nullable=false)
	public int getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}


	@Lob
	@Column(name="QUESTION_DESC")
	public String getQuestionDesc() {
		return this.questionDesc;
	}

	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}


	@Column(name="QUESTION_LEVEL", nullable=false, length=1)
	public String getQuestionLevel() {
		return this.questionLevel;
	}

	public void setQuestionLevel(String questionLevel) {
		this.questionLevel = questionLevel;
	}


	@Column(name="QUESTION_TYPE", nullable=false, length=15)
	public String getQuestionType() {
		return this.questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}


	//bi-directional many-to-one association to Questionfigure
	@OneToMany(mappedBy="question")
	public List<Questionfigure> getQuestionfigures() {
		return this.questionfigures;
	}

	public void setQuestionfigures(List<Questionfigure> questionfigures) {
		this.questionfigures = questionfigures;
	}

	public Questionfigure addQuestionfigure(Questionfigure questionfigure) {
		getQuestionfigures().add(questionfigure);
		questionfigure.setQuestion(this);

		return questionfigure;
	}

	public Questionfigure removeQuestionfigure(Questionfigure questionfigure) {
		getQuestionfigures().remove(questionfigure);
		questionfigure.setQuestion(null);

		return questionfigure;
	}


	//bi-directional many-to-one association to Questioninfo
	@OneToMany(mappedBy="question")
	public List<Questioninfo> getQuestioninfos() {
		return this.questioninfos;
	}

	public void setQuestioninfos(List<Questioninfo> questioninfos) {
		this.questioninfos = questioninfos;
	}

	public Questioninfo addQuestioninfo(Questioninfo questioninfo) {
		getQuestioninfos().add(questioninfo);
		questioninfo.setQuestion(this);

		return questioninfo;
	}

	public Questioninfo removeQuestioninfo(Questioninfo questioninfo) {
		getQuestioninfos().remove(questioninfo);
		questioninfo.setQuestion(null);

		return questioninfo;
	}


	//bi-directional many-to-one association to Questionitem
	@OneToMany(mappedBy="question")
	public List<Questionitem> getQuestionitems() {
		return this.questionitems;
	}

	public void setQuestionitems(List<Questionitem> questionitems) {
		this.questionitems = questionitems;
	}

	public Questionitem addQuestionitem(Questionitem questionitem) {
		getQuestionitems().add(questionitem);
		questionitem.setQuestion(this);

		return questionitem;
	}

	public Questionitem removeQuestionitem(Questionitem questionitem) {
		getQuestionitems().remove(questionitem);
		questionitem.setQuestion(null);

		return questionitem;
	}


	//bi-directional many-to-one association to Questionowner
	@OneToMany(mappedBy="question")
	public List<Questionowner> getQuestionowners() {
		return this.questionowners;
	}

	public void setQuestionowners(List<Questionowner> questionowners) {
		this.questionowners = questionowners;
	}

	public Questionowner addQuestionowner(Questionowner questionowner) {
		getQuestionowners().add(questionowner);
		questionowner.setQuestion(this);

		return questionowner;
	}

	public Questionowner removeQuestionowner(Questionowner questionowner) {
		getQuestionowners().remove(questionowner);
		questionowner.setQuestion(null);

		return questionowner;
	}


	//bi-directional many-to-one association to Questionsolution
	@OneToMany(mappedBy="question")
	public List<Questionsolution> getQuestionsolutions() {
		return this.questionsolutions;
	}

	public void setQuestionsolutions(List<Questionsolution> questionsolutions) {
		this.questionsolutions = questionsolutions;
	}

	public Questionsolution addQuestionsolution(Questionsolution questionsolution) {
		getQuestionsolutions().add(questionsolution);
		questionsolution.setQuestion(this);

		return questionsolution;
	}

	public Questionsolution removeQuestionsolution(Questionsolution questionsolution) {
		getQuestionsolutions().remove(questionsolution);
		questionsolution.setQuestion(null);

		return questionsolution;
	}


	//bi-directional many-to-one association to Questiontopic
	@OneToMany(mappedBy="question")
	public List<Questiontopic> getQuestiontopics() {
		return this.questiontopics;
	}

	public void setQuestiontopics(List<Questiontopic> questiontopics) {
		this.questiontopics = questiontopics;
	}

	public Questiontopic addQuestiontopic(Questiontopic questiontopic) {
		getQuestiontopics().add(questiontopic);
		questiontopic.setQuestion(this);

		return questiontopic;
	}

	public Questiontopic removeQuestiontopic(Questiontopic questiontopic) {
		getQuestiontopics().remove(questiontopic);
		questiontopic.setQuestion(null);

		return questiontopic;
	}


	//bi-directional many-to-one association to Sharedquestion
	@OneToMany(mappedBy="question")
	public List<Sharedquestion> getSharedquestions() {
		return this.sharedquestions;
	}

	public void setSharedquestions(List<Sharedquestion> sharedquestions) {
		this.sharedquestions = sharedquestions;
	}

	public Sharedquestion addSharedquestion(Sharedquestion sharedquestion) {
		getSharedquestions().add(sharedquestion);
		sharedquestion.setQuestion(this);

		return sharedquestion;
	}

	public Sharedquestion removeSharedquestion(Sharedquestion sharedquestion) {
		getSharedquestions().remove(sharedquestion);
		sharedquestion.setQuestion(null);

		return sharedquestion;
	}


	//bi-directional many-to-one association to Testsectionitem
	@OneToMany(mappedBy="question")
	public List<Testsectionitem> getTestsectionitems() {
		return this.testsectionitems;
	}

	public void setTestsectionitems(List<Testsectionitem> testsectionitems) {
		this.testsectionitems = testsectionitems;
	}

	public Testsectionitem addTestsectionitem(Testsectionitem testsectionitem) {
		getTestsectionitems().add(testsectionitem);
		testsectionitem.setQuestion(this);

		return testsectionitem;
	}

	public Testsectionitem removeTestsectionitem(Testsectionitem testsectionitem) {
		getTestsectionitems().remove(testsectionitem);
		testsectionitem.setQuestion(null);

		return testsectionitem;
	}

}