package model;

// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Questiontopic generated by hbm2java
 */
@Entity
@Table(name = "questiontopic")
public class Questiontopic implements java.io.Serializable {

	private Integer questTopicId;
	private Question question;
	private Topic topic;
	private Character questTopicLevel;

	public Questiontopic() {
	}

	public Questiontopic(Question question, Topic topic,
			Character questTopicLevel) {
		this.question = question;
		this.topic = topic;
		this.questTopicLevel = questTopicLevel;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "QUEST_TOPIC_ID", unique = true, nullable = false)
	public Integer getQuestTopicId() {
		return this.questTopicId;
	}

	public void setQuestTopicId(Integer questTopicId) {
		this.questTopicId = questTopicId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUESTION_ID")
	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TOPIC_ID")
	public Topic getTopic() {
		return this.topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	@Column(name = "QUEST_TOPIC_LEVEL", length = 1)
	public Character getQuestTopicLevel() {
		return this.questTopicLevel;
	}

	public void setQuestTopicLevel(Character questTopicLevel) {
		this.questTopicLevel = questTopicLevel;
	}

}
