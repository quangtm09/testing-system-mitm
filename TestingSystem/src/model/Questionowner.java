package model;// default package
// Generated Mar 12, 2014 10:45:56 PM by Hibernate Tools 3.6.0

/**
 * Questionowner generated by hbm2java
 */
public class Questionowner implements java.io.Serializable {

	private Integer questOwnerId;
	private Question question;
	private Lecturer lecturer;

	public Questionowner() {
	}

	public Questionowner(Question question, Lecturer lecturer) {
		this.question = question;
		this.lecturer = lecturer;
	}

	public Integer getQuestOwnerId() {
		return this.questOwnerId;
	}

	public void setQuestOwnerId(Integer questOwnerId) {
		this.questOwnerId = questOwnerId;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Lecturer getLecturer() {
		return this.lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}

}