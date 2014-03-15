package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the topic database table.
 * 
 */
@Entity
@Table(name="topic")
@NamedQuery(name="Topic.findAll", query="SELECT t FROM Topic t")
public class Topic implements Serializable {
	private static final long serialVersionUID = 1L;
	private String topicId;
	private String topicDesc;
	private String topicName;
	private List<Questiontopic> questiontopics;
	private List<Subjectandtopic> subjectandtopics;
	private Topic topic;
	private List<Topic> topics;
	private List<Tquestiontopic> tquestiontopics;

	public Topic() {
	}


	@Id
	@Column(name="TOPIC_ID", unique=true, nullable=false, length=20)
	public String getTopicId() {
		return this.topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}


	@Lob
	@Column(name="TOPIC_DESC")
	public String getTopicDesc() {
		return this.topicDesc;
	}

	public void setTopicDesc(String topicDesc) {
		this.topicDesc = topicDesc;
	}


	@Column(name="TOPIC_NAME", nullable=false, length=200)
	public String getTopicName() {
		return this.topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}


	//bi-directional many-to-one association to Questiontopic
	@OneToMany(mappedBy="topic")
	public List<Questiontopic> getQuestiontopics() {
		return this.questiontopics;
	}

	public void setQuestiontopics(List<Questiontopic> questiontopics) {
		this.questiontopics = questiontopics;
	}

	public Questiontopic addQuestiontopic(Questiontopic questiontopic) {
		getQuestiontopics().add(questiontopic);
		questiontopic.setTopic(this);

		return questiontopic;
	}

	public Questiontopic removeQuestiontopic(Questiontopic questiontopic) {
		getQuestiontopics().remove(questiontopic);
		questiontopic.setTopic(null);

		return questiontopic;
	}


	//bi-directional many-to-one association to Subjectandtopic
	@OneToMany(mappedBy="topic")
	public List<Subjectandtopic> getSubjectandtopics() {
		return this.subjectandtopics;
	}

	public void setSubjectandtopics(List<Subjectandtopic> subjectandtopics) {
		this.subjectandtopics = subjectandtopics;
	}

	public Subjectandtopic addSubjectandtopic(Subjectandtopic subjectandtopic) {
		getSubjectandtopics().add(subjectandtopic);
		subjectandtopic.setTopic(this);

		return subjectandtopic;
	}

	public Subjectandtopic removeSubjectandtopic(Subjectandtopic subjectandtopic) {
		getSubjectandtopics().remove(subjectandtopic);
		subjectandtopic.setTopic(null);

		return subjectandtopic;
	}


	//bi-directional many-to-one association to Topic
	@ManyToOne
	@JoinColumn(name="TOPIC_PARENT_ID")
	public Topic getTopic() {
		return this.topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}


	//bi-directional many-to-one association to Topic
	@OneToMany(mappedBy="topic")
	public List<Topic> getTopics() {
		return this.topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public Topic addTopic(Topic topic) {
		getTopics().add(topic);
		topic.setTopic(this);

		return topic;
	}

	public Topic removeTopic(Topic topic) {
		getTopics().remove(topic);
		topic.setTopic(null);

		return topic;
	}


	//bi-directional many-to-one association to Tquestiontopic
	@OneToMany(mappedBy="topic")
	public List<Tquestiontopic> getTquestiontopics() {
		return this.tquestiontopics;
	}

	public void setTquestiontopics(List<Tquestiontopic> tquestiontopics) {
		this.tquestiontopics = tquestiontopics;
	}

	public Tquestiontopic addTquestiontopic(Tquestiontopic tquestiontopic) {
		getTquestiontopics().add(tquestiontopic);
		tquestiontopic.setTopic(this);

		return tquestiontopic;
	}

	public Tquestiontopic removeTquestiontopic(Tquestiontopic tquestiontopic) {
		getTquestiontopics().remove(tquestiontopic);
		tquestiontopic.setTopic(null);

		return tquestiontopic;
	}

}