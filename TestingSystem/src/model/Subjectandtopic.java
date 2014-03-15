package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the subjectandtopic database table.
 * 
 */
@Entity
@Table(name="subjectandtopic")
@NamedQuery(name="Subjectandtopic.findAll", query="SELECT s FROM Subjectandtopic s")
public class Subjectandtopic implements Serializable {
	private static final long serialVersionUID = 1L;
	private int subjectTopId;
	private float subjectTopPercent;
	private Subject subject;
	private Topic topic;

	public Subjectandtopic() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SUBJECT_TOP_ID", unique=true, nullable=false)
	public int getSubjectTopId() {
		return this.subjectTopId;
	}

	public void setSubjectTopId(int subjectTopId) {
		this.subjectTopId = subjectTopId;
	}


	@Column(name="SUBJECT_TOP_PERCENT")
	public float getSubjectTopPercent() {
		return this.subjectTopPercent;
	}

	public void setSubjectTopPercent(float subjectTopPercent) {
		this.subjectTopPercent = subjectTopPercent;
	}


	//bi-directional many-to-one association to Subject
	@ManyToOne
	@JoinColumn(name="SUBJECT_ID")
	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
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