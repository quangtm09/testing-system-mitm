package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the questionfigure database table.
 * 
 */
@Entity
@Table(name="questionfigure")
@NamedQuery(name="Questionfigure.findAll", query="SELECT q FROM Questionfigure q")
public class Questionfigure implements Serializable {
	private static final long serialVersionUID = 1L;
	private int questFigId;
	private String questFigDesc;
	private Figurelink figurelink;
	private Question question;

	public Questionfigure() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="QUEST_FIG_ID", unique=true, nullable=false)
	public int getQuestFigId() {
		return this.questFigId;
	}

	public void setQuestFigId(int questFigId) {
		this.questFigId = questFigId;
	}


	@Lob
	@Column(name="QUEST_FIG_DESC")
	public String getQuestFigDesc() {
		return this.questFigDesc;
	}

	public void setQuestFigDesc(String questFigDesc) {
		this.questFigDesc = questFigDesc;
	}


	//bi-directional many-to-one association to Figurelink
	@ManyToOne
	@JoinColumn(name="FIGURE_ID")
	public Figurelink getFigurelink() {
		return this.figurelink;
	}

	public void setFigurelink(Figurelink figurelink) {
		this.figurelink = figurelink;
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

}