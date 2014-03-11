package model;

import java.io.Serializable;

public class ExamPerson implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2325455999944562856L;
	public String name;
	public String ExamPersonID;
	public String password;
	public boolean choiceSelected;
	public ExamPerson(){
		
	}
	
	public String getExamPersonID() {
		return ExamPersonID;
	}

	public void setExamPersonID(String examPersonID) {
		ExamPersonID = examPersonID;
	}

	public ExamPerson(String name, String examPersonID, String password,
			boolean choiceSelected) {
		super();
		this.name = name;
		ExamPersonID = examPersonID;
		this.password = password;
		this.choiceSelected = choiceSelected;
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
