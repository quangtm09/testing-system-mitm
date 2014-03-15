package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tquestiontopic database table.
 * 
 */
@Entity
@Table(name="tquestiontopic")
@NamedQuery(name="Tquestiontopic.findAll", query="SELECT t FROM Tquestiontopic t")
public class Tquestiontopic implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tquestTopicId;
	private String tquestTopicLevel;
	private Topic topic;
	private Tquestion tquestion;

	public Tquestiontopic() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TQUEST_TOPIC_ID", unique=true, nullable=false)
	public int getTquestTopicId() {
		return this.tquestTopicId;
	}

	public void setTquestTopicId(int tquestTopicId) {
		this.tquestTopicId = tquestTopicId;
	}


	@Column(name="TQUEST_TOPIC_LEVEL", length=1)
	public String getTquestTopicLevel() {
		return this.tquestTopicLevel;
	}

	public void setTquestTopicLevel(String tquestTopicLevel) {
		this.tquestTopicLevel = tquestTopicLevel;
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


	//bi-directional many-to-one association to Tquestion
	@ManyToOne
	@JoinColumn(name="TQUESTION_ID")
	public Tquestion getTquestion() {
		return this.tquestion;
	}

	public void setTquestion(Tquestion tquestion) {
		this.tquestion = tquestion;
	}

}