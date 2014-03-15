package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the questionowner database table.
 * 
 */
@Entity
@Table(name="questionowner")
@NamedQuery(name="Questionowner.findAll", query="SELECT q FROM Questionowner q")
public class Questionowner implements Serializable {
	private static final long serialVersionUID = 1L;
	private int questOwnerId;
	private Lecturer lecturer;
	private Question question;

	public Questionowner() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="QUEST_OWNER_ID", unique=true, nullable=false)
	public int getQuestOwnerId() {
		return this.questOwnerId;
	}

	public void setQuestOwnerId(int questOwnerId) {
		this.questOwnerId = questOwnerId;
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