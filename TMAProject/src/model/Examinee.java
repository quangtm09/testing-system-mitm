package model;

import java.io.Serializable;

public class Examinee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2325455999944562856L;
	private String name;
	private String ExamineeID;
	private String password;
	private boolean choiceSelected;
	private Examination examination;
	private Test test;

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Examination getExamination() {
		return examination;
	}

	public void setExamination(Examination examination) {
		this.examination = examination;
	}

	public Examinee() {

	}

	public String getExamineeID() {
		return ExamineeID;
	}

	public void setExamineeID(String examineeID) {
		ExamineeID = examineeID;
	}

	public Examinee(String name, String examineeID, String password,
			boolean choiceSelected, Examination examination, Test test) {
		super();
		this.name = name;
		ExamineeID = examineeID;
		this.password = password;
		this.choiceSelected = choiceSelected;
		this.examination = examination;
		this.test = test;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isChoiceSelected() {
		return choiceSelected;
	}

	public void setChoiceSelected(boolean choiceSelected) {
		this.choiceSelected = choiceSelected;
	}

}
