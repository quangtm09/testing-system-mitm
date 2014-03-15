package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the subject database table.
 * 
 */
@Entity
@Table(name="subject")
@NamedQuery(name="Subject.findAll", query="SELECT s FROM Subject s")
public class Subject implements Serializable {
	private static final long serialVersionUID = 1L;
	private String subjectId;
	private byte subjectCredit;
	private String subjectName;
	private List<Subjectandtopic> subjectandtopics;
	private List<Subjectassignment> subjectassignments;

	public Subject() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SUBJECT_ID", unique=true, nullable=false, length=10)
	public String getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}


	@Column(name="SUBJECT_CREDIT", nullable=false)
	public byte getSubjectCredit() {
		return this.subjectCredit;
	}

	public void setSubjectCredit(byte subjectCredit) {
		this.subjectCredit = subjectCredit;
	}


	@Column(name="SUBJECT_NAME", nullable=false, length=100)
	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}


	//bi-directional many-to-one association to Subjectandtopic
	@OneToMany(mappedBy="subject")
	public List<Subjectandtopic> getSubjectandtopics() {
		return this.subjectandtopics;
	}

	public void setSubjectandtopics(List<Subjectandtopic> subjectandtopics) {
		this.subjectandtopics = subjectandtopics;
	}

	public Subjectandtopic addSubjectandtopic(Subjectandtopic subjectandtopic) {
		getSubjectandtopics().add(subjectandtopic);
		subjectandtopic.setSubject(this);

		return subjectandtopic;
	}

	public Subjectandtopic removeSubjectandtopic(Subjectandtopic subjectandtopic) {
		getSubjectandtopics().remove(subjectandtopic);
		subjectandtopic.setSubject(null);

		return subjectandtopic;
	}


	//bi-directional many-to-one association to Subjectassignment
	@OneToMany(mappedBy="subject")
	public List<Subjectassignment> getSubjectassignments() {
		return this.subjectassignments;
	}

	public void setSubjectassignments(List<Subjectassignment> subjectassignments) {
		this.subjectassignments = subjectassignments;
	}

	public Subjectassignment addSubjectassignment(Subjectassignment subjectassignment) {
		getSubjectassignments().add(subjectassignment);
		subjectassignment.setSubject(this);

		return subjectassignment;
	}

	public Subjectassignment removeSubjectassignment(Subjectassignment subjectassignment) {
		getSubjectassignments().remove(subjectassignment);
		subjectassignment.setSubject(null);

		return subjectassignment;
	}

}