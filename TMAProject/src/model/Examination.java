package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import net.sourceforge.jtds.jdbc.DateTime;

public class Examination implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3126174100522266332L;
	private String ExaminationID;
	private String examName;
	private String examType;
	private String location;
	private int numberQuestion;
	private DateTime timeStart;
	private DateTime timeEnd;
	private Set<Test> test = new HashSet<Test>();
	private Set<Examinee> examinee = new HashSet<Examinee>();
	public Examination(){
		
	}
	
		public Set<Examinee> getExaminee() {
		return examinee;
	}

	public void setExaminee(Set<Examinee> examinee) {
		this.examinee = examinee;
	}

		public Set<Test> getTest() {
		return test;
	}
	public void setTest(Set<Test> test) {
		this.test = test;
	}
		public String getExaminationID() {
		return ExaminationID;
	}
	public void setExaminationID(String examinationID) {
		ExaminationID = examinationID;
	}
	
	public Examination(String examinationID, String examName, String examType,
			String location, int numberQuestion, DateTime timeStart,
			DateTime timeEnd, Set<Test> test, Set<Examinee> examinee) {
		super();
		ExaminationID = examinationID;
		this.examName = examName;
		this.examType = examType;
		this.location = location;
		this.numberQuestion = numberQuestion;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.test = test;
		this.examinee = examinee;
	}

	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getNumberQuestion() {
		return numberQuestion;
	}
	public void setNumberQuestion(int numberQuestion) {
		this.numberQuestion = numberQuestion;
	}
	public DateTime getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(DateTime timeStart) {
		this.timeStart = timeStart;
	}
	public DateTime getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(DateTime timeEnd) {
		this.timeEnd = timeEnd;
	}
	
}
