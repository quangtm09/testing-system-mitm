package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sharedquestion database table.
 * 
 */
@Entity
@Table(name="sharedquestion")
@NamedQuery(name="Sharedquestion.findAll", query="SELECT s FROM Sharedquestion s")
public class Sharedquestion implements Serializable {
	private static final long serialVersionUID = 1L;
	private int sharedQuestId;
	private byte sharedQuestModifiable;
	private byte sharedQuestResharable;
	private byte sharedQuestViewable;
	private Lecturer lecturer1;
	private Lecturer lecturer2;
	private Question question;

	public Sharedquestion() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SHARED_QUEST_ID", unique=true, nullable=false)
	public int getSharedQuestId() {
		return this.sharedQuestId;
	}

	public void setSharedQuestId(int sharedQuestId) {
		this.sharedQuestId = sharedQuestId;
	}


	@Column(name="SHARED_QUEST_MODIFIABLE", nullable=false)
	public byte getSharedQuestModifiable() {
		return this.sharedQuestModifiable;
	}

	public void setSharedQuestModifiable(byte sharedQuestModifiable) {
		this.sharedQuestModifiable = sharedQuestModifiable;
	}


	@Column(name="SHARED_QUEST_RESHARABLE", nullable=false)
	public byte getSharedQuestResharable() {
		return this.sharedQuestResharable;
	}

	public void setSharedQuestResharable(byte sharedQuestResharable) {
		this.sharedQuestResharable = sharedQuestResharable;
	}


	@Column(name="SHARED_QUEST_VIEWABLE", nullable=false)
	public byte getSharedQuestViewable() {
		return this.sharedQuestViewable;
	}

	public void setSharedQuestViewable(byte sharedQuestViewable) {
		this.sharedQuestViewable = sharedQuestViewable;
	}


	//bi-directional many-to-one association to Lecturer
	@ManyToOne
	@JoinColumn(name="SHAREE_ID")
	public Lecturer getLecturer1() {
		return this.lecturer1;
	}

	public void setLecturer1(Lecturer lecturer1) {
		this.lecturer1 = lecturer1;
	}


	//bi-directional many-to-one association to Lecturer
	@ManyToOne
	@JoinColumn(name="SHARER_ID")
	public Lecturer getLecturer2() {
		return this.lecturer2;
	}

	public void setLecturer2(Lecturer lecturer2) {
		this.lecturer2 = lecturer2;
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