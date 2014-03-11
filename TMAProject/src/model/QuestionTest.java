package model;

public class QuestionTest {
	public boolean choice;
	public boolean correctAnswer;
	public QuestionTest(boolean choice, boolean correctAnswer) {
		super();
		this.choice = choice;
		this.correctAnswer = correctAnswer;
	}
	public boolean isChoice() {
		return choice;
	}
	public void setChoice(boolean choice) {
		this.choice = choice;
	}
	public boolean isCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(boolean correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
}
