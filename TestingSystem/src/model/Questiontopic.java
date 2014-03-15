package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the questiontopic database table.
 * 
 */
@Entity
@Table(name="questiontopic")
@NamedQuery(name="Questiontopic.findAll", query="SELECT q FROM Questiontopic q")
public class Questiontopic implements Serializable {
	private static final long serialVersionUID = 1L;
	private int questTopicId;
	private String questTopicLevel;
	private Question question;
	private Topic topic;

	public Questiontopic() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="QUEST_TOPIC_ID", unique=true, nullable=false)
	public int getQuestTopicId() {
		return this.questTopicId;
	}

	public void setQuestTopicId(int questTopicId) {
		this.questTopicId = questTopicId;
	}


	@Column(name="QUEST_TOPIC_LEVEL", length=1)
	public String getQuestTopicLevel() {
		return this.questTopicLevel;
	}

	public void setQuestTopicLevel(String questTopicLevel) {
		this.questTopicLevel = questTopicLevel;
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


	//bi-directional many-to-one association to Topic
	@ManyToOne
	@JoinColumn(name="TOPIC_ID")
	public Topic getTopic() {
		return this.topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

}